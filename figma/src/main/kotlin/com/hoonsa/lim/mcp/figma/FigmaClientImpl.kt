package com.hoonsa.lim.mcp.figma

import io.modelcontextprotocol.mcp.sdk.client.ClientSession
import io.modelcontextprotocol.mcp.sdk.common.ToolCall
import io.modelcontextprotocol.mcp.sdk.common.ToolResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.URI

class FigmaClientImpl(
    private val apiToken: String,
    private val baseUrl: String = "https://api.figma.com/v1"
) : FigmaClient {
    private val httpClient = HttpClient.newBuilder().build()
    private var mcpSession: ClientSession? = null

    override suspend fun initialize(session: ClientSession) {
        mcpSession = session
    }

    override suspend fun getFile(fileId: String): String = withContext(Dispatchers.IO) {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$baseUrl/files/$fileId"))
            .header("X-Figma-Token", apiToken)
            .GET()
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            throw FigmaApiException("Failed to get file: ${response.body()}")
        }
        response.body()
    }

    override suspend fun getComments(fileId: String): String = withContext(Dispatchers.IO) {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$baseUrl/files/$fileId/comments"))
            .header("X-Figma-Token", apiToken)
            .GET()
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            throw FigmaApiException("Failed to get comments: ${response.body()}")
        }
        response.body()
    }

    override suspend fun updateDesign(fileId: String, changes: Map<String, Any>): String = withContext(Dispatchers.IO) {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$baseUrl/files/$fileId"))
            .header("X-Figma-Token", apiToken)
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(changes.toString()))
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            throw FigmaApiException("Failed to update design: ${response.body()}")
        }
        response.body()
    }

    override suspend fun handleToolCall(toolCall: ToolCall): ToolResult {
        return when (toolCall.name) {
            "getFigmaFile" -> {
                val fileId = toolCall.arguments["fileId"] as String
                val result = getFile(fileId)
                ToolResult(toolCall.id, result)
            }
            "getFigmaComments" -> {
                val fileId = toolCall.arguments["fileId"] as String
                val result = getComments(fileId)
                ToolResult(toolCall.id, result)
            }
            "updateFigmaDesign" -> {
                val fileId = toolCall.arguments["fileId"] as String
                val changes = toolCall.arguments["changes"] as Map<String, Any>
                val result = updateDesign(fileId, changes)
                ToolResult(toolCall.id, result)
            }
            else -> throw IllegalArgumentException("Unknown tool: ${toolCall.name}")
        }
    }
}

class FigmaApiException(message: String) : Exception(message) 