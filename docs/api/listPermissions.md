# Authorized User List

Lists the users that can access an album.

**URL** : `/albumauth`

**Method** : `GET`

**Authentication** : NO

## Successful Response

**Code** : `200 OK`

**Example**

```json{
"object":
    {
      "albumId": 1,
      "authUsers": [{
        "id":1,"name":"Leanne Graham",
        "username":"Bret",
        "email":"Sincere@april.biz",
        "phone":"1-770-736-8031 x56442",
        "website":"hildegard.org",
        "address":{
          "street":"Kulas Light",
          "suite":"Apt. 556",
          "city":"Gwenborough",
          "zipcode":"92998-3874",
          "geo":{
            "lat":-37.3159,
            "lng":81.1496
          }
        },
        "company":{
          "name":"Romaguera-Crona",
          "catchPhrase":"Multi-layered client-server neural-net",
          "bs":"harness real-time e-markets"
        }
    },,...],}
  "error":null,
  "successful":true
}
```