(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService', '$localStorage'];

    function NavbarController ($state, Auth, Principal, ProfileService, LoginService, $localStorage) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isMenuCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });

        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.toggleMenu = toggleMenu;
        vm.collapseMenu = collapseMenu;
        vm.$state = $state;
        vm.$storage = $localStorage;

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
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

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
