angular.module('appModule')
	.component('search', {
		templateUrl  :  "app/appModule/search.component.html",
		controller   : function(caseItemService, authService, NgMap, $scope, municipalityService) {
			var vm = this;
			
			vm.map = null;
			
			vm.marker = null;
			
			vm.municipality = null;
			vm.caseItem = null;
			
			vm.searchTab = function() {
				if (vm.caseItem == null && vm.municipality == null) {
					vm.caseItem = true;
					vm.municipality = false;
					return;
				}
				if (vm.caseItem == true) {
					vm.caseItem = false;
					vm.municipality = true;
				} else {
					vm.caseItem = true;
					vm.municipality = false;
				}
			}
			
			vm.searchTab();
			
			vm.municipalities = null;
			
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
			
			vm.mapKey = "AIzaSyAM7sMRVwpJLTHY4KScoaPnpnjlZDRH3xg";

			vm.applySelected = function(caseItem) {
				console.log(caseItem);
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
			
			
		},
		controllerAs : "vm"
	})