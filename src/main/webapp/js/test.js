var showMess = true;

window.onload = function() {
    initializeTimer();

    var spoilers = document.querySelectorAll("*[data-target]");
    var size = spoilers.length;

    for (var i = 1; i < size; i++) {
        document.querySelector("*[data-name=" + spoilers[i].getAttribute('data-target') + "]").classList.add('spoiler_hide');
    }

    for (var i = 0; i < size; i++) {
        spoilers[i].onclick = function () {
            var spText = document.querySelector("*[data-name=" + this.getAttribute('data-target') + "]");

            for (var j = 0; j < spoilers.length; j++) {
                document.querySelector("*[data-name=" + spoilers[j].getAttribute('data-target') + "]").classList.remove('spoiler_show');
                document.querySelector("*[data-name=" + spoilers[j].getAttribute('data-target') + "]").classList.add('spoiler_hide');
            }
            spText.classList.remove('spoiler_hide');
            spText.classList.add('spoiler_show');
        }
    }
};

function initializeTimer() {
    var endDate = new Date(document.getElementById("seconds").value);
    var currentDate = new Date();
    var seconds = (endDate - currentDate) / 1000;

    if (seconds > 0) {
        var minutes = seconds/60;
        var hours = minutes/60;
        minutes = (hours - Math.floor(hours)) * 60;
        hours = Math.floor(hours);
        seconds = Math.floor((minutes - Math.floor(minutes)) * 60);
        minutes = Math.floor(minutes);

        setTimePage(hours,minutes,seconds);
        function secOut() {
            if (seconds == 0) {
                if (minutes == 0) {
                    if (hours == 0) {
                        sendTest();
                    }
                    else {
                        hours--;
                        minutes = 59;
                        seconds = 59;
                    }
                }
                else {
                    minutes--;
                    seconds = 59;
                }
            }
            else {
                seconds--;
            }
            setTimePage(hours,minutes,seconds);
        }
        timerId = setInterval(secOut, 1000);
    } else {
        sendTest();
    }
}

function setTimePage(h,m,s) {
    var element = document.getElementById("timer");
    element.innerHTML = "";
    if ( h < 10 ) {
        element.innerHTML += "0";
    }
    element.innerHTML += h + ":";
    if ( m < 10 ) {
        element.innerHTML += "0";
    }
    element.innerHTML += m + ":";
    if ( s < 10 ) {
        element.innerHTML += "0";
    }
    element.innerHTML += s;
};

function sendTest() {
    showMess = false;
    document.forms["send_test"].submit();
};

window.onbeforeunload = function (evt) {
    if ( showMess ) {
        var message = "Document 'foo' is not saved. You will lost the changes if you leave the page.";
        if (typeof evt == "undefined") {
            evt = window.event;
        }
        if (evt) {
            evt.returnValue = message;
        }
        return message;
    }
};
