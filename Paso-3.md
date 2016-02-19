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

- **Crear template html para modal de authores authorModal.tpl.html:**
Usted debe crear el template authorModal.tpl.html en la url *src/modules/book*, este template es el encargado de mostrar al usuario los autores disponibles para seleccionar y asociar a un libro. **Nota: Los autores ya deben estar creados y guardados en memoria**. El siguiente código html muestra una lista de los autores disponibles con un check-box para seleccionarlos.

```html 
<div class="modal-header">
    <h3 class="modal-title">Authors</h3>
</div>
<div class="modal-body">
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th id="check-all"><input type="checkbox" ng-model="allChecked" ng-click="checkAll(allChecked)"/></th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <tr ng-repeat="record in records">
                <td id="{{$index}}-selected"><input type="checkbox" ng-model="record.selected"/></td>
                <td>{{record.name}}</td>
            </tr>
        </tbody>
    </table>
</div>
<div class="modal-footer">
    <button class="btn btn-default btn-sm" ng-click="ok()"><span class="glyphicon glyphicon-ok"></span> Save</button>
    <button class="btn btn-default btn-sm" ng-click="cancel()"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
</div>
``` 
**Nota:Tenga en cuenta el nombre de las funciones para cada botón del modal, para este caso son las funciones ok() y cancel() las cuales se deben implementar en el controlador del modal**

- **Modificar el template book.tpl.html:** En el template book.tpl.html usted debe adicionar un **Tab** para Authors, este tab debe desplegar un toolbar de navegación con un botón con el nombre "Select" para abrir el modal. Al oprimir el botón select se invoca la función **showList()** del controlador authorsCtrl retornando los autores en memoria.

```html
<div id="childs" ng-show="ctrl.editMode" class="col-md-6">
    <ul class="nav nav-tabs">
        <li ng-show="currentRecord.id" role="presentation" ng-class="{active: tab === 'authors'}">
            <a href ng-click="ctrl.changeTab('authors')">Authors</a>
        </li>
    </ul>
    <!-- Aqui se necesita el template de la seleccion de los autores al libro -->
    <div ng-show="tab === 'authors' && refId" ng-controller="authorsCtrl as ctrl">
        <div id="authors-header">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#authors-navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand">Authors</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="authors-navbar">
                        <button id="select-authors" class="btn btn-default navbar-btn" ng-click="ctrl.showList()"><span class="glyphicon glyphicon-check"></span> Select</button>
                    </div>
                </div>
            </nav>
        </div>
        <alert ng-repeat="alert in alerts" type="{{alert.type}}" close="ctrl.closeAlert($index)">{{alert.msg}}</alert>
        <div class="col-md-12">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="record in records">
                        <td>{{record.name}}</td>
                        <td><button id="{{$index}}-delete-btn" class="btn btn-default btn-sm" ng-click="ctrl.removeAuthor($index)"><span class="glyphicon glyphicon-minus"></span> Remove</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
```

- **Implementar la lógica de comunicación en el controlador BookCtrl:** Inyecte los servicios de book y editorial, bookService y editorialService respectivamente, tal como se muestra en el siguiente ejemplo:
```javascript
mod.controller("bookCtrl", ["$scope", "bookService", "editorialService", function ($scope, svc, editorialSvc) {
...
}]);
```
 
- **Implementar la lógica de comunicación del template con el controlador authorsCtrl:**  ** Inyecte el servicio **$modal** de ui.bootstrap al controlador *BookCtrl*, del mismo modo inyecte los servicios de author y book, authorService y bookService respectivamente, tal como se muestra en el siguiente ejemplo:
```javascript
 mod.controller("authorsCtrl", ["$scope", "authorService", "$modal", "bookService", function ($scope, svc, $modal, bookSvc) {
...
}]);
```
En el anterior controlador usted debe instanciar el modal definiendo si tiene animación, el template asociado, el controlador de la instancia y los parámetros que se van a pasar una vez se cree la instancia del modal. Para instanciar el modal usted debe hacer uso del método $modal.open(), tal como se muestra en el siguiente ejemplo. **Nota:** Observe que la declaración del modal está dentro de la función this.showList() ésta se llama al presionar el botón **Select** de la barra de Authors.

