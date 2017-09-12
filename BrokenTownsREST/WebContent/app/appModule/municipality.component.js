angular.module('appModule')
	.component('municipality', {
		templateUrl :  "app/appModule/municipality.component.html",
		controller  :  function(caseItemService, $filter, authService, userService, municipalityService) {
			var vm = this;
			
			vm.municipality = null;
			
			
			vm.loadMunicipality = function() {
				var user = null;
				var token = authService.getToken();
				console.log(token);
			}
			vm.loadMunicipality();
			
			vm.reload = function() {
				caseItemService.index()
					.then(function(res) {
						caseItems = $filter('municipalityCaseItemFilter')(res.data, vm.municipality.id);
					});
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