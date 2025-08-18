// header.js - 네비게이션 관리 모듈

// 페이지 로드 시 네비게이션 초기화
document.addEventListener('DOMContentLoaded', function () {
  setActiveNavigation();
});

// 현재 페이지에 맞는 네비게이션 active 클래스 설정
function setActiveNavigation() {
  const currentPage = getCurrentPageName();
  const navLinks = document.querySelectorAll('.nav-menu a');

  // 모든 링크에서 active 클래스 제거
  navLinks.forEach((link) => {
    link.classList.remove('active');
  });

  // 현재 페이지에 맞는 링크에 active 클래스 추가
  const activeLink = document.getElementById(currentPage);
  if (activeLink) {
    activeLink.classList.add('active');
  }

  // 기본값으로 stockList 설정 (페이지를 찾을 수 없는 경우)
  if (!activeLink && (currentPage === 'index' || currentPage === '')) {
    const stockListLink = document.getElementById('stockList');
    if (stockListLink) {
      stockListLink.classList.add('active');
    }
  }
}

// 현재 페이지 이름 가져오기
function getCurrentPageName() {
  return window.location.pathname.split('/').pop().split('.')[0];
}
