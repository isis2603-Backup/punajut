(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "usuarioModule",
        "usuarioMock",
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
                                templateUrl: "src/Itinerario/CrearItinerario.tpl.html"
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
                                templateUrl: "src/Itinerarios/cerContinentes.tpl.html"
                            }
                        }
                    });
        }]);
})(window.angular);
