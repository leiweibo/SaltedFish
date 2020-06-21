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

## 附加：安装docker-machine
> brew install docker-machine-driver-xhyve
##docker-machine-driver-xhyve need root owner and uid
> sudo chown root:wheel $(brew --prefix)/opt/docker-machine-driver-xhyve/bin/docker-machine-driver-xhyve
> udo chmod u+s $(brew --prefix)/opt/docker-machine-driver-xhyve/bin/docker-machine-driver-xhyve

