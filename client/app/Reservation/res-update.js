(function(){
    'use strict';
    angular
        .module('rrs')
        .controller('ResUpdateController', ResUpdateController);

    ResUpdateController.$inject = ['dataService'];

    function ResUpdateController(dataService){
        var resUpdateModel = this;

        resUpdateModel.updateRes = function (resObj, email) {
            dataService
                .updateReservation(resObj, email);
        }
    }
})();