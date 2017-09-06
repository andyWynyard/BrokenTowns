angular.module('authModule')
.component('login', {
	templateUrl : 'app/authModule/login.component.html',
	controller : function(authService, $location) {
		var vm = this;
		
		vm.login = function(user) {
			authService.login(user)
				.then(function(res) {
					console.log(res.data);
					$location.path('/todo')
				})
				.catch(console.error);
		}
		
		
	},
	controllerAs : 'vm'
})