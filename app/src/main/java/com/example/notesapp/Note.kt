package com.example.notesapp

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import java.io.Serializable

/**
 * Represents a note entity in the database with a unique ID, title, and description.
 *
 * @property id Unique identifier for the note.
 * @property title Title of the note.
 * @property description Description or content of the note.
 *
 * @author Matt Gacek
 */

data class Note( 
    var id: String = "",
    var title: String = "",
    var description: String = ""
)



