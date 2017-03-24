(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('OrderlistController', OrderlistController);

    OrderlistController.$inject = ['Orderlist'];

    function OrderlistController(Orderlist) {
        var vm = this;

        vm.orderlists = [];

        loadAll();

        function loadAll() {
            Orderlist.query(function(result) {
                vm.orderlists = result;
                vm.searchQuery = null;
            });
        }
    }
})();
