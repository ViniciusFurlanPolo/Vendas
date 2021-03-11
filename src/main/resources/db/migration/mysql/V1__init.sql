-- -----------------------------------------------------
-- Table `Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` CHAR(100) NOT NULL,
  `totalVendas` INT NOT NULL,
    PRIMARY KEY(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Venda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dataVenda` DATE NOT NULL,
  `valor` DOUBLE NOT NULL,
  `id_func` INT NOT NULL,
  `nome_func` CHAR(100) NOT NULL,
    PRIMARY KEY(`id`)
    FOREIGN KEY(`id_func`)
    REFERENCES `funcionario` (`id`)
    FOREIGN KEY(`nome_func`)
    REFERENCES `fucionario` (`nome`)
    ON DELETE NO ACTION
    ON UPTADE NO ACTION)
ENGINE = InnoDB;