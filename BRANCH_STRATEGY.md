# ブランチ戦略

## 概要
このプロジェクトでは、機能ベースのブランチ戦略を採用しています。各機能は専用のブランチで開発し、完了後にmainブランチにマージします。

## ブランチ一覧

### バックエンド機能ブランチ
- `feature/backend-user-authentication` - ユーザー認証機能の実装
- `feature/backend-rest-api` - REST API エンドポイントの開発
- `feature/backend-database-integration` - データベース統合機能
- `feature/backend-security-config` - セキュリティ設定とミドルウェア
- `feature/backend-api-foundation` - 既存のAPI基盤機能

### フロントエンド機能ブランチ
- `feature/frontend-user-interface` - UIコンポーネントとデザイン
- `feature/frontend-authentication` - 認証関連のフロントエンド機能
- `feature/frontend-api-integration` - バックエンドAPIとの連携
- `feature/frontend-routing-navigation` - Vue.js ルーティングとナビゲーション

### 共通機能・設定ブランチ
- `feature/ci-cd-pipeline` - GitHub Actions等のCI/CD設定
- `feature/docker-containerization` - Docker化とコンテナ設定
- `feature/testing-framework` - テストフレームワークと自動テスト設定

## ワークフロー

1. **機能開発開始**
   ```bash
   git checkout main
   git pull origin main
   git checkout feature/[機能名]
   ```

2. **開発作業**
   - 機能の実装
   - テストの作成
   - ドキュメントの更新

3. **コミットとプッシュ**
   ```bash
   git add .
   git commit -m "feat: [機能の説明]"
   git push origin feature/[機能名]
   ```

4. **プルリクエスト作成**
   - GitHubでプルリクエストを作成
   - コードレビューを実施
   - テストが通ることを確認

5. **マージ**
   - レビュー承認後、mainブランチにマージ
   - 機能ブランチを削除

## コミットメッセージ規約

- `feat:` - 新機能の追加
- `fix:` - バグ修正
- `docs:` - ドキュメントのみの変更
- `style:` - コードの意味に影響しない変更（空白、フォーマット等）
- `refactor:` - バグ修正や機能追加ではないコード変更
- `test:` - テストの追加や既存テストの修正
- `chore:` - ビルドプロセスやツール、ライブラリの変更

## 注意事項

- mainブランチは常にデプロイ可能な状態を保つ
- 機能ブランチは定期的にmainからrebaseする
- 大きな機能は小さな単位に分割して開発する
- プルリクエストには適切な説明とテスト結果を含める
