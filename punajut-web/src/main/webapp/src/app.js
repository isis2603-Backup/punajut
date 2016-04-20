(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "usuarioModule",
        "itinerarioModule",
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

                    .state('itinerario', {
                        url: '/itinerario',
                        controller: "itinerarioCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/itinerario/itinerario.tpl.html"

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
                    });
        }]);
})(window.angular);
