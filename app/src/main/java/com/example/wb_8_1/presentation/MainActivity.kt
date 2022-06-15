package com.example.wb_8_1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wb_8_1.R
import com.example.wb_8_1.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment()).commit()
        }
    }
}