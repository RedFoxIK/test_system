function showTest(id) {
    document.getElementById("id_test").value = id;
    document.getElementById("hidden_form").submit();
};

function showDiv() {
    document.getElementById("new_test").style.display = "block";
    document.getElementById("add_button").style.visibility = "hidden";
};

function hideDiv() {
    document.getElementById("new_test").style.display = "none";
    document.getElementById("add_button").style.visibility = "visible";
};

function deleteTest() {
    if (confirm("Are you sure? The test will be deleted?")) {
        document.forms["form_delete_test"].submit();
    }
};

