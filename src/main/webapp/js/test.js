window.onload = function() {
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


