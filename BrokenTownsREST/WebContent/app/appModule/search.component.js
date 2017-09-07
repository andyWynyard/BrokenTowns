angular.module('appModule')
	.component('search', {
		templateUrl  :  "app/appModule/search.component.html",
		controller   : function(caseItemService, authService) {
			var vm = this;
			
			var userId = authService.getToken().id;
			
			vm.searchResults = [];
			
			vm.loadAllCases = function() {
				caseItemService.index()
					.then(function(res) {
						vm.searchResults = res.data;
					});
			}
			vm.loadAllCases();
			
			vm.selected = null;
			
			vm.create = function(newCase) {
				newCase.userId = userId;
				console.log(newCase.userId);
				caseItemService.create(newCase)
					.then(function() {
						vm.loadAllCases();
					})
			}
			
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
			
		},
		controllerAs : "vm"
	})