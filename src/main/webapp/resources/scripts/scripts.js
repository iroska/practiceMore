$(function() {
    $(".button-collapse").sideNav();
});

function countChar(val) {

    var len = val.value.length;
    var ctext = len + " Chars";

    var str = val.value;
    var parts = [];
    var partSize = 140;

    while (str) {
        if (str.length < 140) {
            var rtext = (140 - str.length) + " Chars Remaining";
            parts.push(str);
            break;
        }
        else {
            parts.push(str.substr(0, 140));
            str = str.substr(140);
        }



    }
    var ptext = parts.length + " Parts";

    $('#text-character').val(ctext);
    $('#text-parts').val(ptext);
    $('#text-remaining').val(rtext);

}
