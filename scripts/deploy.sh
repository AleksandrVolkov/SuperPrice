#!/usr/bin/env bash

#cd Main/back/CRUD3/src/main/java/front
#ng build --prod

echo 'Copy files...'

#scp \
#    target/demo-0.0.1-SNAPSHOT.jar \
#    ilya@104.248.133.63:/home/ilya/
ssh -i id_rsa ilya@104.248.133.63 << EOF

rm -rf ci
git clone https://Ilyshnya99:Stray228@gitlab.com/KoSedikov/netcracker.git /home/ilya/ci
cd /home/ilya/ci/Main/back/CRUD3
mvn clean package
cd /home/ilya/ci/Main/back/CRUD3/target
pgrep java | xargs kill -9
nohup java -jar CRUD3-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'Restart server...'

#ssh -i id_rsa ilya@104.248.133.63 << EOF

#pgrep java | xargs kill -9
#cd /home/ilya/ci/demo/target
#nohup java -jar demo-0.0.1-SNAPSHOT.jar &


#EOF

echo 'Script finished'

cd ~

cd /home/ilya/html
cd Main
cd back
cd CRUD3
mvn clean package
cd /home/ilya/html/Main/back/CRUD3/target
pgrep java | xargs kill -9
nohup java -jar CRUD3-0.0.1-SNAPSHOT.jar > log.txt 2>nohup.err &

echo 'Restart server...'

exit 0
