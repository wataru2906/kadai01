package com.example.hirakawa.lastapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CustomOpenHelper
(context: Context) : SQLiteOpenHelper(context, "ItiranDb",null,1){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE saitopass(" +
        "sname String PRIMARY KEY," +"pass integer not null);")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       p0?.execSQL("DROP TABLE IF EXISTS[saitopass];")
        onCreate(p0)
    }
   /* fun queryTexts(context: Context) : List<String>{

        val database = CustomOpenHelper(context).readableDatabase

        val cursor = database.query("saitopass",null,null,null,null,null,null)

        val texts = mutableListOf<String>()
        cursor.use{
            while(cursor.moveToNext()){
                val text = cursor.getString(cursor.getColumnIndex("text"))
                texts.add(text)
            }
        }
        database.close()
        return texts
    }
    fun insertText(context: Context,text:String){
        val database = CustomOpenHelper(context).writableDatabase


    database.use{ db->

        val record = ContentValues().apply {
            put("text",text)
            put("text2",text)
        }
        db.insert("texts",null,record)

    }

}
}
        */}