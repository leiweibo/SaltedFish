create database microservice_mallmanagement;
use microservice_mallmanagement;

drop table if exists `tb_order`;
create table `tb_order`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=UTF8;

INSERT INTO `tb_order` VALUES ('1', '2017-10-09 10:15:44','201709181459001', '1');
INSERT INTO `tb_order` VALUES ('2', '2017-10-24 18:22:12','201709181459008', '1');

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
    `address` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

INSERT INTO `tb_user` VALUES ('1', 'beijing', 'shitou');