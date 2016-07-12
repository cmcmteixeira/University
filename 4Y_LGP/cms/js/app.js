var constants ={};
constants.apiUrl = 'api.lgp.dev';

var app = angular.module('arbanking', [
        'ngRoute',
        'arbanking-controllers',
        'arbanking-services',
        'ngFileUpload',
    ]
);

app.constant('parameters',constants);
app.config(function ($httpProvider) {
    $httpProvider.defaults.transformRequest = function(data){
        if (data === undefined) {
            return data;
        }
        return $.param(data);
    };
    $httpProvider.defaults.headers.post = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

});

app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider
            .when('/app/:name', {
                controller: 'channelCtrl',
                templateUrl: 'partials/channel.html'
            })
            .when('/app/:name/trackable/new',{
                controller: 'newTrackableCtrl',
                templateUrl: 'partials/new_trackable.html'
            })
            .when('/app/:name/scene/new',{
                controller: 'newSceneCtrl',
                templateUrl: 'partials/new_scene.html'
            })
            .when('/app',{
                controller: 'newChannelCtrl',
                templateUrl:'partials/new_channel.html'
            })
            .when('/admin',{
                controller: 'adminCtrl',
                templateUrl:'partials/admin.html'
            })
            .when('/login', {
                controller: 'authCtrl',
                templateUrl: 'partials/login.html'
            })
            .otherwise({
                controller: 'defaultCtrl',
                templateUrl:'partials/redirecting.html'
            })
    }
]);

