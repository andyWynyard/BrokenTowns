angular.module('appModule', [ 'ngRoute', 'ui.bootstrap', 'ngCookies', 'authModule', 'ngMap' ]).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).when('/register', {
				template : '<register></register>'
			}).when('/login', {
				template : '<login></login>' 
			}).when('/municipality', {
				template : '<municipality></municipality>'
			}) .when('/user', {
				template : '<user></user>'
			}).when('/search', {
				template : '<search></search>'
			}).otherwise({
				template : '<not-found></not-found>'
			})

		})