# Canal

阿里巴巴mysql数据库binlog的增量订阅&消费组件。

基于日志增量订阅&消费支持的业务：

- 数据库镜像
- 数据库实时备份
- 多级索引 (卖家和买家各自分库索引)
- search build
- 业务cache刷新
- 价格变化等重要业务消息

## Simple Canal Client Example

### MySQL

- 启动MySQL

```bash
./docker/compose/mysql/docker-compose up -d
```

- 配置MySQL

开启binlog，配置为row模式

```properties
[mysqld]  
log-bin=mysql-bin #添加这一行就ok  
binlog-format=ROW #选择row模式  
```

```bash
mysql> show variables like 'binlog_format';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| binlog_format | ROW   |
+---------------+-------+
mysql> show variables like 'log_bin';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| log_bin       | ON    |
+---------------+-------+
```

- 创建canal用户

```bash
mysql> CREATE USER canal IDENTIFIED BY 'canal';    
mysql> GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';  
-- mysql> GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%';  
mysql> FLUSH PRIVILEGES; 
mysql> SHOW GRANTS FOR 'canal';
```

### Canal

- 启动Canal

注意容器访问隔离问题

```bash
sh run.sh -e canal.auto.scan=false \
		  -e canal.destinations=test \
		  -e canal.instance.master.address=mysql_db:3306  \
		  -e canal.instance.dbUsername=canal  \
		  -e canal.instance.dbPassword=canal  \
		  -e canal.instance.connectionCharset=UTF-8 \
		  -e canal.instance.tsdb.enable=true \
		  -e canal.instance.gtidon=false  \
```

### Client

- 运行Client

```bash
java net.hyperj.gist.canal.SimpleCanalClientExample
```

- 添加数据

```mysql
create database test;

use test;

create table `xdual` (
    `id` int(11) not null auto_increment,
    `x` timestamp not null default current_timestamp,
    primary key (`id`)
   ) engine=innodb default charset=utf8;

insert into xdual(id,x) values(null,now());
```

## Reference

- [canal github](https://github.com/alibaba/canal)
- [canal wiki](https://github.com/alibaba/canal/wiki)