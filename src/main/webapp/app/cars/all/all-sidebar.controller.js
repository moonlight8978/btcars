(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('AllSidebarController', AllSidebarController);

    AllSidebarController.$inject = ['getCarFactory', 'hot'];

    function AllSidebarController(getCarFactory, hot) {
        var vm = this;

        vm.hot = hot;
        getCarFactory.priceWithCommas(vm.hot, true);

    }

})();
