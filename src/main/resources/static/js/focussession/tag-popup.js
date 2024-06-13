function openTagPopup() {
    // var url = "/focus-session/tag-popup";
    const startIsoString = startTime.toISOString();
    const endIsoString = endTime.toISOString();

    var url = `/focus-session/tag-popup?start=${startIsoString}&end=${endIsoString}`;
    var name = "태그 팝업창";
    var option = "width = 500, height = 500, top = 100, left = 200, location = no";
    window.open(url, name, option);
}
