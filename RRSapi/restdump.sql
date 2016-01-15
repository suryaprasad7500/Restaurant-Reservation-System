-- MySQL dump 10.13  Distrib 5.6.12, for Win64 (x86_64)
--
-- Host: localhost    Database: rrs
-- ------------------------------------------------------
-- Server version	5.6.12-log

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `FIRST_NAME` varchar(100) NOT NULL,
  `LAST_NAME` varchar(100) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PHONE` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  PRIMARY KEY (`EMAIL`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('Bruce','Wayne','bruce@wayne.com','8826851234','rachel'),('Jack','Sparrow','jack@pearl.com','5885123456','blackpearl');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owner` (
  `REST_NAME` varchar(100) NOT NULL,
  `PHONE` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `LOCATION` varchar(200) DEFAULT NULL,
  `OPEN_DAYS` varchar(45) DEFAULT NULL,
  `OPEN_TIME` varchar(45) DEFAULT NULL,
  `AUTO_ASSIGN` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`REST_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES ('The Kitchen','4695006875','kitchen@hell.com','gordon','New York','MTWRFSU','16:00 - 23:00',0);
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `CONF_NUMBER` varchar(20) NOT NULL,
  `SIZE` int(11) NOT NULL,
  `TIME` varchar(10) NOT NULL,
  `CUSTOMER_NAME` varchar(45) NOT NULL,
  `DATE` date NOT NULL,
  `TABLE_NO` int(11) DEFAULT NULL,
  PRIMARY KEY (`CONF_NUMBER`),
  UNIQUE KEY `CONF_NUMBER_UNIQUE` (`CONF_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('1001',2,'18:00','Bruce Wayne','2016-10-03',23),('1002',16,'21:00','Jack Sparrow','2016-12-13',13);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seating`
--

DROP TABLE IF EXISTS `seating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seating` (
  `TABLE_NUMBER` int(11) NOT NULL,
  `SIZE` int(11) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  `BOOKED_SINCE` date DEFAULT NULL,
  `CONF_NUMBER` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`TABLE_NUMBER`),
  UNIQUE KEY `TABLE_NUMBER_UNIQUE` (`TABLE_NUMBER`),
  UNIQUE KEY `CONF_NUMBER_UNIQUE` (`CONF_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seating`
--

LOCK TABLES `seating` WRITE;
/*!40000 ALTER TABLE `seating` DISABLE KEYS */;
INSERT INTO `seating` VALUES (13,16,'Confirmed','2016-10-01','1001'),(23,2,'Confirmed','2016-10-02','1002');
/*!40000 ALTER TABLE `seating` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-14 20:19:03
