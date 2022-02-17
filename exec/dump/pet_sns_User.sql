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
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `userNumber` bigint NOT NULL AUTO_INCREMENT,
  `userID` varchar(50) NOT NULL,
  `userPW` varchar(100) NOT NULL,
  `userNickName` varchar(50) DEFAULT NULL,
  `userEmail` varchar(45) NOT NULL,
  `petCheck` tinyint NOT NULL,
  `saveFolder` varchar(255) DEFAULT '/home/petNme/profile/',
  `userPhotoName` varchar(255) DEFAULT 'user_default.jpg',
  `userProfileContent` varchar(255) DEFAULT NULL,
  `userType` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`userNumber`),
  UNIQUE KEY `userID_UNIQUE` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'kimjy199','wjddbs','놀라운 여우','kimjy970602@daum.net',1,'/home/test/profile/','8ca0eadd-2ab9-465d-a2b8-051b873e6f66jaeongyoun.jpeg','안녕하세요 반갑습니다',1),(2,'test','1234','근면한 강아지','test@naver.com',1,'/home/test/profile/','bad2ba9c-fdba-46d7-af18-6fd169958a92mozzi.png','Lorem Ipsum is simply dummy text of the printing and typesetting industry.',1),(3,'gildong','gildong!','Sword Master','gildong@naver.com',1,'/home/test/profile/','959e1d89-0c68-42b6-b356-9a9685197b9csibadog.png','hi my name is gildong',1),(4,'bangwon','13670613','매력적인 거북이','killBW@naver.com',1,'/home/test/profile/','24cc050c-bcdf-4092-83b8-652027505c68sibadog2.png','안녕하세요 반갑습니다',1),(5,'leedo','leedo97','예의바른 거북이','sejong@naver.com',1,'/home/test/profile/','6e53d49f-69e5-47b6-8cfe-d71cde3ad160samo.png','안녕하세요 반갑습니다',1),(6,'juhyeong','leeju1234','태평한 물고기','ssrcus27@naver.com',1,'/home/petNme/profile/','user_default.jpg','안녕하세요 반갑습니다',1),(7,'dongho','dongho1234','치킨이 좋은 고양이','dong@naver.com',1,'/home/petNme/profile/','user_default.jpg','안녕하세요 반갑습니다',1),(8,'amateur','asooso1','관대한 돼지','amateur@kakao.com',1,'/home/petNme/profile/','user_default.jpg','안녕하세요 반갑습니다',1),(9,'jibin','thekey1017','jibin','thekey1017@gmail.com',1,'/home/test/profile/','18d8f72f-a4fc-49ae-bf6c-94967d22e852bbubbu.png','안녕하세요 반갑습니다',1),(10,'narin','narin','뛰어다니는 강아지','knl_1219@hanmail.net',1,'/home/test/profile/','08172bfc-cfc2-45c9-9ca6-3cb1b17c860fdfdfdf.PNG','안녕하세요 반갑습니다',1),(11,'alonie0','plonie0','완벽한 강아지','flonie0@twitpic.com',1,'/home/test/profile/','d69bfd95-584f-4fdc-8e2c-cb096bac0befbird.png',NULL,1),(12,'mlabusquiere1','alabusquiere1','근면한 여우','glabusquiere1@ucla.edu',1,'/home/test/profile/','e1eae822-7d18-4b98-b7d6-0c22ecb42ff4corona.png',NULL,1),(13,'cteape2','cteape2','요리하는 물고기','bteape2@sun.com',1,'/home/test/profile/','979f6300-0d4e-43e5-b7e6-8693f3c44b16gufi.png',NULL,1),(14,'ahathorn3','lhathorn3','장난기 많은 강아지','thathorn3@house.gov',1,'/home/test/profile/','93e9634c-b633-482f-a845-191e5fd41880dog1111.png',NULL,1),(15,'tjohanssen4','bjohanssen4','우아한 거북이','wjohanssen4@cpanel.net',1,'/home/test/profile/','b5f7d99f-62a3-411d-90ea-84387b3ad2cbturtle.png',NULL,1),(16,'dparis5','bparis5','인자한 돼지','fparis5@walmart.com',1,'/home/test/profile/','87946a83-4044-4fb2-8a21-47761c060b09minipig.png',NULL,1),(17,'mjurisch6','bjurisch6','Jurisch','tjurisch6@cbsnews.com',1,'/home/test/profile/','12229d56-7e9f-4e77-8273-4939c86b10dabbung.png',NULL,1),(18,'mrekes7','rrekes7','열공하는 개구리','prekes7@dailymotion.com',1,'/home/test/profile/','9d9d0962-dee2-4a86-84da-820ece7c5801greenFrog.png',NULL,1),(19,'dprivett8','fprivett8','Privett','gprivett8@surveymonkey.com',1,'/home/test/profile/','9ca7750f-b7d7-4475-b9fa-691a8046c08bpreittycat.png',NULL,1),(20,'jklaessen9','aklaessen9','유능한 강아지','sklaessen9@ow.ly',1,'/home/test/profile/','ddae9ca0-212e-49c0-a701-e412117243c9bigeyedog.png',NULL,1),(21,'kgurkoa','vgurkoa','Gurko','agurkoa@skype.com',1,'/home/test/profile/','36d22d9e-21dd-442f-82ac-40f34a2c889fgurok.png',NULL,0),(22,'rdegnenb','sdegnenb','능력있는 거북이','kdegnenb@spiegel.de',0,'/home/petNme/profile/','user_default.jpg',NULL,0),(23,'mmacmaykinc','lmacmaykinc','밥먹는 뱀','amacmaykinc@samsung.com',1,'/home/petNme/profile/','user_default.jpg',NULL,1),(24,'ageikied','fgeikied','Geikie','dgeikied@skype.com',0,'/home/petNme/profile/','user_default.jpg',NULL,1),(25,'mheddane','dheddane','Heddan','kheddane@sogou.com',1,'/home/petNme/profile/','user_default.jpg',NULL,1),(26,'eblamphinf','vblamphinf','Blamphin','jblamphinf@phoca.cz',0,'/home/petNme/profile/','user_default.jpg',NULL,1),(27,'jdulakeg','mdulakeg','뛰어다니는 거북이','mdulakeg@mtv.com',1,'/home/petNme/profile/','user_default.jpg',NULL,1),(28,'bflintoffh','yflintoffh','Flintoff','gflintoffh@php.net',1,'/home/petNme/profile/','user_default.jpg',NULL,1),(29,'mmyrtlei','rmyrtlei','조그만 토끼','wmyrtlei@seattletimes.com',1,'/home/petNme/profile/','user_default.jpg',NULL,1),(30,'abarnetj','dbarnetj','Barnet','fbarnetj@nba.com',0,'/home/petNme/profile/','user_default.jpg',NULL,1),(31,'acapponerk','ccapponerk','사랑스러운 오리','dcapponerk@sogou.com',0,'/home/test/profile/','4482bbed-e73b-4cdc-b02b-6f1c5f9b81e0fox15.jpg',NULL,0),(32,'asobtkal','nsobtkal','정확한 강아지','asobtkal@hugedomains.com',1,'/home/test/profile/','ac12e985-9a60-4f63-a7ac-38418393a591dasre.PNG',NULL,1),(33,'hmaidenm','dmaidenm','놀라운 뱀','nmaidenm@bloglines.com',1,'/home/test/profile/','d3f61be2-398e-440b-8f69-e9e5dbc39865dfdj.PNG',NULL,0),(34,'veppsonn','keppsonn','매력적인 고양이','keppsonn@dropbox.com',1,'/home/test/profile/','4d811a8d-5a34-457a-8dd8-4e0d9fb007b7hnhj.PNG',NULL,1),(35,'tlabono','klabono','활동적인 여우','alabono@youtube.com',1,'/home/test/profile/','95325979-fe7b-436b-8644-5f5afeacd46basawq.PNG',NULL,1),(36,'stickelp','ftickelp','춤추는 강아지','btickelp@hud.gov',1,'/home/test/profile/','906cdc3f-1ccf-4f98-b541-700fea3d118fdffg.PNG',NULL,1),(37,'lprudenceq','iprudenceq','노래하는 쥐','aprudenceq@businesswire.com',1,'/home/test/profile/','27abbf7c-6806-4102-ab66-f537a13fbeb0dfdd.PNG',NULL,1),(38,'ehalliburtonr','jhalliburtonr','독서하는 토끼','bhalliburtonr@friendfeed.com',1,'/home/test/profile/','93b70abe-0ad9-4a45-ac99-3e4f5c324263aasa.PNG',NULL,1),(39,'jcusteds','mcusteds','장난기 많은 강아지','dcusteds@samsung.com',1,'/home/test/profile/','e21900f0-08db-4f3a-a3c9-b779e4238f4dfd.PNG',NULL,1),(40,'hposert','jposert','근면한 토끼','dposert@bravesites.com',1,'/home/test/profile/','80eada84-0f6d-4bd0-a801-bcf01fc8f527dfdfdfd.PNG',NULL,1),(41,'bdeleonu','bdeleonu','영리한 토끼','edeleonu@ning.com',1,'/home/test/profile/','f4101a1d-8a7f-432d-8371-39af5658cc88ddd.PNG',NULL,1),(42,'brawstornev','trawstornev','영리한 뱀','brawstornev@tinyurl.com',1,'/home/test/profile/','926cd64f-5fbc-48f1-b35b-a47af76b4b77dddd.PNG',NULL,1),(43,'bgoadew','mgoadew','패셔니스타 돼지','agoadew@techcrunch.com',1,'/home/test/profile/','f0b9fe4d-afd8-4930-a1ea-26b44a6d5eefzz.PNG',NULL,1),(44,'jgummiex','rgummiex','도전적인 여우','rgummiex@rambler.ru',1,'/home/test/profile/','87543216-54db-4949-8747-5dd0547e0602zzzz.PNG',NULL,1),(45,'mrighyy','grighyy','사려깊은 고양이','grighyy@alexa.com',1,'/home/test/profile/','dfd736f4-ec06-4dcc-9ab3-07f5167ac777noun_Tennis_1671480.png',NULL,1),(46,'awhorallz','dwhorallz','귀여운 쥐','cwhorallz@instagram.com',1,'/home/test/profile/','1532766c-765e-4d58-9d11-99c302457341noun_Сrown_220420.png',NULL,1),(47,'bhopfer10','shopfer10','배려하는 뱀','chopfer10@businessweek.com',1,'/home/test/profile/','ed86d05f-2db5-4247-a35a-cbbb2a74b519Labrador Retriever Siberian Husky Pug Puppy Cat PNG - Free Download.jpg',NULL,1),(48,'folliver11','folliver11','춤추는 돼지','kolliver11@dot.gov',1,'/home/test/profile/','641373a7-fdfd-4d73-b76c-39f53b205215Donald Duck PNG, Transparent Donald Duck PNG Image Free Download.jpg',NULL,1),(49,'dbuse12','mbuse12','아름다운 강아지','ebuse12@google.cn',1,'/home/test/profile/','49c32610-0422-4d05-9086-d3409eed2ff1s.PNG',NULL,1),(50,'abubeer13','obubeer13','인간적인 거북이','nbubeer13@amazon.com',1,'/home/test/profile/','706ce718-3069-4c4d-8456-8cf8e6a6bdfe캡처.PNG',NULL,1),(51,'cookie','q123123','태평한 여우','friend_dog@naver.com',1,'/home/test/profile/','1cd6e392-65c9-4e9f-8073-35b953aec066cookie_profile.png','쿠키네\n사랑스러운 쿠키의 사진첩입니다ㅎㅎ',1),(52,'friend_cat','q123123','밥이맘','friend_cat@naver.com',1,'/home/test/profile/','ccdd39ad-7409-40d0-99cc-7b4a29214e78kimbob.jpg','안녕하세요~ 밥이맘입니다!',1),(53,'friend_duck','q123123','치즈언니','friend_duck@naver.com',1,'/home/test/profile/','a18c0f3f-0961-4275-ac81-d66cb9e7c0b1duck2.png',NULL,1),(54,'friend_fish','q123123','사랑스러운 물고기','friend_fish@naver.com',1,'/home/test/profile/','c13f30a7-1428-4fb1-b033-729d7fddc03dfish21.jpg',NULL,1),(55,'friend_fox','q123123','하양맘','friend_fox@naver.com',1,'/home/test/profile/','421dc307-b499-45b4-94d2-d1e536ad226ffox14.jpg',NULL,1),(56,'friend_frog','q123123','개굴개굴','friend_frog@naver.com',1,'/home/test/profile/','a6915ebf-5029-4911-8684-13ad802f6f60frog12.jpg',NULL,1),(57,'friend_mouse','q123123','찍찍','friend_mouse@naver.com',1,'/home/test/profile/','e1e8bb8f-7828-49d6-b2c7-3a54294a60admouse14.jpg',NULL,1),(58,'friend_pig','q123123','꿀꿀이','friend_pig@naver.com',1,'/home/test/profile/','ae41d112-4bf9-4d27-b832-b269c304e0f3pig3.jpg',NULL,1),(59,'friend_rabbit','q123123','토끼조아','friend_rabbit@naver.com',1,'/home/test/profile/','13132077-6899-446f-bb49-29bb587947earabbit7.png',NULL,1),(60,'friend_snake','q123123','스르르륵','friend_snake@naver.com',1,'/home/test/profile/','b2cab215-172b-4e83-95f1-2a1d864f6f51snake11.jpg',NULL,1),(61,'friend_turtle','q123123','꼬북언니','friend_turtle@naver.com',1,'/home/test/profile/','0f9509ba-10b5-4b9a-8c48-d1acb5951accturtle16.jpg',NULL,1),(62,'opopop','opopop1234',NULL,'ooo@naver.com',1,'/home/test/profile/','5351f845-1bc7-4079-bcdb-d194ddfa8904동그리.jpg',NULL,1),(63,'arong123','arong123','잘생긴 고양이','arong12@naver.com',1,'/home/test/profile/','d1933b99-1a4d-4ebe-80c4-bf527f06e772arong7.jpeg','사랑스러운 아롱이의 사진첩입니다ㅎㅎ',1),(64,'testid','testid12','여유를 즐기는 토끼','asooso1@naver.com',0,'/home/petNme/profile/','user_default.jpg',NULL,1),(65,'asdsadasd','asdasdasd1',NULL,'jinsukdl@naver.com',1,'/home/petNme/profile/','user_default.jpg',NULL,1),(66,'cookie123','cookie123',NULL,'asooso1212@gmail.com',0,'/home/petNme/profile/','user_default.jpg',NULL,1),(67,'jb9992','wkdwlqls1!','인내심있는 여우','jb9992@daum.net',1,'/home/petNme/profile/','user_default.jpg',NULL,1),(68,'petnme','1q2w3e4r',NULL,'ssrcus2u@naver.com',1,'/home/petNme/profile/','user_default.jpg',NULL,1);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  5:16:42
