# ConnectedCarsPlatform
ConnectedCarsPlatform

Development environment
1. Gradle 5.0
2. JRE 1.8

To run application
1.	Pull rabbitmq docker image, run rabbitmq  
    docker run -it -d --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
2.	Run rabitmq sender using jar 
3.	Run rabitmq-consumer  using jar 
4.	Run  eventtrigger using jar
5.	Access UI running in http://localhost:8082/submit
