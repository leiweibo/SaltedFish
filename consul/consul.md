### Docker拉去镜像
```
docker pull consul
```

### 指定一个文件夹，用来保存consul数据
```
mkdir -p ~/data-dir/consul
```

### 启动consul server 
```
docker run -d -p 8500:8500 -v /Users/weibolei/data-dir/consul:/consul/data -e CONSUL_BIND_INTERFACE='eth0' --name=consul1 consul agent -server -bootstrap -ui -client='0.0.0.0'
```
### 征集群插入其他节点
```
docker run -d --name=consul2 -e CONSUL_BIND_INTERFACE=eth0 consul agent --server=true --client=0.0.0.0 --join 172.17.0.2;
docker run -d --name=consul3 -e CONSUL_BIND_INTERFACE=eth0 consul agent --server=true --client=0.0.0.0 --join 172.17.0.2;
docker run -d --name=consul4 -e CONSUL_BIND_INTERFACE=eth0 consul agent --server=false --client=0.0.0.0 --join 172.17.0.2;
```

### 查看集群下面的节点
```
docker exec -it consul1 consul members
```

### 通过docker-machine来建立三个宿主主机
```
dm create -d "virtualbox" node1
dm create -d "virtualbox" node2
dm create -d "virtualbox" node3
```

dm ssh node1/node2/node3
sudo vi /etc/docker/daemon.json
将下面的内容拷贝到daemon.json
```
{
  "experimental" : true,
  "registry-mirrors" : [
    "https://7h3ozzkv.mirror.aliyuncs.com"
  ]
}
```
然后重启
```
sudo /etc/init.d/docker restart
```

### 创建第一个台consoul-server
docker run -h node1  --name consul -d -v /data:/data --restart=always\
    -p   8300:8300 \
    -p   8301:8301 \
    -p   8301:8301/udp \
    -p   8302:8302 \
    -p   8302:8302/udp \
    -p   8400:8400 \
    -p   8500:8500 \
progrium/consul -server \
-bootstrap-expect 3 \
-advertise 192.168.99.105

### 创建第二台consul-server
docker run -h node2 --name consul -d -v /data:/data   --restart=always\
    -p   8300:8300 \
    -p   8301:8301/udp \
    -p   8302:8302 \
    -p   8302:8302/udp \
    -p   8400:8400 \
    -p   8500:8500 \
progrium/consul -server \
-advertise 192.168.99.106 \
-join  192.168.99.105

### 创建第台consul-server
docker run -h node3 --name consul -d -v /data:/data   --restart=always\
    -p   8300:8300 \
    -p   8301:8301/udp \
    -p   8302:8302 \
    -p   8302:8302/udp \
    -p   8400:8400 \
    -p   8500:8500 \
progrium/consul -server \
-advertise 192.168.99.107 \
-join  192.168.99.105

### 在三个节点中启动 nginx
docker run -d -p 80:80 --name nginx nginx

docker container run -d -p 80:80 nginx

然后执行以下命令：
```
$ curl -X PUT -d '{"id": "nginx","name": "nginx","address": "192.168.99.105","port": 80,"checks": [{"http": "http://192.168.99.105/","interval": "5s"}]}' http://127.0.0.1:8500/v1/agent/service/register

$ curl -X PUT -d '{"id": "nginx","name": "nginx","address": "192.168.99.106","port": 80,"checks": [{"http": "http://192.168.99.106/","interval": "5s"}]}' http://127.0.0.1:8500/v1/agent/service/register

$ curl -X PUT -d '{"id": "nginx","name": "nginx","address": "192.168.99.107","port": 80,"checks": [{"http": "http://192.168.99.107/","interval": "5s"}]}' http://127.0.0.1:8500/v1/agent/service/register
```


## 附加：安装docker-machine
> brew install docker-machine-driver-xhyve
##docker-machine-driver-xhyve need root owner and uid
> sudo chown root:wheel $(brew --prefix)/opt/docker-machine-driver-xhyve/bin/docker-machine-driver-xhyve
> sudo chmod u+s $(brew --prefix)/opt/docker-machine-driver-xhyve/bin/docker-machine-driver-xhyve
