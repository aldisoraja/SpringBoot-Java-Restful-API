# User API Spec

## Register User

Endpoint : POST/api/users

Request Body :

```json
{
  "username" : "String",
  "password" : "String",
  "name" : "String"
}
```

Response Body (Success) :

```json
{
  "data" : "Register Success"
}
```
Response Body (Failed) :

```json
{
  "errors" : "Register Failed!!"
}
```

## Login User

Endpoint : POST/api/auth/login

Request Body :

```json
{
  "username" : "String",
  "password" : "String"
}
```

Response Body (Success) :

```json
{
  "data" : 
  {
    "token" : "TOKEN",
    "expiredAt" : 2929292929 // milliseconds
  }
}
```
Response Body (Failed, 401) :

```json
{
  "errors" : "Username atau Password Failed!!"
}
```

## Get User

Endpoint : GET/api/users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : 
  {
    "username" : "String",
    "name" : "String"
  }
}
```
Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Update User

Endpoint : PATCH/api/users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "String", // put if only want to update name
}
```

Response Body (Success) :

```json
{
  "data" : 
  {
    "username" : "String",
    "name" : "String"
  }
}
```
Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Logout User

Endpoint : DELETE/api/auth/logout

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : 
  {
    "data" : "Success"
  }
}
```