(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .filter('priceFilter', priceFilter)
        .filter('horsePowerFilter', horsePowerFilter);

    function priceFilter() {
        return function(items, range) {
            var filtered = [];
            angular.forEach(items, function(item) {
                if (range.min <= item.price && item.price <= range.max)
                    filtered.push(item);
            });
            return filtered;
        };
    }

    function horsePowerFilter() {
        return function (items, range) {
            var filtered = [];
            angular.forEach(items, function(item) {
                if (range.min <= item.emp && item.emp <= range.max)
                    filtered.push(item);
            });
            return filtered;
        };
    }

})();
