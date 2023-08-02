package com.ec25p5e.routes

import com.ec25p5e.service.UserService
import com.ec25p5e.util.ApiResponseMessages
import com.ec25p5e.util.QueryParams
import com.ec25p5e.data.responses.BasicApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getUserProfile(userService: UserService) {
    authenticate {
        get("/api/user/profile") {
            val userId = call.parameters[QueryParams.PARAM_USER_ID]

            if(userId.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val profileResponse = userService.getUserProfile(userId, call.userId)

            if(profileResponse == null) {
                call.respond(
                    HttpStatusCode.OK,
                    BasicApiResponse<Unit>(
                        successful = false,
                        message = ApiResponseMessages.USER_NOT_FOUND
                    )
                )

                return@get
            }

            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true,
                    data = profileResponse
                )
            )
        }
    }
}