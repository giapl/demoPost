# build file jar
echo "build project"
mvn clean install
echo "build image"
# docker build images
docker build -f Dockerfile -t app_noron:lastest

# start project
echo "down project"
docker-compose -f Docker-compose.yml down
echo "start project"
docker-comose -f Docker-compose.yml up -d