
var YEAR_EPOCH = 1970;
var DD_MM_YYYY = "[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}"

function onDateOfBirthUpdated() {
    var dateOfBirth = getDateOfBirth();
    if (dateOfBirth) {
        setAge(calculateAge(dateOfBirth));
    }
}

function onAgeUpdated() {
    var patientAge = getAge();
    if (patientAge && patientAge < 150) {
        var yearOfBirth = new Date().getFullYear() - getAge("age");
        setDateOfBirth(new Date(yearOfBirth, 0, 1));
    } else {
        console.log("[INFO] Resetting age");
        setAge("");
    }
}

function calculateAge(dateOfBirth, today = new Date()) {
    var lifeTimeInMillis = today.getTime() - dateOfBirth.getTime();
    return Math.abs(new Date(lifeTimeInMillis).getUTCFullYear() - YEAR_EPOCH);
}

function getDateOfBirth(inputId = "dateOfBirth") {
    var dateOfBirthValue = document.getElementById(inputId).value
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

function setDateOfBirth(dateOfBirth, inputId = "dateOfBirth") {
    var year = dateOfBirth.getFullYear();
    var month = dateOfBirth.getMonth() + 1;
    var day = dateOfBirth.getDate();
    document.getElementById(inputId).value = day + "-" + month + "-" + year;
}

function getAge(inputId = "age") {
    var ageUserInput = document.getElementById(inputId).value
    if(Number.isInteger(parseInt(ageUserInput))) {
        return parseInt(ageUserInput);
    } else {
        return null;
    }
}

function setAge(years, inputId = "age") {
    document.getElementById(inputId).value = years;
}
