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
@Database(entities = [Repo::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class, OwnerConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repoModel(): RepoDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabaseBuilder(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, Constants.APP_DB_NAME)
                        .allowMainThreadQueries()
                        .build()
    }

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
