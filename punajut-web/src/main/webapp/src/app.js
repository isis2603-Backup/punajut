(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "usuarioModule",
        "usuarioMock",
        "crearItinerarioModule",
        "foroModule",
        "foroMock",
        "itinerarioYmapaModule",
        "ciudadModule",
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
                        templateUrl: "src/modules/usuario/PaginaDeInicio.tpl.html"
                    })
                    .state('usuario', {
                        url: '/usuario',
                        controller: "usuarioCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/usuario/usuario.tpl.html"
                    })
                    .state('CrearIt', {
                        url: '/CrearIt',
                        views:{
                            'principal': {
                                controller: "usuarioCtrl",
                                controllerAs: "ctrl",
                                templateUrl: "src/modules/usuario/usuario.tpl.html"
                            },
                            'centro': {
                                templateUrl: "src/modules/itinerario/CrearItinerario.tpl.html"
                            }
                        }
                    })
                    .state('ElegirFechasViaje', {
                        url: '/ElegirFechasViaje',
                        views:{
                            'principal': {
                                controller: "usuarioCtrl",
                                controllerAs: "ctrl",
                                templateUrl: "src/modules/usuario/usuario.tpl.html"
                            },
                            'centro': {
                                templateUrl: "src/modules/itinerarios/verContinentes.tpl.html"
                            }
                        }
                    })

                    .state('itinerarios', {
                        url: '/itinerarios',
                        templateUrl: "src/modules/itinerario/itinerarios.tpl.html"

                    })
                    .state('itinerarioYmapa', {
                        url: '/itinerarioYmapa',
                        templateUrl: "src/modules/itinerario/itinerarioYmapa.tpl.html"

                    })

                    .state('verEuropa', {
                        url: '/verEuropa',
                        templateUrl: "src/modules/itinerario/verEuropa.tpl.html"

                    })
                    .state('verContinentes', {
                        url: '/verContinentes',
                        templateUrl: "src/modules/itinerario/verContinentes.tpl.html"
                    })
                    .state('foro', {
                        url: '/foro',
                        controller: 'foroCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: "src/modules/foro/foroHttpTest.tpl.html"
                    })
                    .state('ciudad', {
                        url: '/ciudad',
                        controller: "ciudadCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/ciudad/ciudad.tpl.html"
                    });
        }]);
})(window.angular);
