plugins {
    id("buildlogic.kotlin-library-conventions")
}

val mcpVersion = "0.4.0"
val slf4jVersion = "2.0.9"
val retrofitVersion = "2.9.0"
val okhttpVersion = "4.12.0"

dependencies {
    implementation(project(":utilities"))
    
    // MCP 관련 의존성
    implementation("io.modelcontextprotocol:kotlin-sdk:$mcpVersion")
    implementation("org.slf4j:slf4j-nop:$slf4jVersion")
    
    // Figma API 클라이언트
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
} 