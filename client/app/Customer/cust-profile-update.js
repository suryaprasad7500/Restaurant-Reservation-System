(function(){
    'use strict';
    angular
        .module('rrs')
        .controller('CustProUpdateController', CustProUpdateController);

    CustProUpdateController.$inject = ['dataService'];
    function CustProUpdateController(dataService){
        var custUpdateModel = this;

        custUpdateModel.updateCust = function(resObj, email){
            var updateModel = this;
            updateModel.info = resObj;
            dataService
                .updateCustomer(resObj, email);
        }
    }
})();