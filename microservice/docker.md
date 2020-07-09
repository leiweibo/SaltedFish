### Ubuntu Docker Configuration
vi /etc/docker/daemon.json
```shell script
{
  "debug": true,
  "insecure-registries": [
    "registry.mirrors.aliyuncs.com"
  ],
  "experimental": true,
  "registry-mirrors": [
    "https://c8it25aj.mirror.aliyuncs.com"
  ]
}
```
     sudo systemctl daemon-reload
     sudo systemctl restart docker

### Mac OS查看端口是否开放


### Docker Swarm(macos下的desktop版本并不支持作为swarm manager)
    manager1: 192.168.0.101
    work1: 10.211.55.8
    work2: 10.211.55.9
    
 * 创建管理节点
    
    docker swarm init --advertise-addr 192.168.0.104 （在manager机器上执行）
 * 向docker swarm 集群添加工作节点
    
    docker swarm join --token SWMTKN-1-0pvis328vlvqj5f9lajaddypg5ywfc0d25g57hlufdtdob5low-ejfj22ayiemawmc9tg1vjd4uf 192.168.0.101:2377
    注意，如果token忘记了，可以通过下面指令进行查看：
    ```shell script
    docker swarm join-token worker
    ```
   
   1. 在manager节点 docker service create --replicas 1 --name helloworld alpine ping docker.com
   2. 查看集群中的服务：docker service ls
   3. 更改docker swarm 副本数量：docker service scale helloworld=5
   
   ```shell script
    # docker network ls >>
       NETWORK ID          NAME                DRIVER              SCOPE
       8ad1d4c21917        bridge              bridge              local
       5378b6a88747        docker_gwbridge     bridge              local
       51b461aa6444        host                host                local
       gm30ge2uxh9z        ingress             overlay             swarm
       1d5e50c3e1ce        none                null                local
   
    ```
   与非集群环境下的Docker网络对比，Docker Swarm集群网络列表中分别增加了一个以bridge和overlay为驱动的网络。在集群中发布服务时，如果没有指定网络，那么默认都是使用名为ingress网络连接的，而在实际开发中，则会使用自定义的overlay驱动网络进行服务管理。
   在集群管理节点manager1上，创建以overlay为驱动的自定义网络
   ```shell script
    sudo docker network create --driver overlay my-multi-host-network
    ```
   在manager节点上再次部署服务：
   ```shell script
    docker service create --network my-multi-host-network --name my-web --publish 8080:80 --replicas 3 nginx
   ```
   