

var container = document.getElementById('visualization');

//Crear un DataSet vacio
var items = new vis.DataSet();

var options = {
    editable: true
};

//Crea el timeline
var timeline = new vis.Timeline(container, items, options);

function loadData()
{

}