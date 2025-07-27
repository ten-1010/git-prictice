<template>
  <div class="super-lucky-container">
    <!-- 花火エフェクト -->
    <div class="fireworks">
      <div v-for="n in 15" :key="n" class="firework" :style="getFireworkStyle(n)"></div>
    </div>
    
    <!-- メインコンテンツ -->
    <div class="jackpot-display">
      <div class="jackpot-header">
        <h1 class="jackpot-title">
          <span class="title-part">🎊</span>
          <span class="title-part">大</span>
          <span class="title-part">当</span>
          <span class="title-part">た</span>
          <span class="title-part">り</span>
          <span class="title-part">🎊</span>
        </h1>
        <div class="subtitle">SUPER JACKPOT!</div>
      </div>
      
      <div class="prize-display">
        <div class="prize-container">
          <div class="coin-rain">
            <div v-for="n in 50" :key="n" class="coin" :style="getCoinStyle(n)">💰</div>
          </div>
          
          <div class="prize-amount">
            <div class="amount-label">獲得賞金</div>
            <div class="amount-value">{{ animatedAmount.toLocaleString() }}円</div>
            <div class="amount-effect"></div>
          </div>
          
          <div class="bonus-items">
            <div class="bonus-item" v-for="(bonus, index) in bonuses" :key="index" :style="{ animationDelay: index * 0.2 + 's' }">
              <span class="bonus-icon">{{ bonus.icon }}</span>
              <span class="bonus-text">{{ bonus.text }}</span>
            </div>
          </div>
        </div>
      </div>
      
      <div class="celebration-text">
        <div class="congrats-message">
          🌟 おめでとうございます！ 🌟
        </div>
        <div class="flavor-text">
          あなたの運は最高潮です！
        </div>
      </div>
      
      <div class="action-buttons">
        <button @click="playAgain" class="play-again-btn">
          <span class="btn-icon">🎯</span>
          もう一度遊ぶ
        </button>
        <button @click="celebrate" class="celebrate-btn">
          <span class="btn-icon">🎉</span>
          お祝いする
        </button>
      </div>
    </div>
    
    <!-- 背景エフェクト -->
    <div class="background-effects">
      <div v-for="n in 30" :key="'star-' + n" class="star" :style="getStarStyle(n)">⭐</div>
      <div v-for="n in 20" :key="'sparkle-' + n" class="sparkle" :style="getSparkleStyle(n)">✨</div>
    </div>
    
    <!-- 音楽ビジュアライザー風エフェクト -->
    <div class="music-visualizer">
      <div v-for="n in 12" :key="'bar-' + n" class="visualizer-bar" :style="{ animationDelay: n * 0.1 + 's' }"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SuperLuckyPage',
  data() {
    return {
      animatedAmount: 0,
      targetAmount: 1000000,
      bonuses: [
        { icon: '🎁', text: 'ボーナスゲーム解放' },
        { icon: '💎', text: 'プレミアムアイテム獲得' },
        { icon: '🏆', text: 'チャンピオン称号' },
        { icon: '🚀', text: '次回確率UP' }
      ]
    }
  },
  mounted() {
    this.animateAmount()
    this.playJackpotSound()
  },
  methods: {
    animateAmount() {
      const duration = 3000
      const increment = this.targetAmount / (duration / 50)
      const timer = setInterval(() => {
        this.animatedAmount += increment
        if (this.animatedAmount >= this.targetAmount) {
          this.animatedAmount = this.targetAmount
          clearInterval(timer)
        }
      }, 50)
    },
    getFireworkStyle() {
      return {
        left: Math.random() * 100 + '%',
        top: Math.random() * 100 + '%',
        animationDelay: Math.random() * 2 + 's',
        animationDuration: (Math.random() * 2 + 1) + 's'
      }
    },
    getCoinStyle() {
      return {
        left: Math.random() * 100 + '%',
        animationDelay: Math.random() * 3 + 's',
        animationDuration: (Math.random() * 2 + 2) + 's'
      }
    },
    getStarStyle() {
      return {
        left: Math.random() * 100 + '%',
        top: Math.random() * 100 + '%',
        animationDelay: Math.random() * 5 + 's',
        fontSize: (Math.random() * 20 + 10) + 'px'
      }
    },
    getSparkleStyle() {
      return {
        left: Math.random() * 100 + '%',
        top: Math.random() * 100 + '%',
        animationDelay: Math.random() * 3 + 's',
        fontSize: (Math.random() * 15 + 8) + 'px'
      }
    },
    playAgain() {
      this.$router.push('/')
    },
    celebrate() {
      // 追加のお祝いエフェクトを発動
      this.triggerCelebration()
    },
    triggerCelebration() {
      // 画面全体にお祝いエフェクトを追加
      const container = document.querySelector('.super-lucky-container')
      container.classList.add('mega-celebration')
      setTimeout(() => {
        container.classList.remove('mega-celebration')
      }, 3000)
    },
    playJackpotSound() {
      // 実際のプロジェクトでは音声ファイルを再生
      console.log('🎵 Jackpot sound playing!')
    }
  }
}
</script>

