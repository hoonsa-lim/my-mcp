package com.hoonsa.lim.mcp.figma

import io.modelcontextprotocol.mcp.sdk.common.Tool
import io.modelcontextprotocol.mcp.sdk.common.ToolCall
import io.modelcontextprotocol.mcp.sdk.common.ToolResult

class FigmaTools(
    private val figmaClient: FigmaClient
) {
    @Tool(
        name = "getFigmaFile",
        description = "Figma 파일 정보를 가져옵니다",
        inputSchema = """
            {
                "type": "object",
                "properties": {
                    "fileId": {
                        "type": "string",
                        "description": "Figma 파일 ID"
                    }
                },
                "required": ["fileId"]
            }
        """
    )
    suspend fun getFigmaFile(fileId: String): ToolResult {
        val result = figmaClient.getFile(fileId)
        return ToolResult("getFigmaFile", result)
    }

    @Tool(
        name = "getFigmaComments",
        description = "Figma 파일의 댓글을 가져옵니다",
        inputSchema = """
            {
                "type": "object",
                "properties": {
                    "fileId": {
                        "type": "string",
                        "description": "Figma 파일 ID"
                    }
                },
                "required": ["fileId"]
            }
        """
    )
    suspend fun getFigmaComments(fileId: String): ToolResult {
        val result = figmaClient.getComments(fileId)
        return ToolResult("getFigmaComments", result)
    }

    @Tool(
        name = "updateFigmaDesign",
        description = "Figma 디자인을 업데이트합니다",
        inputSchema = """
            {
                "type": "object",
                "properties": {
                    "fileId": {
                        "type": "string",
                        "description": "Figma 파일 ID"
                    },
                    "changes": {
                        "type": "object",
                        "description": "변경사항"
                    }
                },
                "required": ["fileId", "changes"]
            }
        """
    )
    suspend fun updateFigmaDesign(fileId: String, changes: Map<String, Any>): ToolResult {
        val result = figmaClient.updateDesign(fileId, changes)
        return ToolResult("updateFigmaDesign", result)
    }

    suspend fun handleToolCall(toolCall: ToolCall): ToolResult {
        return when (toolCall.name) {
            "getFigmaFile" -> {
                val fileId = toolCall.arguments["fileId"] as String
                getFigmaFile(fileId)
            }
            "getFigmaComments" -> {
                val fileId = toolCall.arguments["fileId"] as String
                getFigmaComments(fileId)
            }
            "updateFigmaDesign" -> {
                val fileId = toolCall.arguments["fileId"] as String
                val changes = toolCall.arguments["changes"] as Map<String, Any>
                updateFigmaDesign(fileId, changes)
            }
            else -> throw IllegalArgumentException("Unknown tool: ${toolCall.name}")
        }
    }
} 