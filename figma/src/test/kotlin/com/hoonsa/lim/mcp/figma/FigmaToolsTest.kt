package com.hoonsa.lim.mcp.figma

import io.mockk.coEvery
import io.mockk.mockk
import io.modelcontextprotocol.sdk.common.ToolCall
import io.modelcontextprotocol.sdk.common.ToolResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class FigmaToolsTest {
    private val mockFigmaClient = mockk<FigmaClient>()
    private val figmaTools = FigmaTools(mockFigmaClient)

    @Test
    fun `getFigmaFile should return file information as ToolResult`() = runTest {
        // Given
        val fileId = "test-file-id"
        val expectedResponse = """{"name": "Test File", "version": "1"}"""
        coEvery { mockFigmaClient.getFile(fileId) } returns expectedResponse

        // When
        val result = figmaTools.getFigmaFile(fileId)

        // Then
        assertEquals("getFigmaFile", result.name)
        assertEquals(expectedResponse, result.content)
    }

    @Test
    fun `getFigmaComments should return comments as ToolResult`() = runTest {
        // Given
        val fileId = "test-file-id"
        val expectedResponse = """{"comments": [{"text": "Test comment"}]}"""
        coEvery { mockFigmaClient.getComments(fileId) } returns expectedResponse

        // When
        val result = figmaTools.getFigmaComments(fileId)

        // Then
        assertEquals("getFigmaComments", result.name)
        assertEquals(expectedResponse, result.content)
    }

    @Test
    fun `updateFigmaDesign should update design and return ToolResult`() = runTest {
        // Given
        val fileId = "test-file-id"
        val changes = mapOf("property" to "value")
        val expectedResponse = """{"success": true}"""
        coEvery { mockFigmaClient.updateDesign(fileId, changes) } returns expectedResponse

        // When
        val result = figmaTools.updateFigmaDesign(fileId, changes)

        // Then
        assertEquals("updateFigmaDesign", result.name)
        assertEquals(expectedResponse, result.content)
    }

    @Test
    fun `handleToolCall should handle getFigmaFile tool call`() = runTest {
        // Given
        val fileId = "test-file-id"
        val expectedResponse = """{"name": "Test File", "version": "1"}"""
        val toolCall = ToolCall("test-id", "getFigmaFile", mapOf("fileId" to fileId))
        coEvery { mockFigmaClient.getFile(fileId) } returns expectedResponse

        // When
        val result = figmaTools.handleToolCall(toolCall)

        // Then
        assertEquals("getFigmaFile", result.name)
        assertEquals(expectedResponse, result.content)
    }

    @Test
    fun `handleToolCall should handle getFigmaComments tool call`() = runTest {
        // Given
        val fileId = "test-file-id"
        val expectedResponse = """{"comments": [{"text": "Test comment"}]}"""
        val toolCall = ToolCall("test-id", "getFigmaComments", mapOf("fileId" to fileId))
        coEvery { mockFigmaClient.getComments(fileId) } returns expectedResponse

        // When
        val result = figmaTools.handleToolCall(toolCall)

        // Then
        assertEquals("getFigmaComments", result.name)
        assertEquals(expectedResponse, result.content)
    }

    @Test
    fun `handleToolCall should handle updateFigmaDesign tool call`() = runTest {
        // Given
        val fileId = "test-file-id"
        val changes = mapOf("property" to "value")
        val expectedResponse = """{"success": true}"""
        val toolCall = ToolCall("test-id", "updateFigmaDesign", mapOf(
            "fileId" to fileId,
            "changes" to changes
        ))
        coEvery { mockFigmaClient.updateDesign(fileId, changes) } returns expectedResponse

        // When
        val result = figmaTools.handleToolCall(toolCall)

        // Then
        assertEquals("updateFigmaDesign", result.name)
        assertEquals(expectedResponse, result.content)
    }

    @Test
    fun `handleToolCall should throw exception for unknown tool`() = runTest {
        // Given
        val toolCall = ToolCall("test-id", "unknownTool", emptyMap())

        // When/Then
        val exception = assertThrows<IllegalArgumentException> {
            figmaTools.handleToolCall(toolCall)
        }
        assertEquals("Unknown tool: unknownTool", exception.message)
    }
} 