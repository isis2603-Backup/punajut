// src/modules/task/task.ctrl.js
// Controlador para el módulo de tareas

(function (ng) {

  // es parte del módulo "taskModule"
  var mod = ng.module("eventoViajeroModule");
  console.log("llega ctrl eventoViajero");

  // crea el controlador con dependencias a $scope y a taskService
  mod.controller("eventoViajeroCtrl", ["$scope", "eventoViajeroService", function ($scope, svc) {
            console.log("llega ctrl eventoViajero2");


    // TODO: define los atributos en el scope
    $scope.currentRecord = {};
  $scope.records = [];


    // TODO: define funciones que son invocadas desde la pantalla
    // y que usan funciones definidas en el servicio
  $scope.agregar = function ()
  {
      var tarea = {name: $scope.currentRecord.nombre,
          city: $scope.currentRecord.ciudad,
          address: $scope.currentRecord.direccion,
          description:$scope.currentRecord.descripcion,
          date1:$scope.currentRecord.fechaInicial,
          date2:$scope.currentRecord.fechaFinal};
    //  $scope.records.push(tarea);
//      $scope.id='';
//      $scope.nombre='';
//      $scope.descripcion='';
//      $scope.fecha='';
      svc.saveRecord(tarea);
      this.fetchRecords();
  };



          /*
             * Funcion fetchRecords consulta el servicio svc.fetchRecords con el fin de consultar
             * todos los registros del modulo book.
             * Guarda los registros en la variable $scope.records
             * Muestra el template de la lista de records.
             */

            this.fetchRecords = function () {
                return svc.fetchRecords().then(function (response) {
                    $scope.records = response.data;
                    $scope.currentRecord = {};
                    return response;
                });
            };


            //Variables para el controlador
            this.readOnly = false;
            this.editMode = false;

            /*
             * Funcion fetchRecords consulta todos los registros del módulo book en base de datos
             * para desplegarlo en el template de la lista.
             */
            this.fetchRecords();

  }]);

})(window.angular);