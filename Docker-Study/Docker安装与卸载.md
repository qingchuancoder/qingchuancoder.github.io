![Docker体系结构](Docker%E5%AE%89%E8%A3%85%E4%B8%8E%E5%8D%B8%E8%BD%BD.assets/Docker%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84.png)

## Docker安装

1. 查看系统版本

   ```shell
   cat /etc/os-release
   # 结果
   NAME="CentOS Linux"
   VERSION="7 (Core)"
   ID="centos"
   ID_LIKE="rhel fedora"
   VERSION_ID="7"
   PRETTY_NAME="CentOS Linux 7 (Core)"
   ANSI_COLOR="0;31"
   CPE_NAME="cpe:/o:centos:centos:7"
   HOME_URL="https://www.centos.org/"
   BUG_REPORT_URL="https://bugs.centos.org/"
   
   CENTOS_MANTISBT_PROJECT="CentOS-7"
   CENTOS_MANTISBT_PROJECT_VERSION="7"
   REDHAT_SUPPORT_PRODUCT="centos"
   REDHAT_SUPPORT_PRODUCT_VERSION="7"
   ```

   

2. 卸载旧版本Docker

   ```shell
   yum remove docker \
                     docker-client \
                     docker-client-latest \
                     docker-common \
                     docker-latest \
                     docker-latest-logrotate \
                     docker-logrotate \
                     docker-engine
   ```

   

3. 下载安装

   ```shell
   # 1、安装Docker的环境
   yum install -y yum-utils
   
   # 2、设置镜像仓库
   # 官方示例，慢
   yum-config-manager \
       --add-repo \
       https://download.docker.com/linux/centos/docker-ce.repo
   
   # 国内镜像，推荐
   yum-config-manager \
       --add-repo \
       http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
       
   # 阿里云服务器还可以开启Docker镜像加速，参考官网配置
       
   # 3、更新yum软件包索引
   yum makecache fast
   
   # 4、安装
   yum install docker-ce docker-ce-cli containerd.io docker-compose-plugin
   ```

   

4. 启动Docker

   ```shell
   # 启动Docker
   systemctl start docker
   
   # 查看Docker版本信息，检查是否安装成功
   docker version
   # 结果
   Client: Docker Engine - Community
    Version:           20.10.17
    API version:       1.41
    Go version:        go1.17.11
    Git commit:        100c701
    Built:             Mon Jun  6 23:05:12 2022
    OS/Arch:           linux/amd64
    Context:           default
    Experimental:      true
   
   Server: Docker Engine - Community
    Engine:
     Version:          20.10.17
     API version:      1.41 (minimum version 1.12)
     Go version:       go1.17.11
     Git commit:       a89b842
     Built:            Mon Jun  6 23:03:33 2022
     OS/Arch:          linux/amd64
     Experimental:     false
    containerd:
     Version:          1.6.6
     GitCommit:        10c12954828e7c7c9b6e0ea9b0c02b01407d3ae1
    runc:
     Version:          1.1.2
     GitCommit:        v1.1.2-0-ga916309
    docker-init:
     Version:          0.19.0
     GitCommit:        de40ad0
   ```

   

5. 运行 `hello-word`

   ```shell
   #运行 hello-world
   docker run hello-world
   # 结果
   Unable to find image 'hello-world:latest' locally
   latest: Pulling from library/hello-world
   2db29710123e: Pull complete 
   Digest: sha256:53f1bbee2f52c39e41682ee1d388285290c5c8a76cc92b42687eecf38e0af3f0
   Status: Downloaded newer image for hello-world:latest
   
   Hello from Docker!
   This message shows that your installation appears to be working correctly.
   
   To generate this message, Docker took the following steps:
    1. The Docker client contacted the Docker daemon.
    2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
       (amd64)
    3. The Docker daemon created a new container from that image which runs the
       executable that produces the output you are currently reading.
    4. The Docker daemon streamed that output to the Docker client, which sent it
       to your terminal.
   
   To try something more ambitious, you can run an Ubuntu container with:
    $ docker run -it ubuntu bash
   
   Share images, automate workflows, and more with a free Docker ID:
    https://hub.docker.com/
   
   For more examples and ideas, visit:
    https://docs.docker.com/get-started/
   
   # 查看 Docker 镜像
   docker images
   #结果
   REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
   hello-world   latest    feb5d9fea6a5   10 months ago   13.3kB
   ```



## Docker 卸载

1. 卸载

   ```shell
   yum remove docker-ce docker-ce-cli containerd.io docker-compose-plugin
   ```

   

2. 删除相关文件

   ```shell
   rm -rf /var/lib/docker
   rm -rf /var/lib/containerd
   ```

   

---

# Docker离线安装

