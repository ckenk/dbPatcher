'use strict';

/**
 * @ngdoc function
 * @name dbeditClientApp.controller:EvceditCtrl
 * @description
 * # EvceditCtrl
 * Controller of the dbeditClientApp
 */
//angular.module('dbeditClientApp')
//  .controller('EvceditCtrl', ['$scope', '$http', '$location', '$document',
//						   function ($scope, $http, $location, $document) {
//    this.awesomeThings = [
//      'HTML5 Boilerplate',
//      'AngularJS',
//      'Karma'
//    ];
//  }]);
angular.module('dbeditClientApp')
  .controller('EvceditCtrl', ['$scope', '$http', '$location', '$document',
						   function ($scope, $http, $location, $document) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
							   $scope.myFun = function () {};
  }]);
