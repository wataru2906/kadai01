package com.example.hirakawa.lastapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_touroku.*
import android.content.Intent as Intent1


//data class SaitoPass(val SANA : String, val PANA:Int)
class TourokuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touroku)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "登録ページ"



        val dbHelper = CustomOpenHelper(this)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val SaName = findViewById(R.id.EditSaName) as EditText
     //   val intent = getIntent()
        val data = intent.getStringExtra("sname")
        EditSaName.setText(data)
        val PaName = findViewById<EditText>(R.id.EditPass)




 /*       val database = CustomOpenHelper(this).readableDatabase
        val cursor = database.query("saitopass", arrayOf("sname", "pass"), "sname = ?", arrayOf(data), null, null, null)

        //var value = String()

        //val texts = mutableListOf<String>()
        cursor.use { c ->

            val PaName = findViewById<TextView>(R.id.EditPaName)
            c.moveToNext()
            PaName.text = c.getString(c.getColumnIndex("pass"))

        }

        cursor.close()


        val unko = intent.getStringExtra("sname")
        Toast.makeText(this, unko + "が渡されました", Toast.LENGTH_SHORT).show()
*/

        // val intent2 = android.content.Intent(this, MainActivity::class.java)
        //  intent2.getStringExtra("pass")
        //挿入ボタン
        val touroku = findViewById<Button>(R.id.touroku)
        touroku.setOnClickListener {
            //リストにインサート
            if (isPraimarykeyCheck(db, EditSaName.text.toString())) {
                Toast.makeText(this, "keyは存在します", Toast.LENGTH_SHORT).show()
            } else {
                val values = ContentValues()
                values.put("sname", SaName.text.toString())
                values.put("pass", findViewById<EditText>(R.id.EditPass).text.toString())

                //Toast.makeText(this,findViewById<EditText>(R.id.EditPaName).text.toString(),Toast.LENGTH_SHORT).show()
                db?.insertOrThrow("saitopass", null, values)
                SaName.setText("")
                PaName.setText("")

            }

        }

    }



        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                android.R.id.home -> finish()
                else -> return super.onOptionsItemSelected(item)
            }
            return true
        }

        override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
            super.onSaveInstanceState(outState, outPersistentState)
        }


        //  this.startActivity<EditCommentActivity>("comment_id" to comment.id)*/
        fun isPraimarykeyCheck(db: SQLiteDatabase, key: String): Boolean {
            val sql = "select count(*) as cnt from saitopass where sname = ?"
            val cur = db.rawQuery(sql, arrayOf(key))
            cur.moveToFirst()
            val cnt = cur.getInt(cur.getColumnIndex("cnt"))
            cur.close()
            return cnt > 0
        }



    }
