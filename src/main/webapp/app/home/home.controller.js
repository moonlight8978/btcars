(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$localStorage', 'getCarFactory', 'CartService'];

    function HomeController ($scope, Principal, LoginService, $state, $localStorage, getCarFactory, CartService) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.addItem = CartService.addItem;
        vm.hot = [];
        vm.recommend = [];
        vm.random = [];
        vm.new = [];
        $localStorage.customer = {};
        $scope.$storage = $localStorage;

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                getCart(account.id);
            });
        }
        function register () {
            $state.go('register');
        }

        function getCart(id) {
            getCarFactory.getData('api/customers/user/' + id).then(function (customer) {
                $localStorage.customer = customer.data;
                getCarFactory.priceWithCommas($localStorage.customer.carts, true);
                $localStorage.total = getCarFactory.calTotalPrice($localStorage.customer.carts);
                $localStorage.totalFix = $localStorage.total.toLocaleString();
            }, function (error) {
                $localStorage.customer.carts = [];
                $localStorage.total = 0;
            });
        }

        getCarFactory.getHotCar().then(function (responseHot) {
            vm.hot = responseHot.data;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

        getCarFactory.getNewCar().then(function (responseNew) {
            vm.new = responseNew.data;
            getCarFactory.priceWithCommas(vm.new, true);
        }, function (error) {
            console.log('Error while getting new cars!');
        });

        getCarFactory.getRndCar().then(function (responseRandom) {
            vm.random = responseRandom.data;
            getCarFactory.priceWithCommas(vm.random, true);
        }, function (error) {
            console.log('Error while getting random cars!');
        });

        getCarFactory.getRecommendCar().then(function (responseRecommend) {
            var recc = responseRecommend.data,
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
