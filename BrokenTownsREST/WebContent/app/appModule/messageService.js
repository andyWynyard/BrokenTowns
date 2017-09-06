angular.module('appModule')
	.factory('messageService', function($http, $filter, authService, $location, $rootScope) {
		var service = {};
		
		var checkLogin = function() {
			if (authService.getToken().id) {
				return true;
			}
			$location.path('/login')
	}
		
		service.index = function(caseId) {
			return $http({
				method : 'GET',
				url : 'api/caseItems/' + caseId + '/messages'
			})
			
		}
		
		service.create = function(caseId) {
			return $http({
				method : 'POST',
				url : 'caseItems/' + caseId + '/messages'
			})
		}
		
		return service;
	})