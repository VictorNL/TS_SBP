function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function startTime() {
    var txt = document.getElementById('txt').innerHTML.split(":");
    var s = parseInt(txt[2]);
    var m = parseInt(txt[1]);
    var h = parseInt(txt[0]);
    
    s = s + 1;
    
    if (s > 59) { s = 0; m = m + 1; }
    if (m > 59) { m = 0; h = h + 1; }
    if (h > 23) { h = 0; }
    
    s = checkTime(s);
    m = checkTime(m);
    h = checkTime(h);
    
    document.getElementById('txt').innerHTML = (h + ":" + m + ":" + s);
    t = setTimeout('startTime()', 1000);
}