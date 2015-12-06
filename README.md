This is a RESTful web service that enables one to manage a deck of cards. 

The service enables you to perform the following actions on a given deck: 
 - retrieve
 - create
 - delete
 - shuffle
 - retrieve all deck names

There are two options for shuffling techniques.

Simple

> This is an algorithm that shuffles the cards in place using [Fisher-Yates Shuffle](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle) technique 

Hand

> This is an algorithm that mimics a person shuffling a deck. It splits the deck in half, and then weaves the two together. It does this at least once, and then an additional random number of times before returning the deck as shuffled.

By default, simple is used. This is toggled by the `shuffle.technique` property set when starting the application. The two valid values are `hand` or `simple`. Not setting the value will default to simple.

API GUI to play around with operations and visualize methods 
(Provided by [Swagger](http://swagger.io/) / [Springfox](http://springfox.github.io/springfox/)

   1. Start the application
   2. Go to: http://localhost:8080/swagger-ui.html

Workstation Setup

   1. Install [Eclipse](http://www.eclipse.org/) or [STS](http://spring.io/tools/sts) (Preferred)
   2. Import the project in STS. 
	   3. File -> Import -> Existing Maven Project
   3. Build the application

Building the application

 1. Right click the project -> Run As -> Maven Build...
 2. fill in 'clean install' in the goals
 3. Press Run
   
Running the application

 - Within STS
      1. Right click on the deck-manager project -> Run As -> Spring Boot Application
 - Outside STS
      1. Build the application
      2. Navigate to folder containing the jar
      3. Execute the following command`java -jar deck-manager-1.0.0-SNAPSHOT.jar -Dshuffle.technique=hand`

Executing Integration Tests

 - In STS
      1. Start the application
      2. Run DeckManagerIntegrationTests.java as a JUnit
 - Outside STS - Maven (Jenkins or Local Validation)
      1. mvn verify -Pcheckout
      2. Open the following file to view the report -> ./target/site/serenity/index.html
 
FAQ

 - How do I change the port being used?
	 - Edit application.properties inside src/main/resources and update the value to your liking
 