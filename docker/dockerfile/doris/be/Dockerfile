FROM hyperj/doris-build:0.9.0.rc02

MAINTAINER hyperj

ARG DORIS_DISK1=/home/disk1/palo
ARG DORIS_DISK2=/home/disk2/palo

ENV DORIS_ROOT=/var/local/doris
ENV DORIS_HOME=$DORIS_ROOT/be

WORKDIR ${DORIS_HOME}

RUN mkdir -p ${DORIS_DISK1} ${DORIS_DISK2}

EXPOSE 9060 9070 8040 9050 8060

CMD sh ./bin/start_be.sh
