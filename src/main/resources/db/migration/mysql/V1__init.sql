-- -----------------------------------------------------
-- Table `funcioario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` CHAR(100) NOT NULL,
  `total_de_vendas` INT NOT NULL,
    PRIMARY KEY(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_da_venda` DATE NOT NULL,
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