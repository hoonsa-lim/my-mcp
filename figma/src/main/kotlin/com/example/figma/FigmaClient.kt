package com.example.figma

interface FigmaClient {
    suspend fun getFile(fileId: String): String
    suspend fun getComments(fileId: String): String
    suspend fun updateDesign(fileId: String, changes: Map<String, Any>): String
} 