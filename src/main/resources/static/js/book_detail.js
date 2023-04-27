/* searchList.html에서 클릭한 책의 정보(isbn) 받기 */
const urlParams = new URL(location.href).searchParams;
const bookSearch = urlParams.get("Isbn");
var book_authors;

$.ajax({
  method: "GET",
  url: "https://dapi.kakao.com/v3/search/book?target=title",
  data: { query: bookSearch, sort: "accuracy", target: "isbn" },
  headers: { Authorization: "KakaoAK a139cc1585a4fde2ef179e9e239f02a9" },
}).done(function (msg) {
  //console.log(msg);

  /* book_detail.html에 책 정보 넣기 */
  let book_thumbnail = document.getElementById("book_thumbnail");
  let book_title = document.getElementById("book_title");
  book_authors = document.getElementById("book_authors");
  let book_publisher = document.getElementById("book_publisher");
  let book_summary = document.getElementById("book_summary");

  /* 책 표지 사진이 없으면 대체 이미지 넣기 */
  if (msg.documents[0].thumbnail === "") {
    book_thumbnail.setAttribute("src", "../images/no_img.png");
  } else {
    book_thumbnail.setAttribute("src", msg.documents[0].thumbnail);
  }

  book_title.innerText = msg.documents[0].title;
  book_authors.innerText = msg.documents[0].authors;
  book_publisher.innerText = msg.documents[0].publisher;
  book_summary.innerText = msg.documents[0].contents;

});
 function inputIsbn_1() {
   document.getElementById("isbn1").setAttribute("value", bookSearch);
   document.getElementById("condition1").setAttribute("value", 0); //읽은 책
   document.getElementById("author1").setAttribute("value",book_authors.innerText);

   document.getElementById("read_form").submit();
 }
 function inputIsbn_2() {
   document.getElementById("isbn2").setAttribute("value", bookSearch);
   document.getElementById("condition2").setAttribute("value", 1); //읽고있는 책
   document.getElementById("author2").setAttribute("value",book_authors.innerText);

   document.getElementById("reading_form").submit();
 }
 function inputIsbn_3() {
   document.getElementById("isbn3").setAttribute("value", bookSearch);
   document.getElementById("condition3").setAttribute("value", 2); //읽고싶은 책
   document.getElementById("author3").setAttribute("value",book_authors.innerText);

   document.getElementById("willRead_form").submit();
 }

/* 읽은 상태 선택에 따른 팝업창 띄우기 */
let popup_read = document.getElementById("popup_read");
let popup_reading = document.getElementById("popup_reading");
let popup_want = document.getElementById("popup_want");
let popup_layer = document.querySelector("#popup_layer");

document.getElementById("cart_submit").onclick = function () {
  let radios = document.getElementsByName("cart");
  let selected = Array.from(radios).find((radio) => radio.checked);

  if (selected.value === "read") {
    popup_layer.style.display = "block";
    popup_read.style.display = "block";
  } else if (selected.value === "reading") {
    popup_layer.style.display = "block";
    popup_reading.style.display = "block";
  } else if (selected.value === "wanttoread") {
    popup_layer.style.display = "block";
    popup_want.style.display = "block";
  }
};

/* 팝업창의 "닫기" 버튼 눌렀을 때 */
let close_read = document.getElementById("close_read");
let close_reading = document.getElementById("close_reading");
let close_want = document.getElementById("close_want");

close_read.addEventListener("click", close);
close_reading.addEventListener("click", close);
close_want.addEventListener("click", close);

function close() {
  popup_layer.style.display = "none";
  popup_read.style.display = "none";
  popup_reading.style.display = "none";
  popup_want.style.display = "none";
}

/* "읽고 있는 책" 팝업창에서 독서량 단위 버튼 클릭 이벤트 */
function selectUnit(event) {
  if (event.target.value === "pages") {
    document.getElementById("page").style.backgroundColor = "#666666";
    document.getElementById("page").style.color = "white";
    document.getElementById("per").style.backgroundColor = "white";
    document.getElementById("per").style.color = "#666666";
    document.getElementById("span_page").innerText = "쪽";
  } else {
    document.getElementById("per").style.backgroundColor = "#666666";
    document.getElementById("per").style.color = "white";
    document.getElementById("page").style.backgroundColor = "white";
    document.getElementById("page").style.color = "#666666";
    document.getElementById("span_page").innerText = "%";
  }
}

/* "읽은 책", "읽고 있는 책" 팝업창 독서기간 제한 */
/* 시작일은 현재 날짜 이전, 종료일은 시작일 이후, 현재 날짜 이전  */
let start_read = document.querySelector("#start_read");
let end_read = document.querySelector("#end_read");
let start_reading = document.querySelector("#start_reading");

let now_utc = Date.now(); // 지금 날짜를 밀리초로
// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
let timeOff = new Date().getTimezoneOffset() * 60000; // 분단위를 밀리초로 변환
// new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
let today = new Date(now_utc - timeOff).toISOString().split("T")[0];

start_read.setAttribute("max", today);
start_reading.setAttribute("max", today);
end_read.setAttribute("max", today);

start_read.oninput = function () {
  end_read.setAttribute("min", start_read.value);
};
