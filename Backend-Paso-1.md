## Creación primera entidad
-  [Crear Proyecto]( #crear-proyecto)
-  [Proyecto Lógica]( #bookbasico-logic)
-  [Proyecto Api]( #bookbasico-api)

### Crear proyecto
Con Netbeans se debe crear un proyecto Maven padre con el nombre de "BookBasico" (POM Project), una vez creado se deben agregar dos módulos "BookBasico.logic" y "BookBasico.api", el primero tiene la lógica y la interacción con la base de datos y el segundo tiene los Web services. El "BookBasico.logic" debe ser un proyecto Maven Java Application y el "BookBasico.api" debe ser un proyecto Maven Web Application.

##### Configuración POMS
Para el POM padre se debe agregar un repositorio en donde se encuentran diferentes librerías que se usaran en los proyectos.
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>co.edu.uniandes.csw</groupId>
    <artifactId>BookBasico</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <modules>
        <module>BookBasico.logic</module>
    <module>BookBasico.api</module>
  </modules>
    <repositories>
        <repository>
            <id>csw-releases</id>
            <name>csw releases</name>
            <url>http://157.253.238.75:8081/content/repositories/releases/</url>
        </repository>
    </repositories>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
</project>
```
En el POM de BookBasico.logic se deben agregar las dependencias que se muestran a continuación para el correcto funcionamiento de la persistencia.
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>co.edu.uniandes.csw</groupId>
        <artifactId>BookBasico</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <artifactId>BookBasico.logic</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>org.eclipse.persistence</groupId>
            <artifactId>eclipselink</artifactId>
            <version>2.6.1</version>
        </dependency>
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>7.0</version>
        </dependency>
        <dependency>
            <groupId>co.edu.uniandes.csw</groupId>
            <artifactId>crud-utils</artifactId>
            <version>0.1.0</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```
En el POM de BookBasico.api se deben agregar las dependencias que se muestran a continuación para el correcto funcionamiento de los servicios.
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
    <artifactId>EjemploBook</artifactId>
    <groupId>co.edu.uniandes.csw</groupId>
    <version>1.0.0-SNAPSHOT</version>
  </parent>

    <groupId>co.edu.uniandes.csw</groupId>
    <artifactId>EjemploBook.api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>EjemploBook.api</name>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.glassfish.jersey</groupId>
                <artifactId>jersey-bom</artifactId>
                <version>2.22.1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>co.edu.uniandes.csw</groupId>
            <artifactId>EjemploBook.logic</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>co.edu.uniandes.csw</groupId>
            <artifactId>auth-utils</artifactId>
            <version>0.1.0</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.main.extras</groupId>
            <artifactId>glassfish-embedded-all</artifactId>
            <version>4.0</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.containers</groupId>
            <artifactId>jersey-container-servlet</artifactId>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```
#### BookBasico Logic
##### Entidades
Primero se debe crear un nuevo paquete llamado “entities”, aquí crearemos la clase “BookEntity” con sus respectivos atributos, esta clase debe estar anotada con @Entity la cual funciona para hacer el mapeo con la base de datos usando JPA, esta clase debe extender de “BaseEntity” la cual es una librería que tiene lo métodos y atributos básicos de una entidad, como el nombre y el id.

En el siguiente enlace se muestra como debe quedar la clase.

[BookEntity.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/entities/BookEntity.java)

Se debe tener en cuenta que para el manejo de fechas se debe usar la anotación @Temporal(TemporalType.DATE).

##### Persistencia 
Se debe crear un paquete llamado “persistence”, aquí crearemos la clase “BookPersistence”, esta clase tiene los métodos básico de acceso a la base de datos (create, update, delete, find,findAll). Esta clase debe estar anotada con @Stateless, y  debe tener un objeto EntityManager, el cual debe estar anotado con @PersistenceContext(unitName = "BookBasicoPU") para indicar la unidad de persistencia que se conecta con la base de datos.  

En el siguiente enlace se muestra como debe quedar la clase.

[BookPersistence.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/persistence/BookPersistence.java)

##### Interface 
Se debe crear un paquete llamado “api”, aquí crearemos la interface “IBookLogic”, en la interface definiremos los métodos que usara los Web services.

En el siguiente enlace se muestra como debe quedar la interface.

[IBookLogic.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/api/IBookLogic.java)

##### Lógica
Se debe crear un paquete llamado “ejbs”, aquí crearemos la clase “BookLogic” la cual debe implementar la interface “IBookLogic”, esta clase tendrá los métodos básicos (Crear, Actualizar, Eliminar, Mostrar) los cuales están conectados a la clase de persistencia para su debida interacción con la base de datos.

En el siguiente enlace se muestra como debe quedar la clase.

[BookLogic.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/ejbs/BookLogic.java)

#### BookBasico Api
##### Configuración
Primero se debe crear una carpeta llamada “WEB-INF” dentro de la carpeta “Web Pages”, aquí crearemos un archivo XML llamado “glassfish-resources.xml” el cual tendrá la conexión con la base de datos. 
Este archivo debe quedar de la siguiente manera:
```XML
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE resources PUBLIC "-//GlassFish.org//DTD GlassFish Application Server 3.1 Resource Definitions//EN" "http://glassfish.org/dtds/glassfish-resources_1_5.dtd">
<resources>
    <jdbc-connection-pool name="BookBasico_pool" allow-non-component-callers="false" associate-with-thread="false" connection-creation-retry-attempts="0" connection-creation-retry-interval-in-seconds="10" connection-leak-reclaim="false" connection-leak-timeout-in-seconds="0" connection-validation-method="auto-commit" datasource-classname="org.apache.derby.jdbc.ClientDataSource" fail-all-connections="false" idle-timeout-in-seconds="300" is-connection-validation-required="false" is-isolation-level-guaranteed="true" lazy-connection-association="false" lazy-connection-enlistment="false" match-connections="false" max-connection-usage-count="0" max-pool-size="32" max-wait-time-in-millis="60000" non-transactional-connections="false" pool-resize-quantity="2" res-type="javax.sql.DataSource" statement-timeout-in-seconds="-1" steady-pool-size="8" validate-atmost-once-period-in-seconds="0" wrap-jdbc-objects="false">
        <property name="serverName" value="localhost"/>
        <property name="portNumber" value="1527"/>
        <property name="databaseName" value="BookBasico"/>
        <property name="User" value="APP"/>
        <property name="Password" value="APP"/>
        <property name="driverClass" value="org.apache.derby.jdbc.ClientDriver"/>
        <property name="createDatabase" value="create"/>
    </jdbc-connection-pool>
    <jdbc-resource enabled="true" jndi-name="java:app/jdbc/BookBasico" object-type="user" pool-name="BookBasico_pool"/>
</resources>
```
Luego en la misma carpeta se debe crear un archivo llamado “web.xml”, el cual tendrá la configuración de la aplicación web.
Este archivo debe quedar de la siguiente manera:
```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://java.sun.com/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
</beans>
```
Luego en la misma carpeta se debe crear un archivo llamado “beans.xml”, el cual indica a GlassFish que la aplicación usa EJBs. 
Este archivo debe quedar de la siguiente manera:
```XML

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://java.sun.com/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
</beans>
```

##### Configuración servicios
Ahora se debe crear un nuevo paquete en “Source Packages” llamado “services”, aquí crearemos una nueva clase llamada “RestConfig” la cual indicara en que paquete se encuentran los servicios de la aplicación y la ruta principal para acceder a ellos. 

En el siguiente enlace se muestra como debe quedar la clase.

[RestConfig.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.api/src/main/java/co/edu/uniandes/csw/bookbasico/services/RestConfig.java)

##### DTOs
Ahora se creara un paquete llamado “dtos” (Data transfer object), aquí crearemos la clase “BookDTO”, esta clase deberá tener los mismo atributos que la clase “BookEntity” y como su nombre lo indica, se usa para la transferencia de datos entre servicios. Esto es importante ya que muchas veces se quiere personalizar el objeto antes de ser transferido y no como lo provee la base de datos.

En el siguiente enlace se muestra como debe quedar la clase.

[BookDTO.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.api/src/main/java/co/edu/uniandes/csw/bookbasico/dtos/BookDTO.java)

##### Converters
Ahora se debe crear un paquete llamado “converters” y aquí crearemos una clase llamada “BookConverter” la cual tiene como función convertir objetos de tipo BookEntity a BookDTO y viceversa. Esta clase es muy útil, ya que lo objetos que requiere los servicios son DTOs y lo objetos que requiere la persistencia son Entities, de igual manera al hacer consultas a la base de datos, esta devuelve objetos de tipo Entity, y los servicios exponen objetos de tipo DTO. 

En el siguiente enlace se muestra como debe quedar la clase.

[BookConverter.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.api/src/main/java/co/edu/uniandes/csw/bookbasico/converters/BookConverter.java)

##### Servicios
Ahora se debe crear una clase llamada “BookService” en el paquete anteriormente creado “services”, en esta clase se expondrán todos los servicios de “Book”. 
La clase deberá tener las siguientes anotaciones:
```
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
```
Estas anotaciones indican la ruta para consumir los servicios, el tipo de dato que consume y produce el servicio. 

Cada método debe estar anotado por el tipo de servicio que provee, los cuales son: POST, PUT, GET o DELETE y un path de ser necesario, para indicar la ruta a ese servicios especifico. Por ejemplo:
```
@GET
@Path("{id: \\d+}")
public BookDTO getBook(@PathParam("id") Long id) {
    return BookConverter.basicEntity2DTO(bookLogic.getBook(id));
}
```
En el siguiente enlace se muestra como debe quedar la clase.

[BookService.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.api/src/main/java/co/edu/uniandes/csw/bookbasico/services/BookService.java)
