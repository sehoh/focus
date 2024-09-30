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

function searchTags() {
    var tags = tagify.value.map(tag => tag.value);

    if (tags.length === 0) {
        return;
    }

    var url;

    if (tags.length === 1) {

        url = `/api/focus-session/search?tag=${encodeURIComponent(tags[0])}`;

    } else {
        var queryString = tags.map(tag => `tags=${encodeURIComponent(tag)}`).join("&");
        url = `/api/focus-session/search/multi?${queryString}`;
    }

    $.ajax({
        method: "GET",
        url: url,
    }).done(function (data, status) {
        console.log("tags: " + tags);
        console.log("queryString " + queryString);
        console.log("Response data:", data, "and status: ", status);
        updateSearchResult(data);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log("Error: ", textStatus, errorThrown);
    });
}

document.querySelector("#searchTags").addEventListener("click", searchTags);

function updateSearchResult(results) {

    const tbody = $('#search-tag-resultBody');
    tbody.empty();

    if (results.length === 0) {
        tbody.append(`
            <tr>
                <td colspan="4" style="text-align: center;">데이터가 없습니다.</td>
            </tr>
        `);

    } else {
        results.forEach(function (focusSession) {
            tbody.append(`
                <tr>
                    <td>${focusSession.id}</td>
                    <td>${focusSession.name}</td>
                    <td>${focusSession.startDateTime}</td>
                    <td>${focusSession.endDateTime}</td>
                </tr>
        `)

        });
    }

    $('#search-tag-result').show();
}