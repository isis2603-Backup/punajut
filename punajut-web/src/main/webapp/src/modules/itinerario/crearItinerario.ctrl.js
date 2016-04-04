(function (ng) {

  var mod = ng.module("crearItinerarioModule");

  // crea el controlador con dependencias a $scope y a personService
  mod.controller("crearItinerarioCtrl", ["$scope", "crearItinerarioService", function ($scope, svc) {

            $scope.itinerarios = [];
            $scope.currentRecord = {
                id: 0, /*Tipo long*/
                name:'',
                description:'', /*Tipo String*/
                fechaInicio : '', /*Tipo fecha*/
                fechaFin : '' /*Tipo fecha*/

            };

            this.readOnly = false;
            this.editMode = false;
            var self = this;

            this.createRecord = function () {
                $scope.$broadcast("pre-create", $scope.currentRecord);
                this.editMode = true;
                $scope.currentRecord = {};
                $scope.$broadcast("post-create", $scope.currentRecord);
            };
            this.saveRecord = function () {
                return svc.saveRecord($scope.currentRecord).then(function () {
                    self.fetchRecords();


                });
            };
            this.fetchRecords = function () {
                return svc.fetchRecords().then(function (response) {
                    $scope.itinerarios = response.data;
                    $scope.currentRecord = {};
                    self.editMode = false;
                    return response;
                });
            };

            this.fetchRecords();
      }]);

})(window.angular);
