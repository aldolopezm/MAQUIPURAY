-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.24 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_categoria: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_categoria` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_categoria` (`idCategoria`, `slug`, `nombre`, `descripcion`, `imagen`, `fechaRegistro`, `estado`) VALUES
	(1, 'categoria', 'Dispositivo', 'Dispositivo Descripcion', 'imagen.png', '2020-06-16 16:12:38', 1);
/*!40000 ALTER TABLE `tbl_categoria` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_direccion
CREATE TABLE IF NOT EXISTS `tbl_direccion` (
  `idDireccion` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `referencia` mediumtext,
  `longitud` varchar(200) DEFAULT NULL,
  `latitud` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idDireccion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_direccion: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_direccion` DISABLE KEYS */;
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
  PRIMARY KEY (`idNegocio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_negocio: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_negocio` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_negocio` (`idNegocio`, `idUsuario`, `idCategoria`, `slug`, `nombre`, `descripcion`, `direccion`, `telefono`, `latitud`, `longitud`, `protocoloSeguridad`, `estado`) VALUES
	(1, 1, 1, 'negocio', 'Negocio', 'Descripcion Negocio', 'Tacna', '918468770', '1', '2', '1', 1);
/*!40000 ALTER TABLE `tbl_negocio` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_persona
CREATE TABLE IF NOT EXISTS `tbl_persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_persona: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_persona` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_persona` (`idPersona`, `nombre`, `apellido`, `telefono`, `email`) VALUES
	(1, NULL, NULL, NULL, 'Demo@gmail.com');
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
  PRIMARY KEY (`idPromocion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_promocion: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_promocion` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_promocion` (`idPromocion`, `idNegocio`, `codigoPromocion`, `slug`, `nombre`, `descripcion`, `imagen`, `precio`) VALUES
	(1, 1, 'SK-13', 'promocion', 'promocion', 'promocion descripcion', 'imagen.png', 1.000);
/*!40000 ALTER TABLE `tbl_promocion` ENABLE KEYS */;

-- Volcando estructura para tabla db_maquipuray.tbl_usuario
CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) DEFAULT NULL,
  `idTipoUsuario` int(11) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  `verificado` tinyint(4) DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_maquipuray.tbl_usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_usuario` (`idUsuario`, `idPersona`, `idTipoUsuario`, `email`, `password`, `token`, `verificado`, `fechaRegistro`, `estado`) VALUES
	(1, 1, 2, 'Demo@gmail.com', '$2y$10$NeghmalGkL6vgHZbaNlUbOoX7Orq7dFJySH./SnR7hf9HxEv5inH6', NULL, 1, '2020-06-16 21:58:31', 1);
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
