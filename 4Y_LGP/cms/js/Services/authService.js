/**
 * Created by cteixeira on 5/29/2015.
 */
var newsApp = angular.module('arbanking-services');

newsApp.factory('$auth', ['$constants',function($constants) {
    $url = $constants.getUrl('/login');

    return {
        isLogged : function(){

        },
        login:function(username,password){

        }
    }
}]);