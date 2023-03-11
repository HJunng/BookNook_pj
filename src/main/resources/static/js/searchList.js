const urlParams = new URL(location.href).searchParams;
const bookSearch = urlParams.get('bookSearch');
let ISBN;

$.ajax({
    method: "GET",
    url: "https://dapi.kakao.com/v3/search/book?target=title",
    data: { query: bookSearch, sort: "accuracy"},
    headers: { Authorization: "KakaoAK a139cc1585a4fde2ef179e9e239f02a9" }
})
    .done(function( msg ) {
        console.log(msg);
        
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

            
            //let new_divTag = document.getElementsByClassName('book');
            $( new_divTag ).append("<div><img src='" + msg.documents[i].thumbnail + "'/></div>");

            
            let new_divTag2 = document.createElement('div');
            new_divTag2.setAttribute('class', 'bookContents');
            tagArea.appendChild(new_divTag2);
            new_divTag.appendChild(new_divTag2);
            
            //let new_divTag2 = document.getElementsByClassName('bookContents');

            $( new_divTag2 ).append("<p><strong><a href='book_detail.html'>" + msg.documents[i].title + "</a></strong></p>");
            $( new_divTag2 ).append("<p class='left'>" + msg.documents[i].authors + "</p>");
            $( new_divTag2 ).append("<p class='left'>&middot</p>");
            $( new_divTag2 ).append("<p class='left'>" + msg.documents[i].publisher + "</p>");
            $( new_divTag2 ).append("<p>" + msg.documents[i].contents + "</p>");

            console.log(msg.documents[i].title);
            console.log(msg.documents[i].thumbnail);
            ISBN = msg.documents[i].isbn;
            console.log(ISBN);

        }

        let a_tag ;

        
        console.log(localStorage.getItem('isbn'));
        
    });

    