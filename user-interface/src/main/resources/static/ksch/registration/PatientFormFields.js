
var YEAR_EPOCH = 1970;
var DD_MM_YYYY = "[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}"

// -----------------------------------------------------------------------------
//  Humble, untested functions
// -----------------------------------------------------------------------------

function onDateOfBirthUpdated() {
    var dateOfBirth = getDateOfBirth("inputDateOfBirth");
    if (dateOfBirth) {
        setAge("age", calculateAge(dateOfBirth));
    }
}

function onAgeUpdated() {
    var yearOfBirth = new Date().getFullYear() - getAge("age");
    setDateOfBirth("inputDateOfBirth", new Date(yearOfBirth, 0, 1));
}

// -----------------------------------------------------------------------------
//  Unit tested functions
// -----------------------------------------------------------------------------

function calculateAge(dateOfBirth, today = new Date()) {
    var lifeTimeInMillis = today.getTime() - dateOfBirth.getTime();
    return Math.abs(new Date(lifeTimeInMillis).getUTCFullYear() - YEAR_EPOCH);
}

function getDateOfBirth(inputId) {
    var dateOfBirthValue = document.getElementById( inputId ).value
    if (!new RegExp(DD_MM_YYYY).test(dateOfBirthValue)) {
        console.log("[Warning] Cannot parse date from: " + dateOfBirthValue);
        return null;
    }
    var dateOfBirthElements = dateOfBirthValue.split("-");
    var day = dateOfBirthElements[0];
    var month = dateOfBirthElements[1];
    var year = dateOfBirthElements[2];
    return new Date(year, month - 1, day);
}

function setDateOfBirth(inputId, dateOfBirth) {
    var year = dateOfBirth.getFullYear();
    var month = dateOfBirth.getMonth() + 1;
    var day = dateOfBirth.getDate();
    document.getElementById(inputId).value = day + "-" + month + "-" + year;
}

function getAge(inputId) {
    return parseInt(document.getElementById(inputId).value);
}

function setAge(inputId, years) {
    document.getElementById(inputId).value = years;
}
