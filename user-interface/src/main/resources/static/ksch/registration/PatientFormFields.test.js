/*!
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// -----------------------------------------------------------------------------
QUnit.module("onDateOfBirthUpdated", {
    beforeEach: function() {
        resetDateOfBirth();
        resetAge();
    }
});
// -----------------------------------------------------------------------------

QUnit.test("Should update age after input of date of birth", function(assert) {
    setDateOfBirth(new Date(2000, 1, 1));

    onDateOfBirthUpdated();

    assert.ok(Number.isInteger(getAge()));
});

QUnit.test("Should skip age update if date of birth could not be read", function(assert) {
    resetDateOfBirth();

    onDateOfBirthUpdated();

    assert.notOk(Number.isInteger(getAge()));
});

// -----------------------------------------------------------------------------
QUnit.module("onAgeUpdated");
// -----------------------------------------------------------------------------

QUnit.test("Should set estimated date of birth after input of the patient age", function(assert) {
    setAge(15);

    onAgeUpdated();

    assert.ok(getAge());
    assert.ok(getDateOfBirth());
});

QUnit.test("Should reset age if user input was not a number", function(assert) {
    setAge("Fifteen");

    onAgeUpdated();

    assert.notOk(getAge());
});

QUnit.test("Should reset age if user input is greater than 150", function(assert) {
    setAge(151);

    onAgeUpdated();

    assert.notOk(getAge());
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
    setDateOfBirth(expectedDateOfBirth);

    var dateOfBirth = getDateOfBirth();

    assert.equal(dateOfBirth.getTime(), expectedDateOfBirth.getTime());
});

QUnit.test("Should skip empty date of birth", function(assert) {
    resetDateOfBirth();

    var dateOfBirth = getDateOfBirth();

    assert.notOk(dateOfBirth);
});

QUnit.test("Should skip incomplete date of birth", function(assert) {
    var dateOfBirth = getDateOfBirth();
    assert.notOk(dateOfBirth);
});

// -----------------------------------------------------------------------------
QUnit.module("setDateOfBirth");
// -----------------------------------------------------------------------------

QUnit.test("Should set date of birth input field", function(assert) {
    var dateOfBirth = new Date("2005-11-25");
    dateOfBirth.setHours(0);

    setDateOfBirth(dateOfBirth);

    assert.equal(getDateOfBirth().getTime(), dateOfBirth.getTime());
});

// -----------------------------------------------------------------------------
QUnit.module("getAge");
// -----------------------------------------------------------------------------

QUnit.test("Should read in age", function(assert) {
    setAge(25);

    var retrievedAge = getAge();

    assert.equal(retrievedAge, 25);
});

// -----------------------------------------------------------------------------
QUnit.module("setAge");
// -----------------------------------------------------------------------------

QUnit.test("Should set age", function(assert) {
    setAge(15);
    assert.equal(getAge(), 15);
});
