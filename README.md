# Twitter-app
Twitter Clone using Java 17, Spring Boot, MYSQL, Liquibase, JWT, Gradle. Application is using JWT token to authenticate and authorize users.

This project is always updated with new features.

## About the project

We have three main classes - **User**, **Tweet** and **Subscription**.

**User** class represents user in this application. User can register, create, update, delete, get tweets.

We have 2 types of authority: USER and ADMIN.

User can have roles 2 roles - `ROLE_USER` and `ROLE_ADMIN`, or 1 - `ROLE_USER` or `ROLE_ADMIN`.

**Tweet** class represents tweet in this application. Tweet can be created by user. Only authenticated user can create tweets. You can check the time when a tweet was created or updated by looking at the createdTimestamp or updatedTimestamp.

**Subscription** class represents subscribe/unsubscribe fuction betwen users in this application.

## Diagram database

<img src=https://github.com/MaximRom00/Twitter-app/assets/95149324/c7a2097e-407b-4d2f-a099-a8daae110251  width="900" height="600">


## Explore Rest APIs
### AUTH requests
- #### Register:
 `POST -> "/api/v1/auth/register"`
 
>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "name" : "Ivan Ivanov",
    "password" : "1",
    "email" : "ivanov123@gmail.com",
    "role" :{
    "authority" : "ROLE_USER" (by default 'ROLE_USER')
}
}
```
>Authenticated: `NONE`

- #### Login:
 `POST -> "/api/v1/auth/login"`

 >_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "name" : "Ivan Ivanov",
    "password" : "1"
}
```
>Authenticated: `NONE`


- #### Refresh:
> `POST -> "/api/v1/auth/refresh"`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "refreshtoken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJPbGVnIFpodWsiLCJpZCI6MywiaWF0IjoxNjk1NTg5MDk2LCJleHAiOjE2OTU1OTI2OTZ9.nK5pZjZS5cuMWq6e8A0UQX_awtSGf8eHGWHypso0NmYkkGna-CXZVbPnQkQsFkeWktONXdGN2WNtQv5mYKIgTA"
}
```
>Authenticated: `NONE`

### USER requests
- #### Get user by id:
> `GET -> "/api/v1/accounts/{id}"`

>_Request parameters:_ `NONE`

>_Request body:_ `NONE`

>Authenticated: `USER`, `ADMIN`

### ROLE requests
- #### Get all roles:
> `GET -> "/api/v1/roles""`

>_Request parameters:_ `NONE`

>_Request body:_ `NONE

>Authenticated: `ADMIN`

- #### Create new role:
> `GET -> "/api/v1/roles/create""`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "authority" : "NEW_ROLE"
}
```
>Authenticated: `ADMIN`

### TWEET requests
- #### Get tweet by id:
> `GET -> "/api/v1/tweets/{id}"`

>_Request parameters:_ `NONE`

>_Request body:_ `NONE`

>Authenticated: `USER`, `ADMIN`


- #### Get user's tweets:
> `GET -> "/api/v1/tweets/list"`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "page" : "1",
    "limit" : 10
}
```
>Authenticated: `USER`, `ADMIN`

- #### Create a new tweet:
> `POST -> "/api/v1/tweets/create"`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "id" : "5",
    "message" : "my new tweet"
}
```
>Authenticated: `USER`, `ADMIN`

- #### Update tweet:
> `PUT -> "/api/v1/tweets"`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "id" : "5",
    "message" : "i want to change my tweet"
}
```
>Authenticated: `USER`, `ADMIN`

- #### Delete tweet:
> `DELETE -> "/api/v1/tweets/{id}"`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "id" : "5"
}
```
>Authenticated: `USER`, `ADMIN`

### SUBSCRIPTION requests

- #### Subscribe user:
> `POST -> "/api/v1/subscriptions/subscribe"`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "followerId" : "3"
}
```
>Authenticated: `USER`, `ADMIN`

- ####  Unsubscribe user:
> `POST -> "/api/v1/subscriptions/unsubscribe"`

>_Request parameters:_ `NONE`

>_Request body:_
```json
{
    "followerId" : "2"
}
```

>Authenticated: `USER`, `ADMIN`

