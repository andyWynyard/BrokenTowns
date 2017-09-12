angular.module('appModule')
	.factory('municipalityService', function($http) {
		var service = {};
		
		var BASE_URL = "http://localhost:8080/BrokenTownsREST/api/municipalities/";
		
		service.index = function() {
			return $http({
				method  :  "GET",
				url     : BASE_URL
			})
		}
		
		service.create = function(municipality) {
			return $http({
				method  : "POST",
				url     : BASE_URL,
				headers : {
					  'Content-Type'  :  'application/json'
				  },
				data    : municipality
				});
			
		}
		
		return service;
	})
	