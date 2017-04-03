'use strict';

var gulp = require('gulp'),
	sass = require('gulp-sass');
	
var config = require('./config');

module.exports = {
	compile: compile
}

function compile() {
	return gulp.src(config.app + 'content/scss/*.scss')
		.pipe(sass())
		.on('error', sass.logError)
		.pipe(gulp.dest(config.app + 'content/css'));
	
}