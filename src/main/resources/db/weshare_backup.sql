CREATE DATABASE  IF NOT EXISTS `weshare` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `weshare`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: weshare
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointment_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(12) unsigned NOT NULL,
  `topic_id` int(12) unsigned NOT NULL,
  `meetup_time` bigint(20) DEFAULT NULL,
  `meetup_address` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `appointment_user_id_index` (`user_id`),
  KEY `appointment_topic_id_index` (`topic_id`),
  CONSTRAINT `appointment_topic_id_foreign` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `appointment_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like` (
  `user_a_id` int(12) unsigned NOT NULL,
  `user_b_id` int(12) unsigned NOT NULL,
  `like_type` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`user_a_id`,`user_b_id`),
  KEY `like_user_a_id_index` (`user_a_id`),
  KEY `like_user_b_id_index` (`user_b_id`),
  CONSTRAINT `like_user_a_id_foreign` FOREIGN KEY (`user_a_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `like_user_b_id_foreign` FOREIGN KEY (`user_b_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(12) unsigned NOT NULL,
  `user_id` int(12) unsigned NOT NULL,
  `payment_type` tinyint(2) DEFAULT NULL,
  `payment_account` varchar(127) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `payment_user_id_index` (`user_id`),
  CONSTRAINT `payment_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `position_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(12) unsigned NOT NULL,
  `position_company` varchar(55) DEFAULT NULL,
  `position_title` varchar(55) DEFAULT NULL,
  `position_detail` text,
  `position_deadline` bigint(20) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`position_id`),
  KEY `position_user_id_index` (`user_id`),
  KEY `position_company_index` (`position_company`),
  KEY `position_title_index` (`position_title`),
  KEY `position_deadline_index` (`position_deadline`),
  CONSTRAINT `position_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `user_id` int(12) unsigned NOT NULL,
  `first_name` varchar(63) DEFAULT NULL,
  `last_name` varchar(63) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `major` varchar(63) DEFAULT NULL,
  `summary` text,
  `contact_wechat` varchar(127) DEFAULT NULL,
  `contact_email` varchar(63) DEFAULT NULL,
  `contact_phone` varchar(15) DEFAULT NULL,
  `work_city` varchar(63) DEFAULT NULL,
  `work_state` varchar(63) DEFAULT NULL,
  `work_address` varchar(255) DEFAULT NULL,
  `work_company` varchar(63) DEFAULT NULL,
  `work_position` varchar(63) DEFAULT NULL,
  `work_year` tinyint(2) DEFAULT NULL,
  `work_refer_status` tinyint(1) DEFAULT NULL,
  `work_refer_position` varchar(1023) DEFAULT NULL,
  `available_time` varchar(511) DEFAULT NULL,
  `prefer_time` varchar(511) DEFAULT NULL,
  `prefer_address` varchar(255) DEFAULT NULL,
  `prefer_payment` varchar(511) DEFAULT NULL,
  `phone_rate` smallint(3) unsigned DEFAULT NULL,
  `meetup_rate` smallint(3) unsigned DEFAULT NULL,
  `meetup_bonus` varchar(255) DEFAULT NULL,
  `head_shot_url` varchar(511) DEFAULT NULL,
  `time_zone` varchar(3) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `profile_first_name_index` (`first_name`),
  KEY `profile_last_name_index` (`last_name`),
  KEY `profile_major_index` (`major`),
  KEY `profile_work_position_index` (`work_position`),
  KEY `profile_time_zone_index` (`time_zone`),
  FULLTEXT KEY `profile_available_time_index` (`available_time`),
  FULLTEXT KEY `profile_prefer_time_index` (`prefer_time`),
  FULLTEXT KEY `profile_prefer_address_index` (`prefer_address`),
  CONSTRAINT `profile_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schema_version`
--

DROP TABLE IF EXISTS `schema_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schema_version` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `user_id` int(12) unsigned NOT NULL,
  `device` varchar(63) NOT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`device`),
  KEY `token_user_id_index` (`user_id`),
  CONSTRAINT `token_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `topic_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(12) unsigned NOT NULL,
  `topic_title` varchar(127) DEFAULT NULL,
  `topic_detail` text,
  `topic_target` varchar(127) DEFAULT NULL,
  `topic_type` varchar(127) DEFAULT NULL,
  `topic_length` tinyint(3) DEFAULT NULL,
  `statue` tinyint(1) NOT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `topic_user_id_index` (`user_id`),
  CONSTRAINT `topic_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(63) NOT NULL,
  `username` varchar(63) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `verified` tinyint(1) NOT NULL,
  `access_level` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `last_login` bigint(20) NOT NULL,
  `time_created` bigint(20) NOT NULL,
  `time_updated` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email_unique` (`email`),
  UNIQUE KEY `user_username_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-25 23:10:01
