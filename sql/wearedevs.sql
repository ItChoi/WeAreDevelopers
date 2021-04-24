-- mysql sql 정보
CREATE TABLE CSH_USER (
    ID bigint primary key comment '사용자 고유 번호',
    LOGIN_ID varchar(50) comment '로그인 아이디',
    PASSWORD varchar(50) comment '비밀번호',
    NAME varchar(50) comment '이름',
    EMAIL varchar(50) comment '이메일',
    PROFILE_IMAGE_NAME varchar(200) comment '프로필 사진',
    PROFILE_THUMBNAIL_IMAGE_NAME varchar(200) comment '썸네일 프로필 사진',
    LOGIN_TYPE varchar(50) comment '사용자 로그인 타입 (kakao, google, naver, ...)',
    USER_ACTIVE_STATUS varchar(50) comment '사용자 활동 상태',
    LAST_LOGIN_DATE datetime comment '마지막 로그인 날짜',
    CREATED_DATE datetime comment '생성일',
    CREATED_USER_ID bigint comment '생성자',
    UPDATED_DATE datetime comment '수정일',
    UPDATED_USER_ID bigint comment '수정자'
);
ALTER TABLE CSH_USER COMMENT '사용자';


CREATE TABLE CSH_USER_INFO (
    ID bigint primary key comment '사용자 정보 고유 번호',
    USER_ID bigint not null comment '(사용자 고유 번호)',
    INTRODUCE varchar(2000) comment '소개 내용',
    PHONE_NUMBER varchar(50) comment '핸드폰 번호',
    CREATED_DATE datetime comment '생성일',
    CREATED_USER_ID bigint comment '생성자',
    UPDATED_DATE datetime comment '수정일',
    UPDATED_USER_ID bigint comment '수정자',
    FOREIGN KEY (USER_ID) REFERENCES CSH_USER (ID)
);
ALTER TABLE CSH_USER COMMENT '사용자 정보';