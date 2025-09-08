package com.example.datasource.local.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/*
@Database(entities =
    [

    ],
    version = 1,
    exportSchema = false)
*/
//@TypeConverters(Converter::class)
abstract class AppDB : RoomDatabase() {


    companion object{

        @Volatile
        private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDB {
            return Room.databaseBuilder(context, AppDB::class.java, TableConstants.APP_DB_NAME)
                .fallbackToDestructiveMigrationFrom(true)
                .allowMainThreadQueries()
                .build()
        }

        fun destroyDataBase(){
            instance = null
        }

    }

}

object TableConstants {
    const val APP_DB_NAME = ""
}