-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: testing_system
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answers` (
  `id_answer` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(200) NOT NULL,
  `right` tinyint(1) NOT NULL DEFAULT '0',
  `id_question` int(11) NOT NULL,
  PRIMARY KEY (`id_answer`),
  UNIQUE KEY `unique_answer` (`text`,`id_question`),
  KEY `id_question_idx` (`id_question`),
  CONSTRAINT `id_question` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id_question`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'<!-- This is a valid comment in java -->',0,1),(2,'// This is a valid comment in java',1,1),(3,'/* This is a valid comment in java. */',1,1),(4,'/** This is a valid comment in java. */',1,1),(6,'присвоить null всем ссылкам на объект',0,4),(7,'вызвать деструктор у объекта',0,4),(8,'вызвать метод finalize() у объекта',0,4),(9,'этого нельзя сделать вручную',1,4),(10,'||',0,5),(11,'&&',0,5),(12,'%',1,5),(13,'?: (тернарный оператор)',0,5),(14,'да',1,6),(15,'нет',0,6),(24,'h6',0,9),(25,'h5',0,9),(26,'h1',1,9),(27,'h2',0,9),(28,'<strong>',1,10),(29,'<i>',0,10),(30,'<em>',0,10),(31,'<b>',1,10),(41,'121',1,11),(42,'131',0,11),(43,'111',0,11),(44,'141',0,11),(45,'2',0,12),(46,'8',1,12),(47,'6',0,12),(48,'4',0,12),(49,'10',0,13),(50,'100',0,13),(51,'1000',1,13),(52,'10000',0,13),(53,'0',0,14),(54,'1',1,14),(55,'2',0,14),(56,'3',0,14),(57,'0',1,15),(58,'1',0,15),(59,'2',0,15),(60,'3',0,15),(61,'4',0,16),(62,'2',0,16),(63,'6',1,16),(64,'8',0,16),(108,'dfv',1,30),(110,'sdfe',0,30),(119,'FBV',0,35),(120,'RTBGRTG',1,35),(123,'12344',0,37),(124,'1',1,37),(125,'4',0,37),(133,'DFV',1,40),(134,'DSV',0,40);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(300) NOT NULL,
  `mult_choice` tinyint(4) NOT NULL DEFAULT '0',
  `id_test` int(11) NOT NULL,
  PRIMARY KEY (`id_question`),
  KEY `id_test_idx` (`id_test`),
  CONSTRAINT `id_test` FOREIGN KEY (`id_test`) REFERENCES `tests` (`id_test`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'Выберите варианты комментариев, которые не приведут к ошибке.',1,1),(4,'Как можно уничтожить объект в Java?',0,1),(5,'У каких операторов всегда вычисляются все операнды (выберите все подходящие варианты)?',1,1),(6,'publiс аbstrаct сlass Onе { \r\n    рublic abstraсt int dоJob(); \r\n} \r\nclаss Twо ехtеnds Оnе { } ',0,1),(9,'Какой уровень заголовка самый большой по размеру?',0,3),(10,'Какие теги делают шрифт полужирным?',1,3),(11,'11 * 11 = ',0,8),(12,'4 + 4 = ',0,8),(13,'10 * 100 =',0,8),(14,'12 / 12 =',0,8),(15,'150 * 0 = ',0,8),(16,'2 + 2 * 2 = ',0,8),(30,'bfdb',0,1),(35,'REDVBG',0,1),(37,'S',0,1),(40,'DF',0,3);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `results` (
  `id_result` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_test` int(11) NOT NULL,
  `mark` double NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_result`),
  KEY `user_id_result_idx` (`id_user`),
  KEY `id_test_result_idx` (`id_test`),
  CONSTRAINT `test_id_result` FOREIGN KEY (`id_test`) REFERENCES `tests` (`id_test`) ON UPDATE CASCADE,
  CONSTRAINT `user_id_result` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,4,1,33.33,'2017-04-18 01:13:05'),(2,4,2,66.67,'2017-04-05 08:44:28'),(3,4,8,100,'2017-04-14 20:22:34'),(4,8,1,50,'2017-04-16 04:05:47'),(5,9,2,66.67,'2017-04-16 00:13:00'),(6,9,8,75,'2017-04-22 08:45:15'),(7,4,3,0,'2017-04-22 10:19:06'),(8,4,1,0,'2017-04-22 10:21:45'),(9,4,1,0,'2017-04-22 10:24:21'),(10,4,3,0,'2017-04-22 10:26:11'),(11,4,1,0,'2017-04-22 10:29:21'),(12,4,8,66.67,'2017-04-22 10:33:09'),(13,22,8,100,'2017-04-22 10:37:32'),(14,4,1,100,'2017-04-23 04:43:19'),(15,4,1,100,'2017-04-23 07:13:02'),(16,4,1,20,'2017-04-23 07:36:21'),(17,4,1,100,'2017-04-23 07:57:03'),(18,4,1,100,'2017-04-23 07:58:18'),(19,4,1,20,'2017-04-23 08:18:38'),(20,4,1,57.14,'2017-04-23 09:02:00'),(21,4,1,57.14,'2017-04-23 13:31:01'),(22,4,8,100,'2017-04-23 13:31:29'),(23,4,1,42.86,'2017-04-23 13:38:13'),(24,4,1,14.29,'2017-04-23 14:43:30'),(25,4,1,14.29,'2017-04-24 06:39:41'),(26,4,1,42.86,'2017-04-24 06:51:28'),(27,4,1,28.57,'2017-04-24 08:53:32');
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tests`
--

DROP TABLE IF EXISTS `tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tests` (
  `id_test` int(11) NOT NULL AUTO_INCREMENT,
  `caption` varchar(45) NOT NULL,
  `description` varchar(300) NOT NULL DEFAULT '',
  `size` int(3) NOT NULL,
  `activated` tinyint(4) NOT NULL DEFAULT '0',
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id_test`),
  UNIQUE KEY `unique_caption_author` (`caption`,`author`),
  KEY `author_idx` (`author`),
  CONSTRAINT `author` FOREIGN KEY (`author`) REFERENCES `users` (`id_user`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
INSERT INTO `tests` VALUES (1,'Java','',10,0,1),(2,'CSS','',5,0,1),(3,'HTML','',5,0,2),(4,'Java Script','',5,0,2),(5,'Algorithms','',5,0,1),(7,'C#','',5,0,2),(8,'Math','',5,0,1);
/*!40000 ALTER TABLE `tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_user` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` enum('TUTOR','STUDENT') NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'RedFoxIK','1111','Yaroslava','Kalashnik','yakalashnik@gmail.com','TUTOR'),(2,'tutor','tutor','Vasya','Pupkin','tutor@i.ua','TUTOR'),(4,'student','student','Андрей','Колесник','student@i.ua','STUDENT'),(6,'misha','mishka','Михаил','Романенко','test@gmail.com','STUDENT'),(8,'romka','romka','Роман','Калиненко','rom4ik@gmail.com','STUDENT'),(9,'dimon','dimon4ik','Dima','Pe4kin','dimon@ukr.net','STUDENT'),(12,'petro','petya','Petro','Vaskin','vaskin@mail.ru','STUDENT'),(14,'jam','jamjam','Jon','Smit','smit@yandex.ru','STUDENT'),(15,'login','password','Secret','SecretAgain','secret@i.ua','STUDENT'),(19,'genko','genya1','Евгений','Романович','genya@gmail.com','STUDENT'),(21,'kalinenko','kalina12','Никита','Калиненко','kalinenko@i.ua','STUDENT'),(22,'andri','andri12','Андрей','Бодненко','andrej@gmail.com','STUDENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-24 14:31:54
