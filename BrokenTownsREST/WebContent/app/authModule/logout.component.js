angular.module('authModule')
.component('logout', {
	templateUrl : 'app/authModule/logout.component.html',
	controller : function(authService, $location) {
		var vm = this;
		
		vm.logout = function() {
			authService.logout()
				.then(function(res){
					$location.path("/")
				})
				.catch(console.error)
		}
		
	},
	controllerAs  : 'vm'
})