CREATE DATABASE IF NOT EXISTS `transport` ;
USE `transport`;



CREATE TABLE IF NOT EXISTS `bus` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `from` varchar(200) NOT NULL,
  `to` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=latin1;



INSERT INTO `bus` (`id`, `from`, `to`) VALUES
	(101, 'mumbai', 'bangalore'),
	(102, 'bangalore', 'mumbai');