package com.firozpocyt.androidnotesapp.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firozpocyt.androidnotesapp.models.NoteRequest
import com.firozpocyt.androidnotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    val noteLiveData get() = noteRepository.noteLiveData
    val statusLiveData get() = noteRepository.statusLiveData

    fun getNote(){
        viewModelScope.launch {
        noteRepository.getNotes()
        }
    }

    fun createNote(noteRequest: NoteRequest){
        viewModelScope.launch {
            noteRepository.createNote(noteRequest)
        }
    }

    fun updateNote(notedId : String, noteRequest: NoteRequest){
        viewModelScope.launch {
            noteRepository.updateNote(notedId,noteRequest)
        }
    }

    fun deleteNote(notedId: String){
        viewModelScope.launch {
            noteRepository.deleteNote(notedId)
        }
    }
}