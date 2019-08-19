package com.example.hirakawa.lastapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SyousaiActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.syousai_clear)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "詳細ページ"


        val dbHelper = CustomOpenHelper(this)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val syousai_sa = findViewById(R.id.syousai_sa) as EditText
        val intent = getIntent()
        val data = intent.getStringExtra("sname")
        syousai_sa.setText(data)
        val syousai_pa = findViewById<EditText>(R.id.syousai_pa)
        val database = CustomOpenHelper(this).readableDatabase
        val cursor = database.query("saitopass", arrayOf("sname", "pass"), "sname = ?", arrayOf(data), null, null, null)

        //var value = String()

        //val texts = mutableListOf<String>()
        cursor.use { c ->

            val syousai_pa = findViewById<TextView>(R.id.syousai_pa)
            c.moveToNext()
            syousai_pa.text = c.getString(c.getColumnIndex("pass"))

        }


        val update = findViewById<Button>(R.id.kousin)
        update.setOnClickListener {
            val database = CustomOpenHelper(this).writableDatabase
            val values = ContentValues()
            values.put("sname", syousai_sa.text.toString())
            values.put("pass", findViewById<EditText>(R.id.syousai_pa).text.toString())
            database.update("saitopass",values,"sname = ?", arrayOf(data))
            //database.update("saitopass",values,"pass ?", arrayOf(data))
            syousai_sa.setText("")
            syousai_pa.setText("")

        }


       // cursor.close()
        val delete = findViewById<Button>(R.id.clear)
        delete.setOnClickListener{
            val database = CustomOpenHelper(this).writableDatabase
            database.delete("saitopass", "sname = ?", arrayOf(data))
            syousai_sa.setText("")
            syousai_pa.setText("")
        }


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}