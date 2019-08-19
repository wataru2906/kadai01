package com.example.hirakawa.lastapp

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_touroku.*
import java.sql.DatabaseMetaData

data class saitopass(val SaNa : String,val PaNa:String)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listmake()

        button.setOnClickListener {
            val intent = Intent(this, TourokuActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        listmake()
    }

    fun listmake(){

        val list = mutableListOf<dataarray>()
        var data:dataarray
        val dbhelper=CustomOpenHelper(this)
        val db:SQLiteDatabase?=dbhelper.readableDatabase
        val cur = db?.query("saitopass", arrayOf("sname","pass"),null,null,null,null,null,null)
        val listView = findViewById(R.id.listview) as ListView
        cur.use{
            while(cur!!.moveToNext()){
                val sname = cur.getString(cur.getColumnIndex("sname"))
                val pass =cur.getString(cur.getColumnIndex("pass"))
                list.add(dataarray(sname,pass))

                listView.adapter = CustomAdapter(this,list)
            }

            cur.moveToLast()
            //Toast.makeText(this,cur.getString(cur.getColumnIndex("sname")),Toast.LENGTH_SHORT).show()
            db?.close()

        }
       //リストタップ
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val s = CustomAdapter(this,list).getItem(position).name

            val intent= Intent(this, SyousaiActivity::class.java)
         // intent.putExtra("sname", EditSaName?.getText().toString());
            // intent.putExtra("pass", EditPaName?.getText().toString());
             intent.putExtra("sname",s)
            startActivity(intent)

        }
    }


}

