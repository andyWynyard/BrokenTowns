angular.module('appModule')
	  .factory('caseItemService', function($http, authService, $location) {
		  var service = {};
		  
		  var BASE_URL = "http://localhost:8080/BrokenTownsREST/api/caseItems/";
		  
		  var token = null;
		   
		  var refreshToken = function() {
		    token = authService.getToken();
		  };
	   
		  refreshToken();
		  
		  var checkLogin = function() {
			  var loggedInUser = authService.getToken();
			  if (loggedInUser == null) {
				  $location.path("/login");
			  }
		  }
		  
		  service.index = function() {
			  checkLogin();
			  refreshToken();
			  
			  return $http({
				  method  : "GET", 
				  url     : BASE_URL
			  });
		  }
		  
		  service.create = function(caseItem) {
			  // TEMPORARY STUFF, MUST REMOVE PLZ:
			  caseItem.municipalityId = 3;
			  caseItem.longitude = "231";
			  caseItem.latitude = "123";
			  checkLogin();
			  refreshToken();
			  caseItem.done = false;
			  return $http({
				  method   :   "POST",
				  url      :   BASE_URL,
				  headers  : {
					  'Content-Type'  :  'application/json'
				  },
				  data     : caseItem
			  });
		  }
		  
		  service.update = function(caseItem) {
			  checkLogin();
			  refreshToken();
			  var id = caseItem.id;
			  /*
			   caseItem.completeDate = "";
			   if (caseItem.done == true) {
			   	caseItem.completeDate = $filter('date')(Date.now(), 'MM/dd/yyyy');
			   }
			   */
			  return $http({
				  method   :  "PUT",
				  url      :  BASE_URL + id,
				  headers  : {
					  'Content-Type' : "application/json"
				  },
				  data     : caseItem
			  });
		  }
		  
		  service.destroy = function(caseId) {
			  checkLogin();
			  refreshToken();
			  return $http({
				  method    :   "DELETE",
				  url       :   BASE_URL + caseId
			  });
		  }
		  
		  return service;
	  })
	  