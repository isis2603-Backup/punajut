(function (ng) {
    var mod = ng.module("ciudadModule", ["ui.bootstrap", "ngMessages"]);
              console.log("mod ciudad");

    mod.constant("ciudadContext", "api/ciudades");
})(window.angular);
