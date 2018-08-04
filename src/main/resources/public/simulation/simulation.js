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
    document.body.appendChild( renderer.domElement );
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

function createCube(posX, posZ, color){
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

    return cubeMesh;
}

function createLine(){
    var material = new THREE.LineBasicMaterial( { color: 0x0000ff } );
    var geometry = new THREE.Geometry();
    geometry.vertices.push(new THREE.Vector3( -5, 1, 6) );
    geometry.vertices.push(new THREE.Vector3( 5, 1, -6) );

    //geometry.vertices.push(new THREE.Vector3( -10, 1, 0) );
    //geometry.vertices.push(new THREE.Vector3( 0, 10, 0) );
    //geometry.vertices.push(new THREE.Vector3( 10, 1, 0) );
    return new THREE.Line( geometry, material );
}

function init(){

    initScene();
    initRenderer();

    camera = new THREE.PerspectiveCamera( 60, window.innerWidth/window.innerHeight, 1, 1000 );
    camera.position.set( 40, 20, 0 );

    initControls();

    var cube = createCube(-5,6);
    scene.add( cube );

    var cube1 = createCube(5,-6);
    scene.add( cube1 );
    //line test
    var line = createLine();
    scene.add( line );

    // Grid
    var gridHelper = new THREE.GridHelper( 10, 100 );
    scene.add( gridHelper );


    // lights
    var light = new THREE.DirectionalLight( 0xffffff );
    light.position.set( 1, 1, 1 );
    scene.add( light );

    /** lights
    var light = new THREE.DirectionalLight( 0xffffff );
    light.position.set( 1, 1, 1 );
    scene.add( light );

    light = new THREE.DirectionalLight( 0x002288 );
    light.position.set( - 1, - 1, - 1 );
    scene.add( light );

    light = new THREE.AmbientLight( 0x222222 );
    scene.add( light );
    */
    //var light = new THREE.AmbientLight( 0x404040 ); // soft white light
    //scene.add( light );

    /**
    var geometry1 = new THREE.BoxGeometry( 1 , 1, 1 );
    var material1 = new THREE.MeshBasicMaterial( { color: 0x00ff22 } );
    var cube1 = new THREE.Mesh( geometry1, material1 );
    scene.add( cube1 );
    */
    //camera.position.z = 5;
    //controls.update();

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

    //cube.rotation.x += 0.01;
    //cube.rotation.y += 0.01;


    //cube1.rotation.x -= 0.01;
    //cube1.rotation.y -= 0.01;

    // required if controls.enableDamping or controls.autoRotate are set to true
	controls.update();

    renderer.render( scene, camera );
};

animate();