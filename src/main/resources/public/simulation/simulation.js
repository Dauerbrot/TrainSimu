'use strict'

var camera;
var controls;
var renderer;
var scene;
var groundControl = 1;


function initScene(){
    scene = new THREE.Scene();
    scene.background = new THREE.Color( 0xcccccc );
    scene.fog = new THREE.FogExp2( 0xcccccc, 0.002 );
};

function initRenderer(){
    renderer = new THREE.WebGLRenderer( { antialias: true } );
    renderer.setPixelRatio( window.devicePixelRatio );
    renderer.setSize( window.innerWidth, window.innerHeight );
    var containerRender = document.getElementById('container');

    containerRender.appendChild( renderer.domElement );
}

function initControls(){
    controls = new THREE.OrbitControls( camera, renderer.domElement);
    controls.maxPolarAngle = Math.PI / 2;
    controls.enableDamping = true; // an animation loop is required when either damping or auto-rotation are enabled
    controls.dampingFactor = 0.25;
    controls.screenSpacePanning = false;
    controls.minDistance = 10;
    controls.maxDistance = 500;
}

function createCube(name, posX, posZ, color){
    var material = new THREE.MeshNormalMaterial();
    if(color){
        material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
    }
    var geometry = new THREE.BoxGeometry( 1 , 2, 3 );
    var cubeMesh = new THREE.Mesh( geometry, material );
    if(posX){
        cubeMesh.position.x = posX;
    }

    if(posZ){
        cubeMesh.position.z = posZ;
    }


    cubeMesh.position.y = groundControl;
    cubeMesh.name = name;

    return cubeMesh;
}

function createLine(){
    var material = new THREE.LineBasicMaterial( { color: 0x0000ff } );
    var geometry = new THREE.Geometry();
    geometry.vertices.push(new THREE.Vector3( -5, 1, 6) );
    geometry.vertices.push(new THREE.Vector3( 5, 1, -6) );
    return new THREE.Line( geometry, material );
}

function addItemToScene(item){
    scene.add(item);
}

/**
@param itemName is the Name of the Object in the scene
*/
function removeItemFromScene(item){
    scene.remove(item);
}

/**
*WILL BE DELETED!! Is only for the purpose to create some default
* Stations with one simple Route
*/
function initTestSceneWithStationAndLine(){
    var cube = createCube('margot', -5, 6);
    scene.add( cube );

    var cube1 = createCube('margot1', 5, -6);
    scene.add( cube1 );
    //line test
    var line = createLine();
    scene.add( line );
};

function init(){

    initScene();
    initRenderer();

    camera = new THREE.PerspectiveCamera( 60, window.innerWidth/window.innerHeight, 1, 1000 );
    camera.position.set( 40, 20, 0 );

    initControls();

    //initTestSceneWithStationAndLine();

    // Grid
    var gridHelper = new THREE.GridHelper( 10, 100 );
    scene.add( gridHelper );


    // lights
    var light = new THREE.DirectionalLight( 0xffffff );
    light.position.set( 1, 1, 1 );
    scene.add( light );

    window.addEventListener( 'resize', onWindowResize, false );
}

init();


function onWindowResize() {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize( window.innerWidth, window.innerHeight );

}

var animate = function () {
    requestAnimationFrame( animate );
	controls.update();

    renderer.render( scene, camera );
};

animate();