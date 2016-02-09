# Tabla de Contenidos

-  [Introducción](#introducción)
-  [Relaciones simples uno a muchos](#)
-  [Relaciones de agregación muchos a muchos](#)
-  [Relaciones de composición](#)
-  [Manejo de eventos con AngularJS](#)
-  [FAQ](#faq)


## Introducción
Al finalizar este paso el estudiante estará en la capacidad representar relaciones de composición, agregación y simples en una aplicación web de AngularJS. A continuación se muestra el paso a paso para implementar las relaciones modeladas en cada módulo de la aplicación.

## Relaciones simples Uno a Muchos
Teniendo en cuenta el módelo de clases para el proyecto, se observa que existe una relación Uno a Muchos entre los módulos Editorial y Book, es decir parafraseando: "Un libro tiene una editorial y una editorial tiene muchos libros", esto significa que a nivel de interfaz, la ventana de libro debe tener un drop-Down donde el usuario pueda seleccionar una editorial para el libro. Para implementar esta relación en la interfaz siga los siguientes pasos:
- Añadir el siguiente código html para el select o drop-Down del formulario del módulo book:
```html
<div class="form-group col-md-12" ng-class="{'has-success': form.editorial.$valid && form.editorial.$dirty, 'has-error': form.editorial.$invalid && (form.editorial.$dirty || form.$submitted)}" >
                <label for="editorial" class="col-md-2 control-label">Editorial</label>
                <div class="col-md-10">
                    <select id="editorial" name="editorial" ng-options="rc.name for rc in editorials track by rc.id" class="form-control" type="text" ng-model="currentRecord.editorial" ></select>
                </div>
            </div>
```
- Agregar la lógica en el controlador para consultar las editoriales existentes.Para esto usted debe inyectar el servicio editorialService en el controlador de book y realizar la petición para consultar las editoriales tal como se muestra en el siguiente ejemplo.

```javascript

mod.controller("bookCtrl", ["$scope", "bookService", "editorialService", function ($scope, svc, editorialSvc) {

...
editorialSvc.fetchRecords().then(function (response) {
                $scope.editorials = response.data;
            });
...
}]);
```

## FAQ