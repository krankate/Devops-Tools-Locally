FROM jenkins/jenkins

USER root

RUN apt-get update && rm -rf /var/lib/apt/lists/*

COPY ca-certs/ /usr/local/share/ca-certificates/
RUN update-ca-certificates

USER jenkins

ENV JENKINSTHEME_CSS_URL /userContent/neo-light.css

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY config.groovy /usr/share/jenkins/ref/init.groovy.d/config.groovy

RUN mkdir -p /usr/share/jenkins/ref/secrets && \
    echo false > /usr/share/jenkins/ref/secrets/slave-to-master-security-kill-switch && \
    /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt && \
    mkdir -p /usr/share/jenkins/ref/userContent && \
    curl -fsSL https://tobix.github.io/jenkins-neo2-theme/dist/neo-light.css -o /usr/share/jenkins/ref/userContent/neo-light.css
