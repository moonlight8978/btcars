(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$localStorage', 'getCarFactory', 'CartService', 'hot', 'news', 'random'];

    function HomeController ($scope, Principal, LoginService, $state, $localStorage, getCarFactory, CartService, hot, news, random) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.addItem = CartService.addItem;

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

        $scope.hots = hot.data;
        getCarFactory.priceWithCommas($scope.hots, true);

        $scope.news = news.data;
        getCarFactory.priceWithCommas($scope.news, true);

        $scope.random = random.data;
        getCarFactory.priceWithCommas($scope.random, true);

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
