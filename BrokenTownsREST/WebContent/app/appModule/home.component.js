angular.module('appModule').component(
		'home',
		{
			templateUrl : 'app/appModule/home.component.html',
			controller : function($interval, authService) {
				var vm = this;

				vm.advertisingArray = [ "Report It!", "Get It Fixed!",
						"Keep Track Of Problems!",
						"One Problem, One Text, One Solution!" ];

				vm.checkLogin = function() {
					if (authService.getToken().id) {
						return true;
						console.log("inside of check login: " + vm.showLogin);
					}
					return false;
				}

				var idx = 0;
				vm.selected = "";

				$interval(function() {
					if(vm.checkLogin() === false) {
						vm.selected = vm.advertisingArray[idx];
						idx++;
						
						if(idx == vm.advertisingArray.length) {
							idx = 0;
							
						}
					}
				},

//				The below sets the timeout, the first number is the interval, the second number is how many times it fires
				1200, 70

				);

			},
			controllerAs : 'vm'
		})