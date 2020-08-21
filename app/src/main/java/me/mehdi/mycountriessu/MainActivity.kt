package me.mehdi.mycountriessu

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import me.mehdi.mycountriessu.databinding.ActivityMainBinding
import me.mehdi.mycountriessu.model.AppDatabase
import me.mehdi.mycountriessu.model.Country

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var db : SQLiteDatabase

    lateinit var list: ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = AppDatabase(applicationContext).writableDatabase

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        list = binding.list

        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val addIntent = Intent(this, AddActivity::class.java)
            startActivityForResult(addIntent, 17)
        }

    }

    //ساخت منو
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile -> {
                val myIntent : Intent = Intent(this, AddActivity::class.java)
                myIntent.apply{
                    putExtra("name", "Mohammad")
                }
                startActivity(myIntent)
            }

            R.id.about -> {
                Toast.makeText(applicationContext,  R.string.about_us, Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            17 -> {
                if (resultCode == RESULT_OK) {
                    val country = data?.getStringExtra("keshvar")
                    val capital = data?.getStringExtra("paytakht")
                    val population = data?.getStringExtra("jamiat")

                    val rowId = db.insert("country", "Nothing", ContentValues().apply {
                        put("name", country)
                        put("capital", capital)
                        put("population", population)
                    })
                    if (rowId != -1L) {
                        Toast.makeText(
                            applicationContext,
                            "کشور جدید به دیتابیس اضافه شد",
                            Toast.LENGTH_LONG
                        ).show()
                        updateList()
                    }
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    fun updateList() {
        //TODO: Update the list from database
        //Read database
        val cursor = db.query("country", arrayOf("rowid", "name", "capital", "population"), null, null, null, null, null)
        while(cursor.moveToNext()){
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val paytakht = cursor.getString(cursor.getColumnIndexOrThrow("capital"))
            val jamiat = cursor.getInt(cursor.getColumnIndexOrThrow("population"))
            val keshvar = Country(name, paytakht, jamiat)
            Log.d(TAG, "onActivityResult: Country: ${keshvar.name}, ${keshvar.capital}, ${keshvar.population}")
        }
        cursor.close()

    }
}