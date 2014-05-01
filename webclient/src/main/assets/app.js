define([
  'angular',
  './controllers/main-ctrl'
], function(
  angular,
  mainCtrl
) {

  "use strict";

  var module = angular.module('example-app', ['ui.router']);
  mainCtrl.register(module);
  return module;

});
