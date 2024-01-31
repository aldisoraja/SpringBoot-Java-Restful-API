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