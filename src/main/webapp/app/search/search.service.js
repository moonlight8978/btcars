(function () {
	'use strict';

	angular
		.module('btcarsApp')
		.factory('Search', Search);

	function Search() {
		var search = {
			/* 	Store the searchQuery from navbar
				Then pass to search controller 	*/
			searchQuery: ''
		};

		return search;
	}

})();