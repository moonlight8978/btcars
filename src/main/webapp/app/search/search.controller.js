(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['searchQuery', 'CarSearch', 'Car', 'getCarFactory'];

    function SearchController(searchQuery, CarSearch, Car, getCarFactory) {
    	var vm = this;

    	vm.cars = [];
        vm.hot = [];
        vm.loading = true;

        vm.searchQuery = searchQuery;

        Car.query({ category: 'hot' }, function (result) {
            vm.hot = result;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

    	CarSearch.query({ query: searchQuery }, function(result) {
            vm.cars = result;
            getCarFactory.priceWithCommas(vm.cars, true);
            vm.loading = false;
        });
    }

})();
