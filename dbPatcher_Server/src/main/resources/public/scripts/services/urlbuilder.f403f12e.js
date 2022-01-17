'use strict';

/**
 * @ngdoc service
 * @name dbeditClientApp.urlBuilder
 * @description
 * # urlBuilder
 * Factory in the dbeditClientApp.
 */
angular.module('dbeditClientApp')
  .factory('urlBuilder', function ($httpParamSerializer) {
    // Service logic

	function buildUrl(url, params) {
        var serializedParams = $httpParamSerializer(params);

        if (serializedParams.length > 0) {
            url += ((url.indexOf('?') === -1) ? '?' : '&') + serializedParams;
        }

        return url;
    }
    return buildUrl;
	
//    // Public API here
//    return {
//      someMethod: function () {
//        return meaningOfLife;
//      }
//    };
  });
