#!/usr/bin/env bash

bin/start-cluster.sh

sleep 3s

exec /bin/bash -c "tail -f $FLINK_HOME/log/*.log"

#while true
#do
#    echo "======="
#    sleep 60s
#done
