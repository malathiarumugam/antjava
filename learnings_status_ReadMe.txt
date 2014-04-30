Tomcat Installation:

1. Download some apache-tomcat-version.zip and extract to location .
2. set env variables CATALINA_HOME to the extracted directory.
3. Also need JAVA_HOME with value like c:\program_files\jdk_5.0 and should not end with bin.
4. Add roles and users in tomcat_users.xml
5. the default web applications included with Tomcat will be
      available by visiting:

      http://localhost:8080/


Ant Installation:

1. Download the zip and extract to location.
2. set env variables ANT_HOME to the extracted directory.
3. Optionally, from the ANT_HOME directory run ant -f fetch.xml -Ddest=system to get the library dependencies of most of the Ant tasks that require them. If you don't do this, many of the dependent Ant tasks will not be available


Ivy Installation

1. Download ivy and extract and place the ivy jar into ANT_HOME/lib
2. Integrating ivy with ant task.
     - add ivy.xml in the root level. (specify the jars needed.)
     - in build.xml's project tag specify the ivy namespace.
     - call ivy:retrieve will resolve the jar by copying to the lib directory from local repo (users/aravi_000/.ivy/) if not present download and copy to local repo and then to lib
     - put ivy:retrieve in the prepare target of build.yml.
     - something called ivysettings.xml available if folders and task need to be custom made.
- Have to thing how it will work in CI, QA, reg environments. (whether ivy repo need to present everywhere or should be bundled.)
3. For segregating lib as compile and test folders to contain concerned jar files, ivy has a concept called configurations. Actually it will used to level of generating multiple jars within
   an single app like our hibernate proj , service proj and web proj referring to this. so while packaging that jar , it will deal with only dependencied of particular conf (have not implemented that since we are not mainatined several project in same soln here.)
4. I have used conf property to populate jars separately used for src and test, so while compiling will copy only jars needed by source to output (build directory).
5. While running junit, use classpath pointing to test jars separately and src jars separately.( but need to compile the test classes first).
6. maven dependency tag to ivy tag,

         <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>4.3.5.Final</version>
         </dependency>
         <dependency org="org.hibernate" name="hibernate-core" rev="4.3.5.Final" conf="compile"/>
         groupId --> org
         artifactId --> name
         version --> rev (use latest.version if version number not available.)



    ivysettings.xml [need to read lot]
        - customize local repo folders
        - add more repo using resolvers (for spring add custom resolvers)
        - can specify resolvers for each modules.


Mysql Installation

1. Download msi and install
2. install mysql service using mysqld --install and start the service.
3.

Intellij IDEA
- use Ultimate eition (Web applications)
- use Community edition (Android, Java console)


Intellij tweaks:
- the lib folders (jar resoides) needs to be added as library at project level for intelli-sense.
- mark module as concerned type like test root for able to run test from IDE etc. like excluded, source root etc.


Web application to run on tomcat using ant should have the following structure.

- src
    - package
        - java files
- docs
- web
    - html/jsp files
    - images
    - scripts
    - WEB-INF
       - web.xml
       - any servlet.xml (if spring is used.)
       - some sensitive data (optional)
- META-INF (dono where to place)
    -context.xml file can be used to define Tomcat specific configuration options
- lib (two approcahes)
   --> manually copy of contents
   place the external jar files here.
   in the prepare task in build.xml , copy the jar in lib to WEB-INF/lib.
   in build.xml for path id compile.classpath include the lib files like this
      <fileset dir="${lib.home}">
            <include name="*.jar"/>
        </fileset>
    --> see ivy's details for dependency management.

- build.xml (used by ant) it will have all the tasks needed to compile the classes , dist to create war for remote installation, install for local deployment, reload for reloading changes to local deployments and etc. need to update the keys (especially the tomcat manager username and password for ant install task)
- build.properties (key values used in build.xml) optional.
- build (it will be created after ant compile)
- dist  (it will be created after ant dist and contains the war which can be dispatched for remote installation)
- git ignore build,dist, lib folder contents and not lib folder as such, .idea folders.

Add spring to Web application.
- resolve spring jar using ivy dependencies.
- specify spring's dispatcher servlet in web.xml
- add spring-servlet.xml , to specify package where controller resides, view resolver etc.
- Add controller and jsp etc.
- in web.xml specify servlet-mapping for images, js to use default servlet, and and at the specify spring servlet at the end, so the request will default to it , if not matched by others
    similar to route definitions
- annotate with @service for service method, @autowired for constructor to get injection, need to learn more annotations. (it will use component scan in spring-servlet.xml)

Add Hibernate to Web application.
- Need to know JPA ?
- Need to know JNDI when database is used along with application server.
- Add dependency using ivy for hibernate-core alone , hibernate-validator lot more added see commit. since JPA is not needed (dono what is JPA though)
- create session factory bean using configuration class in singletion scope.
- use that factory in repositories and do operation in transaction scope.


Maven Installation:

1. Download the zip and extract to location.
2. set env variables M2_HOME to the extracted directory and also M2 point to maven bin



Jar/Web application set up using maven

- create project using this command
     mvn archetype:generate \ -DarchetypeGroupId=org.apache.maven.archetypes \ -DgroupId=com.mycompany.app \ -DartifactId=my-app (dono exact meaning.) for JAR
     mvn archetype:generate \ -DarchetypeGroupId=org.apache.maven.archetypes \ -DarchetypeArtifactId=maven-archetype-webapp \ -DgroupId=com.mycompany.app \ -DartifactId=my-webapp for WAR
- a directory with pom.xml will be available
     pom.xml contains the Project Object Model (POM) for this project. The POM is the basic unit of work in Maven. This is important to remember because Maven is inherently project-centric in that everything revolves around the notion of a project.
- structure of the project will be
     my-app
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- mycompany
    |               `-- app
    |                   `-- App.java
         -- webapp
            -- WEB-INF
               -- web.xml
               -- any servlet.xml (if spring is used.)
    `-- test
        `-- java
            `-- com
                `-- mycompany
                    `-- app
                        `-- AppTest.java
- like ant tasks available , maven also has tasks like mvn compile, mvn test for running test, mvn package for packaging your proj and mvn install to push to its local repo
- lots of maven plugins available which will provide tasks like clean, create idea project.
- need to look how to deploy to tomcat though?





Web application using Intellij Idea spring , tomcat , maven plugin:
- enable the plugins in Idea plugins.


Need to read in the order of priority:

- try to do transaction management using @transaction and have it at request level.
- need to understand bean life cycle (application context and web application context.)
- need to look into remote deploy and splicing though.
- Tests not running through ant, working through IDE though. [looks like problem with class path]
- Write test for controller, services added.
- Need to look deep into conf of ivy and scope related to that (compile, runtime, test) --> it looks very easy with maven though. (diff between compile and compile->default),
  how to exclude pulling certain dependencies.
- .gitignore getting deleted when we run ant, need to look
- Need to latest version of junit (currently working without annotations though.).
- How to update jars using ivy and maven.
- Using intellij plugins for setting up spring mvc and maven project.
- Build tools (YAML), ant done though
- Mainatin multiple git hub accounts on same computer.
- git push ask password , try to work using the SSH keys.
- CI server.
- Try sample app using maven.


Questions and answers

- see equivalent for global.asax.cs to initialize stuffs (Db connection etc.) ---> found out that it should be class with @configuration which will have bean declarations.