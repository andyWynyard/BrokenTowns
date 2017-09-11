angular.module('appModule')
	.component('search', {
		templateUrl  :  "app/appModule/search.component.html",
		controller   : function(uploadService, messageService, caseItemService, authService, NgMap, $scope, municipalityService) {

			var vm = this;
			
			vm.map = null;
			
			vm.marker = new google.maps.LatLng(20.68177501,103.3514794);
			
			vm.municipality = false;
			vm.caseItem = true;
			
			vm.searchTab = function(word) {
				if (vm.caseItem == null && vm.municipality == null) {
					vm.caseItem = true;
					vm.municipality = false;
					return;
				}
				if (word == 'case') {
					vm.caseItem = true;
					vm.municipality = false;
					return;
				}
				if (word == 'munic') {
					vm.municipality = true;
					vm.caseItem = false;
					return;
				}
			}
			
			vm.municipalities = null;

			vm.file = {};

			vm.upload = function(imgData) {
				console.log(vm.file)
				uploadService.upload(imgData, vm.file)
				.then((r)=>{
					console.log(r);
					return r;
				});
			}
			
			
			
			
			vm.loadAllMunicipalities = function() {
				municipalityService.index()
					.then(function(results) {
						vm.municipalities = results.data;
					});
			}
			vm.loadAllMunicipalities();
			
			vm.selectedLatLong = {};
			
			var userId = authService.getToken().id;
			
			vm.searchResults = [];
			vm.municipalitySearchResults = [];
			
			vm.loadAllMunicipalities = function() {
				municipalityService.index()
					.then(function(res) {
						vm.municipalitySearchResults = res.data;
					});
			}
			vm.loadAllCases = function() {
				caseItemService.index()
				.then(function(res) {
					vm.searchResults = res.data;
				});
			}
			vm.loadAllCases();
			vm.loadAllMunicipalities();
			
			vm.selected = null;
			
			vm.selectedMunicipality = null;
			
			vm.create = function(newCase) {
				newCase.userId = userId;
				console.log(newCase.userId);
				console.log(newCase.latLong);
				newCase.latitude = newCase.latLong.lat();
				newCase.longitude = newCase.latLong.lng();
				console.log(newCase.latitude);
				console.log(newCase.longitude);
				delete newCase.latLong;
				caseItemService.create(newCase)
					.then(function(res) {
						vm.upload({caseItemId : res.data.id, userId : userId})
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
			
			vm.mapKey = "AIzaSyAM7sMRVwpJLTHY4KScoaPnpnjlZDRH3xg";

			vm.applySelected = function(caseItem) {
				
			
				vm.selected = caseItem;
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
				console.log("BANANANANAANANA")
			}
			
			vm.getLocation = function() {
				NgMap.getMap("createMap").then(
						function(map) {
							vm.map = map;
							google.maps.event.trigger(vm.map, "resize");
							vm.map.setCenter(navigator.geolocation.getCurrentPosition(function(position){
								console.log(position);
								position.latitude,position.longitude;
									}
									));
							vm.map.setZoom(15);
						});
			}
			
			vm.addMarker = function() {
				google.maps.event.addListener(vm.map, 'click', function(event) {
					placeMarker(event.latLng);
					vm.marker = event.latLng;
					console.log("MARKER: " + vm.marker);
				});
				var marker;
				function placeMarker(location) {
					if (marker) {
						marker.setPosition(location);
					} else {
						marker = new google.maps.Marker({
						position: location,
						map: vm.map,
						animation: google.maps.Animation.DROP
					});
					}
					vm.map.setCenter(location);
			}

			}
			
//			message stuff below
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
			
		},
		controllerAs : "vm"
	})
