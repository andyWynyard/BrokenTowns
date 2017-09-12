angular.module('authModule')
.component('register', {
	templateUrl : 'app/authModule/register.component.html',
	controller : function(authService, $location) {
		var vm = this;
		
		vm.errors = [];
		
		vm.invalid = function(user) {
			if (!user) return true;
			if (!user.email || !user.password || !user.confirm) return true;
			return false;
		}
		
		vm.register = function(user) {
			vm.registrationErrors = [];
			
			var re = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
			
			if(!user.firstName) {
				vm.errors.push("You must have a first name");
			}
			
			if(!user.lastName) {
				vm.errors.push("You must have a last name");
			}
			
			
			if (!re.test(user.email)) {
				vm.errors.push("Your email is not an email");
			}
			if (!user.password || user.password.length < 6) {
				vm.errors.push("You must include a password, of at least 6 characters")
			}
			if (user.password !== user.confirm) {
				vm.errors.push("Your passwords do not match");
			}

			if (vm.errors.length > 0) {
				return;
			}
			
			delete user.confirm;
			
			authService.register(user)
			vm.errors = [];
			
			authService.login(user)
				.then(function(res) {
					if(res.data.municipality === null) {
					$location.path('/user')
					return true;
					} else if(res.data.municipality !== null) {
						$location.path('/municipality')
						return true;
					}
		
				})
				.catch();
				vm.errors.push("Your email and/or password is incorrect. Please Try Again");
				return vm.errors;
		}
		
		
	},
	controllerAs : 'vm'
})