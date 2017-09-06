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
			})
		}
		setUser();
		
		vm.messages =[];
		vm.showMessages = function(caseId) {
			messageService.index(caseId)
				.then(function(response) {
					vm.messages = response.data;
					console.log(vm.messages);
				})
		}
		vm.showMessages();
		
	},
	
	controllerAs : 'vm'
			
	})