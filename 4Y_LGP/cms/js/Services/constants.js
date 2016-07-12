var newsApp = angular.module('arbanking-services');

newsApp.factory('$constants', function() {

    var domain = document.domain.replace('')
    var baseUrl = 'http://www.api.'+document.domain.replace(/www\./,'');
    // factory function body that constructs shinyNewServiceInstance
    return {
        getUrl: function(relativeUrl){
            return baseUrl+relativeUrl;
        }

    };
});