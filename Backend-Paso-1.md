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

En el transcurso de este tutorial estaremos trabajando ambos proyectos iterativamente, con el fin de dejar claro el propósito de cada componente.

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
```xml
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

```xml
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
```xml
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
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://java.sun.com/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
</beans>
```

## Configuración servicios REST

Para poder utilizar Jersey, es necesario incluir la dependencia a este en el POM. Esto se hace añadiendo las dependencias al POM del proyecto web:

- `jersey-bom`: Se añade esta dependencia a `dependencyManagement`, la cual permitirá obtener las dependencias compatibles con la versión especificada.
- `jersey-container-servlet`: Dependencia de Jersey. En este caso no es necesario definir la versión ya que la dependencia anterior la especifica.

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
Cuando se desarrolla un API REST, es importante diferenciar la representación del recurso de cualquier estructura de datos al interior de la aplicación.

Por ejemplo, en una aplicación financiera podría crearse un servicio que reciba los datos de un préstamo que desea el usuario y el sistema responda las cuotas que debe pagar. Esta es información que no se encuentra en base de datos (sino que es un resultado de un cálculo) y la respuesta tiene una estructura diferente. Por esta razón se crean los DTO, los cuales definen la estructura de los datos a transmitir por el API REST.

En esta implementación, los DTO son POJO anotados con `@XmlRootElement` de JAX-B. Esta anotación permite serializar y deserializar los POJO a XML o JSON. Por ejemplo:

```java
package co.edu.uniandes.csw.bookstore.dtos;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookDTO {

    private Long id;
    private String name;
    private String isbn;
    private String image;
    private Date publishDate;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
```

## Servicios
Ahora creamos la clase `BookService` en el mismo paquete que la clase `RestConfig`, en esta clase se expondrán todos los servicios de **Book**.
La clase deberá tener las siguientes anotaciones:

```java
package co.edu.uniandes.csw.bookstore.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookService {

}
```

- `@Path`: Define la URL en la cual se publicará el servicio. Esta ruta es relativa a la definida en la anotación `@ApplicationPath` de `RestConfig`. En este caso, la ruta sería `/api/books`.
- `@Consumes`: Define el tipo de dato que recibirá. Se establece el formato JSON
- `@Produces`: Define el tipo de dato que responderá. Se establece el formato JSON

Adicionalmente, Jersey provee otras anotaciones que mapean métodos HTTP a métodos Java, con el fin de poder realizar diferentes acciones dependiendo del método HTTP. Entre estas anotaciones están `@GET`, `@POST`, `@PUT` y `@DELETE`. En el siguiente ejemplo está la firma del método `getBook`, el cual debe retornar una instancia de Book a partir de un ID:
```java
    @GET
    public List<BookDTO> getBook() {
        return null; //Implementar
    }

    @GET
    @Path("{id: \\d+}")
    public BookDTO getBook(@PathParam("id") Long id) {
        return null; //Implementar
    }

    @POST
    public BookDTO createBook(BookDTO dto) {
        return null; //Implementar
    }

    @PUT
    @Path("{id: \\d+}")
    public BookDTO updateBook(@PathParam("id") Long id, BookDTO dto) {
        return null; //Implementar
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteBook(@PathParam("id") Long id) {
        //Implementar
    }
```

> El método recibe un DTO que, como se mencionó anteriormente, puede serializarse y deserializarse gracias a JAX-B

# Implementación de API lógica
Los pasos anteriores nos permitieron la creación de servicios REST, sin embargo, hasta ahora no realizar ninguna tarea. En este paso crearemos un API que nos permita acceder a los métodos expuestos por la capa lógica.

Para poder definir el API de la lógica es necesario crear las clases que representarán los datos que reciben y envían. Estos datos son entidades de JPA, que representan registros de la base de datos. Una vez creados, definiremos las interfaces que expondrán los métodos de la lógica.

## Creación del proyecto bookstore-logic
Dado que los servicios deben estar desacoplados de la lógica, crearemos una capa adicional que nos permita definir un API al cual tendrá acceso los servicios REST, independiente de la implementación de estos. Para esto, en Netbeans escogemos la opción de crear un nuevo proyecto, cuya categoría es `Maven`, el tipo de proyecto es `Java Application` y su nombre será `bookstore-logic`. Una vez se cree, lo añadiremos a los módulos del proyecto padre haciendo clic derecho en `Modules` y seleccionando la opción `Add Existing Module...`

> Se puede unir ambos pasos haciendo clic derecho en `Modules` y seleccionando la opción `Create New Module...`

Después de esto, es necesario añadir las dependencias a utilizar en este proyecto:

