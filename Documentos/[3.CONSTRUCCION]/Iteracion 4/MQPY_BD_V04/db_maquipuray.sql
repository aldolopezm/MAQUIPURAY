-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.24 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para db_maquipuray
CREATE DATABASE IF NOT EXISTS `db_maquipuray` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_maquipuray`;

-- Volcando estructura para tabla db_maquipuray.tbl_categoria
CREATE TABLE IF NOT EXISTS `tbl_categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `slug` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` mediumtext,
  `imagen` varchar(100) DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_categoria: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_categoria` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_categoria` (`idCategoria`, `slug`, `nombre`, `descripcion`, `imagen`, `fechaRegistro`, `estado`) VALUES
	(1, 'minimarkets', 'Minimarkets', 'Minimarkets', '0kRd28L5QH43Q1Dj5K2Sv6F1wOgUhzOSHhjL.png', '2020-06-29 17:26:56', 1),
	(2, 'comidas', 'Comidas', 'Comidas', 'btn_comidas.png', '2020-06-29 17:26:56', 1),
	(3, 'belleza', 'Belleza', 'Belleza', 'btn_belleza.png', '2020-06-29 17:26:56', 1),
	(4, 'ferreterias', 'Ferreterias', 'Ferreterias', 'btn_Ferreteria.png', '2020-06-29 17:26:56', 1),
	(5, 'librerias', 'Librerias', 'Librerias', 'btn_librerias.png', '2020-06-29 17:26:56', 1),
	(6, 'otros', 'Otros', 'Otros', 'btn_otros.png', '2020-06-29 17:26:56', 1);
/*!40000 ALTER TABLE `tbl_categoria` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_direccion
CREATE TABLE IF NOT EXISTS `tbl_direccion` (
  `idDireccion` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `referencia` mediumtext,
  `longitud` varchar(200) DEFAULT NULL,
  `latitud` varchar(200) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idDireccion`),
  KEY `FK_tbl_direccion_tbl_usuario` (`idUsuario`),
  CONSTRAINT `FK_tbl_direccion_tbl_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `tbl_usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_direccion: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_direccion` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_direccion` (`idDireccion`, `idUsuario`, `direccion`, `referencia`, `longitud`, `latitud`, `estado`) VALUES
	(1, 2, 'direccion', 'referencia', '5', '2', 0),
	(2, 2, 'direccion', 'referencia', '5', '2', 1);
