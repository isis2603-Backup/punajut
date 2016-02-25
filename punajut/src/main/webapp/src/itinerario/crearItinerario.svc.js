/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("itinerarioModule");

    mod.service("crearItinerarioService", ["$http", "crearItinerarioContext", function ($http, context)
    {
         this.fetchRecords = function () {
            return $http.get(context);
        };
         this.fetchRecord = function (id) {
            return $http.get(context + "/" + id);
        };
        this.saveRecord = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };
        this.deleteRecord = function (id) {
            return $http.delete(context + "/" + id);
        }; 







    }]);
})(window.angular);


