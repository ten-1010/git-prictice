<template>
  <div class="normal-container">
    <!-- 背景パーティクル -->
    <div class="particles">
      <div v-for="n in 30" :key="n" class="particle" :style="getParticleStyle(n)"></div>
    </div>
    
    <div class="normal-display">
      <div class="normal-header">
        <h1 class="normal-title">
          <span class="title-emoji">😊</span>
          <span class="title-text">普通の結果</span>
          <span class="title-emoji">😊</span>
        </h1>
        <div class="subtitle">Not Bad!</div>
      </div>
      
      <div class="result-card">
        <div class="card-content">
          <div class="emoji-display">
            <div class="main-emoji">🎯</div>
            <div class="floating-emojis">
              <span v-for="emoji in floatingEmojis" :key="emoji.id" class="floating-emoji" :style="emoji.style">
                {{ emoji.icon }}
              </span>
            </div>
          </div>
          
          <div class="message-section">
            <h2 class="main-message">まずまずの結果です！</h2>
            <p class="sub-message">
              完璧ではありませんが、悪くない結果ですね。<br>
              次回はもっと良い結果が期待できるかもしれません！
            </p>
          </div>
          
          <div class="stats-display">
            <div class="stat-item" v-for="stat in stats" :key="stat.label">
              <div class="stat-icon">{{ stat.icon }}</div>
              <div class="stat-content">
                <div class="stat-label">{{ stat.label }}</div>
                <div class="stat-value">{{ stat.value }}</div>
              </div>
            </div>
          </div>
          
          <div class="encouragement-section">
            <div class="encouragement-text">
              <span class="highlight">{{ encouragementMessage }}</span>
            </div>
            <div class="motivational-quote">
              "{{ currentQuote }}"
            </div>
          </div>
        </div>
      </div>
      
      <div class="action-section">
        <button @click="tryAgain" class="try-again-btn">
          <span class="btn-icon">🔄</span>
          もう一度挑戦
        </button>
        <button @click="viewTips" class="tips-btn">
          <span class="btn-icon">💡</span>
          コツを見る
        </button>
      </div>
      
      <div v-if="showTips" class="tips-modal" @click="hideTips">
        <div class="tips-content" @click.stop>
          <h3>🌟 上達のコツ 🌟</h3>
          <ul class="tips-list">
            <li v-for="tip in tips" :key="tip">{{ tip }}</li>
          </ul>
          <button @click="hideTips" class="close-tips-btn">閉じる</button>
        </div>
      </div>
    </div>
    
    <!-- 装飾要素 -->
    <div class="decorative-elements">
      <div class="floating-shapes">
        <div v-for="n in 15" :key="'shape-' + n" class="shape" :style="getShapeStyle(n)"></div>
      </div>
      <div class="ambient-lights">
        <div v-for="n in 8" :key="'light-' + n" class="ambient-light" :style="getLightStyle(n)"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NormalPage',
  data() {
    return {
      showTips: false,
      encouragementMessage: 'Keep Going!',
      currentQuote: '諦めなければ必ず道は開ける',
      floatingEmojis: [],
      stats: [
        { icon: '📊', label: 'スコア', value: '65/100' },
        { icon: '⭐', label: '評価', value: 'B+' },
        { icon: '🎯', label: '精度', value: '70%' },
        { icon: '🚀', label: '次回期待度', value: '80%' }
      ],
      tips: [
        '集中力を高めるために深呼吸をしてみましょう',
        'タイミングを意識することが重要です',
        '過去の結果から学んで改善点を見つけましょう',
        'リラックスした状態で挑戦すると良い結果が出やすいです',
        '練習を重ねることで確実に上達します'
      ],
      quotes: [
        '諦めなければ必ず道は開ける',
        '小さな一歩が大きな変化をもたらす',
        '今日の頑張りが明日の成功につながる',
        '失敗は成功のもと',
        '継続は力なり'
      ]
    }
  },
  mounted() {
    this.startFloatingEmojis()
    this.rotateEncouragementMessage()
    this.rotateQuotes()
  },
  methods: {
    getParticleStyle() {
      return {
        left: Math.random() * 100 + '%',
        top: Math.random() * 100 + '%',
        animationDelay: Math.random() * 5 + 's',
        animationDuration: (Math.random() * 3 + 2) + 's'
      }
    },
    getShapeStyle() {
      return {
        left: Math.random() * 100 + '%',
        top: Math.random() * 100 + '%',
        animationDelay: Math.random() * 4 + 's',
        transform: `scale(${Math.random() * 0.5 + 0.5})`
      }
    },
    getLightStyle() {
      return {
        left: Math.random() * 100 + '%',
        top: Math.random() * 100 + '%',
        animationDelay: Math.random() * 6 + 's'
      }
    },
    startFloatingEmojis() {
      const emojis = ['✨', '🌟', '💫', '⭐', '🌈', '🎈'];
      setInterval(() => {
        if (this.floatingEmojis.length < 10) {
          const emoji = {
            id: Date.now() + Math.random(),
            icon: emojis[Math.floor(Math.random() * emojis.length)],
            style: {
              left: Math.random() * 80 + 10 + '%',
              top: Math.random() * 80 + 10 + '%',
              animationDelay: '0s',
              animationDuration: (Math.random() * 2 + 2) + 's'
            }
          };
          this.floatingEmojis.push(emoji);
          
          setTimeout(() => {
            this.floatingEmojis = this.floatingEmojis.filter(e => e.id !== emoji.id);
          }, 4000);
        }
      }, 800);
    },
    rotateEncouragementMessage() {
      const messages = ['Keep Going!', 'You Can Do It!', 'Try Again!', 'Stay Positive!', 'Never Give Up!'];
      let index = 0;
      setInterval(() => {
        index = (index + 1) % messages.length;
        this.encouragementMessage = messages[index];
      }, 3000);
    },
    rotateQuotes() {
      let index = 0;
      setInterval(() => {
        index = (index + 1) % this.quotes.length;
        this.currentQuote = this.quotes[index];
      }, 5000);
    },
    tryAgain() {
      this.$router.push('/');
    },
    viewTips() {
      this.showTips = true;
    },
    hideTips() {
      this.showTips = false;
    }
  }
}
</script>

