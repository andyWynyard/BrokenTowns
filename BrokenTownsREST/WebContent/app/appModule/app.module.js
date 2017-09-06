angular.module('appModule', [ 'ngRoute', 'ui.bootstrap', 'ngCookies', 'authModule' ]).config(
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
		
			}).otherwise({
				template : '<not-found></not-found>'
			})

		})