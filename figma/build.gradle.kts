plugins {
    id("buildlogic.kotlin-library-conventions")
}

val mcpVersion = "0.4.0"
val slf4jVersion = "2.0.9"

dependencies {
    implementation(project(":utilities"))
    
    // MCP 관련 의존성
    implementation("io.modelcontextprotocol:kotlin-sdk:$mcpVersion")
    implementation("org.slf4j:slf4j-nop:$slf4jVersion")
    
    // Figma API 클라이언트
    implementation("com.figma:figma-api-client:1.0.0") // 실제 Figma API 클라이언트 의존성으로 교체 필요
} 