1. 获取安装包 `docker-20.10.9.tgz`

   [Index of linux/static/stable/x86_64/ (docker.com)](https://download.docker.com/linux/static/stable/x86_64/)

2. 将安装包上传至服务器并解压

   ```shell
   [root@qingchuan Docker]# tar -xvf docker-20.10.9.tgz 
   docker/
   docker/containerd-shim-runc-v2
   docker/dockerd
   docker/docker-proxy
   docker/ctr
   docker/docker
   docker/runc
   docker/containerd-shim
   docker/docker-init
   docker/containerd
   ```

   

3. 将解压出来的docker文件移动到 `/usr/bin/` 下

   ```shell
   [root@qingchuan Docker]# ls
   docker  docker-20.10.9.tgz
   [root@qingchuan Docker]# cp docker/* /usr/bin/
   ```

   

4. 将docker注册为service，在/etc/systemd/system目录下创建docker.service文件，并配置如下内容保存。

   ```shell
   [root@qingchuan Docker]# vim /etc/systemd/system/docker.service
   
   [Unit]
   Description=Docker Application Container Engine
   Documentation=https://docs.docker.com
   After=network-online.target firewalld.service
   Wants=network-online.target
   [Service]
   Type=notify
   # the default is not to use systemd for cgroups because the delegate issues still
   # exists and systemd currently does not support the cgroup feature set required
   # for containers run by docker
   ExecStart=/usr/bin/dockerd
   ExecReload=/bin/kill -s HUP $MAINPID
   # Having non-zero Limit*s causes performance problems due to accounting overhead
   # in the kernel. We recommend using cgroups to do container-local accounting.
   LimitNOFILE=infinity
   LimitNPROC=infinity
   LimitCORE=infinity
   # Uncomment TasksMax if your systemd version supports it.
   # Only systemd 226 and above support this version.
   #TasksMax=infinity
   TimeoutStartSec=0
   # set delegate yes so that systemd does not reset the cgroups of docker containers
   Delegate=yes
   # kill only the docker process, not all processes in the cgroup
   KillMode=process
   # restart the docker process if it exits prematurely
   Restart=on-failure
   StartLimitBurst=3
   StartLimitInterval=60s
    
   [Install]
   WantedBy=multi-user.target
   ```

   

5. 添加文件权限并启动docker，执行如下命令：

   ```shell
   #添加文件权限
   chmod +x /etc/systemd/system/docker.service
   #重载unit配置文件
   systemctl daemon-reload
   #将Docker设置开机自启
   systemctl enable docker.service
   #启动Docker
   systemctl start docker
   ```

   

6. 验证docker安装是否成功：

   ```shell
   #查看Docker状态
   systemctl status docker
   #查看Docker版本
   docker -v
   ```



# 离线安装镜像

- 将镜像导出

```shell
[root@qingchuan Docker]# docker save --help

Usage:  docker save [OPTIONS] IMAGE [IMAGE...]

Save one or more images to a tar archive (streamed to STDOUT by default)

Options:
  -o, --output string   Write to a file, instead of STDOUT
[root@qingchuan Docker]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED       SIZE
nginx        1.22.0    15f7b4a94c7d   12 days ago   142MB
[root@qingchuan Docker]# docker save -o docker-nginx-1.22.0.tar nginx:1.22.0
[root@qingchuan Docker]# ls
docker  docker-20.10.9.tgz  docker-nginx-1.22.0.tar
[root@qingchuan Docker]# 

```



- 导入镜像

```shell
[root@qingchuan Docker]# docker images
REPOSITORY   TAG       IMAGE ID   CREATED   SIZE
[root@qingchuan Docker]# docker load --help

Usage:  docker load [OPTIONS]

Load an image from a tar archive or STDIN

Options:
  -i, --input string   Read from tar archive file, instead of STDIN
  -q, --quiet          Suppress the load output
[root@qingchuan Docker]# docker load -i docker-nginx-1.22.0.tar 
43b3c4e3001c: Loading layer [==================================================>]  83.87MB/83.87MB
3a8f99f3bd90: Loading layer [==================================================>]  62.03MB/62.03MB
9f803fac20f7: Loading layer [==================================================>]  3.072kB/3.072kB
42e6e8955651: Loading layer [==================================================>]  4.096kB/4.096kB
35f05d5547f4: Loading layer [==================================================>]  3.584kB/3.584kB
e36a14968fa3: Loading layer [==================================================>]  7.168kB/7.168kB
Loaded image: nginx:1.22.0
[root@qingchuan Docker]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED       SIZE
nginx        1.22.0    15f7b4a94c7d   12 days ago   142MB
[root@qingchuan Docker]# 

```

- 运行测试

```shell
[root@qingchuan Docker]# docker run --name Nginx -d -p 80:80 nginx:1.22.0
4fd8d3b5c46a488f102465adbf16471df6270bc77a458385bea0555f0c957341
[root@qingchuan Docker]# docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                               NAMES
4fd8d3b5c46a   nginx:1.22.0   "/docker-entrypoint.…"   5 seconds ago   Up 3 seconds   0.0.0.0:80->80/tcp, :::80->80/tcp   Nginx
[root@qingchuan Docker]# curl localhost:80
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
[root@qingchuan Docker]# 

```

