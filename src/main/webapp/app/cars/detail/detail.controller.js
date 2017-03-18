(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('carController', carController);

    carController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$stateParams', 'hot', 'getCarFactory', 'CartService'];

    function carController($scope, Principal, LoginService, $state, $stateParams, hot, getCarFactory, CartService) {
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

        getCarFactory.getData('api/cars/' + $stateParams.id).then(function (car) {
            $scope.car = car.data;
            getCarFactory.priceWithCommas($scope.car, false);
        });


        $scope.hots = hot.data;
        getCarFactory.priceWithCommas($scope.hots, true);

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }

})();
