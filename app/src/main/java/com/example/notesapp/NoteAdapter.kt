package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for displaying a list of notes in a RecyclerView.
 * Provides functionality for clicking on a note title and deleting a note.
 *
 * @param onTitleClick Callback function for when a note title is clicked.
 * @param onDeleteClick Optional callback function for when the delete button of a note is clicked.
 *
 * @author Matt Gacek
 */
class NoteAdapter(
    private val onTitleClick: (Note) -> Unit,
    private val onDeleteClick: ((Note) -> Unit)? = null
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        fun bind(note: Note) {
            titleTextView.text = note.title
            itemView.setOnClickListener { onTitleClick(note) }
            deleteButton.setOnClickListener { onDeleteClick?.invoke(note) }}}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)}
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)}
    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id}
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem}}}