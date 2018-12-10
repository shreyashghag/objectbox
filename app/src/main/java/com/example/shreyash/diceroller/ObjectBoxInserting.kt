package com.example.shreyash.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager

import android.util.Log
import com.example.shreyash.diceroller.Adapter.NoteAdapter
import com.example.shreyash.diceroller.Model.EncryptedProduct
import com.example.shreyash.diceroller.Model.Product
import com.example.shreyash.diceroller.Security.Security
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_object_box_inserting.*

class ObjectBoxInserting : AppCompatActivity() {
    var name1 : String = ""
    var age1 : String = ""
    var email1 : String = ""

    private lateinit var noteList: List<Product>
    private lateinit var noteList1: List<EncryptedProduct>
    private lateinit var notesBox: Box<Product>
    private lateinit var notesBox2: Box<EncryptedProduct>
    private lateinit var noteAdapter:NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_box_inserting)
        notesBox = (application as BaseApp).boxStore.boxFor(Product::class.java)
        notesBox2 = (application as BaseApp).boxStore.boxFor(EncryptedProduct::class.java)
        //Use anko android extensions to get the id's

        val gridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        gridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        recyclerViewNotes.setLayoutManager(gridLayoutManager)

        //Use anko android extensions to get the id's
        buttonSaveNote.setOnClickListener {
            validateNote()

        }
        populateNotes()

    }
    private fun populateNotes() {
        noteList1 = notesBox2.query().build().find()
        val count = noteList1.size

        if (count != 0) {
            noteAdapter = NoteAdapter(this, noteList1)
            recyclerViewNotes.setAdapter(noteAdapter)
        }
    }

    private fun validateNote() {
        /**
         * We check to see if user has added anything to the note edit text box
         */
        name1 = name.text.toString().trim()
        age1 = age.text.toString().trim()
        email1 = email.text.toString().trim()
        if (name1.isNullOrEmpty() && age1.isNullOrEmpty() && email1.isNullOrEmpty()) {
            //Set error to the edit text box and terminate
            name.setError("Field can not be blank!")
            /*age.setError("Field can not be blank!")
            email.setError("Field can not be blank!")
            */
            return
        }
        //We have a note, proceed to save!
        addNote()
    }

    private fun addNote() {
        val note =Product(Name =name1,Age = age1,Email =email1)
        val note1 =EncryptedProduct(Name =Security.encrypt(note.Name),Age =Security.encrypt( age1),Email =Security.encrypt(email1))
        notesBox.put(note)
        notesBox2.put(note1)
        Log.d("App.Tag", "Inserted new note, ID: ${note.id}")
        Log.d("App.Tag", "Inserted new note, ID: ${note1.id}")
        populateNotes()
    }
}