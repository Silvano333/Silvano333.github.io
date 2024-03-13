window.onload = function() {
    var runButton = document.getElementById("runButton");

    runButton.onclick = function() {
        dtjava.launch(            {
                url : 'webstart/GraphsJ_3.jnlp'
            },
            {
                javafx : '8.0+'
            },
            {}
        );

    };
};
