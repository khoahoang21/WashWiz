-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: exe202
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`),
  CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'khhg',4),(2,'District 9, Ho Chi Minh City',2),(3,'District 9, Ho Chi Minh City',7),(4,'District 9, Ho Chi Minh City',5);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `total` double NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqhq5aolak9ku5x5mx11cpjad9` (`user_id`),
  CONSTRAINT `FKqhq5aolak9ku5x5mx11cpjad9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'2024-06-10',1,2410,2),(2,'2024-06-12',1,660,2),(3,'2024-06-12',1,660,2),(4,'2024-06-12',1,660,2),(5,'2024-06-12',1,1320,2),(6,'2024-06-12',1,660,2),(7,'2024-06-12',1,660,2),(8,'2024-06-12',1,660,2),(9,'2024-06-12',1,660,2),(10,'2024-06-12',1,660,2),(11,'2024-06-12',1,660,2),(12,'2024-06-12',1,2640,2),(13,'2024-06-14',1,545,2),(14,'2024-06-14',1,545,2),(15,'2024-06-14',1,545,2),(16,'2024-06-14',1,545,2),(17,'2024-06-14',1,2955,2),(18,'2024-06-14',1,2640,2),(19,'2024-06-15',1,660,2),(20,'2024-06-15',1,660,2),(21,'2024-06-15',1,660,2),(22,'2024-06-15',1,660,2),(23,'2024-06-15',1,660,2),(24,'2024-06-15',1,660,2),(25,'2024-06-15',1,660,2),(26,'2024-06-15',1,660,2),(27,'2024-06-15',1,1865,2),(28,'2024-06-15',0,1865,2),(29,'2024-06-15',0,1865,2),(30,'2024-06-15',0,1320,2),(31,'2024-06-15',0,1205,2),(32,'2024-06-15',0,660,2),(33,'2024-06-15',0,1320,2),(34,'2024-06-15',0,1205,2),(35,'2024-06-15',1,2640,2),(36,'2024-06-15',1,660,2),(37,'2024-06-15',1,1320,2),(38,'2024-06-15',1,1865,2),(39,'2024-06-15',1,1320,2),(40,'2024-06-15',1,660,2),(41,'2024-06-15',1,660,2),(42,'2024-06-15',1,660,2),(43,'2024-06-16',1,660,2),(44,'2024-06-16',1,4275,2),(45,'2024-06-16',1,545,2),(46,'2024-06-16',1,660,2),(47,'2024-06-16',1,545,7),(48,'2024-06-16',1,1980,2),(49,'2024-06-23',1,600,2),(57,'2024-07-07',1,400,2),(58,'2024-07-07',1,100,2),(59,'2024-07-07',1,100,5),(60,'2024-07-07',1,444,2),(61,'2024-07-18',1,444,2),(62,'2024-07-30',0,35000,2),(63,'2024-07-30',0,35000,2),(64,'2024-07-30',0,40000,2),(65,'2024-07-30',0,40000,2),(66,'2024-07-30',0,35000,2),(67,'2024-07-30',0,40000,2),(68,'2024-07-30',0,35000,2);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_detail`
--

