/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller("itinerariosCtrl", function($scope) {
    $scope.apuntes = "";
    $scope.guardarApuntes  = function() {alert("Vas a guardar tu nota");};
    $scope.eliminarApuntes = function() {$scope.apuntes = "";};
});
