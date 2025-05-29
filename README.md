# 情報科学演習D
Pascal風言語[^1]をCASL2にコンパイルするコンパイラをJavaで作成したものです．一般的にコンパイラでは以下の手順でコンパイルをします．
1. **字句解析**：入力プログラムからトークン列を切り出し，構文解析や意味解析のための準備を行う．
2. **構文解析**：プログラムが構文的に正しいかを判定する．
3. **意味解析**：プログラムが意味的に正しいかを判定する．
4. **コード生成**：目的言語のコードを生成する．

情報科学演習Dの詳細は以下を参照してください．
- 指導書: [https://dev.ics.es.osaka-u.ac.jp/enshud/shidousho.pdf](https://dev.ics.es.osaka-u.ac.jp/enshud/shidousho.pdf)
- 使用するファイル: [https://dev.ics.es.osaka-u.ac.jp/file-viewer/](https://dev.ics.es.osaka-u.ac.jp/file-viewer/)
- ホームページ: [https://dev.ics.es.osaka-u.ac.jp/enshud/](https://dev.ics.es.osaka-u.ac.jp/enshud/)

[^1]: 関数やレコード構造などのPascalの一部機能が削除された言語．Pascalのエンジン上で実行可能である．Pascal風言語の文法は[指導書](https://dev.ics.es.osaka-u.ac.jp/enshud/shidousho.pdf)の4.1節を参照してください．
