CREATE TABLE sport(
                      spo_no BIGINT PRIMARY KEY AUTO_INCREMENT,
                      spo_name VARCHAR(30) NOT NULL UNIQUE KEY,
                      spo_category VARCHAR(10)
);

CREATE TABLE user(
                     no BIGINT PRIMARY KEY AUTO_INCREMENT,
                     nickname VARCHAR(30) UNIQUE KEY,
                     name VARCHAR(10) NOT NULL,
                     email VARCHAR(40) NOT NULL UNIQUE KEY,
                     password VARCHAR(200) NOT NULL,
                     age int ,
                     spo_no BIGINT,
                     sport_time_from TIME,
                     sport_time_until TIME,
                     role VARCHAR(10) NOT NULL default "ROLE_USER",
                     create_date DATETIME,
                     modified_date DATETIME,
                     message VARCHAR(10),
                     oauth_member_check VARCHAR(2)
);
CREATE TABLE board(
                      board_no BIGINT PRIMARY KEY AUTO_INCREMENT,
                      no BIGINT NOT NULL,
                      spo_no BIGINT NOT NULL,
                      board_name VARCHAR(40) NOT NULL,
                      board_content VARCHAR(400) NOT NULL,
                      board_promise_from DATETIME,
                      board_promise_until DATETIME,
                      board_map_cordx VARCHAR(50),
                      board_map_cordy VARCHAR(50),
                      board_map_name VARCHAR(30),
                      board_status VARCHAR(5),
                      create_date DATETIME default now()
);

CREATE TABLE img(
                    img_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    img_origin VARCHAR(50) NOT NULL,
                    img_new VARCHAR(150) NOT NULL,
                    img_directory VARCHAR(200) NOT NULL,
                    img_date DATETIME default now(),
                    board_no BIGINT
);