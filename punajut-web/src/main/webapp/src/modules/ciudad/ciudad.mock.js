/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module('ciudadMock', ['ngMockE2E']);


    mod.run(['$httpBackend', '$log', function ($httpBackend, $log) {
            var ignore_regexp = new RegExp('^((?!api).)*$');
            /*
             * @type RegExp
             * recordUrl acepta cualquier url con el formato
             * api/(cualquierpalabra)/(numero)
             * ej: api/ciudades/1
             */
            var recordUrl = new RegExp('api/ciudades/([0-9]+)$');
            var recordsBook = new RegExp('api/ciudades/([0-9]+)/eventos');
            /*
             * @type Array
             * records: Array con una ciudad por defecto
             */
            var records = [{
                    id: 1,
                    nombre: 'Londres',
                    descripcion: 'Capital de Inglaterra',
                    clima: 'Frio',
                    longitud: 1,
                    latitud: 2,
                }];

            function getQueryParams(url) {
                var vars = {}, hash;
                var hashes = url.slice(url.indexOf('?') + 1).split('&');
                for (var i = 0; i < hashes.length; i++) {
                    hash = hashes[i].split('=');
                    vars[hash[0]] = hash[1];
                }
                return vars;
            }

            /*
             * Ignora las peticiones GET, no contempladas en la Exp regular ignore_regexp
             */
            $httpBackend.whenGET(ignore_regexp).passThrough();

            /*
             * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades"
             * Obtiene los parámetros de consulta "queryParams" para establecer
             * la pagina y la maxima cantida de records. Con los anteriores parametros
             * se realiza la simulacion de la paginacion.
             * Response: 200 -> Status ok, array de eventos y los headers.
             */
            $httpBackend.whenGET('api/ciudades').respond(function (method, url) {
                var queryParams = getQueryParams(url);
                var responseObj = [];
                var page = queryParams.page;
                var maxRecords = queryParams.maxRecords;
                var headers = {};
                if (page && maxRecords) {
                    var start_index = (page - 1) * maxRecords;
                    var end_index = start_index + maxRecords;
                    responseObj = records.slice(start_index, end_index);
                    headers = {"X-Total-Count": records.length};
                } else {
                    responseObj = records;
                }
                return [200, responseObj, headers];
            });

            /*
             * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/[numero]"
             * Obtiene el id de la url y el registro asociado dentro del array records.
             * Response: 200 -> Status ok, record -> evento y ningún header.
             */
            $httpBackend.whenGET(recordUrl).respond(function (method, url) {
                var id = parseInt(url.split('/').pop());
                var record;
                ng.forEach(records, function (value) {
                    if (value.id === id) {
                        record = ng.copy(value);
                    }
                });
                return [200, record, {}];
            });

            /*
             * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/ciudades"
             * Obtiene el record de evento desde el cuerpo de la peticion
             * Genera un id aleatorio y lo asocia al record de evento y lo guarda en el
             * array de records.
             * Response: 201 -> Status created, record -> evento y ningún header.
             */
            $httpBackend.whenPOST('api/ciudades').respond(function (method, url, data) {
                var record = ng.fromJson(data);
                record.id = Math.floor(Math.random() * 10000);
                records.push(record);
                return [201, record, {}];
            });

            /*
             * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/ciudades/[numero]"
             * Obtiene el id del la url y el registro asociado dentro del array records.
             * Luego realiza un splice "eliminar registro del array".
             * Response: 204, no retorna ningun dato ni headers.
             */
            $httpBackend.whenDELETE(recordUrl).respond(function (method, url) {
                var id = parseInt(url.split('/').pop());
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        records.splice(key, 1);
                    }
                });
                return [204, null, {}];
            });

            /*
             * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/ciudades/[numero]"
             * Obtiene el id del la url y el record de evento desde el cuerpo de la peticion
             * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
             * Response: 204, no retorna ningun dato ni headers.
             *
             */
            $httpBackend.whenPUT(recordUrl).respond(function (method, url, data) {
                var id = parseInt(url.split('/').pop());
                var record = ng.fromJson(data);
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        records.splice(key, 1, record);
                    }
                });
                return [204, null, {}];
            });

            $httpBackend.whenPUT(recordsEvento).respond(function (method, url, data) {
                var id = parseInt(url.split('/')[2]);
                $log.debug(url);
                var list;
                var response = ng.fromJson(data);
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        value.eventos = response;
                        list = ng.copy(value.eventos);
                        records[key].eventos = list;
                    }
                });
                return [200, list, {}];
            });

            $httpBackend.whenGET(recordsEvento).respond(function (method, url) {
                var id = parseInt(url.split('/')[2]);
                $log.debug(id);
                var responseObj;
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        responseObj = value.eventos;
                    }
                });
                return [200, responseObj];
            });


            $httpBackend.whenDELETE(recordsEvento).respond(function (method, url) {
                var id = parseInt(url.split('/')[2]);
                var idEvento = parseInt(url.split('/').pop());
                $log.debug(idEvento);
                var responseObj;
                ng.forEach(records, function (value) {
                    if (value.id === id) {
                        ng.forEach(value.eventos, function (valueEvento, keyEvento) {
                            if (valueEvento.id === idEvento) {
                                value.eventos.splice(keyEvento, 1);
                            }
                        });
                    }
                });
                return [200, responseObj];
            });

        }]);
})(window.angular);