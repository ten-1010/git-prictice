<template>
  <div class="hazure-container">
    <!-- 雨エフェクト -->
    <div class="rain-effect">
      <div v-for="n in 50" :key="n" class="raindrop" :style="getRainStyle(n)"></div>
    </div>

    <!-- 雷エフェクト -->
    <div class="lightning" :class="{ active: lightningActive }"></div>

    <div class="hazure-display">
      <div class="hazure-header">
        <h1 class="hazure-title">
          <span class="title-part shake">💧</span>
          <span class="title-part shake">ハ</span>
          <span class="title-part shake">ズ</span>
          <span class="title-part shake">レ</span>
          <span class="title-part shake">💧</span>
        </h1>
        <div class="subtitle">Bad Luck...</div>
      </div>

      <div class="result-display">
        <div class="sad-character">
          <div class="character-face">
            <div class="eyes">
              <div class="eye left">
                <div class="pupil"></div>
                <div class="tear"></div>
              </div>
              <div class="eye right">
                <div class="pupil"></div>
                <div class="tear"></div>
              </div>
            </div>
            <div class="mouth"></div>
          </div>
          <div class="character-body">
            <div class="rain-drops">
              <div v-for="n in 10" :key="'drop-' + n" class="drop" :style="getDropStyle(n)">💧</div>
            </div>
          </div>
        </div>

        <div class="message-container">
          <div class="main-message">
            <h2 class="message-text">{{ currentMessage }}</h2>
            <div class="message-indicator">
              <div v-for="n in messages.length" :key="n" class="dot" :class="{ active: n - 1 === messageIndex }"></div>
            </div>
          </div>

          <div class="comfort-section">
            <div class="comfort-text">
              <p>大丈夫です！これも経験の一つです。</p>
              <p>次回はきっと良い結果が待っています！</p>
            </div>

            <div class="stats-breakdown">
              <h3>今回の結果分析</h3>
              <div class="stat-bars">
                <div class="stat-bar" v-for="stat in stats" :key="stat.label">
                  <div class="stat-info">
                    <span class="stat-label">{{ stat.label }}</span>
                    <span class="stat-percentage">{{ stat.value }}%</span>
                  </div>
                  <div class="bar-container">
                    <div class="bar-fill" :style="{ width: stat.value + '%', backgroundColor: stat.color }"></div>
                  </div>
                </div>
              </div>
            </div>

            <div class="improvement-tips">
              <h3>💡 改善のヒント</h3>
              <div class="tips-carousel">
                <div class="tip-card" v-for="(tip, index) in improvementTips" :key="index" :class="{ active: index === activeTipIndex }">
                  <div class="tip-icon">{{ tip.icon }}</div>
                  <div class="tip-text">{{ tip.text }}</div>
                </div>
              </div>
              <div class="carousel-controls">
                <button @click="previousTip" class="carousel-btn">‹</button>
                <button @click="nextTip" class="carousel-btn">›</button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="motivational-quote">
        <div class="quote-content">
          <span class="quote-mark">"</span>
          <span class="quote-text">{{ currentQuote.text }}</span>
          <span class="quote-mark">"</span>
        </div>
        <div class="quote-author">- {{ currentQuote.author }}</div>
      </div>

      <div class="action-buttons">
        <button @click="tryAgain" class="retry-btn">
          <span class="btn-icon">🔄</span>
          リトライ
        </button>
        <button @click="cheerUp" class="cheer-btn" :class="{ cheering: isCheering }">
          <span class="btn-icon">💪</span>
          {{ isCheering ? '頑張って！' : '元気出す' }}
        </button>
        <button @click="analyze" class="analyze-btn">
          <span class="btn-icon">📊</span>
          分析を見る
        </button>
      </div>
    </div>

    <!-- 背景の憂鬱エフェクト -->
    <div class="melancholy-effects">
      <div v-for="n in 20" :key="'cloud-' + n" class="cloud" :style="getCloudStyle(n)">☁️</div>
    </div>

    <!-- 応援モーダル -->
    <div v-if="showCheerModal" class="cheer-modal" @click="hideCheerModal">
      <div class="cheer-content" @click.stop>
        <h2>🌟 あなたならできる！ 🌟</h2>
        <div class="cheer-messages">
          <div v-for="cheer in cheerMessages" :key="cheer" class="cheer-message">{{ cheer }}</div>
        </div>
        <button @click="hideCheerModal" class="close-cheer-btn">ありがとう！</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HazurePage',
  data() {
    return {
      lightningActive: false,
      messageIndex: 0,
      activeTipIndex: 0,
      isCheering: false,
      showCheerModal: false,
      messages: [
        '今回は運が悪かったですね...',
        'でも諦めないでください！',
        '次回は必ずチャンスがあります！',
        'この経験を活かしましょう！'
      ],
      currentMessage: '',
      stats: [
        { label: 'ラッキー度', value: 15, color: '#e74c3c' },
        { label: 'タイミング', value: 25, color: '#f39c12' },
        { label: '集中力', value: 45, color: '#f1c40f' },
        { label: '次回期待度', value: 85, color: '#27ae60' }
      ],
      improvementTips: [
        { icon: '🧘', text: 'リラックスして心を落ち着けましょう' },
        { icon: '⏰', text: 'タイミングを意識することが大切です' },
        { icon: '🔍', text: 'パターンを観察して学習しましょう' },
        { icon: '💭', text: 'ポジティブ思考を心がけましょう' },
        { icon: '🎯', text: '目標を明確にして集中しましょう' }
      ],
      quotes: [
        { text: '失敗は成功のもと', author: '日本のことわざ' },
        { text: '七転び八起き', author: '日本のことわざ' },
        { text: '雨降って地固まる', author: '日本のことわざ' },
        { text: '石の上にも三年', author: '日本のことわざ' },
        { text: '継続は力なり', author: '日本のことわざ' }
      ],
      currentQuote: {},
      cheerMessages: [
        '🌈 虹は雨の後に出るものです',
        '⭐ あなたの可能性は無限大です',
        '🚀 今日の失敗は明日の成功への一歩',
        '💎 困難が人を磨きます',
        '🌸 新しいチャンスが必ず来ます'
      ]
    }
  },
  mounted() {
    this.startLightningEffect()
    this.startMessageRotation()
    this.startTipCarousel()
    this.rotateQuotes()
    this.currentMessage = this.messages[0]
    this.currentQuote = this.quotes[0]
  },
  methods: {
    getRainStyle() {
      return {
        left: Math.random() * 100 + '%',
        animationDelay: Math.random() * 2 + 's',
        animationDuration: (Math.random() * 1 + 1) + 's'
      }
    },
    getDropStyle() {
      return {
        animationDelay: Math.random() * 2 + 's',
        left: Math.random() * 100 + '%'
      }
    },
    getCloudStyle() {
      return {
        left: Math.random() * 100 + '%',
        top: Math.random() * 100 + '%',
        animationDelay: Math.random() * 10 + 's',
        fontSize: (Math.random() * 20 + 15) + 'px',
        opacity: Math.random() * 0.3 + 0.1
      }
    },
    startLightningEffect() {
      setInterval(() => {
        if (Math.random() < 0.1) { // 10%の確率で雷
          this.lightningActive = true
          setTimeout(() => {
            this.lightningActive = false
          }, 200)
        }
      }, 3000)
    },
    startMessageRotation() {
      setInterval(() => {
        this.messageIndex = (this.messageIndex + 1) % this.messages.length
        this.currentMessage = this.messages[this.messageIndex]
      }, 4000)
    },
    startTipCarousel() {
      setInterval(() => {
        this.activeTipIndex = (this.activeTipIndex + 1) % this.improvementTips.length
      }, 5000)
    },
    rotateQuotes() {
      let index = 0
      setInterval(() => {
        index = (index + 1) % this.quotes.length
        this.currentQuote = this.quotes[index]
      }, 8000)
    },
    previousTip() {
      this.activeTipIndex = this.activeTipIndex === 0 ? this.improvementTips.length - 1 : this.activeTipIndex - 1
    },
    nextTip() {
      this.activeTipIndex = (this.activeTipIndex + 1) % this.improvementTips.length
    },
    tryAgain() {
      this.$router.push('/')
    },
    cheerUp() {
      this.isCheering = true
      this.showCheerModal = true
      setTimeout(() => {
        this.isCheering = false
      }, 2000)
    },
    hideCheerModal() {
      this.showCheerModal = false
    },
    analyze() {
      // 分析画面への遷移やモーダル表示
      alert('詳細な分析機能は開発中です 📊')
    }
  }
}
</script>

