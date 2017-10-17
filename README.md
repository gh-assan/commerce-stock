online shop

=====================

## About

This is a small online shop where we Handle stocks for availability

In an online shop we would provide all available products the stock data. Additionally
for analytic reasons, we also want to keep track on some basic statistics.

This application is build using latest version of spring boot , Java 8  

The provided end points : 

1. POST /updateStock
2. GET /stock?productId=p23
3. GET /statistics?time=[today,lastMonth]



## Notes

1. To post new record timestamp is not required in the request
2. In case you want to update exsiting stock , then you should provide the latest timastamp which we can have from 
  the GET request result , and if you do provide the correct timestamp the request will fail with status code 204
3. If the new quantity is less than the old one , it is considerd as sale   
4. As I said in my email I see that sending the stock change is much better and will simplify the task	
5- The application is not perfect yet , there are some points which I did not fix yet , like exceptions handling

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

1. POST /updateStock

```
{
	"id":"000001",
	"timestamp":"2017-10-12T13:19:36.556Z",
	"productId" : "p23",
	"quantity":18
}
```

2. GET /stock?productId=p23
3. GET /statistics?time=lastMonth
4. GET /statistics?time=today

## Run Tests

This Application has a lot of test cases , To run them run the following command in the root directory , 
but most of them are intergration tests .

```
mvn test  
```

