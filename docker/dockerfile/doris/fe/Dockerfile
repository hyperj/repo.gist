FROM hyperj/doris-build:0.9.0.rc02

MAINTAINER hyperj

ENV DORIS_ROOT=/var/local/doris
ENV DORIS_HOME=$DORIS_ROOT/fe

WORKDIR ${DORIS_HOME}

RUN mkdir palo-meta

EXPOSE 8030 9020 9030 9010

CMD sh ./bin/start_fe.sh
