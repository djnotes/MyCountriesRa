package me.mehdi.mycountriessu.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, "countries.db", null, 2) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Companion.SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(Companion.SQL_DELETE)
        onCreate(db)
    }

    companion object {
        const val SQL_CREATE = "CREATE TABLE country (name TEXT, capital TEXT, population INT)"
        const val SQL_DELETE = "DROP TABLE country"
    }
}