CREATE TABLE `users` (
    `u_id`       INT          NOT NULL AUTO_INCREMENT,
    `u_username` VARCHAR(45)  NOT NULL UNIQUE,
    `u_email`    VARCHAR(75)  NOT NULL UNIQUE,
    `u_password` TEXT         NOT NULL,
    `u_image`    LONGBLOB,    

    PRIMARY KEY (`u_id`)
);