/* 검색창에 입력한 값 받아오기 */
const urlParams = new URL(location.href).searchParams;
const bookSearch = urlParams.get("bookSearch");
let ISBN;

$.ajax({
  method: "GET",
  url: "https://dapi.kakao.com/v3/search/book?target=title",
  data: { query: bookSearch, sort: "accuracy" },
  headers: { Authorization: "KakaoAK a139cc1585a4fde2ef179e9e239f02a9" },
}).done(function (msg) {
  //console.log(msg);

  let tagArea = document.getElementById("tagArea");
  let total_result = document.getElementById("total_result");
  let title = document.getElementsByTagName("title");

  $(total_result).append(msg.meta.pageable_count); // 전체 건수
  $(title).append(bookSearch + " | 검색 결과"); // html title

  // 검색된 문서 모두 출력
  for (let i = 0; i < msg.documents.length; i++) {
    // <div class="book"></div> 생성
    let new_divTag = document.createElement("div");
    new_divTag.setAttribute("class", "book");
    tagArea.appendChild(new_divTag);

    // .book 태그에 이미지 넣기
    $(new_divTag).append(
      "<div><img src='" + msg.documents[i].thumbnail + "'/></div>"
    );

    //<div class="bookContents"></div> 생성
    let new_divTag2 = document.createElement("div");
    new_divTag2.setAttribute("class", "bookContents");
    tagArea.appendChild(new_divTag2);
    new_divTag.appendChild(new_divTag2);

    // .bookContents 태그에 제목, 저자, 출판사, 책 소개 넣기
    $(new_divTag2).append(
      "<p><strong><a href='' class='book_link' data-isbn=" +
        msg.documents[i].isbn +
        ">" +
        msg.documents[i].title +
        "</a></strong></p>"
    );
    $(new_divTag2).append(
      "<p class='left'>" + msg.documents[i].authors + "</p>"
    );
    $(new_divTag2).append("<p class='left'>&middot</p>");
    $(new_divTag2).append(
      "<p class='left'>" + msg.documents[i].publisher + "</p>"
    );
    $(new_divTag2).append("<p>" + msg.documents[i].contents + "</p>");

    // 책 제목 클릭하면 해당 책의 isbn을 bookSearch에 넣어 URL에 넣기
    let a_tag = document.getElementsByClassName("book_link");

    for (let i = 0; i < a_tag.length; i++) {
      a_tag[i].onclick = function (event) {
        event.target.href =
          "book_detail.html?bookSearch=" + event.target.dataset.isbn;
      };
    }
  }
});
