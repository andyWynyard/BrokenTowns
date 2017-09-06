angular.module('appModule', [ 'ngRoute', 'ui.bootstrap', 'ngCookies', 'authModule' ]).config(
		function($routeProvider) {
			$routeProvider.when('/', {
				template : '<home></home>'
			}).when('/register', {
				template : '<register></register>'
			}).when('/login', {
				template : '<login></login>'
			}).otherwise({
				template : '<not-found></not-found>'
			})

		})