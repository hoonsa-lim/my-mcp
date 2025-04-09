package com.example.figma

import com.example.figma.api.FigmaApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FigmaClientTest {
    private lateinit var figmaApi: FigmaApi
    private lateinit var figmaClient: FigmaClient
    
    @BeforeEach
    fun setup() {
        figmaApi = mockk()
        figmaClient = FigmaClientImpl()
    }
    
    @Test
    fun `getFile은 Figma 파일 정보를 반환합니다`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val expectedResponse = """{"name":"Test File","lastModified":"2024-04-09"}"""
        
        // when
        val result = figmaClient.getFile(fileId)
        
        // then
        assertEquals(expectedResponse, result)
    }
    
    @Test
    fun `getComments는 Figma 파일의 댓글을 반환합니다`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val expectedResponse = """{"comments":[{"id":"1","text":"Test comment"}]}"""
        
        // when
        val result = figmaClient.getComments(fileId)
        
        // then
        assertEquals(expectedResponse, result)
    }
    
    @Test
    fun `updateDesign은 Figma 디자인 업데이트 결과를 반환합니다`() = runBlocking {
        // given
        val fileId = "test_file_id"
        val changes = mapOf("key" to "value")
        val expectedResponse = """{"status":"success"}"""
        
        // when
        val result = figmaClient.updateDesign(fileId, changes)
        
        // then
        assertEquals(expectedResponse, result)
    }
} 