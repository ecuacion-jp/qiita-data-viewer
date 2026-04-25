# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Java コーディングルール

### スタイル標準
- **Google Java Style Guide** 準拠（CheckstyleによりCIで強制）
- インデント: **スペース2つ**（タブ禁止）— `-base` モジュールを除く
- 最大行長: **100文字**（package/import文・URL除く）— **コメントにも適用**
- エンコーディング: **UTF-8**

### インポート
- ワイルドカードインポート（`.*`）は**禁止**
- static importを先に書き、1行空けてサードパーティパッケージのimport

### Javadoc
- **`public` および `protected` なクラス・メソッドにはJavadocが必須**
- `@Override`・`Test` アノテーションが付いたメソッドは免除
- 編集したメソッドのJavadocも合わせて更新する

### 名前付け規則（Checkstyleで強制）
- メンバー変数・ローカル変数・メソッド名: `^[a-z][a-z0-9][a-zA-Z0-9]*$`（最低2文字）
- パラメータ名: `^[a-z]([a-z0-9][a-zA-Z0-9]*)?$`（1文字も可）
- 略語はアッパーキャメルではなく通常のキャメルケースで

## ビルド検証

**Javaファイルを編集したら必ず以下を実行し、違反を修正してから完了とする:**

```bash
mvn checkstyle:check spotbugs:check
mvn javadoc:javadoc
```

よくある違反:
- Checkstyle: 100文字超の行（コメント・Javadocも含む）
- Checkstyle: `public`/`protected` メンバーへのJavadoc不足
- Checkstyle: ワイルドカードインポート
- SpotBugs: `core` と `batch` モジュールで有効（`base` では無効）

## ビルド・実行コマンド

```bash
# 全モジュールビルド（ローカル環境）
mvn clean install

# 本番環境向けビルド
mvn clean install -P prod

# 特定モジュールのみビルド
mvn clean install -pl qiita-data-viewer-core
mvn clean install -pl qiita-data-viewer-batch
mvn clean install -pl qiita-data-viewer-web

# テスト実行（テストクラスは現在存在しない）
mvn test

# Batchアプリ起動
cd qiita-data-viewer-batch && mvn spring-boot:run

# Webアプリ起動（開発時）
cd qiita-data-viewer-web && mvn spring-boot:run
```

## アーキテクチャ概要

### モジュール構成（依存順）

```
base → core → batch
              └──── web
```

- **base**: エンティティ・リポジトリ基底クラス・共通定数。PostgreSQL/JPA依存。Checkstyleはスキップ。
- **core**: Qiita API v2のモデルクラス（`QiitaItem`・`QiitaUser`・`QiitaTag`・`QiitaGroup`・`QiitaTeamMembership`）・ビジネスロジック。Spring Boot Web Services依存（外部HTTPアクセス用）。
- **batch**: Spring Batchアプリ（jar）。Qiita API v2からユーザの記事一覧を取得し、PV数・いいね数を表示する。
- **web**: Spring Boot Webアプリ（war）。Thymeleafによる画面UI。

### 環境設定

各モジュールの `src/envs/local/` と `src/envs/prod/` に環境別の `application-profile.properties` がある。Mavenプロファイルでどちらをパッケージするかを制御する（デフォルト: local）。

### 外部サービス統合

- Qiita API v2: `https://qiita.com/api/v2/` — アクセストークン（`read_qiita` スコープ）が必要

### データベース

- PostgreSQL、`ecuacion-splib-jpa` 経由でJPA利用
- テーブル自動生成: `spring.jpa.hibernate.ddl-auto=update`（設定ファイル参照）

### 親フレームワーク

`ecuacion-splib`（別リポジトリ: `../ecuacion-splib`）を親pomとして使用。`SplibBatchApplication`・`SplibWebApplication` などの基底クラスを提供している。

## 主要な設定ファイル

| ファイル | 内容 |
|---|---|
| `*/src/main/resources/application.properties` | Spring設定 |
| `*/src/envs/local\|prod/resources/application-profile.properties` | 環境別設定 |
| `qiita-data-viewer-core/src/main/resources/version.properties` | バージョン情報 |
| `maven-profiles.txt` | 利用可能なMavenプロファイル一覧 |
