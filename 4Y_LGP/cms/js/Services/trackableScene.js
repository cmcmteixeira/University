var newsApp = angular.module('arbanking-services');

newsApp.factory('$trackableScene',['$http','$constants', function($http,$constants) {

    var Promise=function(){
        var _success;
        var _error;

        this.success = function(cb){
            _success = cb;
            return this;
        };
        this.error = function(cb){
            _error = cb;
            return this;
        };

        this._callSuccess = function(){return _success;};
        this._callFailure = function(){console.log("Failure");return _error};
    };

    return {
        linkSceneToTrackable: function(scene,trackable,channel){
            var _promise = new Promise();
            $http({
                url : $constants.getUrl('/channel/'+channel+'/scene/link'),
                data: {scene:scene.id,trackable:trackable.id},
                method: 'POST',
            }).success(function(){
                _promise._callSuccess()(arguments);
            }).error(function(){
                _promise._callSuccess()(arguments);
            });

            return _promise;
        }

    };
}]);