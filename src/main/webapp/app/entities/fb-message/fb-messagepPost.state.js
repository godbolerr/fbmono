(function() {
    'use strict';

    angular
        .module('monolithApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fb-message-p-post', {
            parent: 'entity',
            url: '/fb-message-p-post',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FbMessages'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fb-message/fb-messagespPost.html',
                    controller: 'FbMessagePPostController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('fb-message-p-post-detail', {
            parent: 'fb-message-p-post',
            url: '/fb-message-p-post/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FbMessage'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fb-message/fb-messagepPost-detail.html',
                    controller: 'FbMessagePPostDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'FbMessage', function($stateParams, FbMessage) {
                    return FbMessage.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fb-message-p-post',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fb-message-p-post-detail.edit', {
            parent: 'fb-message-p-post-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-message/fb-messagepPost-dialog.html',
                    controller: 'FbMessagePPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FbMessage', function(FbMessage) {
                            return FbMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fb-message-p-post.new', {
            parent: 'fb-message-p-post',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-message/fb-messagepPost-dialog.html',
                    controller: 'FbMessagePPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userid: null,
                                messageId: null,
                                replyMessage: null,
                                replyStatus: null,
                                createdOn: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fb-message-p-post', null, { reload: 'fb-message-p-post' });
                }, function() {
                    $state.go('fb-message-p-post');
                });
            }]
        })
        .state('fb-message-p-post.edit', {
            parent: 'fb-message-p-post',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-message/fb-messagepPost-dialog.html',
                    controller: 'FbMessagePPostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FbMessage', function(FbMessage) {
                            return FbMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fb-message-p-post', null, { reload: 'fb-message-p-post' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fb-message-p-post.delete', {
            parent: 'fb-message-p-post',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fb-message/fb-messagepPost-delete-dialog.html',
                    controller: 'FbMessagePPostDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FbMessage', function(FbMessage) {
                            return FbMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fb-message-p-post', null, { reload: 'fb-message-p-post' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
