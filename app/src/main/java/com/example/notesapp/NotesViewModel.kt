package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NotesViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("notes")
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    init {
        loadNotes()
    }

    private fun loadNotes() {
        val userNotesRef = database.child(auth.currentUser?.uid ?: "")
        userNotesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val notesList = mutableListOf<Note>()
                for (noteSnapshot in snapshot.children) {
                    val note = noteSnapshot.getValue(Note::class.java)
                    if (note != null) {
                        notesList.add(note)
                    }
                }
                _notes.value = notesList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun insert(note: Note) {
        val userNotesRef = database.child(auth.currentUser?.uid ?: "")
        val noteId = userNotesRef.push().key ?: return
        note.id = noteId
        userNotesRef.child(noteId).setValue(note)
    }

    fun update(note: Note) {
        val userNotesRef = database.child(auth.currentUser?.uid ?: "")
        userNotesRef.child(note.id).setValue(note)
    }

    fun delete(note: Note) {
        val userNotesRef = database.child(auth.currentUser?.uid ?: "")
        userNotesRef.child(note.id).removeValue()
    }

    fun getNoteById(noteId: String): Note? {
        return _notes.value?.find { it.id == noteId }
    }
}