```javascript
            this.showList = function () {
                var modal = $modal.open({
                    animation: true,
                    templateUrl: "src/modules/book/authorModal.tpl.html",
                    controller: ["$scope", "$modalInstance", "items", "currentItems", function ($scope, $modalInstance, items, currentItems) {
                            $scope.records = items.data;
                            $scope.allChecked = false;

                            function loadSelected(list, selected) {
                                ng.forEach(selected, function (selectedValue) {
                                    ng.forEach(list, function (listValue) {
                                        if (listValue.id === selectedValue.id) {
                                            listValue.selected = true;
                                        }
                                    });
                                });
                            }

                            $scope.checkAll = function (flag) {
                                this.records.forEach(function (item) {
                                    item.selected = flag;
                                });
                            };

                            loadSelected($scope.records, currentItems);

                            function getSelectedItems() {
                                return $scope.records.filter(function (item) {
                                    return !!item.selected;
                                });
                            }

                            $scope.ok = function () {
                                $modalInstance.close(getSelectedItems());
                            };

                            $scope.cancel = function () {
                                $modalInstance.dismiss("cancel");
                            };
                        }],
                    resolve: {
                        items: function () {
                            return svc.fetchRecords();
                        },
                        currentItems: function () {
                            return $scope.records;
                        }
                    }
                });
                modal.result.then(function (data) {
                    bookSvc.replaceAuthors($scope.refId, data).then(function (response) {
                        $scope.records.splice(0, $scope.records.length);
                        $scope.records.push.apply($scope.records, response.data);
                        $scope.$emit("updateAuthors", $scope.records);
                    }, responseError);
                });
            };
```
### Métodos para el manejo de acciones del modal

|Método|Descripción|
|------|-----------|
|**ok()** | Cierra el modal y retorna los items seleccionados |
|**cancel()**| Cierra el modal y cancela las acciones anteriores|
|**getSelectedItems()**|Obtiene los items seleccionados y los guarda en el $scope|
|**$scope.checkAll**| Permite seleccionar todos los items del modal |
|**loadSelected()**|Carga los elementos seleccionados con anterioridad para que aparezcan en el modal|

