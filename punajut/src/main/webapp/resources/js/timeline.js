  // DOM element where the Timeline will be attached
  var container = document.getElementById('visualization');
  var it = 1;
  var cont = 1;
  // Create a DataSet (allows two way data-binding)
  var items = new vis.DataSet([
   // {className:'it2' ,id: 2, content: 'item 2', start: '2013-04-14'},
   // {className:'it3' ,id: 3, content: 'item 3', start: '2013-04-18'},
   // {className:'it4' ,id: 4, content: 'item 4', start: '2013-04-16', end: '2013-04-19'},
   // {className:'it5' ,id: 5, content: 'item 5', start: '2013-04-25'}
  ]);
  // Configuration for the Timeline
  var options = {
      editable: true,
      selectable:true,

      onAdd: function(item, callback)
      {
          if(it === 6)
              it = 1;

          item.className = 'it'+it;
          it++;
          item.content = 'Evento'+cont;
          cont++;
          callback(item);
      },

      onRemove: function(item, callback)
      {
          if(it === 1)
              it=6;
          else
              it--;
          callback(item);
      }
};

  // Create a Timeline
  var timeline = new vis.Timeline(container, items, options);