cd backend
docker build -t example-demo .
cd ..

docker run -p 8080:8080 --rm -it example-demo:latest

