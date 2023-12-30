# build file jar
echo "build project"
mvn clean install
echo "build image"
# docker build images
docker build -f Dockerfile -t app_noron .
# start project
echo "down container BE_app"
docker-compose -f Docker-compose.yml BE_app
echo "start project"
docker-compose -f Docker-compose.yml up -d