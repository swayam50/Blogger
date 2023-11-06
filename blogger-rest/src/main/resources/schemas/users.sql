USE blogger_db;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `u_id`       VARCHAR(36)  NOT NULL UNIQUE COMMENT "User's unique id.",
    `u_username` VARCHAR(45)  NOT NULL UNIQUE COMMENT "User's unique username.",
    `u_email`    VARCHAR(75)  NOT NULL UNIQUE COMMENT "User's unique email.",
    `u_password` TEXT         NOT NULL        COMMENT "User's encrypted password.",   

    PRIMARY KEY (`u_id`)
);