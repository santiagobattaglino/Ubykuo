package com.battaglino.santiago.ubykuo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.battaglino.santiago.ubykuo.db.converters.DateConverter
import com.battaglino.santiago.ubykuo.db.converters.OwnerConverter
import com.battaglino.santiago.ubykuo.db.dao.RepoDao
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.global.Constants

/**
 * Created by Santiago Battaglino.
 * This class is used to create the database and get an instance of it.
 */
@Database(entities = [Repo::class], version = 1)
@TypeConverters(DateConverter::class, OwnerConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repoModel(): RepoDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabaseBuilder(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, Constants.APP_DB_NAME)
                        .allowMainThreadQueries()
                        .build()
    }

    /*companion object {

        private var INSTANCE: AppDatabase? = null

        /*
        *  Data Access Objects (DAO) to manipulate our db table.
        *  We have to create an abstract method for every DAO class that we create.
        *  inMemoryDatabaseBuilder or databaseBuilder
        */
        fun getDatabaseBuilder(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = if (BuildConfig.DEBUG) {
                    Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, Constants.APP_DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                } else {
                    Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, Constants.APP_DB_NAME)
                            .build()
                }
            }
            return INSTANCE as AppDatabase
        }

        fun getInMemoryDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = if (BuildConfig.DEBUG) {
                    Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                            .allowMainThreadQueries()
                            .build()
                } else {
                    Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                            .build()
                }
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }*/

    // If you need to update your database version, and add entities or new columns,
    // you gonna have to implement a Migration operation in order to avoid crashes or users losing data

    /*public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE product "
                    + " ADD COLUMN street TEXT, number TEXT, neighborhood TEXT");
        }
    };*/
}
