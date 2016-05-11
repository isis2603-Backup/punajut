/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("itinerarioModule");

    mod.service("itinerarioService", ["$http", "itinerarioContext", "$log", function ($http, context, $log)
    {

            this.getRecords = function (viajeroId) {
                return $http.get(context + "/" + viajeroId + "/itinerarios");
            };

            this.getRecord = function (viajeroId, itinerarioId) {
                return $http.get(context + "/" + viajeroId + "/itinerarios/" + itinerarioId);
            };

            this.createRecord = function (viajeroId, itinerario) {
                return $http.post(context + "/" + viajeroId + "/itinerarios", itinerario);
            };

            this.updateRecord = function (viajeroId, itinerarioId, itinerario) {
                return $http.put(context + "/" + viajeroId + "/itinerarios/" + itinerarioId, itinerario);
            };

            this.deleteRecord = function (viajeroId, itinerarioId) {
                return $http.delete(context + "/" + viajeroId + "/itinerarios/" + itinerarioId);
            };

            this.saveRecord = function(viajeroId, itinerario){
                if(itinerario.id){
                    return this.updateRecord(viajeroId, itinerario.id, itinerario);
                }else{
                    return this.createRecord(viajeroId, itinerario);
                }
            };

        }]);

})(window.angular);



