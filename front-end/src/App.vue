<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100%;
  height: 100vh;
}

body {
  margin: 0;
  overflow-x: hidden;
}

/* グローバルアニメーション */
@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@keyframes slideInUp {
  from { 
    transform: translateY(30px); 
    opacity: 0; 
  }
  to { 
    transform: translateY(0); 
    opacity: 1; 
  }
}

/* 共通のボタンホバーエフェクト */
.btn-hover-effect {
  position: relative;
  overflow: hidden;
  transition: all 0.3s ease;
}

.btn-hover-effect::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.3), transparent);
  transition: left 0.5s;
}

.btn-hover-effect:hover::before {
  left: 100%;
}

/* スクロールバーのカスタマイズ */
::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  background: #2c3e50;
}

::-webkit-scrollbar-thumb {
  background: #3498db;
  border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
  background: #2980b9;
}

/* レスポンシブのヘルパークラス */
.mobile-only {
  display: none;
}

.desktop-only {
  display: block;
}

@media (max-width: 768px) {
  .mobile-only {
    display: block;
  }
  
  .desktop-only {
    display: none;
  }
}
</style>
