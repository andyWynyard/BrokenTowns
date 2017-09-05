angular.module('appModule', ['ngRoute', 'ui.bootstrap', 'ngCookies'])
	.config(function($routeProvider) {
		$routeProvider
			.when('/', {
				template : '<home></home>'
			})
			.otherwise({
				template : '<not-found></not-found>'
			})
	
	})