<style scoped>
.hazure-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #2c3e50 0%, #34495e 25%, #4a5568 50%, #2d3748 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
  padding: 20px;
}

.rain-effect {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 1;
}

.raindrop {
  position: absolute;
  width: 2px;
  height: 15px;
  background: linear-gradient(to bottom, rgba(173, 216, 230, 0.8), rgba(173, 216, 230, 0.2));
  animation: rainFall 2s linear infinite;
}

@keyframes rainFall {
  0% {
    transform: translateY(-100vh);
    opacity: 1;
  }
  100% {
    transform: translateY(100vh);
    opacity: 0;
  }
}

.lightning {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.9);
  pointer-events: none;
  opacity: 0;
  z-index: 3;
}

.lightning.active {
  opacity: 1;
  animation: lightningFlash 0.2s ease-out;
}

@keyframes lightningFlash {
  0%, 100% { opacity: 0; }
  50% { opacity: 0.9; }
}

.hazure-display {
  background: rgba(0, 0, 0, 0.85);
  border-radius: 25px;
  padding: 40px;
  text-align: center;
  border: 3px solid #7f8c8d;
  box-shadow:
    0 20px 40px rgba(0, 0, 0, 0.5),
    inset 0 2px 10px rgba(255, 255, 255, 0.05);
  z-index: 2;
  position: relative;
  max-width: 800px;
  width: 100%;
  backdrop-filter: blur(10px);
}

