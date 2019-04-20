
// -----------------------------------------------------------------------------
QUnit.module("onDateOfBirthUpdated");
// -----------------------------------------------------------------------------

// Should update age after input of date of birth

// Should skip age update if date of birth could not be read

// -----------------------------------------------------------------------------
QUnit.module("onAgeUpdated", {
    beforeEach: function() {
        setAge("");
    }
});
// -----------------------------------------------------------------------------

QUnit.test("Should set estimated date of birth after input of the patient age", function(assert) {
    assert.ok(1);
});

QUnit.test("Should reset age if user input was not a number", function(assert) {
    assert.ok(1);
});

QUnit.test("Should reset age if user input is greater than 150", function(assert) {
    assert.ok(1);
});

// -----------------------------------------------------------------------------
QUnit.module("calculateAge");
// -----------------------------------------------------------------------------

QUnit.test("Should calculate age from date of birth", function(assert) {
    assert.equal(calculateAge(new Date("2000-01-01"), new Date("2019-01-01")), 19);
});

// -----------------------------------------------------------------------------
QUnit.module("getDateOfBirth");
// -----------------------------------------------------------------------------

QUnit.test("Should read in date of birth", function(assert) {
    var expectedDateOfBirth = new Date("2005-11-25");
    expectedDateOfBirth.setHours(0);

    var dateOfBirth = getDateOfBirth("dateOfBirth");

    assert.equal(dateOfBirth.getTime(), expectedDateOfBirth.getTime());
});

QUnit.test("Should skip empty date of birth", function(assert) {
    var dateOfBirth = getDateOfBirth("dateOfBirth_empty");
    assert.notOk(dateOfBirth);
});

QUnit.test("Should skip incomplete date of birth", function(assert) {
    var dateOfBirth = getDateOfBirth("dateOfBirth_incomplete");
    assert.notOk(dateOfBirth);
});

// -----------------------------------------------------------------------------
QUnit.module("setDateOfBirth");
// -----------------------------------------------------------------------------

QUnit.test("Should set date of birth input field", function(assert) {
    var dateOfBirth = new Date("2005-11-25");
    dateOfBirth.setHours(0);

    setDateOfBirth(dateOfBirth, "dateOfBirth_testSetValue");

    assert.equal(getDateOfBirth("dateOfBirth_testSetValue").getTime(), dateOfBirth.getTime());
});

// -----------------------------------------------------------------------------
QUnit.module("getAge");
// -----------------------------------------------------------------------------

QUnit.test("Should read in age", function(assert) {
    var expectedAge = 25;

    var retrievedAge = getAge("age_withInitialValue");

    assert.equal(retrievedAge, expectedAge);
});

// -----------------------------------------------------------------------------
QUnit.module("setAge");
// -----------------------------------------------------------------------------

QUnit.test("Should set age", function(assert) {
    setAge(15, "age_withoutValue");
    assert.equal(getAge("age_withoutValue"), 15);
});
