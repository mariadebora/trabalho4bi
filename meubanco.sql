CREATE DATABASE `produtosmari` /*!40100 DEFAULT CHARACTER SET latin1 */;
CREATE TABLE `arquivo` (
  `id_arquivo` int(11) NOT NULL AUTO_INCREMENT,
  `bytes` tinyblob NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id_arquivo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
CREATE TABLE `produto` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `lote` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `preco` varchar(255) NOT NULL,
  `validade` datetime NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `FK_6oyw15c6e7i6ruvc50qsli3is` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `id_foto` int(11) DEFAULT NULL,
  `nascimento` datetime NOT NULL,
  `nome` varchar(255) NOT NULL,
  `rg` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `sexo` char(1) NOT NULL,
  `tipoUsuario` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
