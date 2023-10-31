package com.example.notesapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var viewModel: NotesViewModel
    private val adapter by lazy {
        NoteAdapter(
            onTitleClick = { note -> navigateToNoteFragment(note.id) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
        val notesRecyclerView = view.findViewById<RecyclerView>(R.id.notesRecyclerView)
        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        notesRecyclerView.adapter = adapter
        setupNotesObserver()
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        setupNotesObserver()
    }

    private fun setupNotesObserver() {
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }
    }

    private fun navigateToNoteFragment(noteId: String?) {
        val fragment = NoteFragment().apply {
            arguments = Bundle().apply {
                putString("NOTE_ID", noteId)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

}
