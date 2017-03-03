(function() {
    'use strict';

    angular
        .module('monolithApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fbpost-p-post', {
            parent: 'entity',
            url: '/fbpost-p-post?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fbposts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fbpost/fbpostspPost.html',
                    controller: 'FbpostPPostController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('fbpost-p-post-detail', {
            parent: 'fbpost-p-post',
            url: '/fbpost-p-post/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fbpost'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fbpost/fbpostpPost-detail.html',
                    controller: 'FbpostPPostDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Fbpost', function($stateParams, Fbpost) {
                    return Fbpost.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fbpost-p-post',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fbpost-p-post-detail.edit', {
            parent: 'fbpost-p-post-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fbpost/fbpostpPost-dialog.html',
                    controller: 'FbpostPPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fbpost', function(Fbpost) {
                            return Fbpost.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fbpost-p-post.new', {
            parent: 'fbpost-p-post',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fbpost/fbpostpPost-dialog.html',
                    controller: 'FbpostPPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userId: null,
                                url: null,
                                description: null,
                                name: null,
                                caption: null,
                                place: null,
                                createdOn: null,
                                createdBy: null,
                                objectId: null,
                                privacy: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fbpost-p-post', null, { reload: 'fbpost-p-post' });
                }, function() {
                    $state.go('fbpost-p-post');
                });
            }]
        })
        .state('fbpost-p-post.edit', {
            parent: 'fbpost-p-post',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fbpost/fbpostpPost-dialog.html',
                    controller: 'FbpostPPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fbpost', function(Fbpost) {
                            return Fbpost.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fbpost-p-post', null, { reload: 'fbpost-p-post' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fbpost-p-post.delete', {
            parent: 'fbpost-p-post',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fbpost/fbpostpPost-delete-dialog.html',
                    controller: 'FbpostPPostDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fbpost', function(Fbpost) {
                            return Fbpost.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fbpost-p-post', null, { reload: 'fbpost-p-post' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
