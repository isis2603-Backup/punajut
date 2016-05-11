(function(ng){

  var mod = ng.module("eventoModule", ["ui.bootstrap", "ngMessages"]);
                console.log("mod evento");


  mod.constant("eventoContext", "api/eventos");

})(window.angular);


