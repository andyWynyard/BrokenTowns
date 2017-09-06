angular.module('appModule').component('navigation', {
	templateUrl : 'app/appModule/navigation.component.html',
	controller : function(authService) {
		var vm = this;

		vm.showLogin = true;
		
		console.log("before check login: " + vm.showLogin);
		
		vm.checkLogin = function() {
			if (authService.getToken().id) {
				return false;
				console.log("inside of check login: "  + vm.showLogin);
			}
			return true;
		}
		
		vm.showLogin = vm.checkLogin();
		console.log("After checkLogin: " +vm.showLogin);

	},

	controllerAs : 'vm'
})