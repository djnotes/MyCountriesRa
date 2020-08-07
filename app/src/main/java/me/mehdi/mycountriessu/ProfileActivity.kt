package me.mehdi.mycountriessu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.mehdi.mycountriessu.databinding.ActivitySecondBinding

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val userName = intent.getStringExtra("name")
        binding.message.text = "Hello $userName"

    }
}