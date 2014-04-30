requirejs.config({
  baseUrl: '/assets',
  paths: {
    'angular': '/assets/lib/angularjs/angular',
    'angular-animate': '/assets/lib/angularjs/angular-animate',
    'angular-sanitize': '/assets/lib/angularjs/angular-sanitize',
    'angular-ui-router': '/assets/lib/angular-ui-router/angular-ui-router'
  },
  shim: {
    'angular': {
      exports: 'angular'
    },
    'angular-animate': {
      deps: ['angular']
    },
    'angular-sanitize': {
      deps: ['angular']
    },
    'angular-ui-router': {
      deps: ['angular']
    }
  }
});

requirejs(['angular', 'app', 'angular-ui-router'], function(angular, app) {
  angular.element(document).ready(function() {
    angular.bootstrap(document, [app.name]);
  });
});
