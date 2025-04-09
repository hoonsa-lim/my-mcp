package com.example.figma.api

import retrofit2.http.*

interface FigmaApi {
    @GET("v1/files/{file_key}")
    suspend fun getFile(
        @Path("file_key") fileKey: String,
        @Header("X-Figma-Token") token: String
    ): FigmaFileResponse

    @GET("v1/files/{file_key}/comments")
    suspend fun getComments(
        @Path("file_key") fileKey: String,
        @Header("X-Figma-Token") token: String
    ): FigmaCommentsResponse

    @PATCH("v1/files/{file_key}")
    suspend fun updateDesign(
        @Path("file_key") fileKey: String,
        @Header("X-Figma-Token") token: String,
        @Body changes: Map<String, Any>
    ): FigmaUpdateResponse
} 