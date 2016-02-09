# Tabla de Contenidos

-  [Introducción](#introducción)
-  [Relaciones simples uno a muchos](#relaciones-simples-uno-a-muchos)
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
Para verificar el correcto funcionamiento, usted debe observar la ventana para crear o editar books y debe aparecer un select con todas las opciones para editorial. 

## Relaciones de agregación muchos a muchos

Para implementar las relaciones muchos a muchos se dispone a crear un modal que aparecerá en la pantalla con el fin de asociar o no varios items a un módulo específico. En este caso, existe una relación muchos a muchos entre Book y Authors lo que significa que "un libro tiene asociado varios autores y autor tiene asociado varios libros". Para implementar la interfaz gráfica se utiliza la directiva [ui.bootstrap.modal](https://angular-ui.github.io/bootstrap/) que crea un modal para seleccionar las opciones correspondientes para book y author. **Usted debe revisar la documentación de la directiva ui.bootstrap.modal antes de continuar con la implementación.**

### Implementación de la directiva *ui.bootstrap.modal* para relaciones Muchos a Muchos

- Crear template html para modal de authores authorModal.tpl.html.
- Modificar el template book.tpl.html
- Implementar la lógica de comunicación en el controlador BookCtrl
- Implementar la lógica de comunicación del template con el controlador authorsCtrl
- Agregar métodos en el servicio BookService para añadir y leer listas de authores.
- Implementar métodos que simulan la respuesta de los anteriores servicios mediante el uso de Mocks.

## Relaciones de composición Uno a Muchos.

Para implementar las relaciones de composición *Uno a Muchos* se dispone a crear un **Tab** adicional en la interfaz del  módulo dueño de la relación, donde se puede realizar todas las operaciones CRUD al módulo hijo. Es importante resaltar que una relación de composición establece una fuerte relación entre el dueño de la relación y sus hijos, por ejemplo un hijo sólo puede ser creado y asociado a un módulo padre, *No puede existir hijos sin tener un padre*, de igual manera *si el módulo padre desaparece todos sus hijos también eliminados*. En el ejemplo BookStore existe una relación de composición entre Book y Reviews lo que significa que "Un libro tiene muchos reviews " y cada review sólo puede ser creado a un libro. Por lo tanto si se elimina el libro desaparece junto con él todos sus reviews. **Nota: El paso 2 tenía el módulo review independiente al módulo book, en este paso el link de Review desaparece y sólo se puede crear un review cuando se edita un libro.**


### Implementación de la relación composite Uno a Muchos.

- Modificar el template book.tpl.html
- Implementar el controlador de Reviews acorde a la relación de composición
- Agregar métodos en el servicio BookService para crear, editar, leer y remover listas de reviews.
- Implementar métodos que simulan la respuesta de los anteriores servicios mediante el uso de Mocks.


## Manejo de eventos con AngularJS






## FAQ