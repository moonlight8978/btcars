(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CompanyContentController', CompanyContentController);

    CompanyContentController.$inject = ['$scope', '$stateParams', 'cars', 'CarsService', 'getCarFactory', 'CartService', 'BuyService'];

    function CompanyContentController($scope, $stateParams, cars, CarsService, getCarFactory, CartService, BuyService) {
        var vm = this;

        vm.addItem = CartService.addItem;
        vm.buy = BuyService.buy;

        vm.range = CarsService.priceRange;
        vm.hpRange = CarsService.hpRange;
        vm.sort = CarsService.sort;

        $scope.$watch(CarsService.getSort, function (newValue, oldValue) {
            if ( newValue !== oldValue ) 
                vm.sort = CarsService.getSort();
        });

        vm.cars = cars;
        getCarFactory.priceWithCommas(vm.cars, true);

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }

})();
