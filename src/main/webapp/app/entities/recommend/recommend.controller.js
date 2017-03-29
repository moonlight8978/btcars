(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('RecommendController', RecommendController);

    RecommendController.$inject = ['Recommend', 'RecommendSearch'];

    function RecommendController(Recommend, RecommendSearch) {
        var vm = this;

        vm.recommends = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Recommend.query(function(result) {
                vm.recommends = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            RecommendSearch.query({query: vm.searchQuery}, function(result) {
                vm.recommends = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
