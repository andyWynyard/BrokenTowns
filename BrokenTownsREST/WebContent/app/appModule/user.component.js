angular.module('appModule')
	.component('user', {
	templateUrl : 'app/appModule/user.component.html',
	controller : function($location, authService, userService, messageService) {
		var vm = this;
		
		var userId = authService.getToken().id;
		vm.user = null;
		var setUser = function() {
			userService.show(userId)
			.then(function(res) {
				vm.user = res.data;
				console.log("USER NAME = " + vm.user.firstName)
			})
		}
		setUser();
		
		vm.selected = null;
		
		vm.setSeverityColor = function() {
			if (vm.selected !=null) {
				var severe = vm.selected.severity;
				if (severe == 5) {
					return "red";
				} else if (severe == 4) {
					return "orange";
				} else if (severe == 3) {
					return "yellow";
				} else if (severe == 2) {
					return "yellow-green";
				} else if (severe == 1) {
					return "green";
				}
			}
		}
		
		vm.messages =[];
		vm.showMessages = function() {
			if (vm.selected != null) {
				messageService.index(vm.selected.id)
					.then(function(response) {
						vm.messages = response.data;
						console.log(vm.messages);
					})
			}
		}
		vm.showMessages();
		
	},
	
	controllerAs : 'vm'
			
	})