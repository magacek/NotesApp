package com.example.notesapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class NoteFragment : Fragment(R.layout.fragment_note) {

    private lateinit var viewModel: NotesViewModel
    private var currentNote: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]


        currentNote = arguments?.getSerializable("NOTE") as Note?
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
                    val newNote = Note(id = 0, title = title, description = description)
                    viewModel.insert(newNote)
                }
                viewModel.refreshNotes()

                val allNotes = viewModel.getAllNotesForDebugging()
                allNotes.forEach { note ->
                    Log.d("DEBUG", "Note ID: ${note.id}, Title: ${note.title}")
                }

                parentFragmentManager.popBackStack()
            }
        }

    }
}
