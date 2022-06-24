package com.d3if4043.kalkulator_jodoh.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InputEntity::class], version = 3, exportSchema = false)
abstract class InputDb : RoomDatabase() {
    abstract val dao: InputDao
    companion object {
        @Volatile
        private var INSTANCE: InputDb? = null
        fun getInstance(context: Context): InputDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        InputDb::class.java,
                        "input.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}