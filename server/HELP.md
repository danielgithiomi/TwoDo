# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.6/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.6/reference/web/servlet.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.6/reference/using/devtools.html)
* [Spring Security](https://docs.spring.io/spring-boot/3.5.6/reference/web/spring-security.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.6/reference/data/sql.html#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

---

## Using Maven (not Gradle)

This project is a Maven project. There are no Gradle build files (no `build.gradle`/`settings.gradle`). Use Maven to
build, run, and test.

### Command line

- If you have Maven installed:
    - mvn -v
    - mvn clean verify
    - mvn spring-boot:run
- Or use the Maven Wrapper script included in the repo (recommended so everyone uses the same Maven version):
    - On macOS/Linux: ./mvnw clean verify
    - On Windows (PowerShell): .\mvnw.cmd clean verify
    - Run the app: ./mvnw spring-boot:run

Note: If the wrapper fails because it cannot find `.mvn/wrapper/maven-wrapper.properties`, either install Maven locally
and use `mvn`, or generate the wrapper with a local Maven once: `mvn -N wrapper:wrapper`.

### IntelliJ IDEA configuration to use Maven

1) Open as a Maven project

- File > Open, select the server/pom.xml and open as a project. IntelliJ will index it as a Maven project.

2) Ensure Maven is the build tool

- File > Settings (Preferences on macOS) > Build, Execution, Deployment > Build Tools > Maven:
    - Maven home: use Bundled (recommended) or your local Maven.
    - JDK for importer: select your project JDK.
    - If you prefer the wrapper: check "Use Maven wrapper" (requires the wrapper files) or otherwise point to local
      Maven.

3) Delegate builds and runs to Maven (optional but enforces Maven)

- Settings > Build, Execution, Deployment > Build Tools > Maven > Runner:
    - Check "Delegate IDE build/run actions to Maven".
    - Set VM options/Environment as needed.

4) Reimport Maven

- Open the Maven tool window (View > Tool Windows > Maven) and click the refresh icon to reimport.

5) Create run configurations backed by Maven

- Run > Edit Configurations > Add New > Maven
    - For running: Command line: spring-boot:run
    - For tests: Command line: test

Troubleshooting

- If IntelliJ suggests Gradle, it means there are stray Gradle files. Remove any `build.gradle`, `settings.gradle`, or
  `.gradle` directories if they exist.
- If wrapper scripts exist but the `.mvn/wrapper` directory is missing, either run `mvn -N wrapper:wrapper` once to
  generate it, or use your local `mvn` commands.
