let timerId;
let time = 0;
const stopwatch = document.getElementById("stopwatch");
let hour, min, sec;

let startTime, endTime;
let urlParams = new URLSearchParams(window.location.search);
let email = urlParams.get('email');

// 시간(int)을 시, 분, 초 문자열로 변환
function getTimeFormatString() {
    hour = parseInt(String(time / (60 * 60)));
    min = parseInt(String((time - (hour * 60 * 60)) / 60));
    sec = time % 60;

    return String(hour).padStart(2, '0') + ":" +
        String(min).padStart(2, '0') + ":" +
        String(sec).padStart(2, '0');
}

function printTime() {
    time++;
    stopwatch.innerText = getTimeFormatString();
}

function stopClock() {
    if (timerId != null) {
        clearTimeout(timerId);
    }
    endTime = new Date();
    console.log("종료시각"+ endTime);
}

function setInterval() {
    printTime();
    stopClock();
    timerId = setTimeout(setInterval, 1000);
}

function startClock() {
    // printTime();
    // stopClock();
    startTime = new Date();
    console.log("시작시각" + startTime);
    setInterval();
    // timerId = setTimeout(startClock, 1000);
}

function resetClock() {
    stopClock();
    stopwatch.innerText = "00:00:00";
    time = 0;
}

function endClock() {

    // stopClock();
    const startIsoString = startTime.toISOString();
    const endIsoString = endTime.toISOString();


    $.ajax({
        url: "/api/focus-session/endclock",
        type: "POST",
        dataType: "JSON",
        contentType : "application/json",
        data : JSON.stringify({
            startTime : startIsoString,
            endTime: endIsoString,
            email : email
        }),

        success : function (jsonData){
            alert("등록 성공");
            console.log(startIsoString);
            console.log(endIsoString);
        },

        error: function (error) {
            console.log(error);
            alert("등록 실패");

        },

        complete: function () {
            stopClock();
        },
    })

}

