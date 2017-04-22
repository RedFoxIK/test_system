window.onload = function() {
    var input = document.getElementsByClassName("type_answer");
    var size = input.length;
    for ( var i = 0; i < size; i++ ) {
        input[i].type = "radio";
    }
};

function changeType() {
    var input = document.getElementsByClassName("type_answer");
    var size = input.length;
    var oldType = input[0].type;
    var newType = "radio";

    if (oldType == newType) {
        newType = "checkbox";
    }

    for ( var i = 0; i < size; i++ ) {
        input[i].type = newType;
    }
};

function add() {
    var input = document.getElementsByClassName("type_answer");
    var type = input[0].type;
    var answer = '<div class="test_item"><input type="' + type + '" class="type_answer"  name="group"> ' +
        '<input type="text" class="answer"></div>';
    document.getElementById('other_answers').innerHTML += answer;
};

function remove() {
    var test_items = document.getElementsByClassName("a");
    var last = test_items.length - 1;
    test_items[last].remove();
};