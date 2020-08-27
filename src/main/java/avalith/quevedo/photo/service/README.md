**Service Package**

It is used to provide the domain logic and use cases to external interfaces (Ex: REST Endpoints, Command Line Tools, etc).

The classes defined in this package should:
* Validate incoming parameters because it does not have on control on external input
* Use the functions from the port package to provide an abstraction to data access or communication implementations.

This package provides three types of services:
* **PhotoSrv**: Allows the access to all the photos published by users
* **UserSrv**: Allows the access to all of the user's information
* **AlbumAuthorizationSrv**: Assign permissions to users to access the albums from other users