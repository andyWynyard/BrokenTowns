angular.module('appModule')
	.component('search', {
		templateUrl  :  "app/appModule/search.component.html",
		controller   : function(caseItemService) {
			var vm = this;
			
			vm.searchResults = [];
			
			vm.loadAllCases = function() {
				caseItemService.index()
					.then(function(res) {
						vm.searchResults = res.data;
					});
			}
			vm.loadAllCases();
			
			vm.selected = null;
			
		},
		controllerAs : "vm"
	})