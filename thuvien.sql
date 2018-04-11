-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: thuvien
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
-- Table structure for table `hoivien`
--

DROP TABLE IF EXISTS `hoivien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hoivien` (
  `ma_hoivien` varchar(50) NOT NULL,
  `ten_hoivien` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_hoivien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoivien`
--

LOCK TABLES `hoivien` WRITE;
/*!40000 ALTER TABLE `hoivien` DISABLE KEYS */;
INSERT INTO `hoivien` VALUES ('HV001','Hishiro Xtra'),('HV002','Shirakami Kazuto'),('HV003','Shirakami Kazuma'),('HV004','Hishiro Airi'),('HV005','Hishiro Riko'),('HV006','Daiseiryu Aisha'),('HV007','Risatan Devilord Bediablos'),('HV008','Kyutamae Shouko');
/*!40000 ALTER TABLE `hoivien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaisach`
--

DROP TABLE IF EXISTS `loaisach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaisach` (
  `ma_ls` varchar(45) NOT NULL,
  `ten_ls` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ma_ls`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaisach`
--

LOCK TABLES `loaisach` WRITE;
/*!40000 ALTER TABLE `loaisach` DISABLE KEYS */;
INSERT INTO `loaisach` VALUES ('L001','Công Nghệ Thông Tin'),('L002','Khoa Học'),('L003','Văn Học'),('L004','Giải Trí'),('L005','Khác'),('L006','Ẩm Thực');
/*!40000 ALTER TABLE `loaisach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muon_tra_sach`
--

DROP TABLE IF EXISTS `muon_tra_sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `muon_tra_sach` (
  `ma_muon` int(11) NOT NULL AUTO_INCREMENT,
  `ma_hoivien` varchar(50) DEFAULT NULL,
  `ma_sach` varchar(50) DEFAULT NULL,
  `ngay_muon` datetime DEFAULT NULL,
  `ngay_tra_dk` datetime DEFAULT NULL,
  `ngay_tra_tt` datetime DEFAULT NULL,
  `tinh_trang` varchar(50) DEFAULT NULL,
  `so_ngay_tre` int(11) DEFAULT NULL,
  `tien_phat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_muon`),
  KEY `muon_hoivien_idx` (`ma_hoivien`),
  KEY `muon_sach_idx` (`ma_sach`),
  CONSTRAINT `muon_hoivien` FOREIGN KEY (`ma_hoivien`) REFERENCES `hoivien` (`ma_hoivien`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `muon_sach` FOREIGN KEY (`ma_sach`) REFERENCES `sach` (`ma_sach`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muon_tra_sach`
--

LOCK TABLES `muon_tra_sach` WRITE;
/*!40000 ALTER TABLE `muon_tra_sach` DISABLE KEYS */;
INSERT INTO `muon_tra_sach` VALUES (1,'HV001','S001','2018-02-12 00:00:00','2018-02-20 00:00:00','2018-03-22 00:00:00','trễ hạn',30,'$300,000.00'),(2,'HV001','S002','2018-02-11 00:00:00','2018-02-20 00:00:00','2018-02-19 00:00:00','đúng hạn',0,'$0.00'),(3,'HV001','S003','2018-02-10 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(4,'HV001','S004','2018-02-10 00:00:00','2018-02-20 00:00:00','2018-02-25 00:00:00','trễ hạn',5,'$50,000.00'),(5,'HV001','S001','2018-01-30 00:00:00','2018-02-10 00:00:00','2018-02-10 00:00:00','đúng hạn',0,'$0.00'),(6,'HV001','S004','2018-02-10 00:00:00','2018-02-20 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(7,'HV001','S003','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-20 00:00:00','trễ hạn',1,'$10,000.00'),(8,'HV001','S003','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-12 00:00:00','đúng hạn',0,'$0.00'),(9,'HV001','S004','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-25 00:00:00','trễ hạn',6,'$60,000.00'),(10,'HV001','S002','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(11,'HV002','S002','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-26 00:00:00','trễ hạn',7,'$70,000.00'),(12,'HV002','S001','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(13,'HV002','S003','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-12 00:00:00','đúng hạn',0,'$0.00'),(14,'HV003','S001','2018-02-12 00:00:00','2018-02-19 00:00:00','2018-02-25 00:00:00','trễ hạn',6,'$60,000.00'),(16,'HV002','S001','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-27 00:00:00','trễ hạn',5,'$50,000.00'),(18,'HV003','S003','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-03-16 00:00:00','trễ hạn',22,'$220,000.00'),(19,'HV002','S002','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(20,'HV002','S003','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(21,'HV001','S010','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(22,'HV001','S012','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(23,'HV001','S001','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-26 00:00:00','trễ hạn',4,'$40,000.00'),(24,'HV002','S011','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(25,'HV002','S002','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-25 00:00:00','trễ hạn',3,'$30,000.00'),(26,'HV003','S012','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-26 00:00:00','trễ hạn',4,'$40,000.00'),(27,'HV004','S001','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(28,'HV006','S003','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(29,'HV005','S012','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-20 00:00:00','đúng hạn',0,'$0.00'),(30,'HV004','S008','2018-02-15 00:00:00','2018-02-22 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(31,'HV006','S008','2018-02-16 00:00:00','2018-02-23 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(32,'HV001','S004','2018-02-16 00:00:00','2018-02-23 00:00:00','2018-03-22 00:00:00','trễ hạn',27,'$270,000.00'),(33,'HV003','S005','2018-02-16 00:00:00','2018-02-23 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(34,'HV005','S007','2018-02-16 00:00:00','2018-02-23 00:00:00','2018-02-16 00:00:00','đúng hạn',0,'$0.00'),(35,'HV001','S011','2017-02-19 00:00:00','2017-02-26 00:00:00','2018-03-22 00:00:00','trễ hạn',389,'$3,890,000.00'),(36,'HV007','S001','2017-02-20 00:00:00','2017-02-27 00:00:00','2018-03-22 00:00:00','trễ hạn',388,'$3,880,000.00'),(37,'HV007','S002','2018-02-20 00:00:00','2018-02-25 00:00:00','2018-03-22 00:00:00','trễ hạn',25,'$250,000.00'),(38,'HV007','S003','2017-09-09 00:00:00','2017-09-16 00:00:00','2018-03-22 00:00:00','trễ hạn',187,'$1,870,000.00'),(39,'HV007','S004','2018-03-22 00:00:00','2018-03-29 00:00:00','2018-03-22 00:00:00','đúng hạn',0,'$0.00'),(40,'HV006','S007','2018-03-22 00:00:00','2018-03-29 00:00:00','2018-03-22 00:00:00','đúng hạn',0,'$0.00'),(41,'HV006','S008','2018-03-22 00:00:00','2018-03-29 00:00:00','2018-03-22 00:00:00','đúng hạn',0,'$0.00'),(42,'HV006','S009','2018-03-22 00:00:00','2018-03-29 00:00:00','2018-03-22 00:00:00','đúng hạn',0,'$0.00'),(43,'HV005','S010','2018-03-22 00:00:00','2018-03-29 00:00:00','2018-03-24 00:00:00','đúng hạn',0,'$0.00'),(44,'HV006','S010','2018-03-24 00:00:00','2018-03-31 00:00:00','2018-03-24 00:00:00','đúng hạn',0,'$0.00'),(45,'HV007','S010','2018-03-24 00:00:00','2018-03-31 00:00:00','2018-03-24 00:00:00','đúng hạn',0,'$0.00'),(46,'HV007','S009','2018-03-24 00:00:00','2018-03-31 00:00:00','2018-03-25 00:00:00','đúng hạn',0,'$0.00'),(47,'HV007','S008','2018-04-07 00:00:00','2018-04-14 00:00:00','2018-04-07 00:00:00','đúng hạn',0,'$0.00'),(48,'HV007','S005','2018-04-11 00:00:00','2018-03-18 00:00:00','2018-04-11 00:00:00','trễ hạn',24,'$240,000.00'),(49,'HV008','S006','2018-04-11 00:00:00','2018-03-10 00:00:00','2018-04-11 00:00:00','trễ hạn',32,'$320,000.00'),(50,'HV006','S006','2018-04-11 00:00:00','2018-02-18 00:00:00','2018-04-11 00:00:00','trễ hạn',52,'$520,000.00'),(51,'HV006','S005','2018-04-11 00:00:00','2018-02-08 00:00:00','2018-04-11 00:00:00','trễ hạn',62,'$620,000.00');
/*!40000 ALTER TABLE `muon_tra_sach` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thuvien`.`muon_tra_sach_BEFORE_UPDATE` BEFORE UPDATE ON `muon_tra_sach` FOR EACH ROW
BEGIN
if (NEW.ngay_tra_tt is not NULL) then
	if (NEW.ngay_tra_tt <= NEW.ngay_tra_dk) then
			Set NEW.tinh_trang = 'đúng hạn';
		else
			Set NEW.tinh_trang = 'trễ hạn';
	end if;
    if (DATEDIFF(NEW.ngay_tra_tt, NEW.ngay_tra_dk) > 0) then
		Set New.so_ngay_tre = DATEDIFF(NEW.ngay_tra_tt, NEW.ngay_tra_dk);
	else
		Set New.so_ngay_tre = 0;
	end if;
end if;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `nhaxuatban`
--

DROP TABLE IF EXISTS `nhaxuatban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhaxuatban` (
  `ma_nxb` varchar(50) NOT NULL,
  `ten_nxb` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_nxb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhaxuatban`
--

LOCK TABLES `nhaxuatban` WRITE;
/*!40000 ALTER TABLE `nhaxuatban` DISABLE KEYS */;
INSERT INTO `nhaxuatban` VALUES ('NXB001','Nhà Xuất Bản A'),('NXB002','Nhà Xuất Bản B'),('NXB003','Nhà Xuất Bản C'),('NXB004','Nhà Xuất Bản D'),('NXB005','Nhà Xuất Bản E'),('NXB006','Nhà Xuất Bản Nhi Đồng');
/*!40000 ALTER TABLE `nhaxuatban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sach` (
  `ma_sach` varchar(50) NOT NULL,
  `tensach` varchar(50) DEFAULT NULL,
  `ma_nxb` varchar(50) DEFAULT NULL,
  `ma_ls` varchar(45) DEFAULT NULL,
  `mota` varchar(100) DEFAULT NULL,
  `ma_tg` varchar(50) DEFAULT NULL,
  `ngay_nhap` datetime DEFAULT NULL,
  PRIMARY KEY (`ma_sach`),
  KEY `ma_nxb_idx` (`ma_nxb`),
  KEY `sach_tacgia_idx` (`ma_tg`),
  KEY `sach_loaisach_idx` (`ma_ls`),
  CONSTRAINT `sach_loaisach` FOREIGN KEY (`ma_ls`) REFERENCES `loaisach` (`ma_ls`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sach_nxb` FOREIGN KEY (`ma_nxb`) REFERENCES `nhaxuatban` (`ma_nxb`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sach_tacgia` FOREIGN KEY (`ma_tg`) REFERENCES `tacgia` (`ma_tg`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES ('S001','Sách Toán','NXB001','L002','Giúp bạn học toán tốt hơn','TG001','2018-03-11 12:19:31'),('S002','Sách Vật Lý','NXB001','L002','Lý thuyết vật lý cơ bản','TG001','2018-03-11 12:24:07'),('S003','Sách Hóa Học','NXB003','L002','Làm quen với những nguyên tố hóa học','TG003','2018-03-11 12:24:07'),('S004','Sách Văn Học','NXB002','L003','Tập làm văn','TG002','2018-03-11 12:24:07'),('S005','Sách GDCD','NXB001','L005','Giáo dục công dân','TG001','2018-03-11 12:24:07'),('S006','Sách Triết','NXB003','L005','triết học','TG003','2017-05-11 12:24:07'),('S007','Sách Lập Trình Java','NXB003','L001','Căn bản về lập trình Java','TG003','2018-03-11 12:24:07'),('S008','Sách Lập Trình C#','NXB001','L001','Căn bản về lập trình C#','TG001','2017-12-11 12:24:07'),('S009','Sách Lập Trình C++','NXB002','L001','Căn bản về lập trình C++','TG002','2017-08-11 12:24:07'),('S010','Sách Cơ Sở Dữ Liệu','NXB001','L001','học về cơ sở dữ liệu','TG001','2018-03-11 12:24:07'),('S011','Sách Địa Lý','NXB001','L002','nghiên cứu về địa lý các vùng đất trên thế giới','TG001','2018-03-11 12:24:07'),('S012','Sách Lịch Sử','NXB002','L002','nghiên cứu về lịch sử nhân loại','TG002','2018-03-11 12:24:07'),('S013','Sách Dạy Nấu Ăn','NXB001','L005','dạy nấu ăn','TG001','2017-03-11 12:40:57'),('S014','Sách Cơ Học Lượng Tử','NXB001','L002','căn về cơ học lượng tử','TG001','2018-03-31 19:05:33'),('S015','One Piece Vol.1','NXB006','L004','Gomu Gomu no...','TG005','2018-03-31 19:09:36'),('S016','One Piece Vol.2','NXB006','L004','Gomu Gomu no...','TG005','2018-04-08 19:08:15');
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thuvien`.`sach_BEFORE_INSERT` BEFORE INSERT ON `sach` FOR EACH ROW
BEGIN
/*INSERT INTO thong_tin_nhap_sach(ma_sach_nhap, ten_sach_nhap, ngay_nhap_sach) 
VALUES(NEW.ma_sach, NEW.tensach, NOW());*/
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tacgia`
--

DROP TABLE IF EXISTS `tacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tacgia` (
  `ma_tg` varchar(50) NOT NULL,
  `ten_tg` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_tg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tacgia`
--

LOCK TABLES `tacgia` WRITE;
/*!40000 ALTER TABLE `tacgia` DISABLE KEYS */;
INSERT INTO `tacgia` VALUES ('TG001','Tác Giả A'),('TG002','Tác Giả B'),('TG003','Tác Giả C'),('TG004','Nguyễn Văn A'),('TG005','Oda Eiichiro');
/*!40000 ALTER TABLE `tacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taikhoan` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('dragodrex','123'),('thanhhung','456'),('tuanhung','123');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thong_tin_nhap_sach`
--

DROP TABLE IF EXISTS `thong_tin_nhap_sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thong_tin_nhap_sach` (
  `ma_nhap_sach` int(11) NOT NULL AUTO_INCREMENT,
  `ma_sach_nhap` varchar(50) DEFAULT NULL,
  `ten_sach_nhap` varchar(50) DEFAULT NULL,
  `ngay_nhap_sach` datetime DEFAULT NULL,
  PRIMARY KEY (`ma_nhap_sach`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thong_tin_nhap_sach`
--

LOCK TABLES `thong_tin_nhap_sach` WRITE;
/*!40000 ALTER TABLE `thong_tin_nhap_sach` DISABLE KEYS */;
INSERT INTO `thong_tin_nhap_sach` VALUES (1,'S010','Sách Cơ Sở Dữ Liệu','2018-02-14 12:18:21'),(2,'S011','Sách Địa Lý','2018-02-14 12:20:34'),(3,'S012','Sách Lịch Sử','2018-02-14 12:20:34'),(4,'S013','Sách Nấu Ăn','2018-03-11 12:40:57'),(5,'S014','Sách Cơ Học Lượng Tử','2018-03-31 19:05:33');
/*!40000 ALTER TABLE `thong_tin_nhap_sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'thuvien'
--
/*!50003 DROP PROCEDURE IF EXISTS `new_procedure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure`(IN ma_sach1 varchar(45))
BEGIN
if((select count(*) from thuvien.sach where ma_sach = ma_sach1) = 1) then
 select * from thuvien.sach where ma_sach = ma_sach1;
 else
 select * from thuvien.sach;
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-11 12:52:21
