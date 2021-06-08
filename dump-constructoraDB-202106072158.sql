-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: localhost    Database: constructoraDB
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `arquitectos`
--

DROP TABLE IF EXISTS `arquitectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arquitectos` (
  `id_arquitecto` bigint NOT NULL AUTO_INCREMENT,
  `cedula` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  PRIMARY KEY (`id_arquitecto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arquitectos`
--

LOCK TABLES `arquitectos` WRITE;
/*!40000 ALTER TABLE `arquitectos` DISABLE KEYS */;
INSERT INTO `arquitectos` VALUES (1,'1143940232','ALEJANDRO','JARAMILLO');
/*!40000 ALTER TABLE `arquitectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales`
--

DROP TABLE IF EXISTS `materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales` (
  `id_material` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `nomenclatura` varchar(4) NOT NULL,
  PRIMARY KEY (`id_material`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales`
--

LOCK TABLES `materiales` WRITE;
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` VALUES (1,'CEMENTO','CE'),(2,'GRAVA','GR'),(3,'ARENA','AR'),(4,'MADERA','MA'),(5,'ADOBE','AD');
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales_tipo_construcciones`
--

DROP TABLE IF EXISTS `materiales_tipo_construcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales_tipo_construcciones` (
  `id_material` bigint NOT NULL,
  `id_tipo_construccion` bigint NOT NULL,
  `cantidad` int NOT NULL,
  `id_solicitud` bigint NOT NULL,
  PRIMARY KEY (`id_tipo_construccion`,`id_material`,`id_solicitud`),
  KEY `materiales_tipo_construcciones_FK` (`id_material`),
  KEY `materiales_tipo_construcciones_FK_2` (`id_solicitud`),
  CONSTRAINT `materiales_tipo_construcciones_FK` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`),
  CONSTRAINT `materiales_tipo_construcciones_FK_1` FOREIGN KEY (`id_tipo_construccion`) REFERENCES `tipos_contrucciones` (`id_tipo_construccion`),
  CONSTRAINT `materiales_tipo_construcciones_FK_2` FOREIGN KEY (`id_solicitud`) REFERENCES `solicitudes_construcciones` (`id_solicitud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales_tipo_construcciones`
--

LOCK TABLES `materiales_tipo_construcciones` WRITE;
/*!40000 ALTER TABLE `materiales_tipo_construcciones` DISABLE KEYS */;
INSERT INTO `materiales_tipo_construcciones` VALUES (1,1,200,13),(1,1,100,14),(2,1,150,13),(2,1,50,14),(3,1,60,13),(3,1,90,14),(4,1,30,13),(4,1,20,14),(5,1,20,13),(5,1,100,14),(1,2,50,15),(1,2,50,16),(2,2,60,15),(2,2,60,16),(3,2,80,15),(3,2,80,16),(4,2,10,15),(4,2,10,16),(5,2,20,15),(5,2,20,16),(1,3,20,17),(2,3,20,17),(3,3,20,17),(4,3,20,17),(5,3,20,17),(1,5,50,18),(1,5,50,19),(2,5,25,18),(2,5,25,19),(3,5,45,18),(3,5,45,19),(4,5,10,18),(4,5,10,19),(5,5,50,18),(5,5,50,19);
/*!40000 ALTER TABLE `materiales_tipo_construcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenes_construcciones`
--

DROP TABLE IF EXISTS `ordenes_construcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenes_construcciones` (
  `id_orden` bigint NOT NULL AUTO_INCREMENT,
  `id_solicitud` bigint NOT NULL,
  `estado` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id_orden`),
  KEY `ordenes_construcciones_FK` (`id_solicitud`),
  CONSTRAINT `ordenes_construcciones_FK` FOREIGN KEY (`id_solicitud`) REFERENCES `solicitudes_construcciones` (`id_solicitud`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes_construcciones`
--

LOCK TABLES `ordenes_construcciones` WRITE;
/*!40000 ALTER TABLE `ordenes_construcciones` DISABLE KEYS */;
INSERT INTO `ordenes_construcciones` VALUES (1,13,'PROGRESO'),(2,14,'PENDIENTE'),(3,15,'PENDIENTE'),(4,16,'PENDIENTE'),(5,17,'PENDIENTE'),(6,18,'PENDIENTE');
/*!40000 ALTER TABLE `ordenes_construcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parametros` (
  `id_parametro` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_parametro`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros`
--

LOCK TABLES `parametros` WRITE;
/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
INSERT INTO `parametros` VALUES (1,'CASA.CE','100'),(2,'CASA.GR','50'),(3,'CASA.AR','90'),(4,'CASA.MA','20'),(5,'CASA.AD','100'),(6,'LAGO.CE','50'),(7,'LAGO.GR','60'),(8,'LAGO.AR','80'),(9,'LAGO.MA','10'),(10,'LAGO.AD','20'),(11,'CANCHA.CE','20'),(12,'CANCHA.GR','20'),(13,'CANCHA.AR','20'),(14,'CANCHA.MA','20'),(15,'CANCHA.AD','20'),(16,'EDIFICIO.CE','200'),(17,'EDIFICIO.GR','100'),(18,'EDIFICIO.AR','180'),(19,'EDIFICIO.MA','40'),(20,'EDIFICIO.AD','200'),(21,'GIMNASIO.CE','50'),(22,'GIMNASIO.GR','25'),(23,'GIMNASIO.AR','45'),(24,'GIMNASIO.MA','10'),(25,'GIMNASIO.AD','50');
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos` (
  `id_proyecto` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `fecha_final` datetime DEFAULT NULL,
  PRIMARY KEY (`id_proyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos`
--

LOCK TABLES `proyectos` WRITE;
/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
INSERT INTO `proyectos` VALUES (1,'Ciudadela del Rio','2021-06-29 05:00:00');
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `reporte_proceso_contruccion`
--

DROP TABLE IF EXISTS `reporte_proceso_contruccion`;
/*!50001 DROP VIEW IF EXISTS `reporte_proceso_contruccion`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `reporte_proceso_contruccion` AS SELECT 
 1 AS `numero_solicitud`,
 1 AS `construccion`,
 1 AS `fecha_inicio`,
 1 AS `fecha_fin`,
 1 AS `estado`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `solicitudes_construcciones`
--

DROP TABLE IF EXISTS `solicitudes_construcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudes_construcciones` (
  `id_solicitud` bigint NOT NULL AUTO_INCREMENT,
  `coordenada_x` int NOT NULL,
  `coordenada_y` int NOT NULL,
  `id_arquitecto` bigint NOT NULL,
  `fecha_creacion` date NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_solicitud`),
  KEY `solicitudes_construcciones_FK` (`id_arquitecto`),
  CONSTRAINT `solicitudes_construcciones_FK` FOREIGN KEY (`id_arquitecto`) REFERENCES `arquitectos` (`id_arquitecto`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudes_construcciones`
--

LOCK TABLES `solicitudes_construcciones` WRITE;
/*!40000 ALTER TABLE `solicitudes_construcciones` DISABLE KEYS */;
INSERT INTO `solicitudes_construcciones` VALUES (13,22,34,1,'2021-06-07','2021-06-07','2021-06-11'),(14,0,10,1,'2021-06-07','2021-06-11','2021-06-15'),(15,22,10,1,'2021-06-07','2021-06-15','2021-06-18'),(16,32,45,1,'2021-06-07','2021-06-18','2021-06-21'),(17,3,6,1,'2021-06-07','2021-06-21','2021-06-23'),(18,6,10,1,'2021-06-07','2021-06-23','2021-06-26'),(19,60,2,1,'2021-06-08','2021-06-27','2021-06-29');
/*!40000 ALTER TABLE `solicitudes_construcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_contrucciones`
--

DROP TABLE IF EXISTS `tipos_contrucciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_contrucciones` (
  `id_tipo_construccion` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tiempo_terminado` int NOT NULL,
  PRIMARY KEY (`id_tipo_construccion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_contrucciones`
--

LOCK TABLES `tipos_contrucciones` WRITE;
/*!40000 ALTER TABLE `tipos_contrucciones` DISABLE KEYS */;
INSERT INTO `tipos_contrucciones` VALUES (1,'CASA',3),(2,'LAGO',2),(3,'CANCHA',1),(4,'EDIFICIO',6),(5,'GIMNASIO',2);
/*!40000 ALTER TABLE `tipos_contrucciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'constructoraDB'
--

--
-- Final view structure for view `reporte_proceso_contruccion`
--

/*!50001 DROP VIEW IF EXISTS `reporte_proceso_contruccion`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `reporte_proceso_contruccion` AS select `sc`.`id_solicitud` AS `numero_solicitud`,`tc`.`nombre` AS `construccion`,`sc`.`fecha_inicio` AS `fecha_inicio`,`sc`.`fecha_fin` AS `fecha_fin`,`oc`.`estado` AS `estado` from (((`ordenes_construcciones` `oc` join `solicitudes_construcciones` `sc` on((`sc`.`id_solicitud` = `oc`.`id_solicitud`))) join `materiales_tipo_construcciones` `mtc` on((`mtc`.`id_solicitud` = `sc`.`id_solicitud`))) join `tipos_contrucciones` `tc` on((`tc`.`id_tipo_construccion` = `mtc`.`id_tipo_construccion`))) group by `sc`.`id_solicitud`,`tc`.`nombre`,`sc`.`fecha_inicio`,`sc`.`fecha_fin`,`oc`.`estado` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-07 21:58:59
