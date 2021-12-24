## 필요 데이터베이스 테이블
1. CSH_USER (사용자)
2. CSH_USER_SNS_LOGIN_INFO (사용자 소셜 로그인 정보)
3. CSH_USER_DETAIL (사용자 상세)
4. CSH_HASH_TAG (해시태그)
5. CSH_USER_CAREER_INFO (사용자 경력 정보)
6. CSH_USER_CAREER_DETAIL_INFO (사용자 경력 상세 정보)
7. CSH_COMPANY_ASSIGNED_WORK_DETAIL (회사 담당 업무 상세)
8. CSH_USER_ROLE (사용자 권한)
9. CSH_ROLE_ACCESS_CONTROL (권한 접근 통제)
10. CSH_ROLE_CRUD_SCOPE (권한 CRUD 범위)
11. CSH_COMMON_ACCOUNT_CONFIG (사용자 공통 계정 설정 정보)
12. CSH_TFA (2차 인증 - TFA: Two-Factor Authentication)
13. CSH_USER_ACCOUNT_INFO (사용자 계정 정보)
14. CSH_LOG_LAST_LOGIN (사용자 로그인 로그)


## 데이터베이스 테이블 상세 정보 (TODO 정리 나중)
- CSH_USER (사용자)
    - ID bigint primary key - 시퀀스
    - LOGIN_ID varchar(50) - 아이디
    - PASSWORD varchar(150) - 비밀번호
    - NICKNAME varchar(50) - 사용자 별명
    - NAME varchar(50) - 이름
    - EMAIL varchar(50) - 이메일
    - PHONE_NUMBER varchar(50) - 핸드폰 번호
    - PROFILE_IMAGE_PATH varchar(200) - 프로필 사진 경로
    - PROFILE_THUMBNAIL_IMAGE_PATH varchar(200) - 프로필 썸네일 사진 경로
    - GENDER char(1) 성별 (F:여성, M남성, U:확인불가)
    - BIRTHDAY varchar(50) - 출생연도 (ex: 910429)

- CSH_USER_DETAIL (사용자 상세)
    - ID bigint primary key - 시퀀스
    - USER_ID bigint foreign key - 사용자 시퀀스
    - INTRODUCE varchar(2000) - 간략한 자기 소개 내용
    - AREA_ONE varchar(100) - 특별시 / 광역시 / 도
    - AREA_TWO varchar(100) - 구 / 군
    - AREA_THREE varchar(100) - 동 / 읍, 면, 리
    - SEARCH_AREA_PERMIT_SCOPE varchar(50) - 내 지역 오프라인 스터디만 구하기 (Status 관리 -AREA1 없으면 NULL)
    - USER_ACTIVE_STATUS varchar(50) -사용자 활동 상태 (활동, 휴면, 회원탈퇴) - (비활동 퇴장, 강퇴)
    - LOGIN_TYPE varchar(50) - 사용자 로그인 타입 (카카오, 네이버, 인스타그램, 페이스북, ...)
    - LAST_LOGIN_DATE datetime - 마지막 접속일
    - PRIVACY_INFO_DISPLAY varchar(1) (Y, N) - 개인 정보 공개 여부 (상세 하단 스키마 부터 공개 할 것인지 정하는 상태 값)

- CSH_HASH_TAG (해시태그)
    - ID bigint primary key - 시퀀스
    - USER_ID bigint foreign key - 사용자 시퀀스
    - TYPE varchar(50) - 범위 (관심, 필요, 필수, ...) (값에 테이블.컬럼이 들어가도 될 듯)
    - CONTENT varchar(200) - 해시태그 내용

