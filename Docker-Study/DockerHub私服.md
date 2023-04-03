- 下载 Docker Registry

  ```shell
  [root@qingchuan ~]# docker pull registry
  Using default tag: latest
  latest: Pulling from library/registry
  530afca65e2e: Pull complete 
  d450d4da0343: Pull complete 
  96277bea17b6: Pull complete 
  470ad04e03fb: Pull complete 
  bd3d4dc6e66f: Pull complete 
  Digest: sha256:c631a581c6152f5a4a141a974b74cf308ab2ee660287a3c749d88e0b536c0c20
  Status: Downloaded newer image for registry:latest
  docker.io/library/registry:latest
  ```

  

- 运行 Docker Registry

  ```shell
  [root@qingchuan ~]# docker run -d -p 5000:5000 -v /usr/local/Docker/Registry/:/tmp/registry --privileged=true registry
  dcd635112b3bb783ca7bd1ec867f286cd20b69799cb33a937cffee483ddb20a0
  [root@qingchuan ~]# docker ps
  CONTAINER ID   IMAGE      COMMAND                  CREATED         STATUS         PORTS                                       NAMES
  dcd635112b3b   registry   "/entrypoint.sh /etc…"   8 seconds ago   Up 6 seconds   0.0.0.0:5000->5000/tcp, :::5000->5000/tcp   eager_panini
  ```

  

- commit镜像

  ```shell
  [root@qingchuan ~]# docker commit -a "清川" -m "将webapps.dist目录文件拷贝到webapps目录" bbca1fed5ea5 tomcat:1.0
  sha256:f33ca07339c6c2f59968279250a06d2e8418c0387d9b2dbbfbba8b77423f0bc0
  
  [root@qingchuan ~]# docker images
  REPOSITORY    TAG       IMAGE ID       CREATED              SIZE
  tomcat        1.0       f33ca07339c6   About a minute ago   486MB
  
  [root@qingchuan ~]# docker run -d -p 8082:8080 tomcat:1.0 
  a2bbe5aa0833e3b663e10c8632b1f072d3e637b9f8a20e4215303af5685c90e7
  [root@qingchuan ~]# docker ps
  CONTAINER ID   IMAGE           COMMAND                  CREATED          STATUS          PORTS                                       NAMES
  a2bbe5aa0833   tomcat:1.0      "catalina.sh run"        6 seconds ago    Up 4 seconds    0.0.0.0:8082->8080/tcp, :::8082->8080/tcp   compassionate_lovelace
  bbca1fed5ea5   tomcat:8.5.81   "catalina.sh run"        3 minutes ago    Up 3 minutes    0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   serene_engelbart
  dcd635112b3b   registry        "/entrypoint.sh /etc…"   12 minutes ago   Up 12 minutes   0.0.0.0:5000->5000/tcp, :::5000->5000/tcp   eager_panini
  [root@qingchuan ~]# 
  ```

  

- 查看私服镜像

  ```shell
  [root@qingchuan ~]# curl -XGET http://192.168.74.143:5000/v2/_catalog
  {"repositories":[]}
  ```

- 将TAG规范化

  ```shell
  [root@qingchuan ~]# docker tag tomcat:1.0 192.168.74.143:5000/tomcat:1.0
  [root@qingchuan ~]# docker images
  REPOSITORY                   TAG       IMAGE ID       CREATED         SIZE
  192.168.74.143:5000/tomcat   1.0       f33ca07339c6   9 minutes ago   486MB
  tomcat                       1.0       f33ca07339c6   9 minutes ago   486MB
  tomcat                       8.5.81    8486cd7b845a   2 days ago      486MB
  ```

  

- 修改配置文件，使Docker支持http

  ```shell
  [root@qingchuan ~]# vim /etc/docker/daemon.json
  [root@qingchuan ~]# cat /etc/docker/daemon.json
  {
    "insecure-registries": ["192.168.74.143:5000"]
  }
  
  # 重启
  [root@qingchuan docker]# systemctl restart docker
  [root@qingchuan docker]# systemctl status docker
  ● docker.service - Docker Application Container Engine
     Loaded: loaded (/usr/lib/systemd/system/docker.service; enabled; vendor preset: disabled)
     Active: active (running) since 二 2022-07-26 11:10:10 CST; 10s ago
       Docs: https://docs.docker.com
   Main PID: 64327 (dockerd)
      Tasks: 11
     Memory: 37.8M
     CGroup: /system.slice/docker.service
             └─64327 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
  
  7月 26 11:10:09 qingchuan dockerd[64327]: time="2022-07-26T11:10:09.932972549+08:00" level=info msg="Firewalld: docker zone already exists, returning"
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.151340445+08:00" level=info msg="Firewalld: interface docker0 already par...turning"
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.179398301+08:00" level=info msg="Firewalld: interface docker0 already par...turning"
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.431862949+08:00" level=info msg="Default bridge (docker0) is assigned wit...address"
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.535596374+08:00" level=info msg="Firewalld: interface docker0 already par...turning"
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.640788528+08:00" level=info msg="Loading containers: done."
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.662713836+08:00" level=info msg="Docker daemon" commit=a89b842 graphdrive...20.10.17
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.662959587+08:00" level=info msg="Daemon has completed initialization"
  7月 26 11:10:10 qingchuan systemd[1]: Started Docker Application Container Engine.
  7月 26 11:10:10 qingchuan dockerd[64327]: time="2022-07-26T11:10:10.716148477+08:00" level=info msg="API listen on /var/run/docker.sock"
  Hint: Some lines were ellipsized, use -l to show in full.
  ```

  

