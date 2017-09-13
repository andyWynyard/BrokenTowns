angular.module('appModule')
	.component('municipality', {
		templateUrl :  "app/appModule/municipality.component.html",
		controller  :  function(caseItemService, $filter, authService, userService, municipalityService, $scope, NgMap, messageService) {
			var vm = this;
			
			vm.municipality = null;
			
			vm.map = null;
			vm.selected = null;
			vm.setSeverityColor = function() {
				if (vm.selected != null) {
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
			
			vm.loadMunicipality = function() {
				var token = authService.getToken();
				console.log(token);
				municipalityService.show(token.municipalityId)
					.then(function(res) {
						console.log("RETURNED MUNICIPALITY");
						console.log(res.data);
						vm.municipality = res.data;
					})
					.catch(function(err) {
						console.log("BROKE FOR SOME REASON");
						console.log(err);
					})
				
			}
			vm.loadMunicipality();
			
			vm.reload = function() {
				caseItemService.index()
					.then(function(res) {
						caseItems = $filter('municipalityCaseItemFilter')(res.data, vm.municipality.id);
					});
			}
			
			
			
			vm.markComplete = function(caseItem) {
				caseItem.done = !caseItem.done
				vm.municipality.caseItems.forEach(function(ci) {
					if (ci.id === caseItem.id) {
						ci.done = !caseItem.done
					}
				})
				
				
				console.log("caseItem.done ???");
				console.log(caseItem.done);
				
				caseItemService.update(caseItem)
					.then(function(res) {
						console.log('updated or something');
					})
			}
			
			vm.messages = [];
			vm.showMessages = function() {
				if (vm.selected != null) {
					messageService.index(vm.selected.id).then(
							function(response) {
								vm.messages = response.data;
								console.log(vm.messages);
							})
				}
			}
			vm.showMessages();

			vm.mapKey = "AIzaSyAM7sMRVwpJLTHY4KScoaPnpnjlZDRH3xg";
			
			vm.addMunicipalityToCaseItem = function(caseItem) {
				municipalityService.index()
					.then(function(res) {
						res.data.forEach(function(val) {
							val.caseItems.forEach(function(value) {
								console.log("IN MUNICIPALITY TO CASE ITEM: ");
								if (value.id == caseItem.id) {
									console.log("FOUND IT");
									vm.selected.municipality = val;
									return;
								}
							})
						})
					})
			}
			
			vm.applySelected = function(caseItem) {
				vm.selected = caseItem;

				vm.addMunicipalityToCaseItem(caseItem); 

				NgMap.getMap("map").then(
						function(map) {
							vm.map = map;
							google.maps.event.trigger(vm.map, "resize");
							vm.map.setCenter(new google.maps.LatLng(
									vm.selected.latitude,vm.selected.longitude
//									20.68177501,-103.3514794
									));
							vm.map.setZoom(15);
						});
				// .then(function(map){
				// google.maps.event.trigger(map, "resize");
				// map.setCenter(new google.maps.LatLng(20.68177501,
				// -103.3514794))
				// console.log(map.getCenter());
				//					
				// })
				// .catch(console.error);
				console.log(vm.selected);
				console.log("^^^^^^^vm.selected^^^^^^^");
			}
			
//		Message Stuff Below
			
			vm.showCreateMessage = true;
			
			vm.changeShowCreateMessageVariable = function() {
				vm.showCreateMessage = false;
			}
			
			vm.changeVariableToTrue = function() {
				vm.showCreateMessage = true;
			}
			
			vm.messages = [];
			
			
			vm.showMessages = function(caseId) {
				messageService.index(caseId)
				.then(function(response) {
				vm.messages = response.data
				
				})
			}
			
			vm.createMessage = function(caseId, message) {
				message.createDate = new Date();
				messageService.create(caseId, message)
				.then(function(response) {
					vm.messages.push(response.data);
					console.log(response.data);
					vm.showMessages(caseId);
				})
			}
			
			vm.checkLogin = function() {
				if (authService.getToken().id) {
					return true;
					console.log("inside of check login: "  + vm.showLogin);
				}
				return false;
			}
			
	
		},
		controllerAs : "vm"
});