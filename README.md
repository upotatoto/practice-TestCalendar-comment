```
spring-comment-calendar
 ├── src
 │   └── main
 │       ├── java
 │       │   └── com.sparta.springcommentcalendar
 │       │       ├── config
 │       │       │   └── JwtConfig.java              // JWT 설정
 │       │       ├── controller
 │       │       │   └── AuthController.java         // 인증 관련 컨트롤러 (회원가입, 로그인)
 │       │       ├── dto
 │       │       │   └── UserDTO.java                // 사용자 데이터 전송 객체
 │       │       ├── entity
 │       │       │   └── User.java                   // 사용자 엔티티
 │       │       ├── repository
 │       │       │   └── UserRepository.java         // 사용자 저장소
 │       │       ├── security
 │       │       │   ├── JwtAuthenticationFilter.java // JWT 인증 필터
 │       │       │   ├── JwtTokenProvider.java        // JWT 토큰 생성 및 검증
 │       │       │   └── SecurityConfig.java          // 보안 설정
 │       │       └── service
 │       │           └── UserService.java            // 사용자 서비스
 │       └── resources
 │           ├── application.properties              // 애플리케이션 설정 파일
 │           └── schema.sql                          // 데이터베이스 스키마 파일
 ├── .gitignore                                      // Git에서 무시할 파일 목록
 ├── README.md                                       // 프로젝트 설명 파일
 └── pom.xml                                         // Maven 종속성 관리 파일
```

# Spring Comment Calendar

## 프로젝트 개요
이 프로젝트는 Spring Boot를 사용하여 만든 일정 관리 애플리케이션입니다. 사용자는 회원가입 및 로그인을 통해 인증을 받고, 일정 및 댓글을 관리할 수 있습니다. JWT를 통해 보안을 강화하고, RESTful API를 제공합니다.

## 기능
- 회원가입 및 로그인 (JWT 인증)
- 일정 관리 (CRUD)
- 댓글 관리 (CRUD)
- Spring Security를 사용한 보안
- MySQL을 사용한 데이터 저장

## 기술 스택
- Java 17
- Spring Boot 3.3.4
- Spring Security
- JWT (JSON Web Token)
- MySQL
- Maven
- Postman (API 테스트)

## 프로젝트 파일 구조

```
spring-comment-calendar
 ├── src
 │   └── main
 │       ├── java
 │       │   └── com.sparta.springcommentcalendar
 │       │       ├── config
 │       │       │   └── JwtConfig.java
 │       │       ├── controller
 │       │       │   └── AuthController.java
 │       │       ├── dto
 │       │       │   └── UserDTO.java
 │       │       ├── entity
 │       │       │   └── User.java
 │       │       ├── repository
 │       │       │   └── UserRepository.java
 │       │       ├── security
 │       │       │   ├── JwtAuthenticationFilter.java
 │       │       │   ├── JwtTokenProvider.java
 │       │       │   └── SecurityConfig.java
 │       │       └── service
 │       │           └── UserService.java
 │       └── resources
 │           ├── application.properties
 │           └── schema.sql
 ├── .gitignore
 ├── README.md
 └── pom.xml
```

## 실행 방법

### 1. MySQL 설정
먼저 MySQL에 데이터베이스를 생성합니다.

```sql
CREATE DATABASE schedule_db;
```

### 2. application.properties 설정
`src/main/resources/application.properties` 파일에서 MySQL 연결 정보를 설정합니다.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/schedule_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=your_jwt_secret_key  # JWT 비밀키
```

### 3. 프로젝트 실행
```bash
mvn spring-boot:run
```

서버가 성공적으로 실행되면, `http://localhost:8080`에서 API를 사용할 수 있습니다.

## API 사용 방법 (Postman)

### 회원가입 (Signup)
- **URL**: `POST /auth/signup`
- **Request Body** (JSON):
  ```json
  {
    "username": "john_doe",
    "password": "password123"
  }
  ```

### 로그인 (Login)
- **URL**: `POST /auth/login`
- **Request Body** (JSON):
  ```json
  {
    "username": "john_doe",
    "password": "password123"
  }
  ```
- **Response**:
  - 성공 시: JWT 토큰 반환

### JWT 인증 헤더 사용
로그인 후 반환된 JWT 토큰을 API 요청에 사용합니다.

- **Authorization 헤더**:
  ```
  Authorization: Bearer <JWT 토큰>
  ```
