angular.module('appModule').component(
		'user',
		{
			templateUrl : 'app/appModule/user.component.html',
			controller : function($location, authService, caseItemService,
					userService, messageService, $scope, NgMap) {
				var vm = this;

				vm.map = null;

				var userId = authService.getToken().id;
				vm.user = null;
				var setUser = function() {
					userService.show(userId).then(function(res) {
						vm.user = res.data;
						console.log("USER NAME = " + vm.user.firstName)
					})
				}
				setUser();

				vm.caseItems = [];

				vm.loadCaseItems = function() {
					caseItemService.index().then(function(result) {
						console.log("USER ID: " + userId);
						var temp = result.data;
						temp.forEach(function(val) {
							console.log(val);
							console.log(val.user.id);
							if (val.user.id == userId) {
								vm.caseItems.push(val);
							}
						})
						console.log(vm.caseItems)
					})
				}

				vm.loadCaseItems();

				vm.getNumCaseItems = function() {
					return vm.caseItems.length;
				}

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

				vm.applySelected = function(caseItem) {
					vm.selected = caseItem;
					NgMap.getMap("map").then(
							function(map) {
								vm.map = map;
								google.maps.event.trigger(vm.map, "resize");
								vm.map.setCenter(new google.maps.LatLng(
										vm.selected.latitude,vm.selected.longitude
//										20.68177501,-103.3514794
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
					console.log("BANANANANAANANA")
				}

			},

			controllerAs : 'vm'

		})
