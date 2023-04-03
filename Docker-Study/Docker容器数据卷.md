心中无女人，编码自然神

忘掉心上人，抬手灭红尘

人间清醒，挣钱第一

好好学习，天天向上

听懂掌声！

# 容器数据卷



> 数据卷就是目录或文件，存放于一个或多个容器中，由docker挂载到容器，但不属于联合文件系统，因此能绕过Union File System提供一些用于存储或共享数据的特性.
>
> 卷设计的目的就是==数据持久化==，卷完全独立于容器的生命周期，因此Docker不会在删除容器时删除其挂载 的数据卷
>
> 

> - 数据卷可在容器之间共享或重用数据
> - 卷中的更改实时生效
> - 数据卷中的更改不会包含在镜像的更新中
> - 数据卷的声明周期一直持续到没有容器使用它为止



---

# 容器数据卷挂载实例

```shell
# 指定路径挂载
docker run -it --privileged=true -v /宿主机绝对路径目录:/容器内目录:读写规则 镜像名

# 具名挂载
docker run -it --privileged=true -v 挂载名:/容器内目录:读写规则 镜像名

# 匿名挂载
docker run -it --privileged=true -v /容器内目录 镜像名
```



- -v 的值 由==挂载名、宿主机绝对路径目录、容器内路径目录、读写规则==组成
  - 挂载名可省略
  - 宿主机路径目录可省略，省略时默认挂载在 `/var/lib/docker/volumes/xxxx/_data`
  - **宿主机绝对路径需以 `/` 开始，表示绝对路径**
  - 读写规则可省略，默认为 `rw` 可读可写（readwrite）；也可指定为 `ro` 只读（readonly）
  - 读写规则限定容器读写权限，若为 `ro` 则在容器内不能写，只能通过宿主机进行写操作
  - 容器内目录必须指定
  - 宿主机目录、容器目录不存在时会自动创建
- **`--privileged=true`  为这个容器提供扩展权限**
- 可以由多个 -v 挂载多个文件夹
- 挂载的文件会==双向同步==
- 将容器停止，宿主机再创建文件，再次启动容器，宿主机新建的文件同步到容器中

```shell
# 启动容器并挂载
[root@qingchuan local]# docker run --name TomcatWithV --privileged=true -d -v /usr/local/Docker/tomcat/webapps:/usr/local/tomcat/webapps tomcat:8.5.81 2c79b3fc895471b1cfa94bbb60a73c79508966c74b749261f0b4e16d23374325

# 在宿主机创建文件
[root@qingchuan local]# cd /usr/local/Docker/tomcat/webapps/
[root@qingchuan webapps]# vim Local.txt
[root@qingchuan webapps]# cat Local.txt
宿主机文件

# 进入容器
[root@qingchuan webapps]# docker exec -it TomcatWithV bash

# 查看宿主机挂载的文件
root@2c79b3fc8954:/usr/local/tomcat# cd /usr/local/tomcat/webapps
root@2c79b3fc8954:/usr/local/tomcat/webapps# cat Local.txt 
宿主机文件

# 容器内创建文件
root@2c79b3fc8954:/usr/local/tomcat/webapps# echo "容器内文件" > Container.txt
root@2c79b3fc8954:/usr/local/tomcat/webapps# cat Container.txt 
容器内文件
root@2c79b3fc8954:/usr/local/tomcat/webapps# ls
Container.txt  Local.txt

# 退出容器，在宿主机查看容器内的文件
[root@qingchuan webapps]# cat Container.txt 
容器内文件

# 在宿主机内修改
[root@qingchuan webapps]# vim Local.txt 
[root@qingchuan webapps]# cat Local.txt 
宿主机文件
宿主机修改测试
[root@qingchuan webapps]# vim Container.txt 
[root@qingchuan webapps]# cat Container.txt 
容器内文件
宿主机修改测试

# 进入容器再查看
[root@qingchuan webapps]# docker exec -it TomcatWithV bash
root@2c79b3fc8954:/usr/local/tomcat# cd /usr/local/tomcat/webapps
root@2c79b3fc8954:/usr/local/tomcat/webapps# ls
Container.txt  Local.txt
root@2c79b3fc8954:/usr/local/tomcat/webapps# cat Container.txt 
容器内文件
宿主机修改测试
root@2c79b3fc8954:/usr/local/tomcat/webapps# cat Local.txt 
宿主机文件
宿主机修改测试
root@2c79b3fc8954:/usr/local/tomcat/webapps# 

# 容器内修改 
root@2c79b3fc8954:/usr/local/tomcat/webapps# echo "容器内修改" >> Local.txt 
root@2c79b3fc8954:/usr/local/tomcat/webapps# cat Local.txt 
宿主机文件
宿主机修改测试
容器内修改
root@2c79b3fc8954:/usr/local/tomcat/webapps# echo "容器内修改" >> Container.txt 
root@2c79b3fc8954:/usr/local/tomcat/webapps# cat Container.txt 
容器内文件
宿主机修改测试
容器内修改

# 退出容器查看
[root@qingchuan webapps]# cat Container.txt 
容器内文件
宿主机修改测试
容器内修改
[root@qingchuan webapps]# cat Local.txt 
宿主机文件
宿主机修改测试
容器内修改

# 将容器停止，宿主机再创建文件，再次启动容器，宿主机新建的文件同步到容器中
[root@qingchuan webapps]# docker stop TomcatWithV 
TomcatWithV
[root@qingchuan webapps]# ls
Container.txt  Local.txt
[root@qingchuan webapps]# echo "停止容器后在宿主机创建文件" > test.txt
[root@qingchuan webapps]# cat test.txt 
停止容器后在宿主机创建文件
[root@qingchuan webapps]# docker start TomcatWithV 
TomcatWithV
[root@qingchuan webapps]# docker exec -it TomcatWithV bash
root@2c79b3fc8954:/usr/local/tomcat# cat /usr/local/tomcat/webapps/test.txt 
停止容器后在宿主机创建文件
root@2c79b3fc8954:/usr/local/tomcat# 
```



