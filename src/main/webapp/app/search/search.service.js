(function () {
	'use strict';

	angular
		.module('btcarsApp')
		.factory('Search', Search);

	function Search() {
		var search = {
			searchQuery: ''
		};

		return search;
	}

})();