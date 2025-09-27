# Getting Started

## Connect to MariaDB

1) Add dependencies (already configured in pom.xml):
- spring-boot-starter-data-jpa
- mariadb-java-client

2) Configure connection in src/main/resources/application.properties:

spring.datasource.url=${DB_URL:jdbc:mariadb://localhost:3306/week05}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:password}
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect

You can override DB_URL/DB_USERNAME/DB_PASSWORD via environment variables.

3) Start MariaDB locally with Docker:

docker run --name mariadb -e MARIADB_ROOT_PASSWORD=password -e MARIADB_DATABASE=week05 -p 3306:3306 -d mariadb:11

4) Run the app:

./mvnw spring-boot:run

5) Optional: For tests, the project uses an in-memory H2 database via the "test" profile.

---

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.5/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.5/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.5/reference/using/devtools.html)
* [Validation](https://docs.spring.io/spring-boot/3.5.5/reference/io/validation.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.5/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

