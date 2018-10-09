package com.battaglino.santiago.ubykuo.db.converters

import android.arch.persistence.room.TypeConverter
import android.text.TextUtils
import com.battaglino.santiago.ubykuo.db.entity.Owner
import java.util.*

/**
 * Created by Santiago Battaglino.
 */
class OwnerConverter {

    @TypeConverter
    fun fromOwner(owner: Owner?): String? {
        return if (owner == null) null else String.format(Locale.getDefault(), "%s %s %s",
                owner.login,
                owner.type,
                owner.url)
    }

    @TypeConverter
    fun toOwner(fullOwner: String): Owner? {
        if (TextUtils.isEmpty(fullOwner)) {
            return null
        }

        val parts = fullOwner.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val owner = Owner()
        owner.login = parts[0]
        owner.type = parts[1]
        owner.url = parts[2]

        return owner
    }
}