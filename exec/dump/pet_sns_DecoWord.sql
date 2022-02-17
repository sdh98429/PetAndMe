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
-- Table structure for table `DecoWord`
--

DROP TABLE IF EXISTS `DecoWord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DecoWord` (
  `word` varchar(255) NOT NULL,
  PRIMARY KEY (`word`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DecoWord`
--

LOCK TABLES `DecoWord` WRITE;
/*!40000 ALTER TABLE `DecoWord` DISABLE KEYS */;
INSERT INTO `DecoWord` VALUES ('감각적인'),('개성적인'),('건강한'),('겸손한'),('곱슬곱슬한'),('공부하는'),('공정한'),('관대한'),('귀여운'),('그윽한'),('근면한'),('기다란'),('기쁜'),('기운찬'),('깔끔한'),('꼼꼼한'),('꿈꾸는'),('낭만적인'),('너그러운'),('노래하는'),('놀기 좋아하는'),('놀라운'),('놀란'),('눈부신'),('느긋한'),('능력있는'),('다정한'),('단호한'),('당황한'),('도전적인'),('독서하는'),('뛰어다니는'),('매력적인'),('멋있는'),('모험심이 강한'),('발랄한'),('밝은'),('밥먹는'),('배려하는'),('부드러운'),('빛나는'),('사랑스러운'),('사려깊은'),('사색적인'),('소중한'),('솔직한'),('순수한'),('쉬고있는'),('아기'),('아름다운'),('알고있는'),('야망있는'),('여유를 즐기는'),('열공하는'),('열렬한'),('열정적인'),('영리한'),('예의바른'),('완벽한'),('요리하는'),('용감한'),('우아한'),('운동하는'),('웃고있는'),('웃기는'),('유능한'),('융통성있는'),('인간적인'),('인내심있는'),('인자한'),('자고있는'),('자신감있는'),('잘생긴'),('잠자는'),('장난기 많은'),('재미있는'),('적극적인'),('젊은'),('정직한'),('정확한'),('조그만'),('즉흥적인'),('지혜로운'),('창의적인'),('창조적인'),('춤추는'),('취준생'),('치킨이 좋은'),('침착한'),('커다란'),('커피마시는'),('태평한'),('통찰력있는'),('패셔니스타'),('편안한'),('행복한'),('헌신적인'),('활동적인');
/*!40000 ALTER TABLE `DecoWord` ENABLE KEYS */;
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
