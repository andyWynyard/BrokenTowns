angular.module('appModule')
	.component('municipality', {
		templateUrl :  "app/appModule/municipality.component.html",
		controller  :  function(caseItemService, $filter) {
			var vm = this;
			
			vm.caseItems = [];
			
			//NEED A WAY TO GET MUNICIPALITY ID BASED ON USER
			// vvvvvvvvv
			vm.reload = function() {
				caseItemService.index()
					.then(function(res) {
						caseItems = $filter('municipalityCaseItemFilter')(res.data, vm.municipality.id);
					});
			}
			// ^^^^^^^^^
			vm.municipality = {
				name : "Practice",
				state: "Practice State",
				pic  : "http://www.monaco-denver.com/images/1700-960/denver-downtown-buildings-mountains-boutique-33b2fcd3.jpg"
			}
			
			vm.markComplete = function(municipality) {
				caseItemService.update(municipality.id)
					.then(function(res) {
						vm.municipality = res.data;
					})
			}
		},
		controllerAs : "vm"
});