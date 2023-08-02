package com.ec25p5e.service

import com.ec25p5e.data.models.Note
import com.ec25p5e.data.repository.note.NoteRepository
import com.ec25p5e.data.requests.CreateNoteRequest
import com.ec25p5e.data.requests.PushNotesRequest
import com.ec25p5e.data.responses.BasicApiResponse
import com.ec25p5e.data.responses.NoteResponse

class NoteService(
    private val noteRepository: NoteRepository
) {

    suspend fun getNotes(userId: String): List<NoteResponse> {
        return noteRepository.getNotes(userId)
    }

    suspend fun pushNotes(notes: List<PushNotesRequest>): BasicApiResponse<Unit> {
        return noteRepository.pushNotes(notes)
    }

    suspend fun createNote(note: CreateNoteRequest): Note? {
        return noteRepository.createNote(note)
    }
}