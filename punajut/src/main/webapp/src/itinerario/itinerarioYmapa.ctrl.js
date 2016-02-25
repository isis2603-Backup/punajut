angular.module('todoAp', [])
  .controller('ControladorEventos', ['$scope', function($scope) {
    var ctrl = this;
  var ctrl = this;

  ctrl.eventos = [{texto: 'ev1'},{texto: 'ev2'}];

  ctrl.agregarEvento = function() {
      console.log("entre aca")
    ctrl.eventos.push({texto: ctrl.textoNuevoEvento});
    ctrl.textoNuevoEvento = '';
  };

}]);