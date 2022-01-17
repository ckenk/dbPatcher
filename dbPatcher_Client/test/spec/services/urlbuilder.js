'use strict';

describe('Service: urlBuilder', function () {

  // load the service's module
  beforeEach(module('dbeditClientApp'));

  // instantiate service
  var urlBuilder;
  beforeEach(inject(function (_urlBuilder_) {
    urlBuilder = _urlBuilder_;
  }));

  it('should do something', function () {
    expect(!!urlBuilder).toBe(true);
  });

});
