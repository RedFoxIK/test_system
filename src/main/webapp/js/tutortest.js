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
    var index = input.length;
    var answer = '<div class="test_item"><input type="' + type + '" value="'+index+'" class="type_answer"  name="group"> ' +
        '<input type="text" class="answer" name="'+index+'"></div>';
    document.getElementById('other_answers').innerHTML += answer;
};

function remove() {
    var test_items = document.getElementsByClassName("test_item");
    var last = test_items.length - 1;
    test_items[last].remove();
};

function count_answers() {
    var input = document.getElementsByClassName("answer");
    var size = input.length;
    document.getElementById("number_answers").value = size;

    var answers = document.getElementsByName("group");
    var numAnswers = answers.length;
    var numRightAnswers = 0;

    for ( var i = 0; i <numAnswers; i++ ) {
        if ( answers[i].checked ) {
            numRightAnswers += 1;
        }
    }

    if ( numRightAnswers > 0 ) {
        document.forms["form_add_question"].submit();
    } else {
        alert("You must select at least one right answer!!!")
    }
}

