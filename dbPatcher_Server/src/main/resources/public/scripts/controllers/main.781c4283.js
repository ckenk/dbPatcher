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
//	   .then(function(result) {
	   		$location.path("/upload")
	   }).error(function (err) {
//	   },function (err){
		   $scope.status = err
	   });
   };
			//http://stackoverflow.com/questions/18599764/redirect-to-a-different-page-after-post-using-angular
			//http://stackoverflow.com/questions/13963022/angularjs-how-to-implement-a-simple-file-upload-with-multipart-form
//			$scope.uploadGlobal = function () {
//				
////				var myElement = angular.element($document[0].querySelector("gldb_input"))
//				
//				var fd = new FormData();
//				//Take the first selected file
//				fd.append("file", files[0]);
//
//				$http.post('http://localhost:8080/upload_global/', fd, {
//					withCredentials: false,
//					headers: {
//						'Content-Type': undefined
//					},
//					transformRequest: angular.identity
//				}).success(function (data) {
//					console.log('File uploaded succecssfully')
//					$location.path('http://localhost:9000/#/');
//				}).error(function(data){
//					console.log('File uploaded failed')
//				});
//
////				$http({
////					method: 'POST',
////					url: 'http://localhost:8080/upload_global/',
////					data: $scope.person,
////					headers: {
////						'Content-Type': 'application/json; charset=utf-8'
////					}
////				}).success(function (data) {
////					$location.path('/addressBook');
////				});
//			};
//			
//			$scope.submit_gl = function() {
//				var ecuDbfiles = $scope.form.ecuDbfiles;
//				var glDbFile = $scope.form.gldbfile;
//				
////				if ($scope.form.file.$valid && $scope.file) {
//				if (glDbFile.$valid) {
//					//$scope.upload($scope.file);
//					var fd = new FormData();
//					//fd.append("file", $scope.form.gldbfile);
//					fd.append("file", glDbFile);
//					$http.post('http://localhost:8080/global/', fd, {
//						withCredentials: false,
//						headers: {
//							'Content-Type': undefined
//						},
//						transformRequest: angular.identity
//					}).success(function (data) {
//						console.log('Global DB upload succeed',data);
//						//if($scope.form.ecudbfiles.$valid) {
//						//if($scope.parent.form_ecu_db_upload.ecudbfiles.$valid) {
//						if(ecuDbfiles.$valid) {
//							var fd = new FormData();
//							//fd.append("file", $scope.form.gldbfile);
//							fd.append("file", ecuDbfiles);
//							$http.post('http://localhost:8080/ecus/', fd, {
//								withCredentials: false,
//								headers: {
//									'Content-Type': undefined
//								},
//								transformRequest: angular.identity
//							}).success(function (data) {
//								console.log('Ecu DBs uploaded succecssfully', data);
//								//$location.path('http://localhost:9000/#/')
//								$location.path('http://localhost:9000/edit');
//							}).error(function(data){
//								console.log('Ecu DBs upload failed', data);
//							});
//						}}).error(function(data) {
//							console.log('Global DB upload failed',data);
//						});
//					//$location.path('http://localhost:9000/#/');
//				}
//			};
  }]);