(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('SearchContentController', SearchContentController);

    SearchContentController.$inject = ['searchQuery', 'CarSearch', 'getCarFactory'];

    function SearchContentController(searchQuery, CarSearch, getCarFactory) {
    	var vm = this;

    	vm.cars = [];

        vm.loading = true;

        vm.searchQuery = searchQuery;

    	CarSearch.query({ query: searchQuery }, function(result) {
            vm.cars = result;
            getCarFactory.priceWithCommas(vm.cars, true);
            vm.loading = false;
        });
    }

})();
