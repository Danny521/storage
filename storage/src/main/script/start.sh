#!/bin/sh
java_home=env|grep "JAVA_HOME";
process_exists=`ps -ef|grep np-dgep-dataccess-storage|grep -v grep|awk '{print $2}'`
if [ -n "${process_exists}" ];then
	kill -9 ${process_exists}
fi

if [ ! -n "$java_home" ]; then
    nohup java -jar np-dgep-dataccess-storage.jar > /dev/null &
	echo "data storage application is starting......";
    exit;
else
	java -version;
	if [ $? -eq 0]; then
		nohup java -jar np-dgep-dataccess-storage.jar > /dev/null &
	else
		echo "this system has no jdk";
		exit;
	fi
fi