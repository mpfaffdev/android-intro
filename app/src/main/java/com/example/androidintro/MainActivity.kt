package com.example.androidintro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidintro.view.compose.ComposeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ComposeFragment.newInstance())
                .commitNow()
        }
    }
}
