package com.example.notesapp

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import java.io.Serializable


/**
 * A data class representing a note in the NotesApp.
 * Each note consists of an ID, a title, and a description.
 *
 * @author Matt
 */

data class Note( 
    var id: String = "",
    var title: String = "",
    var description: String = ""
)



