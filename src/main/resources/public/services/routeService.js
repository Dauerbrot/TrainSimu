app.service('routeService',['$http', 'stationService', function($http, stationService){
    var routeMap = {};
    var routeMapMesh = {};

    this.getRouteResponse = function(){
        $http.get(serviceAddress + 'route').then(function(response){
            this.route = response.data;
            //later an array, now just a single Object
            var routeName = this.route.routeName;
            //store route in Map
            routeMap[routeName] = this.route;

            console.log(this.route);
            //get All registred Meshes
            var stationMeshes = stationService.getStationMeshes();
            //get start and Endpoint
            var startStation = stationMeshes[this.route.startStation.name];
            var endStation = stationMeshes[this.route.endStation.name];
            //create routeMesh
            var routeMesh = createRoute(startStation, endStation);
            removeItemFromScene(routeMesh);
            addItemToScene(routeMesh);

            //store routeMesh
            routeMapMesh[routeName] = routeMesh;
        })
    };

    this.simulateRoute = function(){
        $http.get(serviceAddress + 'simRoute').then(function(response){
            this.route = response.data;
            console.log(this.route);
        })
    };

}]);