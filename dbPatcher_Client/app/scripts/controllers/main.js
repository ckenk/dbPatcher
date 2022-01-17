'use strict';

/**
 * @ngdoc function
 * @name dbeditClientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the dbeditClientApp
 */
angular.module('dbeditClientApp')
	.controller('MainCtrl', ['$scope', '$http', '$location','$document',
						   function ($scope, $http, $location, $document) {
			this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
							   
   $scope.status = "";
   
   $scope.init = function() {
	   $scope.status = "";
	   $http.get($scope.BASE_URL + "/init")
	   .success(function(result) {
	   		$location.path("/upload")
	   }).error(function (err) {
		   $scope.status = err
	   });
   };
  }]);