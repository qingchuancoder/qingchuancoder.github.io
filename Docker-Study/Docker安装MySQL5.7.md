# Docker 安装 MySQL:5.7

- `Docker Hub`  搜索 `mysql`

- 下载 `docker pull mysql:5.7`

- 启动

  - --name 指定容器名称
  - -p 宿主机端口:容器端口
  - **-e 设置环境，此处设置MySQL的root**用户密码为root
  - -d 后台运行

  ```shell
  docker run --name MySQL --privileged=true -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -v /usr/local/Docker/mysql5.7/log/:/var/log -v /usr/local/Docker/mysql5.7/data/:/var/lib/mysql -v /usr/local/Docker/mysql5.7/conf/:/etc/mysql/conf.d mysql:5.7
  ```

  ```shell
  # 创建MySQL容器
  [root@qingchuan nginx]# docker run --name MySQL --privileged=true -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -v /usr/local/Docker/mysql5.7/log/:/var/log -v /usr/local/Docker/mysql5.7/data/:/var/lib/mysql -v /usr/local/Docker/mysql5.7/conf/:/etc/mysql/conf.d mysql:5.7 
  d6b55323d02d8c2950fa1744d173dc60a055d735d64cd01114aaa495982f3c04
  [root@qingchuan nginx]# docker ps
  CONTAINER ID   IMAGE       COMMAND                  CREATED         STATUS         PORTS                                                  NAMES
  d6b55323d02d   mysql:5.7   "docker-entrypoint.s…"   6 seconds ago   Up 4 seconds   0.0.0.0:3306->3306/tcp, :::3306->3306/tcp, 33060/tcp   MySQL
  [root@qingchuan nginx]# 
  
  ```

- 设置

  ```shell
  [root@qingchuan nginx]# cd /usr/local/Docker/mysql5.7/conf/
  [root@qingchuan conf]# ls
  [root@qingchuan conf]# vim my.cnf
  [root@qingchuan conf]# cat my.cnf 
  [client]
  default_character_set=utf8
  
  [mysql]
  default_character_set=utf8
  
  [mysqld]
  init_connect='SET collation_connection=utf8_unicode_ci'
  init_connect='SET NAMES utf8'
  character_set_server = utf8
  collation_server = utf8_general_ci
  skip_character_set_client_handshake
  skip_name_resolve
  
  [root@qingchuan conf]# 
  
  ```

- 重启

  ```shell
  [root@qingchuan conf]# docker restart MySQL 
  MySQL
  [root@qingchuan conf]# docker ps
  CONTAINER ID   IMAGE       COMMAND                  CREATED         STATUS          PORTS                                                  NAMES
  d6b55323d02d   mysql:5.7   "docker-entrypoint.s…"   9 minutes ago   Up 15 seconds   0.0.0.0:3306->3306/tcp, :::3306->3306/tcp, 33060/tcp   MySQL
  [root@qingchuan conf]# 
  
  ```

- 查看设置

  ```shell
  # env LANG=C.UTF-8 解决MySQL容器内无法输入中文BUG，临时解决
  [root@qingchuan conf]# docker exec -it MySQL env LANG=C.UTF-8 bash
  bash-4.2# mysql -uroot -proot
  mysql: [Warning] Using a password on the command line interface can be insecure.
  Welcome to the MySQL monitor.  Commands end with ; or \g.
  Your MySQL connection id is 2
  Server version: 5.7.38 MySQL Community Server (GPL)
  
  Copyright (c) 2000, 2022, Oracle and/or its affiliates.
  
  Oracle is a registered trademark of Oracle Corporation and/or its
  affiliates. Other names may be trademarks of their respective
  owners.
  
  Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
  
  mysql> SHOW VARIABLES LIKE 'character%';
  +--------------------------+----------------------------+
  | Variable_name            | Value                      |
  +--------------------------+----------------------------+
  | character_set_client     | utf8                       |
  | character_set_connection | utf8                       |
  | character_set_database   | utf8                       |
  | character_set_filesystem | binary                     |
  | character_set_results    | utf8                       |
  | character_set_server     | utf8                       |
  | character_set_system     | utf8                       |
  | character_sets_dir       | /usr/share/mysql/charsets/ |
  +--------------------------+----------------------------+
  8 rows in set (0.04 sec)
  
  mysql> 
  ```

- 添加数据

  ```shell
  mysql> CREATE DATABASE test;
  Query OK, 1 row affected (0.01 sec)
  
  mysql> use test;
  Database changed
  mysql> CREATE TABLE t1(id int,name varchar(20));
  Query OK, 0 rows affected (0.02 sec)
  
  mysql> INSERT INTO t1 VALUES(1,'test');
  Query OK, 1 row affected (0.01 sec)
  
  mysql> INSERT INTO t1 VALUES(2,'清川');
  Query OK, 1 row affected (0.00 sec)
  
  mysql> SELECT * FROM t1;
  +------+--------+
  | id   | name   |
  +------+--------+
  |    1 | test   |
  |    2 | 清川   |
  +------+--------+
  2 rows in set (0.00 sec)
  
  mysql> 
  
  ```

  

- 连接

  - navicat

  <img src="Docker%E5%AE%89%E8%A3%85MySQL5.7.assets/%E8%BF%9E%E6%8E%A5Docker%E7%9A%84MySQL%E5%AE%B9%E5%99%A8.png" alt="连接Docker的MySQL容器" style="zoom:50%;" />
  - 命令

    ```shell
    # 进入MySQL容器
    [root@qingchuan ~]# docker exec -it MySQL bash
    
    # 进入MySQL容器啦
    # 连接MySQL
    bash-4.2# mysql -uroot -proot
    mysql: [Warning] Using a password on the command line interface can be insecure.
    Welcome to the MySQL monitor.  Commands end with ; or \g.
    Your MySQL connection id is 4
    Server version: 5.7.38 MySQL Community Server (GPL)
    
    Copyright (c) 2000, 2022, Oracle and/or its affiliates.
    
    Oracle is a registered trademark of Oracle Corporation and/or its
    affiliates. Other names may be trademarks of their respective
    owners.
    
    Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
    
    # 连接上MySQL啦
    # 查看数据库
    mysql> show databases;
    +--------------------+
    | Database           |
    +--------------------+
    | information_schema |
    | mysql              |
    | performance_schema |
    | sys                |
    +--------------------+
    4 rows in set (0.01 sec)
    
    # 退出连接
    mysql> exit
    Bye
    
    # 退出MySQL容器
    bash-4.2# exit
    exit
    [root@qingchuan ~]# 
    ```

    