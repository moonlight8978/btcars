(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('AllController', AllController);

    AllController.$inject = ['$scope', 'getCarFactory', 'cars', 'CartService', 'BuyService', 'CarsService'];

    function AllController($scope, getCarFactory, cars, CartService, BuyService, CarsService) {
        var vm = this;

        vm.all = cars;
        getCarFactory.priceWithCommas(vm.all, true);

        vm.addItem = CartService.addItem;
        vm.buy = BuyService.buy;
        
        vm.range = CarsService.priceRange;
        vm.hpRange = CarsService.hpRange;
        vm.sort = CarsService.getSort();

        $scope.$watch(CarsService.getSort, function (newValue, oldValue) {
            if ( newValue !== oldValue ) 
                vm.sort = CarsService.getSort();
        });

        $(document).ready(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });
    }

})();
