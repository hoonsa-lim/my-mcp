package com.hoonsa.lim.mcp.figma

import io.modelcontextprotocol.mcp.sdk.client.ClientSession
import io.modelcontextprotocol.mcp.sdk.common.ToolCall
import io.modelcontextprotocol.mcp.sdk.common.ToolResult

interface FigmaClient {
    suspend fun initialize(session: ClientSession)
    suspend fun getFile(fileId: String): String
    suspend fun getComments(fileId: String): String
    suspend fun updateDesign(fileId: String, changes: Map<String, Any>): String
    suspend fun handleToolCall(toolCall: ToolCall): ToolResult
} 