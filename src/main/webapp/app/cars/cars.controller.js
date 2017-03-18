(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('carsController', carsController);

    carsController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'getCarFactory', 'all', 'hot', 'CartService'];

    function carsController($scope, Principal, LoginService, $state, getCarFactory, all, hot, CartService) {
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

        $scope.hots = hot.data;
        getCarFactory.priceWithCommas($scope.hots, true);

        $scope.all = all.data;
        getCarFactory.priceWithCommas($scope.all, true);

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
