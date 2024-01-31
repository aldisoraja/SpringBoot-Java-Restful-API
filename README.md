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


# Contact API Spec

## Create Contact

Endpoint : POST/api/contacts

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "fistName" : "String",
  "lastName" : "String",
  "email" : "String",
  "phone" : "String"
}
```

Response Body (Success) :

```json
{
  "data" :
  {
    "id" : "random-string",
    "fistName" : "String",
    "lastName" : "String",
    "email" : "String",
    "phone" : "String"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Create Contact Failed"
}
```

## Update Contact

Endpoint : PUT/api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "data" :
  {
    "id" : "random-string",
    "fistName" : "String",
    "lastName" : "String",
    "email" : "String",
    "phone" : "String"
  }
}
```

Response Body (Success) :

```json
{
  "data" :
  {
    "id" : "random-string",
    "fistName" : "String",
    "lastName" : "String",
    "email" : "String",
    "phone" : "String"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Update Contact Failed"
}
```

## Get Contact

Endpoint : GET/api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" :
  {
    "id" : "random-string",
    "fistName" : "String",
    "lastName" : "String",
    "email" : "String",
    "phone" : "String"
  }
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Contact is not found"
}
```

## Search Contact

Endpoint : GET/api/contacts

Query Param
- name : String, contact first name or last name, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : [
    {
      "id" : "random-string",
      "fistName" : "String",
      "lastName" : "String",
      "email" : "String",
      "phone" : "String"
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Delete Contact

Endpoint : DELETE/api/contacts/{idContact}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "Success"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact is not found"
}
```


# Address API Spec

## Create Address

Endpoint : POST/api/contacts/{idContacts}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street" : "String",
  "city" : "String",
  "province" : "String",
  "country" : "String",
  "postalCode" : "String"
}
```

Response Body (Success) :

```json
{
  "data" :
  {
    "id" : "random-string",
    "street" : "String",
    "city" : "String",
    "province" : "String",
    "Country" : "String",
    "postalCode" : "Long"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact is not found"
}
```

## List Address

Endpoint : GET/api/contacts/{idContact}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : [
    {
      "id" : "random-string",
      "street" : "String",
      "city" : "String",
      "province" : "String",
      "country" : "String",
      "postalCode" : "String"
    }
  ]
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact is not found"
}
```

## Get Address

Endpoint : GET/api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" :
  {
    "id" : "random-string",
    "street" : "String",
    "city" : "String",
    "province" : "String",
    "country" : "String",
    "postalCode" : "String"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address is not found"
}
```

## Update Address

Endpoint : PUT/api/contacts/{idContacts}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street" : "String",
  "city" : "String",
  "province" : "String",
  "country" : "String",
  "postalCode" : "String"
}
```

Response Body (Success) :

```json
{
  "data" :
  {
    "id" : "random-string",
    "street" : "String",
    "city" : "String",
    "province" : "String",
    "country" : "String",
    "postalCode" : "String"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address is not found"
}
```

## Remove Address

Endpoint : DELETE/api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "Success"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address is not found"
}
```
