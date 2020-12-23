/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.40 : Database - ssm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssm`;

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `nickName` varchar(25) DEFAULT NULL COMMENT '昵称',
  `phoneNum` varchar(25) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `member` */

insert  into `member`(`id`,`name`,`nickName`,`phoneNum`,`email`) values (1,'张三','小三','1388888888','zhangsan@QQ.com'),(2,'李四','小四','1554457785','12362517@qq.com');

/*Table structure for table `order_traveller` */

DROP TABLE IF EXISTS `order_traveller`;

CREATE TABLE `order_traveller` (
  `orderId` varchar(32) DEFAULT NULL,
  `travellerId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_traveller` */

insert  into `order_traveller`(`orderId`,`travellerId`) values ('1','1'),('2','2'),('3','1'),('8','1'),('9','2');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(20) NOT NULL,
  `orderTime` timestamp NULL DEFAULT NULL,
  `peopleCount` int(11) DEFAULT NULL,
  `orderDesc` varchar(500) DEFAULT NULL,
  `payType` int(11) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `memberId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_o_p` (`productId`) USING BTREE,
  KEY `fk_o_m` (`memberId`) USING BTREE,
  CONSTRAINT `fk_o_m` FOREIGN KEY (`memberId`) REFERENCES `member` (`id`),
  CONSTRAINT `fk_o_p` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `orders` */

insert  into `orders`(`id`,`orderNum`,`orderTime`,`peopleCount`,`orderDesc`,`payType`,`orderStatus`,`productId`,`memberId`) values (1,'12345','2018-02-03 00:00:00',2,'没什么',0,1,1,1),(2,'12346','2020-04-15 11:15:17',2,'没什么',0,1,2,2),(3,'12347','2018-02-03 00:00:00',5,'免费游玩，想去',0,1,3,2),(5,'12349','2020-08-15 08:37:35',4,'好吃好玩',0,1,3,1);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23532 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `permission` */

insert  into `permission`(`id`,`permissionName`,`url`) values (107,'角色管理权限','/role/findAll.do'),(1943,'资源权限管理','/permission/findAll.do'),(23527,'用户管理权限','/user/findAll.do'),(23528,'商品管理权限','/product/findAll.do'),(23529,'日志管理权限','/sysLog/findAll.do'),(23531,'订单管理权限','orders/findAll.do');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productNum` varchar(50) DEFAULT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  `DepartureTime` date DEFAULT NULL,
  `productPrice` double(11,0) DEFAULT NULL,
  `productDesc` varchar(250) DEFAULT NULL,
  `productStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `product` */

insert  into `product`(`id`,`productNum`,`productName`,`cityName`,`DepartureTime`,`productPrice`,`productDesc`,`productStatus`) values (1,'001','云南一日游','云南','2020-04-07',2100,'云南欢迎你',0),(2,'002','昆明三日游','昆明','2020-06-06',1800,'昆明欢迎你',1),(3,'003','上海一日游','上海','2020-05-08',3800,'魔都欢迎你',1),(4,'004','北京三日游','北京','2020-05-09',5800,'北京我来了',1),(5,'005','深圳七日游','昆明','2020-04-07',18000,'深圳欢迎你',1),(6,'006','昭通一日游','昭通','2020-05-08',1200,'昭通大山包',0),(7,'007','丽江一日游','昆明','2020-06-04',1500,'丽江古镇',0),(10,'008','老长沙臭豆腐','长沙','1920-05-05',25,'好处不上火！！！',1),(19,'13','13','13','2020-12-13',13,'31',1),(20,'009','老长沙臭豆腐乳','长沙','2020-07-09',51,'想吃',1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `roleDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `role` */

