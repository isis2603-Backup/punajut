
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
          console.log("mod itinerario");

    var mod = ng.module("itinerarioModule", ["ui.bootstrap", "ngMessages"]);
    mod.constant("itinerarioContext", "api/viajeros");

})(window.angular);


