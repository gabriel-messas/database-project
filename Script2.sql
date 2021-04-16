SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `trab` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `trab` ;

-- -----------------------------------------------------
-- Table `trab`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`venda` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DATA` VARCHAR(20) NOT NULL,
  `QUANTIDADE` INT NOT NULL,
  `PRECO` DOUBLE NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`entrega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`entrega` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_VENDA` INT NOT NULL,
  `DATA_PREVISTA` VARCHAR(20) NULL DEFAULT NULL,
  `DATA_ENTREGA` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`, `ID_VENDA`),
  INDEX `fk_entregar_venda` (`ID_VENDA` ASC) VISIBLE,
  CONSTRAINT `fk_entregar_venda`
    FOREIGN KEY (`ID_VENDA`)
    REFERENCES `trab`.`venda` (`ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`avaliacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`avaliacao` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_ENTREGA` INT NOT NULL,
  `NOTA` INT NOT NULL,
  `OBSERVACAO` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`, `ID_ENTREGA`),
  INDEX `fk_avaliar_entrega` (`ID_ENTREGA` ASC) VISIBLE,
  CONSTRAINT `fk_avaliar_entrega`
    FOREIGN KEY (`ID_ENTREGA`)
    REFERENCES `trab`.`entrega` (`ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`contato` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NULL DEFAULT NULL,
  `APELIDO` VARCHAR(45) NULL DEFAULT NULL,
  `EMPRESA` VARCHAR(45) NULL DEFAULT NULL,
  `ENDERECO` VARCHAR(80) NULL DEFAULT NULL,
  `BAIRRO` VARCHAR(45) NULL DEFAULT NULL,
  `CIDADE` VARCHAR(45) NULL DEFAULT NULL,
  `TELEFONE1` VARCHAR(15) NULL DEFAULT NULL,
  `TELEFONE2` VARCHAR(15) NULL DEFAULT NULL,
  `TELEFONE3` VARCHAR(15) NULL DEFAULT NULL,
  `OBSERVACAO` VARCHAR(150) NULL DEFAULT NULL,
  `IDADE` INT NULL DEFAULT NULL,
  `CLIENTE` TINYINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`contato_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`contato_venda` (
  `ID_CONTATO` INT NOT NULL,
  `ID_VENDA` INT NOT NULL,
  PRIMARY KEY (`ID_CONTATO`, `ID_VENDA`),
  INDEX `fk_venda3` (`ID_VENDA` ASC) VISIBLE,
  CONSTRAINT `fk_contato`
    FOREIGN KEY (`ID_CONTATO`)
    REFERENCES `trab`.`contato` (`ID`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_venda3`
    FOREIGN KEY (`ID_VENDA`)
    REFERENCES `trab`.`venda` (`ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`funcionario` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NULL DEFAULT NULL,
  `IDADE` INT NULL DEFAULT NULL,
  `SALARIO` DOUBLE NULL DEFAULT NULL,
  `HORAINICIO` VARCHAR(10) NULL DEFAULT NULL,
  `HORAFIM` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`funcionario_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`funcionario_venda` (
  `ID_FUNCIONARIO` INT NOT NULL,
  `ID_VENDA` INT NOT NULL,
  `COMISSAO` DOUBLE NOT NULL,
  PRIMARY KEY (`ID_FUNCIONARIO`, `ID_VENDA`),
  INDEX `fk_venda` (`ID_VENDA` ASC) VISIBLE,
  CONSTRAINT `fk_funcionario`
    FOREIGN KEY (`ID_FUNCIONARIO`)
    REFERENCES `trab`.`funcionario` (`ID`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_venda`
    FOREIGN KEY (`ID_VENDA`)
    REFERENCES `trab`.`venda` (`ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`produto` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NULL DEFAULT NULL,
  `PRECOVENDA` DOUBLE NULL DEFAULT NULL,
  `QUANTIDADE` INT NULL DEFAULT NULL,
  `PRECOCOMPRA1` VARCHAR(45) NULL DEFAULT NULL,
  `PRECOCOMPRA2` VARCHAR(45) NULL DEFAULT NULL,
  `ID_CONTATO` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_fabrica_contato` (`ID_CONTATO` ASC) VISIBLE,
  CONSTRAINT `fk_fabrica_contato`
    FOREIGN KEY (`ID_CONTATO`)
    REFERENCES `trab`.`contato` (`ID`)
    ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`produto_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`produto_venda` (
  `ID_PRODUTO` INT NOT NULL,
  `ID_VENDA` INT NOT NULL,
  `QUANTIDADE` INT NOT NULL,
  `PRECO` DOUBLE NOT NULL,
  PRIMARY KEY (`ID_PRODUTO`, `ID_VENDA`),
  INDEX `fk_venda2` (`ID_VENDA` ASC) VISIBLE,
  CONSTRAINT `fk_produto`
    FOREIGN KEY (`ID_PRODUTO`)
    REFERENCES `trab`.`produto` (`ID`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_venda2`
    FOREIGN KEY (`ID_VENDA`)
    REFERENCES `trab`.`venda` (`ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trab`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trab`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(20) NOT NULL,
  `PASSWORD` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC) VISIBLE,
  UNIQUE INDEX `PASSWORD_UNIQUE` (`PASSWORD` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
