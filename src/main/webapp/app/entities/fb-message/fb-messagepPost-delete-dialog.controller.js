(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbMessagePPostDeleteController',FbMessagePPostDeleteController);

    FbMessagePPostDeleteController.$inject = ['$uibModalInstance', 'entity', 'FbMessage'];

    function FbMessagePPostDeleteController($uibModalInstance, entity, FbMessage) {
        var vm = this;

        vm.fbMessage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FbMessage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
