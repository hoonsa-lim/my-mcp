package com.example.figma.api

import com.google.gson.annotations.SerializedName

data class FigmaFileResponse(
    val name: String,
    val lastModified: String,
    val thumbnailUrl: String,
    val version: String,
    val document: FigmaDocument
)

data class FigmaDocument(
    val id: String,
    val name: String,
    val type: String,
    val children: List<FigmaNode>
)

data class FigmaNode(
    val id: String,
    val name: String,
    val type: String,
    val children: List<FigmaNode>?
)

data class FigmaCommentsResponse(
    val comments: List<FigmaComment>
)

data class FigmaComment(
    val id: String,
    val text: String,
    val user: FigmaUser,
    val createdAt: String,
    val resolved: Boolean
)

data class FigmaUser(
    val id: String,
    val handle: String,
    val imgUrl: String
)

data class FigmaUpdateResponse(
    val status: String,
    val error: String?
) 