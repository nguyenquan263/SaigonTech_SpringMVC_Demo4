/*
MySQL Backup
Source Server Version: 5.5.5
Source Database: demodb4
Date: 11/12/2018 08:11:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `specializations`
-- ----------------------------
DROP TABLE IF EXISTS `specializations`;
CREATE TABLE `specializations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `credit_number` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `students`
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `is_male` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `spec_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK441bknglntrwc2mj6w4qqe2ql` (`spec_id`),
  CONSTRAINT `FK441bknglntrwc2mj6w4qqe2ql` FOREIGN KEY (`spec_id`) REFERENCES `specializations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(255) NOT NULL,
  `ROLE` varchar(255) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `specializations` VALUES ('1','61','JAVA'), ('2','59','C#'), ('3','80','ANDROID'), ('4','90','PYTHON');
INSERT INTO `users` VALUES ('1','123abc','ADMIN','1','admin');
