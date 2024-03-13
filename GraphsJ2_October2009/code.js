function onDefaultSelected() {
    var launchLink = getLaunchLink();
    launchLink.href = "dist/launch.jnlp";
}

function onNimbusSelected() {
    var launchLink = getLaunchLink()
    launchLink.href = "dist/nimbusLaunch.jnlp";
}

function getLaunchLink() {
    return document.getElementById("launchLink");
}