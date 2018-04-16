-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 52.79.105.142    Database: polarisfinder
-- ------------------------------------------------------
-- Server version	5.5.58

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
-- Table structure for table `anchor`
--

DROP TABLE IF EXISTS `anchor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anchor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '1 : notice\n2 : produce 101\n',
  `related_id` int(11) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anchor`
--

LOCK TABLES `anchor` WRITE;
/*!40000 ALTER TABLE `anchor` DISABLE KEYS */;
/*!40000 ALTER TABLE `anchor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitchatpub`
--

DROP TABLE IF EXISTS `chitchatpub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitchatpub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placename` varchar(45) DEFAULT NULL,
  `placelongitude` varchar(45) DEFAULT NULL,
  `placelatitude` varchar(45) DEFAULT NULL,
  `placecomment` varchar(45) DEFAULT NULL,
  `star_tot_cnt` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitchatpub`
--

LOCK TABLES `chitchatpub` WRITE;
/*!40000 ALTER TABLE `chitchatpub` DISABLE KEYS */;
INSERT INTO `chitchatpub` (`id`, `placename`, `placelongitude`, `placelatitude`, `placecomment`, `star_tot_cnt`, `user_id`, `reg_dt`) VALUES (27,'오스트레일리아 뉴사우스웨일스 주 시드니','151.20929','-33.86882','ㅁㅁ',0,23,NULL);
/*!40000 ALTER TABLE `chitchatpub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitchatpubstar`
--

DROP TABLE IF EXISTS `chitchatpubstar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitchatpubstar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chitchatpub_id` int(11) DEFAULT NULL,
  `star_cnt` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitchatpubstar`
--

LOCK TABLES `chitchatpubstar` WRITE;
/*!40000 ALTER TABLE `chitchatpubstar` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitchatpubstar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dreamers`
--

DROP TABLE IF EXISTS `dreamers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dreamers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `like_cnt` int(11) DEFAULT '0',
  `bookmark_cnt` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT '0',
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamers`
--

LOCK TABLES `dreamers` WRITE;
/*!40000 ALTER TABLE `dreamers` DISABLE KEYS */;
INSERT INTO `dreamers` (`id`, `content`, `like_cnt`, `bookmark_cnt`, `user_id`, `reg_dt`) VALUES (28,'<p>첫번째 게시물이에요! 잘 지켜 봐주세요!</p>',0,0,23,'2018-04-11 09:32:42'),(29,'<p style=\"text-align: center; \"><img style=\"width: 550px;\" src=\"/files/23/20180416/20180416080237183.jpg\"></p><p><br></p><p>까만 하늘에 별이 <span style=\"color: rgb(255, 214, 99);\">참 예쁘네요!</span></p><p><br></p><p style=\"text-align: right; \"><span style=\"color: rgb(255, 255, 0);\">어제와 다른</span> 오늘 열심히 또 화이팅!</p><p style=\"text-align: center; \"><span style=\"color: rgb(156, 198, 239);\">하루가 다르게 성장하고 발전하는 내 모습</span></p>',0,0,23,'2018-04-16 08:06:20');
/*!40000 ALTER TABLE `dreamers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dreamersbookmark`
--

DROP TABLE IF EXISTS `dreamersbookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dreamersbookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dreamers_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamersbookmark`
--

LOCK TABLES `dreamersbookmark` WRITE;
/*!40000 ALTER TABLE `dreamersbookmark` DISABLE KEYS */;
INSERT INTO `dreamersbookmark` (`id`, `dreamers_id`, `user_id`, `reg_dt`) VALUES (118,19,22,'2018-03-15 15:17:27'),(119,15,22,'2018-03-15 15:18:18'),(120,14,22,'2018-03-15 15:18:20');
/*!40000 ALTER TABLE `dreamersbookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dreamerscomment`
--

DROP TABLE IF EXISTS `dreamerscomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dreamerscomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dreamers_id` int(11) DEFAULT NULL,
  `dreamers_comment` varchar(200) DEFAULT NULL,
  `user_id` int(11) DEFAULT '0',
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamerscomment`
--

LOCK TABLES `dreamerscomment` WRITE;
/*!40000 ALTER TABLE `dreamerscomment` DISABLE KEYS */;
INSERT INTO `dreamerscomment` (`id`, `dreamers_id`, `dreamers_comment`, `user_id`, `reg_dt`) VALUES (20,8,'abcd',0,NULL),(21,8,'dddd',0,NULL),(22,8,'ssss',0,NULL),(23,8,'fdsa',0,NULL),(24,8,'',0,NULL),(25,13,'dsa',0,NULL),(26,13,'dsa',0,NULL),(27,13,'dsadsa',0,NULL),(28,13,'dsadsadsa',0,NULL),(29,13,'fdsafdsa',0,NULL),(30,13,'fda',0,NULL),(31,13,'fdsafdsa',0,NULL),(32,13,'fdsa',0,NULL),(33,13,'fdsafdsa',0,NULL),(34,13,'fdsafds',0,NULL),(35,13,'fdsafdsa',0,NULL),(36,13,'fdsafdsa',0,NULL),(37,13,'gggggggaa',0,NULL),(38,13,'fdsa',0,NULL),(39,13,'fdsafdsa',0,NULL),(40,13,'fdsafdsa',0,NULL),(41,13,'fdsa',0,NULL),(42,13,'fdsafdsa',0,NULL),(43,13,'fdsafdsa',0,NULL),(44,13,'fdsafdsa',0,NULL),(45,13,'fdsgfds',0,NULL),(46,13,'fdsafdsa',0,NULL),(47,13,'fdsa',0,NULL),(48,13,'fdsa',0,NULL),(49,13,'fdsafdsa',0,NULL),(50,13,'fdsa',0,NULL),(51,13,'fdsa',0,NULL),(52,13,'fdsa',0,NULL),(53,13,'fda',0,NULL),(54,13,'fdafdsa',0,NULL),(55,13,'',0,NULL),(56,13,'',0,NULL),(57,13,'',0,NULL),(58,13,'vcxz',0,NULL),(59,13,'',0,NULL),(60,13,'',0,NULL),(61,13,'',0,NULL),(62,13,'vcxvcxzbcx',0,NULL),(63,13,'fdafafafafa',0,NULL),(64,13,'asddsa',0,NULL),(65,12,'fdsafdsa',0,NULL),(66,11,'\n',0,NULL),(67,12,'\n',0,NULL),(68,10,'',0,NULL),(69,12,'fdsa\n',0,NULL),(70,11,'fdsafdsafads',0,NULL),(71,10,'fdsafdsafdsafdas',0,NULL),(72,13,'fdsafdsa',22,NULL),(73,15,'fdsafdsa',22,NULL),(74,15,'fdsafdsa',22,'2018-03-15 09:13:42'),(75,15,'fdsa',22,'2018-03-15 09:25:58');
/*!40000 ALTER TABLE `dreamerscomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dreamersfile`
--

DROP TABLE IF EXISTS `dreamersfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dreamersfile` (
  `dreamersfile_id` int(11) NOT NULL AUTO_INCREMENT,
  `dreamersfile_ext` text,
  `dreamersfile_orign_nm` text,
  `dreamersfile_serv_nm` text,
  `dreamersfile_serv_path` text,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`dreamersfile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamersfile`
--

LOCK TABLES `dreamersfile` WRITE;
/*!40000 ALTER TABLE `dreamersfile` DISABLE KEYS */;
INSERT INTO `dreamersfile` (`dreamersfile_id`, `dreamersfile_ext`, `dreamersfile_orign_nm`, `dreamersfile_serv_nm`, `dreamersfile_serv_path`, `user_id`, `reg_dt`) VALUES (1,'jpg','polarxy.jpg','20180416075418318.jpg','/files/23/20180416/20180416075418318.jpg',23,'2018-04-16 07:54:18'),(2,'jpg','polarxy.jpg','20180416075435868.jpg','/files/23/20180416/20180416075435868.jpg',23,'2018-04-16 07:54:35'),(3,'jpg','depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416075547327.jpg','/files/23/20180416/20180416075547327.jpg',23,'2018-04-16 07:55:47'),(4,'jpg','polarxy.jpg','20180416080237183.jpg','/files/23/20180416/20180416080237183.jpg',23,'2018-04-16 08:02:37');
/*!40000 ALTER TABLE `dreamersfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dreamerslike`
--

DROP TABLE IF EXISTS `dreamerslike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dreamerslike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dreamers_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamerslike`
--

LOCK TABLES `dreamerslike` WRITE;
/*!40000 ALTER TABLE `dreamerslike` DISABLE KEYS */;
INSERT INTO `dreamerslike` (`id`, `dreamers_id`, `user_id`, `reg_dt`) VALUES (61,8,0,NULL),(62,8,0,NULL),(63,8,0,NULL),(64,8,0,NULL),(65,8,0,NULL),(66,8,0,NULL),(67,8,0,NULL),(68,8,0,NULL),(69,8,0,NULL),(70,8,0,NULL),(71,8,0,NULL),(72,8,0,NULL),(73,8,0,NULL),(74,8,0,NULL),(75,8,0,NULL),(76,8,0,NULL),(77,11,0,NULL),(78,13,0,NULL),(79,13,0,NULL),(80,13,0,NULL),(81,13,0,NULL),(82,12,0,NULL),(83,13,0,NULL),(84,13,0,NULL),(85,13,0,NULL),(86,13,0,NULL),(87,13,0,NULL),(88,13,0,NULL),(89,12,0,NULL),(90,12,0,NULL),(91,11,0,NULL),(92,11,0,NULL),(93,13,0,NULL),(94,13,0,NULL),(95,13,0,NULL),(96,13,0,NULL),(97,13,0,NULL),(98,13,0,NULL),(99,13,0,NULL),(100,13,0,NULL),(112,14,22,'2018-03-15 12:26:21'),(115,19,22,'2018-03-15 14:24:54'),(116,15,22,'2018-03-15 14:24:57'),(118,19,22,'2018-03-15 14:42:41'),(119,15,22,'2018-03-15 14:42:43'),(120,19,22,'2018-03-15 15:06:12'),(121,14,22,'2018-03-15 15:11:29'),(122,19,22,'2018-03-15 15:15:49');
/*!40000 ALTER TABLE `dreamerslike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `following_user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` (`id`, `user_id`, `following_user_id`, `reg_dt`) VALUES (1,23,22,NULL),(2,23,23,'2018-03-29 17:56:32');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `to_user_id` int(11) DEFAULT NULL,
  `send_user_id` int(11) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` text,
  `star` tinyint(1) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`id`, `to_user_id`, `send_user_id`, `subject`, `content`, `star`, `status`, `reg_dt`) VALUES (1,23,23,'ad','ff',1,0,'2018-03-28 16:20:36'),(2,22,23,'abc','111',0,0,'2018-03-29 10:11:40'),(3,22,23,'aaaaa','aaaa',0,0,'2018-03-29 16:08:34');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(200) DEFAULT NULL,
  `content` text,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` (`id`, `subject`, `content`, `user_id`, `reg_dt`) VALUES (1,'공지사항1 제목','공지사항1 내용',23,'2018-04-02 13:06:23'),(2,'[긴급!] :  Australia Visa 701 변경되었습니다.!','변경내용입니다.',22,'2018-04-06 09:10:32');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role`) VALUES (1,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `related_id` varchar(45) DEFAULT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `content` text,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` (`id`, `type`, `related_id`, `subject`, `content`, `status`, `user_id`, `reg_dt`) VALUES (5,'notice','1','공지사항1 제목','',0,23,'2018-04-03 13:38:30'),(6,'roadmap','1','워킹홀리세이란','',0,23,'2018-04-03 16:31:26'),(7,'roadmap','2','CIC 계정 만들기','',0,23,'2018-04-03 16:36:29'),(8,'roadmap','2','CIC 계정 만들기','',0,22,'2018-04-06 09:09:39'),(9,'roadmap','1','워킹홀리세이란','',0,22,'2018-04-06 09:09:43'),(10,'notice','2','[긴급!] :  Australia Visa 701 변경되었습니다.!','',0,22,'2018-04-06 09:10:35');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `oauthid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `active`, `username`, `nickname`, `password`, `type`, `oauthid`) VALUES (22,1,'admin23@google.com','관리자1','$2a$10$whBI5jx9CvlkP1yPBY/iSelmiONd.ZyVyfu.zAQSYxHhGOzduup5q',NULL,NULL),(23,1,'a@a.a','관리자','$2a$10$Jn09iSooXQK16pXVLtkc.OztHM2keUxMJ3wxsKO8EjARBKgnOWJ36',NULL,NULL),(24,0,'hueieglobal@gmail.com','hueieglobal@gmail.com','hueieglobal@gmail.com','facebook','347041749114471');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (22,1),(23,1),(24,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-16 17:10:47
