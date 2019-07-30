#!/bin/bash

source /etc/profile

WORK_HOME=$(cd "$(dirname "$0")"; pwd)/..

APP_NAME="order-lunch"
JAVA_CMD="java"
CLASSPATH="${WORK_HOME}/conf/*:${WORK_HOME}/lib/*:${CLASSPATH}"
MAIN_CLASS="com.github.johnsonmoon.orderlunch.OrderLunchApplication"
JAVA_OPTS="-Dapp.name=${APP_NAME} -Dwork.home=${WORK_HOME} -Dspring.config.location=file:${WORK_HOME}/conf/ -Dspring.profiles.active=prod"
JAVA_OPTS="${JAVA_OPTS} -Xmx512m -Xms256m -Xss256K -XX:MaxMetaspaceSize=256m"
JAVA_OPTS="${JAVA_OPTS} -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+ExplicitGCInvokesConcurrent"
DEBUG_PORT=9081
LOG_DIR=${WORK_HOME}/logs/
DB_DATA_DIR=${WORK_HOME}/dbdata/h2/

# 判断java命令是否存在
type java >/dev/null 2>&1 || { echo >&2 "java command not found."; exit 1; }

# 判断日志存放路径是否存在，否，创建之
if [ -e ${LOG_DIR} ]; then
    echo -n ""
else
    mkdir -p ${LOG_DIR}
fi

if [ -e ${DB_DATA_DIR} ]; then
    echo -n ""
else
    mkdir -p ${DB_DATA_DIR}
fi

eval_pid() {
    PID=0
    if [ -n "$(ps -ef | grep "app.name=$APP_NAME" | grep -v grep)" ] ; then
        PID=($(ps -ef | grep "app.name=$APP_NAME" | grep -v grep | awk '{print $2}'))
    fi
}

run() {
    eval_pid
    if [ $PID -ne 0 ] ; then
        echo "WARN: $APP_NAME already started (PID=${PID[@]})."
    else
        java ${JAVA_OPTS} -cp ${CLASSPATH} ${MAIN_CLASS}
    fi
}

start() {
    eval_pid
    if [ $PID -ne 0 ] ; then
        echo "WARN: $APP_NAME already started (PID=${PID[@]})."
    else
        nohup java ${JAVA_OPTS} -cp ${CLASSPATH} ${MAIN_CLASS} > /dev/null 2>&1 &

        if [ $? -eq 0 ] ; then
            sleep 3

            eval_pid
            if [ $PID -ne 0 ] ; then
                echo "INFO: $APP_NAME started (PID=${PID[@]})."
            else
                echo "ERROR: $APP_NAME start failed."
                exit 1
            fi
        else
            echo "ERROR: $APP_NAME start failed."
            exit 1
        fi
    fi
}

stop() {
    eval_pid
    if [ $PID -ne 0 ] ; then
        echo -n "INFO: Stopping $APP_NAME (PID=${PID[@]}) ..... "
        kill -9 ${PID[@]}
        if [ $? -eq 0 ] ; then
            sleep 3
            eval_pid
            if [ $PID -eq 0 ] ; then
                echo "[OK]."
            else
                echo "[Failed]."
                exit 1
            fi
        else
            echo "[Failed]."
            exit 1
        fi
    else
        echo "WARN: $APP_NAME not running."
    fi
}

debug() {
    eval_pid
    if [ $PID -ne 0 ] ; then
        echo "WARN: $APP_NAME already started (PID=${PID[@]})."
    else
        DEBUG_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"
        java $JAVA_OPTS $DEBUG_OPTS -cp $CLASSPATH $MAIN_CLASS
    fi
}

status() {
    eval_pid
    if [ $PID -ne 0 ] ; then
        echo "$APP_NAME is running."
        exit 0
    else
        echo "$APP_NAME is not running."
        exit 1
    fi
}

function help(){
    echo "-----------------------"
    echo "Usage: ./XXX.sh [command]"
    echo ""
    echo "Commands:"
    echo "    status       Check status of ${APP_NAME}."
    echo "    run          Start ${APP_NAME} in foreground mode."
    echo "    start        Start ${APP_NAME}."
    echo "    restart      Restart ${APP_NAME}."
    echo "    stop         Stop  ${APP_NAME}."
    echo "    debug        Start ${APP_NAME} with debug mode."
    echo "    help         Print help options."
    echo "-----------------------"
}

if [ ! $1 ] ; then
    help
elif [ $1 = "help" ] ; then
    help
elif [ $1 = "status" ] ; then
    status
elif [ $1 = "run" ] ; then
    run
elif [ $1 = "start" ] ; then
    start
elif [ $1 = "stop" ] ; then
    stop
elif [ $1 = "debug" ] ; then
    debug
elif [ $1 = "restart" ] ; then
    stop
    sleep 3
    start
else
    help
fi