(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('recommend', {
            parent: 'entity',
            url: '/recommend',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Recommends'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/recommend/recommends.html',
                    controller: 'RecommendController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('recommend-detail', {
            parent: 'recommend',
            url: '/recommend/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Recommend'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/recommend/recommend-detail.html',
                    controller: 'RecommendDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Recommend', function($stateParams, Recommend) {
                    return Recommend.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'recommend',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('recommend-detail.edit', {
            parent: 'recommend-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/recommend/recommend-dialog.html',
                    controller: 'RecommendDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Recommend', function(Recommend) {
                            return Recommend.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('recommend.new', {
            parent: 'recommend',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/recommend/recommend-dialog.html',
                    controller: 'RecommendDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('recommend', null, { reload: 'recommend' });
                }, function() {
                    $state.go('recommend');
                });
            }]
        })
        .state('recommend.edit', {
            parent: 'recommend',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/recommend/recommend-dialog.html',
                    controller: 'RecommendDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Recommend', function(Recommend) {
                            return Recommend.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('recommend', null, { reload: 'recommend' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('recommend.delete', {
            parent: 'recommend',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/recommend/recommend-delete-dialog.html',
                    controller: 'RecommendDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Recommend', function(Recommend) {
                            return Recommend.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('recommend', null, { reload: 'recommend' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
