

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema wcc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wcc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wcc` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `wcc` ;

-- -----------------------------------------------------
-- Table `wcc`.`collection_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`collection_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` INT NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `price` INT NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `url` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `wcc`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `point` INT NOT NULL DEFAULT '1000',
  `money` INT NOT NULL DEFAULT '0',
  `admin` TINYINT NOT NULL DEFAULT '1',
  `suspension_day` DATETIME NULL DEFAULT NULL,
  `current_login` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `UKfkaovnen2l4coihx0ggycgfmt` (`email` ASC, `nickname` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wcc`.`member_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`member_item` (
  `collection_id` INT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `wear` TINYINT NOT NULL DEFAULT '0',
  `buy` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`collection_id`, `member_id`),
  INDEX `member_id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK1pdtrsq8bseum0ugb4rlj61m3`
    FOREIGN KEY (`collection_id`)
    REFERENCES `wcc`.`collection_item` (`id`),
  CONSTRAINT `member_id2`
    FOREIGN KEY (`member_id`)
    REFERENCES `wcc`.`member` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wcc`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`notice` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT NULL DEFAULT NULL,
  `subject` VARCHAR(30) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `hit` BIGINT NOT NULL DEFAULT '1',
  `date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `member_id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `member_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `wcc`.`member` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wcc`.`record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`record` (
  `record_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `smile_count` INT NOT NULL,
  `change_point` INT NOT NULL,
  `change_money` INT NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `rank` INT NOT NULL,
  PRIMARY KEY (`record_id`, `member_id`),
  INDEX `member_id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `member_id1`
    FOREIGN KEY (`member_id`)
    REFERENCES `wcc`.`member` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wcc`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`report` (
  `date` DATE NOT NULL,
  `member_id` BIGINT NOT NULL,
  `count` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`date`, `member_id`),
  INDEX `member_id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `member_id3`
    FOREIGN KEY (`member_id`)
    REFERENCES `wcc`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wcc`.`topic_type_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`topic_type_detail` (
  `type` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `sub_keyword` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wcc`.`topic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wcc`.`topic` (
  `type` INT NOT NULL,
  `keyword` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`type`, `keyword`),
  CONSTRAINT `topic_ibfk_1`
    FOREIGN KEY (`type`)
    REFERENCES `wcc`.`topic_type_detail` (`type`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- collection_item
-- =====================
-- collecton item type
-- 1. 프로필
-- 2. 휘장
-- 3. 테두리
-- =====================
insert into wcc.collection_item (type, name, price, description, url)
values (1, "기본 프로필", 0, "기본 프로필", "https://wcc-ssafy.s3.ap-northeast-2.amazonaws.com/profile.png"),
(1, "신입 고양이", 500, "단정 + 긴장한 고양이", "https://wcc-ssafy.s3.ap-northeast-2.amazonaws.com/profile.jpg"),
(1, "F4 고양이", 1200, "바람과 흰 천만 있다면 어디든 같이 갈 수 있어", "https://wcc-ssafy.s3.ap-northeast-2.amazonaws.com/profile_mid.jpg"),
(1, "미치광 냥", 700, "넌 광냥을 떠돌고 있어 yayayayayayaya", "https://wcc-ssafy.s3.ap-northeast-2.amazonaws.com/profile_low.jpg"),
(1, "보라돌이 야용", 1500, "보라돌이뚜비나나보다 내가 낫지 않니?", "https://wcc-ssafy.s3.ap-northeast-2.amazonaws.com/profile_high.jpg"),
(1, "관리자 프로필", 0, "관리자 프로필", "https://wcc-ssafy.s3.ap-northeast-2.amazonaws.com/admin.png"),
(2, "금장", 1500, "화려하게 빛이 나는 내 이름", "#ffd700"),
(2, "은장", 1000, "갈치처럼 번짝이는 내 이름", "#c0c0c0"),
(2, "동장", 500, "그거 아시나요? 가장 먼저 발견된 금속이 구리랍니다. 구리처럼 전통있는 이름을 가져보세요.", "#ab5033"),
(3, "금투리", 1500, "화려하게 빛이 나는 내 프로필", "#ffd700"),
(3, "은투리", 1000, "갈치처럼 번쩍이는 내 프로필", "#c0c0c0"),
(3, "동투리", 500, "그거 아시나요? 가장 먼저 발견된 금속이 구리랍니다. 구리처럼 전통있는 프로필을 가져보세요.", "#ab5033");

-- notice dump
insert into wcc.notice (subject, content, hit, date)
values ("웃참클럽 개장 기념 이벤트 공지", "웃참클럽의 개장을 축하하기 위해 웃참클럽이 생일 아이콘을 쏩니다! 더불어 저녁 9시부터 11시까지 게임 시 포인트 50% 추가 증정 등 다양한 혜택을 즐기세요!", 0, "2023-08-16 00:00:00");

-- topic type detail
insert into wcc.topic_type_detail (type, name, sub_keyword)
values (1, "삼행시", 1), (2, "이름 짓기", 1), (3, "아재 개그", 0), (4, "슬랩스틱", 0), (5, "성대모사", 0), (6, "자유", 0);

-- topic type DB dump 파일
insert into wcc.topic (type, keyword)
values
(1, "노트북"), (1, "디지털"), (1, "물고기"), (1, "별자리"), (1, "빗소리"),
(1, "오징어"), (1, "홍길동"), (1, "냉장고"), (1, "콩나물"), (1, "자전거"),
(2, "노씨 자식"), (2, "방씨 자식"), (2, "설씨 자식"), (2, "유씨 애인"), (2, "옥씨 애인"), 
(2, "정씨 애인"), (2, "조선시대 식"), (2, "르네상스 식"), (2, "동물 식"), (2, "민씨 조카");


-- report procedure
DELIMITER $$
CREATE PROCEDURE reportProc (
)
BEGIN

    delete from report where date IN (SELECT temp.date FROM (select * from report WHERE date<=(CURDATE()-INTERVAL 4 DAY)) AS temp);

    UPDATE member m
        JOIN (
            SELECT r.member_id
            FROM (
                SELECT member_id, SUM(count) AS total_count
                FROM report
                GROUP BY member_id
            ) r
            WHERE r.total_count > 40
        ) sub ON m.id = sub.member_id
    SET m.suspension_day = DATE_ADD(CURDATE(), INTERVAL 2 DAY);
    
    
    UPDATE report re
        JOIN (
            SELECT r.member_id
            FROM (
                SELECT member_id, SUM(count) AS total_count
                FROM report
                GROUP BY member_id
            ) r
            WHERE r.total_count > 40
        ) sub ON re.member_id = sub.member_id
    SET re.count = 0;
    
    END $$
DELIMITER ;
-- DROP PROCEDURE reportProc;
-- CALL reportProc();
-- SHOW PROCEDURE STATUS;

create event report_event
on schedule 
    EVERY 1 DAY
    STARTS '2023-08-09 00:00:00'
do
    CALL reportProc();

-- point procedure
DELIMITER &&

CREATE PROCEDURE pointProc(IN nickname_input VARCHAR(255), IN point_change INT, In money_change INT)
BEGIN
    DECLARE current_point INT;
    DECLARE current_money Int;
    
    SELECT point INTO current_point FROM member WHERE nickname = nickname_input;
    SELECT money INTO current_money FROM member WHERE nickname = nickname_input;
     
    SET current_point = current_point + point_change;
    SET current_money = current_money + money_change;

    IF current_point < 0 THEN
        SET current_point = 0;
    END IF;

    UPDATE member SET point = current_point WHERE nickname = nickname_input;
    UPDATE member SET money = current_money WHERE nickname = nickname_input;
    
    commit;
END &&

DELIMITER ;