.hazure-header {
  margin-bottom: 30px;
}

.hazure-title {
  font-size: 3em;
  font-weight: bold;
  margin: 0;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.title-part {
  display: inline-block;
  color: #95a5a6;
  text-shadow: 0 0 10px rgba(149, 165, 166, 0.5);
}

.shake {
  animation: titleShake 2s ease-in-out infinite;
}

.title-part:nth-child(1) { animation-delay: 0s; }
.title-part:nth-child(2) { animation-delay: 0.1s; }
.title-part:nth-child(3) { animation-delay: 0.2s; }
.title-part:nth-child(4) { animation-delay: 0.3s; }
.title-part:nth-child(5) { animation-delay: 0.4s; }

@keyframes titleShake {
  0%, 100% { transform: translateX(0) rotate(0deg); }
  25% { transform: translateX(-2px) rotate(-1deg); }
  75% { transform: translateX(2px) rotate(1deg); }
}

.subtitle {
  font-size: 1.5em;
  color: #7f8c8d;
  font-weight: 600;
  margin-top: 10px;
  animation: subtitleFade 3s ease-in-out infinite;
}

@keyframes subtitleFade {
  0%, 100% { opacity: 0.5; }
  50% { opacity: 0.8; }
}

.result-display {
  margin: 40px 0;
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 40px;
  align-items: start;
}

.sad-character {
  position: relative;
}

.character-face {
  width: 120px;
  height: 120px;
  background: #f39c12;
  border-radius: 50%;
  margin: 0 auto 20px;
  position: relative;
  animation: characterBob 3s ease-in-out infinite;
}

@keyframes characterBob {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-10px); }
}

