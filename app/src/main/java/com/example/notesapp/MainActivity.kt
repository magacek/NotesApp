package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Add Note Button Listener
        findViewById<ImageButton>(R.id.addNoteButtonToolbar).setOnClickListener {
            // Navigate to NoteScreen to add a new note
            // Replace with your navigation logic
        }

        // User Authentication Button Listener
        findViewById<ImageButton>(R.id.userAuthButton).setOnClickListener {
            // Navigate to UserScreen for authentication
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserScreen())
                .addToBackStack(null) // Allows you to navigate back to HomeFragment
                .commit()
        }


        // Load the HomeFragment when the app starts
        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}
