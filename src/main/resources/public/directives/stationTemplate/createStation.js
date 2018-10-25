app.directive("createStation",function(){
    return {
        restrict: 'EA',
        templateUrl: "/directives/stationTemplate/createStation.html",
        controller: ['$scope', 'stationService', function($scope, stationService){
            //Cache will be deleted, the moment the post is sended.
            $scope.stationCreateCache = []

            $scope.createStation = function(){
                var createStation = {};
                createStation.name = $scope.stationName;
                createStation.positionX = $scope.stationPositionX;
                createStation.positionZ = $scope.stationPositionZ;

                $scope.stationCreateCache.push(createStation);
                //clear input
                $scope.clearInputFields();
                //pseudocall for now. later the we´re able to add a bundle of stations and send them as a single request
                $scope.addStationToDataBase();
            }


            $scope.clearInputFields = function(){
                $scope.stationName = "";
                $scope.stationPositionX = "";
                $scope.stationPositionZ = "";
            };

            $scope.addStationToDataBase = function(){
                stationService.addStationToDataBase($scope.stationCreateCache);
                $scope.stationCreateCache = [];
            }

            $scope.disableCreateButton = false;

            $scope.checkName = function(){
                $scope.stationsInSystem = stationService.getStationInSystem();
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