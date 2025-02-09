$(document).ready(function () {
    $("#languageDropdownMenuButton a").click(function (e) {
        e.preventDefault(); // Cancelar el comportamiento del enlace

        let languageSelectedText = $(this).text();
        let languageSelectedValue = $(this).attr("value");

        $("#btnLanguage").text(languageSelectedText);
        window.location.replace('?lang=' + languageSelectedValue);

        return false;
    });
});
