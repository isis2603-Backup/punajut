// src/modules/task/task.svc.js
// Servicio para el Modulo de Tareas

(function (ng) {

    var mod = ng.module("eventoViajeroModule");

    mod.service("eventoViajeroService", ["$http", "eventoViajeroContext", function ($http, context) {
            /**
             * Obtener la lista de task.
             * Hace una petición GET con $http a /tasks para obtener la lista
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de task con sus atributos
             */
            this.fetchRecords = function () {
                return $http.get(context);
            };

            /**
             * Obtener un registro de tasks.
             * Hace una petición GET a /tasks/:id para obtener
             * los datos de un registro específico de tasks
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de task con sus atributos
             */
            this.fetchRecord = function (id) {
                return $http.get(context + "/" + id);
            };

            /**
             * Guardar un registro de task.
             * Si currentRecord tiene la propiedad id, hace un PUT a /task/:id con los
             * nuevos datos de la instancia de task.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /task
             * para crear el nuevo registro de task
             * @param {object} currentRecord instancia de task a guardar/actualizar
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de task con sus datos incluyendo el id
             */
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    return $http.put(context + "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context, currentRecord);
                }
            };

            /**
             * Hace una petición DELETE a /task/:id para eliminar una task
             * @param {number} id identificador de la instancia de task a eliminar
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.deleteRecord = function (id) {
                return $http.delete(context + "/" + id);
            };
        }]);
})(window.angular);