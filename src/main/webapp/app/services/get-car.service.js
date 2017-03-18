(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('getCarFactory', getCarFactory);

    getCarFactory.$inject = ['$http'];

    function getCarFactory($http) {
        var factory = {};

        factory.getData = getData;
        factory.calTotalPrice = calTotalPrice;
        factory.priceWithCommas = priceWithCommas; // ex 1200 USD => 1.200 USD

        function getData(url) {
            return $http.get(url);
        }

        function priceWithCommas(array, isArray) {
            if (isArray) {
                for (var i = 0; i < array.length; i++)
                    array[i].priceFix = array[i].price.toLocaleString();
            }
            else {
                var object = array;
                object.priceFix = object.price.toLocaleString();
            }
        }

        function calTotalPrice(array) {
            let total = 0;
            for (var i = 0; i < array.length; i++)
                total += array[i].price;
            return total;
        }

        return factory;
    }
})();
