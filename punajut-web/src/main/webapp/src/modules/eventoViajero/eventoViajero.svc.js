// src/modules/task/task.svc.js
// Servicio para el Modulo de Tareas

(function (ng) {

    var mod = ng.module("eventoViajeroModule");

    mod.service("eventoViajeroService", ["$http", "Context", function ($http, context) {
            /**
             * Obtener la lista de eventos viajero.
             * Hace una petición GET con $http a /viajeros/:idViajero/itinerarios/:idItinerario/visitas/:idVisitaCiudad/eventosViajero para obtener la lista
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de eventos viajero con sus atributos
             */
            this.getEventosViajero = function (viajeroId, itinerarioId, visitaCiudadId)
            {
                return $http.get(context + "/" + viajeroId + "/itinerarios/" + itinerarioId + "/visitas/" + visitaCiudadId + "/eventosViajero");
            };

            /**
             * Obtener un registro de eventos viajero.
             * Hace una petición GET a /viajeros/:idViajero/itinerarios/:idItinerario/visitas/:idVisitaCiudad/eventosViajero/:id para obtener
             * los datos de un registro específico de evento viajero
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de evento viajero con sus atributos
             */
            this.getEventoViajero = function (viajeroId, itinerarioId, visitaCiudadId, eventoViajeroId)
            {
                return $http.get(context + "/" + viajeroId + "/itinerarios/" + itinerarioId + "/visitas/" + visitaCiudadId + "/eventosViajero/" + eventoViajeroId);
            };

            /**
             * Guardar un registro de eventos viajero.
             * Si currentRecord tiene la propiedad id, hace un PUT a /viajeros/:idViajero/itinerarios/:idItinerario/visitas/:idVisitaCiudad/eventosViajero/:id con los
             * nuevos datos de la instancia de un evento viajero.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /viajeros/:idViajero/itinerarios/:idItinerario/visitas/:idVisitaCiudad/eventosViajero
             * para crear el nuevo registro deun evento viajero
             * @param {object} currentRecord instancia de un evento viajero a guardar/actualizar
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de evento viajero con sus datos incluyendo el id
             */
            this.saveEventoViajero = function (viajeroId, itinerarioId, visitaCiudadId, currentRecord)
            {
                if (currentRecord.id)
                {
                    return $http.put(context + "/" + viajeroId + "/itinerarios/" + itinerarioId + "/visitas/" + visitaCiudadId + "/eventosViajero/" + currentRecord.id, currentRecord);
                }

                else
                {
                    return $http.post(context + "/" + viajeroId + "/itinerarios/" + itinerarioId + "/visitas/" + visitaCiudadId + "/eventosViajero", currentRecord);
                }
            };

            /**
             * Hace una petición DELETE a /viajeros/:idViajero/itinerarios/:idItinerario/visitas/:idVisitaCiudad/eventosViajero/:id para eliminar un evento viajero
             * @param {number} id identificador de la instancia del evento viajero a eliminar
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.deleteEventoViajero = function (viajeroId, itinerarioId, visitaCiudadId, eventoViajeroId)
            {
                    return $http.delete(context + "/" + viajeroId + "/itinerarios/" + itinerarioId + "/visitas/" + visitaCiudadId + "/eventosViajero/" + eventoViajeroId);
            };
        }]);
})(window.angular);