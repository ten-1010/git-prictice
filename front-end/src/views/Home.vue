<template>
  <div class="home-container">
    <div class="pachinko-machine">
      <div class="machine-header">
        <h1 class="neon-text">🎰 パチンコ WebApp 🎰</h1>
        <div class="led-strips">
          <div class="led-strip top"></div>
          <div class="led-strip bottom"></div>
        </div>
      </div>

      <div class="machine-body">
        <div class="display-screen">
          <div class="screen-content">
            <h2 class="instruction">結果を入力してください</h2>
            <div class="input-section">
              <input
                v-model="userInput"
                @keyup.enter="processInput"
                class="result-input"
                placeholder="大当たり / 普通 / ハズレ"
                autofocus
              />
              <button @click="processInput" class="spin-button">
                <span class="button-text">🎯 結果確認</span>
                <div class="button-glow"></div>
              </button>
            </div>

            <div class="quick-buttons">
              <button @click="navigateTo('大当たり')" class="quick-btn jackpot">
                <span>🎊 大当たり</span>
              </button>
              <button @click="navigateTo('普通')" class="quick-btn normal">
                <span>😊 普通</span>
              </button>
              <button @click="navigateTo('ハズレ')" class="quick-btn miss">
                <span>😢 ハズレ</span>
              </button>
            </div>
          </div>
        </div>

        <div class="machine-lights">
          <div v-for="n in 20" :key="n" class="light" :style="{ animationDelay: n * 0.1 + 's' }"></div>
        </div>
      </div>

      <div class="machine-base">
        <div class="control-panel">
          <div class="coin-slot"></div>
          <div class="handle"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data() {
    return {
      userInput: ''
    }
  },
  methods: {
    processInput() {
      this.navigateTo(this.userInput.trim())
    },
    navigateTo(input) {
      const routes = {
        '大当たり': '/SuperLucky',
        '普通': '/Normal',
        'ハズレ': '/hazure'
      }

      const route = routes[input]
      if (route) {
        this.$router.push(route)
      } else {
        this.showErrorAnimation()
      }
    },
    showErrorAnimation() {
      const input = document.querySelector('.result-input')
      input.classList.add('error-shake')
      setTimeout(() => {
        input.classList.remove('error-shake')
      }, 600)
    }
  }
}
</script>

<style scoped>
.home-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  font-family: 'Arial', sans-serif;
}

.pachinko-machine {
  width: 500px;
  background: linear-gradient(145deg, #2c3e50, #34495e);
  border-radius: 20px;
  box-shadow:
    0 20px 40px rgba(0, 0, 0, 0.3),
    inset 0 2px 10px rgba(255, 255, 255, 0.1);
  overflow: hidden;
  border: 3px solid #f39c12;
}

.machine-header {
  background: linear-gradient(45deg, #e74c3c, #c0392b);
  padding: 20px;
  text-align: center;
  position: relative;
}

.neon-text {
  color: #fff;
  font-size: 2.2em;
  font-weight: bold;
  text-shadow:
    0 0 10px #fff,
    0 0 20px #ff6b6b,
    0 0 30px #ff6b6b,
    0 0 40px #ff6b6b;
  animation: neonFlicker 2s infinite alternate;
  margin: 0;
}

@keyframes neonFlicker {
  0%, 100% { text-shadow: 0 0 10px #fff, 0 0 20px #ff6b6b, 0 0 30px #ff6b6b; }
  50% { text-shadow: 0 0 5px #fff, 0 0 10px #ff6b6b, 0 0 15px #ff6b6b; }
}

.led-strips {
  position: relative;
  margin-top: 10px;
}

.led-strip {
  height: 8px;
  background: linear-gradient(90deg, #f39c12, #e67e22, #d35400);
  margin: 5px 0;
  border-radius: 4px;
  animation: ledPulse 1.5s ease-in-out infinite;
}

@keyframes ledPulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.6; }
}

.machine-body {
  background: linear-gradient(145deg, #34495e, #2c3e50);
  padding: 30px;
  position: relative;
}

.display-screen {
  background: #000;
  border-radius: 15px;
  padding: 30px;
  border: 5px solid #3498db;
  box-shadow:
    inset 0 0 20px rgba(52, 152, 219, 0.3),
    0 0 30px rgba(52, 152, 219, 0.2);
  position: relative;
  overflow: hidden;
}

.display-screen::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.1), transparent);
  animation: screenScan 3s linear infinite;
}

@keyframes screenScan {
  0% { left: -100%; }
  100% { left: 100%; }
}

.screen-content {
  position: relative;
  z-index: 2;
}

.instruction {
  color: #3498db;
  font-size: 1.5em;
  margin-bottom: 25px;
  text-align: center;
  text-shadow: 0 0 10px rgba(52, 152, 219, 0.8);
}

.input-section {
  margin-bottom: 30px;
  text-align: center;
}

.result-input {
  width: 100%;
  max-width: 300px;
  padding: 15px 20px;
  font-size: 1.2em;
  border: 2px solid #3498db;
  border-radius: 10px;
  background: rgba(52, 152, 219, 0.1);
  color: #fff;
  text-align: center;
  margin-bottom: 20px;
  transition: all 0.3s ease;
}

.result-input:focus {
  outline: none;
  border-color: #f39c12;
  box-shadow: 0 0 20px rgba(243, 156, 18, 0.5);
  background: rgba(243, 156, 18, 0.1);
}

.result-input::placeholder {
  color: rgba(255, 255, 255, 0.6);
}

.spin-button {
  background: linear-gradient(45deg, #e74c3c, #c0392b);
  color: white;
  border: none;
  padding: 15px 30px;
  font-size: 1.1em;
  font-weight: bold;
  border-radius: 10px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: transform 0.2s;
}

.spin-button:hover {
  transform: scale(1.05);
}

.spin-button:active {
  transform: scale(0.95);
}

.button-glow {
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.4), transparent);
  animation: buttonGlow 2s linear infinite;
}

