# Recipes REST API in Java, using play framework

This project is an example of play framework use, wich contains the following features:

- REST resources design (routes)
- Operation resources design (verbs)
- Models: attributes, finder, etc
- 1 to 1 model relation
- 1 to N model relation
- N to M model relation (adapation, see References)
- Model validations
- Custom validations
- Controllers
- JSON and XML views
- i18n (internationalization): English and Spanish
- Cache (only for fun, 5 seconds in GET methods. POST and PUT remove cache.)
- Action composition

Prerequisites include:

* Java Software Developer's Kit (SE) 1.8 or higher
* sbt 0.13.15 or higher (we recommend 1.2.3) Note: if you downloaded this project as a zip file from https://developer.lightbend.com, the file includes an sbt distribution for your convenience.

To check your Java version, enter the following in a command window:

`java -version`

To check your sbt version, enter the following in a command window:

`sbt sbtVersion`

If you do not have the required versions, follow these links to obtain them:

* [Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [sbt](http://www.scala-sbt.org/download.html)

## Build and run the project

This API Play project was created from a seed template. It includes all Play components and an Akka HTTP server. The project is also configured with filters for Cross-Site Request Forgery (CSRF) protection and security headers.

To build and run the project:

1. Use a command window to change into the example project directory, for example: `cd recipe-api`

2. Build the project. Enter: `sbt run`. The project builds and starts the embedded HTTP server. Since this downloads libraries and dependencies, the amount of time required depends partly on your connection's speed.

3. After the message `Server started, ...` displays, enter the following URL in a browser: <http://localhost:9000>

## Plugins used

- com.typesafe.play - sbt-plugin, Version 2.7.3
- com.typesafe.sbt - sbt-play-ebean, Version 5.0.2

## Methods

See a [full documented postman collection here](https://documenter.getpostman.com/view/4401930/SzKWuxaK).


## References

- Play Framework java template: https://developer.lightbend.com/start/?group=play&project=play-samples-play-java-starter-example
- N to M model relation adaptation: https://stackoverflow.com/questions/27204672/custom-bridge-table-in-playframework-ebean
- Play Framework Documentation: https://www.playframework.com/documentation/2.8.x/Home
