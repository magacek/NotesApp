package com.example.notesapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
/**
 * A Fragment class that represents the user authentication screen of the NotesApp.
 * This fragment provides options for the user to sign up, sign in, and sign out.
 * Upon successful authentication, the fragment navigates to the HomeFragment.
 * The UI is dynamically updated based on the authentication state of the user.
 *
 * @author Matt
 */

class UserScreen : Fragment(R.layout.fragment_user_screen) {

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val signUpButton = view.findViewById<Button>(R.id.signUpButton)
        val signInButton = view.findViewById<Button>(R.id.signInButton)
        val signOutButton = view.findViewById<Button>(R.id.signOutButton)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            signUp(email, password)
        }

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            signIn(email, password)
        }

        signOutButton.setOnClickListener {
            signOut()
        }

        updateUI()
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    updateUI()
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
                    updateUI()
                    navigateToHomeFragment()
                } else {
                    Toast.makeText(context, "Sign In Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun signOut() {
        auth.signOut()
        Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show()
        updateUI()
    }

    private fun navigateToHomeFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
    }

    private fun updateUI() {
        val isSignedIn = auth.currentUser != null
        view?.findViewById<EditText>(R.id.emailEditText)?.visibility = if (isSignedIn) View.GONE else View.VISIBLE
        view?.findViewById<EditText>(R.id.passwordEditText)?.visibility = if (isSignedIn) View.GONE else View.VISIBLE
        view?.findViewById<Button>(R.id.signUpButton)?.visibility = if (isSignedIn) View.GONE else View.VISIBLE
        view?.findViewById<Button>(R.id.signInButton)?.visibility = if (isSignedIn) View.GONE else View.VISIBLE
        view?.findViewById<Button>(R.id.signOutButton)?.visibility = if (isSignedIn) View.VISIBLE else View.GONE
    }
}
