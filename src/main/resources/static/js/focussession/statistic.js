let urlParams = new URLSearchParams(window.location.search);
let email = urlParams.get('email');

function fetchWeeklyFocusTime() {
    $('#dailyTimeTable').hide();

    $.ajax({
        url: "/api/focus-session/weeks/" + email,
        type: "GET",

        success : function (data) {
            updateWeeklyTable(data);
        },
        error : function (error) {
            console.log(error);
        }
    })
}

function updateWeeklyTable(weeklyData){
    const tbody = $('#cumulativeWeekTimeTableBody');
    tbody.empty(); // 기존 테이블 내용 삭제

    // 주간 데이터를 테이블에 추가
    weeklyData.forEach(function (cumulativeWeekTimes) {
        tbody.append(`
                <tr>
                    <td>${cumulativeWeekTimes.weekStart}</td>
                    <td>${cumulativeWeekTimes.weekEnd}</td>
                    <td>${cumulativeWeekTimes.cumulativeTimeDiff}</td>
                </tr>
            `)
    });

    // show
    $('#weeklyTimeTable').show();
}

function fetchMonthlyFocusTime() {
    $('#dailyTimeTable').hide();
    $('#weeklyTimeTable').hide();

    $.ajax({
        url: "/api/focus-session/months/" + email,
        type: "GET",

        success: function (data) {
            updateMonthlyTable(data);
        },
        error: function (error) {
            console.log(error);
        },
    });
}

function updateMonthlyTable(monthlyData) {
    const tbody = $('#monthlyTimeTableBody');
    tbody.empty();

    monthlyData.forEach(function (monthlyCumulativeTimes){
        tbody.append(`
                <tr>
                    <td>${monthlyCumulativeTimes.monthStart}</td>
                    <td>${monthlyCumulativeTimes.monthEnd}</td>
                    <td>${monthlyCumulativeTimes.cumulativeTimeDiff}</td>
                </tr>
            `)
    });

    $('#monthlyTimeTable').show();
}

function fetchDailyFocusTime() {

    $('#weeklyTimeTable').hide();
    $('#monthlyTimeTable').hide();

    $.ajax({
        url: "/api/focus-session/days/" + email,
        type: "GET",

        success: function (data) {
            updateDailyTable(data);
        },
        error: function (error) {
            console.log(error);
        },
    })

}


function updateDailyTable(dailyData) {
    const tbody = $('#dailyTimeTableBody');
    // 필요?
    tbody.empty();
    dailyData.forEach(function (dailyCumulativeTimes) {
        tbody.append(`
                <tr>
                    <td>${dailyCumulativeTimes.date}</td>
                    <td>${dailyCumulativeTimes.cumulativeTimeDiff}</td>
                </tr>
            `)
    });

    $('#dailyTimeTable').show();
}