(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('RecommendController', RecommendController);

    RecommendController.$inject = ['Recommend'];

    function RecommendController(Recommend) {
        var vm = this;

        vm.recommends = [];

        loadAll();

        function loadAll() {
            Recommend.query(function(result) {
                vm.recommends = result;
                vm.searchQuery = null;
            });
        }
    }
})();
