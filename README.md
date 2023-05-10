# 
1.EventStorming Model: Design Level
![image](https://github.com/wack0910/deliveryApp_1/assets/37098903/3552972d-fc38-49d2-8088-8257af160651)

2. Saga(Pub/Sub)
 1) Order Aggregate 영역에 notify Policy
 ![image](https://github.com/wack0910/deliveryApp/assets/37098903/e670e6eb-5103-49db-8258-12993ff9e940)
 ![image](https://github.com/wack0910/deliveryApp/assets/37098903/02669392-5c36-4169-9c99-683728efa48c)
 ![image](https://github.com/wack0910/deliveryApp/assets/37098903/f6a7dc85-d97d-4879-a8cb-2f5c2bb0fabc)
 
 2) Payment
 ![image](https://github.com/wack0910/deliveryApp_1/assets/37098903/8269d195-16f9-4ca5-8d8d-6f171affdda5)

 3) Restaurant
 ![image](https://github.com/wack0910/deliveryApp_1/assets/37098903/b036970f-e3b5-45fb-a583-c26ea7e7d5d4)
 ![image](https://github.com/wack0910/deliveryApp_1/assets/37098903/db6a31c0-edff-4787-b200-5266a332b4ed)
 ![image](https://github.com/wack0910/deliveryApp_1/assets/37098903/3ea38777-b66b-4857-861a-dc8b36a31dee)

 4) Delivery
 ![image](https://github.com/wack0910/deliveryApp_1/assets/37098903/0cf835ef-3b17-4344-bbb1-da622f8a2584)

3. CQRS
주문 상태 변경때마다 status 변경된 내용을 고객은 실시간으로 확인 가능 
![image](https://github.com/wack0910/deliveryApp/assets/37098903/8bff447a-eb88-4c31-8c32-b360c3f7013e)
![image](https://github.com/wack0910/deliveryApp/assets/37098903/9914b407-e8d5-4367-ac2c-88e396881c7c)
![image](https://github.com/wack0910/deliveryApp/assets/37098903/0ce63291-bfde-49dc-b77a-6b7342487155)
![image](https://github.com/wack0910/deliveryApp/assets/37098903/95d71123-4610-49ff-aec5-83785b54f63c)

4. Compensation / Correlation
![image](https://github.com/wack0910/deliveryApp_1/assets/37098903/f2361a7c-35b6-478a-899f-7cddc304dfe0)

## Model
www.msaez.io/#/storming/form

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- front
- restaurant
- delivery
- dashboard


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- front
```
 http :8088/orders id="id" customerId="customerId" totalPrice="totalPrice" status="status" address="address" foodId="foodId" foodName="foodName" qty="qty" 
 http :8088/payments id="id" orderId="orderId" status="status" totalPrice="totalPrice" 
```
- restaurant
```
 http :8088/restaurants id="id" customerId="customerId" orderId="orderId" foodId="foodId" foodName="foodName" status="status" address="address" qty="qty" 
```
- delivery
```
 http :8088/deliveries id="id" orderId="orderId" restaurantId="restaurantId" address="address" status="status" customerId="customerId" 
```
- dashboard
```
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

