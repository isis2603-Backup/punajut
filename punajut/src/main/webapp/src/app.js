(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    // mod.controller('MainController', ['$scope', function($scope) { $scope.title = 'Top Sellers in Books'; }]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
          //$urlRouterProvider.otherwise("/paginaDeInicio");
            $stateProvider

                    .state('paginaDeInicio', {
                        url: '/paginaDeInicio',
                        templateUrl: "src/Pagina de inicio/paginaDeInicio.tpl.html"
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
                    .state('PerfilInicio', {
                        url: '/PerfilInicio',
                        templateUrl: "src/Pagina de inicio/PerfilInicio.tpl.html"

                    })


                    .state('configurarCuenta', {
                        url: '/configurarCuenta',
                        templateUrl: "src/Pagina de inicio/configurarCuenta.tpl.html"

                    })

//                    .state('seleccionaFecha', {
//                        url: '/seleccionaFecha',
//                        views: {
//                            'PerfilInicio': {
//                                url: "",
//                                controller: 'perfil',
//                                templateUrl: 'src/Pagina de inicio/PerfilInicio.tpl.html'
//                            },
//                            'crearItinerario': {
//                                url: "",
//                                controller: 'loQueEstaAlLado',
//                                templateUrl: 'src/itinerario/crearItinerario.tpl.html'
//                            }
//                        }
//
//                    })
                    .state('verEuropa', {
                        url: '/verEuropa',
                        templateUrl: "src/itinerario/verEuropa.tpl.html"

                    })
                    .state('verContinentes', {
                        url: '/verContinentes',
                        templateUrl: "src/itinerario/verContinentes.tpl.html"
                    })
                    .state('foro', {
                        url: '/foro',
                        controller: 'foroCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: "src/modules/comunidad/foroHttpTest.tpl.html"

                    })
                    .state('crearItinerario', {
                        url: '/crearItinerario',
                        controller: "crearItinerarioCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/itinerario/crearItinerario.tpl.html"

                    });

        }]);

})(window.angular);
