(function (ng) {
    var mod = ng.module("comunidadModule");

    mod.controller("comunidadCtrl", ["$scope", "comunidadServuce", function ($scope, svc) {
            $scope.apuntes = "";
            $scope.guardarApuntes = function () {
                alert("Vas a guardar tu nota");
            };
            $scope.eliminarApuntes = function () {
                $scope.apuntes = "";
            };
        }
    ]);

})(window.angular);

