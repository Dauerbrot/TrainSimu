app.controller("myCtrl", function($scope, $http) {
    var address = "http://localhost";
    var port = ":8080/";
    var serviceAddress = address+port;
    $scope.serviceAddress = address+port;
    $scope.firstName = "John";
    $scope.lastName= "Doe";

    /**
    activate the toggle function on the button, which allows us to activate or deactive the sidebar
    */
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
        });
    });

    $scope.showGraphic = true;
    /**
    purpose is to activate or deactivate the graphic field, so we can edit the informations from the train network
    */
    $scope.toggleGraphicField = function(){
        if($scope.showGraphic){
            $scope.showGraphic = false;
        }else{
            $scope.showGraphic = true;
        }
    }


    $scope.station;
    //collect all Stationdata in Map from Database
    $scope.stationMap = {};
    //collect all created meshs, which are in the scene
    $scope.stationMapMesh = {}
    $scope.filterStationByName = function(station){
        for(var i = 0; i < station.length; i++){
            var singleStation = station[i];
            var stationModel = createCube(singleStation.name, singleStation.positionX, singleStation.positionZ)

            removeItemFromScene(stationModel);
            addItemToScene(stationModel)
            $scope.stationMap[singleStation.name] = singleStation;
            $scope.stationMapMesh[singleStation.name] = stationModel;
        }
    }

    $scope.getStationResponse = function(){
        $http.get(serviceAddress + 'station').then(function(response){
            $scope.station = response.data;
            $scope.filterStationByName($scope.station);
        })
    }

    $scope.getStationResponse();

    $scope.deleteStation = function(stationName){
        var stationResult =  $scope.stationMapMesh[stationName];
        if(stationResult !== null){
            removeItemFromScene(stationResult);
        }
    }

});