FROM openjdk:8-jdk

RUN unlink /bin/sh \
    && ln -s /bin/bash /bin/sh \
    && apt-get update \
    && apt-get install -y curl \
    && apt-get -y autoclean \
    && curl -L https://services.gradle.org/distributions/gradle-3.0-bin.zip -o gradle-3.0-bin.zip \
    && apt-get install -y unzip \
    && unzip gradle-3.0-bin.zip \
    && rm gradle-3.0-bin.zip \
    && mkdir /backend

ENV GRADLE_HOME=/gradle-3.0
ENV PATH=$PATH:$GRADLE_HOME/bin


COPY . /backend
RUN cd /backend \
    && gradle clean \
    && gradle build -x test

VOLUME ./database /backend/database
VOLUME ./logs /backend/logs

EXPOSE 8080

# TODO: I know this is large
ENTRYPOINT [ "sh", "-c", "java -Xms1M -Xmx4G -Xss2M -Djava.security.egd=file:/dev/./urandom -jar $(find . -name oracle*.jar)" ]
