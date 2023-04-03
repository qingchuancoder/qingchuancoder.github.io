# 安装Jenkins

- 安装

```shell
[root@qingchuan Docker]# docker pull jenkins/jenkins:lts
lts: Pulling from jenkins/jenkins
1339eaac5b67: Pull complete 
20401c7e91bc: Pull complete 
7138cd942003: Pull complete 
6d1b42f45e89: Pull complete 
98b0e135a912: Pull complete 
ed90436583b0: Pull complete 
b0b3716848f8: Pull complete 
4035b7550508: Pull complete 
e9a1c1f127f6: Pull complete 
6137d1289fb5: Pull complete 
213d8e7e603c: Pull complete 
42b46c55d38d: Pull complete 
8324f1380818: Pull complete 
2201f3ff6253: Pull complete 
Digest: sha256:c878e1aac1f5152a6234b33a10542c7f694b7c5c37de27191d1c173800853b93
Status: Downloaded newer image for jenkins/jenkins:lts
docker.io/jenkins/jenkins:lts
[root@qingchuan Docker]# docker images
REPOSITORY        TAG       IMAGE ID       CREATED       SIZE
nginx             1.22.0    15f7b4a94c7d   12 days ago   142MB
jenkins/jenkins   lts       89b279054578   2 weeks ago   461MB
[root@qingchuan Docker]# 

```

```shell
# 将镜像导出，备用
[root@qingchuan Docker]# docker save -o docker-jenkins-lts.tar jenkins/jenkins:lts
```



- 启动

```shell
[root@qingchuan Docker]# chown -R 1000 /usr/local/Docker/Jenkins_Home/

[root@qingchuan Docker]# docker run --name Jenkins --privileged=true -d -p 8080:8080 -v /usr/local/Docker/Jenkins_Home:/var/jenkins_home jenkins/jenkins:lts
47b39fe08e92fa716ac9498af976306b1d02e0474a790b562206b41dbf35242e
[root@qingchuan Docker]# docker ps
CONTAINER ID   IMAGE                 COMMAND                  CREATED          STATUS          PORTS                                                  NAMES
47b39fe08e92   jenkins/jenkins:lts   "/usr/bin/tini -- /u…"   5 seconds ago    Up 3 seconds    0.0.0.0:8080->8080/tcp, :::8080->8080/tcp, 50000/tcp   Jenkins
# 以root权限进入容器
[root@qingchuan Docker]# docker exec -it -u root Jenkins bash

```

![image-20220801222022480](Docker%E5%AE%89%E8%A3%85Jenkins.assets/image-20220801222022480.png)

```shell
[root@qingchuan Docker]# cat /usr/local/Docker/Jenkins_Home/secrets/initialAdminPassword 
76da7df17e324f5fb5f32350a4e2f809
[root@qingchuan Docker]# 

```



- 跳过插件安装

- 创建管理员用户

  Username、Password、Confirm password、Full name

  全填写admin

- 使用admin继续 --> 保存并完成 -- > 开始



# Maven

1. 将maven压缩包上传至服务器
2. 启动Jenkins，并挂载 `docker run --name Jenkins --privileged=true -d -p 8080:8080 -v /usr/local/Docker/Jenkins_Home:/var/jenkins_home jenkins/jenkins:lts`
3. `docker ps` 查看容器是否运行
   - 没运行可能 是权限问题 `chown -R 1000 /usr/local/Docker/Jenkins_Home/`
4. 将maven压缩包移动至Jenkins挂载目录
5. 以root权限进入Jenkins容器 `docker exec -it -u root Jenkins bash`
6. 将maven压缩包解压
7. 进入Jenkins网页
8. Global Tool Configuration --> maven![image-20220802000209042](Docker%E5%AE%89%E8%A3%85Jenkins.assets/image-20220802000209042.png)
9. Configure System --> Global properties![image-20220802000121706](Docker%E5%AE%89%E8%A3%85Jenkins.assets/image-20220802000121706.png)
10. 容器内创建链接 `ln -s /opt/maven/apache-maven-3.6.1/bin/mvn /usr/bin/mvn`
11. 容器内 `mvn -v` ![image-20220802001011702](Docker%E5%AE%89%E8%A3%85Jenkins.assets/image-20220802001011702.png)



