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

        factory.getCarById = getCarById;
        factory.getCarByCompany = getCarByCompany;
        factory.getHotCar = getHotCar;
        factory.getNewCar = getNewCar;
        factory.getRndCar = getRndCar;
        factory.getRecommendCar = getRecommendCar;
        factory.getAllCar = getAllCar;

        function getData(url) {
            return $http.get(url);
        }

        function getCarById(id) {
            return $http.get('api/cars/' + id);
        }
        function getCarByCompany(company) {
            return $http.get('api/cars/' + company);
        }
        function getHotCar() {
            return $http.get('api/cars/hot');
        }
        function getNewCar() {
            return $http.get('api/cars/new');
        }
        function getRndCar() {
            return $http.get('api/cars/random');
        }
        function getRecommendCar() {
            return $http.get('api/recommends');
        }
        function getAllCar() {
            return $http.get('api/cars/');
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