```xml
<dependency>
    <groupId>org.eclipse.persistence</groupId>
    <artifactId>eclipselink</artifactId>
    <version>2.6.2</version>
</dependency>
<dependency>
    <groupId>co.edu.uniandes.csw</groupId>
    <artifactId>crud-utils</artifactId>
    <version>0.1.3</version>
</dependency>
<dependency>
    <groupId>javax</groupId>
    <artifactId>javaee-api</artifactId>
    <version>7.0</version>
    <scope>provided</scope>
</dependency>
```

- `eclipselink`: Implementación de JPA.
- `javaee-api`: API de Java Enterprise.
- `crud-utils`: Librería de utilidades de Uniandes.

## Configuración de persistencia
Para la implementación de la persistencia se usará JPA (Java Persistence API). Este *framework* permite hacer un mapeo de las tablas de la base de datos a objetos Java, de manera que se puedan manipular fácilmente.

Para configurar JPA, es necesario crear un archivo `persistence.xml` en la carpeta `META-INF` de los recursos. Para esto, se hace clic derecho en el proyecto de lógica y se selecciona `New > Other...`, y en la categoría **XML** elegir la opción **XML Document** con la siguiente configuración:

- El nombre del archivo debe ser `persistence.xml`
- La ubicación del archivo debe ser `src/main/resources/META-INF`.

Finalmente, incluir en el archivo lo siguiente:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
  <persistence-unit name="BookStorePU" transaction-type="JTA">
    <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
    <jta-data-source>java:app/jdbc/BookStore</jta-data-source>
    <properties>
      <property name="eclipselink.logging.level" value="FINE"/>
      <property name="eclipselink.ddl-generation" value="create-or-extend-tables"/>
      <property name="eclipselink.cache.type.default" value="NONE" />
    </properties>
  </persistence-unit>
