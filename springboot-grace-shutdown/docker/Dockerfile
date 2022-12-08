FROM myshop/openjdk
ADD ../target/springboot-grace-shutdown-0.0.1.jar /root/demo/
ADD start.sh /root/demo/
ADD tini /usr/bin/tini
RUN chmod +x /usr/bin/tini && chmod +x /root/demo/start.sh
ENTRYPOINT ["/usr/bin/tini","--","/root/demo/start.sh"]

