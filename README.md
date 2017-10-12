online shop

=====================

## About

This is a small online shop where we Handle stocks for availability

In an online shop we would provide all available products the stock data. Additionally
for analytic reasons, we also want to keep track on some basic statistics.

This application is build using latest version of spring boot , Java 8  

The provided end points : 

● POST /updateStock
● GET /stock?productId=p23
● GET /statistics?time=[today,lastMonth]



## Notes

● To post new record timestamp is not required in the request
● In case you want to update exsiting stock , then you should provide the latest timastamp which we can have from 
  the GET request result , and if you do provide the correct timestamp the request will fail with status code 204
● If the new quantity is less than the old one , it is considerd as sale   
● The spec of the task is not clear , and this forced me to ask a lot of questions 
● during my last read I foun You get in return: status-code 204 , I return the status code as 204 , but I do not know if you mean return json with status message inside it.
● As I said in my email I see that sending the stock change is much better and will simplify the task	


## Installation


### Clone project

First clone the project, if not already done so.


```
mvn clean
mvn insatll
```

run the applicatio using 

```
 mvn spring-boot:run
```


Example:

● POST /updateStock

```
{
	"id":"000001",
	"timestamp":"2017-10-12T13:19:36.556Z",
	"productId" : "p23",
	"quantity":18
}
```

● GET /stock?productId=p23
● GET /statistics?time=lastMonth

## Run Tests

This Application has a lot of test cases , To run them run the following command in the root directory , 
but most of them are intergration tests .

```
mvn test  
```

