(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbpostPPostDeleteController',FbpostPPostDeleteController);

    FbpostPPostDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fbpost'];

    function FbpostPPostDeleteController($uibModalInstance, entity, Fbpost) {
        var vm = this;

        vm.fbpost = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fbpost.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