.eyes {
  position: absolute;
  top: 35px;
  width: 100%;
  display: flex;
  justify-content: space-around;
}

.eye {
  width: 15px;
  height: 20px;
  background: #2c3e50;
  border-radius: 50%;
  position: relative;
}

.pupil {
  width: 8px;
  height: 8px;
  background: #000;
  border-radius: 50%;
  position: absolute;
  top: 6px;
  left: 3px;
  animation: eyeMove 4s ease-in-out infinite;
}

@keyframes eyeMove {
  0%, 100% { transform: translateX(0); }
  25% { transform: translateX(-2px); }
  75% { transform: translateX(2px); }
}

.tear {
  position: absolute;
  width: 4px;
  height: 8px;
  background: #74b9ff;
  border-radius: 0 0 50% 50%;
  bottom: -8px;
  left: 50%;
  transform: translateX(-50%);
  animation: tearFall 2s ease-in-out infinite;
}

@keyframes tearFall {
  0% { transform: translateX(-50%) translateY(0) scale(1); opacity: 1; }
  100% { transform: translateX(-50%) translateY(20px) scale(0.5); opacity: 0; }
}

.mouth {
  position: absolute;
  bottom: 30px;
  left: 50%;
  transform: translateX(-50%);
  width: 30px;
  height: 15px;
  border: 3px solid #2c3e50;
  border-top: none;
  border-radius: 0 0 30px 30px;
}

.character-body {
  position: relative;
}

.rain-drops {
  position: absolute;
  top: -50px;
  left: 0;
  width: 100%;
  height: 100px;
}

.drop {
  position: absolute;
  font-size: 12px;
  animation: dropFall 2s linear infinite;
}

@keyframes dropFall {
  0% { transform: translateY(-20px); opacity: 1; }
  100% { transform: translateY(50px); opacity: 0; }
}

.message-container {
  text-align: left;
}

.main-message {
  margin-bottom: 25px;
}

.message-text {
  color: #ecf0f1;
  font-size: 1.4em;
  margin: 0;
  min-height: 2em;
  display: flex;
  align-items: center;
}

.message-indicator {
  display: flex;
  gap: 8px;
  margin-top: 10px;
}

.dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: #34495e;
  transition: background 0.3s ease;
}

.dot.active {
  background: #3498db;
}

.comfort-section {
  background: rgba(52, 73, 94, 0.3);
  padding: 25px;
  border-radius: 15px;
  margin: 20px 0;
}

.comfort-text {
  color: #bdc3c7;
  line-height: 1.6;
  margin-bottom: 25px;
}

.stats-breakdown h3,
.improvement-tips h3 {
  color: #ecf0f1;
  margin-bottom: 15px;
  font-size: 1.2em;
}

.stat-bars {
  margin-bottom: 30px;
}

.stat-bar {
  margin-bottom: 15px;
}

.stat-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
  color: #bdc3c7;
  font-size: 0.9em;
}

.bar-container {
  height: 8px;
  background: #34495e;
  border-radius: 4px;
  overflow: hidden;
}

.bar-fill {
  height: 100%;
  border-radius: 4px;
  transition: width 2s ease-out;
  animation: barFill 2s ease-out;
}

@keyframes barFill {
  from { width: 0; }
}

.tips-carousel {
  height: 80px;
  position: relative;
  overflow: hidden;
  margin-bottom: 15px;
}

.tip-card {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  display: flex;
  align-items: center;
  gap: 15px;
  padding: 15px;
  background: rgba(46, 62, 81, 0.5);
  border-radius: 10px;
  opacity: 0;
  transform: translateX(100%);
  transition: all 0.5s ease;
}

.tip-card.active {
  opacity: 1;
  transform: translateX(0);
}

.tip-icon {
  font-size: 1.5em;
}

.tip-text {
  color: #ecf0f1;
  flex: 1;
}

