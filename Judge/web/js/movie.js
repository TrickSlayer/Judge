var checkbox = document.getElementsByName("rating");
var check = false;
for (var i = 0; i < checkbox.length; i++) {
    if (checkbox[i].checked === true) {
        change(checkbox[i].value);
        check = check || true;
    }
    if (!check)
        change(-1);
}

document.getElementById("btn0").onclick = function () {
    change(document.getElementById("btn0").value);
};
document.getElementById("btn1").onclick = function () {
    change(document.getElementById("btn1").value);
};
document.getElementById("btn2").onclick = function () {
    change(document.getElementById("btn2").value);
};
document.getElementById("btn3").onclick = function () {
    change(document.getElementById("btn3").value);
};
document.getElementById("btn4").onclick = function () {
    change(document.getElementById("btn4").value);
};
document.getElementById("btn5").onclick = function () {
    change(document.getElementById("btn5").value);
};
document.getElementById("btn6").onclick = function () {
    change(document.getElementById("btn6").value);
};
document.getElementById("btn7").onclick = function () {
    change(document.getElementById("btn7").value);
};
document.getElementById("btn8").onclick = function () {
    change(document.getElementById("btn8").value);
};
document.getElementById("btn9").onclick = function () {
    change(document.getElementById("btn9").value);
};



document.getElementById("btn0").onmouseover = function () {
    change(document.getElementById("btn0").value);
};
document.getElementById("btn0").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn1").onmouseover = function () {
    change(document.getElementById("btn1").value);
};
document.getElementById("btn1").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn2").onmouseover = function () {
    change(document.getElementById("btn2").value);
};
document.getElementById("btn2").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn3").onmouseover = function () {
    change(document.getElementById("btn3").value);
};
document.getElementById("btn3").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn4").onmouseover = function () {
    change(document.getElementById("btn4").value);
};
document.getElementById("btn4").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn5").onmouseover = function () {
    change(document.getElementById("btn5").value);
};
document.getElementById("btn5").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn6").onmouseover = function () {
    change(document.getElementById("btn6").value);
};
document.getElementById("btn6").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn7").onmouseover = function () {
    change(document.getElementById("btn7").value);
};
document.getElementById("btn7").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn8").onmouseover = function () {
    change(document.getElementById("btn8").value);
};
document.getElementById("btn8").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};
document.getElementById("btn9").onmouseover = function () {
    change(document.getElementById("btn9").value);
};
document.getElementById("btn9").onmouseout = function () {
    var checkbox = document.getElementsByName("rating");
    var check = false;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            change(checkbox[i].value);
            check = check || true;
        }
        if (!check)
            change(-1);
    }
};



function change(a) {
    let b = parseInt(a) + 1;
    for (var i = 0; i < b; i++) {
        document.getElementById("star" + i).style.color = "gold";
    }
    for (var i = b; i < 10; i++) {
        document.getElementById("star" + i).style.color = "whitesmoke";
    }
}