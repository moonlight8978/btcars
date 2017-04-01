(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('CurrentCustomer', CurrentCustomer);

    CurrentCustomer.$inject = ['$resource'];

    function CurrentCustomer ($resource) {
        var service = $resource('api/customers/user', {}, {
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            }
        });

        return service;
    }
})();
