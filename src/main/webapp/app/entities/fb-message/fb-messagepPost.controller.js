(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbMessagePPostController', FbMessagePPostController);

    FbMessagePPostController.$inject = ['FbMessage'];

    function FbMessagePPostController(FbMessage) {

        var vm = this;

        vm.fbMessages = [];

        loadAll();

        function loadAll() {
            FbMessage.query(function(result) {
                vm.fbMessages = result;
                vm.searchQuery = null;
            });
        }
    }
})();
