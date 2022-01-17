'use strict';

/**
 * @ngdoc overview
 * @name dbeditClientApp
 * @description
 * # dbeditClientApp
 *
 * Main module of the application.
 */
angular
  .module('dbeditClientApp', [
	'ui.bootstrap'
    ,'ngAnimate'
    ,'ngCookies'
    ,'ngResource'
    ,'ngRoute'
    ,'ngSanitize'
    //,'ngTouch'
	,'smart-table'
	,'checklist-model'
//	,'FileUploader'
	,'ngFileUpload'
  ])
  .config(['$httpProvider', function ($httpProvider) {
  	//Reset headers to avoid OPTIONS request (aka preflight)
  	$httpProvider.defaults.headers.common = {};
  	$httpProvider.defaults.headers.post = {};
  	$httpProvider.defaults.headers.put = {};
  	$httpProvider.defaults.headers.patch = {};
  }])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/download', {
        templateUrl: 'views/download.html',
        controller: 'DownloadCtrl',
        controllerAs: 'download'
      })
	  .when('/upload', {
        templateUrl: 'views/upload.html',
        controller: 'UploadCtrl',
        controllerAs: 'upload'
      })
      .when('/global', {
        templateUrl: 'views/upload_global.html',
        controller: 'UploadGlobalCtrl',
        controllerAs: 'upload_global'
      })
      .when('/ecus', {
        templateUrl: 'views/upload_ecus.html',
        controller: 'UploadEcusCtrl',
        controllerAs: 'ecus'
      })
      .when('/edit', {
        templateUrl: 'views/edit.html',
        controller: 'EditCtrl',
        controllerAs: 'edit'
      })
      .when('/vehicle/:year/:body', {
        templateUrl: 'views/evcedit.html',
        controller: 'EvceditCtrl',
        controllerAs: 'evcedit'
      })
	  .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutMyCtrl',
        controllerAs: 'AboutMyCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
//http://www.jvandemo.com/how-to-configure-your-angularjs-application-using-environment-variables/
.constant('config', {  
  apiUrl: 'http://localhost:8008',
  baseUrl: '/',
  enableDebug: true
})
//http://stackoverflow.com/questions/17505138/can-i-directly-access-module-constant-from-html-under-angularjs/17505414#17505414
//http://stackoverflow.com/questions/24621522/how-to-make-grunt-not-minify-certain-js-files
.run(function ($rootScope) {
      $rootScope.BASE_URL = 'http://localhost:8008';
      console.log('$rootScope.BASE_URL', $rootScope.BASE_URL);
})
.controller('HeaderCtrl', function ($scope, $location) { 
    $scope.isActive = function (viewLocation) { 
      return viewLocation === $location.path();
    };
  });
