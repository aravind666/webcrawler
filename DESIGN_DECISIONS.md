Design Decisions On Technology

Tech stack 

1. JDK 8 + 
2. Spring Boot 2 as application framework 
3. Undertow as embeded web server
4. jsoup library to parse DOM
5. JUnit and Mokito for unit testing 


Why Spring Boot 2 : - 
It helped me to get things running soon by scaffolding a lot, Not only that, it helps me build an application which is 
scalable, maintanable, testable really quick. 

Why Undertow : - 
lightweight and high-performance

Why JSOUP Library : - 
In the past I have worked on lots of Enterprise applications using Jquery in the frontend. I was very much comfortable with Jquery. So to make my life little easy, in parsing the DOM, I made use of this library. 

Why JUNIT : - 
JUNIT To assert behavior and Mokito to mock dependencies.