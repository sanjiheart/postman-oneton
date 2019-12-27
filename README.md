
# Postman Oneton

**Postman Oneton** is just a testing tool that sends email to many email addresses from one email address.

## Prerequisite

- MongoDB
- Java
- Gradle
- Yarn

## Features

- Set **MAIL_SERVER** configuration dynamically
- Set **MAIL_CLIENT** configuration dynamically
- Send email by scheduling (by API)
- Send email manually (by API)

## First execution

1. Go to **src/main/resources/static/web** directory and execute:

	`yarn install`
	
2. Go back to the root directory and execute:
 
	`gradle clean build`

3. Execute JAR file:

	`java -jar build/libs/postman-oneton-0.1.0.jar`

## Web UI

http://localhost:8787/web/index.html