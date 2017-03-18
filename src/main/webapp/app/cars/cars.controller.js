(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CarsController', CarsController);

    CarsController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'getCarFactory', 'CartService'];

    function CarsController($scope, Principal, LoginService, $state, getCarFactory, CartService) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

        vm.hot = [];
        vm.all = [];
        vm.addItem = CartService.addItem;
        vm.sort = 'id';
        vm.range = {
            min: 0,
            max: 999999999
        };
        vm.setPriceFilter = setPriceFilter;
        vm.hpRange = {
            min: 0,
            max: 99999
        };
        vm.setHpFilter = setHpFilter;

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        getCarFactory.getHotCar().then(function (responseHot) {
            vm.hot = responseHot.data;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });
        getCarFactory.getAllCar().then(function (responseAll) {
            vm.all = responseAll.data;
            getCarFactory.priceWithCommas(vm.all, true);
        }, function (error) {
            console.log('Error while getting all cars!');
        });

        function setPriceFilter(min, max) {
            vm.range.min = min;
            vm.range.max = max;
        }
        function setHpFilter(min, max) {
            vm.hpRange.min = min;
            vm.hpRange.max = max;
        }

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
