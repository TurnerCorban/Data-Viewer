
# Software Design Construction Lab 1: Maven
[Original ReadMe](Original%20ReadMe)
## Quick commands for Maven functions:


**Compile and package jar**
```shell
mvn clean compile assembly:single
````


**Execute program**
```shell
mvn exec:java
```


**Run jar package**
```shell 
java -jar target\Data-Viewer-1.0-SNAPSHOT-jar-with-dependencies.jar
```


## Lab Document: ##

| High Level Purpose Statement: | I want to learn how to use Maven to bundle an existing Java project and deploy it, along with managing dependencies.                                                                                                                                                                                                                                                           |
|:------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Experimental Design           | For this lab I will use Lab3/Lab4 from OOP due to it requiring an external dependancy and containing many classes. I have Maven installed from another class, but I will need to learn how to add support for Maven to an existing project and configure it.                                                                                                                   |
| Resources Available           | I found numerous resources from Apache, JetBrains, and W3Schools. JetBrains has a guide on how to add support for Maven to an existing Java project, so I will follow that.                                                                                                                                                                                                    |
| Time Estimate:                | I believe this project will take about 3 hours. From my very limited experience with Maven I know that the built in support for it in Intellij is very good, and the guides I found are not very long and contain simple steps.                                                                                                                                                |
| Experiment Notes:             | The lab took much longer than anticipated. This was due mostly to Maven being picky about directory structure, and unhelpful error messages. The solution was to create the src/main/java package manually and place all java files in it so the jar package would include them.                                                                                               | 
| Results:                      | Although I ran into some roadblocks, I managed to refactor the project successfully. I added Maven plugins for assembling a jar file with dependencies and for executing the program normally. The java class locations are in the correct location, and the CSV file for my data is in a Maven managed resource folder so it is included in the jar file, making it portable. |
| Consequences for the Future:  | Maven is a powerful tool that is used in the industry for managing Java projects. I plan to use it for future medium-large scale java projects of my own, and will likely use it during my career.                                                                                                                                                                             |



