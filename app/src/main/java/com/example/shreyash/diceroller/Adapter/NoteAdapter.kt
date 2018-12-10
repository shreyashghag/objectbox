package com.example.shreyash.diceroller.Adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.shreyash.diceroller.Model.EncryptedProduct
import com.example.shreyash.diceroller.Model.Product
import com.example.shreyash.diceroller.R
import com.example.shreyash.diceroller.Security.Security

class NoteAdapter(internal var context: Context, internal var note: List<EncryptedProduct>) : RecyclerView.Adapter<NoteAdapter.NoteVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return NoteVH(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteVH, position: Int) {
        holder.note.text = Security.decrypt(note[position].Name)
        holder.date.text = Security.decrypt(note[position].Email)
    }

    override fun getItemCount(): Int {
        return note.size
    }
    inner class NoteVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var note: TextView
        var date: TextView

        init {

            note = itemView.findViewById(R.id.note) as TextView
            date = itemView.findViewById(R.id.noteDate) as TextView
        }
    }
}