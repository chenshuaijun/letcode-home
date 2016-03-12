/**
 * 
 */
app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/about', {
		templateUrl : 'views/about/about.html',
		controller : 'crtlabout'
	}).when('/index', {
		templateUrl : 'views/index.html',
		controller : 'crtlindex'
	}).when('/note', {
		templateUrl : 'views/note/note.html',
		controller : 'crtlNote'
	}).when('/about/:bookId/ch/:chapterId', {
		templateUrl : 'about.html',
		controller : 'crtlabout'
	}).otherwise({
		redirectTo : '/index'
	});
});