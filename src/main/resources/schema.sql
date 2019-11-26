DROP DATABASE IF EXISTS ssm;
CREATE DATABASE ssm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ssm;

CREATE TABLE Todo
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `content`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `ssm`.`Topic`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `title`   VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `userId` INT NOT NULL ,
  `boardId` INT NOT NULL ,
  `createdTime` VARCHAR(255) NOT NULL,
  `updatedTime` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `ssm`.`User`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'guest', 'normal') NOT NULL,
  `avatar` VARCHAR(500) NOT NULL,
  `createdTime` VARCHAR(255) NOT NULL,
  `updatedTime` VARCHAR(255) NOT NULL,
  `mail` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ssm`.`TopicComment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `userId` INT NOT NULL,
  `topicId` INT NOT NULL,
  `createdTime` VARCHAR(255) NOT NULL,
  `updatedTime` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `ssm`.`Board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
