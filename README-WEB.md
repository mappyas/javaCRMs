# CRM Webアプリケーション 🌐

Spring BootとThymeleafを使用した、モダンなWeb版の顧客管理システム（CRM）です。

## 🎯 機能

### 主要機能
- ✅ **顧客一覧表示** - 全顧客を見やすい表形式で表示
- ✅ **顧客詳細表示** - 個別顧客の詳細情報を表示
- ✅ **顧客登録** - 新規顧客情報を入力フォームから登録
- ✅ **顧客編集** - 既存顧客情報を更新
- ✅ **顧客削除** - 不要な顧客データを削除（確認あり）
- ✅ **顧客検索** - 名前、会社名、メールアドレスで検索
- ✅ **統計情報** - 顧客数や会社別統計をビジュアル表示
- ✅ **データ永続化** - H2データベースで自動保存
- ✅ **レスポンシブデザイン** - スマートフォンにも対応

### 管理する顧客情報
- 顧客ID（自動採番）
- 名前（必須）
- メールアドレス（必須）
- 電話番号
- 会社名
- 住所
- 登録日時（自動記録）
- 最終連絡日時（自動更新）
- メモ

## 🏗️ システム構成

```
src/
├── main/
│   ├── java/com/example/crm/
│   │   ├── CrmApplication.java          # メインクラス
│   │   ├── entity/
│   │   │   └── Customer.java            # 顧客エンティティ
│   │   ├── repository/
│   │   │   └── CustomerRepository.java  # データアクセス層
│   │   ├── service/
│   │   │   └── CustomerService.java     # ビジネスロジック層
│   │   └── controller/
│   │       └── CustomerController.java  # コントローラー層
│   └── resources/
│       ├── templates/customers/         # Thymeleafテンプレート
│       │   ├── list.html               # 顧客一覧画面
│       │   ├── detail.html             # 顧客詳細画面
│       │   ├── form.html               # 登録/編集フォーム
│       │   └── statistics.html         # 統計情報画面
│       ├── static/css/
│       │   └── style.css               # スタイルシート
│       └── application.properties       # 設定ファイル
└── pom.xml                              # Maven設定
```

## 🛠️ 技術スタック

- **バックエンド**: Spring Boot 3.2.0
- **フロントエンド**: Thymeleaf, HTML5, CSS3
- **データベース**: H2 Database（組み込み）
- **ORM**: Spring Data JPA / Hibernate
- **ビルドツール**: Maven
- **Java バージョン**: 17以上

## 📦 セットアップ

### 必要な環境
- JDK 17以上
- Maven 3.6以上

### インストール手順

1. **プロジェクトをクローン**
   ```bash
   git clone <repository-url>
   cd javasilver
   ```

2. **Mavenで依存関係をインストール**
   ```bash
   mvn clean install
   ```

3. **アプリケーションを起動**
   ```bash
   mvn spring-boot:run
   ```

4. **ブラウザでアクセス**
   ```
   http://localhost:8080/customers
   ```

## 🚀 使い方

### 起動方法

**方法1: Mavenコマンド**
```bash
mvn spring-boot:run
```

**方法2: JARファイルから実行**
```bash
mvn clean package
java -jar target/crm-web-1.0.0.jar
```

起動後、以下のURLにアクセス：
- **メインアプリ**: http://localhost:8080/customers
- **H2コンソール（開発用）**: http://localhost:8080/h2-console

### 画面説明

#### 1. 顧客一覧画面 (`/customers`)
- 全顧客を表形式で表示
- 検索フォームで顧客検索
- 「新規登録」ボタンで顧客追加
- 各行から「詳細」「編集」にアクセス可能

#### 2. 顧客詳細画面 (`/customers/{id}`)
- 選択した顧客の全情報を表示
- 「編集」「削除」ボタンで操作可能
- 削除時には確認ダイアログが表示

#### 3. 登録/編集フォーム (`/customers/new`, `/customers/{id}/edit`)
- 顧客情報の入力フォーム
- 名前とメールは必須項目
- バリデーション機能付き
- エラーがあれば赤字で表示

#### 4. 統計情報画面 (`/customers/statistics`)
- 総顧客数を大きく表示
- 会社別の顧客数をグラフで表示
- 上位10社をランキング表示

