(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

   // mod.controller('MainController', ['$scope', function($scope) { $scope.title = 'Top Sellers in Books'; }]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/itinerario");
            $stateProvider
                    .state('recuerdos', {
                        url: '/recuerdo',
                        templateUrl: "src/recuerdo/recuerdo.tpl.html"
                    })
                    .state('verContinentes', {
                        url: '/verContinentes',
                        templateUrl: "src/itinerario/verContinentes.tpl.html"
                    });
        }]);
})(window.angular);