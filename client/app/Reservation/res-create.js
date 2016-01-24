(function(){
    'use strict';

    angular
        .module('rrs')
        .controller('ResCreateController', ResCreateController);

    ResCreateController.$inject = ['dataService'];

    function ResCreateController(dataService){
        var createRes = this;

        createRes.addReservation = function(resObj){
            var createVm = this;
            createVm.info = resObj;
            dataService
                .createReservation(resObj);
            console.log("ResCreate up and running!");
        }
    }
})();