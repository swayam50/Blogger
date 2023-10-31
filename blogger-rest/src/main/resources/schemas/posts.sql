CREATE TABLE `posts` (
    `p_id`          BIGINT          NOT NULL AUTO_INCREMENT,
    `p_title`       VARCHAR(50)  NOT NULL UNIQUE,
    `p_description` VARCHAR(255),
    `p_image`       TEXT,
    `p_datetime`    DATETIME,
    `u_id`          INT,

    PRIMARY KEY (`p_id`),
    FOREIGN KEY (`u_id`) REFERENCES users(`u_id`)
);