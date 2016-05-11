// src/modules/task/task.mod.js
// Definición del modulo de tareas

(function(ng){

  // define el módulo "taskModule" con dependencia a ui.bootstrap
  var mod = ng.module("eventoViajeroModule", ["ui.bootstrap"]);

  // define una constante usada por el servicio y el mock del servicio
  mod.constant("Context", "api/viajeros");

})(window.angular);