.carousel-controls {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.carousel-btn {
  width: 30px;
  height: 30px;
  border: none;
  background: #3498db;
  color: white;
  border-radius: 50%;
  cursor: pointer;
  font-size: 1.2em;
  transition: background 0.3s ease;
}

.carousel-btn:hover {
  background: #2980b9;
}

.motivational-quote {
  background: linear-gradient(135deg, #8e44ad, #9b59b6);
  padding: 25px;
  border-radius: 15px;
  margin: 30px 0;
}

.quote-content {
  color: white;
  font-size: 1.2em;
  font-style: italic;
  margin-bottom: 10px;
}

.quote-mark {
  font-size: 1.5em;
  opacity: 0.7;
}

.quote-author {
  color: rgba(255, 255, 255, 0.8);
  text-align: right;
  font-size: 0.9em;
}

.action-buttons {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin-top: 30px;
  flex-wrap: wrap;
}

.retry-btn, .cheer-btn, .analyze-btn {
  padding: 12px 25px;
  font-size: 1em;
  font-weight: bold;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
}

.retry-btn {
  background: linear-gradient(45deg, #27ae60, #2ecc71);
  color: white;
}

.cheer-btn {
  background: linear-gradient(45deg, #f39c12, #e67e22);
  color: white;
}

.cheer-btn.cheering {
  animation: cheerPulse 0.5s ease-in-out;
}

@keyframes cheerPulse {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.1); }
}

.analyze-btn {
  background: linear-gradient(45deg, #3498db, #2980b9);
  color: white;
}

.retry-btn:hover, .cheer-btn:hover, .analyze-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
}

.btn-icon {
  margin-right: 8px;
}

.melancholy-effects {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 0;
}

.cloud {
  position: absolute;
  animation: cloudDrift 20s linear infinite;
}

@keyframes cloudDrift {
  0% { transform: translateX(-10%); }
  100% { transform: translateX(110%); }
}

.cheer-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  animation: modalFadeIn 0.3s ease-out;
}

.cheer-content {
  background: linear-gradient(135deg, #ff7675, #fd79a8);
  padding: 40px;
  border-radius: 20px;
  text-align: center;
  color: white;
  max-width: 400px;
  width: 90%;
  animation: cheerModalBounce 0.5s ease-out;
}

@keyframes cheerModalBounce {
  0% { transform: scale(0.8); opacity: 0; }
  100% { transform: scale(1); opacity: 1; }
}

.cheer-content h2 {
  margin-bottom: 25px;
  font-size: 1.8em;
}

.cheer-messages {
  margin-bottom: 25px;
}

.cheer-message {
  margin: 10px 0;
  font-size: 1.1em;
  animation: cheerMessageSlide 0.5s ease-out forwards;
  opacity: 0;
  transform: translateY(20px);
}

.cheer-message:nth-child(1) { animation-delay: 0.1s; }
.cheer-message:nth-child(2) { animation-delay: 0.2s; }
.cheer-message:nth-child(3) { animation-delay: 0.3s; }
.cheer-message:nth-child(4) { animation-delay: 0.4s; }
.cheer-message:nth-child(5) { animation-delay: 0.5s; }

@keyframes cheerMessageSlide {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.close-cheer-btn {
  background: rgba(255, 255, 255, 0.2);
  color: white;
  border: 2px solid white;
  padding: 12px 25px;
  border-radius: 15px;
  cursor: pointer;
  font-weight: bold;
  transition: all 0.3s ease;
}

.close-cheer-btn:hover {
  background: white;
  color: #ff7675;
}

/* レスポンシブ対応 */
@media (max-width: 768px) {
  .hazure-title {
    font-size: 2.2em;
  }

  .result-display {
    grid-template-columns: 1fr;
    gap: 30px;
    text-align: center;
  }

  .action-buttons {
    flex-direction: column;
    align-items: center;
  }

  .cheer-content {
    padding: 30px 20px;
  }
}
</style>
