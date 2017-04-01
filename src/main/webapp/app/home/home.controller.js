(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$localStorage', 'getCarFactory', 'CartService', 'BuyService', 'Recommend', 'Car', 'CurrentCustomer'];

    function HomeController ($scope, Principal, LoginService, $state, $localStorage, getCarFactory, CartService, BuyService, Recommend, Car, CurrentCustomer) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

        vm.buy = BuyService.buy;
        vm.addItem = CartService.addItem;
        vm.hot = [];
        vm.recommend = [];
        vm.random = [];
        vm.new = [];

        $localStorage.customer = {
            id: null,
            cars: [],
            user: null
        };

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                $localStorage.customer.user = account;
                if (vm.account !== null)
                    getCart();
            });
        }
        function register () {
            $state.go('register');
        }

        function getCart() {
            CurrentCustomer.get(function (result) {
                $localStorage.customer = result;
                getCarFactory.priceWithCommas($localStorage.customer.cars, true);
                $localStorage.total = getCarFactory.calTotalPrice($localStorage.customer.cars);
                $localStorage.totalFix = $localStorage.total.toLocaleString();
            }, function (error) {
                $localStorage.customer.cars = [];
                $localStorage.total = 0;
            });
        }

        Car.query({ category: 'hot' }, function (result) {
            vm.hot = result;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

        Car.query({ category: 'new' }, function (result) {
            vm.new = result;
            getCarFactory.priceWithCommas(vm.new, true);
        }, function (error) {
            console.log('Error while getting new cars!');
        });

        Car.query({ category: 'random' }, function (result) {
            vm.random = result;
            getCarFactory.priceWithCommas(vm.random, true);
        }, function (error) {
            console.log('Error while getting random cars!');
        });

        Recommend.query(function(result) {
            var recc = result,
                length = recc.length,
                i;
            for (i=0; i<length; i++)
                vm.recommend.push(recc[i].car);
            getCarFactory.priceWithCommas(vm.recommend, true);
        }, function (error) {
            console.log('Error while getting random cars!');
        });

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
