# Tabla de contenidos

- [Introducción](#introducción)
- [Configuración de Bootstrap](#configuración-de-bootstrap)
- [Configuración de AngularJS](#configuración-de-angularjs)
  - [Configuración de ui-router](#configuración-de-ui-router)
  - [Creación del módulo principal](#creación-del-módulo-principal)
  - [Configuración de log de debug](#configuración-de-log-de-debug)
  - [Configuración template principal](#configuración-template-principal)
  - [Creación de plantillas](#creación-de-plantillas)
  - [Configuración de estados](#configuración-de-estados)

# Introducción

Este ejemplo puede verse desplegado en [este link](http://uniandes-isis2603-201520.github.io/ejemplo-book)

Para la aplicación que está creando se utilizarán dos frameworks web:

* [AngularJS](https://angularjs.org/): Framework que facilita la creación de *SPA (Single Page Application)* con javascript.
* [Bootstrap](http://getbootstrap.com): Framework que permite la creación rápida de sitios web *responsive* mediante el uso de componentes HTML/CSS/JS.

# Creación del Proyecto
Para el desarrollo de este ejemplo es necesario crear un proyecto *Maven Web Application*. Para esto, desde Netbeans 8.1 debe hacer lo siguiente:

* Click en `File > New Project...`
* Seleccionar categoría **Maven**
* Seleccionar tipo de proyecto **Web Application**
* Hacer click en **Next**
* Escoger un nombre para el proyecto. En este caso se escogió `bookstore-web`
* Escoger un servidor en el cual será desplegado. En este caso se escogió Glassfish 4.0.

Las siguientes secciones del ejemplo se basan en que el directorio de trabajo es aquel en el cual se creó el archivo `index.html`, el cual debió ser generado automáticamente al crear el proyecto Maven Web Application.

El código fuente resultante de este ejemplo se encuentra en [este link](https://github.com/Uniandes-isis2603-201520/ejemplo-book/tree/paso1).



# Configuración de Bootstrap
Inicialmente, para que la aplicación pueda hacer uso de bootstrap, se debe seguir los pasos indicados en [Getting Started](http://getbootstrap.com/getting-started/#template)

En resumen se debe agregar las siguientes etiquetas dentro del elemento `<head>` de `index.html`:

```HTML
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
```

Adicionalmente, es necesario cargar las dependencias de Bootstrap. Para esto, descargamos la versión 3.3.6 desde la [página](http://getbootstrap.com/getting-started/#download). Una vez descargados, descomprimir los archivos y ubicarlos en un nuevo directorio `resources`, el cual debe ir ubicado junto a `index.html`. Se deben copiar las 3 carpetas encontradas en el archivo que descomprimió. 

```
/
|-resources
|  /
|  |-css
|  |-fonts
|  |-js
|-index.html
```

Dado que *Bootstrap* depende de *JQuery*, es necesario descargarlo desde su [página oficial](https://jquery.com/download/) y ubicar el archivo minificado (https://en.wikipedia.org/wiki/Minification_(programming) ) en la ruta `resources/js`.

Finalmente, es necesario añadir en el elemento `<head>` la referencia a los archivos de *Bootstrap* y *JQuery* para que estos sean cargados por el navegador. Tener en cuenta que dado que Bootstrap depende de JQuery, es necesario cargar JQuery antes que el archivo Javascript de Bootstrap para esto es importante mantener el orden como se muestra en a continuación:

```html
<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<script src="resources/js/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
```

# Configuración de AngularJS
Primero que todo, para poder utilizar *AngularJS*, al igual que *Bootstrap* (y cualquier otra librería Javascript o CSS), es necesario incluir una referencia a los fuentes en `index.html`. En este caso, se debe descargar los [fuentes de AngularJS](https://angularjs.org/) y ubicarlos en la ruta `resources/js`. Seguido añadimos una referencia a estos fuentes en `index.html`

```html
<script src="resources/js/angular.min.js" type="text/javascript"></script>
```

## Configuración de ui-router
[UI-Router](https://github.com/angular-ui/ui-router) es una librería para AngularJS que permite manejar la navegación de la aplicación de manera flexible a partir de  *estados* de la interfaz.

Igual que con las librerías anteriores, es necesario descargar los fuentes, ubicarlos en la ruta `resources/js` y referenciarlos en `index.html`.

```html
<script src="resources/js/angular-ui-router.min.js" type="text/javascript"></script>
```

## Creación del módulo principal
Para el uso de AngularJS, es necesaria la creación de módulos. Los módulos son conjuntos de componentes y configuraciones que permiten una organización más eficiente del código fuente, además de facilitar la *inyección de dependencias* (https://docs.angularjs.org/guide/di).

En toda aplicación de *AngularJS*, existe un módulo principal encargado de coordinar los otros. Como convención para este ejemplo, dicho módulo principal se encontrará en la ruta `src/app.js` así que debe crear un archivo Javascript con el nombre _app.js_ para iniciar con el módulo principal.

En este archivo, se hará la creación del módulo principal, el cual llamaremos `mainApp`. `mainApp` será el punto de entrada para *AngularJS* para poder identificar las dependencias a otros módulos y poder cargar la configuración de cada uno de éstos. Adicionalmente, en `mainApp` definiremos los estados en los cuales puede encontrarse la aplicación, los cuales permiten configurar la navegación entre las diferentes vistas de la aplicación.

Todo el contenido de `app.js` se hará dentro de un [closure](http://www.w3schools.com/js/js_function_closures.asp) con el fin de poder crear variables privadas, a continuación veamos el ejemplo.

```javascript
(function (ng) {
  /* aquí el contenido y variables privadas
   * window.angular es un parámetro que pasa al closure
   * ng es el nombre que se le da a dicho parámetro dentro del closure
  */
})(window.angular);
```

Lo primero que hay que hacer para crear el módulo `mainApp` es invocar la función `ng.module`, la cual permite crear módulos, o instanciar un módulo ya existente, dependiendo de la cantidad de parámetros que se pasen a la función, en este caso recibe dos paramentos:

1. Nombre del módulo a crear o instanciar.
2. Array con dependencias para el módulo a crear. Si este parámetro es omitido, la función buscará un módulo ya existente, de lo contrario, creará uno nuevo (si ya existe anteriormente lo sobreescribe).

```javascript
(function (ng) {
  // El resultado de la función se guarda en la variable mod
  var mod = ng.module("mainApp", ["ui.router"]);
})(window.angular);
```

Se puede apreciar en el anterior fragmento de código el Array con la dependencia _ui.router_ que usará el modulo _mainApp_. Es importante resaltar también que siempre que se desee usar un módulo, debe haberse invocado su creación anteriormente. De lo contrario, la aplicación no desplegará correctamente.

Para poder utilizar el módulo recién creado, es necesario incluir en `index.html` una referencia al archivo que lo crea igual que con las librerías anteriores (y con todos los archivos javascript que se utilizarán en la aplicación).

```html
<script src="src/app.js" type="text/javascript"></script>
```

Finalmente, debemos indicar a *AngularJS* cuál es el nombre de nuestro módulo principal. Esto se hace a través de la [directiva](https://docs.angularjs.org/guide/directive) `ng-app`. Cabe aclarar que sólo se puede utilizar directivas de AngularJS en elementos del DOM que se encuentren dentro de un elemento que tenga la directiva `ng-app`, razón por la cual la ubicaremos en el elemento `<html>`:

```html
<html ng-app="mainApp">
```

## Configuración de log de debug
Durante todo desarrollo, es muy importante poder hacer *debug* a una aplicación con el fin de asegurar que el comportamiento implementado es igual al esperado. Sin embargo, llenar la aplicación de impresiones en consola y que éstas aparezcan en un ambiente de producción no es adecuado. Por esto, se añade a `app.js` la configuración para que la aplicación imprima los mensajes de *debug* sólo cuando éstos se activan:

```javascript
mod.config(['$logProvider', function ($logProvider) {
    $logProvider.debugEnabled(true);
}]);
```

En el bloque anterior, se usa la [inyección de dependencias](https://docs.angularjs.org/guide/di) de AngularJS para inyectar el servicio `$logProvider`, el cual permite configurar el *logger* de *AngularJS*.

## Configuración template principal
El propósito principal de AngularJS es facilitar el desarrollo de aplicaciones web de una página (SPA). La característica principal de este tipo de aplicaciones es que el navegador sólo actualiza en el [DOM](https://www.w3.org/DOM/) aquellos elementos que es necesario cambiar, dejando lo demás sin alterar. De esta manera, se reduce la carga en las peticiones HTTP y se obtiene una experiencia más agradable al usar la aplicación.

En esta aplicación, contamos con una porción de la interfaz que está presente en todos los estados posibles, y otra parte que es dinámica y se actualiza dependiendo del estado. La parte inmutable se encuentra configurada en `index.html` y permite mantener uniformidad en la interfaz, además de puntos globales de acceso que permitan usar la aplicación más fácilmente.

La parte estática se trabaja como cualquier documento HTML5 con CSS y de ser necesario Javascript. Para este ejemplo, añadiremos un toolbar en la parte superior de la interfaz que nos permita acceder a los diferentes estados de la aplicación. La implementación de este toolbar está hecha acorde con lo que indica *Bootstrap* en su [documentación](http://getbootstrap.com/components/#navbar):

```html
<body style="padding-top: 60px;">
    <div class="container-fluid">
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
                            <li><a ui-sref="book">Book</a></li> <!-- redirect to state book -->
                            <li><a ui-sref="editorial">Editorial</a></li> <!-- redirect to state editorial -->
                        </ul>
                    </div> <!-- /.navbar-collapse -->
                </div> <!-- /.container-fluid -->
            </nav>
        </div>
        <div ui-view></div>
    </div>
</body>
```

En el bloque anterior vemos el uso de la [directiva](https://docs.angularjs.org/guide/directive) [`ui-sref`](http://angular-ui.github.io/ui-router/site/#/api/ui.router.state.directive:ui-sref). Esta directiva es parte de `ui-router` y permite crear un link en el navegador que lleve a la URL correspondiente a un estado específico. De esta manera, es fácil crear botones o enlaces que faciliten la navegación.

Adicionalmente encontramos la directiva [`ui-view`](http://angular-ui.github.io/ui-router/site/#/api/ui.router.state.directive:ui-view) que permite definir en qué sección de la plantilla se llevará a cabo la actualización dinámica de la aplicación. Es decir, dentro del elemento con esta directiva es donde se cargarán las plantillas correspondientes a los estados.

## Creación de plantillas
En este momento la aplicación se encuentra lista para crear las plantillas que definirán la interfaz de la aplicación. Para facilitar el mantenimiento de la aplicación conforme a su crecimiento, se sugiere crear todos los módulos de la aplicación en la ruta `src/modules`, en la cual cada módulo tendrá su respectiva carpeta, con el fin de mantener separados los fuentes de cada uno. por ejemplo, el módulo **book** se encontraría en `src/modules/book`.

A continuación crearemos una plantilla para el módulo **book** llamada `book.tpl.html`, la cual será visible cuando se solicite el estado `book`. En este momento, la plantilla a crear tendrá sólo contenido estático, sin embargo más adelante en este tutorial se cambiará este comportamiento para implementar contenido dinámico.

```html
<div class="col-sm-12">
    <div class="col-md-4 col-sm-6 col-lg-3 well">
        <div class="col-md-12">
            <div class="img-thumbnail" id="image">
                <img class="img-responsive" style="height: 20vmax;" src="http://ecx.images-amazon.com/images/I/61KaCYPW28L._AA160_.jpg" alt="">
            </div>
        </div>
        <div class="caption" >
            <p><strong>Name:</strong> The Life We Bury</p>
            <p><strong>Description:</strong> College student Joe Talbert has the modest goal of completing a writing assignment for an English class.</p>
            <p><strong>ISBN:</strong> 1616149981</p>
            <p><strong>Publish Date:</strong> October, 2014</p>
        </div>
    </div>
    <div class="col-md-4 col-sm-6 col-lg-3 well">
        <div class="col-md-12">
            <div class="img-thumbnail" id="image">
                <img class="img-responsive" style="height: 20vmax;" src="http://ecx.images-amazon.com/images/I/51MfO0a70ZL._AA160_.jpg" alt="">
            </div>
        </div>
        <div class="caption" >
            <p><strong>Name:</strong> All the Light We Cannot See</p>
            <p><strong>Description:</strong> Marie-Laure lives with her father in Paris near the Museum of Natural History, where he works as the master of its thousands of locks.</p>
            <p><strong>ISBN:</strong> 1476746583</p>
            <p><strong>Publish Date:</strong> May, 2014</p>
        </div>
    </div>
    <div class="col-md-4 col-sm-6 col-lg-3 well">
        <div class="col-md-12">
            <div class="img-thumbnail" id="image">
                <img class="img-responsive" style="height: 20vmax;" src="http://ecx.images-amazon.com/images/I/51OiLCU-zyL._AA160_.jpg" alt="">
            </div>
        </div>
        <div class="caption" >
            <p><strong>Name:</strong> What Alice Forgot</p>
            <p><strong>Description:</strong> Alice Love is twenty-nine, crazy about her husband, and pregnant with her first child.</p>
            <p><strong>ISBN:</strong> 9780425247440</p>
            <p><strong>Publish Date:</strong> April, 2012</p>
        </div>
    </div>
</div>
```

De igual manera, se creará una plantilla para el módulo **Editorial** en la ruta `src/modules/editorial/editorial.tpl.html`. Para entender mejor qué significa cada uno de los elementos y atributos de las plantillas dirigirse a la documentación de [Bootstrap](http://getbootstrap.com/).

```html
<div class="col-md-12">
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Seventh Street Books</td>
            </tr>
            <tr>
                <td>Killer Reads</td>
            </tr>
            <tr>
                <td>Kensington</td>
            </tr>
        </tbody>
    </table>
</div>
```

## Configuración de estados
Como se mencionó anteriormente, `ui-router` es una herramienta que permite configurar la navegación de la aplicación a partir de estados. Por ejemplo, podríamos inventar el estado `login`, configurarlo de manera que al estar activo, se muestre una ventana de inicio de sesión.

Por facilidad para poder ver todos los estados disponibles, esta configuración la haremos en el módulo principal, sin embargo, puede hacerse en cualquier bloque de configuración de cualquier módulo, mientras éste tenga la dependencia a `ui.router`, sea de manera directa o transitiva.

```javascript
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/book");
    $stateProvider
        .state('book', {
            url: '/book',
            templateUrl: "src/modules/book/book.tpl.html"
        })
        .state('editorial', {
            url: '/editorial',
            templateUrl: "src/modules/editorial/editorial.tpl.html"
        });
}]);
```

En el bloque anterior,Se registra por defecto la ruta `/book`, lo cual implica que cuando se solicite una ruta que no corresponda a ningún estado, el navegador será redireccionado a la ruta por defecto. Después se crea un estado llamado `book`, el cual está configurado para llevar al navegador a la url `/book` y cargar la plantilla en la ruta `src/modules/book/book.tpl.html`, correspondiente a la ubicación de la plantilla creada en el paso anterior. Lo mismo se realiza para la entidad *Editorial*.

Así finaliza la configuración del módulo principal, la configuración del logger y dos estados de la aplicación. En el siguiente paso se explicará cómo dar dinamismo a las plantillas y añadir comportamiento a cada estado.