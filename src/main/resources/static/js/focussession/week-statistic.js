let urlParams = new URLSearchParams(window.location.search);
let email = urlParams.get('email');

function fetchWeeklyStudyTime() {
    $('#dailyTimeTable').hide();

    $.ajax({
        url: "/api/focus-session/weeks/" + email,
        type: "GET",

        success : function (data) {
            updateTable(data);
        },
        error : function (error) {
            console.log(error);
        }
    })
}

function updateTable(weeklyData){
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