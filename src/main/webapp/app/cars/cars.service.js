(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('CarsService', CarsService);

    function CarsService() {
        let service = {
            reset: reset,
            setPriceFilter: setPriceFilter,
            getPriceFilter: getPriceFilter,
            setHpFilter: setHpFilter,
            getHpFilter: getHpFilter,
            setSort: setSort,
            getSort: getSort,
            priceRange: {min: 0, max: 999999999},
            hpRange: {min: 0, max: 999999999},
            sort: 'id'
        };

        return service;

        function reset() {
            setPriceFilter(0, 999999999);
            setHpFilter(0,999999999);
            setSort('id');
        }

        function setHpFilter(min, max) {
            service.hpRange.min = min;
            service.hpRange.max = max;
        }

        function setPriceFilter(min, max) {
            service.priceRange.min = min;
            service.priceRange.max = max;
        }

        function getPriceFilter() {
            return service.priceRange;
        }

        function getHpFilter() {
            return service.hpRange;
        }

        function setSort(value) {
            service.sort = value;
        }

        function getSort() {
            return service.sort;
        }
        
    }

})();
