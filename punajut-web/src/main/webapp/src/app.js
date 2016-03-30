(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "usuarioModule",
        "usuarioMock",
        "crearItinerarioModule",
        "foroModule",
        "foroMock",
        "itinerarioYmapaModule",
        "comunidadModule",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/PaginaDeInicio");
            $stateProvider
                    .state('PaginaDeInicio', {
                        url: '/PaginaDeInicio',
                        templateUrl: "src/usuario/PaginaDeInicio.tpl.html"
                    })
                    .state('usuario', {
                        url: '/usuario',
                        controller: "usuarioCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/usuario/usuario.tpl.html"
                    })
                    .state('CrearIt', {
                        url: '/CrearIt',
                        views:{
                            'principal': {
                                controller: "usuarioCtrl",
                                controllerAs: "ctrl",
                                templateUrl: "src/usuario/usuario.tpl.html"
                            },
                            'centro': {
                                templateUrl: "src/itinerario/CrearItinerario.tpl.html"
                            }
                        }
                    })
                    .state('ElegirFechasViaje', {
                        url: '/ElegirFechasViaje',
                        views:{
                            'principal': {
                                controller: "usuarioCtrl",
                                controllerAs: "ctrl",
                                templateUrl: "src/usuario/usuario.tpl.html"
                            },
                            'centro': {
                                templateUrl: "src/itinerarios/verContinentes.tpl.html"
                            }
                        }
                    })

                    .state('itinerarios', {
                        url: '/itinerarios',
                        templateUrl: "src/itinerario/itinerarios.tpl.html"

                    })
                    .state('itinerarioYmapa', {
                        url: '/itinerarioYmapa',
                        templateUrl: "src/itinerario/itinerarioYmapa.tpl.html"

                    })

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

                    });
        }]);
})(window.angular);