</persistence>
```

> En este archivo se define que la conexión a base de datos se hará por un recurso JNDI, el cual se puede definir directamente en el contenedor. De esta manera, la base de datos a usar se puede cambiar en el contenedor sin necesidad de modificar código fuente.

## Entidades
Las estructuras de datos que manejará el API de la lógica son las entidades. Estas son POJO que permiten realizar un mapeo entre la base de datos y objetos de Java. Para la creación de estas es necesario anotar los POJO con `@Entity`. Con esto, JPA crea la asociación entre las entidades y las tablas de base de datos, facilitando la consulta y modificación de los datos.

Estas entidades las guardaremos en un paquete llamado `co.edu.uniandes.csw.bookstore.entities` en el proyecto `bookstore-logic`. El siguiente es un ejemplo de la entidad `BookEntity`:

```java
package co.edu.uniandes.csw.bookstore.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BookEntity extends BaseEntity implements Serializable {

    private String isbn;
    private String image;
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    private String description;

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
``` 

> **IMPORTANTE**
> Para poder mapear objetos tipo fecha, JPA necesita que estos atributos se anoten con `@Temporal`, la cual recibe un parámetro tipo `TemporalType` que define si se almacena la fecha, la hora, o ambos.

Se puede observar que la clase extiende de `BaseEntity`. Esta es una clase abstracta parte de `crud-utils` que permite heredar los atributos `id` y `name`, al igual que sobreescribe la implementación de los métodos `equals` y `hashcode` para realizar la comparación a través del ID si existe. Se recomienda que todas las entidades hereden de esta clase, ya que ofrece funcionalidad que será usada constantemente en los proyectos.

## Creación de interfaces
Teniendo las entidades definidas, ya podemos crear las interfaces que expondrán los métodos de la lógica. Para esto, creamos el paquete `co.edu.uniandes.csw.bookstore.api` dentro del proyecto `bookstore-logic`. Dentro de este paquete creamos la interfaz `IBookLogic`.

```java
package co.edu.uniandes.csw.bookstore.api;

import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.List;

public interface IBookLogic {

    public List<BookEntity> getBooks();

    public BookEntity getBook(Long id);

    public BookEntity createBook(BookEntity entity);

    public BookEntity updateBook(BookEntity entity);

    public void deleteBook(Long id);
}
```

Los métodos definidos en la interfaz corresponden a operaciones CRUD de listar, consultar, crear, actualizar y borrar respectivamente.

## Converters
Dada la cantidad de datos que se transmiten en los DTO y su similitud con los Entity, se crean clases llamadas **Converter**. Estas clases son las encargadas de realizar la conversión entre un DTO y un Entity (en ambos sentidos), y contienen métodos con los siguientes prefijos:

* **ref**: Métodos que se usan cuando se transmitirá información como una referencia desde otro, los cuales usualmente son `id` y `name`. Por ejemplo, cuando se carga una instancia de Book y se quiere saber el nombre de la Editorial a la que está asociada, se usa este método para convertir la editorial. De esta manera, se evita la transmisión de datos innecesarios.
* **basic**: Métodos que se usan dentro de los métodos **list**. Estos realizan la conversión de todos los atributos de la entidad a excepción de las colecciones. Esto con el fin de evitar consultas exhaustivas que contengan toda la base de datos. Además que al listar registros de una entidad no se visualiza sus colecciones.
* **full**: Métodos que se usan cuando se consulta una instancia específica de una entidad. Estos convierten todos los atributos de una entidad incluyendo las colecciones. Se usas cuando se desea editar una instancia.
* **list**: Métodos usados para convertir listas de registros. Estos por dentro usan los métodos **basic**.

Los converter se crearán en el proyecto web, en el paquete `co.edu.uniandes.csw.bookstore.converters`. Sin embargo, para teneer acceso a las clases de entidades, es necesario añadir al POM del proyecto web la dependencia al proyecto de lógica:

```xml
<dependency>
    <groupId>co.edu.uniandes.csw.bookstore</groupId>
    <artifactId>bookstore-logic</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Finalmente, la implementación de la clase `BookConverter` queda así:

```java
package co.edu.uniandes.csw.bookstore.converters;

import co.edu.uniandes.csw.bookstore.dtos.BookDTO;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.ArrayList;
import java.util.List;

public class BookConverter {

    private BookConverter() {
    }

    public static BookDTO refEntity2DTO(BookEntity entity) {
        if (entity != null) {
            BookDTO dto = new BookDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPublishDate(entity.getPublishDate());
            dto.setIsbn(entity.getIsbn());
            dto.setImage(entity.getImage());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    public static BookEntity refDTO2Entity(BookDTO dto) {
        if (dto != null) {
            BookEntity entity = new BookEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    public static BookDTO basicEntity2DTO(BookEntity entity) {
        if (entity != null) {
            BookDTO dto = refEntity2DTO(entity);

            return dto;
        } else {
            return null;
        }
    }

    public static BookEntity basicDTO2Entity(BookDTO dto) {
        if (dto != null) {
            BookEntity entity = new BookEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setIsbn(dto.getIsbn());
            entity.setImage(dto.getImage());
            entity.setPublishDate(dto.getPublishDate());
            entity.setDescription(dto.getDescription());

            return entity;
        } else {
            return null;
        }
    }

    public static BookDTO fullEntity2DTO(BookEntity entity) {
        BookDTO dto = basicEntity2DTO(entity);
        return dto;
    }

    public static BookEntity fullDTO2Entity(BookDTO dto) {
        BookEntity entity = basicDTO2Entity(dto);
        return entity;
    }

    public static List<BookDTO> listEntity2DTO(List<BookEntity> entities) {
        List<BookDTO> dtos = new ArrayList<BookDTO>();
        if (entities != null) {
            for (BookEntity entity : entities) {
                dtos.add(fullEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<BookEntity> listDTO2Entity(List<BookDTO> dtos) {
        List<BookEntity> entities = new ArrayList<BookEntity>();
        if (dtos != null) {
            for (BookDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
```

## Inyección de la lógica en API REST
Finalmente podemos incluir en los servicios REST la invocación de la lógica. Gracias a la inyección de dependencias de JavaEE, es posible hacer uso de una implementación del API sin necesidad de conocerla. Esto se logra con la anotación `@Inject`. En la clase `BookService` creamos un atributo privado cuyo tipo es `IBookLogic` e incluimos la anotación `@Inject` para que el contenedor se encargue de inicializarlo en ejecución.

```java
@Inject
private IBookLogic bookLogic;
```

Finalmente, haciendo uso de los converter y el API de la lógica, completamos la implementación de los servicios, para que llamen a los métodos de la lógica y devuelvan la información correspondiente:

```java
public class BookService {

    @Inject
    private IBookLogic bookLogic;

    @GET
    public List<BookDTO> getBook() {
        return BookConverter.listEntity2DTO(bookLogic.getBooks());
    }

    @GET
    @Path("{id: \\d+}")
    public BookDTO getBook(@PathParam("id") Long id) {
        return BookConverter.basicEntity2DTO(bookLogic.getBook(id));
    }

    @POST
    public BookDTO createBook(BookDTO dto) {
        return BookConverter.basicEntity2DTO(bookLogic.createBook(BookConverter.basicDTO2Entity(dto)));
    }

    @PUT
    @Path("{id: \\d+}")
    public BookDTO updateBook(@PathParam("id") Long id, BookDTO dto) {
        dto.setId(id);
        return BookConverter.basicEntity2DTO(bookLogic.updateBook(BookConverter.basicDTO2Entity(dto)));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteBook(@PathParam("id") Long id) {
        bookLogic.deleteBook(id);
    }
}
```

------------

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
