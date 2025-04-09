# MCP (Model Context Protocol) Server

이 프로젝트는 Model Context Protocol을 구현한 Kotlin 기반 서버입니다.

## 개발 환경

- Kotlin
- Gradle
- JDK 21

## 프로젝트 구조

```
my-mcp/
├── app/                    # 메인 애플리케이션 모듈
├── list/                   # 리스트 유틸리티 모듈
├── utilities/              # 유틸리티 모듈
└── buildSrc/               # Gradle 빌드 설정
```

## 시작하기

### 필수 조건

- JDK 21 이상
- Gradle 8.13 이상

### 빌드

```bash
./gradlew build
```

### 실행

```bash
./gradlew run
```

## 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 