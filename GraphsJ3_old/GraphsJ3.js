window.onload = function() {
    var runButton = document.getElementById("runButton");

    runButton.onclick = function() {
        dtjava.launch(
            {
                url: 'fxDist/GraphsJ_3.jnlp'
            },
        
            {
                javafx: '2.2+'
            },

            {}
        );

    };
};