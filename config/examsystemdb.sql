/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : examsystemdb

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 08/01/2020 14:18:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for examlog
-- ----------------------------
DROP TABLE IF EXISTS `examlog`;
CREATE TABLE `examlog`  (
  `studentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentClass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentNumber` int(11) NOT NULL,
  `score` float(255, 0) NOT NULL,
  `scoreid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`scoreid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examlog
-- ----------------------------
INSERT INTO `examlog` VALUES ('吴权请', '四班', 170591101, 100, 1);
INSERT INTO `examlog` VALUES ('杨幂', '一班', 10001, 30, 2);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `existpicture` int(4) NOT NULL DEFAULT 0,
  `pirture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('右图是以下哪个数据库的Logo?<br>    A.MySQL<br>    B.Oracle<br>    C.SqlServer<br>    D.DB2', 'A', 1, 'mysql.jpg');
INSERT INTO `question` VALUES ('右图是以下哪个服务器的Logo?<br>    A.JBoss<br>    B.Oracle<br>    C.Jetty<br>    D.Tomcat', 'D', 1, 'tomcat.jpg');
INSERT INTO `question` VALUES ('右图是以下哪个数据库的Logo?<br>    A.MySQL<br>    B.Oracle<br>    C.SqlServer<br>    D.DB2', 'B', 1, 'oracle.jpg');
INSERT INTO `question` VALUES ('在WEB-INF目录下，必须存放的文件为?<br>    A.class文件<br>    B.web.xml<br>    C.jar文件<br>    D.html文件', 'B', 0, NULL);
INSERT INTO `question` VALUES ('下面哪个不是JAVA关键字?<br>    A.integer<br>    B.double<br>    C.float<br>    D.default', 'A', 0, NULL);
INSERT INTO `question` VALUES ('构造函数何时被调用<br>    A.类定义时<br>    B.创建对象时<br>    C.调用对象方法时<br>    D.使用对象的变量时', 'B', 0, NULL);
INSERT INTO `question` VALUES ('下面哪项不是response对象的方法<br>    A.setContentType(String contentTypestr)<br>    B.setHeader(String headername,String headervalue)<br>    C.getParameter(String str)<br>    D.sendError(int errorcode)', 'C', 0, NULL);
INSERT INTO `question` VALUES ('下列哪一种叙述是正确的<br>    A.abstract修饰符可修饰字段、方法和类<br>    B.抽象方法的body部分必须用一对大括号{ }包住<br>    C.声明抽象方法，大括号可有可无<br>    D.声明抽象方法不可写出大括号', 'D', 0, NULL);
INSERT INTO `question` VALUES ('在Servlet处理请求的方式为<br>    A.以进程的方式<br>    B.以程序的方式<br>    C.以线程的方式<br>    D.以响应的方式', 'C', 0, NULL);
INSERT INTO `question` VALUES ('JDBC中，用于表示数据库连接的对象是<br>    A.Statement<br>    B.Connection<br>    C.DriverManager<br>    D.PreparedStatement', 'B', 0, NULL);
INSERT INTO `question` VALUES ('以下关于异常的说法正确的是<br>    Ａ.一旦出现异常，程序运行就终止了<br>    Ｂ. 如果一个方法申明将抛出某个异常，它就必须真的抛出那个异常<br>    Ｃ. 在catch子句中匹配异常是一种精确匹配<br>    Ｄ. 可能抛出系统异常的方法是不需要申明异常的', 'D', 0, NULL);
INSERT INTO `question` VALUES ('在J2EE中属于Web层的组件有<br>    A.Servlet<br>    B.EJB<br>    C.Applet<br>    D.HTML', 'A', 0, NULL);
INSERT INTO `question` VALUES ('jsp指令中isELIgnored=\"boolean\"的意思是<br>    A.决定是否实现Servler的单线程模式<br>    B.决定改页面是否是一个错误处理页面<br>    C.决定是否支持EL表示<br>    D.没有具体的含义', 'C', 0, NULL);
INSERT INTO `question` VALUES ('每个使用Swing构件的程序必须有一个(   ).<br>    A.按钮<br>    B.标签<br>    C.菜单<br>    D.容器', 'D', 0, NULL);

-- ----------------------------
-- Table structure for studenttable
-- ----------------------------
DROP TABLE IF EXISTS `studenttable`;
CREATE TABLE `studenttable`  (
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentClass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `existImage` int(11) NULL DEFAULT 0,
  `studentImage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 180491102 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of studenttable
-- ----------------------------
INSERT INTO `studenttable` VALUES ('lisi', '444', '李四', '170591123', '五班', 1, 'lisi.jpg');
INSERT INTO `studenttable` VALUES ('qiezi', '123456', '吴权请', '170591101', '四班', 1, 'qiezi.jpg');
INSERT INTO `studenttable` VALUES ('wangwu', '555', '王五', '180491101', '一班', 1, 'wangwu.jpg');
INSERT INTO `studenttable` VALUES ('yangmi777', '123', '杨幂', '10001', '一班', 1, 'yangmi.jpg');
INSERT INTO `studenttable` VALUES ('zhangsan', '333', '张三', '170591111', '四班', 1, 'zhangsan.jpg');

SET FOREIGN_KEY_CHECKS = 1;
