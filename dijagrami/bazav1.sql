SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `in4maticsquiz` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `in4maticsquiz` ;

-- -----------------------------------------------------
-- Table `in4maticsquiz`.`tip_korisnika`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`tip_korisnika` (
  `IDtip` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`IDtip`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `in4maticsquiz`.`korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`korisnik` (
  `IDkorisnik` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(50) NULL,
  `prezime` VARCHAR(50) NULL,
  `korisnickoIme` VARCHAR(50) NOT NULL,
  `lozinka` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `IDtip` INT NOT NULL,
  PRIMARY KEY (`IDkorisnik`),
  INDEX `fk_korisnik_tip_korisnika_idx` (`IDtip` ASC),
  CONSTRAINT `fk_korisnik_tip_korisnika`
    FOREIGN KEY (`IDtip`)
    REFERENCES `in4maticsquiz`.`tip_korisnika` (`IDtip`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `in4maticsquiz`.`razred`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`razred` (
  `IDrazred` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`IDrazred`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `in4maticsquiz`.`poglavlje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`poglavlje` (
  `IDpoglavlje` INT NOT NULL AUTO_INCREMENT,
  `naziv` INT NOT NULL,
  `ukljuceno` TINYINT(1) NOT NULL,
  PRIMARY KEY (`IDpoglavlje`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `in4maticsquiz`.`pitanja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`pitanja` (
  `IDpitanja` INT NOT NULL AUTO_INCREMENT,
  `pitanje` TEXT NULL,
  `poglavlje` INT NOT NULL,
  `IDpoglavlje` INT NOT NULL,
  PRIMARY KEY (`IDpitanja`),
  INDEX `fk_pitanja_poglavlje1_idx` (`IDpoglavlje` ASC),
  CONSTRAINT `fk_pitanja_poglavlje1`
    FOREIGN KEY (`IDpoglavlje`)
    REFERENCES `in4maticsquiz`.`poglavlje` (`IDpoglavlje`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `in4maticsquiz`.`odgovor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`odgovor` (
  `IDodgovor` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`IDodgovor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `in4maticsquiz`.`odgovor_pripada_pitanju_pripada_razredu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`odgovor_pripada_pitanju_pripada_razredu` (
  `IDpitanja` INT NOT NULL,
  `IDodgovor` INT NOT NULL,
  `IDrazred` INT NOT NULL,
  INDEX `fk_pitanja_has_odgovor_odgovor1_idx` (`IDodgovor` ASC),
  INDEX `fk_pitanja_has_odgovor_pitanja1_idx` (`IDpitanja` ASC),
  INDEX `fk_pitanja_has_odgovor_razred1_idx` (`IDrazred` ASC),
  CONSTRAINT `fk_pitanja_has_odgovor_pitanja1`
    FOREIGN KEY (`IDpitanja`)
    REFERENCES `in4maticsquiz`.`pitanja` (`IDpitanja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pitanja_has_odgovor_odgovor1`
    FOREIGN KEY (`IDodgovor`)
    REFERENCES `in4maticsquiz`.`odgovor` (`IDodgovor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pitanja_has_odgovor_razred1`
    FOREIGN KEY (`IDrazred`)
    REFERENCES `in4maticsquiz`.`razred` (`IDrazred`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `in4maticsquiz`.`rezultat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `in4maticsquiz`.`rezultat` (
  `IDrezultat` INT NOT NULL AUTO_INCREMENT,
  `IDkorisnik` INT NOT NULL,
  `IDrazred` INT NOT NULL,
  `bodovi` INT NOT NULL,
  INDEX `fk_pitanja_has_odgovor_has_korisnik_korisnik1_idx` (`IDkorisnik` ASC),
  INDEX `fk_pitanja_has_odgovor_has_korisnik_razred1_idx` (`IDrazred` ASC),
  PRIMARY KEY (`IDrezultat`),
  CONSTRAINT `fk_pitanja_has_odgovor_has_korisnik_korisnik1`
    FOREIGN KEY (`IDkorisnik`)
    REFERENCES `in4maticsquiz`.`korisnik` (`IDkorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pitanja_has_odgovor_has_korisnik_razred1`
    FOREIGN KEY (`IDrazred`)
    REFERENCES `in4maticsquiz`.`razred` (`IDrazred`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
