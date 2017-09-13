angular.module('appModule')
	.factory('municipalityService', function($http) {
		var service = {};
		
		var BASE_URL = "api/municipalities/";
		
		service.index = function() {
			return $http({
				method  :  "GET",
				url     : BASE_URL
			})
		}
		
		service.show = function(id) {
			return $http({
				method : "GET",
				url    : BASE_URL + id
			});
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
	