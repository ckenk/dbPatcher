'use strict';

/**
 * @ngdoc function
 * @name dbeditClientApp.controller:DownloadCtrl
 * @description
 * # DownloadCtrl
 * Controller of the dbeditClientApp
 */
angular.module('dbeditClientApp')
  .controller('DownloadCtrl', ['$scope','$http',
							   function ($scope,$http) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
	
	$scope.downloadLink = "";
								   
	$scope.downloadGlobal = function() {
		$http.get($scope.BASE_URL + "/global")
		.success(function(globalDB) {
			console.log("File download success",globalDB);
		}).error(function(errorData) {
			console.log("File download failed",errorData);
		});
	};
	
	var init = function() {
		$scope.downloadLink = $scope.BASE_URL + "/global";
	};
	init();
	
  }]);
