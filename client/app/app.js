(function(){
    angular.module('rrs', ['ngRoute']).config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];
    function moduleConfig($routeProvider){
        $routeProvider
            .when('/customer_home/:email', {
                templateUrl: 'app/Customer/home.tmpl.html',
                controller: 'ResSingleController',
                controllerAs: 'resSingleModel'
            })
            .when('/customer_res/:email', {
             templateUrl: 'app/Customer/edit.tmpl.html',
             controller: 'ResSingleController',
             controllerAs: 'resSingleModel'
             })
            .when('/customer_create', {
                templateUrl: 'app/Customer/create.tmpl.html'
            })
            .when('/customer_profile/:email',{
                templateUrl: 'app/Customer/settings.tmpl.html',
                controller: 'CustSingleController',
                controllerAs: 'custSingle'
            })
            .when('/login', {
                templateUrl: 'app/login.tmpl.html'
            })
            .when('/owner_home', {
                templateUrl: 'app/Owner/home.tmpl.html',
                controller: 'ResListController',
                controllerAs: 'resListModel'
            })
            .when('/owner_edit/:email',{
                templateUrl : 'app/Owner/edi.tmpl.html',
                controller: 'ResSingleController',
                controllerAs: 'resSingleModel'
            })
            .when('/owner_settings',{
                templateUrl : 'app/Owner/settings.tmpl.html',
                controller: 'OwnerDetails',
                controllerAs: 'ownerDetails'
            })
            .when('/owner_create',{
                templateUrl: 'app/Owner/create.tmpl.html'
            })
            .when('/owner_seating', {
                templateUrl: 'app/Seating/view_seating.tmpl.html',
                controller: 'ViewTablesController',
                controllerAs: 'tableInfo'
            })
            .when('/owner_contacts', {
                templateUrl: 'app/Owner/contacts.tmpl.html',
                controller: 'ViewCustomers',
                controllerAs: 'custList'
            })
            .otherwise({
                redirectTo: '/login'
            })
    }
})();