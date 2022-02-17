-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: i6b106.p.ssafy.io    Database: pet_sns
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Follow`
--

DROP TABLE IF EXISTS `Follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Follow` (
  `fromUserNumber` bigint NOT NULL,
  `toUserNumber` bigint NOT NULL,
  PRIMARY KEY (`fromUserNumber`,`toUserNumber`),
  KEY `fk_Follow_fromUser_idx` (`fromUserNumber`),
  KEY `fk_Follow_toUser_idx` (`toUserNumber`),
  CONSTRAINT `fk_Follow_fromUser` FOREIGN KEY (`fromUserNumber`) REFERENCES `User` (`userNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Follow_toUser` FOREIGN KEY (`toUserNumber`) REFERENCES `User` (`userNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Follow`
--

LOCK TABLES `Follow` WRITE;
/*!40000 ALTER TABLE `Follow` DISABLE KEYS */;
INSERT INTO `Follow` VALUES (1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,14),(1,15),(1,51),(1,63),(2,1),(2,3),(2,51),(2,63),(3,1),(3,2),(3,4),(3,51),(3,63),(4,1),(4,2),(4,51),(4,63),(5,1),(5,2),(5,4),(5,51),(5,63),(6,1),(6,2),(6,4),(6,51),(6,63),(7,1),(7,2),(7,4),(7,51),(7,63),(8,1),(8,2),(8,4),(8,51),(8,63),(9,1),(9,2),(9,4),(9,51),(9,63),(10,1),(10,2),(10,4),(10,51),(10,63),(11,1),(11,2),(11,4),(11,51),(11,63),(12,1),(12,2),(12,4),(12,51),(12,63),(13,1),(13,2),(13,4),(13,51),(13,63),(14,1),(14,2),(14,4),(14,51),(14,63),(15,1),(15,2),(15,4),(15,51),(15,63),(16,1),(16,2),(16,4),(16,51),(16,63),(17,1),(17,2),(17,4),(17,51),(17,63),(18,1),(18,2),(18,4),(18,51),(18,63),(19,1),(19,2),(19,4),(19,51),(19,63),(20,1),(20,2),(20,4),(20,51),(20,63),(21,1),(21,2),(21,5),(21,51),(21,63),(22,1),(22,2),(22,5),(22,51),(22,63),(23,1),(23,2),(23,5),(23,51),(23,63),(24,1),(24,2),(24,5),(24,51),(24,63),(25,1),(25,2),(25,5),(25,51),(25,63),(26,1),(26,2),(26,5),(26,51),(26,63),(27,1),(27,3),(27,5),(27,51),(27,63),(28,1),(28,3),(28,5),(28,51),(28,63),(29,1),(29,3),(29,5),(29,51),(29,63),(30,1),(30,3),(30,5),(30,51),(30,63),(31,1),(31,3),(31,5),(31,51),(31,63),(32,1),(32,3),(32,5),(32,51),(32,63),(33,1),(33,3),(33,5),(33,51),(33,63),(34,1),(34,3),(34,5),(34,51),(34,63),(35,1),(35,3),(35,5),(35,51),(35,63),(36,1),(36,3),(36,5),(36,51),(36,63),(37,1),(37,3),(37,5),(37,51),(37,63),(38,1),(38,3),(38,5),(38,51),(38,63),(39,1),(39,3),(39,5),(39,51),(39,63),(40,1),(40,3),(40,5),(40,51),(40,63),(41,1),(41,3),(41,5),(41,51),(41,63),(42,1),(42,3),(42,5),(42,51),(42,63),(43,1),(43,3),(43,5),(43,51),(43,63),(44,1),(44,3),(44,5),(44,51),(44,63),(45,1),(45,3),(45,5),(45,51),(45,63),(46,1),(46,3),(46,5),(46,51),(46,63),(47,1),(47,3),(47,5),(47,51),(47,63),(48,1),(48,3),(48,5),(48,63),(49,1),(49,3),(49,5),(49,63),(50,1),(50,3),(50,63),(51,1),(51,2),(51,3),(51,4),(51,5),(51,9),(51,12),(51,15),(51,16),(51,17),(51,18),(51,20),(51,21),(51,28),(51,32),(51,33),(51,34),(51,35),(51,42),(51,45),(51,46),(51,47),(51,48),(51,49),(51,50),(51,52),(51,59),(51,63),(52,63),(53,63),(54,63),(55,63),(56,63),(57,63),(58,63),(59,63),(60,63),(61,63),(62,63),(63,1),(63,51);
/*!40000 ALTER TABLE `Follow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  5:16:45
