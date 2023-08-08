package com.ec25p5e.routes

import com.ec25p5e.data.requests.category.CreateCategoryRequest
import com.ec25p5e.data.requests.category.GetCategoryRequest
import com.ec25p5e.data.responses.BasicApiResponse
import com.ec25p5e.service.CategoryService
import com.ec25p5e.util.ApiResponseMessages.CATEGORY_CREATE_SUCCESSFULLY
import com.ec25p5e.util.ApiResponseMessages.CATEGORY_PUSHED_SUCCESSFULLY
import com.ec25p5e.util.ApiResponseMessages.SUCCESSFULLY_FETCHED_CATEGORY
import com.ec25p5e.util.ApiResponseMessages.SUCCESSFULLY_FETCHED_NOTE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createCategory(
    categoryService: CategoryService
) {
    authenticate {
        post("/api/category/create") {
            val request = call.receiveOrNull<CreateCategoryRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if(request.isIncomplete()) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val response = categoryService.createCategory(request)
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true,
                    message = CATEGORY_CREATE_SUCCESSFULLY,
                    data = response
                )
            )
        }
    }
}

fun Route.pushCategories(
    categoryService: CategoryService
) {
    authenticate {
        post("/api/category/push") {
            val request = call.receiveOrNull<List<CreateCategoryRequest>>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            request.map {
                if(it.isIncomplete()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
            }

            val response = categoryService.pushCategories(request)
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true,
                    message = CATEGORY_PUSHED_SUCCESSFULLY,
                    data = response
                )
            )
        }
    }
}

fun Route.getCategories(
    categoryService: CategoryService
) {
    authenticate {
        post("/api/category/categories") {
            val request = call.receiveOrNull<GetCategoryRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if(request.isIncomplete()) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val categories = categoryService.getCategories(request)
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true,
                    message = SUCCESSFULLY_FETCHED_CATEGORY,
                    data = categories
                )
            )
        }
    }
}