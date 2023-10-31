package com.example.notesapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class UserScreen : Fragment(R.layout.fragment_user_screen) {

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)

        view.findViewById<Button>(R.id.signUpButton).setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            signUp(email, password)
        }

        view.findViewById<Button>(R.id.signInButton).setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            signIn(email, password)
        }

        view.findViewById<Button>(R.id.signOutButton).setOnClickListener {
            signOut()
        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    navigateToHomeFragment()
                } else {
                    Toast.makeText(context, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    navigateToHomeFragment()
                } else {
                    Toast.makeText(context, "Sign In Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signOut() {
        auth.signOut()
        Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show()
        navigateToHomeFragment()
    }

    private fun navigateToHomeFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
    }
}
