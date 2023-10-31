package com.example.notesapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Add Note Button Listener
        val addNoteButton = findViewById<ImageButton>(R.id.addNoteButtonToolbar)
        addNoteButton.setOnClickListener {
            // Navigate to NoteFragment for adding a new note
            navigateToFragment(NoteFragment())
        }

        // User Authentication Button Listener
        findViewById<ImageButton>(R.id.userAuthButton).setOnClickListener {
            // Navigate to UserScreen for authentication
            navigateToFragment(UserScreen())
        }

        // Load the appropriate fragment when the app starts
        if (savedInstanceState == null) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val fragment = if (currentUser == null) {
                // If user is not signed in, navigate to UserScreen
                UserScreen()
            } else {
                // If user is signed in, navigate to HomeFragment
                HomeFragment()
            }
            navigateToFragment(fragment)
        }

        // Listener to update toolbar based on current fragment
        supportFragmentManager.addOnBackStackChangedListener {
            updateToolbar(addNoteButton)
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun updateToolbar(addNoteButton: ImageButton) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment is NoteFragment) {
            // If in NoteFragment, change toolbar button to delete
            supportActionBar?.show()
            addNoteButton.setImageResource(R.drawable.ic_delete_icon)  // Replace with your delete icon
            addNoteButton.setOnClickListener {
                // Show dialog to confirm deletion
                AlertDialog.Builder(this)
                    .setTitle("Delete Note")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Yes") { _, _ ->
                        (currentFragment as NoteFragment).deleteNote()
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        } else if (currentFragment is UserScreen) {
            // If in UserScreen, hide the toolbar
            supportActionBar?.hide()
        } else {
            // If in any other fragment, change toolbar button to add note
            supportActionBar?.show()
            addNoteButton.setImageResource(R.drawable.ic_add_icon)  // Replace with your add note icon
            addNoteButton.setOnClickListener {
                navigateToFragment(NoteFragment())
            }
        }
    }
}
