define(['angular'], function(angular) {
  "use strict";

  var MainCtrl = function(scope, http) {
    scope.stringCompare = {
      text: undefined,
      hypothesis: undefined
    };

    scope.results = undefined;
    scope.error = {};

    scope.submit = function() {
      var success = function(response) {
        scope.results = response.data;
      };

      var error = function(response) {
        console.error("Something went wrong while trying to compare strings", response);
        scope.error.response = response;
        scope.error.message = response.data.error;
      };

      http.post('/api/string-compare', scope.stringCompare).then(success, error);
    };
  };

  return {
    register: function(module) {
      module.controller('MainCtrl', ['$scope', '$http', MainCtrl]);
    }
  };
});
