/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.11 : Database - lxb_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Data for the table `sys_dictionaries` */

/*Data for the table `sys_fhbutton` */

insert  into `sys_fhbutton`(`FHBUTTON_ID`,`NAME`,`QX_NAME`,`BZ`) values ('46992ea280ba4b72b29dedb0d4bc0106','发邮件','email','发送电子邮件');

/*Data for the table `sys_log` */

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`MENU_ID`,`MENU_NAME`,`MENU_URL`,`PARENT_ID`,`MENU_ORDER`,`MENU_ICON`,`MENU_TYPE`,`MENU_STATE`) values (1,'系统管理','#','0','10','menu-icon fa fa-desktop blue','2',1),(2,'权限管理','#','1','1','menu-icon fa fa-lock black','1',1),(36,'角色(基础权限)','role.do','2','1','menu-icon fa fa-key orange','1',1),(37,'按钮权限','buttonrights/list.do','2','2','menu-icon fa fa-key green','1',1),(38,'菜单管理','menu/listAllMenu.do','1','3','menu-icon fa fa-folder-open-o brown','1',1),(39,'按钮管理','fhbutton/list.do','1','2','menu-icon fa fa-download orange','1',1),(40,'用户管理','#','0','7','menu-icon fa fa-users blue','2',1),(41,'系统用户','user/listUsers.do','40','1','menu-icon fa fa-users green','1',1),(43,'数据字典','dictionaries/listAllDict.do?DICTIONARIES_ID=0','1','4','menu-icon fa fa-book purple','1',1),(61,'测试菜单bbj','bbj/list.do','22','2','menu-icon fa fa-leaf black','1',1),(100,'参数项','param/list.do','1','4','menu-icon fa fa-leaf black','1',1);

/*Data for the table `sys_param` */

insert  into `sys_param`(`PARAM_ID`,`NAME`,`VAL`,`BZ`) values ('9fb3fbee66fe468d858f454b24bc9348','RES_DIR','E:/res/','静态资源存放目录'),('caab9d9c7b7b4b02833fe77333a4f803','API_SECRET','$t%rGvFb','服务端密钥，用于服务器和app端的通讯校验');

/*Data for the table `sys_role` */

insert  into `sys_role`(`ROLE_ID`,`ROLE_NAME`,`RIGHTS`,`PARENT_ID`,`ADD_QX`,`DEL_QX`,`EDIT_QX`,`CHA_QX`) values ('1','系统管理组','5923600852152800150339472076830291552886726','0','1','1','1','1'),('115b386ff04f4352b060dffcd2b5d1da','中级会员','498','2','0','0','0','0'),('1b67fc82ce89457a8347ae53e43a347e','初级会员','498','2','0','0','0','0'),('2','会员组','498','0','0','0','0','1'),('3264c8e83d0248bb9e3ea6195b4c0216','系统管理员','3802951800684692725613204603078','1','2745757475961053486176818902302049547153563590','2748566294888769542605944182694910380361113542','510423577002070300284756946605316235206','22300705290411743380241273568078190912462790'),('8b70a7e67f2841e7aaba8a4d92e5ff6f','高级会员','498','2','0','0','0','0');

/*Data for the table `sys_role_fhbutton` */

insert  into `sys_role_fhbutton`(`RB_ID`,`ROLE_ID`,`BUTTON_ID`) values ('d55968621ca24c229fa5e1d8c932d6e9','3264c8e83d0248bb9e3ea6195b4c0216','46992ea280ba4b72b29dedb0d4bc0106');

/*Data for the table `sys_user` */

insert  into `sys_user`(`USER_ID`,`USERNAME`,`PASSWORD`,`NAME`,`RIGHTS`,`ROLE_ID`,`LAST_LOGIN`,`IP`,`STATUS`,`BZ`,`SKIN`,`EMAIL`,`NUMBER`,`PHONE`) values ('098cc6af197b43888bda3a6b50f9b4db','linxinbin','846af8e5d35f419b376d7a57b26cd3734bdfc152','林鑫滨','','3264c8e83d0248bb9e3ea6195b4c0216','2016-10-31 12:31:05','0:0:0:0:0:0:0:1','0','哈哈','default','936348175@qq.com','linxinbin','13726516869'),('1','admin','de41b7fb99201d8334c23c014db35ecd92df81bc','FH','1133671055321055258374707980945218933803269864762743594642571294','3264c8e83d0248bb9e3ea6195b4c0216','2016-12-23 16:27:12','0:0:0:0:0:0:0:1','0','admin','default','QQ313596790@main.com','001','18788888888'),('b0e764cf06084e66ac718b243ca14f38','zouqianyu','b9d0373ef6ed70e35c1c56bd04119874065d5c14','邹乾宇','','3264c8e83d0248bb9e3ea6195b4c0216','2016-11-04 10:14:34','0:0:0:0:0:0:0:1','0','','default','15695@qq.com','611','18064032748');

/*Data for the table `t_docrecord` */

/*Data for the table `tb_pictures` */

insert  into `tb_pictures`(`PICTURES_ID`,`TITLE`,`NAME`,`PATH`,`CREATETIME`,`MASTER_ID`,`BZ`) values ('1727eb943b2941a49762c1c30bb4e99c','图片','b2b36ea7dbfd4fb4a8ccf6d6ddc09fd1.png','','2016-12-20 09:48:12','1','图片管理处上传'),('b1a8ac7d9e47409a9243e012309affc2','图片','cef0ec1afe88409badb2337f40d07295.gif','20161028/cef0ec1afe88409badb2337f40d07295.gif','2016-10-28 10:10:51','1','图片管理处上传'),('d8ef572b9633445fbc51aeaa585801e7','图片','55e91513ad0d4c81823b5800231faee9.png','20161220/55e91513ad0d4c81823b5800231faee9.png','2016-12-20 09:48:12','1','图片管理处上传');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
