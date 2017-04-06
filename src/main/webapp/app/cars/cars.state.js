(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('cars', {
            parent: 'app',
            abtract: true,
            url: '/cars',
            resolve: {
                hot: function (Car, $q) {
                    var hot = Car.query({ category: 'hot' });
                    return $q.resolve(hot.$promise).then(function (result) {
                        return result;
                    });
                }
            }
        });
    }

})();