insert  into `role`(`id`,`roleName`,`roleDesc`) values (1,'ADMIN','系统管理员'),(2,'USER','用户'),(3,'King','总统权限(无视一切防御)'),(4,'test','test'),(5,'5','5');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `permissionId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`permissionId`,`roleId`) USING BTREE,
  KEY `r_id` (`roleId`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `role_permission` */

insert  into `role_permission`(`permissionId`,`roleId`) values (107,1),(1943,1),(23527,1),(23528,1),(23531,1),(23528,2),(23531,2),(107,3),(1943,3),(23527,3),(23528,3),(23529,3),(23531,3),(23527,4),(23528,4),(23529,4);

/*Table structure for table `syslog` */

DROP TABLE IF EXISTS `syslog`;

CREATE TABLE `syslog` (
  `id` int(70) NOT NULL AUTO_INCREMENT,
  `visitTime` datetime DEFAULT NULL COMMENT '访问时间',
  `username` varchar(50) DEFAULT NULL COMMENT '操作者用户名',
  `ip` varchar(40) DEFAULT NULL COMMENT '访问ip',
  `url` varchar(40) DEFAULT NULL COMMENT '访问资源url',
  `executionTime` int(11) DEFAULT NULL COMMENT '执行时长',
  `method` varchar(255) DEFAULT NULL COMMENT '访问方法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=617 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `syslog` */

insert  into `syslog`(`id`,`visitTime`,`username`,`ip`,`url`,`executionTime`,`method`) values (473,'2020-12-15 15:38:09','root','0:0:0:0:0:0:0:1','/role/findAll.do',921,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(474,'2020-12-15 16:29:44','root','0:0:0:0:0:0:0:1','/',175,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(475,'2020-12-15 16:30:00','root','0:0:0:0:0:0:0:1','/logout.do',0,'[类名] com.ldf.demo.controller.LoginController[方法名] logout'),(476,'2020-12-15 16:30:00','root','0:0:0:0:0:0:0:1','/',0,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(477,'2020-12-15 16:30:04','root','0:0:0:0:0:0:0:1','/login.do',2,'[类名] com.ldf.demo.controller.LoginController[方法名] postLogin'),(478,'2020-12-15 16:35:25',NULL,'0:0:0:0:0:0:0:1','/',5,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(479,'2020-12-15 16:35:30',NULL,'0:0:0:0:0:0:0:1','/login.do',16,'[类名] com.ldf.demo.controller.LoginController[方法名] postLogin'),(480,'2020-12-15 16:55:41',NULL,'0:0:0:0:0:0:0:1','/',4,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(481,'2020-12-15 16:56:27',NULL,'0:0:0:0:0:0:0:1','/login.do',17,'[类名] com.ldf.demo.controller.LoginController[方法名] postLogin'),(482,'2020-12-15 19:24:28',NULL,'0:0:0:0:0:0:0:1','/',8,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(483,'2020-12-15 19:33:39',NULL,'0:0:0:0:0:0:0:1','/',5,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(484,'2020-12-15 19:39:42',NULL,'0:0:0:0:0:0:0:1','/',6,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(485,'2020-12-15 19:39:55',NULL,'0:0:0:0:0:0:0:1','/',1,'[类名] com.ldf.demo.controller.LoginController[方法名] login'),(486,'2020-12-15 21:57:21',NULL,'0:0:0:0:0:0:0:1','/product/findAll.do',1320,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(487,'2020-12-15 22:09:07',NULL,'0:0:0:0:0:0:0:1','/product/findAll.do',51,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(488,'2020-12-15 22:16:51',NULL,'0:0:0:0:0:0:0:1','/orders/findAll.do',351,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(489,'2020-12-15 22:16:57',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',24,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(490,'2020-12-15 22:20:27',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',215,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(491,'2020-12-15 22:30:44',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',21,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(492,'2020-12-15 22:50:05',NULL,'0:0:0:0:0:0:0:1','/orders/findAll.do',16,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(493,'2020-12-15 22:50:07',NULL,'0:0:0:0:0:0:0:1','/product/findAll.do',13,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(494,'2020-12-15 22:50:09',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',9,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(495,'2020-12-15 22:50:11',NULL,'0:0:0:0:0:0:0:1','/role/findAll.do',9,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(496,'2020-12-15 22:50:14',NULL,'0:0:0:0:0:0:0:1','/permission/findAll.do',48,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(497,'2020-12-15 22:50:51',NULL,'0:0:0:0:0:0:0:1','/role/findAll.do',3,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(498,'2020-12-16 08:37:38',NULL,'0:0:0:0:0:0:0:1','/product/findAll.do',228,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(499,'2020-12-16 08:37:41',NULL,'0:0:0:0:0:0:0:1','/orders/findAll.do',13,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(500,'2020-12-16 08:37:45',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',14,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(501,'2020-12-16 08:37:48',NULL,'0:0:0:0:0:0:0:1','/role/findAll.do',9,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(502,'2020-12-16 08:37:53',NULL,'0:0:0:0:0:0:0:1','/permission/findAll.do',7,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(503,'2020-12-16 08:55:54',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',443,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(504,'2020-12-16 10:06:04',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',1754,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(505,'2020-12-16 10:06:13',NULL,'0:0:0:0:0:0:0:1','/permission/findAll.do',12,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(506,'2020-12-16 10:06:15',NULL,'0:0:0:0:0:0:0:1','/product/findAll.do',14,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(507,'2020-12-16 10:06:18',NULL,'0:0:0:0:0:0:0:1','/orders/findAll.do',14,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(508,'2020-12-16 10:06:22',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',4,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(509,'2020-12-16 20:11:20',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',59,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(510,'2020-12-16 20:11:31',NULL,'0:0:0:0:0:0:0:1','/permission/findAll.do',50,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(511,'2020-12-16 20:11:34',NULL,'0:0:0:0:0:0:0:1','/product/findAll.do',35,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(512,'2020-12-16 20:11:43',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',5,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(513,'2020-12-16 20:45:53',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',47,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(514,'2020-12-16 20:45:56',NULL,'0:0:0:0:0:0:0:1','/role/findAll.do',10,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(515,'2020-12-16 20:47:02',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',7,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(516,'2020-12-16 20:47:04',NULL,'0:0:0:0:0:0:0:1','/permission/findAll.do',8,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(517,'2020-12-16 20:49:12',NULL,'0:0:0:0:0:0:0:1','/permission/findById.do',559,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(518,'2020-12-16 20:49:35',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',4,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(519,'2020-12-16 20:49:46',NULL,'0:0:0:0:0:0:0:1','/product/findAll.do',23,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(520,'2020-12-16 20:49:48',NULL,'0:0:0:0:0:0:0:1','/orders/findAll.do',410,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(521,'2020-12-16 20:49:51',NULL,'0:0:0:0:0:0:0:1','/user/findAll.do',4,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(522,'2020-12-16 20:49:53',NULL,'0:0:0:0:0:0:0:1','/role/findAll.do',3,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(523,'2020-12-16 20:55:20',NULL,'0:0:0:0:0:0:0:1','/role/findAll.do',28,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(524,'2020-12-16 21:04:04','root','0:0:0:0:0:0:0:1','/user/findAll.do',10,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(525,'2020-12-16 21:04:07','root','0:0:0:0:0:0:0:1','/user/findAll.do',4,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(526,'2020-12-16 21:04:29','root','0:0:0:0:0:0:0:1','/product/findAll.do',14,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(527,'2020-12-16 21:04:59','root','0:0:0:0:0:0:0:1','/permission/findAll.do',5,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(528,'2020-12-16 21:09:44','root','0:0:0:0:0:0:0:1','/user/findAll.do',43,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(529,'2020-12-16 21:09:51','user','0:0:0:0:0:0:0:1','/user/findById.do',55,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findById'),(530,'2020-12-16 21:10:03','user','0:0:0:0:0:0:0:1','/user/findAll.do',6,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(531,'2020-12-16 21:10:10','user','0:0:0:0:0:0:0:1','/role/findAll.do',11,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(532,'2020-12-16 21:10:14','user','0:0:0:0:0:0:0:1','/role/findById.do',6,'[类名] com.ldf.demo.controller.RoleController[方法名] findById'),(533,'2020-12-16 21:10:26','user','0:0:0:0:0:0:0:1','/role/findAll.do',4,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(534,'2020-12-16 21:10:30','user','0:0:0:0:0:0:0:1','/role/findById.do',5,'[类名] com.ldf.demo.controller.RoleController[方法名] findById'),(535,'2020-12-16 21:10:39','user','0:0:0:0:0:0:0:1','/role/findAll.do',3,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(536,'2020-12-16 21:10:42','user','0:0:0:0:0:0:0:1','/role/findRoleByIdAndNotPermission.do',4,'[类名] com.ldf.demo.controller.RoleController[方法名] findRoleByIdAndNotPermission'),(537,'2020-12-16 21:10:52','user','0:0:0:0:0:0:0:1','/orders/findAll.do',12,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(538,'2020-12-16 21:10:56','user','0:0:0:0:0:0:0:1','/permission/findAll.do',6,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(539,'2020-12-16 21:11:48','user','0:0:0:0:0:0:0:1','/permission/findAll.do',3,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(540,'2020-12-16 21:11:52','user','0:0:0:0:0:0:0:1','/role/findAll.do',5,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(541,'2020-12-16 21:11:55','user','0:0:0:0:0:0:0:1','/role/findRoleByIdAndNotPermission.do',3,'[类名] com.ldf.demo.controller.RoleController[方法名] findRoleByIdAndNotPermission'),(542,'2020-12-16 21:12:02','user','0:0:0:0:0:0:0:1','/role/findAll.do',4,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(543,'2020-12-16 21:12:05','user','0:0:0:0:0:0:0:1','/role/findRoleByIdAndNotPermission.do',4,'[类名] com.ldf.demo.controller.RoleController[方法名] findRoleByIdAndNotPermission'),(544,'2020-12-16 21:13:33','admin','0:0:0:0:0:0:0:1','/role/findAll.do',8,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(545,'2020-12-16 21:13:35','admin','0:0:0:0:0:0:0:1','/user/findAll.do',7,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(546,'2020-12-16 21:13:37','admin','0:0:0:0:0:0:0:1','/orders/findAll.do',7,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(547,'2020-12-16 21:13:38','admin','0:0:0:0:0:0:0:1','/product/findAll.do',10,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(548,'2020-12-16 21:14:05','user','0:0:0:0:0:0:0:1','/product/findAll.do',5,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(549,'2020-12-16 21:14:07','user','0:0:0:0:0:0:0:1','/orders/findAll.do',4,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(550,'2020-12-16 21:14:48','admin','0:0:0:0:0:0:0:1','/orders/findAll.do',5,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(551,'2020-12-16 21:14:52','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',4,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(552,'2020-12-16 21:14:56','admin','0:0:0:0:0:0:0:1','/permission/permission-add.do',0,'[类名] com.ldf.demo.controller.PermissionController[方法名] permissionadd'),(553,'2020-12-16 21:15:25','admin','0:0:0:0:0:0:0:1','/permission/save.do',101,'[类名] com.ldf.demo.controller.PermissionController[方法名] save'),(554,'2020-12-16 21:15:25','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',2,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(555,'2020-12-16 21:15:36','admin','0:0:0:0:0:0:0:1','/permission/findById.do',8,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(556,'2020-12-16 21:22:37','admin','0:0:0:0:0:0:0:1','/permission/findById.do',17,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(557,'2020-12-16 21:22:48','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',2,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(558,'2020-12-16 21:22:51','admin','0:0:0:0:0:0:0:1','/permission/findById.do',6,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(559,'2020-12-16 21:22:59','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',2,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(560,'2020-12-16 21:23:01','admin','0:0:0:0:0:0:0:1','/permission/findById.do',6,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(561,'2020-12-16 21:23:09','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',1,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(562,'2020-12-16 21:23:12','admin','0:0:0:0:0:0:0:1','/permission/findById.do',6,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(563,'2020-12-16 21:23:45','admin','0:0:0:0:0:0:0:1','/permission/findById.do',5,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(564,'2020-12-16 22:10:12','root','0:0:0:0:0:0:0:1','/permission/findAll.do',10,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(565,'2020-12-16 22:10:16','root','0:0:0:0:0:0:0:1','/permission/findById.do',235,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(566,'2020-12-16 22:12:30','root','0:0:0:0:0:0:0:1','/orders/findAll.do',269,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(567,'2020-12-16 22:12:32','root','0:0:0:0:0:0:0:1','/orders/findById.do',172,'[类名] com.ldf.demo.controller.OrderController[方法名] findById'),(568,'2020-12-16 22:12:36','root','0:0:0:0:0:0:0:1','/product/findAll.do',13,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(569,'2020-12-16 22:12:38','root','0:0:0:0:0:0:0:1','/product/findById.do',83,'[类名] com.ldf.demo.controller.ProductController[方法名] findById'),(570,'2020-12-16 22:12:41','root','0:0:0:0:0:0:0:1','/user/findAll.do',32,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(571,'2020-12-16 22:12:42','admin','0:0:0:0:0:0:0:1','/user/findById.do',15,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findById'),(572,'2020-12-16 22:16:42','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',11,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(573,'2020-12-16 22:16:45','admin','0:0:0:0:0:0:0:1','/permission/findById.do',21,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(574,'2020-12-16 22:16:52','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',2,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(575,'2020-12-16 22:16:54','admin','0:0:0:0:0:0:0:1','/permission/findById.do',7,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(576,'2020-12-16 22:18:48','admin','0:0:0:0:0:0:0:1','/permission/findById.do',96,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(577,'2020-12-16 22:22:06','root','0:0:0:0:0:0:0:1','/permission/findAll.do',4,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(578,'2020-12-16 22:22:08','root','0:0:0:0:0:0:0:1','/permission/findById.do',8,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(579,'2020-12-16 22:27:34','root','0:0:0:0:0:0:0:1','/permission/findById.do',101,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(580,'2020-12-16 22:33:18','root','0:0:0:0:0:0:0:1','/permission/findAll.do',3,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(581,'2020-12-16 22:33:20','root','0:0:0:0:0:0:0:1','/permission/findById.do',8,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(582,'2020-12-16 22:37:20','root','0:0:0:0:0:0:0:1','/permission/findAll.do',3,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(583,'2020-12-16 22:37:22','root','0:0:0:0:0:0:0:1','/permission/findById.do',8,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(584,'2020-12-16 22:37:31','root','0:0:0:0:0:0:0:1','/permission/addRoleByPermissionId.do',111,'[类名] com.ldf.demo.controller.PermissionController[方法名] addRoleByPermissionId'),(585,'2020-12-16 22:37:31','root','0:0:0:0:0:0:0:1','/permission/findById.do',8,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(586,'2020-12-16 22:37:34','root','0:0:0:0:0:0:0:1','/permission/addRoleByPermissionId.do',39,'[类名] com.ldf.demo.controller.PermissionController[方法名] addRoleByPermissionId'),(587,'2020-12-16 22:37:34','root','0:0:0:0:0:0:0:1','/permission/findById.do',6,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(588,'2020-12-16 22:37:38','root','0:0:0:0:0:0:0:1','/permission/addRoleByPermissionId.do',80,'[类名] com.ldf.demo.controller.PermissionController[方法名] addRoleByPermissionId'),(589,'2020-12-16 22:37:38','root','0:0:0:0:0:0:0:1','/permission/findById.do',5,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(590,'2020-12-16 22:39:12','root','0:0:0:0:0:0:0:1','/orders/findAll.do',50,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(591,'2020-12-16 22:39:17','root','0:0:0:0:0:0:0:1','/permission/findAll.do',6,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(592,'2020-12-16 22:43:05','user','0:0:0:0:0:0:0:1','/orders/findAll.do',52,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(593,'2020-12-16 22:43:13','user','0:0:0:0:0:0:0:1','/product/findAll.do',27,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(594,'2020-12-16 22:43:15','user','0:0:0:0:0:0:0:1','/orders/findAll.do',6,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(595,'2020-12-16 22:43:18','user','0:0:0:0:0:0:0:1','/orders/findById.do',6,'[类名] com.ldf.demo.controller.OrderController[方法名] findById'),(596,'2020-12-16 22:43:34','admin','0:0:0:0:0:0:0:1','/orders/findAll.do',5,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(597,'2020-12-16 22:43:35','admin','0:0:0:0:0:0:0:1','/product/findAll.do',5,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(598,'2020-12-16 22:43:39','admin','0:0:0:0:0:0:0:1','/user/findAll.do',6,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(599,'2020-12-16 22:43:50','admin','0:0:0:0:0:0:0:1','/user/findAll.do',4,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(600,'2020-12-16 22:48:56','admin','0:0:0:0:0:0:0:1','/user/findAll.do',45,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(601,'2020-12-16 22:49:17','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',7,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(602,'2020-12-16 22:49:28','admin','0:0:0:0:0:0:0:1','/permission/findById.do',31,'[类名] com.ldf.demo.controller.PermissionController[方法名] findById'),(603,'2020-12-16 22:49:41','admin','0:0:0:0:0:0:0:1','/product/findAll.do',11,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(604,'2020-12-16 22:49:43','admin','0:0:0:0:0:0:0:1','/orders/findAll.do',13,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(605,'2020-12-16 22:49:46','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',2,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(606,'2020-12-16 22:50:26','user','0:0:0:0:0:0:0:1','/product/findAll.do',8,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(607,'2020-12-16 22:50:29','user','0:0:0:0:0:0:0:1','/orders/findAll.do',4,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(608,'2020-12-16 22:52:15','user','0:0:0:0:0:0:0:1','/product/findAll.do',46,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(609,'2020-12-16 22:52:18','user','0:0:0:0:0:0:0:1','/orders/findAll.do',15,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(610,'2020-12-16 22:52:32','admin','0:0:0:0:0:0:0:1','/permission/findAll.do',5,'[类名] com.ldf.demo.controller.PermissionController[方法名] findAll'),(611,'2020-12-16 22:52:36','admin','0:0:0:0:0:0:0:1','/role/findAll.do',8,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(612,'2020-12-16 22:52:39','admin','0:0:0:0:0:0:0:1','/user/findAll.do',8,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll'),(613,'2020-12-16 22:52:44','admin','0:0:0:0:0:0:0:1','/orders/findAll.do',6,'[类名] com.ldf.demo.controller.OrderController[方法名] findAll'),(614,'2020-12-16 22:52:47','admin','0:0:0:0:0:0:0:1','/product/findAll.do',7,'[类名] com.ldf.demo.controller.ProductController[方法名] findAll'),(615,'2020-12-16 22:53:06','admin','0:0:0:0:0:0:0:1','/role/findAll.do',3,'[类名] com.ldf.demo.controller.RoleController[方法名] findAll'),(616,'2020-12-16 22:53:08','admin','0:0:0:0:0:0:0:1','/user/findAll.do',7,'[类名] com.ldf.demo.controller.UserInfoController[方法名] findAll');

/*Table structure for table `traveller` */

DROP TABLE IF EXISTS `traveller`;

CREATE TABLE `traveller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `credentialsType` int(11) DEFAULT NULL COMMENT '证件类型 0身份证 1护照 2军官证',
  `credentialsNum` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `travellerType` int(11) DEFAULT NULL COMMENT '旅客类型(人群) 0 成人 1 儿童',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `traveller` */

