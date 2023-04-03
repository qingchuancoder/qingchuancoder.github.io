# Docker提交镜像

1. 启动 `tomcat` 镜像
2. 进入 `Tomcat` 容器
3. 对容器进行修改
4. 将当前容器的状态提交为镜像

```shell
# 当前镜像
[root@qingchuan ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
tomcat        8.5.81    ae1a89e0f415   30 hours ago    482MB
nginx         1.22.0    15f7b4a94c7d   3 days ago      142MB
mysql         5.7       459651132a11   10 days ago     429MB
hello-world   latest    feb5d9fea6a5   10 months ago   13.3kB

# 运行tomcat镜像
[root@qingchuan ~]# docker run -d --name Tomcat -p 8080:8080 tomcat:8.5.81 
c84d5c9655cf8ddabddfc35bbdb603cd3840055bc577b27ea5faec734fb95a8a

# 进入Tomcat容器
[root@qingchuan ~]# docker exec -it Tomcat /bin/bash
root@c84d5c9655cf:/usr/local/tomcat# ls
bin           conf             lib      logs            NOTICE     RELEASE-NOTES  temp     webapps.dist
BUILDING.txt  CONTRIBUTING.md  LICENSE  native-jni-lib  README.md  RUNNING.txt    webapps  work

# 修改容器
root@c84d5c9655cf:/usr/local/tomcat# cp -r webapps.dist/* webapps
root@c84d5c9655cf:/usr/local/tomcat# ls
bin           conf             lib      logs            NOTICE     RELEASE-NOTES  temp     webapps.dist
BUILDING.txt  CONTRIBUTING.md  LICENSE  native-jni-lib  README.md  RUNNING.txt    webapps  work
root@c84d5c9655cf:/usr/local/tomcat# cd webapps
root@c84d5c9655cf:/usr/local/tomcat/webapps# ls
docs  examples  host-manager  manager  ROOT
root@c84d5c9655cf:/usr/local/tomcat/webapps# exit
exit

# 查看提交镜像帮助命令
[root@qingchuan ~]# docker commit --help 

Usage:  docker commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]

Create a new image from a container's changes

Options:
  -a, --author string    Author (e.g., "John Hannibal Smith <hannibal@a-team.com>")
  -c, --change list      Apply Dockerfile instruction to the created image
  -m, --message string   Commit message
  -p, --pause            Pause container during commit (default true)
  
# 提交镜像  
[root@qingchuan ~]# docker commit -a "清川" -m "将webapps.dist目录文件拷贝到webapps目录" Tomcat tomcat:8.5.81 
sha256:8486cd7b845a3ae10649c5e6b05bdf7089348c677471c970bab9930113295aaf

# 查看现在的镜像
[root@qingchuan ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED          SIZE
tomcat        8.5.81    8486cd7b845a   11 seconds ago   486MB
tomcat        <none>    ae1a89e0f415   30 hours ago     482MB
nginx         1.22.0    15f7b4a94c7d   3 days ago       142MB
mysql         5.7       459651132a11   10 days ago      429MB
hello-world   latest    feb5d9fea6a5   10 months ago    13.3kB
[root@qingchuan ~]# 

```

