(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('orderlist', {
            parent: 'entity',
            url: '/orderlist',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Orderlists'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/orderlist/orderlists.html',
                    controller: 'OrderlistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('orderlist-detail', {
            parent: 'orderlist',
            url: '/orderlist/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Orderlist'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/orderlist/orderlist-detail.html',
                    controller: 'OrderlistDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Orderlist', function($stateParams, Orderlist) {
                    return Orderlist.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'orderlist',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('orderlist-detail.edit', {
            parent: 'orderlist-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orderlist/orderlist-dialog.html',
                    controller: 'OrderlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Orderlist', function(Orderlist) {
                            return Orderlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('orderlist.new', {
            parent: 'orderlist',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orderlist/orderlist-dialog.html',
                    controller: 'OrderlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                ho: null,
                                ten: null,
                                address: null,
                                total: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('orderlist', null, { reload: 'orderlist' });
                }, function() {
                    $state.go('orderlist');
                });
            }]
        })
        .state('orderlist.edit', {
            parent: 'orderlist',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orderlist/orderlist-dialog.html',
                    controller: 'OrderlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Orderlist', function(Orderlist) {
                            return Orderlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('orderlist', null, { reload: 'orderlist' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('orderlist.delete', {
            parent: 'orderlist',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orderlist/orderlist-delete-dialog.html',
                    controller: 'OrderlistDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Orderlist', function(Orderlist) {
                            return Orderlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('orderlist', null, { reload: 'orderlist' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
