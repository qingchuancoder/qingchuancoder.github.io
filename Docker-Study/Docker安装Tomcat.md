# Docker安装Tomcat

- `Docker Hub` 搜索

- `docker pull tomcat:8.5.81` 下载

  ```shell
  [root@qingchuan ~]# docker pull tomcat:8.5.81
  8.5.81: Pulling from library/tomcat
  405f018f9d1d: Pull complete 
  160c99d3182b: Pull complete 
  e07a736be37f: Pull complete 
  eb991d49ce62: Pull complete 
  0ff310af806b: Pull complete 
  d6fc662ef7c2: Pull complete 
  24b05d996520: Pull complete 
  Digest: sha256:8d52fd9a94486f3b418203b0f6f46eae0c07c353964a60f3d5b0b611e60265f1
  Status: Downloaded newer image for tomcat:8.5.81
  docker.io/library/tomcat:8.5.81
  [root@qingchuan ~]# docker images
  REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
  tomcat        8.5.81    ae1a89e0f415   28 hours ago    482MB
  [root@qingchuan ~]# 
  ```

  

- 启动

  ```shell
  [root@qingchuan ~]# docker run -d --name Tomcat -p 8081:8080 tomcat:8.5.81 
  2dc70fe35a1e60b1e12c7eaae417af6171af1249560d9ca7059bc23934ef8d74
  [root@qingchuan ~]# docker ps
  CONTAINER ID   IMAGE           COMMAND                  CREATED          STATUS          PORTS                                       NAMES
  2dc70fe35a1e   tomcat:8.5.81   "catalina.sh run"        11 seconds ago   Up 10 seconds   0.0.0.0:8081->8080/tcp, :::8081->8080/tcp   Tomcat
  
  ```

- 测试

  ```shell
  # 服务器 本机测试 或 浏览器输入URL测试，显示404
  [root@qingchuan ~]# curl localhost:8081
  <!doctype html><html lang="en"><head><title>HTTP Status 404 – Not Found</title><style type="text/css">body {font-family:Tahoma,Arial,sans-serif;} h1, h2, h3, b {color:white;background-color:#525D76;} h1 {font-size:22px;} h2 {font-size:16px;} h3 {font-size:14px;} p {font-size:12px;} a {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>HTTP Status 404 – Not Found</h1><hr class="line" /><p><b>Type</b> Status Report</p><p><b>Description</b> The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.</p><hr class="line" /><h3>Apache Tomcat/8.5.81</h3></body></html>[root@qingchuan ~]# 
  
  # 进入Tomcat容器
  [root@qingchuan ~]# docker exec -it Tomcat /bin/bash
  
  # webapps文件夹下没有文件
  root@2dc70fe35a1e:/usr/local/tomcat# ls
  bin           conf             lib      logs            NOTICE     RELEASE-NOTES  temp     webapps.dist
  BUILDING.txt  CONTRIBUTING.md  LICENSE  native-jni-lib  README.md  RUNNING.txt    webapps  work
  root@2dc70fe35a1e:/usr/local/tomcat# cd webapps
  root@2dc70fe35a1e:/usr/local/tomcat/webapps# ls
  root@2dc70fe35a1e:/usr/local/tomcat/webapps# cd ../webapps.dist/
  root@2dc70fe35a1e:/usr/local/tomcat/webapps.dist# ls
  docs  examples  host-manager  manager  ROOT
  
  # 将 webapps.dist 的文件拷贝到 webapps
  root@2dc70fe35a1e:/usr/local/tomcat/webapps.dist# cp -r /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps
  root@2dc70fe35a1e:/usr/local/tomcat/webapps.dist# cd /usr/local/tomcat/webapps
  root@2dc70fe35a1e:/usr/local/tomcat/webapps# ls
  docs  examples  host-manager  manager  ROOT
  
  # 退出容器
  root@2dc70fe35a1e:/usr/local/tomcat/webapps# exit
  exit
  
  # 再次测试，显示tomcat欢迎页
  [root@qingchuan ~]# curl localhost:8081
  
  # 停止Tomcat容器,不能够访问了
  [root@qingchuan ~]# docker stop Tomcat 
  Tomcat
  
  ```

  

  > # 坑
  >
  > tomcat镜像的webapps目录下没有文件，所以启动容器后，能访问但404
  >
  > ## 解决：
  >
  > 将tomcat 容器的 webapps.dist 目录下的文件拷贝到 webapps 目录