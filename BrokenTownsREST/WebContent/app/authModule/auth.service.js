angular.module('authModule')
  .factory('authService', function($http, $cookies, $rootScope) {
    var service = {};

    var saveToken = function(user) {
    		$cookies.put('uid', user.id)
    		$cookies.put('email', user.email)
    		$cookies.put('firstName', user.firstName)
    		$cookies.put('lastName', user.lastName)
    		$cookies.put('municipalityId', user.municipalityId);
    }

    service.getToken = function() {
    		return {
    			id : $cookies.get("uid"),
    			email : $cookies.get("email"),
    			firstName : $cookies.get("firstName"),
    			lastName : $cookies.get("lastName"),
    			municipalityId : $cookies.get("municipalityId")
    			
    		}
    }

    var removeToken = function() {
    		$cookies.remove('uid');
		$cookies.remove('email');
		$cookies.remove('firstName');
		$cookies.remove('lastName');
		$cookies.remove('municipalityId');
    }

    service.login = function(user) {
    		return $http({
    			method : 'POST',
    			url : 'api/auth/login',
    			headers : {
    				'Content-Type' : 'application/json'
    			},
    			data : user
    		})
    		.then(function(res) {
    			console.log("HEY EVERYONE LOOK HERE NOW 955AM:");
    			console.log(res.data);
    			saveToken(res.data);
    			$rootScope.$broadcast('loggedIn', {
    				user : res.data
    			})
    			return res;
    		})
    		.catch(console.error);
    }

    service.register = function(user) {
		return $http({
			method : 'POST',
			url : 'api/auth/register',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : user
		})
		.then(function(res) {
			saveToken(res.data);
			return res;
		})
		.catch(console.error);
    }

    service.logout = function() {
    		return $http({
    			method : 'POST',
    			url : 'api/auth/logout'
    		})
    		.then(function(res) {
    			removeToken();
    			return res;
    		})
    		.catch(console.error);
    }

    return service;
})