/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 function nuevaPagina1() {
                    var opcion = prompt("¿Qué quieres hacer con el itinerario 1? Editar:0, Eliminar:1");

                    if (opcion == 0)
                    {
                        alert("Editar imagen");
                    } else
                    {
                        alert("Eliminar imagen");
                        var elem = document.getElementById("imagen1");
                        elem.parentNode.removeChild(elem);
                        return false;
                    }
                }

                function nuevaPagina2() {
                    var opcion = prompt("¿Qué quieres hacer con el itinerario 2? Editar:0, Eliminar:1");

                    if (opcion == 0)
                    {
                        alert("Editar imagen");
                    } else
                    {
                        alert("Eliminar imagen");
                        var elem = document.getElementById("imagen2");
                        elem.parentNode.removeChild(elem);
                        return false;
                    }
                }

                function nuevaPagina3() {
                    var opcion = prompt("¿Qué quieres hacer con el itinerario 3? Editar:0, Eliminar:1");

                    if (opcion == 0)
                    {
                        alert("Editar imagen");
                    } else
                    {
                        alert("Eliminar imagen");
                        var elem = document.getElementById("imagen3");
                        elem.parentNode.removeChild(elem);
                        return false;
                    }
                }
                function nuevaPagina4() {
                    var opcion = prompt("¿Qué quieres hacer con el itinerario 4? Editar:0, Eliminar:1");

                    if (opcion == 0)
                    {
                        alert("Editar imagen");
                    } else
                    {
                        alert("Eliminar imagen");
                        var elem = document.getElementById("imagen4");
                        elem.parentNode.removeChild(elem);
                        return false;
                    }
                }
