package com.ec25p5e.plugins

import com.ec25p5e.routes.*
import com.ec25p5e.service.NoteService
import com.ec25p5e.service.PostService
import com.ec25p5e.service.UserService
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.http.content.*
import io.ktor.server.http.content.static
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Application.configureRouting(appConfig: HoconApplicationConfig) {
    val userService: UserService by inject(UserService::class.java)
    val postService: PostService by inject(PostService::class.java)
    val noteService: NoteService by inject(NoteService::class.java)

    val jwtIssuer = appConfig.property("jwt.domain").getString()
    val jwtAudience = appConfig.property("jwt.audience").getString()
    val jwtSecret = appConfig.property("jwt.secret").getString()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        authenticate()
        createUser(userService)

        loginUser(
            userService = userService,
            jwtIssuer = jwtIssuer,
            jwtAudience = jwtAudience,
            jwtSecret = jwtSecret
        )

        // Post routes
        getPostsForFollows(postService)

        // Profile routes
        getUserProfile(userService)

        // Note routes
        getNotes(noteService)
        pushNotes(noteService)
        createNote(noteService)

        static {
            resource("static")
        }
    }
}
