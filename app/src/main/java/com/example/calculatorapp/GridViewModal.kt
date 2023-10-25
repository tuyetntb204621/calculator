package com.example.calculatorapp

enum class Category {
    OPERAND, OPERATOR,
}

data class GridViewModal (
    val keyName: String,
    val category: Category
)