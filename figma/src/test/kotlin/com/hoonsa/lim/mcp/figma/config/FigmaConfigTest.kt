package com.hoonsa.lim.mcp.figma.config

import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import kotlin.test.assertEquals

class FigmaConfigTest {
    private val testToken = "test_token"
    private val envVarName = "FIGMA_API_TOKEN"
    
    @BeforeEach
    fun setup() {
        mockkStatic(System::class)
    }
    
    @AfterEach
    fun cleanup() {
        // 테스트 후 환경 변수 정리
        System.clearProperty(envVarName)
    }
    
    @Test
    fun `환경 변수에서 토큰을 가져옵니다`() {
        // given
        every { System.getenv(envVarName) } returns testToken
        
        // when
        val token = FigmaConfig.getApiToken()
        
        // then
        assertEquals(testToken, token)
    }
    
    @Test
    fun `환경 변수가 없을 때 .env 파일에서 토큰을 가져옵니다`() {
        // given
        every { System.getenv(envVarName) } returns null
        
        val envFile = File(".env")
        envFile.writeText("$envVarName=$testToken")
        
        try {
            // when
            val token = FigmaConfig.getApiToken()
            
            // then
            assertEquals(testToken, token)
        } finally {
            // cleanup
            envFile.delete()
        }
    }
    
    @Test
    fun `환경 변수와 .env 파일이 모두 없을 때 예외가 발생합니다`() {
        // given
        every { System.getenv(envVarName) } returns null
        
        // when & then
        assertThrows<IllegalStateException> {
            FigmaConfig.getApiToken()
        }
    }
} 