@keyframes buttonGlow {
  0% { left: -100%; }
  100% { left: 100%; }
}

.quick-buttons {
  display: flex;
  justify-content: space-around;
  gap: 15px;
}

.quick-btn {
  flex: 1;
  padding: 15px 10px;
  border: none;
  border-radius: 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
}

.quick-btn span {
  position: relative;
  z-index: 2;
}

.jackpot {
  background: linear-gradient(45deg, #f39c12, #e67e22);
  color: white;
}

.normal {
  background: linear-gradient(45deg, #27ae60, #2ecc71);
  color: white;
}

.miss {
  background: linear-gradient(45deg, #95a5a6, #7f8c8d);
  color: white;
}

.quick-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
}

.machine-lights {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
}

.light {
  position: absolute;
  width: 6px;
  height: 6px;
  background: #f39c12;
  border-radius: 50%;
  animation: lightBlink 1s ease-in-out infinite;
}

.light:nth-child(odd) {
  left: 10px;
  top: calc(var(--i) * 5%);
}

.light:nth-child(even) {
  right: 10px;
  top: calc(var(--i) * 5%);
}

@keyframes lightBlink {
  0%, 50% { opacity: 1; box-shadow: 0 0 10px #f39c12; }
  51%, 100% { opacity: 0.3; }
}

.machine-base {
  background: linear-gradient(145deg, #2c3e50, #34495e);
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.control-panel {
  display: flex;
  align-items: center;
  gap: 30px;
}

.coin-slot {
  width: 40px;
  height: 8px;
  background: #34495e;
  border-radius: 4px;
  box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.5);
}

.handle {
  width: 20px;
  height: 60px;
  background: linear-gradient(145deg, #e74c3c, #c0392b);
  border-radius: 10px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}

.error-shake {
  animation: errorShake 0.6s ease-in-out;
}

@keyframes errorShake {
  0%, 100% { transform: translateX(0); }
  10%, 30%, 50%, 70%, 90% { transform: translateX(-5px); }
  20%, 40%, 60%, 80% { transform: translateX(5px); }
}

/* レスポンシブ対応 */
@media (max-width: 600px) {
  .pachinko-machine {
    width: 90%;
    max-width: 400px;
  }

  .neon-text {
    font-size: 1.8em;
  }

  .quick-buttons {
    flex-direction: column;
    gap: 10px;
  }
}
</style>
