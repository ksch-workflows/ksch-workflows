
var module = require('./PatientFormFields.js');

QUnit.test( "test sum calculation", function( assert ) {
  assert.ok( module.sum(1, 2) == 3, "Passed!" );
});
