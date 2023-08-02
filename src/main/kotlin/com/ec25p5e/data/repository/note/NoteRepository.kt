package com.ec25p5e.data.repository.note

import com.ec25p5e.data.models.Note
import com.ec25p5e.data.requests.CreateNoteRequest
import com.ec25p5e.data.requests.PushNotesRequest
import com.ec25p5e.data.responses.BasicApiResponse
import com.ec25p5e.data.responses.NoteResponse

interface NoteRepository {

    suspend fun getNotes(userId: String): List<NoteResponse>

    suspend fun pushNotes(notes: List<PushNotesRequest>): BasicApiResponse<Unit>

    suspend fun createNote(note: CreateNoteRequest): Note?
}