# -USER-exam-pub
ETYKA Digital Task

# Project structure

Using InteliJ IDEA Ultimate. Spring Framework, GitHub, MySQL Database. 

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
- Properties: Id, Name, IsActive, IsAdult, Pocket, List of orders (OneToMany)

OrderModel:
- Properties: Id, ProductName, Amount, Price, User mapped (ManyToOne)

DrinkModel:
- Properties: Id, ProductName, Price, IsForAdult

DTOs:
- UpdateUserDTO
- CreateUserDTO
- SummaryDTO

VIEW:

Views for displaying user-related information, drink menu, and order summary.
Views should render the data from the models in a user-friendly format for the end-users.
Had break for a lunch :) then continued developing this task.

CONTROLLER:

UserController:
- Handles requests related to users.

AppController:
- Handles requests related to application in general.

SummaryController:
- Handles requests related to the summary tasks since they have common @RequestMapping("/summary").

## Others

For Database connection using Environment variables to protect sensitive data.
Had break for Lunch :)) then continued developing.