- CSH_USER_CAREER_INFO - 사용자 경력 정보 - (TODO: 사용자 경력 정보에 대한 레벨을 정하고, 스터디 모집 시 원하는 레벨 모집)
    - ID bigint primary key - 시퀀스
    - USER_DETAIL_ID bigint foreign key - 사용자 상세 시퀀스
    - EXISTS_CAREER_DISPLAY char(1) - 경력 상태 존재 여부
    - ALL_COMPANY_YEARS int - 년차
    - WORK_STATUS varchar(50) - 재직상태 (취준, 재직, 이직 준비)
    - CARRER_DETAIL_DISPLAY - 경력 상세 정보 공개 여부
    - COMPANY_NAME_DISPLAY char(1) - (수정)CURRENT_COMPANY_DISPALY - 회사 이름 공개 여부 (인증이 된 상태에서만 사용자의 회사 이름만 노출 - 인증 → 이메일 방식 (이메일 방식 인증까지 할 필요가 있을까?) (재직 기간에 따라 전, 현 표시 필요할듯) (우선 순위 → 현재 회사 < 대표 회사)

- **CSH_USER_CAREER_DETAIL_INFO - 사용자 경력 상세 정보**
    - ID bigint primary key - 시퀀스
    - USER_CARRER_INFO_ID foreign key - 사용자 경력 정보 시퀀스
    - CURRENT_COMPANY_YN char(1) - 현재 회사 여부
    - TODO: 비개발 여부 체크
    - REPRESENTATIVE_COMPANY char(1) - 대표 회사 여부 (대표 회사 O: Y, 대표 회사 X: N) - 현재 회사 보다 상위 노출 (현재 회사 Y, 대표 회사 Y인 경우 대표 회사 노출)',
    - COMPANY_NAME varchar(50) - 회사명
    - POSITION varchar(50) - 직급
    - DEPARTMENT varchar(50) - 담당 부서
    - WORK_START_DATE datetime - 재직 기간 (시작일)
    - WORK_END_DATE datetime - 재직 기간 (종료일)

- CSH_COMPANY_ASSIGNED_WORK_DETAIL - 회사 담당 업무 상세
    - ID primary key - 시퀀스
    - USER_CAREER_DETAIL_INFO_ID foreign key - 사용자 경력 상세 정보 시퀀스
    - FEATURE_DEVELOP_TITLE varchar(300) - 개발한 기능 제목
    - EXPLANATION varchar(2000) - 상세 설명
    - DEVELOPMENT_START_DATE datetime - 개발 기간 (시작일)
    - DEVELOPMENT_END_DATE datetime - 개발 기간 (종료일)
    - 해시태그 필요

- CSH_ACCOUNT_INFO (계정 정보)
    - ID bigint primary key - 시퀀스
    - USER_ID - bigint foreign key - 사용자 시퀀스
    - ACCOUNT_LOCK_STATUS varchar(50) - 계정 Lock 상태 (LOCK, UNLOCK, ...)
    - ACCOUNT_USE_STATUS- 계정 사용 여부 상태 (USE, NOT_USE)
    - 계정 만료 체크 (임시)
    - PASSWORD_CHANGE_TERM int - 비밀번호 변경 기한 (디폴트 90)
    - FAIL_PASSWORD_COUNT int - 비밀번호 오류 횟수

- CSH_USER_ROLE (사용자 권한)
    - ID bigint primary key - 시퀀스
    - USER_ID bigint foreign key - 사용자 고유 번호
    - AUTHORITY varchar(50) - 권한 이름
- CSH_ROLE_ACCESS_CONTROL (권한 접근 통제)
    - ID bigint primary key - 시퀀스
    - USER_ROLE_ID bigint foreign key - 사용자 권한 시퀀스
    - URL varchar(200) - 접속 가능 메뉴
- CSH_ROLE_CRUD_SCOPE (권한 CRUD 범위)
    - ID bigint primary key - 시퀀스
    - USER_ROLE_ID bigint foreign key - 사용자 권한 시퀀스
    - MODE varchar(50) - 모드 (ex: create, read, update, delete)


## 인증, 인가
- User (사용자)
  - (1:N) User_Role (사용자 권한)
    - (N:1) Role (권한)
      - (1:N) RoleResource (권한 자원)
        - (N:1) Resources (자원)
- RoleHierarchy (권한 계층)

- USER_ROLE
  - ID
  - USER_ID
  - ROLE_ID

- ROLE
  - ID
  - ROLE_DESCRIPTION
  - ROLE_NAME

- ROLE_RESOURCE
  - ID
  - HTTP_METHOD
  - ORDER_NUM
  - RESOURCE_NAME (url name, method name, ...)
  - RESOURCE_TYPE (url, method, ...)

- RESOURCES
  - ID
  - ROLE_RESOURCE_ID
  - ROLE_ID