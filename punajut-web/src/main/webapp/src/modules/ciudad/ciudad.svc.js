/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("ciudadModule");

    mod.service("ciudadService", ["$http", "ciudadContext", function ($http, context) {

            /**
             * Obtener la lista de ciudades.
             * Hace una petición GET con $http a /ciudades para obtener la lista
             * de objetos de la entidad ciudades
             * @returns {promise} promise para leer la respuesta del servidor.
             * Se recibe un array de objetos de ciudad.
             */
            this.fetchRecords = function () {
                return $http.get(context);
            };

            /**
             * Obtener un registro de ciudades.
             * Hace una petición GET a /ciudades/:id para obtener
             * el objeto de un registro específico de ciudades
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor.
             * Se recibe un objeto instancia de ciudades.
             */
            this.fetchRecord = function (id) {
                return $http.get(context + "/" + id);
            };

            /**
             * Guardar un registro de ciudades.
             * Si currentRecord tiene la propiedad id, hace un PUT a /ciudades/:id con los
             * nuevos datos de la instancia de ciudades.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /ciudades
             * para crear el nuevo registro de ciudades
             * @param {object} currentRecord instancia de ciudades a guardar/actualizar
             * @returns {promise} promise para leer la respuesta del servidor.
             * Se recibe un objeto de ciudades con su nuevo id
             */
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    return $http.put(context + "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context, currentRecord);
                }
            };

            /**
             * Hace una petición DELETE a /ciudades/:id para eliminar una ciudad
             * @param {number} id identificador de la instancia de ciudad a eliminar
             * @returns {promise} promise para leer la respuesta del servidor.
             * No se recibe cuerpo en la respuesta.
             */
            this.deleteRecord = function (id) {
                return $http.delete(context + "/" + id);
            };

            /**
             * Hace una petición PUT a /ciudades/:id/eventos
             * @param {number} ciuadId Identificador de la instancia de ciudad
             * @param {array} eventos Colección de ciudades nueva
             * @returns {promise} promise para leer la respuesta del servidor.
             * Devuelve el objeto de ciudades con sus nuevos datos.
             */
            this.replaceEventos = function (ciudadId, eventos) {
                return $http.put(context + "/" + ciudadId + "/eventos", eventos);
            };

            /**
             * Hace una petición GET a /ciudades/:id/eventos
             * @param {number} id Identificador de la instancia de ciudad
             * @returns {promise} promise para leer la respuesta del servidor.
             * Retorna un array de objetos de eventos.
             */
            this.getEventos = function (id) {
                return $http.get(context + "/" + id + "/eventos");
            };

            /**
             * Hace una petición DELETE a /ciudades/:id/eventos/:id
             * @param {number} ciudadId Identificador de la instancia de ciudad
             * @param {number} eventoId Identificador de la instancia de evento
             * @returns {promise} promise para leer la respuesta del servidor
             * La respuesta no devuelve datos.
             */
            this.removeEvento = function (ciudadId, eventoId) {
                return $http.delete(context + "/" + ciudadId + "/eventos/" + eventoId);
            };
        }]);
})(window.angular);


