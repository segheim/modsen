# Modsen

### Build project

Build project using the Gradle wrapper. You also need JDK 11. 
Use the build task:  
`$ ./gradlew build`

You should get the project with name like &#8220;modsen-0.0.1-SNAPSHOT.jar&#8221;  

###Examples URL for using project

Getting a list of all events: GET <localhost:8080/api/event>  
getting a specific event on id: GET <localhost:8080/api/event/2>  
registration of a new: POST <localhost:8080/api/event>  
edit information about an existing event on id: PATCH <localhost:8080/api/event/2>  
Delete event: DELETE <localhost:8080/api/event/2>  
