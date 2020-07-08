启动本地创建镜像仓库：
```
docker run -d \
  -p 5000:5000 \
  --restart=always \
  --name registry \
  -v /Users/weibolei/Dev/docker-workspace/registry:/var/lib/registry \
  registry:2
```

通过下面的指令来查看推到私有仓库里的内容
```shell script
http://127.0.0.1:5000/v2/_catalog
```
查看虚悬镜像：
```shell script
docker image ls -f dangling=true
```

删除虚悬镜像：
```shell script
docker image prune
```

### Demo
0. 进入到try-docker目录，然后执行
1. docker build -t nginx:v3 .
2. docker image ls 查看
3. docker run -d -p 80:80 --rm --name nginx nginx:v3
4. docker tag nginx:v3 127.0.0.1:5000/nginx:v3
5. docker push 127.0.0.1:5000/nginx:v3
6. docker image rm 127.0.0.1:5000/nginx:v3
7. docker pull 127.0.0.1:5000/nginx:v3
8. docker image ls