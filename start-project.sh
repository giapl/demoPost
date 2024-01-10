# build file jar
echo "build project"
mvn clean install
# docker build images
echo "build image"
docker build -f Dockerfile -t app_noron .
# down noron_app
echo "down noron_app"
docker-compose -f Docker-compose.yml rm -f -s noron_app
# start project
echo "start project"
docker-compose -f Docker-compose.yml up -d