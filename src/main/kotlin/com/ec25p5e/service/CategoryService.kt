package com.ec25p5e.service

import com.ec25p5e.data.repository.category.CategoryRepository
import com.ec25p5e.data.requests.category.CreateCategoryRequest
import com.ec25p5e.data.requests.category.GetCategoryRequest
import com.ec25p5e.data.responses.category.CategoryResponse

class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    suspend fun createCategory(category: CreateCategoryRequest) {
        categoryRepository.createCategory(category)
    }

    suspend fun getCategories(data: GetCategoryRequest): List<CategoryResponse> {
        return categoryRepository.getCategories(data)
    }
}