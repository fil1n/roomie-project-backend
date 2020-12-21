# Backend for Roommie project
Application was created for contest using following frameworks, libraries and database:
* Javalin
* Hibernate
* Libphonenumber
* Jbcrypt
* Jackson 
* Postgresql


### Deploy
```
git clone https://github.com/fil1n/source
cd source
mvn clean compile assembly:single
cd target
nohup java -jar $jar_name > source_backend.log  2>&1 &
```
