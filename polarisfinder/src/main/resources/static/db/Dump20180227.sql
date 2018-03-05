CREATE DATABASE `polarisfinder` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE polarisfinder;


-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: polarisfinder
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `article_id` int(5) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookarng`
--

DROP TABLE IF EXISTS `bookarng`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookarng` (
  `ARNG_ID` int(11) DEFAULT NULL,
  `BOX_ID` int(11) DEFAULT NULL,
  `STACK_ID` tinytext,
  `BOOKSF_ID` tinytext,
  `BOOKSF_F_NO` int(11) DEFAULT NULL,
  `BOOKSF_R_NO` int(11) DEFAULT NULL,
  `BOOKSF_R_SNO` int(11) DEFAULT NULL,
  `ARNG_CD` tinytext,
  `BOX_ARNG_DT` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookarng`
--

LOCK TABLES `bookarng` WRITE;
/*!40000 ALTER TABLE `bookarng` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookarng` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksf`
--

DROP TABLE IF EXISTS `booksf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booksf` (
  `BOOKSF_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STACK_ID` int(11) DEFAULT NULL,
  `BOOKSF_NM` text,
  `BOOKSF_REMK` text,
  `BOOKSF_HEIGHT` int(11) DEFAULT '0',
  `BOOKSF_ROW_CNT` int(11) DEFAULT '0',
  PRIMARY KEY (`BOOKSF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksf`
--

LOCK TABLES `booksf` WRITE;
/*!40000 ALTER TABLE `booksf` DISABLE KEYS */;
/*!40000 ALTER TABLE `booksf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksf_flw`
--

DROP TABLE IF EXISTS `booksf_flw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booksf_flw` (
  `STACK_ID` tinytext,
  `BOOKSF_ID` tinytext,
  `FLW_NO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksf_flw`
--

LOCK TABLES `booksf_flw` WRITE;
/*!40000 ALTER TABLE `booksf_flw` DISABLE KEYS */;
/*!40000 ALTER TABLE `booksf_flw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `BOX_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOX_NM` tinytext,
  `BOX_REMK` tinytext,
  PRIMARY KEY (`BOX_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cam`
--

DROP TABLE IF EXISTS `cam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cam` (
  `cam_id` int(11) NOT NULL AUTO_INCREMENT,
  `fileupload_id` int(11) DEFAULT NULL,
  `cam_nm` text,
  `cam_imgsrc` text,
  PRIMARY KEY (`cam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cam`
--

LOCK TABLES `cam` WRITE;
/*!40000 ALTER TABLE `cam` DISABLE KEYS */;
/*!40000 ALTER TABLE `cam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cammapping`
--

DROP TABLE IF EXISTS `cammapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cammapping` (
  `cammapping_id` int(11) NOT NULL AUTO_INCREMENT,
  `fileupload_id` int(11) DEFAULT NULL,
  `line_id` int(11) DEFAULT NULL,
  `start_x` int(11) DEFAULT NULL,
  `end_x` int(11) DEFAULT NULL,
  `start_y` int(11) DEFAULT NULL,
  `end_y` int(11) DEFAULT NULL,
  `booksf_id` int(11) NOT NULL DEFAULT '0',
  `cammapping_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cammapping_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cammapping`
--

LOCK TABLES `cammapping` WRITE;
/*!40000 ALTER TABLE `cammapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `cammapping` ENABLE KEYS */;
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
  `placelocation` varchar(45) DEFAULT NULL,
  `placecomment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitchatpub`
--

LOCK TABLES `chitchatpub` WRITE;
/*!40000 ALTER TABLE `chitchatpub` DISABLE KEYS */;
INSERT INTO `chitchatpub` (`id`, `placename`, `placelocation`, `placecomment`) VALUES (3,'오스트레일리아 뉴사우스웨일스 주 시드니 시 시드니 오페라 하우스','(-33.8567844, 151.21529669999995)','보스가 성격이 안좋아요'),(4,'Sydney 뉴사우스웨일스 주 오스트레일리아','(-33.8688197, 151.20929550000005)','음식 맛이 없네요!'),(5,'Sydney 뉴사우스웨일스 주 오스트레일리아','(-33.8688197, 151.20929550000005)','집 상태가 안좋아요! 룸메이트는 깔끔해서 좋아요!'),(7,'Sydney 뉴사우스웨일스 주 오스트레일리아','(-33.8688197, 151.20929550000005)','호주는 눈이 부실 정도로 햇빛이 비쳐요!'),(8,'Sydney 뉴사우스웨일스 주 오스트레일리아','(-33.8688197, 151.20929550000005)','오늘은 비가 많이오네요.'),(9,'Sydney 뉴사우스웨일스 주 오스트레일리아','(-33.8688197, 151.20929550000005)','오늘 불꽃 놀이 축제를 많이해요! 명당자리는 하버브릿지 아래!'),(10,'Sydney 뉴사우스웨일스 주 오스트레일리아','(-33.8688197, 151.20929550000005)','하우스키핑 일 너무 힘들어요.ㅠㅠ');
/*!40000 ALTER TABLE `chitchatpub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cube_map`
--

DROP TABLE IF EXISTS `cube_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cube_map` (
  `cube_idx` int(11) NOT NULL,
  `stack_id` int(11) DEFAULT NULL,
  `pos_x` int(11) DEFAULT NULL,
  `pos_y` int(11) DEFAULT NULL,
  `pos_z` int(11) DEFAULT NULL,
  `object_id` int(11) DEFAULT NULL,
  `cube_type` int(11) DEFAULT NULL,
  `linked_id` int(11) DEFAULT NULL,
  `cube_size` int(11) DEFAULT NULL,
  `cube_axis` int(11) DEFAULT NULL,
  PRIMARY KEY (`cube_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cube_map`
--

LOCK TABLES `cube_map` WRITE;
/*!40000 ALTER TABLE `cube_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `cube_map` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamers`
--

LOCK TABLES `dreamers` WRITE;
/*!40000 ALTER TABLE `dreamers` DISABLE KEYS */;
INSERT INTO `dreamers` (`id`, `content`) VALUES (1,'<h2>jQuery insert plugin for MediumEditor</h2>\n	\n			            <p>My father’s family name being Pirrip, and my Christian name Philip, my infant tongue could make of both names nothing longer or more explicit than Pip. So, I called myself Pip, and came to be called Pip.</p><div class=\"medium-insert-images medium-insert-active\"><figure contenteditable=\"false\">\n    <img src=\"http://localhost:8888/files/header.jpg\" alt=\"\">\n        \n</figure></div>\n						<!-- \n			            <div class=\"medium-insert-images\">\n			                <figure contenteditable=\"false\">\n			                    <img src=\"./uploads/01.jpg\" alt=\"\">\n			                    <figcaption contenteditable=\"true\">So, I called myself Pip, and came to be called Pip.</figcaption>\n			                </figure>\n			            </div>\n			 			-->\n			            <p>I give Pirrip as my father’s family name, on the authority of his tombstone and my sister,—Mrs. Joe Gargery, who married the blacksmith. As I never saw my father or my mother, and never saw any likeness of either of them (for their days were long before the days of photographs), my first fancies regarding what they were like were unreasonably derived from their tombstones. The shape of the letters on my father’s, gave me an odd idea that he was a square, stout, dark man, with curly black hair. From the character and turn of the inscription, “Also Georgiana Wife of the Above,” I drew a childish conclusion that my mother was freckled and sickly. To five little stone lozenges, each about a foot and a half long, which were arranged in a neat row beside their grave, and were sacred to the memory of five little brothers of mine,—who gave up trying to get a living, exceedingly early in that universal struggle,—I am indebted for a belief I religiously entertained that they had all been born on their backs with their hands in their trousers-pockets, and had never taken them out in this state of existence.</p>\n			\n			            <p>Ours was the marsh country, down by the river, within, as the river wound, twenty miles of the sea. My first most vivid and broad impression of the identity of things seems to me to have been gained on a memorable raw afternoon towards evening. At such a time I found out for certain that this bleak place overgrown with nettles was the churchyard; and that Philip Pirrip, late of this parish, and also Georgiana wife of the above, were dead and buried; and that Alexander, Bartholomew, Abraham, Tobias, and Roger, infant children of the aforesaid, were also dead and buried; and that the dark flat wilderness beyond the churchyard, intersected with dikes and mounds and gates, with scattered cattle feeding on it, was the marshes; and that the low leaden line beyond was the river; and that the distant savage lair from which the wind was rushing was the sea; and that the small bundle of shivers growing afraid of it all and beginning to cry, was Pip.</p>\n			        \n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"left: 725px; top: 659px;\" data-active-addon=\"images\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none;\">\n            <li style=\"\"><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li style=\"display: none;\"><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>'),(2,'<h2>jQuery insert plugin for MediumEditor</h2>\n	\n			            <p>My father’s family name being Pirrip, and my Christian name Philip, my infant tongue could make of both names nothing longer or more explicit than Pip. So, I called myself Pip, and came to be called Pip.</p><div class=\"medium-insert-images medium-insert-active\"><figure contenteditable=\"false\">\n    <img src=\"http://localhost:8888/files/header.jpg\" alt=\"\">\n        \n</figure></div>\n						<!-- \n			            <div class=\"medium-insert-images\">\n			                <figure contenteditable=\"false\">\n			                    <img src=\"./uploads/01.jpg\" alt=\"\">\n			                    <figcaption contenteditable=\"true\">So, I called myself Pip, and came to be called Pip.</figcaption>\n			                </figure>\n			            </div>\n			 			-->\n			            <p>I give Pirrip as my father’s family name, on the authority of his tombstone and my sister,—Mrs. Joe Gargery, who married the blacksmith. As I never saw my father or my mother, and never saw any likeness of either of them (for their days were long before the days of photographs), my first fancies regarding what they were like were unreasonably derived from their tombstones. The shape of the letters on my father’s, gave me an odd idea that he was a square, stout, dark man, with curly black hair. From the character and turn of the inscription, “Also Georgiana Wife of the Above,” I drew a childish conclusion that my mother was freckled and sickly. To five little stone lozenges, each about a foot and a half long, which were arranged in a neat row beside their grave, and were sacred to the memory of five little brothers of mine,—who gave up trying to get a living, exceedingly early in that universal struggle,—I am indebted for a belief I religiously entertained that they had all been born on their backs with their hands in their trousers-pockets, and had never taken them out in this state of existence.</p>\n			\n			            <p>Ours was the marsh country, down by the river, within, as the river wound, twenty miles of the sea. My first most vivid and broad impression of the identity of things seems to me to have been gained on a memorable raw afternoon towards evening. At such a time I found out for certain that this bleak place overgrown with nettles was the churchyard; and that Philip Pirrip, late of this parish, and also Georgiana wife of the above, were dead and buried; and that Alexander, Bartholomew, Abraham, Tobias, and Roger, infant children of the aforesaid, were also dead and buried; and that the dark flat wilderness beyond the churchyard, intersected with dikes and mounds and gates, with scattered cattle feeding on it, was the marshes; and that the low leaden line beyond was the river; and that the distant savage lair from which the wind was rushing was the sea; and that the small bundle of shivers growing afraid of it all and beginning to cry, was Pip.</p>\n			        \n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"left: 725px; top: 659px;\" data-active-addon=\"images\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none;\">\n            <li style=\"\"><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li style=\"display: none;\"><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>'),(3,'<h2>jQuery insert plugin for MediumEditor</h2>\n	\n			            <p>My father’s family name being Pirrip, and my Christian name Philip, my infant tongue could make of both names nothing longer or more explicit than Pip. So, I called myself Pip, and came to be called Pip.</p><div class=\"medium-insert-images medium-insert-active\"><figure contenteditable=\"false\">\n    <img src=\"http://localhost:8888/files/header.jpg\" alt=\"\">\n        \n</figure></div>\n						<!-- \n			            <div class=\"medium-insert-images\">\n			                <figure contenteditable=\"false\">\n			                    <img src=\"./uploads/01.jpg\" alt=\"\">\n			                    <figcaption contenteditable=\"true\">So, I called myself Pip, and came to be called Pip.</figcaption>\n			                </figure>\n			            </div>\n			 			-->\n			            <p>I give Pirrip as my father’s family name, on the authority of his tombstone and my sister,—Mrs. Joe Gargery, who married the blacksmith. As I never saw my father or my mother, and never saw any likeness of either of them (for their days were long before the days of photographs), my first fancies regarding what they were like were unreasonably derived from their tombstones. The shape of the letters on my father’s, gave me an odd idea that he was a square, stout, dark man, with curly black hair. From the character and turn of the inscription, “Also Georgiana Wife of the Above,” I drew a childish conclusion that my mother was freckled and sickly. To five little stone lozenges, each about a foot and a half long, which were arranged in a neat row beside their grave, and were sacred to the memory of five little brothers of mine,—who gave up trying to get a living, exceedingly early in that universal struggle,—I am indebted for a belief I religiously entertained that they had all been born on their backs with their hands in their trousers-pockets, and had never taken them out in this state of existence.</p>\n			\n			            <p>Ours was the marsh country, down by the river, within, as the river wound, twenty miles of the sea. My first most vivid and broad impression of the identity of things seems to me to have been gained on a memorable raw afternoon towards evening. At such a time I found out for certain that this bleak place overgrown with nettles was the churchyard; and that Philip Pirrip, late of this parish, and also Georgiana wife of the above, were dead and buried; and that Alexander, Bartholomew, Abraham, Tobias, and Roger, infant children of the aforesaid, were also dead and buried; and that the dark flat wilderness beyond the churchyard, intersected with dikes and mounds and gates, with scattered cattle feeding on it, was the marshes; and that the low leaden line beyond was the river; and that the distant savage lair from which the wind was rushing was the sea; and that the small bundle of shivers growing afraid of it all and beginning to cry, was Pip.</p>\n			        \n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"left: 725px; top: 659px;\" data-active-addon=\"images\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none;\">\n            <li style=\"\"><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li style=\"display: none;\"><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>'),(4,'<h2>jQuery insert plugin for MediumEditor</h2>\n	\n			            <p>My father’s family name being Pirrip, and my Christian name Philip, my infant tongue could make of both names nothing longer or more explicit than Pip. So, I called myself Pip, and came to be called Pip.</p>\n						<!-- \n			            <div class=\"medium-insert-images\">\n			                <figure contenteditable=\"false\">\n			                    <img src=\"./uploads/01.jpg\" alt=\"\">\n			                    <figcaption contenteditable=\"true\">So, I called myself Pip, and came to be called Pip.</figcaption>\n			                </figure>\n			            </div>\n			 			-->\n			            <p>I give Pirrip as my father’s family name, on the authority of his tombstone and my sister,—Mrs. Joe Gargery, who married the blacksmith. As I never saw my father or my mother, and never saw any likeness of either of them (for their days were long before the days of photographs), my first fancies regarding what they were like were unreasonably derived from their tombstones. The shape of the letters on my father’s, gave me an odd idea that he was a square, stout, dark man, with curly black hair. From the character and turn of the inscription, “Also Georgiana Wife of the Above,” I drew a childish conclusion that my mother was freckled and sickly. To five little stone lozenges, each about a foot and a half long, which were arranged in a neat row beside their grave, and were sacred to the memory of five little brothers of mine,—who gave up trying to get a living, exceedingly early in that universal struggle,—I am indebted for a belief I religiously entertained that they had all been born on their backs with their hands in their trousers-pockets, and had never taken them out in this state of existence.</p>\n			\n			            <p>Ours was the marsh country, down by the river, within, as the river wound, twenty miles of the sea. My first most vivid and broad impression of the identity of things seems to me to have been gained on a memorable raw afternoon towards evening. At such a time I found out for certain that this bleak place overgrown with nettles was the churchyard; and that Philip Pirrip, late of this parish, and also Georgiana wife of the above, were dead and buried; and that Alexander, Bartholomew, Abraham, Tobias, and Roger, infant children of the aforesaid, were also dead and buried; and that the dark flat wilderness beyond the churchyard, intersected with dikes and mounds and gates, with scattered cattle feeding on it, was the marshes; and that the low leaden line beyond was the river; and that the distant savage lair from which the wind was rushing was the sea; and that the small bundle of shivers growing afraid of it all and beginning to cry, was Pip.</p>\n			        \n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>'),(5,'<h2>23232jQuery insert plugin for MediumEditor</h2>\n	\n			            <p>My father’s family name being Pirrip, and my Christian name Philip, my infant tongue could make of both names nothing longer or more explicit than Pip. So, I called myself Pip, and came to be called Pip.</p>\n						<!-- \n			            <div class=\"medium-insert-images\">\n			                <figure contenteditable=\"false\">\n			                    <img src=\"./uploads/01.jpg\" alt=\"\">\n			                    <figcaption contenteditable=\"true\">So, I called myself Pip, and came to be called Pip.</figcaption>\n			                </figure>\n			            </div>\n			 			-->\n			            <p>I give Pirrip as my father’s family name, on the authority of his tombstone and my sister,—Mrs. Joe Gargery, who married the blacksmith. As I never saw my father or my mother, and never saw any likeness of either of them (for their days were long before the days of photographs), my first fancies regarding what they were like were unreasonably derived from their tombstones. The shape of the letters on my father’s, gave me an odd idea that he was a square, stout, dark man, with curly black hair. From the character and turn of the inscription, “Also Georgiana Wife of the Above,” I drew a childish conclusion that my mother was freckled and sickly. To five little stone lozenges, each about a foot and a half long, which were arranged in a neat row beside their grave, and were sacred to the memory of five little brothers of mine,—who gave up trying to get a living, exceedingly early in that universal struggle,—I am indebted for a belief I religiously entertained that they had all been born on their backs with their hands in their trousers-pockets, and had never taken them out in this state of existence.</p>\n			\n			            <p>Ours was the marsh country, down by the river, within, as the river wound, twenty miles of the sea. My first most vivid and broad impression of the identity of things seems to me to have been gained on a memorable raw afternoon towards evening. At such a time I found out for certain that this bleak place overgrown with nettles was the churchyard; and that Philip Pirrip, late of this parish, and also Georgiana wife of the above, were dead and buried; and that Alexander, Bartholomew, Abraham, Tobias, and Roger, infant children of the aforesaid, were also dead and buried; and that the dark flat wilderness beyond the churchyard, intersected with dikes and mounds and gates, with scattered cattle feeding on it, was the marshes; and that the low leaden line beyond was the river; and that the distant savage lair from which the wind was rushing was the sea; and that the small bundle of shivers growing afraid of it all and beginning to cry, was Pip.</p>\n			        \n					<div class=\"medium-insert-buttons\" contenteditable=\"false\" style=\"display: none\">\n    <button class=\"medium-insert-buttons-show\" type=\"button\"><span>+</span></button>\n    <ul class=\"medium-insert-buttons-addons\" style=\"display: none\">\n            <li><button data-addon=\"images\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-camera\"></span></button></li>\n            <li><button data-addon=\"embeds\" data-action=\"add\" class=\"medium-insert-action\" type=\"button\"><span class=\"fa fa-youtube-play\"></span></button></li>\n    </ul>\n</div>');
/*!40000 ALTER TABLE `dreamers` ENABLE KEYS */;
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
-- Table structure for table `stack`
--

DROP TABLE IF EXISTS `stack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stack` (
  `stack_id` int(11) NOT NULL AUTO_INCREMENT,
  `stack_nm` text,
  `keep_booksf_cnt` int(11) DEFAULT NULL,
  `stack_remk` text,
  PRIMARY KEY (`stack_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stack`
--

LOCK TABLES `stack` WRITE;
/*!40000 ALTER TABLE `stack` DISABLE KEYS */;
/*!40000 ALTER TABLE `stack` ENABLE KEYS */;
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
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ysindex`
--

DROP TABLE IF EXISTS `ysindex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ysindex` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ysindex`
--

LOCK TABLES `ysindex` WRITE;
/*!40000 ALTER TABLE `ysindex` DISABLE KEYS */;
/*!40000 ALTER TABLE `ysindex` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 11:41:13

DROP TABLE IF EXISTS `dreamerscomment`;
CREATE TABLE `dreamerscomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dreamers_id` int(11) DEFAULT NULL,
  `dreamers_comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `dreamerslike`;
CREATE TABLE `dreamerslike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dreamers_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
