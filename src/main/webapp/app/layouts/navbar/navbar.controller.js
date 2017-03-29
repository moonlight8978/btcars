(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService', '$localStorage', 'CartService', 'Search'];

    function NavbarController ($state, Auth, Principal, ProfileService, LoginService, $localStorage, CartService, Search) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isMenuCollapsed = true;
        vm.isSearchEnable = false;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });

        vm.openCart = CartService.openCart;
        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.toggleMenu = toggleMenu;
        vm.collapseMenu = collapseMenu;
        vm.enableSearch = enableSearch;
        vm.disableSearch = disableSearch;
        vm.$state = $state;
        vm.$storage = $localStorage;

        vm.search = search;
        vm.searchQuery = '';

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $localStorage.$reset();
            $state.go('home');
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }

        function toggleMenu() {
            vm.isMenuCollapsed = !vm.isMenuCollapsed;
        }

        function collapseMenu() {
            vm.isMenuCollapsed = true;
        }

        function enableSearch() {
            vm.isSearchEnable = true;
        }

        function disableSearch() {
            vm.isSearchEnable = false;
        }

        function search() {
            Search.searchQuery = vm.searchQuery;
            $state.go('search', { q: Search.searchQuery });
        }

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
