
QUnit.test( "Should calculate age from date of birth", function( assert ) {
    assert.equal(calculateAge(new Date("2000-01-01"), new Date("2019-01-01")), 19);
});

QUnit.test( "Should read in date of birth", function( assert ) {
    var expectedDateOfBirth = new Date("2005-11-25");
    expectedDateOfBirth.setHours(0);

    var dateOfBirth = getDateOfBirth("dateOfBirth");

    assert.equal(dateOfBirth.getTime(), expectedDateOfBirth.getTime());
});

QUnit.test( "Should skip empty date of birth", function( assert ) {
    var dateOfBirth = getDateOfBirth("dateOfBirth_empty");
    assert.notOk(dateOfBirth);
});

QUnit.test( "Should skip incomplete date of birth", function( assert ) {
    var dateOfBirth = getDateOfBirth("dateOfBirth_incomplete");
    assert.notOk(dateOfBirth);
});

QUnit.test( "Should set date of birth input field", function( assert ) {
    var dateOfBirth = new Date("2005-11-25");
    dateOfBirth.setHours(0);

    setDateOfBirth("dateOfBirth_testSetValue", dateOfBirth);

    assert.equal(getDateOfBirth("dateOfBirth_testSetValue").getTime(), dateOfBirth.getTime());
});

QUnit.test( "Should read in age", function( assert ) {
    var expectedAge = 25;

    var retrievedAge = getAge("age_withInitialValue");

    assert.equal(retrievedAge, expectedAge);
});

QUnit.test( "Should set age", function( assert ) {
    setAge(15, "age_withoutValue");
    assert.equal(getAge("age_withoutValue"), 15);
});
