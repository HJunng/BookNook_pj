var socket = new WebSocket("ws://localhost:8080/chat");

socket.onopen = function() {
    console.log("WebSocket 연결이 열렸습니다.");
};

socket.onmessage = function(event) {
    var message = event.data;
    console.log("서버로부터 메시지 수신: " + message);
    // 메시지 처리 로직 작성
};

socket.onclose = function(event) {
    console.log("WebSocket 연결이 닫혔습니다.");
};

function sendMessage() {
    var message = document.getElementById("messageInput").value;
    socket.send(message);
}