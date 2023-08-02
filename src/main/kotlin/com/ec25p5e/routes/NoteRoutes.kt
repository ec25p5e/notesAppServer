package com.ec25p5e.routes

import com.ec25p5e.data.requests.CreateNoteRequest
import com.ec25p5e.data.requests.NoteRequest
import com.ec25p5e.data.requests.PushNotesRequest
import com.ec25p5e.data.responses.NoteResponse
import com.ec25p5e.util.ApiResponseMessages.SUCCESSFULLY_FETCHED_NOTE
import com.ec25p5e.data.responses.BasicApiResponse
import com.ec25p5e.service.NoteService
import com.ec25p5e.util.ApiResponseMessages.NOTE_CREATE_SUCCESSFULLY
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getNotes(
    noteService: NoteService
) {
    // authenticate {
        post("/api/note/notes") {
            val request = call.receiveOrNull<NoteRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if(request.userId.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val notes = noteService.getNotes(request.userId)
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true,
                    message = SUCCESSFULLY_FETCHED_NOTE,
                    data = notes
                )
            )
        }
    // }
}

fun Route.pushNotes(
    noteService: NoteService
) {
    // authenticate {
        post("/api/note/push") {
            val request = call.receiveOrNull<List<PushNotesRequest>>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            request.map {
                if(it.isIncomplete()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
            }

            val notes = noteService.pushNotes(request)
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    message = notes.message,
                    successful = true,
                    data = null
                )
            )
        }
    // }
}

fun Route.createNote(
    noteService: NoteService
) {
    // authenticate {
        post("/api/note/create") {
            val request = call.receiveOrNull<CreateNoteRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if(request.isIncomplete()) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val note = noteService.createNote(request)
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    message = NOTE_CREATE_SUCCESSFULLY,
                    successful = true,
                    data = note
                )
            )
        }
    // }
}