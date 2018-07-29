//set fly postion
function fly(flightMarker, startPosition, endPosition) {
    var fps = 60;
    var flyTime = 1000;
    var step = flyTime / 1000 * fps;
    var xStep = (endPosition.lat() - startPosition.lat()) / step;
    var yStep = (endPosition.lng() - startPosition.lng()) / step;
    var x = markers[0].getPosition().lat();
    var y = markers[0].getPosition().lng();
    for (var i = 1; i <= step; i++) {
        window.setTimeout(function () {
            x += xStep;
            y += yStep;
            flightMarker.setPosition({lat: x, lng: y})
            console.log(markers[0].getPosition().lat());
        }, (flyTime / step) * i);
    }
}