package com.example.task1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task1.R
import com.example.task1.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, mainFragment)
            .commit()
    }
}

