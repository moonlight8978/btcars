(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('SearchSidebarController', SearchSidebarController);

    SearchSidebarController.$inject = ['Car', 'getCarFactory'];

    function SearchSidebarController(Car, getCarFactory) {
    	var vm = this;

        vm.hot = [];
        Car.query({ category: 'hot' }, function (result) {
            vm.hot = result;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });
    }
    
})();
