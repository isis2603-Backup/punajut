# Tabla de Contenidos

-  [Introducción](#introducción)
-  [Módulo Ng Auth](#módulo-ng-auth)
-  [Configuración Módulo de Seguridad](#configuración-módulo-de-seguridad)
-  [Configuración de Mock para Módulo AuthModule](#configuración-de-mock-para-módulo-authmodule)
-  [FAQ](#faq)


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

- Crear módulo auth.mock.js: 
Usted debe crear el archivo auth.mock.js dentro del directorio *src/modules/auth/*. Este archivo debe declarar el módulo authMock, agregar la librería de ngMockE2E y configurar las peticiones o servicios a simular del backend. Las urls a simular son:

Acción          | Url                | Descripción                               | Tipo de petición
--------------- |--------------------|-------------------------------------------|-------------------
Login           | api/users/login    | Inicio de sesión en la aplicación         | POST
Register        | api/users/register | Registro de usuarios en la aplicación     | POST
Me              | api/users/me       | Obtiene el actual usuario conectado       | GET
Forgot          | api/users/forgot   | Envía correo para restablecer contraseña  | POST
Logout          | api/users/logout   | Elimina la sesión del usuario conectado   | GET

De igual manera, en el archivo auth.mock.js se observa la declaración de cada petición dentro del módulo authMock. Tenga en cuenta que cada método ```$httpBackend.whenXXX``` intercepta las solicitudes realizadas desde los módulos de servicios, tales como: "bookService", "authorService" y en este caso "authService", si desea recordar la creación de los mocks para book, author, review y editorial vaya al siguiente [link](https://github.com/Uniandes-isis2603-201520/ejemplo-book/wiki/Paso-2#m%C3%B3dulos-mocks). *Nota:* Como puede observar el módulo "authService" no se encuentra dentro de la aplicación *bookstore-web* sino está declarado dentro de la librería *ng-crud-auth*.

A continuación se muestra el contenido del archivo auth.mock.js junto con comentarios para cáda método.

```javascript
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module('authMock', ['ngMockE2E', 'ngCookies']);

    mod.run(['$httpBackend', '$log', '$cookies', function ($httpBackend, $log, $cookies) {
            var ignore_regexp = new RegExp('^((?!api).)*$');
            var messages =  { debug: "You Called ", 
                              wrong_pass: "The Password you've entered is incorrect!",
                              email_info: "The message was sent!!"
                            };
            /*
             * @type Array
             * users: Array con Usuarios por defecto
             */
            var users = [{
                    userName: "User",
                    password: "Uni123",
                    surName: "SurName",
                    email: "test@uniandes.edu.co",
                    givenName: "GivenName",
                    roles: ['user', 'admin'],
                    customData: {id: 1}
                }];

            var userConnected = "";
            var forgotPassEmails = [];
            var urls = {me: "api/users/me", 
                        login: "api/users/login",
                        register: "api/users/register",
                        logout: "api/users/logout", 
                        forgot: "api/users/forgot"};
            /*
             * Ignora las peticiones GET, no contempladas en la Exp regular ignore_regexp
             */
            $httpBackend.whenGET(ignore_regexp).passThrough();

            /*
             * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/users/me"
             * Retorna el usuario conectado actualmente.
             * Response: 200 -> Status ok, record -> usuario conectado y ningún header.
             */
            $httpBackend.whenGET(urls.me).respond(function (method, url) {
                $log.debug(messages.debug + urls.me);
                if (userConnected === "") {
                    return [204, userConnected, {}];
                } else {
                    return [200, userConnected, {}];
                }
            });
            /*
             * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/users/register"
             * Guarda en memoria (Array users) el usuario registrado
             * Response: 201 -> Status created, record -> usuario registrado y ningún header.
             */
            $httpBackend.whenPOST(urls.register).respond(function (method, url, data) {
                $log.debug(messages.debug + urls.register);
                var record = ng.fromJson(data);
                record.customData = {};
                record.customData.id = Math.floor(Math.random() * 10000);
                users.push(record);
                return [201, record, {}];
            });
            /*
             * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/user/login"
             * Inicia sesion en la aplicacion, valida respecto al Array users 
             * Response: 200 -> Status ok, record -> usuario y nungun header.
             */
            $httpBackend.whenPOST(urls.login).respond(function (method, url, data) {
                $log.debug(messages.debug + urls.login);
                var record = ng.fromJson(data);
                var state = 401;
                var response = messages.wrong_pass;
                ng.forEach(users, function (value) {
                    if (value.userName === record.userName && value.password === record.password) {
                        response = ng.copy(value);
                        state = 200;
                        $cookies.put("Token","UN14ND3S");
                    }
                });
                return [state, response];
            });


            /*
             * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/users/forgot"
             * Guarda en un array las direcciones de correo de los usuarios que olvidaron el password
             * Response: 204, no retorna ningun dato ni headers.
             */


            $httpBackend.whenPOST(urls.forgot).respond(function (method, url, data) {
                $log.debug(messages.debug + urls.forgot);
                var response = ng.fromJson(data);
                forgotPassEmails.push(response);
                $log.info(messages.email_info);
                return [204, null];
            });

            /*
             * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/users/logout"
             * Elimina la información del usuario conectado
             * Response: 204, no retorna ningun dato ni headers.
             */

            $httpBackend.whenGET(urls.logout).respond(function (method, url) {
                $log.debug(messages.debug + urls.logout);
                userConnected = "";
                $cookies.remove("Token");
                return [204, null, {}];
            });
            

        }]);
})(window.angular);
```

[Ir a auth.mock.js](https://github.com/Uniandes-isis2603-201520/ejemplo-book/blob/paso3/bookstore-web/src/main/webapp/src/modules/auth/auth.mock.js).

**Nota:** Recuerde inyectar el módulo "authModule" y el "authMock" en el módulo principal archivo app.js y registrarlos en el archivo index.html

Finalmente, usted debe explorar la aplicación web abriendo la consola del navegador. En la consola deben aparecer mensajes de log de las solicitudes que se realizan, en este caso, al authMock. Para conocer más acerca de cómo crear mensajes de $log en angular diríjase al siguiente [link](https://docs.angularjs.org/api/ng/service/$log). Por recomendación usted debe agregar mensajes tipo **logs** cuando se acceda a las urls para author, book, editorial y review.

Al finalizar esta guía usted debe tener una aplicación con un template para realizar login , registro y para enviar un correo de restauración de contraseña. El módulo de autenticación debe permite la navegación y el manejo de menús personalizados en el botón de login o de usuario. 

# FAQ

* ¿Cómo soluciono el siguiente error.
Error: Unexpected request: GET api/users/me
No more request expected
    at $httpBackend (angular-mocks.js:1322) ?

El error se debe a que no se ha inyectado "authMock" en el archivo app.js

* ¿Cómo soluciono el siguiente error 
Uncaught Error: [$injector:modulerr] ...errors.angularjs.org/1.4.8/$injector/modulerr?p0=mainApp&p1=Error%3A…alhost%3A8080%2Fbookstore-web%2Fresources%2Fjs%2Fangular.min.js%3A19%3A463 ?

El error se debe a que no se ha inyectado "auth.mock.js" en el archivo index.html

* Hasta el momento tenemos un conflicto entre los estados de la aplicación y de la librería _csw-ng-auth.min.js_ para solucionarlo por favor realice el siguiente paso.

Ir al archivo app.js y comentar la siguiente linea de código ```$urlRouterProvider.otherwise("/book"); ```