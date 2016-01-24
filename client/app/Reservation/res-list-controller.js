(function(){
    'use strict';

    angular
        .module('rrs')
        .controller('ResListController', ResListController);

    ResListController.$inject = ['dataService'];

    function ResListController(dataService){
        var resListModel = this;
        resListModel.res = [];
        dataService
            .getReservations()
            .then(function(data){
                resListModel.res = data;
            }, function(error){
                console.log(error);
            });
    }
})();