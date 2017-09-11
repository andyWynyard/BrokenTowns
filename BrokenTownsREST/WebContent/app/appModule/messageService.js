angular.module('appModule')
	.factory('messageService', function($http, $filter, authService, $location, $rootScope) {
		var service = {};
		
		var BASE_URL = "http://localhost:8080/BrokenTownsREST/api/caseItems/";
		
		var checkLogin = function() {
			if (authService.getToken().id) {
				return true;
			}
			$location.path('/login')
	}
		
		service.index = function(caseId) {
			checkLogin();
			return $http({
				method : 'GET',
				url : BASE_URL + caseId + '/messages'
			})
			
		}
		
		service.create = function(caseId, message) {
			return $http({
				method : 'POST',
				url : BASE_URL + caseId + '/messages',
				headers  : {
					  'Content-Type' : "application/json"
				  },
				  data     : message
			})
		}
		
		service.show = function(caseId, messageId) {
			return $http({
				method : 'GET',
				url : BASE_URL + caseId + '/messages/' + messageId
			})
		}
		
		service.update = function(caseId, messageId) {
			return $http({
				method : 'PUT',
				url : BASE_URL + caseId + '/messages/ ' + messageId,
				headers  : {
					  'Content-Type' : "application/json"
				  },
				  data     : message
			})
		}
		
		service.destroy = function(caseId, messageId) {
			return $http({
				method: 'DELETE',
				url : BASE_URL + caseId + '/messages/ ' + messageId
			})
		}
		
		return service;
	})