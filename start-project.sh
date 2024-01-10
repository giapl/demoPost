# build file jar
echo "build project"
mvn clean install
# docker build images
echo "build image"
docker build -f Dockerfile -t app_noron .
# down project
echo "down project"
docker-compose -f Docker-compose.yml down
# start project
echo "start project"
docker-compose -f Docker-compose.yml up -d