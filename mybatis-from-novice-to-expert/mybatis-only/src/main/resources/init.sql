CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1:boy 2girl',
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'hello',0,'1998-10-01'),(2,'world',0,'1998-10-02'),(3,'tom',1,'1998-10-03'),(4,'jerry',1,'1998-10-04');
UNLOCK TABLES;