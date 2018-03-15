CREATE DATABASE  IF NOT EXISTS `polarisfinder` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `polarisfinder`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: polarisfinder
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitchatpub`
--

LOCK TABLES `chitchatpub` WRITE;
/*!40000 ALTER TABLE `chitchatpub` DISABLE KEYS */;
INSERT INTO `chitchatpub` (`id`, `placename`, `placelongitude`, `placelatitude`, `placecomment`) VALUES (3,'오스트레일리아 뉴사우스웨일스 주 시드니 시 시드니 오페라 하우스','0','0','보스가 성격이 안좋아요'),(4,'Sydney 뉴사우스웨일스 주 오스트레일리아','0','0','음식 맛이 없네요!'),(5,'Sydney 뉴사우스웨일스 주 오스트레일리아','0','0','집 상태가 안좋아요! 룸메이트는 깔끔해서 좋아요!'),(7,'Sydney 뉴사우스웨일스 주 오스트레일리아','0','0','호주는 눈이 부실 정도로 햇빛이 비쳐요!'),(8,'Sydney 뉴사우스웨일스 주 오스트레일리아','0','0','오늘은 비가 많이오네요.'),(9,'Sydney 뉴사우스웨일스 주 오스트레일리아','0','0','오늘 불꽃 놀이 축제를 많이해요! 명당자리는 하버브릿지 아래!'),(10,'Sydney 뉴사우스웨일스 주 오스트레일리아','0','0','하우스키핑 일 너무 힘들어요.ㅠㅠ'),(11,'미국 뉴욕','0','0','뉴욕 펍 좋아요'),(12,'','0','0',''),(13,'','0','0','뮻ㅇㅇㅇ'),(14,NULL,'0','0',NULL),(15,NULL,'0','0',NULL),(16,'','0','0','ㄹㅇㄴㅁ'),(17,'오스트레일리아 뉴사우스웨일스 주 시드니 시','0','0','ㅎㄹㅇㄴ'),(18,'오스트레일리아 뉴사우스웨일스 주 시드니 시','0','0','ㄹㅇㄴㅁ'),(19,'오스트레일리아 뉴사우스웨일스 주 시드니 시','0','0','1111111111111111111111'),(22,'','0','0','fdsafdsa'),(23,'','0','0','fdsa'),(24,'','0','0','fdsa111');
/*!40000 ALTER TABLE `chitchatpub` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamers`
--

LOCK TABLES `dreamers` WRITE;
/*!40000 ALTER TABLE `dreamers` DISABLE KEYS */;
INSERT INTO `dreamers` (`id`, `content`, `like_cnt`, `bookmark_cnt`, `user_id`, `reg_dt`) VALUES (8,'<p>testddddddffffsss</p><!-- {{fooObj.content}} -->\n					\n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',6,0,0,NULL),(9,'<p>a</p> <!-- {{fooObj.content}} -->\n					\n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',0,0,0,NULL),(10,'<p>d</p> <!-- {{fooObj.content}} -->\n					\n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',0,0,0,NULL),(11,'<p>c</p> <!-- {{fooObj.content}} -->\n					\n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',3,0,0,NULL),(12,'<p>dqq</p> <!-- {{fooObj.content}} -->\n					\n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',4,-1,0,NULL),(13,'<p>ddddeeeee</p> <!-- {{fooObj.content}} -->\n					\n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',19,-1,0,NULL),(14,'<p>fdsafdsa</p><!-- {{fooObj.content}} -->\n					\n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',1,1,22,NULL),(15,'<p>fdsafdsafdsa</p><div class=\"medium-insert-embeds\" contenteditable=\"false\">\n	<figure>\n		<div class=\"medium-insert-embed\">\n			<div style=\"left: 0; width: 100%; height: 0; position: relative; padding-bottom: 56.2493%;\"><iframe src=\"https://www.youtube.com/embed/zIwLWfaAg-8?rel=0&amp;showinfo=0\" style=\"border: 0; top: 0; left: 0; width: 100%; height: 100%; position: absolute;\" allowfullscreen=\"\" scrolling=\"no\"></iframe></div>\n		</div>\n	</figure>\n	<div class=\"medium-insert-embeds-overlay\"></div>\n</div><!-- {{fooObj.content}} -->\n					\n					<p><br></p><div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"left: 35px; top: 75.4667px; display: none;\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none;\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',5,0,22,NULL),(19,'<p>fdsafdsa</p><div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>',5,0,22,'2018-03-15 09:26:10');
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
-- Table structure for table `fileupload`
--

DROP TABLE IF EXISTS `fileupload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fileupload` (
  `fileupload_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_nm` text,
  `file_path` text,
  `fileupload_reg_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`fileupload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileupload`
--

LOCK TABLES `fileupload` WRITE;
/*!40000 ALTER TABLE `fileupload` DISABLE KEYS */;
/*!40000 ALTER TABLE `fileupload` ENABLE KEYS */;
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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `active`, `username`, `nickname`, `password`) VALUES (22,1,'admin23@google.com','afdsa','$2a$10$whBI5jx9CvlkP1yPBY/iSelmiONd.ZyVyfu.zAQSYxHhGOzduup5q');
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
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (22,1);
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

-- Dump completed on 2018-03-15 15:27:41