### 検索機能

検索タイプを選択して検索可能：
- **全項目検索**: 名前、会社名、メール、電話番号を横断検索
- **名前で検索**: 顧客名のみを検索
- **会社名で検索**: 会社名のみを検索
- **メールで検索**: メールアドレスのみを検索

## 📊 データベース

### H2 Database
- ファイルベースのデータベース
- データは `./data/crmdb.mv.db` に保存
- 開発用コンソールでデータ確認可能

### H2 コンソールへのアクセス
1. http://localhost:8080/h2-console にアクセス
2. 接続情報を入力：
   - JDBC URL: `jdbc:h2:file:./data/crmdb`
   - User Name: `sa`
   - Password: (空欄)
3. 「Connect」をクリック

## 🎨 UIデザイン

- **カラーテーマ**: 紫のグラデーション（モダンでプロフェッショナル）
- **レスポンシブ**: スマートフォン、タブレット、デスクトップに対応
- **直感的な操作**: ボタンにアイコン付き、ホバー効果あり
- **アラート表示**: 操作成功/エラーメッセージを色分けして表示

## 🔧 設定のカスタマイズ

### ポート番号を変更
`src/main/resources/application.properties` を編集：
```properties
server.port=8080  # 好きなポート番号に変更
```

### データベースの場所を変更
```properties
spring.datasource.url=jdbc:h2:file:./data/crmdb  # パスを変更
```

### ログレベルを変更
```properties
logging.level.com.example.crm=DEBUG  # INFO, WARN, ERROR に変更可能
```

## 📱 API エンドポイント

| メソッド | URL | 説明 |
|---------|-----|------|
| GET | `/customers` | 顧客一覧表示 |
| GET | `/customers/new` | 新規登録フォーム |
| POST | `/customers` | 顧客登録 |
| GET | `/customers/{id}` | 顧客詳細表示 |
| GET | `/customers/{id}/edit` | 編集フォーム表示 |
| POST | `/customers/{id}` | 顧客更新 |
| POST | `/customers/{id}/delete` | 顧客削除 |
| GET | `/customers/search` | 顧客検索 |
| GET | `/customers/statistics` | 統計情報表示 |

## 🧪 開発モード

Spring Boot DevToolsが有効になっているため、開発中の変更が自動で反映されます。

### 自動リロード対象
- Javaクラスの変更
- Thymeleafテンプレートの変更
- application.propertiesの変更

## 🔒 セキュリティ

現在はBasic認証などのセキュリティは実装されていません。本番環境で使用する場合は、Spring Securityを追加することをお勧めします。

## 🚀 本番環境へのデプロイ

### JARファイルの作成
```bash
mvn clean package -DskipTests
```

### JARファイルの実行
```bash
java -jar target/crm-web-1.0.0.jar
```

### 本番用設定
`application.properties` で本番用設定に切り替え：
```properties
spring.jpa.hibernate.ddl-auto=validate  # 本番ではupdateではなくvalidateに
spring.h2.console.enabled=false         # H2コンソールを無効化
logging.level.root=WARN                 # ログレベルを下げる
```

## 📝 今後の拡張案

- [ ] ユーザー認証・権限管理（Spring Security）
- [ ] MySQL/PostgreSQLへの移行
- [ ] RESTful API の追加
- [ ] ファイルアップロード機能
- [ ] CSVエクスポート/インポート
- [ ] メール送信機能
- [ ] 営業活動履歴の記録
- [ ] ダッシュボード機能
- [ ] 顧客タグ/カテゴリ機能
- [ ] 検索結果のページネーション

## 🐛 トラブルシューティング

### ポートが使用中のエラー
```
Web server failed to start. Port 8080 was already in use.
```
→ `application.properties` でポート番号を変更してください。

### データベース接続エラー
→ `./data` ディレクトリに書き込み権限があるか確認してください。

### テンプレートが見つからないエラー
→ `src/main/resources/templates/` ディレクトリが正しく配置されているか確認してください。

## 📄 ライセンス

MIT License

## 👨‍💻 作成者

Java CRM Web Application v1.0

---

**Enjoy your CRM System! 🎉**

