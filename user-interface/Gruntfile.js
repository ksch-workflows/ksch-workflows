module.exports = function(grunt) {
    grunt.initConfig({
        qunit: {
          all: ['**/*.test.html']
        }
    });

    // Call these here instead, where the variable grunt is defined.
    grunt.loadNpmTasks('grunt-contrib-qunit');
};
