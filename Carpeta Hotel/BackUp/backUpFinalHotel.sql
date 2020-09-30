-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: HOTEL
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.17.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ALOJAMIENTO`
--

DROP TABLE IF EXISTS `ALOJAMIENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ALOJAMIENTO` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Reservacion` int(11) NOT NULL,
  `Check_Out` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_RESERVACION_IN_ID_RESERVACION` (`Id_Reservacion`),
  CONSTRAINT `FK_RESERVACION_IN_ID_RESERVACION` FOREIGN KEY (`Id_Reservacion`) REFERENCES `RESERVACION` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ALOJAMIENTO`
--

LOCK TABLES `ALOJAMIENTO` WRITE;
/*!40000 ALTER TABLE `ALOJAMIENTO` DISABLE KEYS */;
INSERT INTO `ALOJAMIENTO` VALUES (1,1,0),(2,2,0),(3,3,0),(4,4,0),(5,5,1),(6,6,1),(7,7,1),(8,8,0),(9,9,0),(10,10,0),(11,11,0),(12,12,0),(13,13,0),(14,14,0),(15,15,0),(16,16,0);
/*!40000 ALTER TABLE `ALOJAMIENTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CLIENTE`
--

DROP TABLE IF EXISTS `CLIENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLIENTE` (
  `Dpi` varchar(15) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Nit` varchar(15) NOT NULL,
  PRIMARY KEY (`Dpi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLIENTE`
--

LOCK TABLES `CLIENTE` WRITE;
/*!40000 ALTER TABLE `CLIENTE` DISABLE KEYS */;
INSERT INTO `CLIENTE` VALUES ('cliente1','cliente1','cliente1','cliente1'),('cliente10','cliente10','cliente10','cliente10'),('cliente2','cliente2','cliente2','cliente2'),('cliente3','cliente3','cliente3','cliente3'),('cliente4','cliente4','cliente4','cliente4'),('cliente5','cliente5','cliente5','cliente5'),('cliente6','cliente6','cliente6','cliente6'),('cliente7','cliente7','cliente7','cliente7'),('cliente8','cliente8','cliente8','cliente8'),('cliente9','cliente9','cliente9','cliente9');
/*!40000 ALTER TABLE `CLIENTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONSUMO_RESTAURANTE`
--

DROP TABLE IF EXISTS `CONSUMO_RESTAURANTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONSUMO_RESTAURANTE` (
  `Id_Consumo` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Alojamiento` int(11) NOT NULL,
  `Nombre_Comida` varchar(30) NOT NULL,
  `Precio_Comida` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Fecha_Consumo` date NOT NULL,
  PRIMARY KEY (`Id_Consumo`),
  KEY `FK_ALOJAMIENTO_IN_ID_ALOJAMIENTO` (`Id_Alojamiento`),
  KEY `FK_MENU_IN_NOMBRE_COMIDA` (`Nombre_Comida`),
  CONSTRAINT `FK_ALOJAMIENTO_IN_ID_ALOJAMIENTO` FOREIGN KEY (`Id_Alojamiento`) REFERENCES `ALOJAMIENTO` (`Id`),
  CONSTRAINT `FK_MENU_IN_NOMBRE_COMIDA` FOREIGN KEY (`Nombre_Comida`) REFERENCES `MENU` (`Nombre_Comida`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONSUMO_RESTAURANTE`
--

LOCK TABLES `CONSUMO_RESTAURANTE` WRITE;
/*!40000 ALTER TABLE `CONSUMO_RESTAURANTE` DISABLE KEYS */;
INSERT INTO `CONSUMO_RESTAURANTE` VALUES (1,1,'Carne',70,1,'2018-10-20'),(2,1,'Coca cola',10,1,'2018-10-20'),(3,1,'Lasagna',60,2,'2018-10-20'),(4,1,'Pizza',100,1,'2018-10-20'),(5,1,'Pollo',50,3,'2018-10-20'),(6,1,'Carne',70,1,'2018-10-20'),(7,1,'Coca cola',10,2,'2018-10-20'),(8,1,'Horchata',10,3,'2018-10-20'),(9,1,'Lasagna',60,5,'2018-10-20'),(10,1,'Pizza',100,7,'2018-10-20'),(11,1,'Pollo',50,1,'2018-10-20'),(12,2,'Carne',70,1,'2018-10-20'),(13,2,'Coca cola',10,1,'2018-10-20'),(14,2,'Horchata',10,1,'2018-10-20'),(15,2,'Lasagna',60,1,'2018-10-20'),(16,2,'Pizza',100,1,'2018-10-20'),(17,2,'Pollo',50,1,'2018-10-20'),(18,2,'Carne',70,2,'2018-10-20'),(19,2,'Coca cola',10,2,'2018-10-20'),(20,2,'Horchata',10,2,'2018-10-20'),(21,2,'Lasagna',60,2,'2018-10-20'),(22,2,'Pizza',100,2,'2018-10-20'),(23,2,'Pollo',50,2,'2018-10-20'),(24,4,'Carne',70,1,'2018-10-20'),(25,4,'Coca cola',10,1,'2018-10-20'),(26,4,'Horchata',10,1,'2018-10-20'),(27,4,'Lasagna',60,1,'2018-10-20'),(28,4,'Pizza',100,1,'2018-10-20'),(29,4,'Pollo',50,1,'2018-10-20'),(30,4,'Carne',70,2,'2018-10-20'),(31,4,'Coca cola',10,2,'2018-10-20'),(32,4,'Horchata',10,2,'2018-10-20'),(33,4,'Lasagna',60,2,'2018-10-20'),(34,4,'Pizza',100,2,'2018-10-20'),(35,4,'Pollo',50,2,'2018-10-20'),(36,5,'Horchata',10,1,'2018-10-20'),(37,5,'Coca cola',10,1,'2018-10-20'),(38,5,'Carne',70,1,'2018-10-20'),(39,5,'Lasagna',60,1,'2018-10-20'),(40,5,'Pizza',100,1,'2018-10-20'),(41,5,'Pollo',50,1,'2018-10-20'),(42,5,'Carne',70,4,'2018-10-20'),(43,5,'Coca cola',10,4,'2018-10-20'),(44,5,'Horchata',10,4,'2018-10-20'),(45,5,'Lasagna',60,4,'2018-10-20'),(46,5,'Pizza',100,4,'2018-10-20'),(47,5,'Pollo',50,4,'2018-10-20'),(48,6,'Carne',70,5,'2018-10-20'),(49,6,'Coca cola',10,5,'2018-10-20'),(50,6,'Horchata',10,5,'2018-10-20'),(51,6,'Lasagna',60,5,'2018-10-20'),(52,6,'Pizza',100,5,'2018-10-20'),(53,6,'Pollo',50,5,'2018-10-20'),(54,6,'Carne',70,3,'2018-10-20'),(55,6,'Coca cola',10,3,'2018-10-20'),(56,6,'Horchata',10,3,'2018-10-20'),(57,6,'Lasagna',60,3,'2018-10-20'),(58,6,'Pizza',100,3,'2018-10-20'),(59,6,'Pollo',50,3,'2018-10-20'),(60,7,'Carne',70,2,'2018-10-20'),(61,7,'Carne',70,2,'2018-10-20'),(62,7,'Coca cola',10,3,'2018-10-20'),(63,7,'Horchata',10,3,'2018-10-20'),(64,7,'Lasagna',60,3,'2018-10-20'),(65,7,'Pizza',100,9,'2018-10-20'),(66,7,'Pollo',50,9,'2018-10-20'),(67,7,'Lasagna',60,9,'2018-10-20'),(68,7,'Horchata',10,9,'2018-10-20'),(69,7,'Coca cola',10,9,'2018-10-20'),(70,8,'Carne',70,1,'2018-10-20'),(71,8,'Horchata',10,1,'2018-10-20'),(72,8,'Coca cola',10,1,'2018-10-20'),(73,8,'Pizza',100,1,'2018-10-20'),(74,8,'Pizza',100,2,'2018-10-20'),(75,8,'Horchata',10,2,'2018-10-20'),(76,8,'Coca cola',10,2,'2018-10-20'),(77,9,'Coca cola',10,4,'2018-10-20'),(78,9,'Lasagna',60,4,'2018-10-20'),(79,9,'Pizza',100,4,'2018-10-20'),(80,10,'Lasagna',60,2,'2018-10-20'),(81,10,'Pizza',100,2,'2018-10-20'),(82,11,'Horchata',10,2,'2018-10-20'),(83,11,'Coca cola',10,2,'2018-10-20'),(84,11,'Pizza',100,2,'2018-10-20'),(85,11,'Carne',70,2,'2018-10-20'),(86,11,'Pollo',50,2,'2018-10-20'),(87,11,'Horchata',10,2,'2018-10-20'),(88,12,'Horchata',10,6,'2018-10-20'),(89,12,'Lasagna',60,6,'2018-10-20'),(90,12,'Pizza',100,6,'2018-10-20'),(91,13,'Coca cola',10,6,'2018-10-20'),(92,13,'Pollo',50,6,'2018-10-20'),(93,13,'Horchata',10,2,'2018-10-20'),(94,13,'Lasagna',60,2,'2018-10-20'),(95,13,'Pollo',50,2,'2018-10-20'),(96,14,'Lasagna',60,1,'2018-10-20'),(97,14,'Pizza',100,1,'2018-10-20'),(98,14,'Horchata',10,1,'2018-10-20'),(99,14,'Coca cola',10,1,'2018-10-20'),(100,15,'Coca cola',10,2,'2018-10-20'),(101,15,'Horchata',10,2,'2018-10-20'),(102,15,'Pollo',50,2,'2018-10-20'),(103,15,'Pizza',100,2,'2018-10-20'),(104,16,'Pollo',50,1,'2018-10-20');
/*!40000 ALTER TABLE `CONSUMO_RESTAURANTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HABITACION`
--

DROP TABLE IF EXISTS `HABITACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HABITACION` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo_Habitacion` varchar(30) NOT NULL,
  `Nivel` int(11) NOT NULL,
  `Precio` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HABITACION`
--

LOCK TABLES `HABITACION` WRITE;
/*!40000 ALTER TABLE `HABITACION` DISABLE KEYS */;
INSERT INTO `HABITACION` VALUES (1,'Habitacion Junior',1,200),(2,'Habitacion Junior',1,200),(3,'Habitacion Master',1,300),(4,'Habitacion De Lujo',1,400),(5,'Habitacion Junior',2,200),(6,'Habitacion Junior',2,200),(7,'Habitacion Master',2,300),(8,'Habitacion De Lujo',2,400),(9,'Habitacion Junior',3,200),(10,'Habitacion Junior',3,200),(11,'Habitacion Master',3,300),(12,'Habitacion De Lujo',3,400),(13,'Habitacion Junior',4,200),(14,'Habitacion Junior',4,200),(15,'Habitacion Master',4,300),(16,'Habitacion De Lujo',4,400);
/*!40000 ALTER TABLE `HABITACION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MENU`
--

DROP TABLE IF EXISTS `MENU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MENU` (
  `Nombre_Comida` varchar(30) NOT NULL,
  `Tipo_Comida` varchar(30) NOT NULL,
  `Precio` int(11) NOT NULL,
  PRIMARY KEY (`Nombre_Comida`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MENU`
--

LOCK TABLES `MENU` WRITE;
/*!40000 ALTER TABLE `MENU` DISABLE KEYS */;
INSERT INTO `MENU` VALUES ('Carne','COMIDA',70),('Coca cola','BEBIDA',10),('Horchata','BEBIDA',10),('Lasagna','COMIDA',60),('Pizza','COMIDA',100),('Pollo','COMIDA',50);
/*!40000 ALTER TABLE `MENU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESERVACION`
--

DROP TABLE IF EXISTS `RESERVACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESERVACION` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Dpi_Cliente` varchar(15) NOT NULL,
  `Id_Habitacion` int(11) NOT NULL,
  `Fecha_Entrada` date NOT NULL,
  `Fecha_Salida` date NOT NULL,
  `Precio` int(11) NOT NULL,
  `Check_In` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_CLIENTE_IN_DPI_CLIENTE` (`Dpi_Cliente`),
  KEY `FK_HABITACION_IN_ID_HABITACION` (`Id_Habitacion`),
  CONSTRAINT `FK_CLIENTE_IN_DPI_CLIENTE` FOREIGN KEY (`Dpi_Cliente`) REFERENCES `CLIENTE` (`Dpi`),
  CONSTRAINT `FK_HABITACION_IN_ID_HABITACION` FOREIGN KEY (`Id_Habitacion`) REFERENCES `HABITACION` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESERVACION`
--

LOCK TABLES `RESERVACION` WRITE;
/*!40000 ALTER TABLE `RESERVACION` DISABLE KEYS */;
INSERT INTO `RESERVACION` VALUES (1,'cliente1',1,'2018-10-20','2018-10-23',200,1),(2,'cliente2',2,'2018-10-20','2018-10-22',200,1),(3,'cliente3',3,'2018-10-20','2018-10-24',300,1),(4,'cliente4',4,'2018-10-20','2018-10-25',400,1),(5,'cliente5',5,'2018-10-20','2018-10-21',200,1),(6,'cliente6',6,'2018-10-20','2018-10-21',200,1),(7,'cliente7',7,'2018-10-20','2018-10-21',300,1),(8,'cliente8',8,'2018-10-20','2018-10-22',400,1),(9,'cliente9',9,'2018-10-20','2018-10-22',200,1),(10,'cliente10',10,'2018-10-20','2018-10-22',200,1),(11,'cliente1',11,'2018-10-20','2018-10-23',300,1),(12,'cliente2',12,'2018-10-20','2018-10-23',400,1),(13,'cliente3',13,'2018-10-20','2018-10-24',200,1),(14,'cliente4',14,'2018-10-20','2018-10-24',200,1),(15,'cliente5',15,'2018-10-20','2018-10-24',300,1),(16,'cliente6',16,'2018-10-20','2018-10-26',400,1);
/*!40000 ALTER TABLE `RESERVACION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `Usuario` varchar(30) NOT NULL,
  `Contraseña` varchar(30) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Tipo_Usuario` varchar(30) NOT NULL,
  PRIMARY KEY (`Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES ('admin','1','admin','admin','GERENTE'),('e1','3','3','3','EMPLEADO RESTAURANTE'),('e2','3','3','3','EMPLEADO RESTAURANTE'),('e4','3','3','3','EMPLEADO RESTAURANTE'),('e5','3','3','3','EMPLEADO RESTAURANTE'),('g1','1','1','1','GERENTE'),('g2','1','1','1','GERENTE'),('g3','1','1','1','GERENTE'),('g4','1','1','1','GERENTE'),('g5','1','1','1','GERENTE'),('r1','2','2','2','RECEPCIONISTA'),('r2','2','2','2','RECEPCIONISTA'),('r3','2','2','2','RECEPCIONISTA'),('r4','2','2','2','RECEPCIONISTA'),('r5','2','2','2','RECEPCIONISTA');
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-21 11:50:39
