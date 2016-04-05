// src/modules/eventoViajero/eventoViajero.mod.js
// Definición del modulo de evento viajero

(function(ng){

  // define el módulo "taskModule" con dependencia a ui.bootstrap
  var mod = ng.module("eventoViajeroModule", ["ui.bootstrap"]);

  // define una constante usada por el servicio y el mock del servicio
  mod.constant("eventoViajeroContext", "api/eventoViajero");

})(window.angular);