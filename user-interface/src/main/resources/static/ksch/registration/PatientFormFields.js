
var YEAR_EPOCH = 1970;

function age(dateOfBirth, today = new Date()) {
    var lifeTimeInMillis = today.getTime() - dateOfBirth.getTime();
    return Math.abs(new Date(lifeTimeInMillis).getUTCFullYear() - YEAR_EPOCH);
}

if (typeof module !== 'undefined' && module.exports) {
    exports.age = age;
}
