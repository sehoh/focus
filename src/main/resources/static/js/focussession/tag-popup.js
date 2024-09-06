function openTagPopup() {
    // var url = "/focus-session/tag-popup";
    const startIsoString = startTime.toISOString();
    const endIsoString = endTime.toISOString();

    const urlParam = new URLSearchParams(window.location.search);
    const email = urlParams.get('email');

    var url = `/focus-session/tag-popup?start=${startIsoString}&end=${endIsoString}&email=${email}`;
    var name = "태그 팝업창";
    var option = "width = 500, height = 500, top = 100, left = 200, location = no";
    window.open(url, name, option);
}
