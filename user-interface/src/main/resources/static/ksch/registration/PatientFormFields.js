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

var YEAR_EPOCH = 1970;
var DD_MM_YYYY = "[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}";
var AGE_ID = "age";
var DATE_OF_BIRTH_ID = "dateOfBirth";

function onDateOfBirthUpdated() {
    var dateOfBirth = getDateOfBirth();
    if (dateOfBirth) {
        setAge(calculateAge(dateOfBirth));
    }
}

function onAgeUpdated() {
    var patientAge = getAge();
    if (patientAge && patientAge < 150) {
        var yearOfBirth = new Date().getFullYear() - getAge();
        setDateOfBirth(new Date(yearOfBirth, 0, 1));
    } else {
        console.log("[INFO] Resetting age");
        resetAge();
    }
}

function calculateAge(dateOfBirth, today = new Date()) {
    var lifeTimeInMillis = today.getTime() - dateOfBirth.getTime();
    return Math.abs(new Date(lifeTimeInMillis).getUTCFullYear() - YEAR_EPOCH);
}

function getDateOfBirth() {
    var dateOfBirthValue = document.getElementById(DATE_OF_BIRTH_ID).value
    if (!new RegExp(DD_MM_YYYY).test(dateOfBirthValue)) {
        console.log("[WARNING] Cannot parse date from: " + dateOfBirthValue);
        return null;
    }
    var dateOfBirthElements = dateOfBirthValue.split("-");
    var day = dateOfBirthElements[0];
    var month = dateOfBirthElements[1];
    var year = dateOfBirthElements[2];
    return new Date(year, month - 1, day);
}

function setDateOfBirth(dateOfBirth) {
    var year = dateOfBirth.getFullYear();
    var month = dateOfBirth.getMonth() + 1;
    var day = dateOfBirth.getDate();
    document.getElementById(DATE_OF_BIRTH_ID).value = day + "-" + month + "-" + year;
}

function resetDateOfBirth() {
    document.getElementById(DATE_OF_BIRTH_ID).value = "";
}

function getAge() {
    var ageUserInput = document.getElementById(AGE_ID).value
    if (Number.isInteger(parseInt(ageUserInput))) {
        return parseInt(ageUserInput);
    } else {
        return null;
    }
}

function setAge(years) {
    document.getElementById(AGE_ID).value = years;
}

function resetAge() {
    setAge("");
}
