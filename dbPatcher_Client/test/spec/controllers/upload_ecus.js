'use strict';

describe('Controller: UploadEcusCtrl', function () {

  // load the controller's module
  beforeEach(module('dbeditClientApp'));

  var UploadEcusCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UploadEcusCtrl = $controller('UploadEcusCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(UploadEcusCtrl.awesomeThings.length).toBe(3);
  });
});
