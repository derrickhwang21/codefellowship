# Codefellowship
Buidling an app that allows users to create their profile on Codefellowship

## Feature Task

#### lab 16
Create a splash page at the root route `/` that contains basic information about the site, as well as a link to the "registration" page

An `ApplicationUser` should have:
* `username`
* `password` - hashed using BCrypt
* `firstName`
* `lastName`
* `dateOfBirth`
* `bio`

The site should allow users to create an `ApplicationUser` on the "registration" page

The site should have a page which allows viewing the data about a single `ApplicationUser`
* should include default picture, which is the same for every user.

The site should be well-styled and attractive

The site should use reusable templates for its information

The site should have a non-whitelabel error handling page that lets the user know, at a minimum, the error code and a brief message about what went wrong

The site should contain integration testing

#### lab 17
31
​
32
Allow users to log in to CodeFellowship and create posts
33
* Upon logging in, users should be taken to a `/myprofile` route that displays their information
34
​
35
Ensure homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
36
​
37
Ensure user registration also logs users into the app automatically
38
​
39
Add a `Post` entity
40
* A `Post` has a `body` and a `createdAt` timestamp
41
* A logged-in user should be able to create a `Post`, and a post should belong to the user that created it
42
​
43
A user's posts should be visible on their profile page
44
​
45
When a user is logged in, the app should display the user's username on every page (in the navbar)

## Setting Up
* clone repository
    * ensure the following dependencies are installed:

         `dependencies {
    	implementation('org.springframework.boot:spring-boot-starter-data-jpa')
        	implementation('org.springframework.boot:spring-boot-starter-security')
        	implementation('org.springframework.boot:spring-boot-starter-thymeleaf')
        	implementation('org.springframework.boot:spring-boot-starter-web')
        	runtimeOnly('org.springframework.boot:spring-boot-devtools')
        	runtimeOnly('org.postgresql:postgresql')
        	testImplementation('org.springframework.boot:spring-boot-starter-test')
        	testImplementation('org.springframework.security:spring-security-test')
        	testCompile("org.springframework.boot:spring-boot-starter-test")}`
* From your console, connect to PostgreSQL
    * Create `codefellowship_app` database
    * Connect to `codefellowship_app` database
* In the command line, use `gradle bootrun` to start application.
* Go to localhost: 8080 from a browser to view client side server

**Prior to building and running application be sure `spring.jpa.hibernate.ddl-auto=create` is uncommented within the application.properties to initialize table schema. After running locally once, be sure to comment this out or delete to avoid dropping existing table.**
