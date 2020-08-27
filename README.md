# Photo Administration API
**Autor:** Elio Quevedo <*esqs.quevedo1994@gmail.com*>

## Description
Service that manages information from users and their photos that organized in albums. Additionally, it includes  incluyendo las operaciones CRUD (Creación, eliminación, modificación, lectura) para el manejo de sus registros. La información necesaria para representar a un desarrollador es:

## Architecture
The architecture of this system is based on the *Clean Architecture* principle proposed by Robert C. Martin.
This model uses packages with code that is independent from the technology implementation and packages that are specific of a database or web service technology.
#### Independent Packages
* [Domain](src/main/java/avalith/quevedo/photo/domain): Abstract representation of the data that is managed by the system
* [Ports](src/main/java/avalith/quevedo/photo/port): Provides a common interface to access or modify data from the domain
* [Service](src/main/java/avalith/quevedo/photo/service): Provides use cases that can be managed by technology dependent packages.

#### Technology-dependent Packages
* [Placeholder](src/main/java/avalith/quevedo/photo/adapter/jsonplaceholder): Uses the external service https://jsonplaceholder.typicode.com/ to access information from users, albums and photos
* [Repository](src/main/java/avalith/quevedo/photo/adapter/repository): Provides an implementation to store and retrieve authorization information to access an album. It uses a relational database.
* [Api](src/main/java/avalith/quevedo/photo/adapter/rest): REST API endpoint definition for the application. 

## API
The endpoints provided to access or modify the information are:

* [List Users](docs/api/listUsers.md) : `GET /users/`
* [List Photos](docs/api/listPhotos.md) : `GET /photos/`
* [List Authorized Users](docs/api/listPermissions.md) : `GET /albumauth/:id/`
* [Authorize Access to an Album](docs/api/addPermission.md) : `POST /albumauth`