DROP TABLE IF EXISTS `bill_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_detail` (
  `bill_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`bill_id`,`product_id`),
  KEY `FKe7fmo7042u349ftue4g4oeiuy` (`product_id`),
  CONSTRAINT `FKe7fmo7042u349ftue4g4oeiuy` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKeolgwyayei3o80bb7rj7t207q` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_detail`
--

LOCK TABLES `bill_detail` WRITE;
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
INSERT INTO `bill_detail` VALUES (1,2,2),(1,3,2),(2,1,1),(3,2,1),(4,2,1),(5,2,2),(6,2,1),(7,2,1),(8,2,1),(9,1,1),(10,2,1),(11,2,1),(12,2,4),(13,3,1),(14,3,1),(15,3,1),(16,3,1),(17,1,1),(17,2,1),(17,3,3),(18,1,1),(18,2,3),(19,2,1),(20,1,1),(21,2,1),(22,1,1),(23,1,1),(24,2,1),(25,1,1),(26,2,1),(27,1,1),(27,2,1),(27,3,1),(28,1,1),(28,2,1),(28,3,1),(29,1,1),(29,2,1),(29,3,1),(30,1,1),(30,2,1),(31,2,1),(31,3,1),(32,1,1),(33,1,1),(33,2,1),(34,2,1),(34,3,1),(35,1,2),(36,1,1),(37,1,1),(37,2,1),(38,1,1),(38,2,1),(38,3,1),(39,1,1),(39,2,1),(40,1,1),(41,1,1),(42,1,1),(43,2,1),(44,2,4),(44,3,3),(45,3,1),(46,2,1),(47,3,1),(48,1,3),(49,4,3),(57,4,1),(57,5,3),(58,4,1),(59,5,1),(62,5,1),(63,3,1),(64,4,1),(65,4,1),(66,3,1),(67,4,1),(68,3,1);
/*!40000 ALTER TABLE `bill_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bank` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl4gbym62l738id056y12rt6q6` (`user_id`),
  CONSTRAINT `FKl4gbym62l738id056y12rt6q6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'VCB','34059345',4),(2,'TP','8345089543',4),(3,'SAC','34583405',4),(4,'TP','2394307420934',4);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,' Giặt Quần Áo'),(2,'Giặt Phụ Kiện'),(3,'Dịch Vụ Khẩn Cấp'),(4,'Nước Xả Vải');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_info`
--

DROP TABLE IF EXISTS `customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bithday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `linkavt` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKos8a39a6glhujvhfawobdii81` (`user_id`),
  CONSTRAINT `FKos8a39a6glhujvhfawobdii81` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_info`
--

LOCK TABLES `customer_info` WRITE;
/*!40000 ALTER TABLE `customer_info` DISABLE KEYS */;
INSERT INTO `customer_info` VALUES (1,NULL,'khoahdse150514@fpt.edu.vn','Đăng Khoa Hoàng',NULL,'0364756680',1),(2,'2024-06-15','khoahdse150514@fpt.edu.vn','Đăng Khoa Hoàng','7bac573ed5874c7e94771942082d500e.jpg','0364756680',2),(3,'2024-06-09','khoahdse150514@fpt.edu.vn','Đăng Khoa Hoàng','bddfd186a3ad4b78b1826d03722ca8f8.jpg','0364756680',3),(4,'2024-06-09','khoahdse150514@fpt.edu.vn','hieu','eec37cf70c014dc5855dc40228e33645.jpg','0364756680',4),(5,NULL,'ggdfdfd@fpt.com','fgggg',NULL,'0878877755',7);
/*!40000 ALTER TABLE `customer_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `thumbnail_photo` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,2,'Giặt Ủi',35000,20,'1c322bcbc0404fcea130877c7965200b.jpg',1),(2,2,'Giặt sấy ',30000,20,'1_1_2.jpg',1),(3,2,'Vệ sinh giày',35000,20,'da36e8cf7a8e422db7b784b8a4c3fb54.jpg',2),(4,4,'Giặt Thú Bông',40000,100,'3fb959d64abf4651b99cd6f2e38cb241.jpg',2),(5,3,'Giặt Cấp Tốc',35000,100,'762964f5bcbe47ce9a8f5f133004ddbf.jpg',3),(7,2,'Nước Xả Vải',0,100,'4aa329d5be3e4d3d9a0c41bac917e51b.jpg',4);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKilxoi77ctyin6jn9robktb16c` (`product_id`),
  CONSTRAINT `FKilxoi77ctyin6jn9robktb16c` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6oo0cvcdtb6qmwsga468uuukk` (`product_id`),
  CONSTRAINT `FK6oo0cvcdtb6qmwsga468uuukk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pass_word` varchar(255) DEFAULT NULL,
  `role` int NOT NULL,
  `status` int NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'123',1,1,'khoahd'),(2,'1',1,1,'khoacctv'),(3,'2',1,1,'vctv'),(4,'2',1,1,'hieuthu2'),(5,'admin',3,1,'admin'),(6,'seller',2,1,'seller'),(7,'1',1,1,'vtv1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-05 15:08:26
