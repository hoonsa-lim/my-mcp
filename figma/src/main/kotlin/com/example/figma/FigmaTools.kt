package com.example.figma

import io.modelcontextprotocol.sdk.tools.Tool
import io.modelcontextprotocol.sdk.tools.ToolUnion

class FigmaTools(
    private val figmaClient: FigmaClient
) {
    @Tool(
        name = "getFigmaFile",
        description = "Figma 파일의 정보를 가져옵니다.",
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
    suspend fun getFigmaFile(fileId: String): String {
        return figmaClient.getFile(fileId)
    }

    @Tool(
        name = "getFigmaComments",
        description = "Figma 파일의 댓글을 가져옵니다.",
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
    suspend fun getFigmaComments(fileId: String): String {
        return figmaClient.getComments(fileId)
    }

    @Tool(
        name = "updateFigmaDesign",
        description = "Figma 디자인을 업데이트합니다.",
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
    suspend fun updateFigmaDesign(fileId: String, changes: Map<String, Any>): String {
        return figmaClient.updateDesign(fileId, changes)
    }
} 