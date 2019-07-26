FROM hub.netposa.com/np-dge/apline-jdk8
MAINTAINER yunan
ADD np-dgep-dataccess-storage.jar app.jar
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=" -server -Xms300m -Xmx300m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]