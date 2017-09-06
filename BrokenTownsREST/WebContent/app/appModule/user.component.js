angular.module('appModule')
	.component('user', {
	templateUrl : 'app/appModule/user.component.html',
	controller : function($location, messageService) {
		var vm = this;
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