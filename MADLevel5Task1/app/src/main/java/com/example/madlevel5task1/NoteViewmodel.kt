package com.example.madlevel5task1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.madlevel5task1.repository.NoteRepository

class NoteViewmodel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()
}