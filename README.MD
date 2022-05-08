# Java Stream API Exercises

## Introduction

This GitHub project was created to serve as a playground for practicing Java Stream API. There are 15 exercises which introduce the API usage including filtering, transformation, sorting and the output to various formats.

It is a Spring Boot project with a example data model, repositories and a set of pre-defined data. The system automatically loads data into H2 in-memory database when the Spring Boot app starts up. Therefore, you can fetch data from database by calling repositories and experiment the usage of Java Stream API.

## IDE Setup

This project depends on Lombok Library which is an awesome code auto generation tool, it greatly simplify the source code. Read [this article](https://blog.devgenius.io/how-to-magically-speed-up-java-coding-76fa1a68e0f4) if you would like to know more about the usage.

To configure your IDE for the support of Lombok, check out [the official documentation](https://projectlombok.org/setup/overview) for detail.

## Data Model

The example data model consists of Customer, Order and Product. Customers can place multiple orders and each order would contain a number of products.

![Data Model](https://github.com/gavinklfong/stream-api-exercises/blob/main/blob/Data_Model.jpg?raw=true)

## Exercises

The exercises and the sample solutions can be found in test source `space.gavinklfong.demo.streamapi.StreamApiTest`

You may refer to this article for detail walk through about the exercises.

[15 Practical Exercises Help You Master Java Stream API](https://medium.com/dev-genius/15-practical-exercises-help-you-master-java-stream-api-3f9c86b1cf82)
