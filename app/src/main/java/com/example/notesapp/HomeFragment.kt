package com.example.notesapp
import android.app.AlertDialog
import com.example.notesapp.Note
import java.io.Serializable

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager // Import this
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

/**
 * Represents the home screen where notes are displayed in a list.
 * Provides functionality to view, add, and delete notes.
 *
 * @author Matt Gacek
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: NotesViewModel
    private val adapter by lazy {
        NoteAdapter(
            onTitleClick = { note -> navigateToNoteFragment(note) },
            onDeleteClick = { note -> showDeleteDialog(note) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]

        val notesRecyclerView = view.findViewById<RecyclerView>(R.id.notesRecyclerView)

        // Set the layout manager for the RecyclerView
        notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        notesRecyclerView.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }

        view.findViewById<Button>(R.id.addNoteButton).setOnClickListener {
            navigateToNoteFragment(null)
        }
    }

    private fun navigateToNoteFragment(note: Note?) {
        val fragment = NoteFragment().apply {
            arguments = Bundle().apply {
                putSerializable("NOTE", note)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showDeleteDialog(note: Note) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes") { _, _ ->
                lifecycleScope.launch {
                    viewModel.delete(note)
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
