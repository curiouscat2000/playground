To build from sources:
mvn package
cp target/assignments-0.0.1-SNAPSHOT.jar .
docker build --build-arg JAR_FILE=assignments-0.0.1-SNAPSHOT.jar -t kaplan.assignment.api:1 .
docker run -p 8080:8080 kaplan.assignment.api:1
# open in browser http://http://localhost:8080/

or just run pre cooked docker image assignment.api.docker.img.exported 