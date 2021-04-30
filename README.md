# Overview

This is a simple Web Crawler Spring Boot Application with 2 endpoints:

1. getCrawlData
2. crawl

## crawl
You can cURL the `crawl` endpoint using the below command

```
curl --location --request GET 'localhost:8080/crawl?website=https://wiprodigital.com/'
```

## getCrawlData

After you have "crawled", you can view the data retrieved from said website using the following

```
curl --location --request GET 'localhost:8080/getCrawlData?website=https://wiprodigital.com/'
```

# Setup
You can run this application in 1 of 2 ways,

## Gradle bootrun
```
./gradlew clean build bootRun
```
*Note that for bootRun, it will say "75% executing" because the servlet is running continuously until terminated...but it is ready.
## Docker
```
docker-compose pull
docker-compose up
```

# Configuration
You can customize how deep the crawler goes and how many pages it will visit in `application.yml` under `PROJECT_ROOT/src/main/resources`

# Future work
1. You can create a database backend for more persistent storage instead of just a ConcurrentHashMap that is reset on startup.
2. You can gather data on images and static content.
