// 채팅 관련 변수
const chatMessages = document.getElementById("chatMessages");
const chatInput = document.getElementById("chatInput");
const sendButton = document.getElementById("sendButton");
const typingIndicator = document.getElementById("typingIndicator");

// 즐겨찾기 리스트 렌더링
async function renderFavorites() {
  const favoritesList = document.getElementById("favoritesList");

  try {
    const response = await fetch("http://localhost:3000/stock");
    const data = await response.json();
    console.log(
      "즐겨찾기 데이터:",
      data.filter((stock) => stock.isLike)
    );

    favoritesList.innerHTML = data
      .filter((stock) => stock.isLike) // 즐겨찾기 종목만 필터링
      .map(
        (stock) => `
                <div class="favorite-item" onclick="selectFavorite('${stock.기업명}')">
                    <div class="favorite-info">
                        <div class="favorite-name">${stock.기업명}</div>
                    </div>
                        <div class="star-icon">⭐</div>
                    </div>
                </div>
            `
      )
      .join("");
  } catch (error) {
    console.error("API 데이터 가져오기 실패:", error);
  }
}

// 즐겨찾기 종목 선택
function selectFavorite(stockName) {
  // 선택된 아이템 하이라이트
  document.querySelectorAll(".favorite-item").forEach((item) => {
    item.classList.remove("selected");
  });
  event.currentTarget.classList.add("selected");

  // 채팅 입력창에 자동으로 질문 입력
  chatInput.value = `${stockName}에 대해 알려주세요. 최근 주가 동향과 투자 전망은 어떤가요?`;
  chatInput.focus();
}

// 메시지 추가 함수
function addMessage(content, isUser = false) {
  // 메세지 박스 추가
  const messageDiv = document.createElement("div");

  messageDiv.className = `message ${isUser ? "user" : "ai"}`;

  // 시간 설정
  const now = new Date();
  const timeString = now.toLocaleTimeString("ko-KR", {
    hour: "2-digit",
    minute: "2-digit",
  });

  messageDiv.innerHTML = `
                ${content}
                <div class="message-time">${timeString}</div>
            `;

  chatMessages.appendChild(messageDiv);
  // 스크롤을 항상 맨 아래로
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

// 타이핑 인디케이터 표시/숨김
function showTyping() {
  typingIndicator.style.display = "block";
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

function hideTyping() {
  typingIndicator.style.display = "none";
}

// AI 서버에 질문 전송
async function getAIResponse(question) {
  const response = await fetch("http://localhost:8888/aiComment.stockwave", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ question }),
  });

  if (!response.ok) {
    throw new Error("AI 응답을 가져오는 데 실패했습니다.");
  }

  const data = await response.json();
  return data.answer;
}

// 메시지 전송
function sendMessage() {
  const message = chatInput.value.trim();
  if (!message) return;

  // 사용자 메시지 추가
  addMessage(message, true);
  chatInput.value = "";

  // 메세지 보낼때는 버튼 비활성화
  sendButton.disabled = true;

  // 타이핑 인디케이터 표시
  showTyping();

  // AI 응답 시뮬레이션 (2-3초 후)
  setTimeout(() => {
    hideTyping();
    const aiResponse = getAIResponse(message);
    addMessage(aiResponse);
    sendButton.disabled = false;
    chatInput.focus();
  }, 2000 + Math.random() * 1000);
}

// 이벤트 리스너
sendButton.addEventListener("click", sendMessage);

chatInput.addEventListener("keydown", function (e) {
  if (e.key === "Enter" && !e.shiftKey) {
    e.preventDefault();
    sendMessage();
  }
});

// 텍스트 영역 자동 크기 조절
chatInput.addEventListener("input", function () {
  this.style.height = "auto";
  this.style.height = Math.min(this.scrollHeight, 120) + "px";
});

// 초기화
document.addEventListener("DOMContentLoaded", function () {
  renderFavorites();
  chatInput.focus();
});
