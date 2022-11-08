#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/hibernate-test-1.0-SNAPSHOT.jar \
    su@192.168.147.73:/home/su/

echo 'Restart server...'

ssh -tt -i ~/.ssh/id_rsa su@192.168.147.73 << EOF
pgrep java | xargs kill -9
nohup java -jar hibernate-test-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Complete'