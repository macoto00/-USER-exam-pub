# -USER-exam-pub
ETYKA Digital Task

# Project structure

## Dependencies

1) Spring Boot
2) Spring Web
3) Spring Data JPA
4) MySQL Driver
5) Spring Security
6) Lombok

## MVC

MODEL:

UserModel:
- Properties: Id, Name, IsActive, IsAdult, Pocket

OrderModel:
- Properties: Id, ProductName, Amount, Price

DrinkModel:
- Properties: Id, ProductName, Price, IsForAdult

VIEW:

Views for displaying user-related information, drink menu, and order summary.
Views should render the data from the models in a user-friendly format for the end-users.

CONTROLLER:

UserController:
- Handles requests related to users.
- Methods for handling GET requests for getting all users and a specific user by ID.

OrderController:
- Handles requests related to orders.
- Methods for handling POST requests for creating an order and GET requests for retrieving order summaries.

DrinkController:
- Handles requests related to the drink menu.
- Method for handling GET requests for retrieving the drink menu.
