#FROM ubuntu
#
#WORKDIR /tutku/welcome
#
#ADD welcome.txt welcome.txt
#
#ENTRYPOINT cat welcome.txt

# https://download.java.net/java/GA/jdk21.0.1/415e3f918a1f4062a0074a2794853d0d/12/GPL/openjdk-21.0.1_linux-x64_bin.tar.gz
# apt-get update
# apt-get install curl
#  curl https://download.java.net/java/GA/jdk21.0.1/415e3f918a1f4062a0074a2794853d0d/12/GPL/openjdk-21.0.1_linux-x64_bin.tar.gz --output java21.tar.gz
#  tar -xvzf java21.tar.gz
# rm java21.tar.gz
# export PATH=$PATH:jdk-21.0.1/bin

FROM ubuntu

WORKDIR java

#RUN apt-get update
#
#RUN apt-get install curl -y
#
#RUN curl https://download.java.net/java/GA/jdk21.0.1/415e3f918a1f4062a0074a2794853d0d/12/GPL/openjdk-21.0.1_linux-x64_bin.tar.gz --output java21.tar.gz

ADD https://download.java.net/java/GA/jdk21.0.1/415e3f918a1f4062a0074a2794853d0d/12/GPL/openjdk-21.0.1_linux-x64_bin.tar.gz java21.tar.gz

RUN tar -xvzf java21.tar.gz

RUN rm java21.tar.gz

ENV PATH $PATH:/java/jdk-21.0.1/bin