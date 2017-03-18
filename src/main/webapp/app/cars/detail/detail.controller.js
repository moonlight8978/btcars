(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CarController', CarController);

    CarController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$stateParams', 'getCarFactory', 'CartService'];

    function CarController($scope, Principal, LoginService, $state, $stateParams, getCarFactory, CartService) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

        vm.addItem = CartService.addItem;
        vm.hot = [];
        vm.car = {};

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

        getCarFactory.getCarById($stateParams.id).then(function (responseCar) {
            vm.car = responseCar.data;
            getCarFactory.priceWithCommas(vm.car, false);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });
        getCarFactory.getHotCar().then(function (responseHot) {
            vm.hot = responseHot.data;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
