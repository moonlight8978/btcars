(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('DetailsSidebarController', DetailsSidebarController);

    DetailsSidebarController.$inject = ['getCarFactory', 'hot'];

    function DetailsSidebarController(getCarFactory, hot) {
        var vm = this;

        vm.hot = hot;
        getCarFactory.priceWithCommas(vm.hot, true);

    }
})();
