CREATE TABLE `comments` (
    `c_id`             BIGINT       NOT NULL AUTO_INCREMENT,
    `c_content`        TEXT         NOT NULL,
    `c_publishedOn`    DATETIME,
    `u_id`             VARCHAR(255) NOT NULL,
    `p_id`             BIGINT       NOT NULL,

    PRIMARY KEY (`c_id`),
    FOREIGN KEY (`u_id`) REFERENCES users(`u_id`),
    FOREIGN KEY (`p_id`) REFERENCES posts(`p_id`)
);
