# 호식탐탐 (HosikTamTam) 백엔드

## 프로젝트 소개
호식탐탐은 호서대학교 학생들을 위한 교내/외 식당 정보 제공 서비스입니다.

## 주요 기능
- OAuth2 기반 사용자 인증 (Google, Kakao)
- 교내/교외 식당 목록 조회
- 식당 상세 정보 조회
- 카테고리별 식당 조회
- 메뉴 검색
- 식당 찜하기/찜 취소
- 리뷰 작성 및 조회
- 학식당 혼잡도 조회
- 개인화된 메뉴 추천
- 식당 이름 자동완성 (초성/prefix 검색 지원)

## 기술 스택
- Java 17
- Spring Boot 3.1.0
- Spring Data JPA
- Spring Security
- JWT 인증
- MySQL

## 프로젝트 구조
```
HOSIKTAMTAM_BE
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com
│  │  │     └─ goormthonuniv
│  │  │        └─ hoseo
│  │  │           └─ hosiktamtam
│  │  │              ├─ controller
│  │  │              ├─ domain
│  │  │              ├─ dto
│  │  │              ├─ exception
│  │  │              ├─ repository
│  │  │              ├─ security
│  │  │              ├─ service
│  │  │              ├─ util
│  │  │              └─ HosiktamtamApplication.java
│  │  └─ resources
│  │     └─ application.yml
│  └─ test
│     └─ java
│        └─ com
│           └─ goormthonuniv
│              └─ hoseo
│                 └─ hosiktamtam
│                    └─ HosiktamtamApplicationTests.java
```

## 실행 방법
1. MySQL 데이터베이스 생성:
```sql
CREATE DATABASE hosiktamtam;
```

2. 프로젝트 클론:
```bash
git clone https://github.com/yourusername/hosiktamtam_be.git
cd hosiktamtam_be
```

3. 애플리케이션 설정:
`src/main/resources/application.yml` 파일에서 데이터베이스 정보 및 OAuth 클라이언트 정보 설정

4. 빌드 및 실행:
```bash
./gradlew bootRun
```

## API 문서
- API 엔드포인트는 `/api`로 시작합니다.
- Swagger UI를 통한 API 문서는 서버 실행 후 `http://localhost:8080/api/swagger-ui.html`에서 확인 가능합니다.

## 주요 API 엔드포인트
- 로그인: `/api/auth/google`, `/api/auth/kakao`
- 사용자 프로필: `/api/user/profile`
- 식당 목록: `/api/restaurants?area=campus|outside&sort=rating|favorite`
- 식당 상세: `/api/restaurants/{restaurantId}`
- 카테고리별 식당: `/api/restaurants/category?category=밥|면|탕|카페|주점`
- 메뉴 검색: `/api/menus/search?query={keyword}`
- 학식당 혼잡도: `/api/cafeteria/congestion`
- 찜하기: `/api/favorites` (POST)
- 찜 취소: `/api/favorites/{restaurantId}` (DELETE)
- 리뷰 작성: `/api/reviews` (POST)
- 리뷰 조회: `/api/reviews?restaurant_id={restaurantId}`
- 추천: `/api/recommendations`
- 자동완성: `/api/restaurants/autocomplete?keyword={keyword}`

## 초성 검색 기능
호식탐탐은 한글 초성 검색을 지원합니다. 예를 들어, "ㅎㅅㄷ"로 검색하면 "호식당"이 검색 결과로 나타납니다.