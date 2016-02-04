(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/evento");
            $stateProvider
                    .state('evento', {
                        url: '/evento',
                        templateUrl: "src/modules/book/book.tpl.html"
                    })

                    .state('recuerdos', {
                        url: '/recuerdo',
                        templateUrl: "src/recuerdo/recuerdo.tpl.html"
                    });
        }]);
})(window.angular);