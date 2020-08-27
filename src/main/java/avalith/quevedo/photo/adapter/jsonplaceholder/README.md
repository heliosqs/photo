**JsonPlaceholder**

This package is used as an implementation of the Data Access ports concerning the requests done to the external service https://jsonplaceholder.typicode.com/

it is implemented using the spring library WebClient to execute the requests and manage the responses and errors.

There are three services that where used by the classes defined in this repository:
* **AlbumPlaceholder**: It gets the information of the albums with photos from a user
* **PhotoPlaceholder**: It gets a collection of photos that belongs to a user
* **UserPlaceholder**: It gets the information from a user of the external service