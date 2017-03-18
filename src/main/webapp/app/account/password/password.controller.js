(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('PasswordController', PasswordController);

    PasswordController.$inject = ['Auth', 'Principal', '$scope'];

    function PasswordController (Auth, Principal, $scope) {
        var vm = this;

        vm.changePassword = changePassword;
        vm.doNotMatch = null;
        vm.error = null;
        vm.success = null;

        $scope.show = false;

        $scope.$watch('show', function () {
            document.getElementById('password').type = $scope.show ? 'text' : 'password';
            document.getElementById('confirmPassword').type = $scope.show ? 'text' : 'password';
        });

        Principal.identity().then(function(account) {
            vm.account = account;
        });

        function changePassword () {
            if (vm.password !== vm.confirmPassword) {
                vm.error = null;
                vm.success = null;
                vm.doNotMatch = 'ERROR';
            } else {
                vm.doNotMatch = null;
                Auth.changePassword(vm.password).then(function () {
                    vm.error = null;
                    vm.success = 'OK';
                }).catch(function () {
                    vm.success = null;
                    vm.error = 'ERROR';
                });
            }
        }
    }
})();
