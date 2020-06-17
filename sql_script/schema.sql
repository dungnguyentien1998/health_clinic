-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema health_clinic
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `health_clinic` ;

-- -----------------------------------------------------
-- Schema health_clinic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `health_clinic` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `health_clinic` ;

-- -----------------------------------------------------
-- Table `health_clinic`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_clinic`.`users` ;

CREATE TABLE IF NOT EXISTS `health_clinic`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `address` VARCHAR(1000) NULL DEFAULT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(500) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `room` VARCHAR(45) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `gender` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `health_clinic`.`clinic_services`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_clinic`.`clinic_services` ;

CREATE TABLE IF NOT EXISTS `health_clinic`.`clinic_services` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `health_clinic`.`calendars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_clinic`.`calendars` ;

CREATE TABLE IF NOT EXISTS `health_clinic`.`calendars` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `time_start` TIME NOT NULL,
  `time_end` TIME NOT NULL,
  `state` INT(11) NOT NULL,
  `service_id` INT(11) NOT NULL,
  `medical_staff_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `service_id_idx` (`service_id` ASC) VISIBLE,
  INDEX `medical_staff_id_idx` (`medical_staff_id` ASC) VISIBLE,
  CONSTRAINT `medical_staff_id`
    FOREIGN KEY (`medical_staff_id`)
    REFERENCES `health_clinic`.`users` (`id`),
  CONSTRAINT `service_id`
    FOREIGN KEY (`service_id`)
    REFERENCES `health_clinic`.`clinic_services` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `health_clinic`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_clinic`.`appointments` ;

CREATE TABLE IF NOT EXISTS `health_clinic`.`appointments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `client_id` INT(11) NOT NULL,
  `calendar_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `calendar_id_UNIQUE` (`calendar_id` ASC) VISIBLE,
  INDEX `calendar_id_idx` (`calendar_id` ASC) VISIBLE,
  INDEX `client_id_idx` (`client_id` ASC) VISIBLE,
  CONSTRAINT `calendar_id`
    FOREIGN KEY (`calendar_id`)
    REFERENCES `health_clinic`.`calendars` (`id`),
  CONSTRAINT `client_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `health_clinic`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `health_clinic`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_clinic`.`roles` ;

CREATE TABLE IF NOT EXISTS `health_clinic`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `health_clinic`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_clinic`.`user_role` ;

CREATE TABLE IF NOT EXISTS `health_clinic`.`user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `health_clinic`.`roles` (`id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_clinic`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
