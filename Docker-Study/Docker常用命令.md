# Docker常用命令

![Docker常用命令](Docker%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4.assets/Docker%E5%91%BD%E4%BB%A4.jpg)

## 帮助命令

### 查看命令帮助

- `docker --help` 
  - 查看docker命令帮助
- `docker images --help`
  - 查看指定命令（images命令）的帮助信息

```shell
[root@qingchuan ~]# docker --help

Usage:  docker [OPTIONS] COMMAND

A self-sufficient runtime for containers

Options:
      --config string      Location of client config files (default "/root/.docker")
  -c, --context string     Name of the context to use to connect to the daemon (overrides DOCKER_HOST env var and default context set with "docker
                           context use")
  -D, --debug              Enable debug mode
  -H, --host list          Daemon socket(s) to connect to
  -l, --log-level string   Set the logging level ("debug"|"info"|"warn"|"error"|"fatal") (default "info")
      --tls                Use TLS; implied by --tlsverify
      --tlscacert string   Trust certs signed only by this CA (default "/root/.docker/ca.pem")
      --tlscert string     Path to TLS certificate file (default "/root/.docker/cert.pem")
      --tlskey string      Path to TLS key file (default "/root/.docker/key.pem")
      --tlsverify          Use TLS and verify the remote
  -v, --version            Print version information and quit

Management Commands:
  app*        Docker App (Docker Inc., v0.9.1-beta3)
  builder     Manage builds
  buildx*     Docker Buildx (Docker Inc., v0.8.2-docker)
  compose*    Docker Compose (Docker Inc., v2.6.0)
  config      Manage Docker configs
  container   Manage containers
  context     Manage contexts
  image       Manage images
  manifest    Manage Docker image manifests and manifest lists
  network     Manage networks
  node        Manage Swarm nodes
  plugin      Manage plugins
  scan*       Docker Scan (Docker Inc., v0.17.0)
  secret      Manage Docker secrets
  service     Manage services
  stack       Manage Docker stacks
  swarm       Manage Swarm
  system      Manage Docker
  trust       Manage trust on Docker images
  volume      Manage volumes

Commands:
  attach      Attach local standard input, output, and error streams to a running container
  build       Build an image from a Dockerfile
  commit      Create a new image from a container's changes
  cp          Copy files/folders between a container and the local filesystem
  create      Create a new container
  diff        Inspect changes to files or directories on a container's filesystem
  events      Get real time events from the server
  exec        Run a command in a running container
  export      Export a container's filesystem as a tar archive
  history     Show the history of an image
  images      List images
  import      Import the contents from a tarball to create a filesystem image
  info        Display system-wide information
  inspect     Return low-level information on Docker objects
  kill        Kill one or more running containers
  load        Load an image from a tar archive or STDIN
  login       Log in to a Docker registry
  logout      Log out from a Docker registry
  logs        Fetch the logs of a container
  pause       Pause all processes within one or more containers
  port        List port mappings or a specific mapping for the container
  ps          List containers
  pull        Pull an image or a repository from a registry
  push        Push an image or a repository to a registry
  rename      Rename a container
  restart     Restart one or more containers
  rm          Remove one or more containers
  rmi         Remove one or more images
  run         Run a command in a new container
  save        Save one or more images to a tar archive (streamed to STDOUT by default)
  search      Search the Docker Hub for images
  start       Start one or more stopped containers
  stats       Display a live stream of container(s) resource usage statistics
  stop        Stop one or more running containers
  tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
  top         Display the running processes of a container
  unpause     Unpause all processes within one or more containers
  update      Update configuration of one or more containers
  version     Show the Docker version information
  wait        Block until one or more containers stop, then print their exit codes

Run 'docker COMMAND --help' for more information on a command.

To get more help with docker, check out our guides at https://docs.docker.com/go/guides/
[root@qingchuan ~]# 
```

### 查看 Docker 版本信息

- `docker version`

```shell
[root@qingchuan ~]# docker version
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
[root@qingchuan ~]# 
```

### 查看 Docker 详细信息

- `docker info`

```shell
[root@qingchuan ~]# docker info
Client:
 Context:    default
 Debug Mode: false
 Plugins:
  app: Docker App (Docker Inc., v0.9.1-beta3)
  buildx: Docker Buildx (Docker Inc., v0.8.2-docker)
  compose: Docker Compose (Docker Inc., v2.6.0)
  scan: Docker Scan (Docker Inc., v0.17.0)

Server:
 Containers: 1
  Running: 0
  Paused: 0
  Stopped: 1
 Images: 1
 Server Version: 20.10.17
 Storage Driver: overlay2
  Backing Filesystem: xfs
  Supports d_type: true
  Native Overlay Diff: true
  userxattr: false
 Logging Driver: json-file
 Cgroup Driver: cgroupfs
 Cgroup Version: 1
 Plugins:
  Volume: local
  Network: bridge host ipvlan macvlan null overlay
  Log: awslogs fluentd gcplogs gelf journald json-file local logentries splunk syslog
 Swarm: inactive
 Runtimes: io.containerd.runc.v2 io.containerd.runtime.v1.linux runc
 Default Runtime: runc
 Init Binary: docker-init
 containerd version: 10c12954828e7c7c9b6e0ea9b0c02b01407d3ae1
 runc version: v1.1.2-0-ga916309
 init version: de40ad0
 Security Options:
  seccomp
   Profile: default
 Kernel Version: 3.10.0-1160.el7.x86_64
 Operating System: CentOS Linux 7 (Core)
 OSType: linux
 Architecture: x86_64
 CPUs: 1
 Total Memory: 972.3MiB
 Name: qingchuan
 ID: FP4E:PRDV:JE6E:NEVE:NVAB:MNAM:PDGU:TQUS:5GUC:CKQO:PPUS:E54Q
 Docker Root Dir: /var/lib/docker
 Debug Mode: false
 Registry: https://index.docker.io/v1/
 Labels:
 Experimental: false
 Insecure Registries:
  127.0.0.0/8
 Live Restore Enabled: false

[root@qingchuan ~]# 
```

