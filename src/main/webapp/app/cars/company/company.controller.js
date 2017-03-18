(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('companyController', companyController);

    companyController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'cars', 'hot', 'getCarFactory', 'CartService'];

    function companyController($scope, Principal, LoginService, $state, cars, hot, getCarFactory, CartService) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

        vm.addItem = CartService.addItem;

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

        $scope.cars = cars.data;
        getCarFactory.priceWithCommas($scope.cars, true);

        $scope.hots = hot.data;
        getCarFactory.priceWithCommas($scope.hots, true);

        $scope.sort = 'id';

        $scope.range = {
            min: 0,
            max: 999999999
        };

        $scope.hpRange ={
            min: 0,
            max: 99999
        };


        $scope.setPriceFilter = function (min, max) {
            $scope.range.min = min;
            $scope.range.max = max;
        };

        $scope.setHpFilter = function (min, max) {
            $scope.hpRange.min = min;
            $scope.hpRange.max = max;
        };

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }

})();
