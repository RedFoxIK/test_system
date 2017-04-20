function sendTestForms() {
    alert('ok');
    var tests = document.getElementsByClassName("question");
    var size = tests.length;

    for ( var i = 0; i < size; i++ ) {
        tests[i].submit();
    }
};

function showTest(id) {
    document.getElementById("id_test").value = id;
    document.getElementById('hidden_form').submit();
};




