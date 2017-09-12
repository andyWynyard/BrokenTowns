angular.module('authModule')
.component('login', {
	templateUrl : 'app/authModule/login.component.html',
	controller : function(authService, $location) {
		var vm = this;
		
		vm.errors = [];
			
		vm.login = function(user) {
			vm.errors = [];
		
			authService.login(user)
				.then(function(res) {
					if(res.data.municipality.id === null) {
					$location.path('/user')
					return true;
					} else if(res.data.municipality.id !== null) {
						$location.path('/municipality')
						return true;
					}
		
				})
				.catch();
				vm.errors.push("Your email and/or password is incorrect. Please Try Again");
				return vm.errors;
		}
		
		vm.checkLogin = function() {
			if (authService.getToken().id) {
				return true;
				console.log("inside of check login: "  + vm.showLogin);
			}
			return false;
		}
		
		
	},
	controllerAs : 'vm'
})