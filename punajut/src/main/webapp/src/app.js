(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

   // mod.controller('MainController', ['$scope', function($scope) { $scope.title = 'Top Sellers in Books'; }]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/paginaInicio");
            $stateProvider

                    .state('paginaDeInicio', {
                        url: '/paginaDeInicio',
                        templateUrl: "src/Pagina de inicio/paginaDeInicio.tpl.html"
                    })
                    .state('Sign up', {
                        url: '/Sing up',
                        templateUrl: "src/Pagina de inicio/SignUp.tpl.html"
                    })
                     .state('Login', {
                        url: '/Login',
                        templateUrl: "src/Pagina de inicio/Login.tpl.html"
                    })

                    .state('timeline', {
                        url: '/timeline',
                        templateUrl: "src/timeline/timeline.tpl.html"

                    })
                    .state('itinerarios', {
                        url: '/itinerarios',
                        templateUrl: "src/itinerario/itinerarios.tpl.html"

                    })
                    .state('itinerarioYmapa', {
                        url: '/itinerarioYmapa',
                        templateUrl: "src/itinerario/itinerarioYmapa.tpl.html"

                    })
                    .state('verContinentes', {
                        url: '/verContinentes',
                        templateUrl: "src/itinerario/verContinentes.tpl.html"
            });
        }]);

})(window.angular);
