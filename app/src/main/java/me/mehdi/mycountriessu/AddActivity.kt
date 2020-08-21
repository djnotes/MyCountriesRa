package me.mehdi.mycountriessu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.mehdi.mycountriessu.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityAddBinding = ActivityAddBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.save.setOnClickListener {
            val country = binding.countryName.text.toString()
            val capital = binding.capitalName.text.toString()
            val population = binding.population.text.toString()

            if(country.isEmpty() || capital.isEmpty() || population.isEmpty()){
                Toast.makeText(applicationContext, "لطفا همه موارد را پر کنید", Toast.LENGTH_SHORT).show()
            }
            else {
                val data = Intent().apply {
                    putExtra("keshvar", country)
                    putExtra("paytakht", capital)
                    putExtra("jamiat", population)
                }
                setResult(RESULT_OK, data)
                finish()
            }
        }





    }
}