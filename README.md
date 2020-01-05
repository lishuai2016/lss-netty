依托于netty开发项目脚手架

包结构说明：

- echo [回声测试客户端和服务端]

- http [netty支持http访问，输出一个欢迎页面]

- project1 [包含]

1、服务器加上read idle check – 服务器10s 接受不到channel 的请求就断掉连接。
保护自己、瘦身（及时清理空闲的连接）；客户端加上write idle check + keepalive – 客户端5s 不发送数据就发一个keepalive

2、boss线程池、worker工作线程池、业务business线程池；

3、统计当前链接数sever端channelhandler；

4、限流整形GlobalTrafficShapingHandler

5、白名单过滤RuleBasedIpFilter

6、自定义授权AuthHandler


# 问题

1、channelhandler处理顺序？

client客户端方法
channelRegistered
connect
channelActive
write
flush

channelRead   [获取服务端返回的结果]
channelReadComplete


server端方法
channelRegistered[boss线程]
bind
channelActive
channelRead
channelReadComplete

channelRegistered [worker工作线程]
channelActive
channelRead
write
flush
channelReadComplete


