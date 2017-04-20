window.addEventListener('keydown', function (event) {
    if (event.keyCode === 7) {
        event.preventDefault();
        return false;
    }
});

function showTest(id) {
    document.getElementById("id_test").value = id;
    document.getElementById('hidden_form').submit();
};