La propiedad **resolve** establece los elementos a transferir o inyectar a cada instancia del modal, en este caso se envían los parámetros **items** y **currentItems** los cuales contienen la consulta de todos los autores y los autores seleccionados actualmente.
Luego, el método modal.open() retorna un objeto con varias propiedades ([Ver Link](https://angular-ui.github.io/bootstrap/)) entre estas la propiedad result de tipo promesa, mediante la cual se le pasa una función que toma los actuales autores seleccionados y los envía al servicio **bookSvc.replaceAuthors** el cual se encargará de enviar la petición al servicio del backend o en este caso al mock.
 



- Agregar métodos en el servicio BookService para añadir y leer listas de authores. En el archivo book.svc.js agregue los siguientes servicios para obtener autores asociados a un libro, actualizarlos y removerlos de un libro.

```javascript
/**
             * Hace una petición PUT a /books/:id/authors para reemplazar los
             * author asociados a un book
             * @param {number} bookId Identificador de la instancia de book
             * @param {array} authors Colección de authors nueva
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un array de objetos de authors con los nuevos autores
             */
            this.replaceAuthors = function (bookId, authors) {
                return $http.put(context + "/" + bookId + "/authors", authors);
            };

            /**
             * Hace una petición GET a /books/:id/authors para obtener la colección
             * de author asociados a un book
             * @param {number} id Identificador de la instancia de book
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un array de objetos de authors.
             */
            this.getAuthors = function (id) {
                return $http.get(context + "/" + id + "/authors");
            };

            /**
             * Hace una petición DELETE a /books/:id/authors/:id para remover
             * un author de un book
             * @param {number} bookId Identificador de la instancia de book
             * @param {number} authorId Identificador de la instancia de author
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.removeAuthor = function (bookId, authorId) {
                return $http.delete(context + "/" + bookId + "/authors/" + authorId);
            };
```
- Implementar métodos que simulan la respuesta de los anteriores servicios mediante el uso de Mocks. En el archivo book.mocks.js usted debe colocar los siguientes métodos que simulan la respuesta cuando se hace una petición para agregar un author a un libro, leer autores de un libro y removerlos.

```javascript
$httpBackend.whenPUT(recordsAuthor).respond(function (method, url, data) {
                var id = parseInt(url.split('/')[2]);
                $log.debug(url);
                var list;
                var response = ng.fromJson(data);
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        value.authors = response;
                        list = ng.copy(value.authors);
                        records[key].authors = list;
                    }
                });
                return [200, list, {}];
            });

            /*Completar
             */
            $httpBackend.whenGET(recordsAuthor).respond(function (method, url) {
                var id = parseInt(url.split('/')[2]);
                $log.debug(id);
                var responseObj;
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        responseObj = value.authors;
                    }
                });
                return [200, responseObj];
            });


            $httpBackend.whenDELETE(recordsAuthor).respond(function (method, url) {
                var id = parseInt(url.split('/')[2]);
                var idAuthor = parseInt(url.split('/').pop());
                $log.debug(idAuthor);
                var responseObj;
                ng.forEach(records, function (value) {
                    if (value.id === id) {
                        ng.forEach(value.authors, function (valueAuthor, keyAuthor) {
                            if (valueAuthor.id === idAuthor) {
                                value.authors.splice(keyAuthor, 1);
                            }
                        });
                    }
                });
                return [200, responseObj];
            });
```
## Relaciones de composición Uno a Muchos.

Para implementar las relaciones de composición *Uno a Muchos* se dispone a crear un **Tab** adicional en la interfaz del  módulo dueño de la relación, donde se puede realizar todas las operaciones CRUD al módulo hijo. Es importante resaltar que una relación de composición establece una fuerte relación entre el dueño de la relación y sus hijos, por ejemplo un hijo sólo puede ser creado y asociado a un módulo padre, *No puede existir hijos sin tener un padre*, de igual manera *si el módulo padre desaparece todos sus hijos también serán eliminados*. En el ejemplo BookStore existe una relación de composición entre Book y Reviews lo que significa que "Un libro tiene muchos reviews " y cada review sólo puede ser creado a un libro. Por lo tanto si se elimina el libro desaparece junto con él todos sus reviews. **Nota: El paso 2 tenía el módulo review independiente al módulo book, en este paso el link de Review desaparece y sólo se puede crear un review cuando se edita un libro.**


### Implementación de la relación composite Uno a Muchos entre book y reviews

- Modificar el template book.tpl.html
En el template book.tpl.html usted debe adicionar un **Tab** para Reviews, este tab debe desplegar un toolbar de navegación con el botón "Create". Al oprimir el botón create debe aparecer un formulario con todos los campos de Book.

```html
<div id="childs" ng-show="ctrl.editMode" class="col-md-6">
    <ul class="nav nav-tabs">
      ...
        <li ng-show="currentRecord.id" role="presentation" ng-class="{active: tab === 'reviews'}">
            <a href ng-click="ctrl.changeTab('reviews')">Reviews</a>
        </li>
    </ul>
    <!-- Aqui incluye el template del modulo de reviews e indica el controlador correspondiente reviewCtrl-->
    <div ng-show="tab === 'reviews'">
        <div ng-controller="reviewsCtrl as ctrl" ng-include="'src/modules/review/review.tpl.html'"></div>
    </div>
      ...
</div>
```

Como se observa en el anterior ejemplo, mediante el uso del comando **ng-include** se reutiliza el template ubicado en _src/modules/review/review.tpl.html_ el cual se desplegará cada vez que el usuario realice un click en la pestaña **reviews**.

- Implementar el controlador de **Reviews** acorde a la relación de composición.
En el archivo book.ctrl.js usted debe crear el controlador _reviewsCtrl_ el cual es el encargado de tener la lógica del despliegue de alertas y métodos que invocan a servicios CRUD de reviews.


```javascript
mod.controller("reviewsCtrl", ["$scope", "bookService", function ($scope, bookSvc) {
            $scope.currentRecord = {};
            $scope.records = [];
            $scope.refName = "reviews";
            $scope.alerts = [];

            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            var self = this;
            function responseError(response) {
                self.showError(response.data);
            }

            //Variables para el controlador
            this.readOnly = false;
            this.editMode = false;

            //Escucha de evento cuando se selecciona un registro maestro
            function onCreateOrEdit(event, args) {
                var childName = "reviews";
                if (args[ childName ] === undefined) {
                    args[ childName ] = [];
                }
                $scope.records = [];
                $scope.refId = args.id;
                bookSvc.getReviews(args.id).then(function (response) {
                    $scope.records = response.data;
                }, responseError);
            }

            $scope.$on("post-create", onCreateOrEdit);
            $scope.$on("post-edit", onCreateOrEdit);



            this.createRecord = function () {
                this.editMode = true;
                $scope.currentRecord = {};
            };

            var self = this;
            this.saveRecord = function () {
                bookSvc.saveReview($scope.refId, $scope.currentRecord).then(function (response) {
                    $scope.records.push(response.data);
                    self.fetchRecords();
                    $scope.$emit("updateReview", $scope.records);
                }, responseError);
            };

            this.fetchRecords = function () {
                return bookSvc.getReviews($scope.refId).then(function (response) {
                    $scope.records = response.data;
                    self.editMode = false;
                }, responseError);
            };

            this.editRecord = function (record) {
                return bookSvc.getReview($scope.refId, record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    self.editMode = true;
                    return response;
                }, responseError);
            };

            this.deleteRecord = function (record) {
                bookSvc.removeReview($scope.refId, record.id).then(function () {
                    $scope.records.splice(record, 1);
                    self.fetchRecords();
                }, responseError);
            };
        }]);

```
Este controlador escucha los eventos del **$scope** padre para conocer cuando se realiza la edición de un libro o su creación. El evento captura el id del libro y trae todos los _reviews_ asociados a este libro.

- Agregar métodos en el servicio _BookService_ para crear, editar, leer y remover listas de reviews.
En este paso se procede a crear todos los métodos que realizan el llamado a los servicios REST para obtener reviews de un libro mediante el comando de angular $http. Los métodos permiten crear, leer, actualizar y eliminar reviews asociados a un libro.

```javascript
            //Este método consulta todos los reviews asociados a un libro. Necesita el id del libro
            this.getReviews = function (idBook) {
                $log.debug("GET" + context + "/" + idBook + "/reviews");
                return $http.get(context + "/" + idBook + "/reviews");
            };
            //Este método permite obtener un review por id. 
            this.getReview = function (idBook, idReview) {
                $log.debug("GET" + context + "/" + idBook + "/reviews/" + idReview);
                return $http.get(context + "/" + idBook + "/reviews/" + idReview);
            };
            //Este método permite crear o actualizar un review. 
            this.saveReview = function (idBook, currentRecord) {
                if (currentRecord.id) {
                    $log.debug("PUT" + context + "/" + idBook + "/reviews/" + currentRecord.id);
                    return $http.put(context + "/" + idBook + "/reviews/" + currentRecord.id, currentRecord);
                } else {
                    $log.debug("POST" + context + "/" + idBook + "/reviews/");
                    return $http.post(context + "/" + idBook + "/reviews", currentRecord);
                }
            };
            //El método permite remover reviews que están asociados a un libro
            this.removeReview = function (idBook, idReview) {
                $log.debug("Llamo a post");
                return $http.delete(context + "/" + idBook + "/reviews/" + idReview);
            };

        }]);
```

- Implementar métodos que simulan la respuesta de los anteriores servicios mediante el uso de Mocks: En el archivo book.mocks.js usted debe colocar los siguientes métodos que simulan la respuesta cuando se hace una petición para crear, leer, actualizar y remover un review a de un libro.

## Manejo de eventos con AngularJS






## FAQ