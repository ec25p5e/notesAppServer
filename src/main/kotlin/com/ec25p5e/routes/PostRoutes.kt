package com.ec25p5e.routes

import com.ec25p5e.service.PostService
import com.ec25p5e.util.Constants
import com.ec25p5e.util.QueryParams
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getPostsForFollows(
    postService: PostService,
) {
    authenticate {
        get("/api/post/get") {
            val page = call.parameters[QueryParams.PARAM_PAGE]?.toIntOrNull() ?: 0
            val pageSize =
                call.parameters[QueryParams.PARAM_PAGE_SIZE]?.toIntOrNull() ?: Constants.DEFAULT_PAGE_SIZE

            val posts = postService.getPostsForFollows(call.userId, page, pageSize)
            call.respond(
                HttpStatusCode.OK,
                posts
            )
        }
    }
}