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

There is also quite a bit of exception handling (checking for duplicates, invalid stock quotes) as well as plenty of notifications on what is happening behind the scenese. Please check out the screenshots for more detail. All screenshots were created using Postman.

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
curl --location --request DELETE 'localhost:8080/stocks/single/AAPL'
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
curl --location --request PUT 'localhost:8080/stocks/single/AAPL'
```


## Contact

Trenton Taylor - trenton.a.taylor@gmail.com

Project Link: [https://github.com/trenton-taylor/stock-assist](https://github.com/trenton-taylor/stock-assist)

## Screenshots
![single put](https://user-images.githubusercontent.com/9722718/125177438-a9d58f00-e1a9-11eb-94ef-3dad8501f75b.png)
![multi put](https://user-images.githubusercontent.com/9722718/125177412-84e11c00-e1a9-11eb-923c-c9733b7e445a.png)
![single get](https://user-images.githubusercontent.com/9722718/125177452-ceca0200-e1a9-11eb-98d9-62c93d34a72d.png)
![multi get](https://user-images.githubusercontent.com/9722718/125177446-bfe34f80-e1a9-11eb-8a32-26541ffb8972.png)
![single delete](https://user-images.githubusercontent.com/9722718/125177469-f325de80-e1a9-11eb-9339-427fadeb5fb1.png)
![multi delete](https://user-images.githubusercontent.com/9722718/125177476-06d14500-e1aa-11eb-94ad-90d31b3b7c18.png)
![Not found in Yahoo! API](https://user-images.githubusercontent.com/9722718/125177398-6844e400-e1a9-11eb-8aba-f1d23fe36f6f.png)
![No stocks being watched](https://user-images.githubusercontent.com/9722718/125177527-90811280-e1aa-11eb-97bf-c26e9a45b214.png)
![Not watching](https://user-images.githubusercontent.com/9722718/125177584-fa99b780-e1aa-11eb-990a-43e53fa42f03.png)
![Not in watch list](https://user-images.githubusercontent.com/9722718/125177599-1604c280-e1ab-11eb-984c-6a67a3506036.png)