insert  into `traveller`(`id`,`name`,`sex`,`phoneNum`,`credentialsType`,`credentialsNum`,`travellerType`) values (1,'赵龙','男','13888888888',0,'123456789009876543',0),(2,'天猫','女','14516455587',0,'154563554425887745',0);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态0 未开启 1 开启',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20620 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`username`,`password`,`phoneNum`,`status`) values (1,'a@qq.com','admin','YWRtaW4=','13888888888',1),(2,'adfa','tom','$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe','546514684',1),(3,'1847481@QQ.com','wBekvam','$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe','15752250992',1),(4,'11948939@qq.com','等灯','$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe','15752250992',0),(5,'a@qq.com','user','dXNlcg==','54154151',1),(6,'a@qq.com','赵龙','$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe','13888888888',1),(6862,'11919@qq.com','hz','$2a$10$mEKoiccVd9lmBJh7czLgy.3bIzaGLiUmn1nsQ65mEvSlI7G3K.1J6','15752250992',1),(20614,'zhixing1010@163.com','root','cm9vdA==','15752250992',1),(20615,'1305366530@qq.com','lindaifeng','$2a$10$UFXD/3j0wi9qEWKj5d2SiOh.oZA/.53u1xHE6h3CEFwxtZThP7pGa','15616112322',1),(20616,'1305366530@qq.com','test','$2a$10$Hl4AmOhwSamY0.KXN.H8senH2iewK84fzyKQIWGzLIFO0thzzmU96','15616112322',1),(20617,'1305366530@qq.com','test','$2a$10$dbJO5xrs667n3x83FzsRrO2zjTWnBgia.IV.dvAO4F8srV2qhN71K','15616112322',1),(20618,'1305366531@qq.com','lll','$2a$10$cMqwONQH60jJc8gvBi0N9O2BWjJI9OuKzr7TD0PbSQvxZ8zpv5sze','15616112322',1),(20619,'feng','feng','feng','15616112327',1);

/*Table structure for table `users_role` */

DROP TABLE IF EXISTS `users_role`;

CREATE TABLE `users_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`) USING BTREE,
  KEY `roleId` (`roleId`) USING BTREE,
  CONSTRAINT `users_role_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_role_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `users_role` */

insert  into `users_role`(`userId`,`roleId`) values (1,1),(6,1),(6862,1),(20618,1),(20619,1),(2,2),(3,2),(4,2),(5,2),(6,2),(6862,2),(20616,2),(20617,2),(20614,3),(20615,3),(20616,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
