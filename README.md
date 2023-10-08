# -USER-exam-pub
ETYKA Digital Task

# Project structure

Using InteliJ IDEA Ultimate, Spring Framework, GitHub, MySQL Database. 

## Dependencies

1) Spring Boot
2) Spring Web
3) Spring Data JPA
4) MySQL Driver
5) Spring Security
6) Lombok

## MVC

1) MODEL:

User, Order, Drink, Role, BuyRequest. 

Working with DTOs.

2) VIEW:

Views for displaying user-related information, drink menu, and order summary.
Views should render the data from the models in a user-friendly format for the end-users.

3) CONTROLLER:

UserController:
- Handles requests related to users.

AdminController:
- Handles statistics for Admin.

AppController:
- Handles requests related to application in general.

SummaryController:
- Handles requests related to the summary tasks since they have common @RequestMapping("/summary").

AuthController:
- Handles security - register, login, logout, refresh.

## Others

- For Database connection using Environment variables to protect sensitive data.
- Had break for Lunch :)) then continued developing.
- Spring security for register and login.
- Using JWT Token stored in cookies. 
- Loading Database for Roles and Drinks.
- Structure of project divided into Controllers, DTOs, Loaders (of Database), Models, Repositories, Security, Services.
- Total work time - 8 hours.

## What would i do differenty next time

1) Start project without Spring security so i could test the endpoints via Postman - I got blocked since I started with Spring Security and didn't hadle the security at the beginning.
2) Try to remove the dependency of Spring Security in ongoing process.
3) Check relations for Entities properly.

## What to add in future development

1) Nice and decent Frontend.
2) 100 % cover of Testing.

# Task Requirements

Cíl projektu: Vytvořit aplikaci simulující “hospodu”, která simuluje standardní chování v
reálném světě.
1. REST API
2. Spring framework
3. Github
4. Mysql 8.0
5. Návrh MVC
6. Využití angličtiny v db návrhu i v kódu
7. Basic auth
a. Přihlášení pro uživatele, kteří mohou objednávat
b. Účet výčepního, který uvidí jen statistiky

Před realizací projektu
1. Založit github repository pod jménem {USER}/exam-pub
Struktura api:
1. GET /users
Vraci vsechny objekty User s atributy:
a. Id
b. Name
c. IsActive
d. IsAdult
e. Pocket
2. GET /users/{id}
Vraci User s danym id a jeho objednavky:
a. Id
b. Name
c. IsActive
d. IsAdult
e. Pocket
f. Orders
i. Id
ii. ProductName
iii. Amount
iv. Price
3. GET /drink-menu
Vraci napojovy listek, drinky maji tyto atributy:
a. Id
b. ProductName
c. Price
d. IsForAdult
4. POST /buy //Validace zdali je uživatel plnoletý a zdali má dostatek financí v
peněžence
a. UserId
b. ProductId

c. Price
5. GET /summary
a. /summary/all
Vraci vsechny objednavky daneho produktu/drinku pro vsechny drinky a jejich
celkovy soucet:

i. Product
ii. Amount
iii. UnitPrice
iv. SummaryPrice
b. /summary/product
Vraci vsechny objednavky daneho drinku pro kazdy drink:
i. UserId
ii. Amount
iii. Price
c. /summary/user
Vraci vsechny objednavky daneho uzivatele pro vsechny uzivatele:
i. UserId
ii. Product
iii. Price
