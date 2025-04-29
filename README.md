# Dyno Climb Server

클라이밍에 게임 요소를 추가하여 더 즐겁게 클라이밍을 즐길 수 있도록 돕는 미션 기반 서비스입니다.

---

## ⭐ 서비스 개요

- 클라이밍 운동에 **게임 요소**를 추가하여 **재미**와 **몰입**을 증가시킵니다.
- **암장 정보**, **미션 정보**, **사용자 경험치 및 레벨**을 관리합니다.
- 미션 수행을 통해 사용자의 **지속적인 참여**를 유도합니다.

---

## 📊 기능 요구사항

| 주요 기능 | 세부 기능 |
|:---|:---|
| **사용자 관리** | 가입, 로그인, 정보 수정, 탈퇴 |
| **암장 관리** | 암장 등록, 수정, 삭제, 검색, 조회 |
| **미션 관리** | 데일리/위클리/먼슬리 미션, 타임 미션, 랭킹 미션 |
| **경험치 및 레벨 관리** | 미션 달성에 따른 경험치 지급, 레벨 상승 관리 |
| **등반 기록 관리** | 등반 기록 생성, 조회, 수정, 삭제 및 영상 관리 |
| **커뮤니티** | 자유게시판, 크루 모집, 중고거래, 장터 기능, 영상 공유 |

---

## 🏠 Bounded Contexts

| Context | 설명 |
|:---|:---|
| **Gym** | 암장, 벽면, 난이도 정보 관리 |
| **Mission** | 미션 그룹 및 개별 미션 설계와 사용자 참여 관리 |
| **Record** | 개인 등반 기록 및 해시태그, 영상 관리 |
| **User** | 사용자 프로필, 경험치, 레벨 및 소셜 기능 관리 |

---

## 🚀 API 명세

### User (/users)
- 로그인 (POST /login) ✅
- 가입 (POST /register) ✅
- 탈퇴 (POST /users/me/revoke) ✅
- 조회 (GET /users/me) ✅
- 수정 (PUT /users/me) ✅
- 로그아웃 (POST /users/me/logout) ✅

### Gym (/gyms)
- 상세 조회 (GET /{id}) ✅
- 거리순 조회 (GET ?keyword=?&lat=37.7749&lng=-122.4192) ✅

### BoulderProblem (/boulder-problems)
- 상세 조회 (GET /{id})
- 목록 조회 (GET ?gymId=?&keyword=?)

### MissionGroup (/mission-groups)
- 미션 그룹 상세 조회 (GET /{id})
- 거리순/최신순 미션 그룹 조회 (GET ?gymId=&keyword=?)
- 미션 참여 요청 (PATCH /{id}/missions/{missionId}/participation)
- 미션 단건 조회 (GET /{id}/missions/{missionId})

### Record (/records)
- 미션 기록 등록 (POST /records/{id}/mission)
- 미션 내 기록 조회 (GET ?missionId=&keyword=?)
- 공개된 사용자 기록 조회 (GET /public?missionId=?)
- 기록 상세 조회 (GET /{id})
- 기록 수정 (PUT /{id})
- 기록 삭제 (DELETE /{id})
- S3 미디어 업로드를 위한 Presigned URL 발급 (PUT /records/presigned-url)

### Hashtag (/hashtags)
- 해시태그 검색 (GET ?keyword=)
- 해시태그 등록 (POST /)

---

## 💡 다음 작업 예정
- Swagger 기반 API 문서 자동화
- ERD(Entity-Relationship Diagram) 설계
- Spring Boot 프로젝트 초기 세팅

---

## 🛠️ 개발 환경

- Language: Kotlin / Java
- Framework: Spring Boot
- Database: MySQL
- Cloud Storage: AWS S3
- Authentication: JWT (예정)

---

계속해서 다양한 미션이 추가될 예정입니다! 🚀💪
