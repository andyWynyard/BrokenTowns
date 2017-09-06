angular.module("appModule")
	.filter("municipalityCaseItemFilter", function() {
		return function(caseItems, municipalityId) {
			var results = [];
			
			caseItems.forEach(function(val) {
				if (val.municipalityId == municipalityId) {
					results.push(val);
				};
			});
			
			return results;
			
		}
		
	})