## 镜像命令

### 镜像列表

| 项         | 说明             |
| ---------- | ---------------- |
| REPOSITORY | 镜像的仓库源     |
| TAG        | 镜像标签（版本） |
| IMAGE ID   | 镜像ID           |
| CREATED    | 镜像创建时间     |
| SIZE       | 镜像大小         |



- `docker images`
  - `-a` 展示所有镜像
  - `-q` 只显示镜像ID
  - `-aq` 显示所有镜像ID

```shell
[root@qingchuan ~]# docker images --help

Usage:  docker images [OPTIONS] [REPOSITORY[:TAG]]

List images

Options:
  -a, --all             Show all images (default hides intermediate images)
      --digests         Show digests
  -f, --filter filter   Filter output based on conditions provided
      --format string   Pretty-print images using a Go template
      --no-trunc        Don't truncate output
  -q, --quiet           Only show image IDs
[root@qingchuan ~]#  docker images
REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
hello-world   latest    feb5d9fea6a5   10 months ago   13.3kB
[root@qingchuan ~]# docker images -qa
feb5d9fea6a5
[root@qingchuan ~]# 
```

### 搜索镜像

- `docker search 镜像`
- **建议在 `Docker Hub` 网页搜索，显示信息更全面**
- [Docker Hub Container Image Library | App Containerization](https://hub.docker.com/)

```shell
# 帮助
[root@qingchuan ~]# docker search --help

Usage:  docker search [OPTIONS] TERM

Search the Docker Hub for images

Options:
  -f, --filter filter   Filter output based on conditions provided
      --format string   Pretty-print search using a Go template
      --limit int       Max number of search results (default 25)
      --no-trunc        Don't truncate output

# 查询MySQL镜像
[root@qingchuan ~]# docker search mysql
NAME                           DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
mysql                          MySQL is a widely used, open-source relation…   12899     [OK]       
mariadb                        MariaDB Server is a high performing open sou…   4945      [OK]       

# 查询STARS3000以上的MySQL镜像
[root@qingchuan ~]# docker search --filter STARS=3000 mysql
NAME      DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
mysql     MySQL is a widely used, open-source relation…   12899     [OK]       
mariadb   MariaDB Server is a high performing open sou…   4945      [OK]       
[root@qingchuan ~]# 
```

### 下载镜像

- `docker pull 镜像:TAG` 或 `docker pull 镜像|DIGEST`
  - 下载指定标签或摘要的镜像
  - 标签和摘要可选
  - **一般会通过标签指定版本,但指定的版本一定得在Docker Hub存在**
  - **默认下载最新版**
- `docker pull mysql:5.7`
  - **docker 下载镜像是分层下载的**
  - **已经存在的层不再下载**
  - `docker.io/library/mysql:5.7` --> 镜像地址
- `docker images` 查看已下载的镜像

```shell
[root@qingchuan ~]# docker  pull --help

Usage:  docker pull [OPTIONS] NAME[:TAG|@DIGEST]

Pull an image or a repository from a registry

Options:
  -a, --all-tags                Download all tagged images in the repository
      --disable-content-trust   Skip image verification (default true)
      --platform string         Set platform if server is multi-platform capable
  -q, --quiet                   Suppress verbose output

# 下载MySQL5.7
[root@qingchuan ~]# docker pull mysql:5.7
5.7: Pulling from library/mysql
66fb34780033: Pull complete 
ef4ccd63cdb4: Pull complete 
d6f28a94c51f: Pull complete 
7feea2a503b5: Pull complete 
71dd5852ecd9: Pull complete 
3da2c95cac2f: Pull complete 
af7913db289c: Pull complete 
77f552f93c12: Pull complete 
3ed53edb61ab: Pull complete 
67e1c6839f08: Pull complete 
abcdaaf08d0f: Pull complete 
Digest: sha256:bbe0e2b0a33ef5c3a983e490dcb3c1a42d623db1d5679e82f65cce3f32c8f254
Status: Downloaded newer image for mysql:5.7
docker.io/library/mysql:5.7

# 下载MySQL最新版
[root@qingchuan ~]# docker pull mysql
Using default tag: latest
latest: Pulling from library/mysql
e54b73e95ef3: Pull complete 
327840d38cb2: Pull complete 
642077275f5f: Pull complete 
e077469d560d: Pull complete 
cbf214d981a6: Pull complete 
7d1cc1ea1b3d: Pull complete 
d48f3c15cb80: Pull complete 
94c3d7b2c9ae: Pull complete 
f6cfbf240ed7: Pull complete 
e12b159b2a12: Pull complete 
4e93c6fd777f: Pull complete 
Digest: sha256:152cf187a3efc56afb0b3877b4d21e231d1d6eb828ca9221056590b0ac834c75
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest

# 查看已下载镜像
[root@qingchuan ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
mysql         5.7       459651132a11   8 days ago      429MB
mysql         latest    33037edcac9b   8 days ago      444MB
hello-world   latest    feb5d9fea6a5   10 months ago   13.3kB
[root@qingchuan ~]# 
```

### 查看镜像/容器/数据卷所占的空间

```shell
[root@qingchuan ~]# docker system df
TYPE            TOTAL     ACTIVE    SIZE      RECLAIMABLE
Images          5         1         1.057GB   627.9MB (59%)
Containers      1         0         62B       62B (100%)
Local Volumes   13        1         1.241GB   1.035GB (83%)
Build Cache     0         0         0B        0B
[root@qingchuan ~]# 

```



### 删除镜像

- `docker rmi 镜像1 镜像2 ...`
  - `-f` 强制删除
  - 可以通过镜像名或镜像ID删除
- ` docker rmi -f $(docker search -qa)`
  - 删除所有镜像

```shell
[root@qingchuan ~]# docker rmi --help

Usage:  docker rmi [OPTIONS] IMAGE [IMAGE...]

Remove one or more images

Options:
  -f, --force      Force removal of the image
      --no-prune   Do not delete untagged parents
[root@qingchuan ~]# docker rmi hello-world:latest

```



## 容器命令

### 新建容器并启动

- 启动容器需要镜像

  - `--name` 为容器指定名称

  - `-d` 在后台运行容器并打印容器ID

  - `-it` 以交互方式运行容器

  - `-p`  向宿主机发布容器的端口

    ```shell
    # 指定容器端口四种方式
    -p 宿主机端口:容器端口 # 常用
    -p 宿主机IP:主机端口:容器端口
    -p 容器端口
    端口
    ```

    

  - `-P` 将所有暴露的端口发布到随机端口

```shell
[root@qingchuan ~]# docker run --help

Usage:  docker run [OPTIONS] IMAGE [COMMAND] [ARG...]

Run a command in a new container

Options:
      --add-host list                  Add a custom host-to-IP mapping (host:ip)
  -a, --attach list                    Attach to STDIN, STDOUT or STDERR
      --blkio-weight uint16            Block IO (relative weight), between 10 and 1000, or 0 to disable (default 0)
      --blkio-weight-device list       Block IO weight (relative device weight) (default [])
      --cap-add list                   Add Linux capabilities
      --cap-drop list                  Drop Linux capabilities
      --cgroup-parent string           Optional parent cgroup for the container
      --cgroupns string                Cgroup namespace to use (host|private)
                                       'host':    Run the container in the Docker host's cgroup namespace
                                       'private': Run the container in its own private cgroup namespace
                                       '':        Use the cgroup namespace as configured by the
                                                  default-cgroupns-mode option on the daemon (default)
      --cidfile string                 Write the container ID to the file
      --cpu-period int                 Limit CPU CFS (Completely Fair Scheduler) period
      --cpu-quota int                  Limit CPU CFS (Completely Fair Scheduler) quota
      --cpu-rt-period int              Limit CPU real-time period in microseconds
      --cpu-rt-runtime int             Limit CPU real-time runtime in microseconds
  -c, --cpu-shares int                 CPU shares (relative weight)
      --cpus decimal                   Number of CPUs
      --cpuset-cpus string             CPUs in which to allow execution (0-3, 0,1)
      --cpuset-mems string             MEMs in which to allow execution (0-3, 0,1)
  -d, --detach                         Run container in background and print container ID
      --detach-keys string             Override the key sequence for detaching a container
      --device list                    Add a host device to the container
      --device-cgroup-rule list        Add a rule to the cgroup allowed devices list
      --device-read-bps list           Limit read rate (bytes per second) from a device (default [])
      --device-read-iops list          Limit read rate (IO per second) from a device (default [])
      --device-write-bps list          Limit write rate (bytes per second) to a device (default [])
      --device-write-iops list         Limit write rate (IO per second) to a device (default [])
      --disable-content-trust          Skip image verification (default true)
      --dns list                       Set custom DNS servers
      --dns-option list                Set DNS options
      --dns-search list                Set custom DNS search domains
      --domainname string              Container NIS domain name
      --entrypoint string              Overwrite the default ENTRYPOINT of the image
  -e, --env list                       Set environment variables
      --env-file list                  Read in a file of environment variables
      --expose list                    Expose a port or a range of ports
      --gpus gpu-request               GPU devices to add to the container ('all' to pass all GPUs)
      --group-add list                 Add additional groups to join
      --health-cmd string              Command to run to check health
      --health-interval duration       Time between running the check (ms|s|m|h) (default 0s)
      --health-retries int             Consecutive failures needed to report unhealthy
      --health-start-period duration   Start period for the container to initialize before starting health-retries countdown (ms|s|m|h) (default 0s)
      --health-timeout duration        Maximum time to allow one check to run (ms|s|m|h) (default 0s)
      --help                           Print usage
  -h, --hostname string                Container host name
      --init                           Run an init inside the container that forwards signals and reaps processes
  -i, --interactive                    Keep STDIN open even if not attached
      --ip string                      IPv4 address (e.g., 172.30.100.104)
      --ip6 string                     IPv6 address (e.g., 2001:db8::33)
      --ipc string                     IPC mode to use
      --isolation string               Container isolation technology
      --kernel-memory bytes            Kernel memory limit
  -l, --label list                     Set meta data on a container
      --label-file list                Read in a line delimited file of labels
      --link list                      Add link to another container
      --link-local-ip list             Container IPv4/IPv6 link-local addresses
      --log-driver string              Logging driver for the container
      --log-opt list                   Log driver options
      --mac-address string             Container MAC address (e.g., 92:d0:c6:0a:29:33)
  -m, --memory bytes                   Memory limit
      --memory-reservation bytes       Memory soft limit
      --memory-swap bytes              Swap limit equal to memory plus swap: '-1' to enable unlimited swap
      --memory-swappiness int          Tune container memory swappiness (0 to 100) (default -1)
      --mount mount                    Attach a filesystem mount to the container
      --name string                    Assign a name to the container
      --network network                Connect a container to a network
      --network-alias list             Add network-scoped alias for the container
      --no-healthcheck                 Disable any container-specified HEALTHCHECK
      --oom-kill-disable               Disable OOM Killer
      --oom-score-adj int              Tune host's OOM preferences (-1000 to 1000)
      --pid string                     PID namespace to use
      --pids-limit int                 Tune container pids limit (set -1 for unlimited)
      --platform string                Set platform if server is multi-platform capable
      --privileged                     Give extended privileges to this container
  -p, --publish list                   Publish a container's port(s) to the host
  -P, --publish-all                    Publish all exposed ports to random ports
      --pull string                    Pull image before running ("always"|"missing"|"never") (default "missing")
      --read-only                      Mount the container's root filesystem as read only
      --restart string                 Restart policy to apply when a container exits (default "no")
      --rm                             Automatically remove the container when it exits
      --runtime string                 Runtime to use for this container
      --security-opt list              Security Options
      --shm-size bytes                 Size of /dev/shm
      --sig-proxy                      Proxy received signals to the process (default true)
      --stop-signal string             Signal to stop a container (default "SIGTERM")
      --stop-timeout int               Timeout (in seconds) to stop a container
      --storage-opt list               Storage driver options for the container
      --sysctl map                     Sysctl options (default map[])
      --tmpfs list                     Mount a tmpfs directory
  -t, --tty                            Allocate a pseudo-TTY
      --ulimit ulimit                  Ulimit options (default [])
  -u, --user string                    Username or UID (format: <name|uid>[:<group|gid>])
      --userns string                  User namespace to use
      --uts string                     UTS namespace to use
  -v, --volume list                    Bind mount a volume
      --volume-driver string           Optional volume driver for the container
      --volumes-from list              Mount volumes from the specified container(s)
  -w, --workdir string                 Working directory inside the container
[root@qingchuan ~]# 
```

### 查看运行的容器

- `docker ps` 查看正在运行的容器

- `docker ps -aq` 查看所有的容器，仅显示容器ID
- `docker ps -a` 查看所有容器
- `docker ps -n 5` 显示最近创建的5个容器(包括所有状态)(默认值-1)

```shell
# 帮助
[root@qingchuan ~]# docker ps --help

Usage:  docker ps [OPTIONS]

List containers

Options:
  -a, --all             Show all containers (default shows just running)
  -f, --filter filter   Filter output based on conditions provided
      --format string   Pretty-print containers using a Go template
  -n, --last int        Show n last created containers (includes all states) (default -1)
  -l, --latest          Show the latest created container (includes all states)
      --no-trunc        Don't truncate output
  -q, --quiet           Only display container IDs
  -s, --size            Display total file sizes
  
# 查看正在运行的容器
[root@qingchuan ~]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES

# 查看所有的容器，仅显示容器ID
[root@qingchuan ~]# docker ps -aq
1b7319e55151

# 查看所有容器
[root@qingchuan ~]# docker ps -a
CONTAINER ID   IMAGE         COMMAND    CREATED             STATUS                         PORTS     NAMES
1b7319e55151   hello-world   "/hello"   About an hour ago   Exited (0) About an hour ago             confident_khorana

# 显示最近创建的容器(包括所有状态)(默认值-1);
# -n 5 表示显示最近5条
[root@qingchuan ~]# docker ps -n 5
CONTAINER ID   IMAGE         COMMAND    CREATED             STATUS                         PORTS     NAMES
1b7319e55151   hello-world   "/hello"   About an hour ago   Exited (0) About an hour ago             confident_khorana
[root@qingchuan ~]# 
```

### 退出容器

```shell
# 直接退出并停止运行容器
exit
# 退出容器，但容器仍然运行
Ctrl + P + Q
```

### 删除容器

- `docker rm 容器名称` 或 `docker rm 容器ID` 删除指定容器
  - 若容器正在运行，则删除失败
  - `-f` 强制删除，运行中的容器也能删除
- `docker rm -f $(docker ps -aq)` 删除所有容器
- `docker ps -aq | xargs docker rm` 删除所有容器

```shell
# 查看帮助信息
[root@qingchuan ~]# docker rm --help

Usage:  docker rm [OPTIONS] CONTAINER [CONTAINER...]

Remove one or more containers

Options:
  -f, --force     Force the removal of a running container (uses SIGKILL)
  -l, --link      Remove the specified link
  -v, --volumes   Remove anonymous volumes associated with the container

# 查看所有容器
[root@qingchuan ~]# docker ps -a
CONTAINER ID   IMAGE         COMMAND    CREATED          STATUS                      PORTS     NAMES
5b53e26577d6   hello-world   "/hello"   10 minutes ago   Exited (0) 10 minutes ago             dreamy_shamir
1b7319e55151   hello-world   "/hello"   4 hours ago      Exited (0) 4 hours ago                confident_khorana

# 通过容器名称删除容器
[root@qingchuan ~]# docker rm dreamy_shamir
dreamy_shamir

#通过容器ID删除容器
[root@qingchuan ~]# docker rm 1b7319e55151
1b7319e55151

[root@qingchuan ~]# docker ps -a
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@qingchuan ~]# 
```

### 启动、停止容器

```shell
# 启动容器
[root@qingchuan ~]# docker start --help

Usage:  docker start [OPTIONS] CONTAINER [CONTAINER...]

Start one or more stopped containers

Options:
  -a, --attach               Attach STDOUT/STDERR and forward signals
      --detach-keys string   Override the key sequence for detaching a container
  -i, --interactive          Attach container's STDIN

# 停止运行的容器
[root@qingchuan ~]# docker stop --help

Usage:  docker stop [OPTIONS] CONTAINER [CONTAINER...]

Stop one or more running containers

Options:
  -t, --time int   Seconds to wait for stop before killing it (default 10)

# 重启容器
[root@qingchuan ~]# docker restart --help

Usage:  docker restart [OPTIONS] CONTAINER [CONTAINER...]

Restart one or more containers

Options:
  -t, --time int   Seconds to wait for stop before killing the container (default 10)

# 杀死运行中的容器
[root@qingchuan ~]# docker kill --help

Usage:  docker kill [OPTIONS] CONTAINER [CONTAINER...]

Kill one or more running containers

Options:
  -s, --signal string   Signal to send to the container (default "KILL")
[root@qingchuan ~]# 

```

### 将容器设置为自启动

```shell
# docker update 容器ID/容器名称 --restart=always
[root@qingchuan ~]# docker update 10d --restart=always
```



# 常用其他命令

## 后台启动

```shell
docker run -d 镜像名
```

> # 坑
>
> 以后台方式启动容器，在使用 `docker ps` 命令查看时，发现容器没有运行！！！
>
> # 原因
>
> **以后台方式启动的容器，必须要有前台进程，docker 发现其没有前台进程，未提供服务，则自动停止该容器**



## 查看日志

- `docker logs 容器`
  - `--tail 5` 查看末尾 5条日志

```shell
[root@qingchuan ~]# docker logs --help

Usage:  docker logs [OPTIONS] CONTAINER

Fetch the logs of a container

Options:
      --details        Show extra details provided to logs
  -f, --follow         Follow log output
      --since string   Show logs since timestamp (e.g. 2013-01-02T13:23:37Z) or relative (e.g. 42m for 42 minutes)
  -n, --tail string    Number of lines to show from the end of the logs (default "all")
  -t, --timestamps     Show timestamps
      --until string   Show logs before a timestamp (e.g. 2013-01-02T13:23:37Z) or relative (e.g. 42m for 42 minutes)
[root@qingchuan ~]# 
```



## 查看容器中的进程信息

```shell
[root@qingchuan ~]# docker top --help

Usage:  docker top CONTAINER [ps OPTIONS]

Display the running processes of a container
[root@qingchuan ~]# 
```



## 查看容器元数据

```shell
[root@qingchuan ~]# docker inspect --help

Usage:  docker inspect [OPTIONS] NAME|ID [NAME|ID...]

Return low-level information on Docker objects

Options:
  -f, --format string   Format the output using the given Go template
  -s, --size            Display total file sizes if the type is container
      --type string     Return JSON for specified type

# docker inspect 容器
# 查看 hello-world 的元数据
[root@qingchuan ~]# docker inspect 8611ed82b882
[
    {
        "Id": "8611ed82b882c261d2f96d17fc6213259a7d9f5cf3f37b64fcf36eb21aae31a1",
        "Created": "2022-07-22T07:19:43.014115706Z",
        "Path": "/hello",
        "Args": [],
        "State": {
            "Status": "exited",
            "Running": false,
            "Paused": false,
            "Restarting": false,
            "OOMKilled": false,
            "Dead": false,
            "Pid": 0,
            "ExitCode": 0,
            "Error": "",
            "StartedAt": "2022-07-22T07:20:07.603972054Z",
            "FinishedAt": "2022-07-22T07:20:07.60324539Z"
        },
        "Image": "sha256:feb5d9fea6a5e9606aa995e879d862b825965ba48de054caab5ef356dc6b3412",
        "ResolvConfPath": "/var/lib/docker/containers/8611ed82b882c261d2f96d17fc6213259a7d9f5cf3f37b64fcf36eb21aae31a1/resolv.conf",
        "HostnamePath": "/var/lib/docker/containers/8611ed82b882c261d2f96d17fc6213259a7d9f5cf3f37b64fcf36eb21aae31a1/hostname",
        "HostsPath": "/var/lib/docker/containers/8611ed82b882c261d2f96d17fc6213259a7d9f5cf3f37b64fcf36eb21aae31a1/hosts",
        "LogPath": "/var/lib/docker/containers/8611ed82b882c261d2f96d17fc6213259a7d9f5cf3f37b64fcf36eb21aae31a1/8611ed82b882c261d2f96d17fc6213259a7d9f5cf3f37b64fcf36eb21aae31a1-json.log",
        "Name": "/gallant_easley",
        "RestartCount": 0,
        "Driver": "overlay2",
        "Platform": "linux",
        "MountLabel": "",
        "ProcessLabel": "",
        "AppArmorProfile": "",
        "ExecIDs": null,
        "HostConfig": {
            "Binds": null,
            "ContainerIDFile": "",
            "LogConfig": {
                "Type": "json-file",
                "Config": {}
            },
            "NetworkMode": "default",
            "PortBindings": {},
            "RestartPolicy": {
                "Name": "no",
                "MaximumRetryCount": 0
            },
            "AutoRemove": false,
            "VolumeDriver": "",
            "VolumesFrom": null,
            "CapAdd": null,
            "CapDrop": null,
            "CgroupnsMode": "host",
            "Dns": [],
            "DnsOptions": [],
            "DnsSearch": [],
            "ExtraHosts": null,
            "GroupAdd": null,
            "IpcMode": "private",
            "Cgroup": "",
            "Links": null,
            "OomScoreAdj": 0,
            "PidMode": "",
            "Privileged": false,
            "PublishAllPorts": false,
            "ReadonlyRootfs": false,
            "SecurityOpt": null,
            "UTSMode": "",
            "UsernsMode": "",
            "ShmSize": 67108864,
            "Runtime": "runc",
            "ConsoleSize": [
                0,
                0
            ],
            "Isolation": "",
            "CpuShares": 0,
            "Memory": 0,
            "NanoCpus": 0,
            "CgroupParent": "",
            "BlkioWeight": 0,
            "BlkioWeightDevice": [],
            "BlkioDeviceReadBps": null,
            "BlkioDeviceWriteBps": null,
            "BlkioDeviceReadIOps": null,
            "BlkioDeviceWriteIOps": null,
            "CpuPeriod": 0,
            "CpuQuota": 0,
            "CpuRealtimePeriod": 0,
            "CpuRealtimeRuntime": 0,
            "CpusetCpus": "",
            "CpusetMems": "",
            "Devices": [],
            "DeviceCgroupRules": null,
            "DeviceRequests": null,
            "KernelMemory": 0,
            "KernelMemoryTCP": 0,
            "MemoryReservation": 0,
            "MemorySwap": 0,
            "MemorySwappiness": null,
            "OomKillDisable": false,
            "PidsLimit": null,
            "Ulimits": null,
            "CpuCount": 0,
            "CpuPercent": 0,
            "IOMaximumIOps": 0,
            "IOMaximumBandwidth": 0,
            "MaskedPaths": [
                "/proc/asound",
                "/proc/acpi",
                "/proc/kcore",
                "/proc/keys",
                "/proc/latency_stats",
                "/proc/timer_list",
                "/proc/timer_stats",
                "/proc/sched_debug",
                "/proc/scsi",
                "/sys/firmware"
            ],
            "ReadonlyPaths": [
                "/proc/bus",
                "/proc/fs",
                "/proc/irq",
                "/proc/sys",
                "/proc/sysrq-trigger"
            ]
        },
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/a891a0c3cbde020f8bb098c6d419e3ecaeea2f89603a55465be392d565b5e58c-init/diff:/var/lib/docker/overlay2/67f37c10d9e960d62d23405143bbd954abfe9d9f59068c419ce0bf994db2a707/diff",
                "MergedDir": "/var/lib/docker/overlay2/a891a0c3cbde020f8bb098c6d419e3ecaeea2f89603a55465be392d565b5e58c/merged",
                "UpperDir": "/var/lib/docker/overlay2/a891a0c3cbde020f8bb098c6d419e3ecaeea2f89603a55465be392d565b5e58c/diff",
                "WorkDir": "/var/lib/docker/overlay2/a891a0c3cbde020f8bb098c6d419e3ecaeea2f89603a55465be392d565b5e58c/work"
            },
            "Name": "overlay2"
        },
        "Mounts": [],
        "Config": {
            "Hostname": "8611ed82b882",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": true,
            "AttachStderr":  true,
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
            ],
            "Cmd": [
                "/hello"
            ],
            "Image": "hello-world",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": {}
        },
        "NetworkSettings": {
            "Bridge": "",
            "SandboxID": "69307c530514509e86a7e45ffdecc725ea98fed329ae7fba3bd3b01ad812428e",
            "HairpinMode": false,
            "LinkLocalIPv6Address": "",
            "LinkLocalIPv6PrefixLen": 0,
            "Ports": {},
            "SandboxKey": "/var/run/docker/netns/69307c530514",
            "SecondaryIPAddresses": null,
            "SecondaryIPv6Addresses": null,
            "EndpointID": "",
            "Gateway": "",
            "GlobalIPv6Address": "",
            "GlobalIPv6PrefixLen": 0,
            "IPAddress": "",
            "IPPrefixLen": 0,
            "IPv6Gateway": "",
            "MacAddress": "",
            "Networks": {
                "bridge": {
                    "IPAMConfig": null,
                    "Links": null,
                    "Aliases": null,
                    "NetworkID": "d7fa2c189493f99cb32af7a37b29f9f728ec200d639bcf4b0eab0d3e5801f0b8",
                    "EndpointID": "",
                    "Gateway": "",
                    "IPAddress": "",
                    "IPPrefixLen": 0,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "",
                    "DriverOpts": null
                }
            }
        }
    }
]
[root@qingchuan ~]# 
```

## 进入运行的容器

在运行的容器中运行命令

```shell
[root@qingchuan ~]# docker exec --help

Usage:  docker exec [OPTIONS] CONTAINER COMMAND [ARG...]

Run a command in a running container

Options:
  -d, --detach               Detached mode: run command in the background
      --detach-keys string   Override the key sequence for detaching a container
  -e, --env list             Set environment variables
      --env-file list        Read in a file of environment variables
  -i, --interactive          Keep STDIN open even if not attached
      --privileged           Give extended privileges to the command
  -t, --tty                  Allocate a pseudo-TTY
  -u, --user string          Username or UID (format: <name|uid>[:<group|gid>])
  -w, --workdir string       Working directory inside the container
[root@qingchuan ~]#
```

将本地标准输入、输出和错误流附加到正在运行的容器

```shell
[root@qingchuan ~]# docker attach --help

Usage:  docker attach [OPTIONS] CONTAINER

Attach local standard input, output, and error streams to a running container

Options:
      --detach-keys string   Override the key sequence for detaching a container
      --no-stdin             Do not attach STDIN
      --sig-proxy            Proxy all received signals to the process (default true)
[root@qingchuan ~]# 
```



> # attach 和 exec 的差异
>
> - attach 直接进入容器启动命令的终端，不启动新的进程；使用exit退出时，会停止容器
> - exec 在容器中打开新的终端，并启动新的 进程；使用exit退出，不停止容器
>
> **推荐使用exec**

## 在容器和本地文件系统之间复制文件/文件夹

```shell
[root@qingchuan ~]# docker cp --help

Usage:  docker cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH|-
        docker cp [OPTIONS] SRC_PATH|- CONTAINER:DEST_PATH

Copy files/folders between a container and the local filesystem

Use '-' as the source to read a tar archive from stdin
and extract it to a directory destination in a container.
Use '-' as the destination to stream a tar archive of a
container source to stdout.

Options:
  -a, --archive       Archive mode (copy all uid/gid information)
  -L, --follow-link   Always follow symbol link in SRC_PATH
[root@qingchuan ~]# 
```

#### 将宿主机的文件复制到容器内

```shell
# 在宿主机中创建文件
[root@qingchuan Docker]# vim LocalToContainer.md
[root@qingchuan Docker]# cat LocalToContainer.md 
将宿主机的文件复制到容器内

# 将宿主机的文件复制到容器内
[root@qingchuan Docker]# docker cp /usr/local/Docker/LocalToContainer.md Tomcat:/usr/local/tomcat/Test/
[root@qingchuan Docker]# 

# 进入容器 
[root@qingchuan Docker]# docker exec -it Tomcat bash

# 查看文件
root@55d8cbde1759:/usr/local/tomcat# cd Test/
root@55d8cbde1759:/usr/local/tomcat/Test# ls
LocalToContainer.md
root@55d8cbde1759:/usr/local/tomcat/Test# cat LocalToContainer.md 
将宿主机的文件复制到容器内
root@55d8cbde1759:/usr/local/tomcat/Test# 
```

####  修改宿主机的文件

```shell
# 修改宿主机文件
[root@qingchuan Docker]# vim LocalToContainer.md
[root@qingchuan Docker]# cat LocalToContainer.md 
将宿主机的文件复制到容器内
修改测试
[root@qingchuan Docker]# 

# 容器内查看
root@55d8cbde1759:/usr/local/tomcat/Test# cat LocalToContainer.md 
将宿主机的文件复制到容器内
root@55d8cbde1759:/usr/local/tomcat/Test# 
```

---

#### 将容器内的文件复制到宿主机

```shell
# 在容器内创建文件
root@55d8cbde1759:/usr/local/tomcat/Test# echo 将容器内的文件复制到宿主机 > ContainerToLocal.md
root@55d8cbde1759:/usr/local/tomcat/Test# cat ContainerToLocal.md 
将容器内的文件复制到宿主机

# 将容器内的文件复制到宿主机
[root@qingchuan Docker]# docker cp Tomcat:/usr/local/tomcat/Test/ContainerToLocal.md /usr/local/Docker/
[root@qingchuan Docker]# ls
ContainerToLocal.md  LocalToContainer.md
[root@qingchuan Docker]# cat ContainerToLocal.md 
将容器内的文件复制到宿主机
[root@qingchuan Docker]# 
```



#### 修改容器内的文件

```shell
# 修改容器内文件
root@55d8cbde1759:/usr/local/tomcat/Test# echo 修改测试 >> ContainerToLocal.md
root@55d8cbde1759:/usr/local/tomcat/Test# cat ContainerToLocal.md 
将容器内的文件复制到宿主机
修改测试
root@55d8cbde1759:/usr/local/tomcat/Test#

# 宿主机查看
[root@qingchuan Docker]# cat ContainerToLocal.md 
将容器内的文件复制到宿主机
[root@qingchuan Docker]# 
```



> **docker cp 命令只是复制，并不会将文件同步**



## 导出容器

```shell
[root@qingchuan Docker]# docker export --help

Usage:  docker export [OPTIONS] CONTAINER

Export a container's filesystem as a tar archive

Options:
  -o, --output string   Write to a file, instead of STDOUT
  

[root@qingchuan Docker]# docker export Tomcat > TomcatExportTest.tar
[root@qingchuan Docker]# ls
ContainerToLocal.md  LocalToContainer.md  TomcatExportTest.tar
[root@qingchuan Docker]# 

```

```shell
[root@qingchuan Docker]# docker export --help

Usage:  docker export [OPTIONS] CONTAINER

Export a container's filesystem as a tar archive

Options:
  -o, --output string   Write to a file, instead of STDOUT
  
# docker export -o 指定文件名.tar Tomcat  
[root@qingchuan Docker]# docker export -o TomcatExportTest.tar Tomcat
[root@qingchuan Docker]# ls
ContainerToLocal.md  LocalToContainer.md  TomcatExportTest.tar
[root@qingchuan Docker]# 
```



## 导入容器

```shell
[root@qingchuan Docker]# docker import --help

Usage:  docker import [OPTIONS] file|URL|- [REPOSITORY[:TAG]]

Import the contents from a tarball to create a filesystem image

Options:
  -c, --change list       Apply Dockerfile instruction to the created image
  -m, --message string    Set commit message for imported image
      --platform string   Set platform if server is multi-platform capable
      
# 镜像名必须小写
[root@qingchuan Docker]# cat TomcatExportTest.tar | docker import - qingchuan/TomcatImportTest:1.0
invalid reference format: repository name must be lowercase

# cat 文件 | docker import - 镜像用户/镜像名:TAG
[root@qingchuan Docker]# cat TomcatExportTest.tar | docker import - qingchuan/tomcatimporttest:1.0
sha256:61149a375b2e85b1cc190b190435ef9954b2946ff711551b47da50400e20aeca

# 查看镜像
[root@qingchuan Docker]# docker images
REPOSITORY                   TAG       IMAGE ID       CREATED         SIZE
qingchuan/tomcatimporttest   1.0       61149a375b2e   8 seconds ago   477MB
tomcat                       8.5.81    8486cd7b845a   2 days ago      486MB
tomcat                       <none>    ae1a89e0f415   3 days ago      482MB
nginx                        1.22.0    15f7b4a94c7d   5 days ago      142MB
mysql                        5.7       459651132a11   12 days ago     429MB
hello-world                  latest    feb5d9fea6a5   10 months ago   13.3kB
[root@qingchuan Docker]# 

```

```shell
[root@qingchuan Docker]# docker import --help

Usage:  docker import [OPTIONS] file|URL|- [REPOSITORY[:TAG]]

Import the contents from a tarball to create a filesystem image

Options:
  -c, --change list       Apply Dockerfile instruction to the created image
  -m, --message string    Set commit message for imported image
      --platform string   Set platform if server is multi-platform capable
      
      
[root@qingchuan Docker]# docker import TomcatExportTest.tar qingchuan/tomcatimporttest:2.0
sha256:d04de81413dd3f348896cfb7940fe399f4818d7d193c5a90bef9ff1ac94101b8
[root@qingchuan Docker]# docker images
REPOSITORY                   TAG       IMAGE ID       CREATED          SIZE
qingchuan/tomcatimporttest   2.0       d04de81413dd   23 seconds ago   477MB
qingchuan/tomcatimporttest   1.0       61149a375b2e   22 minutes ago   477MB
tomcat                       8.5.81    8486cd7b845a   2 days ago       486MB
tomcat                       <none>    ae1a89e0f415   3 days ago       482MB
nginx                        1.22.0    15f7b4a94c7d   5 days ago       142MB
mysql                        5.7       459651132a11   12 days ago      429MB
hello-world                  latest    feb5d9fea6a5   10 months ago    13.3kB
[root@qingchuan Docker]# 

```



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
  - -p Linux服务器端口:容器端口
  - **`nginx:1.22.0` 需指定版本，否则会使用最新版**

```shell
# 启动
[root@qingchuan ~]# docker run -d --name Nginx -p 8080:80 nginx:1.22.0
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

# 停止Nginx服务，不能够访问了
[root@qingchuan ~]# docker stop Nginx 
Nginx
[root@qingchuan ~]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@qingchuan ~]# 

```