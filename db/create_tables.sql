--创建数据库
CREATE DATABASE `letcodedb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
grant all privileges on letcodedb.* to 'letcode'@'%'  identified by 'letcode123' with grant option;
--刷新权限
flush privileges;


CREATE TABLE `nextval` (
  `name` varchar(50) NOT NULL COMMENT '自增表名',
  `start_value` int(6) NOT NULL DEFAULT '1' COMMENT '初始值',
  `max_value` int(7) DEFAULT '1000000' COMMENT '最大值',
  `current_value` int(6) NOT NULL DEFAULT '0' COMMENT '值',
  `increment` int(6) NOT NULL DEFAULT '1' COMMENT '增量',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=2730 COMMENT='序列表';

--创建序列函数
CREATE FUNCTION `nextval`(seq_name varchar(30)) RETURNS int(11)
    COMMENT '获取序列下一个值'
BEGIN
  UPDATE nextval a
  SET a.current_value = (CASE
      WHEN LAST_INSERT_ID(a.current_value + a.increment) >= a.max_value THEN a.start_value ELSE LAST_INSERT_ID(a.current_value + a.increment)
    END)
  WHERE a.name = seq_name;
  RETURN LAST_INSERT_ID();
END

CREATE TABLE `letcodedb`.`note` (
  `serno` VARCHAR(30) NOT NULL COMMENT '编号',
  `create_date` CHAR(8) NULL COMMENT '创建日期',
  `create_time` CHAR(6) NULL COMMENT '创建时间',
  `content` BLOB NULL COMMENT '内容',
  `remark` VARCHAR(128) NULL COMMENT '备注',
  PRIMARY KEY (`serno`)  COMMENT '')
COMMENT = '笔记本';



--创建用户表
CREATE TABLE `sys_user` (
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `email` varchar(128) NOT NULL COMMENT '注册邮箱',
  `phone` varchar(20) NOT NULL COMMENT '注册手机号码',
  `user_pwd` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `status` char(2) DEFAULT '0' COMMENT '用户状态（00：正常，01：锁定，02：销户）',
  `email_check_flag` varchar(1) DEFAULT NULL COMMENT '邮箱验证结果',
  `phone_check_flag` varchar(1) DEFAULT NULL COMMENT '手机严重结果',
  `reg_date` varchar(8) DEFAULT NULL COMMENT '注册日期',
  `reg_time` varchar(6) DEFAULT NULL COMMENT '注册时间',
  `last_login` varchar(14) DEFAULT NULL COMMENT '最后登录时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
