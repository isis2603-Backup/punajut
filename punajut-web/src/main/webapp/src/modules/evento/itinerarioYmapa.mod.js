/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// src/itinerario/itineraioYmapa.mod.js
// Definición del modulo de personas

(function(ng){

  // define el módulo "itineraioModule" con dependencia a ui.bootstrap
  var mod = ng.module("itineraioYmapaModule", ["ui.bootstrap"]);

  // define una constante usada por el servicio y el mock del servicio
  mod.constant("itineraioYmapaContext", "api/itineraioYmapa");

})(window.angular);
