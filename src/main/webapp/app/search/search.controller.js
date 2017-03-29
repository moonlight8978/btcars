(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['searchQuery', 'CarSearch', 'Search'];

    function SearchController(searchQuery, CarSearch, Search) {
    	var vm = this;

    	vm.cars = [];
    	vm.searchQuery = searchQuery;

    	CarSearch.query({ query: Search.searchQuery }, function(result) {
            vm.cars = result;
            Search.searchQuery = '';
        });
    }

})();