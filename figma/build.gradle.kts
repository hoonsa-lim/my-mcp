plugins {
    id("buildlogic.kotlin-library-conventions")
}

val mcpVersion = "0.4.0"
val slf4jVersion = "2.0.9"
val retrofitVersion = "2.9.0"
val okhttpVersion = "4.12.0"
val mockkVersion = "1.13.9"
val kotlinTestVersion = "1.9.22"
val coroutinesVersion = "1.7.3"

dependencies {
    implementation(project(":utilities"))
    
    // MCP 관련 의존성
    implementation("io.modelcontextprotocol:kotlin-sdk:$mcpVersion")
    implementation("org.slf4j:slf4j-nop:$slf4jVersion")
    
    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    
    // Figma API 클라이언트
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    
    // 테스트 의존성
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinTestVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:$kotlinTestVersion") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-test-junit")
    }
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
} 