#codetrack

The code tracking tool.

#Modules
##codetrack-core
Track core and base classes project
##codetrack-annotation-java
Track java annotations project
##codetrack-annotation-jsf
Track JSF tags "annotations" project
##codetrack-shell
The shell interface based on spring-shell project.
Provide command access for tracking tool.
##codetrack-scanner-core
Track lib with code scanner base classes
##codetrack-scanner-java
Track java language scanner processors
##codetrack-scanner-jsf
Track JSF scanner processors

To run first download and build project with:

    $> git clone https://github.com/josecmoj/codetrack.git
    $> mvn install
    
To execute codetrack-shell

    $> cd codetrack
    $> java -jar codetrack-shell/target/codetrack-shell-1.0-SNAPSHOT.jar

