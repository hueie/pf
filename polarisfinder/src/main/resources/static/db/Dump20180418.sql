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
-- Table structure for table `animals`
--

DROP TABLE IF EXISTS `animals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animals` (
  `animals_id` int(11) NOT NULL,
  `animals_nm` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`animals_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animals`
--

LOCK TABLES `animals` WRITE;
/*!40000 ALTER TABLE `animals` DISABLE KEYS */;
INSERT INTO `animals` (`animals_id`, `animals_nm`) VALUES (1,'난쟁이 Dwarf'),(2,'거인 Giant'),(3,'도깨비 Goblin'),(4,'마귀할멈 Hag'),(5,'집요정 House-elf'),(6,'님프 Nymph'),(7,'뱀파이어 Vampire'),(8,'벨라 Veela'),(9,'애크로맨툴라 Acromantula'),(10,'애쉬와인더 Ashwinder'),(11,'어거레이 Augurey'),(12,'바실리스크 Basilisk'),(13,'빌리위그 Billywig'),(14,'폭탄꼬리스크루트 Blast-EndedSkrewt'),(15,'보우트러클 Bowtruckle'),(16,'번디먼 Bundimun'),(17,'켄타우로스 Centaur'),(18,'키메라 Chimera'),(19,'치즈퍼플 Chizpurfle'),(20,'클래버트 Clabbert'),(21,'코카트리스 Cokatrice'),(22,'크럽 Crup'),(23,'데미가이즈 Demiguise'),(24,'디리코울 Diricawl'),(25,'독시 Doxy'),(26,'용 Dragon'),(27,'더그보그 Dugbog'),(28,'어클링 Erkling'),(29,'에럼펀트 Erumpent'),(30,'요정 Fairy'),(31,'불게 FireCrab'),(32,'플로버웜 Flobberworm'),(33,'프우퍼 Fwooper'),(34,'지니 Genie'),(35,'구울 Ghoul'),(36,'글럼범블 Glumbumble'),(37,'땅신령 Gnome'),(38,'골든스니젯 GoldenSnidget'),(39,'그래폰 Graphorn'),(40,'그리핀 Griffin'),(41,'그라인딜로우 Grindylow'),(42,'하이드비하인드 Hidebehind'),(43,'히포캠퍼스 Hippocampus'),(44,'히포그리프 Hippogriff'),(45,'호닥 Hodac'),(46,'호클럼프 Horklump'),(47,'혼드서펀트 HornedSerpent'),(48,'임프 Imp'),(49,'자베이 Jarvey'),(50,'자버놀 Jobberknoll'),(51,'갓파 Kappa'),(52,'켈피 Kelpie'),(53,'날 Knarl'),(54,'니즐 Kneazle'),(55,'레프러콘 Leprechaun'),(56,'레시폴드 Lethifold'),(57,'로바러그 Lobalug'),(58,'맥클리드말라클로우 MackledMalaclaw'),(59,'만티코어 Manticore'),(60,'인어 Merpeople'),(61,'모크 Moke'),(62,'문카프 Mooncalf'),(63,'머트랩 Murtlap'),(64,'니플러 Niffler'),(65,'녹테일 Nogtail'),(66,'눈두 Nundu'),(67,'오캐미 Occamy'),(68,'오거 Ogre'),(69,'부엉이,올빼미 Owl'),(70,'불사조 Phoenix'),(71,'픽시 Pixie'),(72,'플림피 Plimpy'),(73,'포그레빈 Pogrebin'),(74,'폴락 Porlock'),(75,'퍼프스캔 Puffskein'),(76,'퀸타페드 Quintaped'),(77,'라모라 Ramora'),(78,'레드캡 RedCap'),(79,'리엠 Re\'em'),(80,'런에스푸어 Runespoor'),(81,'살라맨더 Salamander'),(82,'바다뱀 SeaSerpent'),(83,'쉬레이크 Shrake'),(84,'스낼리개스터 Snallygaster'),(85,'스핑크스 Sphinx'),(86,'스트릴러 Streeler'),(87,'스우핑이블 SwoopingEvil'),(88,'테보 Tebo'),(89,'천둥새 Thunderbird'),(90,'트롤 Troll'),(91,'유니콘 Unicorn'),(92,'웜퍼스캣 WampusCat'),(93,'늑대인간 Werewolf'),(94,'화이트강의괴물 WhiteRiverMonster'),(95,'날개달린말 Wingedhorse'),(96,'아브라산 Abraxan'),(97,'애토난 Aethonan'),(98,'그라니안 Granian'),(99,'세스트랄 Thestrals'),(100,'예티 Yeti'),(101,'유령 Ghost'),(102,'인페리우스 Inferius'),(103,'밴시 Banshee'),(104,'보가트 Boggart'),(105,'디멘터 Dementor'),(106,'소리의요정 Poltergeist'),(107,'옵스큐러스 Obscurus');
/*!40000 ALTER TABLE `animals` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamers`
--

LOCK TABLES `dreamers` WRITE;
/*!40000 ALTER TABLE `dreamers` DISABLE KEYS */;
INSERT INTO `dreamers` (`id`, `content`, `like_cnt`, `bookmark_cnt`, `user_id`, `reg_dt`) VALUES (30,'<p>abc</p>',0,0,23,'2018-04-10 16:49:41'),(31,'<p>저는 admin이에요 잘부탁 드릴께요!!!</p>',0,0,22,'2018-04-11 12:00:53'),(32,'<p><img src=\"/files/23/20180416/20180416160658904.jpg\" style=\"width: 550px; float: right;\" class=\"note-float-right pull-right\"></p><p>test hh</p><p><br></p>',0,0,23,'2018-04-17 13:17:39'),(33,'<p>test fload&nbsp;<img style=\"width: 550px;\" src=\"/files/23/20180417/20180417131836410.jpg\" class=\"pull-right\"></p>',0,0,23,'2018-04-17 13:18:42'),(35,'<p>dsa</p><div class=\"embed-responsive embed-responsive-16by9\" style=\"float: none;\"><iframe frameborder=\"0\" src=\"//www.youtube.com/embed/np1XQY4T5Zs?&amp;loop=0\" width=\"auto\" height=\"auto\" class=\"embed-responsive note-video-clip\"></iframe></div><p><br></p>',0,0,23,'2018-04-17 16:27:06'),(36,'<p>ㅅㄷㄴㅅ</p><div class=\"embed-responsive embed-responsive-16by9\" style=\"float: none;\"><iframe frameborder=\"0\" src=\"//www.youtube.com/embed/np1XQY4T5Zs?&amp;loop=0\" width=\"auto\" height=\"auto\" class=\"embed-responsive note-video-clip\"></iframe></div><p><br></p>',0,0,23,'2018-04-17 16:29:02'),(37,'<p>tests<span style=\"font-family: &quot;Black Han Sans&quot;;\">﻿</span></p><p><span style=\"font-family: &quot;Black Han Sans&quot;;\"><br></span></p><p><span style=\"font-family: &quot;Black Han Sans&quot;;\">Black Yack&nbsp;</span></p><p><span style=\"font-size: 24px;\">﻿gdsafdsaf1111</span><span style=\"font-family: &quot;Black Han Sans&quot;;\"><br></span></p>',0,0,23,'2018-04-17 17:43:20');
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
  `dreamersfile_orign_nm` text,
  `dreamersfile_serv_nm` text,
  `dreamersfile_serv_path` text,
  `user_id` int(11) DEFAULT NULL,
  `reg_dt` datetime DEFAULT NULL,
  `dreamersfile_ext` text,
  PRIMARY KEY (`dreamersfile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamersfile`
--

LOCK TABLES `dreamersfile` WRITE;
/*!40000 ALTER TABLE `dreamersfile` DISABLE KEYS */;
INSERT INTO `dreamersfile` (`dreamersfile_id`, `dreamersfile_orign_nm`, `dreamersfile_serv_nm`, `dreamersfile_serv_path`, `user_id`, `reg_dt`, `dreamersfile_ext`) VALUES (1,'polarxy.jpg','20180416160229209.jpg','/files/23/20180416/20180416160229209.jpg',23,NULL,NULL),(2,'polarxy.jpg','20180416160658904.jpg','/files/23/20180416/20180416160658904.jpg',23,'2018-04-16 16:06:59','jpg'),(3,'polarxy.jpg','20180416162333133.jpg','/files/23/20180416/20180416162333133.jpg',23,'2018-04-16 16:23:33','jpg'),(4,'polarxy.jpg','20180416162410039.jpg','/files/23/20180416/20180416162410039.jpg',23,'2018-04-16 16:24:10','jpg'),(5,'polarxy.jpg','20180416162643201.jpg','/files/23/20180416/20180416162643201.jpg',23,'2018-04-16 16:26:43','jpg'),(6,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416163412032.jpg','/files/23/20180416/20180416163412032.jpg',23,'2018-04-16 16:34:12','jpg'),(7,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416163510027.jpg','/files/23/20180416/20180416163510027.jpg',23,'2018-04-16 16:35:10','jpg'),(8,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416163836484.jpg','/files/23/20180416/20180416163836484.jpg',23,'2018-04-16 16:38:36','jpg'),(9,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416163952223.jpg','/files/23/20180416/20180416163952223.jpg',23,'2018-04-16 16:39:52','jpg'),(10,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416164416179.jpg','/files/23/20180416/20180416164416179.jpg',23,'2018-04-16 16:44:16','jpg'),(11,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416164511417.jpg','/files/23/20180416/20180416164511417.jpg',23,'2018-04-16 16:45:11','jpg'),(12,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180416164556317.jpg','/files/23/20180416/20180416164556317.jpg',23,'2018-04-16 16:45:56','jpg'),(13,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180417105750831.jpg','/files/23/20180417/20180417105750831.jpg',23,'2018-04-17 10:57:51','jpg'),(14,'polarxy.jpg','20180417131836410.jpg','/files/23/20180417/20180417131836410.jpg',23,'2018-04-17 13:18:36','jpg'),(15,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180417132821772.jpg','/files/23/20180417/20180417132821772.jpg',23,'2018-04-17 13:28:22','jpg'),(16,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180417133413364.jpg','/files/23/20180417/20180417133413364.jpg',23,'2018-04-17 13:34:13','jpg'),(17,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180417133433577.jpg','/files/23/20180417/20180417133433577.jpg',23,'2018-04-17 13:34:34','jpg'),(18,'depositphotos_168693914-stock-photo-purple-color-tone-shading-curve.jpg','20180417133447678.jpg','/files/23/20180417/20180417133447678.jpg',23,'2018-04-17 13:34:48','jpg'),(19,'polarxy.jpg','20180417133602640.jpg','/files/23/20180417/20180417133602640.jpg',23,'2018-04-17 13:36:03','jpg'),(20,'polarxy.jpg','20180417135019668.jpg','/files/23/20180417/20180417135019668.jpg',23,'2018-04-17 13:50:20','jpg'),(21,'polarxy.jpg','20180417170741656.jpg','/files/23/20180417/20180417170741656.jpg',23,'2018-04-17 17:07:42','jpg'),(22,'header.jpg','20180417174208757.jpg','/files/23/20180417/20180417174208757.jpg',23,'2018-04-17 17:42:09','jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` (`id`, `subject`, `content`, `user_id`, `reg_dt`) VALUES (1,'공지사항1 제목','공지사항1 내용',23,'2018-04-02 13:06:23');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` (`id`, `type`, `related_id`, `subject`, `content`, `status`, `user_id`, `reg_dt`) VALUES (5,'notice','1','공지사항1 제목','',0,23,'2018-04-03 13:38:30'),(6,'roadmap','1','워킹홀리세이란','',0,23,'2018-04-03 16:31:26'),(7,'roadmap','2','CIC 계정 만들기','',0,23,'2018-04-03 16:36:29');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `active`, `username`, `nickname`, `password`, `type`, `oauthid`) VALUES (22,1,'admin23@google.com','관리자1','$2a$10$whBI5jx9CvlkP1yPBY/iSelmiONd.ZyVyfu.zAQSYxHhGOzduup5q',NULL,NULL),(23,1,'a@a.a','관리자2','$2a$10$Jn09iSooXQK16pXVLtkc.OztHM2keUxMJ3wxsKO8EjARBKgnOWJ36',NULL,NULL);
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
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (22,1),(23,1);
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

-- Dump completed on 2018-04-18 16:45:37
