package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
/**
 * An adapter class for managing and displaying a list of notes in a RecyclerView.
 * This class handles the creation of view holders for the notes, binds the data to
 * the views, and updates the list of notes efficiently using DiffUtil.
 *
 * @param onTitleClick A callback function to be invoked when the title of a note is clicked.
 * @param onDeleteClick An optional callback function to be invoked when a note is to be deleted.
 * @author Matt
 */

class NoteAdapter(
    private val onTitleClick: (Note) -> Unit,
    private val onDeleteClick: ((Note) -> Unit)? = null
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(note: Note) {
            titleTextView.text = note.title
            descriptionTextView.text = note.description
            itemView.setOnClickListener { onTitleClick(note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
