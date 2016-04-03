(function (ng) {
    var mod = ng.module("ciudadModule", ["ui.bootstrap", "ngMessages"]);
    mod.constant("ciudadContext", "api/ciudades");
})(window.angular);
