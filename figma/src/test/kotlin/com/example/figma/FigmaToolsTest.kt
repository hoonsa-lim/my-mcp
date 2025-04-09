package com.example.figma

import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class FigmaToolsTest {
    private val mockFigmaClient = mockk<FigmaClient>()
    private val figmaTools = FigmaTools(mockFigmaClient)

    @Test
    fun `getFigmaFile should return file information`() = runTest {
        // Given
        val fileId = "test-file-id"
        val expectedResponse = """{"name": "Test File", "version": "1"}"""
        coEvery { mockFigmaClient.getFile(fileId) } returns expectedResponse

        // When
        val result = figmaTools.getFigmaFile(fileId)

        // Then
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getFigmaComments should return comments`() = runTest {
        // Given
        val fileId = "test-file-id"
        val expectedResponse = """{"comments": [{"text": "Test comment"}]}"""
        coEvery { mockFigmaClient.getComments(fileId) } returns expectedResponse

        // When
        val result = figmaTools.getFigmaComments(fileId)

        // Then
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `updateFigmaDesign should update design and return response`() = runTest {
        // Given
        val fileId = "test-file-id"
        val changes = mapOf("property" to "value")
        val expectedResponse = """{"success": true}"""
        coEvery { mockFigmaClient.updateDesign(fileId, changes) } returns expectedResponse

        // When
        val result = figmaTools.updateFigmaDesign(fileId, changes)

        // Then
        assertEquals(expectedResponse, result)
    }
} 