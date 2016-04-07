

(function (ng) {

  // es parte del módulo "eventoViajeroModule"
  var mod = ng.module("eventoViajeroModule");

  // crea el controlador con dependencias a $scope y a eventoViajeroService
  mod.controller("eventoViajeroCtrl", ["$scope", "eventoViajeroService", function ($scope, svc) {

   $scope.records = [];


    // TODO: define funciones que son invocadas desde la pantalla
    // y que usan funciones definidas en el servicio
  $scope.agregar = function ()
  {
      var eventoViajero = {date1: $scope.fechaInicial, date2: $scope.fechaFinal, name: $scope.nombre, city: $scope.ciudad, address: $scope.direccion, description:$scope.descripcion};
      $scope.records.push(eventoViajero);
      console.log("llegaaaaaaaaaaaaaaaaaaaaaaaaa");
      $scope.date1='';
      $scope.date2='';
      $scope.name='';
      $scope.city='';
      $scope.address='';
      $scope.description='';
 //     svc.saveRecord(eventoViajero);
 //     this.fetchRecords();
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
