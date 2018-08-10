app.directive("createStation",function(){


    return {
        restrict: 'EA',
        scope:{
            serviceAddress: '=',
            getStationResponse: '&',
            stationsInSystem: '='
        },
        templateUrl: "/directives/createStation.html",
        controller: ['$scope', '$http', function($scope, $http){
            console.log($scope);
            $scope.createStation = function(){
                var createStation = {};
                createStation.name = $scope.stationName;
                createStation.positionX = $scope.stationPositionX;
                createStation.positionZ = $scope.stationPositionZ;

                $scope.stationCreateCache.push(createStation);

                //pseudocall for now. later the we´re able to add a bundle of stations and send them as a single request
                $scope.addStationToDataBase();
            }

            //Cache will be deleted, the moment the post is sended.
            $scope.stationCreateCache = []

            $scope.addStationToDataBase = function(){
                $http.post($scope.serviceAddress + 'addStation', $scope.stationCreateCache).then(function(response){
                    console.log(response);
                    if('complete' === response.xhrStatus){
                        $scope.getStationResponse();
                    }
                });
                $scope.stationCreateCache = [];
            }

            $scope.disableCreateButton = false;

            $scope.checkName = function(){
                if( (angular.isUndefined($scope.stationName) || "" === $scope.stationName ) || $scope.stationsInSystem[$scope.stationName] != undefined){
                    $scope.disableCreateButton = true;
                    if(angular.isUndefined($scope.stationName) || "" === $scope.stationName){
                        $scope.errorMessage = '*Please type a name for the station!';
                    }else{
                        $scope.errorMessage = '*The station already exists in the system!'
                    }

                }else{
                    $scope.disableCreateButton = false;
                    $scope.errorMessage = '';
                }
            };
            $scope.checkName();
        }]
    }
})