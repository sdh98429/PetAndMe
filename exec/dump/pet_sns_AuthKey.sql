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
-- Table structure for table `AuthKey`
--

DROP TABLE IF EXISTS `AuthKey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AuthKey` (
  `userEmail` varchar(45) NOT NULL,
  `AuthKey` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AuthKey`
--

LOCK TABLES `AuthKey` WRITE;
/*!40000 ALTER TABLE `AuthKey` DISABLE KEYS */;
INSERT INTO `AuthKey` VALUES ('agoadew@techcrunch.com','authorization'),('agurkoa@skype.com','authorization'),('alabono@youtube.com','authorization'),('amacmaykinc@samsung.com','authorization'),('amateur@kakao.com','authorization'),('aprudenceq@businesswire.com','authorization'),('arong12@naver.com','authorization'),('asdasdasd@naver.com','zGfv0PgH'),('asdfasdf@asdf.com','94hS2C1a'),('asdfasdfasdf@naver.com','075V9s30'),('asdfopajfpsjop@nvaver.com','kOM7pL1H'),('asdopjasdopjafsopj@asdklf.com','nq0EXBrE'),('asobtkal@hugedomains.com','authorization'),('asooso1@naver.com','authorization'),('asooso1212@gmail.com','authorization'),('bhalliburtonr@friendfeed.com','authorization'),('brawstornev@tinyurl.com','authorization'),('bteape2@sun.com','authorization'),('btickelp@hud.gov','authorization'),('chopfer10@businessweek.com','authorization'),('cwhorallz@instagram.com','authorization'),('dcapponerk@sogou.com','authorization'),('dcusteds@samsung.com','authorization'),('dgeikied@skype.com','authorization'),('dong@naver.com','authorization'),('dposert@bravesites.com','authorization'),('ebuse12@google.cn','authorization'),('edeleonu@ning.com','authorization'),('fbarnetj@nba.com','authorization'),('flonie0@twitpic.com','authorization'),('fparis5@walmart.com','authorization'),('friend_cat@naver.com','authorization'),('friend_dog@naver.com','authorization'),('friend_duck@naver.com','authorization'),('friend_fish@naver.com','authorization'),('friend_fox@naver.com','authorization'),('friend_frog@naver.com','authorization'),('friend_mouse@naver.com','authorization'),('friend_pig@naver.com','authorization'),('friend_rabbit@naver.com','authorization'),('friend_snake@naver.com','authorization'),('friend_turtle@naver.com','authorization'),('gflintoffh@php.net','authorization'),('gildong@naver.com','authorization'),('glabusquiere1@ucla.edu','authorization'),('gprivett8@surveymonkey.com','authorization'),('grighyy@alexa.com','authorization'),('jb9992@daum.net','authorization'),('jblamphinf@phoca.cz','authorization'),('jinsukdl@naver.com','authorization'),('kdegnenb@spiegel.de','authorization'),('keppsonn@dropbox.com','authorization'),('kheddane@sogou.com','authorization'),('killBW@naver.com','authorization'),('kimjy970602@daum.net','authorization'),('knl_1219@hanmail.net','authorization'),('kolliver11@dot.gov','authorization'),('mdulakeg@mtv.com','authorization'),('nbubeer13@amazon.com','authorization'),('nmaidenm@bloglines.com','authorization'),('ooo@naver.com','authorization'),('prekes7@dailymotion.com','authorization'),('rgummiex@rambler.ru','authorization'),('sejong@naver.com','authorization'),('sklaessen9@ow.ly','authorization'),('ssrcus27@naver.com','authorization'),('ssrcus2u@naver.com','authorization'),('test@naver.com','authorization'),('test222@naver.com','Ajuv7QaV'),('thathorn3@house.gov','authorization'),('thekey1017@gmail.com','authorization'),('tjurisch6@cbsnews.com','authorization'),('wjohanssen4@cpanel.net','authorization'),('wmyrtlei@seattletimes.com','authorization');
/*!40000 ALTER TABLE `AuthKey` ENABLE KEYS */;
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
