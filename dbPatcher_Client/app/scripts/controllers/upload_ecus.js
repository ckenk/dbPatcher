'use strict';

/**
 * @ngdoc function
 * @name dbeditClientApp.controller:UploadEcusCtrl
 * @description
 * # UploadEcusCtrl
 * Controller of the dbeditClientApp
 */

var app = angular.module('dbeditClientApp');

app.controller('UploadEcusCtrl', ['$scope', '$http', '$location', '$sce', 'Upload', '$timeout',
			  function ($scope, $http, $location, $sce, Upload, $timeout) {
//  .controller('UploadEcusCtrl',  function () {
				  
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
	
	$scope.upload_url = $scope.BASE_URL + "/ecus";
	$scope.errorMsg = "";
	$scope.upload_success = false;
				  
    $scope.uploadFiles = function (files) {
        $scope.files = files;
        if (files && files.length) {
            Upload.upload({
                url: $scope.upload_url,
                data: {
                    files: files
                },
				arrayKey: ''
            }).then(function (response) {
                $timeout(function () {
                    $scope.result = response.data;
					$scope.upload_success=true;
                });
            }, function (response) {
                if (response.status > 0) {
                    $scope.errorMsg = response.status + ': ' + response.data;
                }
            }, function (evt) {
                $scope.progress = 
                    Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
            });
        }
    };
		
	$scope.goToEdit = function() {
		$location.path("/edit");		
	};
				  
	$scope.trustSrc = function(src) {
		return $sce.trustAsResourceUrl(src);
  	};	
  }]);
