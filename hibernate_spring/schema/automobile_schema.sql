-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema auto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema auto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `auto` ;
USE `auto` ;

-- -----------------------------------------------------
-- Table `auto`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'Client',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`.`User_Data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`User_Data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `id_User` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Data_User_idx` (`id_User` ASC),
  CONSTRAINT `fk_User_Data_User`
    FOREIGN KEY (`id_User`)
    REFERENCES `auto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`.`Auto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`Auto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mark` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `year` DATE NOT NULL,
  `spec` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`.`Dealer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`Dealer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`.`Shopping_Сart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`Shopping_Сart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_User` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Shopping_Сart_User1_idx` (`id_User` ASC),
  CONSTRAINT `fk_Shopping_Сart_User1`
    FOREIGN KEY (`id_User`)
    REFERENCES `auto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`.`User_History`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`User_History` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_User` INT NOT NULL,
  `id_Shoping` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`.`Auto_Dealer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`Auto_Dealer` (
  `id_Auto` INT NOT NULL,
  `id_Dealer` INT NOT NULL,
  `cost` DOUBLE NOT NULL,
  PRIMARY KEY (`id_Auto`, `id_Dealer`),
  INDEX `fk_Auto_has_Dealer_Dealer1_idx` (`id_Dealer` ASC),
  INDEX `fk_Auto_has_Dealer_Auto1_idx` (`id_Auto` ASC),
  CONSTRAINT `fk_Auto_has_Dealer_Auto1`
    FOREIGN KEY (`id_Auto`)
    REFERENCES `auto`.`Auto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Auto_has_Dealer_Dealer1`
    FOREIGN KEY (`id_Dealer`)
    REFERENCES `auto`.`Dealer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`.`Сart_Auto_Dealer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto`.`Сart_Auto_Dealer` (
  `id_Shopping_Сart` INT NOT NULL,
  `id_Auto_Auto_Dealer` INT NOT NULL,
  `id_Dealer_Auto_Dealer` INT NOT NULL,
  PRIMARY KEY (`id_Shopping_Сart`, `id_Auto_Auto_Dealer`, `id_Dealer_Auto_Dealer`),
  INDEX `fk_Shopping_Сart_has_Auto_Dealer_Auto_Dealer1_idx` (`id_Auto_Auto_Dealer` ASC, `id_Dealer_Auto_Dealer` ASC),
  INDEX `fk_Shopping_Сart_has_Auto_Dealer_Shopping_Сart1_idx` (`id_Shopping_Сart` ASC),
  CONSTRAINT `fk_Shopping_Сart_has_Auto_Dealer_Shopping_Сart1`
    FOREIGN KEY (`id_Shopping_Сart`)
    REFERENCES `auto`.`Shopping_Сart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Shopping_Сart_has_Auto_Dealer_Auto_Dealer1`
    FOREIGN KEY (`id_Auto_Auto_Dealer` , `id_Dealer_Auto_Dealer`)
    REFERENCES `auto`.`Auto_Dealer` (`id_Auto` , `id_Dealer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
