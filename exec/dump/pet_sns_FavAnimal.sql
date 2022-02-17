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
-- Table structure for table `FavAnimal`
--

DROP TABLE IF EXISTS `FavAnimal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FavAnimal` (
  `userNumber` bigint NOT NULL,
  `animalNumber` int NOT NULL,
  KEY `fk_FavAnimal_Animal_idx` (`animalNumber`),
  KEY `fk_FavAnimal_User_idx` (`userNumber`),
  CONSTRAINT `fk_FavAnimal_Animal` FOREIGN KEY (`animalNumber`) REFERENCES `Animal` (`animalNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_FavAnimal_User` FOREIGN KEY (`userNumber`) REFERENCES `User` (`userNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FavAnimal`
--

LOCK TABLES `FavAnimal` WRITE;
/*!40000 ALTER TABLE `FavAnimal` DISABLE KEYS */;
INSERT INTO `FavAnimal` VALUES (8,1),(15,1),(22,1),(29,1),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(8,3),(15,3),(23,3),(29,3),(2,4),(9,4),(16,4),(23,4),(30,4),(2,5),(9,5),(16,5),(23,5),(30,5),(30,2),(31,1),(32,2),(33,2),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,2),(43,3),(44,4),(45,5),(46,6),(47,7),(48,8),(49,9),(50,10),(53,2),(53,3),(53,7),(54,1),(54,4),(54,5),(55,1),(55,5),(55,6),(56,1),(56,2),(56,3),(57,7),(57,8),(57,9),(58,7),(58,8),(58,9),(59,7),(59,8),(59,11),(60,6),(60,8),(60,11),(61,6),(61,8),(61,11),(62,1),(62,2),(62,3),(52,2),(52,8),(52,9),(1,1),(1,2),(1,3),(63,1),(63,2),(63,8),(63,9),(64,3),(64,2),(64,8),(64,9),(65,4),(65,3),(65,9),(65,10),(66,2),(66,6),(66,10),(66,11),(67,1),(67,2),(67,3),(68,1),(68,2),(68,9),(51,1),(51,2),(51,9);
/*!40000 ALTER TABLE `FavAnimal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  5:16:43
