(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('getCarFactory', getCarFactory);

    getCarFactory.$inject = ['$http'];

    function getCarFactory($http) {
        var factory = {};

        factory.calTotalPrice = calTotalPrice;
        factory.priceWithCommas = priceWithCommas; // ex 1200 USD => 1.200 USD

        function priceWithCommas(array, isArray) {
            if (isArray) {
                angular.forEach(array, function(item) {
                    item.priceFix = item.price.toLocaleString();
                });
            }
            else {
                let object = array;
                object.priceFix = object.price.toLocaleString();
            }
        }

        function calTotalPrice(array) {
            var total = 0;
            angular.forEach(array, function(item) {
                total += item.price;
            });
            return total;
        }

        return factory;
    }
})();
