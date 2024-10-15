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
- Postman (API 테스트)

## 프로젝트 파일 구조

```
src
 └── main
     ├── java
     │    └── com.example.schedule
     │          ├── config
     │          │    └── JwtConfig.java            // JWT 관련 설정 (PasswordEncoder 설정 포함)
     │          ├── controller
     │          │    ├── AuthController.java       // 로그인, 회원가입 컨트롤러
     │          │    ├── ScheduleController.java   // 일정 CRUD 컨트롤러
     │          │    └── CommentController.java    // 댓글 CRUD 컨트롤러
     │          ├── dto
     │          │    ├── UserDTO.java              // 유저 관련 DTO
     │          │    ├── ScheduleDTO.java          // 일정 관련 DTO
     │          │    └── CommentDTO.java           // 댓글 관련 DTO
     │          ├── entity
     │          │    ├── User.java                 // 유저 엔티티
     │          │    ├── Schedule.java             // 일정 엔티티
     │          │    └── Comment.java              // 댓글 엔티티
     │          ├── repository
     │          │    ├── UserRepository.java       // 유저 JPA 리포지토리
     │          │    ├── ScheduleRepository.java   // 일정 JPA 리포지토리
     │          │    └── CommentRepository.java    // 댓글 JPA 리포지토리
     │          ├── service
     │          │    ├── UserService.java          // 유저 관련 서비스 (회원가입, 로그인, 인증)
     │          │    ├── ScheduleService.java      // 일정 CRUD 서비스
     │          │    └── CommentService.java       // 댓글 CRUD 서비스
     │          ├── security
     │          │    ├── JwtTokenProvider.java     // JWT 생성 및 검증
     │          │    ├── JwtAuthenticationFilter.java  // JWT 인증 필터
     │          │    ├── JwtAuthenticationEntryPoint.java  // 인증 실패 시 핸들러
     │          │    ├── JwtAccessDeniedHandler.java  // 접근 거부 시 핸들러
     │          │    └── SecurityConfig.java        // Spring Security 설정 (JWT 필터 포함)
     └── resources
          ├── application.properties               // 애플리케이션 설정 (데이터베이스 연결, JWT 설정 등)
          └── schema.sql                           // 데이터베이스 초기화 SQL 스크립트

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

