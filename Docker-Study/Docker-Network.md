- docker0

```shell
[root@qingchuan ~]# ifconfig
docker0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        inet 172.17.0.1  netmask 255.255.0.0  broadcast 172.17.255.255
        ether 02:42:e6:c4:52:70  txqueuelen 0  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

ens33: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.74.143  netmask 255.255.255.0  broadcast 192.168.74.255
        inet6 fe80::ee5d:a66f:99a:4c10  prefixlen 64  scopeid 0x20<link>
        ether 00:0c:29:df:89:5c  txqueuelen 1000  (Ethernet)
        RX packets 958  bytes 87604 (85.5 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 1499  bytes 385858 (376.8 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 1000  (Local Loopback)
        RX packets 32  bytes 2592 (2.5 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 32  bytes 2592 (2.5 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

virbr0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        inet 192.168.122.1  netmask 255.255.255.0  broadcast 192.168.122.255
        ether 52:54:00:62:1c:44  txqueuelen 1000  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

[root@qingchuan ~]# 

```

- docker network ls

```shell
[root@qingchuan ~]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
cd09ffc6c48f   bridge    bridge    local
16cdddb825df   host      host      local
5c2194784270   none      null      local
[root@qingchuan ~]# 
```

- docker network --help 

```shell
[root@qingchuan ~]# docker network --help 

Usage:  docker network COMMAND

Manage networks

Commands:
  connect     Connect a container to a network
  create      Create a network
  disconnect  Disconnect a container from a network
  inspect     Display detailed information on one or more networks
  ls          List networks
  prune       Remove all unused networks
  rm          Remove one or more networks

Run 'docker network COMMAND --help' for more information on a command.
[root@qingchuan ~]# 
```



- test

```shell
[root@qingchuan ~]# docker network create test
59f2fee624656fae04746ced236d203b4d6b7da21f6da1c083ad3f971b9e09bd

[root@qingchuan ~]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
cd09ffc6c48f   bridge    bridge    local
16cdddb825df   host      host      local
5c2194784270   none      null      local
59f2fee62465   test      bridge    local

[root@qingchuan ~]# docker network rm test 
test

[root@qingchuan ~]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
cd09ffc6c48f   bridge    bridge    local
16cdddb825df   host      host      local
5c2194784270   none      null      local

[root@qingchuan ~]# docker network inspect bridge
[
    {
        "Name": "bridge",
        "Id": "cd09ffc6c48f82ef30dad6f8f544e14e27810e02d6f6ebdb979ea4406c09573a",
        "Created": "2022-07-28T21:30:11.221606939+08:00",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "172.17.0.0/16",
                    "Gateway": "172.17.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {},
        "Options": {
            "com.docker.network.bridge.default_bridge": "true",
            "com.docker.network.bridge.enable_icc": "true",
            "com.docker.network.bridge.enable_ip_masquerade": "true",
            "com.docker.network.bridge.host_binding_ipv4": "0.0.0.0",
            "com.docker.network.bridge.name": "docker0",
            "com.docker.network.driver.mtu": "1500"
        },
        "Labels": {}
    }
]
[root@qingchuan ~]# 
```



| 网络模式      | 简介                                                         |
| ------------- | ------------------------------------------------------------ |
| bridge        | 为每个容器分配IP，并将容器连接到 `docker0` ,虚拟网桥，默认该模式 |
| host          | 容器不会虚拟自己的网卡、配置自己的IP，而是使用宿主机的IP和端口 |
| ~~none~~      | 容器独有的Network Namespace,没有进行任何网络设置，如分配 `veth pair` 、网络桥接、IP等 |
| ~~container~~ | 新创建的容器不创建自己的网卡和配置IP，而是和一个指定的容器共享IP和端口范围等 |



> Docker 容器的IP是可能发生变化的



Docker服务默认创建一个 `docker0网桥` ,该网桥在内核层联通了其他物理网卡或虚拟网卡，将所有容器和宿主机组成同一个物理网络



- bridge 模式
  - 虚拟网络接口
  - `veth pair` 技术，docker0 的每个 veth 和容器的 eth0 两两配对
  - **容器互通**

![image-20220728220203625](Docker-Network.assets/bridge%E6%A8%A1%E5%BC%8F.png)



- host 模式

  使用宿主机的IP和端口，**-p 端口映射没有意义**

![image-20220728221408116](Docker-Network.assets/host%E6%A8%A1%E5%BC%8F.png)

- container模式

![image-20220728222110905](Docker-Network.assets/container%E6%A8%A1%E5%BC%8F.png)



# 自定义网络

- 新建网络

  ```shell
  [root@qingchuan ~]# docker network create diy
  94172d975755be10008e909aeca6daf9b01fc1831c4fa95d4221e77caf6a1700
  [root@qingchuan ~]# docker network ls
  NETWORK ID     NAME      DRIVER    SCOPE
  cd09ffc6c48f   bridge    bridge    local
  94172d975755   diy       bridge    local
  16cdddb825df   host      host      local
  5c2194784270   none      null      local
  [root@qingchuan ~]# 
  
  ```

- 新建容器加入自定义网络

  ```shell
  [root@qingchuan ~]# docker run -d -p 8081:8080 --name tomcat81 --network diy tomcat:8.5.81 
  33ba3648d39fed96f8ee60f87c679ee2ad7b936dddd35220c9e0bf728da156a9
  [root@qingchuan ~]# docker run -d -p 8082:8080 --name tomcat82 --network diy tomcat:8.5.81 
  a1bf686785ffa7902f2c387bb45a87dd115a260b81209caf05007a4533410377
  [root@qingchuan ~]# 
  ```

- 自定义网络能够通过服务名ping通

  tomcat81`ping tomcat82`  和tomcat82 `ping tomcat81` 都成功



# 网络连通

```shell
# 将容器连接到网络
# 会将指定容器加入指定网络
docker network connect NETWORK CONTAINER
```

