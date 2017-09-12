angular.module('appModule').component('navigation', {
	templateUrl : 'app/appModule/navigation.component.html',
	controller : function(authService, $location) {
		var vm = this;
		
		console.log("before check login: " + vm.showLogin);
		
		vm.checkLogin = function() {
			if (authService.getToken().id) {
				vm.userFirstName = authService.getToken().firstName;
				return true;
				console.log("inside of check login: "  + vm.showLogin);
			}
			return false;
		}
		
		vm.logout = function() {
			authService.logout()
				.then(function(res){
					$location.path("/")
				})
				.catch(console.error)
		}
		
		
		console.log("After checkLogin: " +vm.showLogin);
//		
//		$interval(function() {
//			vm.checkLogin();
//		},
//
////The below sets the timeout, the first number is the interval, the second
////number is how many times it fires
//		1, 1
//
//		);

	},

	controllerAs : 'vm'
})