- push

  ```shell
  [root@qingchuan docker]# docker images
  REPOSITORY                   TAG       IMAGE ID       CREATED          SIZE
  tomcat                       1.0       f33ca07339c6   29 minutes ago   486MB
  192.168.74.143:5000/tomcat   1.0       f33ca07339c6   29 minutes ago   486MB
  tomcat                       8.5.81    8486cd7b845a   2 days ago       486MB
  tomcat                       <none>    ae1a89e0f415   4 days ago       482MB
  nginx                        1.22.0    15f7b4a94c7d   6 days ago       142MB
  registry                     latest    d1fe2eaf6101   7 days ago       24.1MB
  mysql                        5.7       459651132a11   12 days ago      429MB
  hello-world                  latest    feb5d9fea6a5   10 months ago    13.3kB
  [root@qingchuan docker]# docker push 192.168.74.143:5000/tomcat:1.0 
  The push refers to repository [192.168.74.143:5000/tomcat]
  f754dc0026a6: Pushed 
  197e88832e75: Pushed 
  98d5e5d3ed5a: Pushed 
  99721982388a: Pushed 
  31c135ba5e11: Pushed 
  571824725172: Pushed 
  e369ffcb8f5d: Pushed 
  16f636807506: Pushed 
  a790f937a6ae: Pushed 
  1.0: digest: sha256:c1af76b67814ba195529d496a445299d299379b55e001a799b2e1ee39911b9af size: 2207
  [root@qingchuan docker]# 
  ```

  

- 再次查看

  ```shell
  [root@qingchuan docker]# curl -XGET http://192.168.74.143:5000/v2/_catalog
  {"repositories":["tomcat"]}
  ```

  

- pull

  ```shell
  # 已经没有tomcat镜像
  [root@qingchuan docker]# docker images
  REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
  nginx         1.22.0    15f7b4a94c7d   6 days ago      142MB
  registry      latest    d1fe2eaf6101   7 days ago      24.1MB
  mysql         5.7       459651132a11   12 days ago     429MB
  hello-world   latest    feb5d9fea6a5   10 months ago   13.3kB
  
  # 查看私服镜像
  [root@qingchuan docker]# curl -XGET http://192.168.74.143:5000/v2/_catalog
  {"repositories":["tomcat"]}
  
  # 拉取镜像
  [root@qingchuan docker]# docker pull 192.168.74.143:5000/tomcat:1.0
  1.0: Pulling from tomcat
  405f018f9d1d: Already exists 
  160c99d3182b: Already exists 
  e07a736be37f: Already exists 
  eb991d49ce62: Already exists 
  0ff310af806b: Already exists 
  d6fc662ef7c2: Already exists 
  24b05d996520: Already exists 
  646b60ce888d: Already exists 
  f83de85a41f3: Already exists 
  Digest: sha256:c1af76b67814ba195529d496a445299d299379b55e001a799b2e1ee39911b9af
  Status: Downloaded newer image for 192.168.74.143:5000/tomcat:1.0
  192.168.74.143:5000/tomcat:1.0
  
  # 已经下载啦
  [root@qingchuan docker]# docker images
  REPOSITORY                   TAG       IMAGE ID       CREATED          SIZE
  192.168.74.143:5000/tomcat   1.0       f33ca07339c6   36 minutes ago   486MB
  nginx                        1.22.0    15f7b4a94c7d   6 days ago       142MB
  registry                     latest    d1fe2eaf6101   7 days ago       24.1MB
  mysql                        5.7       459651132a11   12 days ago      429MB
  hello-world                  latest    feb5d9fea6a5   10 months ago    13.3kB
  [root@qingchuan docker]# 
  ```

  

- 运行

  ```shell
  [root@qingchuan docker]# docker run -d -p 8080:8080 --name Tomcat 192.168.74.143:5000/tomcat:1.0 
  fae1e6ff65fb82ff5d86a8ff125176c150928441faf034587ad68495cff65b08
  [root@qingchuan docker]# docker ps
  CONTAINER ID   IMAGE                            COMMAND                  CREATED          STATUS          PORTS                                       NAMES
  fae1e6ff65fb   192.168.74.143:5000/tomcat:1.0   "catalina.sh run"        5 seconds ago    Up 3 seconds    0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   Tomcat
  b5a0424476de   registry                         "/entrypoint.sh /etc…"   12 minutes ago   Up 12 minutes   0.0.0.0:5000->5000/tcp, :::5000->5000/tcp   sharp_colden
  [root@qingchuan docker]# 
  
  ```

  