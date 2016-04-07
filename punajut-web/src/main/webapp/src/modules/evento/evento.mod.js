/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){

  // define el módulo "taskModule" con dependencia a ui.bootstrap
  var mod = ng.module("eventoModule", ["ui.bootstrap"]);

  // define una constante usada por el servicio y el mock del servicio
  mod.constant("eventoContext", "api/evento");

})(window.angular);


