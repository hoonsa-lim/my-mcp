package com.hoonsa.lim.mcp.figma

import io.modelcontextprotocol.sdk.client.ClientSession
import io.modelcontextprotocol.sdk.common.ToolCall
import io.modelcontextprotocol.sdk.common.ToolResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FigmaClientTest {
    private lateinit var figmaClient: FigmaClient
    private lateinit var mockSession: ClientSession
    
    @BeforeEach
    fun setup() {
        mockSession = mockk()
        figmaClient = FigmaClientImpl("test-token")
    }
    
    @Test
    fun `initialize should set MCP session`() = runBlocking {
        // given
        val session = mockk<ClientSession>()
        
        // when
        figmaClient.initialize(session)
        
        // then
        // No exception should be thrown
    }
    
    @Test
    fun `getFile should return Figma file information`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val expectedResponse = """{"name":"Test File","lastModified":"2024-04-09"}"""
        
        // when
        val result = figmaClient.getFile(fileId)
        
        // then
        assertEquals(expectedResponse, result)
    }
    
    @Test
    fun `getComments should return Figma file comments`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val expectedResponse = """{"comments":[{"id":"1","text":"Test comment"}]}"""
        
        // when
        val result = figmaClient.getComments(fileId)
        
        // then
        assertEquals(expectedResponse, result)
    }
    
    @Test
    fun `updateDesign should return update result`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val changes = mapOf("key" to "value")
        val expectedResponse = """{"status":"success"}"""
        
        // when
        val result = figmaClient.updateDesign(fileId, changes)
        
        // then
        assertEquals(expectedResponse, result)
    }
    
    @Test
    fun `handleToolCall should handle getFigmaFile tool call`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val expectedResponse = """{"name":"Test File","lastModified":"2024-04-09"}"""
        val toolCall = ToolCall("test-id", "getFigmaFile", mapOf("fileId" to fileId))
        
        // when
        val result = figmaClient.handleToolCall(toolCall)
        
        // then
        assertEquals("getFigmaFile", result.name)
        assertEquals(expectedResponse, result.content)
    }
    
    @Test
    fun `handleToolCall should handle getFigmaComments tool call`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val expectedResponse = """{"comments":[{"id":"1","text":"Test comment"}]}"""
        val toolCall = ToolCall("test-id", "getFigmaComments", mapOf("fileId" to fileId))
        
        // when
        val result = figmaClient.handleToolCall(toolCall)
        
        // then
        assertEquals("getFigmaComments", result.name)
        assertEquals(expectedResponse, result.content)
    }
    
    @Test
    fun `handleToolCall should handle updateFigmaDesign tool call`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val changes = mapOf("key" to "value")
        val expectedResponse = """{"status":"success"}"""
        val toolCall = ToolCall("test-id", "updateFigmaDesign", mapOf(
            "fileId" to fileId,
            "changes" to changes
        ))
        
        // when
        val result = figmaClient.handleToolCall(toolCall)
        
        // then
        assertEquals("updateFigmaDesign", result.name)
        assertEquals(expectedResponse, result.content)
    }
    
    @Test
    fun `handleToolCall should throw exception for unknown tool`() = runBlocking {
        // given
        val toolCall = ToolCall("test-id", "unknownTool", emptyMap())
        
        // when/then
        val exception = assertThrows<IllegalArgumentException> {
            figmaClient.handleToolCall(toolCall)
        }
        assertEquals("Unknown tool: unknownTool", exception.message)
    }
} 