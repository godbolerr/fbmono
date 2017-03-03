(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbUTokenPPostDeleteController',FbUTokenPPostDeleteController);

    FbUTokenPPostDeleteController.$inject = ['$uibModalInstance', 'entity', 'FbUToken'];

    function FbUTokenPPostDeleteController($uibModalInstance, entity, FbUToken) {
        var vm = this;

        vm.fbUToken = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FbUToken.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
