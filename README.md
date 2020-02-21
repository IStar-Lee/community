# 码匠社区

## 资料  
[Spring 文档](https://spring.io/guides)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[es 中文社区（参考模板）](https://elasticsearch.cn/explore)  
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/)  
[BootStrap](https://v3.bootcss.com/getting-started/)   
[Github OAuth](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)   
[OKhttp 需要的代码和依赖](https://square.github.io/okhttp/)  
[maven 仓库](https://mvnrepository.com/)  
[thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)  
[Mybatis Generator](http://mybatis.org/generator)  
[阿里巴巴icon图库](https://www.iconfont.cn/)
## 工具  
[Git](https://git-scm.com/downloads)  
[VP 时序图画图工具](https://www.visual-paradigm.com/cn/)   
[Lombok工具](https://projectlombok.org/)  
[postman](https://chrome.google.com/webstore/detail/tabbed-postman-rest-clien/coohjcphdfgbiolnekdpbcijmhambjff)

## 脚本
```bash
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate   //运行Mybatis Generator
```
##数据库
```bash
CREATE TABLE `communityuser` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` VARCHAR(100) NOT NULL,
  `NAME` VARCHAR(20) NOT NULL,
  `TOKEN` CHAR(100) NOT NULL,
  `GMT_CREATE` BIGINT(20) NOT NULL,
  `GMT_MODIFY` BIGINT(20) NOT NULL,
  `AVATAR_URL` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8


CREATE TABLE `question` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(50) NOT NULL COMMENT '问题标题',
  `DESCRIPTION` VARCHAR(1000) NOT NULL COMMENT '问题描述',
  `GMT_CREATE` BIGINT(20) NOT NULL COMMENT '问题创建时间',
  `GMT_MODIFY` BIGINT(20) NOT NULL COMMENT '问题修改时间',
  `CREATOR` INT(11) NOT NULL COMMENT '问题创建者',
  `COMMENT_COUNT` INT(10) UNSIGNED ZEROFILL NOT NULL COMMENT '评论数',
  `VIEW_COUNT` INT(10) UNSIGNED ZEROFILL NOT NULL COMMENT '浏览数',
  `LIKE_COUNT` INT(10) UNSIGNED ZEROFILL NOT NULL COMMENT '喜欢数',
  `TAG` VARCHAR(50) NOT NULL COMMENT '标签',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8


CREATE TABLE `comment` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` bigint(11) NOT NULL COMMENT '问题的id为1，回复的评论id为2',
  `TYPE` int(11) NOT NULL COMMENT '回复的是问题还是评论',
  `COMMENTATOR` bigint(11) NOT NULL,
  `GMT_CREATE` bigint(20) NOT NULL,
  `GMT_MODIFIED` bigint(20) NOT NULL,
  `LIKE_COUNT` bigint(11) NOT NULL DEFAULT '0',
  `CONTENT` varchar(1024) NOT NULL,
  `COMMENT_COUNT` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8


CREATE TABLE `notification` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `notifier` BIGINT(20) NOT NULL COMMENT '谁回复了你',
  `receiver` BIGINT(20) NOT NULL COMMENT '谁收到回复',
  `outerid` BIGINT(20) NOT NULL COMMENT '回复的是评论，问题还是点赞了问题，评论...',
  `type` INT(11) NOT NULL COMMENT '回复的是评论，问题还是点赞了问题，评论...的类型',
  `gmt_create` BIGINT(20) NOT NULL,
  `status` INT(11) NOT NULL DEFAULT '0' COMMENT '是否已读通知',
  `notifier_name` VARCHAR(20) DEFAULT NULL COMMENT '回复人的姓名',
  `outer_title` VARCHAR(256) DEFAULT NULL COMMENT '问题的title',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8
```
