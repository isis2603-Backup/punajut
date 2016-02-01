# Tabla de Contenidos

-  [Introducción](#introducción)
-  [Módulo Ng Auth](#módulo-ng-auth)
-  [Configuración Módulo de Seguridad](#configuración-módulo-de-seguridad)


## Introducción
Al finalizar este paso el estudiante estará en la capacidad de configurar y hacer uso del módulo de seguridad para un proyecto AngularJS. Las siguientes instrucciones muestran el paso a paso para la configuración del módulo dentro del proyecto.  

## Módulo Ng-Auth
El módulo **ng-crud-auth** tiene como finalidad asociar los *templates* de registro, autenticación, autorización y soporte de restauración de contraseña de un proyecto AngularJS con los servicios REST diseñados en un proyecto de Backend. Usted simplemente debe configurar el módulo con la url de los servicios de backend y definir el estados de éxito. Ng-Crud-Auth tiene soporte para UI-Router. Ver documentación de [UI-Router](https://github.com/angular-ui/ui-router#angularui-router-)


## Configuración Módulo de Seguridad
Realice los siguientes pasos para configurar el módulo de seguridad

- Usted debe agregar en el index.html la librería javascript del módulo de seguridad. Para ésto usted debe agregar la siguiente línea en el header del archivo.

```HTML
<script src="https://rawgit.com/recursosCSWuniandes/ng-crud-auth/master/dist/ngcrud-auth.min.js" type="text/javascript"></script>
```

