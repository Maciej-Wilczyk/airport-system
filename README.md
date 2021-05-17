# airport-system
## Table of contents
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Basci overview](#basic-overview)

## Technologies
Project is created with:
* Java 11
* Spring-Boot
* SQLite
* Maven

## Setup
You need Java 11 (minimum) installed to run the application. Clone this repo to your desktop or dowland zip.
The easiest way to start the application with Maven (you must have it installed on your computer)
is to open the console in the project directory and run the command:: <br /> mvn spring-boot:run 

## Features
1. For requested Flight Number and date will respond with following :a. Cargo Weight for requested Flight<br />b. Baggage Weight for requested Flight<br />c. Total Weight for requested Flight<br />
2. For requested IATA Airport Code and date will respond with following :<br />a. Number of flights departing from this airport,<br />b. Number of flights arriving to this airport,<br />
c. Total number (pieces) of baggage arriving to this airport,<br />d. Total number (pieces) of baggage departing from this airport.


## Basic overview
At the beginning, the application saves data from JSON files to the database. 
Next the application can be used, for example, with a web browser. The application runs on a port 8080.
<br />
Examples:
<br />
input: http://localhost:8080/weight/5436/2017-03-17T09:15:37-01:00
<br />
output: {"cargoWeight":815,"baggageWeight":2552,"totalWeight":3367,"weightUnit":"kg"}
<br />
input: http://localhost:8080/airport/LEW/2019-11-18T10:43:12-01:00
<br />
output: {"numberOfFlightsDeparting":0,"numberOfFlightsArriving":1,"numberOfBaggageDeparting":0,"numberOfBaggageArriving":1912}


