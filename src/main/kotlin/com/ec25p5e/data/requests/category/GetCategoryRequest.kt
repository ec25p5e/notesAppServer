package com.ec25p5e.data.requests.category

data class GetCategoryRequest(
    val userId: String
) {

    fun isIncomplete(): Boolean {
        if(userId.isBlank())
            return true

        return false
    }
}
