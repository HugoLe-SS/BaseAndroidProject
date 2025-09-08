package com.example.datasource.local.RoomDB

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

class LocalDataSourceImpl(
    @ApplicationContext private val context: Context
): LocalDataSource {
    private val appDB: AppDB = AppDB.getDatabase(context)

}