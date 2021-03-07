# Shopping online app
Shopping web app made using Spring Boot + Thymeleaf + Bootstrap

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Shopping App Demo](#shopping-app-demo)
* [Setup](#setup)
* [Project status](#project-status)

## General info
Users can filter products by price, star rating and name.
Only logged users can make orders and see previous orders. Each user has his own shopping cart (session functionality). 
Checkout is transactional. 
Users can login and register. 

## Technologies
* Spring Data JPA 
* Spring Boot Test
* Spring Boot Security
* H2 database 
* Flyway
* Thymeleaf
* Bootstrap



## Shopping App Demo
<img src="demo/ShoppingAppDemo.gif" width="720" height="398" alt="Shopping App Demo"/>

## Setup
To create an executable jar run:
`$ mvn clean package`
To run that application, use the java -jar command, as follows:
`$ java -jar target/shopping-online-0.0.1-SNAPSHOT.jar`

Once the app starts, go to the web browser and visit http://localhost:9091/products

