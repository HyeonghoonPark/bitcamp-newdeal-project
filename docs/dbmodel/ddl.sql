-- 회원
DROP TABLE IF EXISTS ND_MEMBER RESTRICT;

-- 명함
DROP TABLE IF EXISTS ND_BIZCARD RESTRICT;

-- 그룹
DROP TABLE IF EXISTS ND_GROUP RESTRICT;

-- 그룹명함
DROP TABLE IF EXISTS ND_GROUP_CARD RESTRICT;

-- 회원
CREATE TABLE ND_MEMBER (
    MNO   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
    EMAIL VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
    NAME  VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
    PWD   VARCHAR(100) NOT NULL COMMENT '암호' -- 암호
)
COMMENT '회원';

-- 회원
ALTER TABLE ND_MEMBER
    ADD CONSTRAINT PK_ND_MEMBER -- 회원 기본키
        PRIMARY KEY (
            MNO -- 회원번호
        );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_ND_MEMBER
    ON ND_MEMBER ( -- 회원
        EMAIL ASC -- 이메일
    );

ALTER TABLE ND_MEMBER
    MODIFY COLUMN MNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 명함
CREATE TABLE ND_BIZCARD (
    BCNO  INTEGER     NOT NULL COMMENT '명함번호', -- 명함번호
    MNO   INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
    NAME  VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
    CNAME VARCHAR(50) NULL     COMMENT '회사명', -- 회사명
    CPOSI VARCHAR(50) NULL     COMMENT '직급', -- 직급
    MTEL  VARCHAR(30) NOT NULL COMMENT '휴대전화', -- 휴대전화
    TEL   VARCHAR(30) NULL     COMMENT '일반전화', -- 일반전화
    FAX   VARCHAR(30) NULL     COMMENT '팩스', -- 팩스
    EMAIL VARCHAR(40) NULL     COMMENT '이메일', -- 이메일
    MEMO  TEXT        NULL     COMMENT '메모', -- 메모
    PCARD TEXT        NULL     COMMENT '명함사진' -- 명함사진
)
COMMENT '명함';

-- 명함
ALTER TABLE ND_BIZCARD
    ADD CONSTRAINT PK_ND_BIZCARD -- 명함 기본키
        PRIMARY KEY (
            BCNO -- 명함번호
        );

-- 명함 인덱스
CREATE INDEX IX_ND_BIZCARD
    ON ND_BIZCARD( -- 명함
        NAME ASC, -- 이름
        MTEL ASC  -- 휴대전화
    );

ALTER TABLE ND_BIZCARD
    MODIFY COLUMN BCNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '명함번호';

-- 그룹
CREATE TABLE ND_GROUP (
    GNO   INTEGER     NOT NULL COMMENT '그룹번호', -- 그룹번호
    MNO   INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
    GNAME VARCHAR(50) NOT NULL COMMENT '그룹명' -- 그룹명
)
COMMENT '그룹';

-- 그룹
ALTER TABLE ND_GROUP
    ADD CONSTRAINT PK_ND_GROUP -- 그룹 기본키
        PRIMARY KEY (
            GNO -- 그룹번호
        );

-- 그룹 인덱스
CREATE INDEX IX_ND_GROUP
    ON ND_GROUP( -- 그룹
        GNAME ASC -- 그룹명
    );

ALTER TABLE ND_GROUP
    MODIFY COLUMN GNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '그룹번호';

-- 그룹명함
CREATE TABLE ND_GROUP_CARD (
    GNO  INTEGER NOT NULL COMMENT '그룹번호', -- 그룹번호
    BCNO INTEGER NOT NULL COMMENT '명함번호' -- 명함번호
)
COMMENT '그룹명함';

-- 그룹명함
ALTER TABLE ND_GROUP_CARD
    ADD CONSTRAINT PK_ND_GROUP_CARD -- 그룹명함 기본키
        PRIMARY KEY (
            GNO,  -- 그룹번호
            BCNO  -- 명함번호
        );

ALTER TABLE ND_GROUP_CARD
    MODIFY COLUMN GNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '그룹번호';

-- 명함
ALTER TABLE ND_BIZCARD
    ADD CONSTRAINT FK_ND_MEMBER_TO_ND_BIZCARD -- 회원 -> 명함
        FOREIGN KEY (
            MNO -- 회원번호
        )
        REFERENCES ND_MEMBER ( -- 회원
            MNO -- 회원번호
        );

-- 그룹
ALTER TABLE ND_GROUP
    ADD CONSTRAINT FK_ND_MEMBER_TO_ND_GROUP -- 회원 -> 그룹
        FOREIGN KEY (
            MNO -- 회원번호
        )
        REFERENCES ND_MEMBER ( -- 회원
            MNO -- 회원번호
        );

-- 그룹명함
ALTER TABLE ND_GROUP_CARD
    ADD CONSTRAINT FK_ND_GROUP_TO_ND_GROUP_CARD -- 그룹 -> 그룹명함
        FOREIGN KEY (
            GNO -- 그룹번호
        )
        REFERENCES ND_GROUP ( -- 그룹
            GNO -- 그룹번호
        );

-- 그룹명함
ALTER TABLE ND_GROUP_CARD
    ADD CONSTRAINT FK_ND_BIZCARD_TO_ND_GROUP_CARD -- 명함 -> 그룹명함
        FOREIGN KEY (
            BCNO -- 명함번호
        )
        REFERENCES ND_BIZCARD ( -- 명함
            BCNO -- 명함번호
        );