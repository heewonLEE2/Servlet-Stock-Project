<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>StockWave - AI 활용 주식 최신 뉴스</title>
    <link rel="stylesheet" href="../css/aiNews.css" />
    <script src="../js/aiNews.js" defer></script>
  </head>
  <body>
	<%@ include file="header.jsp" %>
    <div class="main-container">
      <!-- 즐겨찾기 사이드바 -->
      <div class="favorites-sidebar">
        <div class="sidebar-header">
          <div class="sidebar-title">⭐ 즐겨찾기</div>
          <div class="sidebar-subtitle">관심 종목들을 확인하세요</div>
        </div>
        <div class="favorites-list" id="favoritesList">
          <!-- 즐겨찾기 목록이 여기에 추가됩니다 -->
        </div>
      </div>

      <!-- AI 채팅 컨테이너 -->
      <div class="ai-chat-container">
        <div class="chat-header">
          <div class="chat-title">AI 주식 분석</div>
          <div class="chat-subtitle">
            궁금한 회사나 주식에 대해 AI에게 물어보세요
          </div>
        </div>

        <div class="chat-messages" id="chatMessages">
          <div class="welcome-message">
            안녕하세요! 주식 관련 질문이나 회사 분석을 도와드릴 AI입니다.<br />
            궁금한 회사나 주식에 대해 무엇이든 물어보세요!
          </div>
        </div>

        <div class="typing-indicator" id="typingIndicator">
          <div class="typing-dots">
            <div class="typing-dot"></div>
            <div class="typing-dot"></div>
            <div class="typing-dot"></div>
          </div>
        </div>

        <div class="chat-input-container">
          <div class="input-group">
            <textarea
              class="chat-input"
              id="chatInput"
              placeholder="예: 삼성전자의 최근 실적은 어떤가요? 반도체 업계 전망은?"
              rows="1"
            ></textarea>
            <button class="send-button" id="sendButton">전송</button>
          </div>
        </div>
      </div>
    </div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
