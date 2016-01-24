(function(){
    'use strict';
    angular
        .module('rrs')
        .controller('ViewTablesController', ViewTablesController);

    function ViewTablesController(dataService){
        var table = this;
        table.info = null;
        dataService
            .getTableData()
            .then(function(data){
                table.info = data;
            }, function(error){
                console.error(error);
            });
    }
})();