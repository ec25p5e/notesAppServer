package com.ec25p5e.data.repository.category

import com.ec25p5e.data.requests.category.CreateCategoryRequest
import com.ec25p5e.data.requests.category.GetCategoryRequest
import com.ec25p5e.data.responses.category.CategoryResponse

interface CategoryRepository {

    suspend fun createCategory(category: CreateCategoryRequest)

    suspend fun getCategories(data: GetCategoryRequest): List<CategoryResponse>
}