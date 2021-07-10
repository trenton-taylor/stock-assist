# Stock Assist Application
## About The Project
This is a code project to showcase a simple API for reading stock data from Yahoo! Finance and keeping track of a 'watch list' of desired stocks. The application also tracks the average stock price in real time, based on the number of times a stock has been queried.

## Getting Started

This application uses Spring Boot, Java 11 and Maven for dependency resolution. 

### Prerequisites

- Java 11 Runtime
- Maven 

### Installation

1. Clone the repo
   ```
   git clone https://github.com/trenton-taylor/stock-assist.git
   ```
4. Build and run the project from the command line
   ```
   mvn clean spring-boot:run
   ```

## Usage
There are a few different endpoints to interact with the application to achieve the desired functionality. These options consist of standard Http `@Get`, `@Put`, and `@Delete` and can be operated on a single stock symbol, or multiple stock symbols at the same time.

### Sample @GETs
*Retrieves all currently watched stocks:*
```
curl --location --request GET 'localhost:8080/stocks/multi'
```

*Retrieves a single watched stock:*
```
curl --location --request GET 'localhost:8080/stocks/single/AAPL'
```
### Sample @PUTs
*With request body:*
```
curl --location --request PUT 'localhost:8080/stocks/multi' \
--header 'Content-Type: application/json' \
--data-raw '[
    "GOOG",
    "AAPL",
    "GME"
]'
```

*With path param:*
```
curl --location --request PUT 'localhost:8080/stocks/single/AAPL'
```

### Sample @Deletes
*With request body:*
```
curl --location --request DELETE 'localhost:8080/stocks/multi' \
--header 'Content-Type: application/json' \
--data-raw '[
    "GOOG",
    "AAPL",
    "GME"
]'
```

*With path param:*
```
curl --location --request DELETE 'localhost:8080/stocks/single/AAPL'
```


## Contact

Trenton Taylor - trenton.a.taylor@gmail.com

Project Link: [https://github.com/trenton-taylor/stock-assist](https://github.com/trenton-taylor/stock-assist)
