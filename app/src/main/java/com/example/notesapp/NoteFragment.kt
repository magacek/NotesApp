package com.example.notesapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
/**
 * A Fragment class that represents the note editing screen of the NotesApp.
 * This fragment allows the user to create a new note or edit an existing one.
 * It provides fields for the title and description of the note and buttons to save or delete the note.
 * If a note ID is provided in the fragment's arguments, it fetches and displays the corresponding note for editing.
 *
 * @author Matt
 */

class NoteFragment : Fragment(R.layout.fragment_note) {
    private lateinit var viewModel: NotesViewModel
    private var currentNote: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]

        // Fetch note ID from arguments
        val noteId = arguments?.getString("NOTE_ID")

        // If noteId is not null, fetch the note from the ViewModel
        if (noteId != null) {
            currentNote = viewModel.getNoteById(noteId)
        }

        currentNote?.let {
            view.findViewById<EditText>(R.id.titleEditText).setText(it.title)
            view.findViewById<EditText>(R.id.descriptionEditText).setText(it.description)
        }

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            val title = view.findViewById<EditText>(R.id.titleEditText).text.toString()
            val description = view.findViewById<EditText>(R.id.descriptionEditText).text.toString()

            lifecycleScope.launch {
                if (currentNote != null) {
                    val updatedNote = currentNote!!.copy(title = title, description = description)
                    viewModel.update(updatedNote)
                } else {
                    val newNote = Note(id = "", title = title, description = description)
                    viewModel.insert(newNote)
                }
                parentFragmentManager.popBackStack()
            }

        }

    }
    fun deleteNote() {
        currentNote?.let { note ->
            lifecycleScope.launch {
                viewModel.delete(note)
                parentFragmentManager.popBackStack()
            }
        }
    }
}