<style scoped>
.normal-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #74b9ff 0%, #0984e3 50%, #6c5ce7 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
  padding: 20px;
}

.particles {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 1;
}

.particle {
  position: absolute;
  width: 4px;
  height: 4px;
  background: rgba(255, 255, 255, 0.6);
  border-radius: 50%;
  animation: particleFloat 5s ease-in-out infinite;
}

@keyframes particleFloat {
  0%, 100% {
    transform: translateY(0px) rotate(0deg);
    opacity: 0.6;
  }
  50% {
    transform: translateY(-20px) rotate(180deg);
    opacity: 1;
  }
}

.normal-display {
  background: rgba(255, 255, 255, 0.95);
  border-radius: 25px;
  padding: 40px;
  text-align: center;
  box-shadow: 
    0 20px 40px rgba(0, 0, 0, 0.2),
    0 0 30px rgba(116, 185, 255, 0.3);
  z-index: 2;
  position: relative;
  max-width: 700px;
  width: 100%;
  backdrop-filter: blur(10px);
}

.normal-header {
  margin-bottom: 30px;
}

.normal-title {
  font-size: 2.8em;
  font-weight: bold;
  margin: 0;
  color: #2d3436;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 15px;
}

.title-emoji {
  animation: titleWiggle 2s ease-in-out infinite;
}

