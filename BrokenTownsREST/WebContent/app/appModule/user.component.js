angular.module('appModule')
	.component('user', {
	templateUrl : 'app/appModule/user.component.html',
	controller : function($location) {
		var vm = this;
		
		vm.showMessages = function(caseId) {
			messageService.index(caseId);
		}
		
		vm.messages = vm.showMessages();
		
	},
	
	controllerAs : 'vm'
			
	})