<style scoped>
.super-lucky-container {
  min-height: 100vh;
  background: radial-gradient(ellipse at center, #ff6b6b 0%, #ee5a24 25%, #e74c3c 50%, #c0392b 75%, #922b21 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
  animation: backgroundPulse 4s ease-in-out infinite;
}

@keyframes backgroundPulse {
  0%, 100% { filter: brightness(1); }
  50% { filter: brightness(1.2); }
}

.fireworks {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 1;
}

.firework {
  position: absolute;
  width: 4px;
  height: 4px;
  background: radial-gradient(circle, #fff 0%, #f39c12 50%, transparent 100%);
  border-radius: 50%;
  animation: fireworkExplode 2s ease-out infinite;
}

@keyframes fireworkExplode {
  0% {
    transform: scale(0);
    opacity: 1;
  }
  50% {
    transform: scale(1);
    opacity: 1;
  }
  100% {
    transform: scale(3);
    opacity: 0;
  }
}

.jackpot-display {
  background: rgba(0, 0, 0, 0.8);
  border-radius: 30px;
  padding: 40px;
  text-align: center;
  border: 5px solid #f39c12;
  box-shadow: 
    0 0 50px rgba(243, 156, 18, 0.8),
    inset 0 0 30px rgba(255, 255, 255, 0.1);
  z-index: 2;
  position: relative;
  max-width: 800px;
  width: 90%;
}

.jackpot-header {
  margin-bottom: 30px;
}

.jackpot-title {
  font-size: 4em;
  font-weight: bold;
  margin: 0;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.title-part {
  display: inline-block;
  color: #f39c12;
  text-shadow: 
    0 0 10px #fff,
    0 0 20px #f39c12,
    0 0 30px #f39c12,
    0 0 40px #f39c12;
  animation: titleBounce 1s ease-in-out infinite;
}

.title-part:nth-child(1) { animation-delay: 0s; }
.title-part:nth-child(2) { animation-delay: 0.1s; }
.title-part:nth-child(3) { animation-delay: 0.2s; }
.title-part:nth-child(4) { animation-delay: 0.3s; }
.title-part:nth-child(5) { animation-delay: 0.4s; }
.title-part:nth-child(6) { animation-delay: 0.5s; }

@keyframes titleBounce {
  0%, 50%, 100% { transform: translateY(0) scale(1); }
  25% { transform: translateY(-20px) scale(1.1); }
}

.subtitle {
  font-size: 2em;
  color: #fff;
  font-weight: bold;
  text-shadow: 0 0 20px #f39c12;
  animation: subtitleGlow 2s ease-in-out infinite alternate;
}

@keyframes subtitleGlow {
  from { text-shadow: 0 0 20px #f39c12; }
  to { text-shadow: 0 0 30px #f39c12, 0 0 40px #ff6b6b; }
}

.prize-display {
  margin: 40px 0;
  position: relative;
}

.coin-rain {
  position: absolute;
  top: -100px;
  left: 0;
  width: 100%;
  height: 200px;
  pointer-events: none;
}

.coin {
  position: absolute;
  font-size: 24px;
  animation: coinFall 4s linear infinite;
}

@keyframes coinFall {
  0% {
    transform: translateY(-100px) rotate(0deg);
    opacity: 1;
  }
  100% {
    transform: translateY(300px) rotate(360deg);
    opacity: 0;
  }
}

.prize-amount {
  background: linear-gradient(45deg, #f39c12, #e67e22);
  border-radius: 20px;
  padding: 30px;
  margin: 20px 0;
  position: relative;
  overflow: hidden;
}

.amount-label {
  color: #fff;
  font-size: 1.2em;
  margin-bottom: 10px;
}

.amount-value {
  color: #fff;
  font-size: 3.5em;
  font-weight: bold;
  text-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
  animation: amountPulse 1.5s ease-in-out infinite;
}

@keyframes amountPulse {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.05); }
}

.amount-effect {
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.3), transparent);
  animation: amountSweep 3s linear infinite;
}

@keyframes amountSweep {
  0% { left: -100%; }
  100% { left: 100%; }
}

.bonus-items {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 15px;
  margin-top: 30px;
}

.bonus-item {
  background: rgba(255, 255, 255, 0.1);
  padding: 15px;
  border-radius: 15px;
  border: 2px solid #f39c12;
  animation: bonusSlideIn 0.8s ease-out forwards;
  opacity: 0;
  transform: translateY(50px);
}

@keyframes bonusSlideIn {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.bonus-icon {
  font-size: 1.5em;
  margin-right: 10px;
}

.bonus-text {
  color: #fff;
  font-weight: bold;
}

.celebration-text {
  margin: 30px 0;
}

.congrats-message {
  font-size: 2em;
  color: #f39c12;
  font-weight: bold;
  text-shadow: 0 0 15px #fff;
  margin-bottom: 10px;
  animation: congratsPulse 2s ease-in-out infinite;
}

@keyframes congratsPulse {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.02); }
}

.flavor-text {
  font-size: 1.3em;
  color: #fff;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}

.action-buttons {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 40px;
}

.play-again-btn, .celebrate-btn {
  padding: 15px 30px;
  font-size: 1.2em;
  font-weight: bold;
  border: none;
  border-radius: 15px;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
}

.play-again-btn {
  background: linear-gradient(45deg, #27ae60, #2ecc71);
  color: white;
}

.celebrate-btn {
  background: linear-gradient(45deg, #8e44ad, #9b59b6);
  color: white;
}

.play-again-btn:hover, .celebrate-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
}

.btn-icon {
  margin-right: 8px;
}

.background-effects {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 0;
}

.star, .sparkle {
  position: absolute;
  animation: twinkle 3s ease-in-out infinite;
}

@keyframes twinkle {
  0%, 100% { opacity: 0.3; transform: scale(1); }
  50% { opacity: 1; transform: scale(1.2); }
}

.music-visualizer {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 5px;
  z-index: 1;
}

.visualizer-bar {
  width: 6px;
  height: 30px;
  background: linear-gradient(to top, #f39c12, #e74c3c);
  border-radius: 3px;
  animation: musicBeat 0.8s ease-in-out infinite;
}

@keyframes musicBeat {
  0%, 100% { height: 20px; }
  50% { height: 60px; }
}

.mega-celebration {
  animation: megaCelebration 3s ease-in-out;
}

@keyframes megaCelebration {
  0%, 100% { transform: scale(1) rotate(0deg); }
  25% { transform: scale(1.02) rotate(1deg); }
  50% { transform: scale(1.05) rotate(-1deg); }
  75% { transform: scale(1.02) rotate(0.5deg); }
}

/* レスポンシブ対応 */
@media (max-width: 768px) {
  .jackpot-title {
    font-size: 2.5em;
  }
  
  .amount-value {
    font-size: 2.5em;
  }
  
  .action-buttons {
    flex-direction: column;
    align-items: center;
  }
  
  .bonus-items {
    grid-template-columns: 1fr;
  }
}
</style>
