FROM apachedoris/doris-dev:build-env

MAINTAINER hyperj

ENV DEFAULT_DIR /var/local

WORKDIR ${DEFAULT_DIR}

ARG DORIS_VERSION=0.9.0
ARG DORIS_PACKAGE=incubator/doris/${DORIS_VERSION}-incubating/apache-doris-${DORIS_VERSION}-incubating-src.tar.gz
ARG DORIS_URL=https://www.apache.org/dyn/mirrors/mirrors.cgi?action=download&filename=${DORIS_PACKAGE}
ARG DORIS_HOME=${DEFAULT_DIR}/doris
ARG DORIS_BUILD=apache-doris-build

RUN mkdir -p ${DORIS_HOME} \
    && wget --trust-server-names ${DORIS_URL} \
    && tar -zxvf apache-doris-*.tar.gz \
    && mv apache-doris-*src ${DORIS_BUILD} \
    && cd ${DORIS_BUILD} \
    && sh build.sh \
    && cp -R output/* ${DORIS_HOME} \
    && cd fs_brokers/apache_hdfs_broker \
    && sh build.sh \
    && cp -R output/* ${DORIS_HOME} \
    && cd ${DEFAULT_DIR} \
    && rm -rf ${DORIS_BUILD}
