
QUnit.test( "Should calculate age from date of birth", function( assert ) {
    assert.equal( age(new Date("2000-01-01"), new Date("2019-01-01")), 19 );
});

QUnit.test( "Should create date object", function( assert ) {
    assert.equal( date("2005", "11", "25").getTime(), new Date("2005-11-25").getTime() );
});

QUnit.test( "xxx", function( assert ) {
    assert.ok( 1, "Passed!" );
});


// update age

QUnit.test( "Should read in date of birth", function( assert ) {
    var dateOfBirth = getDateOfBirth("dateOfBirth");
    assert.equal( dateOfBirth.getTime(), new Date("2005-11-25").getTime() );
});

QUnit.test( "Should skip empty date of birth", function( assert ) {
    var dateOfBirth = getDateOfBirth("dateOfBirth_empty");
    assert.notOk( dateOfBirth );
});

QUnit.test( "Should skip incomplete date of birth", function( assert ) {
    var dateOfBirth = getDateOfBirth("dateOfBirth_incomplete");
    assert.notOk( dateOfBirth );
});