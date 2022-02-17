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
-- Table structure for table `Pet`
--

DROP TABLE IF EXISTS `Pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pet` (
  `petNumber` bigint NOT NULL AUTO_INCREMENT,
  `userNumber` bigint NOT NULL,
  `animalNumber` int NOT NULL,
  `petName` varchar(20) NOT NULL,
  `petGender` varchar(1) NOT NULL,
  `petBirth` varchar(20) NOT NULL,
  PRIMARY KEY (`petNumber`),
  UNIQUE KEY `userNumber` (`userNumber`,`petName`),
  KEY `fk_Pet_Animal` (`animalNumber`),
  CONSTRAINT `fk_Pet_Animal` FOREIGN KEY (`animalNumber`) REFERENCES `Animal` (`animalNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pet_User` FOREIGN KEY (`userNumber`) REFERENCES `User` (`userNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pet`
--

LOCK TABLES `Pet` WRITE;
/*!40000 ALTER TABLE `Pet` DISABLE KEYS */;
INSERT INTO `Pet` VALUES (1,1,2,'동글이','M','2020-06-27'),(2,2,2,'모찌','M','2016-12-06'),(3,3,1,'해피','F','2022-01-22'),(4,4,1,'둥이','F','2018-01-23'),(5,5,1,'구름','M','2019-08-22'),(6,6,6,'Neysa','F','2015-12-11'),(7,7,7,'Yetty','F','2020-12-13'),(8,8,8,'Nickie','F','2017-11-07'),(9,9,1,'콩이','M','2016-08-28'),(10,10,10,'Debby','F','2013-07-22'),(11,11,11,'Barby','F','2015-12-13'),(12,12,9,'Bob','M','2019-06-12'),(13,13,7,'Shayne','M','2020-04-01'),(14,14,8,'Hanan','M','2017-06-24'),(15,15,2,'Gussi','F','2013-04-29'),(16,16,2,'Robbin','F','2020-12-25'),(17,17,1,'Rollins','M','2020-08-01'),(18,18,1,'Hetty','F','2018-07-27'),(19,19,5,'Omar','M','2019-05-06'),(20,20,1,'Calhoun','M','2020-02-16'),(21,21,2,'Valentijn','M','2019-03-10'),(22,23,8,'Friedrich','M','2015-09-25'),(23,25,7,'Nelia','F','2017-07-02'),(24,27,6,'Sibelle','F','2016-04-26'),(25,28,2,'L, urette','F','2016-10-23'),(26,29,7,'Gustave','M','2014-05-27'),(27,32,1,'Cayla','F','2020-04-03'),(28,33,2,'Juana','F','2019-11-13'),(29,34,1,'Kristoforo','M','2018-07-17'),(30,35,2,'Felecia','F','2021-09-09'),(31,36,3,'Lars','M','2015-09-27'),(32,37,4,'Auria','F','2015-09-03'),(33,38,5,'Laurianne','F','2015-05-21'),(34,39,6,'Caron','F','2013-01-09'),(35,40,7,'Rosanna','F','2018-08-13'),(36,41,8,'Tova','F','2017-05-12'),(37,42,9,'Gothart','M','2019-12-25'),(38,43,10,'Patsy','M','2016-10-27'),(39,44,11,'Mirabelle','F','2015-06-08'),(40,45,1,'Darda','F','2022-01-30'),(41,46,1,'Gale','F','2021-05-12'),(42,47,2,'Tommy','M','2013-09-08'),(43,48,2,'Elisabeth','F','2013-07-04'),(44,49,2,'Sibyl','M','2015-01-25'),(45,50,1,'Elmo','M','2015-05-01'),(46,51,1,'쿠키','F','2007-01-01'),(47,52,2,'누리','F','2011-01-01'),(48,53,3,'치즈','M','2012-01-01'),(49,54,4,'구피','M','2020-01-01'),(50,55,5,'하양','F','2017-01-01'),(51,56,6,'개굴','M','2017-01-01'),(52,57,7,'쥐돌이','M','2017-01-01'),(53,58,8,'선아','M','2019-01-01'),(54,59,9,'막내','F','2019-01-01'),(55,60,10,'스르륵','M','2018-01-01'),(56,61,11,'꼬부기','M','2018-01-01'),(57,62,1,'뽀뽀','M','2022-02-01'),(58,63,1,'아롱이','M','2021-03-17'),(59,65,3,'두두','M','2021-01-09'),(60,67,1,'뽀뽀','M','2018-11-11'),(61,68,1,'쿠키','M','2021-04-01');
/*!40000 ALTER TABLE `Pet` ENABLE KEYS */;
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
