'use strict';

describe('Controller: UploadGlobalCtrl', function () {

  // load the controller's module
  beforeEach(module('dbeditClientApp'));

  var UploadGlobalCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UploadGlobalCtrl = $controller('UploadGlobalCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(UploadGlobalCtrl.awesomeThings.length).toBe(3);
  });
});
