cd backend
docker build -t example-demo-backend .
cd ../frontend
docker build -t example-demo-frontend .
cd ..

docker-compose up


