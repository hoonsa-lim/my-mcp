package com.hoonsa.lim.mcp.figma.config

import java.io.File
import java.util.Properties

class FigmaConfig {
    companion object {
        private const val ENV_FILE = ".env"
        private const val ENV_VAR_NAME = "FIGMA_API_TOKEN"
        
        fun getApiToken(): String {
            // 1. 환경 변수에서 확인
            System.getenv(ENV_VAR_NAME)?.let { return it }
            
            // 2. .env 파일에서 확인
            val envFile = File(ENV_FILE)
            if (envFile.exists()) {
                val properties = Properties()
                properties.load(envFile.inputStream())
                properties.getProperty(ENV_VAR_NAME)?.let { return it }
            }
            
            throw IllegalStateException("Figma API 토큰이 설정되지 않았습니다. 환경 변수 $ENV_VAR_NAME 또는 $ENV_FILE 파일에서 설정해주세요.")
        }
    }
} 