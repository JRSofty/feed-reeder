CREATE TABLE `group_feed`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title` VARCHAR(100) NOT NULL,
	`description` VARCHAR(255),
	`parent_id` BIGINT(20)
);

CREATE TABLE `web_feed`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title` VARCHAR(100) NOT NULL,
	`description` VARCHAR(255),
	`parent_id` BIGINT(20),
	`html_url` VARCHAR(255),
	`feed_url` VARCHAR(255) NOT NULL,
	`feed_type` INT(5) NOT NULL,
	`last_update_attempt` DATETIME,
	`last_update_success` DATETIME,
	`last_update_failure` DATETIME,
	`last_failure_reason` VARCHAR(255),
	`cron_expression` VARCHAR(40) NOT NULL DEFAULT '0 15 10 * * ?'
);

CREATE TABLE `feed_item`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title` VARCHAR(100) NOT NULL,
	`parent_id` BIGINT(20) NOT NULL,
	`link_url` VARCHAR(255) NOT NULL,
	`pub_date` DATETIME NOT NULL,
	`description` TEXT NOT NULL,
	`viewed` TINYINT(2) NOT NULL
);
