package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * The main activity for the NotesApp, serving as the entry point of the application.
 * This activity is responsible for loading the HomeFragment when the app starts.
 *
 * @author Matt
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}
