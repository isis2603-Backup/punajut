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
        "bookMock",
        "editorialMock",
        "authorMock",
        "ngMessages",
        "authModule" // Módulo de seguridad
    ]);
```
- El módulo authModule requiere para su funcionamiento las librerias **ngCookies** y **Checklist-model**. Para descargarlas haga click en cada link, guarde los archivos como archivos javascript dentro de la carpeta *resources/js*.

 - [Link de descarga ngCookies](https://code.angularjs.org/1.4.8/angular-cookies.min.js)
 - [Link de descarga checklist-model](http://vitalets.github.io/checklist-model/checklist-model.js)
Nota: Tenga en cuenta el orden de importación de las librerias descargadas. Un ejemplo de registro de las librerías en el archivo index.html es:
```javascript
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
