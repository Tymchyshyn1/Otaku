package com.example.a16_rxjava_data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a16_rxjava_data.database.models.AnimePosterConverters
import com.example.a16_rxjava_data.database.models.LocalAnimePosterEntity
import javax.inject.Singleton


@Database(entities = [LocalAnimePosterEntity::class], version = 1, exportSchema = false)
@TypeConverters(AnimePosterConverters::class)
@Singleton
abstract class ShikimoriDataBase : RoomDatabase() {

    abstract fun getCurrencyDao(): ShikimoriDAO

    companion object {
        @Volatile
        private var INSTANCE: ShikimoriDataBase? = null

        fun getDatabase(context: Context): ShikimoriDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShikimoriDataBase::class.java,
                    "shikimori_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}