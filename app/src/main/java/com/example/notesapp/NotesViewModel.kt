package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val notesDatabase = NotesDatabase.getDatabase(application)
    private val notesDao = notesDatabase.noteDao()

    var notes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun getAllNotesForDebugging(): List<Note> {
        return notesDao.getAllNotes().value ?: listOf()
    }


    fun refreshNotes() {
        notes = notesDao.getAllNotes()
    }

}
