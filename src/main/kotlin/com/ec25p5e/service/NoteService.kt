package com.ec25p5e.service

import com.ec25p5e.data.repository.note.NoteRepository
import com.ec25p5e.data.requests.note.CreateNoteRequest
import com.ec25p5e.data.requests.note.DeleteNoteRequest
import com.ec25p5e.data.responses.PushNotesResponse
import com.ec25p5e.data.responses.BasicApiResponse
import com.ec25p5e.data.responses.NoteResponse

class NoteService(
    private val noteRepository: NoteRepository
) {

    suspend fun getNotes(userId: String): List<NoteResponse> {
        return noteRepository.getNotes(userId)
    }

    suspend fun pushNotes(notes: List<PushNotesResponse>): BasicApiResponse<Unit> {
        return noteRepository.pushNotes(notes)
    }

    suspend fun createNote(note: CreateNoteRequest) {
        noteRepository.createNote(note)
    }

    suspend fun deleteNote(note: DeleteNoteRequest) {
        noteRepository.deleteNote(note)
    }
}