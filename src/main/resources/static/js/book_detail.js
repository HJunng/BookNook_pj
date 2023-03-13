const urlParams = new URL(location.href).searchParams;
const bookSearch = urlParams.get("bookSearch");

let isbn;

isbn = localStorage.getItem("isbn");
isbn = isbn.split(" ")[1];

console.log(isbn);

$.ajax({
  method: "GET",
  url: "https://dapi.kakao.com/v3/search/book?target=title",
  data: { query: isbn, sort: "accuracy", target: "isbn" },
  headers: { Authorization: "KakaoAK a139cc1585a4fde2ef179e9e239f02a9" },
}).done(function (msg) {
  console.log(msg);

  /*
        let tagArea = document.getElementById('tagArea');
        let total_result = document.getElementById('total_result');
        let title = document.getElementsByTagName('title');

        $( total_result ).append(msg.meta.pageable_count);
        $( title ).append(bookSearch + " | 검색 결과");

        for(let i=0; i<msg.documents.length; i++){
            let new_divTag = document.createElement('div');
            new_divTag.setAttribute('class', 'book');
            //new_divTag.setAttribute('title', msg.documents[i].title);
            tagArea.appendChild(new_divTag);

            $( new_divTag ).append("<div><img src='" + msg.documents[i].thumbnail + "'/></div>");

            let new_divTag2 = document.createElement('div');
            new_divTag2.setAttribute('class', 'bookContents');
            tagArea.appendChild(new_divTag2);
            new_divTag.appendChild(new_divTag2);

            $( new_divTag2 ).append("<p><strong><a href='book_detail.html'>" + msg.documents[i].title + "</a></strong></p>");
            $( new_divTag2 ).append("<p class='left'>" + msg.documents[i].authors + "</p>");
            $( new_divTag2 ).append("<p class='left'>&middot</p>");
            $( new_divTag2 ).append("<p class='left'>" + msg.documents[i].publisher + "</p>");
            $( new_divTag2 ).append("<p>" + msg.documents[i].contents + "</p>");

            console.log(msg.documents[i].title);
            console.log(msg.documents[i].thumbnail);
        }
        */
});

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

let start_read = document.querySelector("#start_read");
let end_read = document.querySelector("#end_read");
let start_reading = document.querySelector("#start_reading");

let now_utc = Date.now(); // 지금 날짜를 밀리초로
// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
let timeOff = new Date().getTimezoneOffset() * 60000; // 분단위를 밀리초로 변환
// new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
let today = new Date(now_utc - timeOff).toISOString().split("T")[0];
//document.getElementById("Date").setAttribute("max", today);

start_read.setAttribute("max", today);
start_reading.setAttribute("max", today);
end_read.setAttribute("max", today);

start_read.oninput = function () {
  end_read.setAttribute("min", start_read.value);
};
