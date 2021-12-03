/* global Document */
function show(){
    a = document.getElementById("search");
    b = document.getElementById("search-button");
    a.style.display = "block";
    b.style.display = "none";
}

function hide(){
    a = document.getElementById("search");
    b = document.getElementById("search-button");
    a.style.display = "none";
    b.style.display = "inline-block";
}
