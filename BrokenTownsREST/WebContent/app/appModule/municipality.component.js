angular.module('appModule')
	.component('municipality', {
		templateUrl :  "app/appModule/municipality.component.html",
		controller  :  function(caseItemService) {
			var vm = this;
			
			vm.caseItems = [];
			
			vm.reload = function() {
				caseItemService.index()
					.then(function(res) {
						caseItems = res.data;
						//NEED TO INSERT FILTER!!
					});
			}
			
			vm.municipality = {
				name : "Practice",
				state: "Practice State",
				pic  : "http://www.monaco-denver.com/images/1700-960/denver-downtown-buildings-mountains-boutique-33b2fcd3.jpg"
			}
		},
		controllerAs : "vm"
});