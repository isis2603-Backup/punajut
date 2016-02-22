# Tabla de Contenido
- [Crear Proyecto](#crear-proyecto-padre)
  - [Agregación de POM](#agregación-de-pom)
- [Proyecto Lógica](#bookbasico-logic)
- [Proyecto Api](#bookbasico-api)

# Introducción
En este tutorial haremos la construcción del backend para realizar operaciones CRUD de la entidad **Book**. Al finalizar se tendrá tres proyectos que consisten en:

- **Proyecto Logic:** Es el proyecto que contendrá la lógica y persitencia de la aplicación.
- **Proyecto Web:** Es el proyecto que se ha trabajado hasta ahora con el frontend. Adicional a esto, tendrá la implementación de los servicios RESTful y tendrá dependencia al proyecto Logic.
- **Proyecto Padre:** Agrupa los dos anteriores, almacenando información común de ellos y permitiendo realizar acciones en ellos en conjunto.

# Crear proyecto Padre
Maven ofrece la posibilidad de realizar [herencia][mvn_inheritance] entre proyectos, de manera que de un proyecto tipo POM, otros puedan usar sus propiedades, manejo de dependencias y de plugins, y demás, sin necesidad de redefinirlos.

En este taller, tenemos la necesidad de crear un proyecto independiente para la capa lógica, el cual tiene algunos elementos en común con el proyecto web. Para asegurar la mantenibilidad de estos aspectos comunes haremos uso de la herencia de POM, crearemos un proyecto que contenga estos elementos y del cual se pueda heredar.

Para esto, se debe crear un proyecto tipo POM llamado **BookStore**, y el mismo `GroupId` que el proyecto web.

> Netbeans no permite crear un proyecto sin la carpeta que lo contiene, sin embargo, necesitamos que el archivo `pom.xml` del proyecto padre se encuentre justo al lado de la carpeta que contiene al proyecto web. Para lograr esto, es necesario mover dicho archivo desde el gestor de archivos de su sistema operativo.
>
> ```
|   pom.xml
|
\---bookstore-web
   |   pom.xml
> ```

## Agregación de POM
Otra característica útil de maven es permitir la creación de proyectos de [agregación o multimódulo][mvn_aggregation]. Esta característica permite agrupar diferentes proyectos para ejecutar acciones sobre todos los módulos a partir de un único proyecto agrupador. De esta manera, se puede ejecutar tareas como `clean and build` para todos los proyectos automáticamente.

Para configurar la agregación, es necesario añadir al POM padre los proyectos a agrupar, usando las anotaciones `<modules>` y `<module>`:

```xml
<modules>
    <module>bookstore-web</module>
</modules>
```

> Esto también puede hacerse desde la interfaz gráfica de NetBeans haciendo clic derecho sobre la carpeta **Modules** y seleccionando la opción `Add Existing Module...`

## Configuración POM
En el POM padre se debe agregar un repositorio en donde se encuentran diferentes librerías creadas en Uniandes que se usarán en los proyectos. Despues de esto debería quedar con la siguiente estructura:
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>co.edu.uniandes.csw.bookstore</groupId>
    <artifactId>bookstore</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <modules>
        <module>bookstore-web</module>
        <module>bookstore-logic</module>
    </modules>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <repositories>
        <repository>
            <id>csw-releases</id>
            <name>csw releases</name>
            <url>http://157.253.238.75:8081/content/repositories/releases/</url>
        </repository>
    </repositories>
</project>
```

# API RESTful
Ahora haremos la implementación de los servicios RESTful con Jersey, una implementación de JAX-RS. Estos servicios serán los encargados de recibir peticiones HTTP por parte del frontend, e invocar los métodos de la capa lógica que realicen la tarea requerida.

> Se debe evitar al máximo incluir lógica de negocio en estos servicios. La lógica de negocio debe implementarse en el proyecto de lógica que crearemos más adelante. En el API RESTful debe tenerse únicamente validaciones correspondientes a la capa web (información de autenticación, autorización, acceso a parámetros de la petición o construcción de la respuesta).

## Configuración
### Creación de web.xml
`web.xml` es un descriptor de despliegue que permite definir cómo se mapea las URL a los servlet. Para crearlo, basta con:

 1.  Hacer **clic derecho** sobre el proyecto web.
 2. Seleccionar la opción `New > Other...`
 3. En la categoría **Web** escoger el tipo de archivo **Standard Deployment Descriptor**
 4. Clic en **Finalizar**

Esto creará un archivo en la ruta `src/main/webapp/WEB-INF/web.xml` con el siguiente contenido:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>
</web-app>
```

### Creación de beans.xml
Luego en la misma carpeta se debe crear un archivo llamado beans.xml”, cuya existencia indica que se habilitará la inyección de dependencias. Para crear este archivo se debe se ejecutar los siguientes pasos:

1.  Hacer **clic derecho** sobre el proyecto web.
2. Seleccionar la opción `New > Other...`
3. En la categoría **Context and Dependency Injection** escoger el tipo de archivo **beans.xml (CDI Configuration File)**
4. Clic en **Finalizar**

El archivo será creado en la misma ubicación que `web.xml` y tendrá la siguiente estructura:

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
       bean-discovery-mode="annotated">
</beans>
```

### Creación de glassfish-resources.xml
Glassfish permite la creación de recursos por aplicación, con el fin de crearlos automáticamente en su despliegue. Para la creación de un *pool* de conexiones, se hace mediante el archivo `glassfish-resources.xml`, el cual debe ubicarse en el directorio **WEB-INF**. En nuestro caso, este archivo creará un pool de conexiones y un recurso JNDI para poder accederlo.

Este archivo se debe crear con la siguiente información, que define la configuración para la conexión a base de datos:
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
Luego en la misma carpeta se debe crear un archivo llamado “beans.xml”, el cual indica a GlassFish que la aplicación usa EJBs.
Este archivo debe quedar de la siguiente manera:
```XML

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://java.sun.com/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
</beans>
```

## Configuración servicios

Para poder utilizar Jersey, es necesario incluir la dependencia a este en el POM. Esto se hace añadiendo lo siguiente:

```xml
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
        <groupId>org.glassfish.jersey.containers</groupId>
        <artifactId>jersey-container-servlet</artifactId>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

Ahora tenemos acceso al API de Jersey. Ahora para crear servicios REST, es necesario configurar la aplicación de manera que sepa en qué ruta publicarlos. Para esto, es necesario crear una clase con la anotación `@ApplicationPath`. Pero primero debemos crear el paquete donde se almacenará dicha aplicación, el cual será `co.edu.uniandes.csw.bookstore.services`.

En este paquete creamos una clase Java, la cual llamaremos `RestConfig`. Esta clase será la encargada de:

- Definir la ruta donde se exponen los servicios REST.
- Definir qué servicios exponer
- Definir los filtros a usar (como por ejemplo LoggingFilter para mostrar las peticiones en los log)
- Definir los provider a usar (Los provider son interceptores que permiten realizar tareas al recibir o responder una petición)

En este caso, los servicios que vamos a usar estarán en el mismo paquete que `RestConfig`. Por esta razón debemos registrarlo con Jersey, por lo que la clase quedará implementada de la siguiente manera:
```java
package co.edu.uniandes.csw.bookstore.services;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class RestConfig extends ResourceConfig {

    public RestConfig() {
        packages("co.edu.uniandes.csw.bookstore.services");
    }
}

```

## DTOs
Ahora se creara un paquete llamado “dtos” (Data transfer object), aquí crearemos la clase “BookDTO”, esta clase deberá tener los mismo atributos que la clase “BookEntity” y como su nombre lo indica, se usa para la transferencia de datos entre servicios. Esto es importante ya que muchas veces se quiere personalizar el objeto antes de ser transferido y no como lo provee la base de datos.

En el siguiente enlace se muestra como debe quedar la clase.

[BookDTO.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.api/src/main/java/co/edu/uniandes/csw/bookbasico/dtos/BookDTO.java)

## Converters
Ahora se debe crear un paquete llamado “converters” y aquí crearemos una clase llamada “BookConverter” la cual tiene como función convertir objetos de tipo BookEntity a BookDTO y viceversa. Esta clase es muy útil, ya que lo objetos que requiere los servicios son DTOs y lo objetos que requiere la persistencia son Entities, de igual manera al hacer consultas a la base de datos, esta devuelve objetos de tipo Entity, y los servicios exponen objetos de tipo DTO.

En el siguiente enlace se muestra como debe quedar la clase.

[BookConverter.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.api/src/main/java/co/edu/uniandes/csw/bookbasico/converters/BookConverter.java)

## Servicios
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

# BookBasico Logic
## Entidades
Primero se debe crear un nuevo paquete llamado “entities”, aquí crearemos la clase “BookEntity” con sus respectivos atributos, esta clase debe estar anotada con @Entity la cual funciona para hacer el mapeo con la base de datos usando JPA, esta clase debe extender de “BaseEntity” la cual es una librería que tiene lo métodos y atributos básicos de una entidad, como el nombre y el id.

En el siguiente enlace se muestra como debe quedar la clase.

[BookEntity.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/entities/BookEntity.java)

Se debe tener en cuenta que para el manejo de fechas se debe usar la anotación `@Temporal(TemporalType.DATE)`.

## Persistencia
Se debe crear un paquete llamado “persistence”, aquí crearemos la clase “BookPersistence”, esta clase tiene los métodos básico de acceso a la base de datos (create, update, delete, find,findAll). Esta clase debe estar anotada con @Stateless, y  debe tener un objeto EntityManager, el cual debe estar anotado con @PersistenceContext(unitName = "BookBasicoPU") para indicar la unidad de persistencia que se conecta con la base de datos.

En el siguiente enlace se muestra como debe quedar la clase.

[BookPersistence.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/persistence/BookPersistence.java)

## Interface
Se debe crear un paquete llamado “api”, aquí crearemos la interface “IBookLogic”, en la interface definiremos los métodos que usara los Web services.

En el siguiente enlace se muestra como debe quedar la interface.

[IBookLogic.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/api/IBookLogic.java)

## Lógica
Se debe crear un paquete llamado “ejbs”, aquí crearemos la clase “BookLogic” la cual debe implementar la interface “IBookLogic”, esta clase tendrá los métodos básicos (Crear, Actualizar, Eliminar, Mostrar) los cuales están conectados a la clase de persistencia para su debida interacción con la base de datos.

En el siguiente enlace se muestra como debe quedar la clase.

[BookLogic.java](https://github.com/recursosCSWuniandes/ejemplo-book-back/blob/1.0.0/BookBasico.logic/src/main/java/co/edu/uniandes/csw/bookbasico/ejbs/BookLogic.java)

[mvn_inheritance]: https://maven.apache.org/pom.html#Inheritance "Maven POM inheritance"
[mvn_aggregation]: https://maven.apache.org/pom.html#Aggregation "Maven Aggregation"
