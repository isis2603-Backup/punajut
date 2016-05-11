(function (ng) {
    var mod = ng.module("ciudadModule");
console.log("llega ctrl ciudad");
    mod.controller("ciudadCtrl", ["$scope", "ciudadService", "eventoService", function ($scope, svc) {
            console.log("llega ctrl ciudad2");

            $scope.currentRecord = {
                id: undefined /* Tipo Long */,
                name: '' /* Tipo String */,
                descripcion: '' /* Tipo String */,
                clima: '' /* Tipo String */,
                longitud: '' /* Tipo Long */,
                latitud: '' /* Tipo Long */
            };

            $scope.records = [];
            $scope.alerts = [];

            $scope.today = function () {
                $scope.value = new Date();
            };

            $scope.clear = function () {
                $scope.value = null;
            };

            $scope.open = function ($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = true;
            };

            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            var self = this;
            function responseError(response) {
                self.showError(response.data);
            }

            //Variables para el controlador
            this.readOnly = false;
            this.editMode = false;
            this.showEventosMode = false;




            this.changeTab = function (tab) {
                $scope.tab = tab;
            };

            //Ejemplo alerta
            showMessage("Bienvenido!, Para agregar una ciudad, presione el botón Crear ciudad", "warning");

            this.createRecord = function () {
                $scope.$broadcast("pre-create", $scope.currentRecord);
                this.editMode = true;
                $scope.currentRecord = {};
                $scope.$broadcast("post-create", $scope.currentRecord);
            };

            this.editRecord = function (record) {
                $scope.$broadcast("pre-edit", $scope.currentRecord);
                return svc.fetchRecord(record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    self.editMode = true;
                    $scope.$broadcast("post-edit", $scope.currentRecord);
                    return response;
                }, responseError);
            };

            this.fetchRecords = function () {
                return svc.fetchRecords().then(function (response) {
                    $scope.records = response.data;
                    $scope.currentRecord = {};
                    self.editMode = false;

                    return response;
                }, responseError);
            };

            this.saveRecord = function () {
                return svc.saveRecord($scope.currentRecord).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            this.deleteRecord = function (record) {
                this.showEventosMode = false;
                return svc.deleteRecord(record.id).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            function updateEventos(event, args) {
                $scope.currentRecord.eventos = args;
            }
            ;

            $scope.$on('updateEventos', updateEventos);

            this.showEventos = function () {
                this.showEventosMode = true;
            };

            this.fetchRecords();
        }
    ]);

})(window.angular);

