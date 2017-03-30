(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['searchQuery', 'CarSearch', 'Search', 'Car', 'getCarFactory'];

    function SearchController(searchQuery, CarSearch, Search, Car, getCarFactory) {
    	var vm = this;

    	vm.cars = [];
        vm.hot = [];
        vm.loading = true;

        vm.searchQuery = searchQuery;   //  will not reset after reset Search service

        Car.query({ category: 'hot' }, function (result) {
            vm.hot = result;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

    	CarSearch.query({ query: Search.searchQuery }, function(result) {
            vm.cars = result;
            getCarFactory.priceWithCommas(vm.cars, true);
            Search.searchQuery = '';    //  Reset searchQuery
            vm.loading = false;
        });
    }

})();