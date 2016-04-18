/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Code goes here
(function (ng) {

  var mod = ng.module("itinerarioModule", []);
  // crea el controlador con dependencias a $scope y a itinerarioService
  mod.controller("itinerarioCtrl", ["$scope", "itinerarioService", function ($scope, svc) {

            $scope.ciudades = [];
            $scope.currentRecord = {
                id: 0, /*Tipo long*/
                name:''
            };

            this.readOnly = false;
            this.editMode = false;
            var self = this;
            
             this.changeTab = function (tab) {
                $scope.tab = tab;
            };

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
                    $scope.ciudades = response.data;
                    $scope.currentRecord = {};
                    self.editMode = false;
                    return response;
                });
            };
            
            this.deleteRecord = function (record) {
                return svc.deleteRecord(record.id).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            this.fetchRecords();
      }]);

})(window.angular);




