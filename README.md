# KYC
to validate your customer Identity information

Hi guys!

this is Mohamad Basij which is called mohamad.bsj6962 in future
i'm really excited about Java and Spring framework and also big-data tools;)

today we start a journey to have module to ensure that your customer is valid person to take advantage of your service;

let's start!!!



run in directory eureka-server : mvn clean install in
run : java -jar target/bsj-eureka-server-api


and check localhost:8761
no service is registered


---------------------------------------

run in directory bsj.kyc : mvn clean install
run : java -jar target/bsj-kyc-api.jar

Tip: no need to say that you need to install mysql in your system and create Database 
with name KYC and set username and password and set it in application.properties
and change com/bsj/utils/Address.java to your local machine address ;)

again check  localhost:8761

one service has been registered