# 容器数据卷间的继承

> - 只继承挂载关系
> - 容器间仍是独立关系
> - 某个容器挂了，不影响其他容器
> - 容器又活了，那么挂载会自动同步

- 创建父子容器

  ```shell
  # 父容器
  [root@qingchuan nginx]# docker run --name NginxWithVP --privileged=true -d -v /usr/local/Docker/nginx:/home nginx:1.22.0
  0bd71f85911d22f36a364ef758c0085a9f51ce6f891af897346089a8198b8d76
  
  # 子容器
  [root@qingchuan nginx]# docker run --name NginxWithVC --privileged=true -d --volumes-from NginxWithVP nginx:1.22.0 
  258842fc5ddc90939be9c95a2fd627fe3f68a28a4349de2ec0633a1f5ca38641
  [root@qingchuan nginx]# 
  ```

  

- 宿主机、父容器、子容器文件同步测试

  ```shell
  # 宿主机创建文件
  [root@qingchuan nginx]# cd /usr/local/Docker/nginx/
  [root@qingchuan nginx]# echo "宿主机" > host.txt
  [root@qingchuan nginx]# cat host.txt 
  宿主机
  [root@qingchuan nginx]# 
  
  # 父容器查看
  root@0bd71f85911d:/# cd /home/
  root@0bd71f85911d:/home# cat host.txt 
  宿主机
  root@0bd71f85911d:/home# 
  
  # 子容器查看
  root@258842fc5ddc:/# cd /home/
  root@258842fc5ddc:/home# cat host.txt 
  宿主机
  root@258842fc5ddc:/home# 
  
  ```

  ```shell
  # 父容器创建
  root@0bd71f85911d:/home# echo "parent" > parent.txt
  root@0bd71f85911d:/home# cat parent.txt 
  parent
  
  # 子容器查看
  root@258842fc5ddc:/home# cat parent.txt 
  parent
  
  # 宿主机查看
  [root@qingchuan nginx]# cat parent.txt 
  parent
  
  ```

  ```shell
  # 子容器创建
  root@258842fc5ddc:/home# echo "child" > child.txt
  root@258842fc5ddc:/home# cat child.txt 
  child
  
  
  # 父容器查看
  root@0bd71f85911d:/home# cat child.txt 
  child
  
  # 宿主机查看
  [root@qingchuan nginx]# cat child.txt 
  child
  
  ```

  ```shell
  # 宿主机修改
  [root@qingchuan nginx]# echo "宿主机修改测试" >> host.txt 
  [root@qingchuan nginx]# cat host.txt 
  宿主机
  宿主机修改测试
  
  # 父容器查看
  root@0bd71f85911d:/home# cat host.txt 
  宿主机
  宿主机修改测试
  
  # 子容器查看
  root@258842fc5ddc:/home# cat host.txt 
  宿主机
  宿主机修改测试
  
  ```

  ```shell
  # 子改父
  root@258842fc5ddc:/home# echo "child update" >> parent.txt 
  root@258842fc5ddc:/home# cat parent.txt 
  parent
  child update
  root@258842fc5ddc:/home# 
  
  # 父亲查看
  root@0bd71f85911d:/home# cat parent.txt 
  parent
  child update
  
  # 宿主机
  [root@qingchuan nginx]# cat parent.txt 
  parent
  child update
  
  ```