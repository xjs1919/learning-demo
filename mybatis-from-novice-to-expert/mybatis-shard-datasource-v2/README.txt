SaaS办公系统：OA系统、财务系统、招聘系统、绩效系统、员工管理系统
组织结构：集团公司 分公司

CREATE TABLE `employee` (
  `tenant_id` varchar(32) not null default '' COMMENT '租户ID',
  `group_id` int not null  default -1 COMMENT '集团ID',
  `company_id` int not null  default -1 COMMENT '分公司ID',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `name` varchar(16) DEFAULT NULL COMMENT '员工姓名',
  `gender` tinyint not null  DEFAULT 1 COMMENT '1男，2女',
  `birth` date DEFAULT NULL COMMENT '生日',
  `entry_time`  date DEFAULT NULL COMMENT '入职日期',
  `mobile` varchar(11)  comment '手机号',
  PRIMARY KEY (`id`),
  key idx_tenant_group_company(`tenant_id`, `group_id`, `company_id`)
);
db1:
 insert into employee values('9e8e8a8652fc493d913224b96816b1aa', 1, 1,1, 'xjs', 1, '1998-01-01', '2022-03-01', '13111111111');
 insert into employee values('9e8e8a8652fc493d913224b96816b1aa', 1, 1,2, 'joshua', 1, '1988-01-01', '2021-03-01', '13222222222');
db2:
 insert into employee values('db45003a2bed4d48b8bf360f7afd4bbd', 1, 1,1, 'hello', 1, '1997-01-01', '2022-04-01', '13333333333');
 insert into employee values('db45003a2bed4d48b8bf360f7afd4bbd', 1, 1,2, 'world', 1, '1987-01-01', '2020-04-01', '13444444444');


 （1）浏览器发起的请求，controller
 （2）内部服务之间调用，controller
 （3）定时调度任务调用，service，写一个service的aop，拦截service方法的参数，设置tenantId