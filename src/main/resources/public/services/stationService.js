app.service('stationService',['$http', function($http){
    //collect all Stationdata in Map from Database
    var stationMap = {};
    //collect all created meshs, which are in the scene
    var stationMapMesh = {}

    var filterStationByName = function(station){
        for(var i = 0; i < station.length; i++){
            var singleStation = station[i];
            var stationModel = createCube(singleStation.name, singleStation.positionX, singleStation.positionZ)
            removeItemFromScene(stationModel);
            addItemToScene(stationModel)
            stationMap[singleStation.name] = singleStation;
            stationMapMesh[singleStation.name] = stationModel;
        }
    }


    this.addStationToDataBase = function(stationCreateCache){
        $http.post(serviceAddress + 'addStation', stationCreateCache).then(function(response){
            console.log(response);
            if('complete' === response.xhrStatus){
                getStationResponse();
            }
        });
    };

    this.getStationResponse = function(){
        $http.get(serviceAddress + 'station').then(function(response){
            this.station = response.data;
            filterStationByName(this.station);
        })
    };
    //set station as var, again. so the method can be used in the wrapped call in
    //addStationToDataBase or deleteStation
    var getStationResponse = this.getStationResponse;

    this.deleteStation = function(stationName){
        var stationResult = stationMapMesh[stationName];
        if(stationResult !== null){
            delete stationMapMesh[stationName];
            delete stationMap[stationName];
            removeItemFromScene(stationResult);
        }
    };

    /******************* GETTER **********************/
    this.getStationInSystem = function(){
        return stationMap;
    }

    this.getStationMeshes = function(){
        return stationMapMesh;
    }

}]);