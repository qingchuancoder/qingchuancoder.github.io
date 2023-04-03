# Docker安装Nginx

- 在 `Docker Hub` 上搜索 `Nginx`
- 下载

```shell
# 下载
[root@qingchuan ~]# docker pull nginx:1.22.0
1.22.0: Pulling from library/nginx
461246efe0a7: Pull complete 
e27da404f098: Pull complete 
17848039ae1c: Pull complete 
d7500eda5464: Pull complete 
9285eb05269f: Pull complete 
557ae17479aa: Pull complete 
Digest: sha256:685c35d9259ce180b5f0116a2989c45a9d56e8dc404cab56717d6338c0dc4cce
Status: Downloaded newer image for nginx:1.22.0
docker.io/library/nginx:1.22.0

# 查看
[root@qingchuan ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
nginx         1.22.0    15f7b4a94c7d   3 days ago      142MB
[root@qingchuan ~]# 
```

- 启动
  - -d 表示后台运行
  - --name 指定名称
  - -p 宿主机端口:容器端口
  - **`nginx:1.22.0` 需指定版本，否则会使用最新版**

```shell
# 启动
[root@qingchuan ~]# docker run --name Nginx -d \
-p 8080:80 \
-p 8088:88 \
-v /usr/local/Docker/Nginx/config/nginx.conf:/etc/nginx/nginx.cong \
-v /usr/local/Docker/Nginx/config/conf.d:/etc/nginx/conf.d \
-v /usr/local/Docker/Nginx/html:/usr/share/nginx/html \
-v /usr/local/Docker/Nginx/log:/var/log/nginx nginx:1.22.0

8dc14b43e4df3fbd0a37f873122c77e9b27f850d46c4f63abedcfb71dfcb75e2

# 查看
[root@qingchuan ~]# docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED          STATUS          PORTS                                   NAMES
8dc14b43e4df   nginx:1.22.0   "/docker-entrypoint.…"   23 seconds ago   Up 21 seconds   0.0.0.0:8080->80/tcp, :::8080->80/tcp   Nginx
[root@qingchuan ~]# 
```

- 测试

```shell
# 服务器本机测试 或 浏览器输入URL测试，显示Nginx的欢迎页
[root@qingchuan ~]#  curl localhost:8080
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
html { color-scheme: light dark; }
body { width: 35em; margin: 0 auto;
font-family: Tahoma, Verdana, Arial, sans-serif; }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>

# 进入Nginx
[root@qingchuan ~]# docker exec -it Nginx /bin/bash

# 现在可以使用Nginx命令
root@8dc14b43e4df:/# whereis nginx
nginx: /usr/sbin/nginx /usr/lib/nginx /etc/nginx /usr/share/nginx
root@8dc14b43e4df:/# exit
exit
[root@qingchuan ~]# docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                   NAMES
8dc14b43e4df   nginx:1.22.0   "/docker-entrypoint.…"   9 minutes ago   Up 9 minutes   0.0.0.0:8080->80/tcp, :::8080->80/tcp   Nginx

# 停止Nginx容器，不能够访问了
[root@qingchuan ~]# docker stop Nginx 
Nginx
[root@qingchuan ~]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@qingchuan ~]# 

```