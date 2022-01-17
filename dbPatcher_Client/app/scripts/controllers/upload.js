'use strict';

/**
 * @ngdoc function
 * @name dbeditClientApp.controller:UploadCtrl
 * @description
 * # UploadCtrl
 * Controller of the dbeditClientApp
 */
var app = angular.module('dbeditClientApp');
//angular.module('dbeditClientApp')

app.controller('UploadCtrl', ['$scope', 'Upload', '$timeout', '$location',
							  function ($scope, Upload, $timeout, $location) {
		this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

	$scope.upload_success = false;
								  
	$scope.uploadVehicle = function (file) {
		$scope.init_fields();
		file.upload = Upload.upload({
			url: $scope.BASE_URL + "/global/",
			data: {
				//username: $scope.username,
				file: file
			},
		});

		file.upload.then(function (response) {
			$timeout(function () {
				file.result = response.data;
				$scope.upload_success = true;
			});
		}, function (response) {
			if (response.status > 0)
				$scope.errorMsg = response.status + ': ' + response.data;
		}, function (evt) {
			// Math.min is to fix IE which reports 200% sometimes
			file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		});
	}
	
	$scope.goToEcus = function() {
		$location.path("/ecus");		
	};

	$scope.init_fields = function() {
		$scope.upload_success = false;
		$scope.errorMsg = "";
//		file.progress = "";
	}
  }]);