#!/bin/sh
if [ -z "$JAVA_HOME" ];then
	echo "Environment variables 'JAVA_HOME' is NULL."
	exit 0
fi
CUR_INSTALL_DIR=$(cd "$(dirname "$0")";cd ../;pwd)
JAVA_OPT="-Xmx512M -Xms512M -DINSTALL_DIR=$CUR_INSTALL_DIR"
if [ "$1" = "jpda" ];then
	if [ -z "$JPDA_ADDRESS" ];then
		JPDA_ADDRESS=65535
	fi
	if [ -z "$JPDA_SUSPEND" ];then
		JPDA_SUSPEND=n
	fi
	if [ -z "$JPDA_OPT" ];then
		JPDA_OPT="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=$JPDA_SUSPEND,address=$JPDA_ADDRESS"
	fi
	shift
fi
$JAVA_HOME/bin/java $JPDA_OPT $JAVA_OPT -jar $CUR_INSTALL_DIR/alpaca-starter-0.1.0-SNAPSHOT.jar
