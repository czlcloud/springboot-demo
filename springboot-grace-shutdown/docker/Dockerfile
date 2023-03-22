FROM myshop/openjdk:dev
WORKDIR /root/demo
ADD springboot-grace-shutdown-0.0.1.jar .
ADD start.sh .
ADD tini /tini
ADD arthas-agent.jar .
RUN echo "https://mirror.tuna.tsinghua.edu.cn/alpine/latest-stable/main/" > /etc/apk/repositories \
      && apk update --allow-untrusted \
      && apk add --no-cache --allow-untrusted bash curl busybox-extras \
      && rm -rf /var/cache/apk/*r  \
      && chmod +x /tini && chmod +x /root/demo/start.sh \
      && curl -L https://arthas.aliyun.com/install.sh | sh

CMD ["/root/demo/start.sh"]

