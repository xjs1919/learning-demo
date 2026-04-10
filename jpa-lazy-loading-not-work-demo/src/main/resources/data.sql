insert into author(id, name)values(1, 'zhangsan');
insert into author(id, name)values(2, 'lisi');

insert into contact(id, mobile, email, author_id)values(1, '13112341234', 'zhangsan@qq.com', 1);
insert into contact(id, mobile, email, author_id)values(2, '13212341234', 'lisi@163.com', 2);

insert into book(id, name, author_id)values(1,'Tom & Jerry', 1);
insert into book(id, name, author_id)values(2,'Jack & Rose', 1);
insert into book(id, name, author_id)values(3,'Old Man & Sea', 1);
insert into book(id, name, author_id)values(4,'Java Programming', 2);