/*!40000 ALTER TABLE `tbl_direccion` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_negocio
CREATE TABLE IF NOT EXISTS `tbl_negocio` (
  `idNegocio` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `slug` varchar(100) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `descripcion` mediumtext,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `latitud` varchar(100) DEFAULT NULL,
  `longitud` varchar(100) DEFAULT NULL,
  `protocoloSeguridad` varchar(200) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idNegocio`),
  KEY `FK_tbl_negocio_tbl_usuario` (`idUsuario`),
  KEY `FK_tbl_negocio_tbl_categoria` (`idCategoria`),
  CONSTRAINT `FK_tbl_negocio_tbl_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `tbl_categoria` (`idCategoria`),
  CONSTRAINT `FK_tbl_negocio_tbl_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `tbl_usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_negocio: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_negocio` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_negocio` (`idNegocio`, `idUsuario`, `idCategoria`, `slug`, `nombre`, `descripcion`, `direccion`, `telefono`, `latitud`, `longitud`, `protocoloSeguridad`, `estado`) VALUES
	(1, 2, 1, 'mi-negocio', 'Mi Negocio', 'Mi Negocio', 'Tacna', NULL, '1', '2', NULL, 1);
/*!40000 ALTER TABLE `tbl_negocio` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_negocio_transporte
CREATE TABLE IF NOT EXISTS `tbl_negocio_transporte` (
  `idNegocioTransporte` int(11) NOT NULL AUTO_INCREMENT,
  `idNegocio` int(11) NOT NULL,
  `idTransporte` int(11) NOT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idNegocioTransporte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_negocio_transporte: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_negocio_transporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_negocio_transporte` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_pedido
CREATE TABLE IF NOT EXISTS `tbl_pedido` (
  `idPedido` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `idPromocion` int(11) DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `FK_tbl_pedido_tbl_usuario` (`idUsuario`),
  KEY `FK_tbl_pedido_tbl_promocion` (`idPromocion`),
  CONSTRAINT `FK_tbl_pedido_tbl_promocion` FOREIGN KEY (`idPromocion`) REFERENCES `tbl_promocion` (`idPromocion`),
  CONSTRAINT `FK_tbl_pedido_tbl_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `tbl_usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_pedido: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_pedido` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_pedido` (`idPedido`, `idUsuario`, `idPromocion`, `fechaRegistro`, `estado`) VALUES
	(1, 2, 1, '2020-07-06 12:14:29', 1),
	(2, 2, 1, '2020-07-06 13:03:31', 1),
	(3, 2, 1, '2020-07-06 13:04:09', 1),
	(4, 2, 1, '2020-07-06 13:04:10', 1),
	(5, 2, 1, '2020-07-06 13:05:24', 1);
/*!40000 ALTER TABLE `tbl_pedido` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_persona
CREATE TABLE IF NOT EXISTS `tbl_persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_persona: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_persona` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_persona` (`idPersona`, `nombre`, `apellido`, `telefono`, `email`) VALUES
	(1, 'Briken', 'Studio', '952585652', 'administrador@gmail.com'),
	(2, 'Javier', 'Pino', NULL, 'Javier@gmail.com');
/*!40000 ALTER TABLE `tbl_persona` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_promocion
CREATE TABLE IF NOT EXISTS `tbl_promocion` (
  `idPromocion` int(11) NOT NULL AUTO_INCREMENT,
  `idNegocio` int(11) DEFAULT NULL,
  `codigoPromocion` varchar(100) DEFAULT NULL,
  `slug` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` mediumtext,
  `imagen` varchar(200) DEFAULT NULL,
  `precio` decimal(12,3) DEFAULT NULL,
  `descuento` decimal(12,2) DEFAULT NULL,
  `fechaValidaPromocion` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idPromocion`),
  KEY `FK_tbl_promocion_tbl_negocio` (`idNegocio`),
  CONSTRAINT `FK_tbl_promocion_tbl_negocio` FOREIGN KEY (`idNegocio`) REFERENCES `tbl_negocio` (`idNegocio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_promocion: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_promocion` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_solicitudes_transporte
CREATE TABLE IF NOT EXISTS `tbl_solicitudes_transporte` (
  `idSolicitudTransporte` int(11) NOT NULL AUTO_INCREMENT,
  `idNegocio` int(11) DEFAULT NULL,
  `idTransporte` int(11) DEFAULT NULL,
  `descripcion` mediumtext,
  `fechaRegistro` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idSolicitudTransporte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_solicitudes_transporte: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_solicitudes_transporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_solicitudes_transporte` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_transporte
CREATE TABLE IF NOT EXISTS `tbl_transporte` (
  `idTransporte` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `nombreEmpresa` varchar(200) DEFAULT NULL,
  `placa` varchar(200) DEFAULT NULL,
  `imagen` varchar(200) DEFAULT NULL,
  `protocoloSeguridad` varchar(200) DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idTransporte`),
  KEY `FK_tbl_transporte_tbl_usuario` (`idUsuario`),
  CONSTRAINT `FK_tbl_transporte_tbl_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `tbl_usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_transporte: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_transporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_transporte` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_usuario
CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) DEFAULT NULL,
  `idTipoUsuario` int(11) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `token` mediumtext,
  `UltimaFechaSesion` datetime DEFAULT NULL,
  `verificado` tinyint(4) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `tokenMovil` varchar(200) DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_tbl_usuario_tbl_persona` (`idPersona`),
  CONSTRAINT `FK_tbl_usuario_tbl_persona` FOREIGN KEY (`idPersona`) REFERENCES `tbl_persona` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_usuario` (`idUsuario`, `idPersona`, `idTipoUsuario`, `email`, `password`, `token`, `UltimaFechaSesion`, `verificado`, `avatar`, `tokenMovil`, `fechaRegistro`, `estado`) VALUES
	(1, 1, 1, 'administrador@gmail.com', '$2y$10$U6rhHQ7.3cpOrNSFHebjX.mZHzSogLM/WUWFOt5losrD8cnAMPc/e', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9tYXF1aXB1cmF5LmxvY2FsXC9Vc3VhcmlvQXV0ZW50aWNhcldlYkpzb24iLCJpYXQiOjE1OTQ3NDM1MTYsIm5iZiI6MTU5NDc0MzUxNiwianRpIjoiUFdPUW1xTlVGQ3pxcHVYWSIsInN1YiI6MSwicHJ2IjoiMTQzMDZkZDhiNjllMjUyMmRhMTk1NzU5YmJlMTIyYmNkMWU1MjJkMSJ9.Xqo1nwDGANsv-CwZFsOmKjXJq0lKNsfQXwj7xlGPMEo', '2020-07-14 11:18:36', 1, NULL, NULL, '2020-06-29 17:26:56', 1),
	(2, 2, 2, 'Javier@gmail.com', '$2y$10$3aXTAsERgQSeASSXl1JdUuBZfoPKY7B2BCjHoE4mx5/czLpGViBAu', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9tYXF1aXB1cmF5LmxvY2FsXC9Vc3VhcmlvQXV0ZW50aWNhcldlYkpzb24iLCJpYXQiOjE1OTQ3NDQ1NzIsIm5iZiI6MTU5NDc0NDU3MiwianRpIjoiTUlyRDgzTWd1aDNENVdXeSIsInN1YiI6MiwicHJ2IjoiMTQzMDZkZDhiNjllMjUyMmRhMTk1NzU5YmJlMTIyYmNkMWU1MjJkMSJ9.SOOqQAKANNb3diKFnMmqH-5F3SxV0tlhOQkhZodQg5g', '2020-07-14 11:36:12', 1, NULL, NULL, '2020-07-06 10:46:14', 1);
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
