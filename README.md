Facts App

Fact App gives you the list of random facts about the animals. This app follows the MVVM with clean architecture & how retrofit communicate with api
and concept of coroutines with view binding and follow the SOLID principle concept while developing the app. App also have UI test cases with Junit test cases
using hilt.


## FEATURES ADDED: 
As this app follows Some Principles known as SOLID.
A design principle is a technique that can be applied to designing or writing code to make that code more maintainable,
flexible and extensible

1. SOLID stands for.

## S - Single Responsibility Principle(SRP)
## O - Open close Principle(OCP)
## L - Liskov substitution Principle(LSP)
## I - Interface Segregation Principle(ISP)
## D - Dependency Inversion Principle(DIP)

2. MVVM is the industry-recognized software architecture pattern that overcomes all drawbacks of MVP and MVC design patterns. MVVM suggests separating the data presentation logic(Views or UI) from the core business logic part of the application.
## M - Model
This layer is responsible for the abstraction of the data sources. Model and ViewModel work together to get and save the data.
## V - view
The purpose of this layer is to inform the ViewModel about the user’s action. This layer observes the ViewModel and does not contain any kind of application logic.
## VM - ViewModel
It exposes those data streams which are relevant to the View. Moreover, it serves as a link between the Model and the View.

3. Retrofit with OkHttp 
Retrofit is a type-safe HTTP client library for Android and Java developed by Square. It simplifies the process of making 
network requests and interacting with RESTful APIs in your Android applications. Retrofit is built on top of OkHttp, 
another popular networking library by Square, and provides a higher-level abstraction to define API endpoints and handle responses in a type-safe manner. 
Use okHttp for okHttpLogging interceptor which helps in printing the api logs with url and header and the response data coming from the api call

4. Coroutine
Coroutines help to manage long-running tasks that might otherwise block the main thread and cause your app to become unresponsive. 

5. UI test cases using espresso and Junit test cases using mockk ( to mock up the data) using Hilt


App build on kotlin and compile and target sdk is 34.


