document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll("input[type='text'], input[type='search']").forEach(function (input) {
        input.addEventListener("blur", function () {
            input.value = input.value.trim();
        });
    });
});
