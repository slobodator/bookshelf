# Notices
- Gradle is upgraded to version 6.3 in gradle/wrapper/gradle-wrapper.properties
- Lombok is used for compactness
- Domain (model) object should escape neither API bounds nor transactional ones
- Every CRUD operation has its own DTO to have fine grained control and clear Swagger doc.
- MapStruct is used for transforming to DTO. And vise versa for simplicity, however, in case of introducting authorId, titleId method toModel will gone and Book will be built within Service.

# Java Backend Developer Test

Thank you for applying to the backend developer position.  To this point we've had a few conversations about your skills and experience and would like you to demonstrate to us what you know.  We've provided you with the source code to a simple Java Web Application that was created using the Spring Boot framework.  The application is incomplete, and could also use some refactoring.  Your task is to complete as many tasks as you can in 4 hours.


Prerequisites:
- PC with internet connection
- Java SE Development Kit 8
- Any IDE or text editor [Eclipse project files included]

To run it
```
gradlew clean books-api:bootRun --parallel
```

The application uses the gradle wrapper (gradlew). No installation of gradle is required. Dependencies are downloaded automatically.

The RESTful web services can be found under the base URI http://localhost:8085/books. You may use any REST client to access them.

Note: The package also contains an Eclipse project for your convenience. Requires Eclipse Oxygen and the Spring Tools (STS) plugin from the Eclipse Marketplace. After the project is imported you should run gradle to finalize the setup: Right click context menu on the 'build.gradle' file, then select 'Refresh Gradle Project' from the 'Gradle' menu. The app can then be run as a 'Spring Boot App'.

## Project Structure

The application follows standard Spring Boot folder structure conventions:
  
- src/main/java: Contains all Java source code for the project.
- src/main/resources: Contains all static content and views for the project
- src/test/java: Contains all Java source code for the unit tests.


## Your Tasks

1. Try getting the list of all books. Uh oh! It looks like there's a bug. Can you fix it?  After you fixed it, add an unit test to ensure that this bug doesn't re-enter the system.  Make sure to update the relevant gradle files to import any dependencies.
*I decided to populate DB within its Service. Also FlyWay might be an option. Test is BookshelfApplicationTests::getAllBooks.*

2. Take a look at 'BooksRestController'. Hmm... Writing code  directly against an implementation?! That's not a very loosely coupled way of doing things.  Use Spring's inversion of control functionality to remove the dependency on a concrete implementation in the controller.
*Splitted into Controller-Service-Dao*

3. Ok.  A hardcoded data set is nice and all, but I really want to save the users to a persistent store.  Implement a real repository of some kind so that changes to your data are persisted in between application restarts.  For your convenience the project already has a H2 database configured.
*Well, used it*

4. The application currently does not update books. Implement the web service to update an existing book in the list.
*Since the Book class is quite simple, only PUT method implemented. PATCH might be also considered*

5. Great.  Taking a look at the app, it looks like that list API retrieves the entire list of books.  That's not going to scale very well.  Add paging functionality to the service so that you're only retrieving sets of 10 books at a time. 
*Done with default Spring Pagination*

6. Your application is live. The REST services are consumed by an external Android app. Great! As we continually improve our features we also want to add a short optional description text to each book. Extend the services in a way that it doesn't break the legacy Android app. Since we don't know how fault-tolerant that app is, we have to be careful which data we send.
*Implemented BookDtoV2 with corresponding mapping.*

7. For your implementation above, write some integration tests that prove the correctness of your implementation.
*See test createBookWithDescAndTestVersionableMethods()*

8. Hmm. Now that you actually have a remote server to talk to, the likelihood of errors occurring is quite high... and oh no! There's no explicit exception handling or logging!  Choose one use case (Create / Update / Save / Delete) and add appropriate exception handling and logging to it.  You are free to use any open source logging libraries that you see fit.
*Used Spring default ControllerAdvice*


### Bonus Tasks

1. Although this is a backend developer position, frontend knowledge is always an asset. Add a simple web page that interacts with all the REST interfaces in a new gradle project "books-web". 
*Isn't my profile and had no time to play html form, sorry*
2. Code is truth but it is always advisable to have documentation. Provide such a specification of our RESTful web services we can hand out to our users.  If you prefer to generate it from the sources please make sure to add a task to for this to the gradle files.
*Swagger is added, see localhost:8085/swagger, please*
3. Help! The services are currently not secured at all. Everybody can access them and manipulate data. Since applying security to an application is not for the faint of heart add some layer of authentication and authorization on top of the REST API.
*Basic Auth is added* 

--------

Revision: 2019-10-17

© 2019 Rakuten Kobo Inc. All Rights Reserved
