(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "usuarioModule",
        "itinerarioModule",
        "ciudadModule",
        "eventoModule",
        //"eventoViajeroModule",
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

                    .state('ciudad', {
                        url: '/ciudad',
                        controller: "ciudadCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/ciudad/ciudad.tpl.html"
                    })

                    .state('visitaCiudad', {
                        url: '/visitaCiudad',
                        controller: "visitaCiudadCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/visitaCiudad/visitaCiudadCrear.tpl.html"
                    })

                    .state('evento', {
                        url: '/evento',
                        controller: "eventoCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/evento/evento.tpl.html"
                    })

                    .state('eventoViajero', {
                        url: '/eventoViajero',
                        controller: "eventoViajeroCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/eventoViajero/eventoViajero.tpl.html"
                    });
        }]);
})(window.angular);
