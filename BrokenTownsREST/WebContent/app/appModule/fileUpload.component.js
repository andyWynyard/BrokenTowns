angular.module('appModule')
.component('fileUpload', {
	templateUrl : 'app/fileUpload.component.html',
	controller : function(uploadService) {
		var vm = this;
		console.log("loaded")

		vm.file = {};

		vm.upload = function(imgData) {
			uploadService.upload(imgData, vm.file);
		}
	},
	controllerAs : 'vm'
})