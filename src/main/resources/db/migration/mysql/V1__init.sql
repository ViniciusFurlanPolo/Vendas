-- -----------------------------------------------------
-- Table `Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` CHAR(100) NOT NULL,
  `totalVendas` INT NOT NULL,
    PRIMARY KEY(`id`)
)
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
    PRIMARY KEY(`id`),
    CONSTRAINT `FK_Funcionario_id`
    FOREIGN KEY(`id_func`)
    REFERENCES `Funcionario` (`id`)
)
ENGINE = InnoDB;