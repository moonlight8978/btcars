(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('OrderlistController', OrderlistController);

    OrderlistController.$inject = ['Orderlist', 'OrderlistSearch'];

    function OrderlistController(Orderlist, OrderlistSearch) {
        var vm = this;

        vm.orderlists = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Orderlist.query(function(result) {
                vm.orderlists = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            OrderlistSearch.query({query: vm.searchQuery}, function(result) {
                vm.orderlists = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
