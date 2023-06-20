/**
出货信息表
 */
DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user
(
    id BIGINT NOT NULL COMMENT 'id',
    name VARCHAR(30)  COMMENT '姓名',
    PRIMARY KEY (id)
);
insert into tb_user(id,name)values(1, 'zhangsan');
insert into tb_user(id,name)values(2, 'lisi');
insert into tb_user(id,name)values(3, 'wangwu');