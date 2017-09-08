angular.module('appModule')
.factory('uploadService', function(authService,$http) {
	var service = {};

	service.upload = function(s3Image,file) {
		var fd = new FormData();
		fd.append('file', file);
	//	s3Image.userId = authService.getToken();
		fd.append('data', JSON.stringify(s3Image));
		 $http.post(`api/user/${s3Image.userId}/upload`, fd, {
				transformRequest : angular.identity,
				headers: {
	            	'Content-Type': undefined
	            	}
	        })
	        .then(function(r) {
	        	console.log(r)
	        })
	        .catch(function(e) {
	        	console.log(e);
	        })
	}

	return service;
})