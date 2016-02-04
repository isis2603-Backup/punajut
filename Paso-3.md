# Tabla de Contenidos

-  [Introducción](#introducción)
-  [Módulo Ng Auth](#módulo-ng-auth)
-  [Configuración Módulo de Seguridad](#configuración-módulo-de-seguridad)


## Introducción
Al finalizar este paso el estudiante estará en la capacidad de configurar y hacer uso del módulo de seguridad para un proyecto AngularJS. Las siguientes instrucciones muestran el paso a paso para la configuración del módulo dentro del proyecto.  

## Módulo Ng-Auth
El módulo **ng-crud-auth** tiene como finalidad asociar los *templates* de registro, autenticación, autorización y soporte de restauración de contraseña de un proyecto AngularJS con los servicios REST diseñados en un proyecto de Backend. Usted simplemente debe configurar el módulo con la url de los servicios de backend y definir el estados de éxito. Actualmente, Ng-Crud-Auth tiene soporte para sólo para UI-Router. Ver documentación de [UI-Router](https://github.com/angular-ui/ui-router#angularui-router-)


## Configuración Módulo de Seguridad
Realice los siguientes pasos para configurar el módulo de seguridad

- Usted debe descargar la librería javascript del módulo de seguridad. Para esto descargue el archivo **csw-ng-auth.min.js** del siguiente [link](https://github.com/recursosCSWuniandes/ng-crud-auth/blob/master/dist/csw-ng-auth.min.js) y copielo dentro de la carpeta *resources/js/*. Se recomienda descargar todo el repositorio en su máquina virtual y copiar el archivo **csw-ng-auth.min.js**  ubicado en la ruta dist/*. *Nota:* No olvide registrar el archivo **csw-ng-auth.min.js** dentro del index.html. Ver ejemplo:

```HTML
<script src="resources/js/csw-ng-auth.min.js" type="text/javascript"></script>
```

- Luego, debe registrar el módulo **authModule** en el archivo app.js. Para ésto, inyecte el módulo de seguridad según como se muestra en el ejemplo:
```javascript
var mod = ng.module("mainApp", [
        "ui.router",
        "bookModule",
        "editorialModule",
        "authorModule",
        "reviewModule",
        "bookMock",
        "editorialMock",
        "authorMock",
        "reviewMock",
        "ngMessages",
        "authModule" // Módulo de seguridad
    ]);
```
- El módulo authModule requiere para su funcionamiento las librerias **ngCookies** y **Checklist-model**. Para descargarlas haga click en cada link, guarde los archivos como archivos javascript dentro de la carpeta *resources/js*.

 - [Link de descarga ngCookies](https://code.angularjs.org/1.4.8/angular-cookies.min.js)
 - [Link de descarga checklist-model](http://vitalets.github.io/checklist-model/checklist-model.js)

Nota: Tenga en cuenta el orden de importación de las librerias descargadas. Un ejemplo de registro de las librerías en el archivo index.html es:
```javascript
        <script src="resources/js/checklist-model.js" type="text/javascript"></script>
        <script src="resources/js/angular-cookies.min.js" type="text/javascript"></script>        
        <script src="resources/js/csw-ng-auth.min.js" type="text/javascript"></script>
```

- Para el buen funcionamiento del módulo de seguridad es necesario configurar los roles asociados a la aplicación y los menús respectivos para cada rol. Mediante el siguiente ejemplo se configura el provider de seguridad dentro del archivo app.js:

```javascript
...
mod.config(['authServiceProvider', function (auth) {
            auth.setValues({
                apiUrl: 'api/users/',
                successState: 'book'
            });
            auth.setRoles({'user': [{id: 'indexUser', label: 'Author', icon: 'list-alt', state: 'author'}],
                'admin': [{id: 'indexAdmin', label: 'Admin', icon: 'list-alt', state: 'editorial'}]});
        }]);

```

- Finalmente, usted debe agregar la directiva que despliega el botón de login ```<login-button></login-button>``` al index.html. Se recomienda colocarla en el toolbar superior, tal como se muestra a continuación:

```html
<div class="col-md-12">
                <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-bar">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href>BookBasico</a>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="main-bar">
                            <ul class="nav navbar-nav">
                                <li><a ui-sref="book">Book</a></li>
                                <li><a ui-sref="editorial">Editorial</a></li>
                                <li><a ui-sref="author">Author</a></li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                <login-button></login-button><!-- Directiva ngauth -->
                                </li>
                            </ul>
                        </div> <!-- /.navbar-collapse -->
                    </div> <!-- /.container-fluid -->
                </nav>
            </div>
```

# Configuración de Mock para Módulo AuthModule

El módulo de seguridad anteriormente configurado necesita para su funcionamiento la comunicación con servicios de backend que permitan la autenticación y autorización de usuarios. Por lo tanto, usted debe crear un archivo mock para simular las respuestas del servidor. A continuación se muestra los pasos para crear el archivo **authMock.js**.

- ### Crear módulo auth.mock.js
Usted debe crear el archivo auth.mock.js dentro del directorio *src/modules/auth/*. Este archivo debe declarar el módulo authMock, agregar la librería de ngMockE2E y configurar las peticiones o servicios a simular del backend. Las urls a simular son:

Acción          | Url                | Descripción                               | Tipo de petición
--------------- |--------------------|-------------------------------------------|-------------------
Login           | api/users/login    | Inicio de sesión en la aplicación         | POST
Register        | api/users/register | Registro de usuarios en la aplicación     | POST
Me              | api/users/me       | Obtiene el actual usuario conectado       | GET
Forgot          | api/users/forgot   | Envía correo para restablecer contraseña  | POST
Logout          | api/users/logout   | Elimina la sesión del usuario conectado   | GET

En el siguiente archivo usted puede observar la declaración de cada petición dentro del módulo authMock. Tenga en cuenta que cada método ```$httpBackend.whenXXX``` intercepta las solicitudes realizadas desde los módulos de servicios, tales como: "bookService", "authorService" y en este caso "authService", si desea recordar la creación de los mocks para book, author, review y editorial vaya al siguiente [link](https://github.com/Uniandes-isis2603-201520/ejemplo-book/wiki/Paso-2#m%C3%B3dulos-mocks). *Nota:* Como puede observar el módulo "authService" no se encuentra dentro de la aplicación *bookstore-web* sino ya está declarada dentro de la librería *ng-crud-auth*.







