-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TAW_Project
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `TAW_Project` ;

-- -----------------------------------------------------
-- Schema TAW_Project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TAW_Project` DEFAULT CHARACTER SET utf8 ;
USE `TAW_Project` ;

-- -----------------------------------------------------
-- Table `TAW_Project`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW_Project`.`Usuario` (
  `idUsuario` INT GENERATED ALWAYS AS () VIRTUAL,
  `admin` TINYINT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `nombrel` VARCHAR(50) NULL,
  `email` VARCHAR(50) NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAW_Project`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW_Project`.`Categoria` (
  `idCategoria` INT GENERATED ALWAYS AS () VIRTUAL,
  `nombre` VARCHAR(50) NOT NULL,
  `Categoria_idCategoria` INT NULL,
  PRIMARY KEY (`idCategoria`),
  INDEX `fk_Categoria_Categoria1_idx` (`Categoria_idCategoria` ASC) VISIBLE,
  CONSTRAINT `fk_Categoria_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `TAW_Project`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAW_Project`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW_Project`.`Producto` (
  `idProducto` INT GENERATED ALWAYS AS (),
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(200) NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `fecha` DATE NULL,
  `foto` MEDIUMBLOB NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idProducto`, `Categoria_idCategoria`),
  INDEX `fk_Producto_Usuario_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  INDEX `fk_Producto_Categoria1_idx` (`Categoria_idCategoria` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_Usuario`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `TAW_Project`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `TAW_Project`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAW_Project`.`Palabras_Clave`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW_Project`.`Palabras_Clave` (
  `idPalabrasClave` INT GENERATED ALWAYS AS () VIRTUAL,
  `palabra` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idPalabrasClave`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAW_Project`.`Comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW_Project`.`Comentario` (
  `Usuario_idUsuario` INT NOT NULL,
  `Producto_idProducto` INT NOT NULL,
  `texto` VARCHAR(200) NULL,
  `valoracion` INT NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`Usuario_idUsuario`, `Producto_idProducto`),
  INDEX `fk_Usuario_has_Producto_Producto1_idx` (`Producto_idProducto` ASC) VISIBLE,
  INDEX `fk_Usuario_has_Producto_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_Producto_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `TAW_Project`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Producto_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `TAW_Project`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAW_Project`.`Producto_PalabrasClave`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW_Project`.`Producto_PalabrasClave` (
  `Producto_Categoria_idCategoria` INT NOT NULL,
  `PalabrasClave_idPalabrasClave` INT NOT NULL,
  PRIMARY KEY (`Producto_Categoria_idCategoria`, `PalabrasClave_idPalabrasClave`),
  INDEX `fk_Producto_has_PalabrasClave_PalabrasClave1_idx` (`PalabrasClave_idPalabrasClave` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_has_PalabrasClave_Producto1`
    FOREIGN KEY (`Producto_Categoria_idCategoria`)
    REFERENCES `TAW_Project`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_has_PalabrasClave_PalabrasClave1`
    FOREIGN KEY (`PalabrasClave_idPalabrasClave`)
    REFERENCES `TAW_Project`.`Palabras_Clave` (`idPalabrasClave`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
