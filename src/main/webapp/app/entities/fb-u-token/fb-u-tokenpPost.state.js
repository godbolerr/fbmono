(function() {
    'use strict';

    angular
        .module('monolithApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fb-u-token-p-post', {
            parent: 'entity',
            url: '/fb-u-token-p-post',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FbUTokens'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fb-u-token/fb-u-tokenspPost.html',
                    controller: 'FbUTokenPPostController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('fb-u-token-p-post-detail', {
            parent: 'fb-u-token-p-post',
            url: '/fb-u-token-p-post/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FbUToken'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fb-u-token/fb-u-tokenpPost-detail.html',
                    controller: 'FbUTokenPPostDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'FbUToken', function($stateParams, FbUToken) {
                    return FbUToken.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fb-u-token-p-post',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fb-u-token-p-post-detail.edit', {
            parent: 'fb-u-token-p-post-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-u-token/fb-u-tokenpPost-dialog.html',
                    controller: 'FbUTokenPPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FbUToken', function(FbUToken) {
                            return FbUToken.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fb-u-token-p-post.new', {
            parent: 'fb-u-token-p-post',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-u-token/fb-u-tokenpPost-dialog.html',
                    controller: 'FbUTokenPPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userid: null,
                                uToken: null,
                                createdOn: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fb-u-token-p-post', null, { reload: 'fb-u-token-p-post' });
                }, function() {
                    $state.go('fb-u-token-p-post');
                });
            }]
        })
        .state('fb-u-token-p-post.edit', {
            parent: 'fb-u-token-p-post',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-u-token/fb-u-tokenpPost-dialog.html',
                    controller: 'FbUTokenPPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FbUToken', function(FbUToken) {
                            return FbUToken.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fb-u-token-p-post', null, { reload: 'fb-u-token-p-post' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fb-u-token-p-post.delete', {
            parent: 'fb-u-token-p-post',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-u-token/fb-u-tokenpPost-delete-dialog.html',
                    controller: 'FbUTokenPPostDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FbUToken', function(FbUToken) {
                            return FbUToken.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fb-u-token-p-post', null, { reload: 'fb-u-token-p-post' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
