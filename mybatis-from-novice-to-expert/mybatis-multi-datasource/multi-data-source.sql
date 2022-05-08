CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1:boy 2girl',
  `birth` date DEFAULT NULL,
  `tenant_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
INSERT INTO `users` VALUES (1,'xjs',1,'1998-10-01',''),(2,'Joshua',1,'1988-10-01',''),(3,'hello',1,'1990-10-01','');


 CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO `goods` VALUES (1,'Apple13','苹果13',1000),(2,'Apple14','苹果14',1000),(3,'Apple13','苹果13',1000);
