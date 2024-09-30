var urlParams = new URLSearchParams(window.location.search);
var start = urlParams.get('start');
var end = urlParams.get('end');
var email = urlParams.get('email');

var tagInput = document.querySelector("#tags");

let tagify = new Tagify(tagInput, {
    maxTags: 10, // 최대 허용 태그 갯수

    dropdown: {
        maxItems: 20,               // 드롭다운 메뉴에서 몇개 정도 항목을 보여줄지
        classname: "tags-look",     // 드롭다운 메뉴 엘리먼트 클래스 이름. 이걸로 css 선택자로 쓰면 된다
        enabled: 0,                 // 단어 몇글자 입력했을때 추천 드롭다운 메뉴가 나타날지
        closeOnSelect: false        // 드롭다운 메뉴에서 태그 선택하면 자동으로 꺼지는지 안꺼지는지
    }
})


// 태그가 추가되면 이벤트 발생
tagify.on('add', function () {
    console.log(tagify.value);
});


function sendTagsToServer() {
    var tags = tagify.value.map(tag => tag.value);
    var tagData = {
        tags: tags,
        startDateTime : start,
        endDateTime : end,
        email : email
    };

    $.ajax({
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        method: "POST",
        url: "/api/tag",
        data: JSON.stringify(tagData)
    }).done(function (data, status) {
        console.log("Response data:", data, "and status: ", status);
        window.close();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error: ", textStatus, errorThrown);
        alert("등록 실패");
    });
}


document.querySelector("#submitTags").addEventListener("click", sendTagsToServer);