.title-text {
  background: linear-gradient(45deg, #74b9ff, #6c5ce7);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

@keyframes titleWiggle {
  0%, 100% { transform: rotate(-5deg); }
  50% { transform: rotate(5deg); }
}

.subtitle {
  font-size: 1.5em;
  color: #636e72;
  font-weight: 600;
  margin-top: 10px;
  animation: subtitleFade 3s ease-in-out infinite;
}

@keyframes subtitleFade {
  0%, 100% { opacity: 0.7; }
  50% { opacity: 1; }
}

.result-card {
  background: linear-gradient(145deg, #ffffff, #f8f9fa);
  border-radius: 20px;
  padding: 30px;
  margin: 30px 0;
  box-shadow: 
    inset 0 2px 10px rgba(116, 185, 255, 0.1),
    0 5px 15px rgba(0, 0, 0, 0.05);
}

.emoji-display {
  position: relative;
  margin-bottom: 25px;
}

.main-emoji {
  font-size: 4em;
  animation: mainEmojiPulse 2s ease-in-out infinite;
}

@keyframes mainEmojiPulse {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.1); }
}

.floating-emojis {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
}

.floating-emoji {
  position: absolute;
  font-size: 1.5em;
  animation: floatUp 4s ease-out forwards;
}

@keyframes floatUp {
  0% {
    opacity: 1;
    transform: translateY(0px) scale(1);
  }
  100% {
    opacity: 0;
    transform: translateY(-100px) scale(0.5);
  }
}

.message-section {
  margin: 25px 0;
}

.main-message {
  font-size: 1.8em;
  color: #2d3436;
  font-weight: bold;
  margin-bottom: 15px;
}

.sub-message {
  font-size: 1.1em;
  color: #636e72;
  line-height: 1.6;
  margin: 0;
}

.stats-display {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 15px;
  margin: 30px 0;
}

.stat-item {
  background: linear-gradient(135deg, #74b9ff, #6c5ce7);
  color: white;
  padding: 20px;
  border-radius: 15px;
  display: flex;
  align-items: center;
  gap: 15px;
  transition: transform 0.3s ease;
  animation: statSlideIn 0.8s ease-out forwards;
  opacity: 0;
  transform: translateY(30px);
}

.stat-item:nth-child(1) { animation-delay: 0.1s; }
.stat-item:nth-child(2) { animation-delay: 0.2s; }
.stat-item:nth-child(3) { animation-delay: 0.3s; }
.stat-item:nth-child(4) { animation-delay: 0.4s; }

@keyframes statSlideIn {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.stat-item:hover {
  transform: translateY(-3px);
}

.stat-icon {
  font-size: 1.5em;
}

.stat-content {
  text-align: left;
}

.stat-label {
  font-size: 0.9em;
  opacity: 0.9;
}

.stat-value {
  font-size: 1.2em;
  font-weight: bold;
}

.encouragement-section {
  margin: 30px 0;
  padding: 25px;
  background: linear-gradient(135deg, #00b894, #00cec9);
  border-radius: 15px;
  color: white;
}

.encouragement-text {
  font-size: 1.4em;
  font-weight: bold;
  margin-bottom: 15px;
}

.highlight {
  animation: highlightPulse 2s ease-in-out infinite;
}

@keyframes highlightPulse {
  0%, 100% { text-shadow: 0 0 10px rgba(255, 255, 255, 0.5); }
  50% { text-shadow: 0 0 20px rgba(255, 255, 255, 0.8); }
}

.motivational-quote {
  font-size: 1.1em;
  font-style: italic;
  opacity: 0.9;
}

.action-section {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 30px;
}

.try-again-btn, .tips-btn {
  padding: 15px 30px;
  font-size: 1.1em;
  font-weight: bold;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
}

.try-again-btn {
  background: linear-gradient(45deg, #00b894, #00cec9);
  color: white;
}

.tips-btn {
  background: linear-gradient(45deg, #fdcb6e, #e17055);
  color: white;
}

.try-again-btn:hover, .tips-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
}

.btn-icon {
  margin-right: 8px;
}

.tips-modal {
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

@keyframes modalFadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

.tips-content {
  background: white;
  padding: 40px;
  border-radius: 20px;
  max-width: 500px;
  width: 90%;
  text-align: left;
  animation: modalSlideIn 0.3s ease-out;
}

@keyframes modalSlideIn {
  from { transform: translateY(-50px); opacity: 0; }
  to { transform: translateY(0); opacity: 1; }
}

.tips-content h3 {
  text-align: center;
  color: #2d3436;
  margin-bottom: 25px;
  font-size: 1.5em;
}

.tips-list {
  list-style: none;
  padding: 0;
}

.tips-list li {
  padding: 10px 0;
  border-bottom: 1px solid #eee;
  color: #636e72;
  position: relative;
  padding-left: 25px;
}

.tips-list li:before {
  content: '💡';
  position: absolute;
  left: 0;
}

.close-tips-btn {
  display: block;
  margin: 25px auto 0;
  padding: 10px 25px;
  background: linear-gradient(45deg, #74b9ff, #6c5ce7);
  color: white;
  border: none;
  border-radius: 15px;
  cursor: pointer;
  font-weight: bold;
}

.decorative-elements {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 0;
}

.floating-shapes {
  position: absolute;
  width: 100%;
  height: 100%;
}

.shape {
  position: absolute;
  width: 20px;
  height: 20px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 50%;
  animation: shapeFloat 6s ease-in-out infinite;
}

@keyframes shapeFloat {
  0%, 100% { transform: translateY(0px) rotate(0deg); }
  50% { transform: translateY(-30px) rotate(180deg); }
}

.ambient-lights {
  position: absolute;
  width: 100%;
  height: 100%;
}

.ambient-light {
  position: absolute;
  width: 50px;
  height: 50px;
  background: radial-gradient(circle, rgba(116, 185, 255, 0.3), transparent);
  border-radius: 50%;
  animation: lightPulse 4s ease-in-out infinite;
}

@keyframes lightPulse {
  0%, 100% { opacity: 0.3; transform: scale(1); }
  50% { opacity: 0.6; transform: scale(1.2); }
}

/* レスポンシブ対応 */
@media (max-width: 768px) {
  .normal-title {
    font-size: 2.2em;
    flex-direction: column;
    gap: 10px;
  }
  
  .action-section {
    flex-direction: column;
    align-items: center;
  }
  
  .stats-display {
    grid-template-columns: 1fr;
  }
  
  .tips-content {
    padding: 30px 20px;
  }
}
</style>
