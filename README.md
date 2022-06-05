# Application nr.2
Internship recruitment task for position Java Back-end Software Enigneer Intern.

Author: Szymon Noras

To run the application you need to:
- clone the repository to local directory
- open project in IDE (for example IntelliJ IDEA)
- open App2Application.java file
- run App2Application.java file

This will read data from file from resources package, if you want to change the file you need to:
- open CardService.java
- change variable "path" to desired location

The origin URL of application is:
http://localhost:8080/

Application provides following mappings:
- All cards
http://localhost:8080/cards/getall - listing all cards the app has read from file (if data in file is valid)

- Names
http://localhost:8080/cards/name/equals/{value} - listing all cards which have equal "name" value. Value is provided as a parameter in {}
http://localhost:8080/cards/name/contains/{value} - listing all cards which contains "name" value. Value is provided as a parameter in {}

- Surames
http://localhost:8080/cards/surname/equals/{value} - listing all cards which have equal "surname" value. Value is provided as a parameter in {}
http://localhost:8080/cards/surname/contains/{value} - listing all cards which contains "surname" value. Value is provided as a parameter in {}

- Phones
http://localhost:8080/cards/phone/equals/{value} - listing all cards which have equal "phone" value. Value is provided as a parameter in {}
http://localhost:8080/cards/phone/contains/{value} - listing all cards which contains "phone" value. Value is provided as a parameter in {}
