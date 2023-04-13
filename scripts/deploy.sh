#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/visiting-schedule-1.0.jar \
    su@192.168.147.56:/home/su/

echo 'Restart server...'

ssh -tt -i ~/.ssh/id_rsa su@192.168.147.56 << EOF
pgrep java | xargs kill -9
nohup java -jar visiting-schedule-1.0.jar > log.txt &
EOF

echo 'Complete'