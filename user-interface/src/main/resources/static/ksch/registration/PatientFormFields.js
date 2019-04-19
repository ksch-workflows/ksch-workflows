
var YEAR_EPOCH = 1970;

function age( dateOfBirth, today = new Date() ) {
    var lifeTimeInMillis = today.getTime() - dateOfBirth.getTime();
    return Math.abs(new Date(lifeTimeInMillis).getUTCFullYear() - YEAR_EPOCH);
}

function date( year, month, day ) {
    return new Date(year + "-" + month + "-" + day);
}

function onAgeChanged() {

}

function getDateOfBirth( inputId ) {
    var retrievedDateOfBirth = document.getElementById( inputId ).value
    if ( ! new RegExp("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}").test(retrievedDateOfBirth) ) {
        return null;
    }
    return new Date( retrievedDateOfBirth );
}
