package me.mehdi.mycountriessu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import me.mehdi.mycountriessu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            val myIntent : Intent = Intent(this, ProfileActivity::class.java)
            myIntent.apply{
                putExtra("name", "Mohammad")
            }
            startActivity(myIntent)
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
                val myIntent : Intent = Intent(this, ProfileActivity::class.java)
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
}