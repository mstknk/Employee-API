# Employee API ­ Coding Challenge


Employee Rest API  exposes rest endpoints so that other services can communicate.


System Requirements
------------------------------------------------------------
- Java 8
- Maven 3
- Install and set up docker on your machine.

application is built using

* Java
* Spring Data JPA
* Spring Boot
* Swagger-api
* PostgreSQL



# How to Run

This is a step-by-step guide how to run the example:

## Run the containers
Go to project folder  ~/src/main/resources/docker/docker-postgresql 

```
docker-compose up -d postgres
```

This will create a new postgresql container and you will have Postgres database ready for your service.The new postgresql container contains a database with the following configuration:

  * port: `55432`
  * username: `postgres`
  * password: `postgres`
  * database name: `employeeproddb`

  If you don't want to use the postgresql container or you want to use existing postgresql make sure to have a database with the same configuration or modify your `application-prod.properties` 
  
  ----
    CREATE DATABASE employeeproddb
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
  ----
  


 ## Run the Application
Once you've started a PostgreSQL database you can run the application using.
```
mvn spring-boot:run
```
The application will start on port `8080` so you can send a sample request to `http://localhost:8080/swagger-ui.html` to see Employee Rest API endpoints .

![image](https://github.com/mstknk/Employee-API/blob/master/src/main/resources/images/Screen_Shot_1.png)


 ## Run Test Cases 
 
 !! You must have a database named "employeetestdb" !!
 
if you already have an up and running Postgresql Server , just execute the following SQL create query 
 
   ----
    CREATE DATABASE employeetestdb
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
  ----
  
  if you don't have a running server postgresql server that 
go to project folder  ~/src/test/resources/docker/docker-postgresql 
(before run docker-compose for testing just make sure you don't have running postgresql instance because postgresql container cannot start it exposes the same port number 5432 )
 ```
 docker-compose up -d postgres
 ```
 
 ```
mvn test
 ```
 
 ## The Build Project
 
  ```
mvn clean package
 ```
 

### Api Endpoints

|Method | 	Url		| 	Decription |
|-------| ------- | ----------- |
|POST| /v1/employee |	Create a new employee|
|GET|  /v1/employees| 	List of all employee (support pagination)|
|GET|  /v1/employee/{uuid}| 	Find an employee by uuid|
|PUT|  /v1/employees/{uuid}| 	Update an employee by uuid|
|DELETE|/v1/employee/{uuid}| Delete an employee by uuid|

### Api http codes

[200] - **Ok** - The request was fulfilled.
[201] - **Created** - Employee was created successfully
[302] - **Found** - Employee was found
[400] - **Bad Request** - validation errors (Invalid uuid,email etc ... )
[404] - **Not Found** - Employee not found
[500] - **Internal Server Error** - server error

### Api Responses 

 ```
{
  "http_status": "CREATED",
  "http_status_code": 201,
  "response_date": "2018-07-24T16:23:01.469+0000",
  "called_service": "create an employee",
  "api_response": {
  "responseMessage": "223bc35e-26cb-4cd9-8ee6-4772fe17b49e was created successfully"
  }
}
 ```
 ```

{
  "http_status": "FOUND",
  "http_status_code": 302,
  "response_date": "2018-07-24T16:25:56.287+0000",
  "called_service": "get employee by uuid",
  "api_response": {
    "uuid": "223bc35e-26cb-4cd9-8ee6-4772fe17b49e",
    "email": "mesutkonak@gmail.com",
    "birthday": "2018-04-21",
    "hobbies": [
      {
        "hobby_name": "soccer"
      },
      {
        "hobby_name": "music"
      }
    ],
    "full_name": "mesut konak"
  }
}
 ```
 
 ```

{
  "http_status": "OK",
  "http_status_code": 200,
  "response_date": "2018-07-24T16:29:54.807+0000",
  "called_service": "update employee by uuid",
  "api_response": {
    "responseMessage": "223bc35e-26cb-4cd9-8ee6-4772fe17b49e was update successfully"
  }
}
 ```


 ```

{
  "http_status": "OK",
  "http_status_code": 200,
  "response_date": "2018-07-24T16:30:35.550+0000",
  "called_service": "delete employee by uuid",
  "api_response": {
    "responseMessage": "223bc35e-26cb-4cd9-8ee6-4772fe17b49e was deleted successfully"
  }
}
 ```
 
  ```
{
  "http_status": "OK",
  "http_status_code": 200,
  "response_date": "2018-07-24T16:32:37.032+0000",
  "called_service": "list all employees",
  "api_response": {
    "count": 10,
    "total_pages": 2,
    "next_page_url": "http://localhost:8080/employee-api/v1/employees?&pageSize=5&pageNumber=1",
    "previous_page_url": null,
    "employees_results": [
      {
        "uuid": "83b0b78b-c482-41bd-b50e-e31eff311999",
        "email": "mesutkonak@gmail.com",
        "birthday": "2018-04-21",
        "hobbies": [
          {
            "hobby_name": "soccer"
          },
          {
            "hobby_name": "music"
          }
        ],
        "full_name": "mesut konak"
      },
      {
        "uuid": "303825e5-18a2-4e84-8b0c-41144a1e4cf3",
        "email": "mesutkonak2@gmail.com",
        "birthday": "2018-04-21",
        "hobbies": [
          {
            "hobby_name": "music"
          },
          {
            "hobby_name": "soccer"
          }
        ],
        "full_name": "mesut konak"
      },
      {
        "uuid": "b0e3e9fc-d6f6-49ac-9a62-d24c1dd66bf2",
        "email": "mesutkonak3@gmail.com",
        "birthday": "2018-04-21",
        "hobbies": [
          {
            "hobby_name": "soccer"
          },
          {
            "hobby_name": "music"
          }
        ],
        "full_name": "mesut konak"
      },
      {
        "uuid": "6f948087-4d96-4a26-bbec-644c51e7f0aa",
        "email": "mesutkonak4@gmail.com",
        "birthday": "2018-04-21",
        "hobbies": [
          {
            "hobby_name": "soccer"
          },
          {
            "hobby_name": "music"
          }
        ],
        "full_name": "mesut konak"
      },
      {
        "uuid": "afa17028-58d0-42a1-8792-34f944b63a1d",
        "email": "mesutkonak5@gmail.com",
        "birthday": "2018-04-21",
        "hobbies": [
          {
            "hobby_name": "soccer"
          },
          {
            "hobby_name": "music"
          }
        ],
        "full_name": "mesut konak"
      }
    ]
  }
}
 ```
### Database Diagram

![image](https://github.com/mstknk/Employee-API/blob/master/src/main/resources/images/Screen_Shot_DB.png)