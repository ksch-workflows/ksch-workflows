
var module = require('./PatientFormFields.js');

QUnit.test( "Should calculate age from date of birth", function( assert ) {
  assert.equal( module.age(new Date("2000-01-01"), new Date("2019-01-01")), 19 );
});

QUnit.test( "xxx", function( assert ) {
  assert.ok( 1, "Passed!" );
});
