1. 在ProxyServletConfig建立bean，配置拦截路径。(见ServletRegistrationBean，可参照此方式配置多个bean。)
2. 在ProxyService的service方法中，根据需要设置url和httpHost，可从redis中读取配置。
3. 发送请求如下：
```
➜  ~ curl -H "edge_clouster_id:agent1" http://localhost:8080/proxy/edge-agents/welcome
我是agent1%
➜  ~ curl -H "edge_clouster_id:agent2" http://localhost:8080/proxy/edge-agents/welcome
我是agent2%

参考：https://github.com/mitre/HTTP-Proxy-Servlet
