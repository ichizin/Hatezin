package com.ichizin.hatezin;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void イメージURLを取得する() throws Exception {

        String str1 = "<blockquote cite=\"http://postd.cc/npm-and-left-pad/\" title=\"NPMとleft-pad : 私たちはプログラミングのやり方を忘れてしまったのか？ | プログラミング | POSTD\"><cite><img src=\"http://cdn-ak.favicon.st-hatena.com/?url=http%3A%2F%2Fpostd.cc%2F\" alt=\"\" /> <a href=\"http://postd.cc/npm-and-left-pad/\">NPMとleft-pad : 私たちはプログラミングのやり方を忘れてしまったのか？ | プログラミング | POSTD</a></cite><p><a href=\"http://postd.cc/npm-and-left-pad/\"><img src=\"http://cdn-ak.b.st-hatena.com/entryimage/283496970-1459165601.jpg\" alt=\"NPMとleft-pad : 私たちはプログラミングのやり方を忘れてしまったのか？ | プログラミング | POSTD\" title=\"NPMとleft-pad : 私たちはプログラミングのやり方を忘れてしまったのか？ | プログラミング | POSTD\" class=\"entry-image\" /></a></p><p>さあ開発者のみなさん、真面目な話の時間です。読者の皆様はおそらくすでにお気づきでしょうが、今週、ReactやBabelやその他大量の有名なNPMパッケージ群が壊れました。そしてその原因は少々驚くようなものでした。 ReactやBabel、その他のパッケージが依存する、left-padというシンプルなNPMパッケーさあ開発者のみなさん、真面目な話の時間です。読者の皆様はおそらくすでにお気づきでしょう...</p><p><a href=\"http://b.hatena.ne.jp/entry/http://postd.cc/npm-and-left-pad/\"><img src=\"http://b.hatena.ne.jp/entry/image/http://postd.cc/npm-and-left-pad/\" alt=\"はてなブックマーク - NPMとleft-pad : 私たちはプログラミングのやり方を忘れてしまったのか？ | プログラミング | POSTD\" title=\"はてなブックマーク - NPMとleft-pad : 私たちはプログラミングのやり方を忘れてしまったのか？ | プログラミング | POSTD\" border=\"0\" style=\"border: none\" /></a> <a href=\"http://b.hatena.ne.jp/append?http://postd.cc/npm-and-left-pad/\"><img src=\"http://b.hatena.ne.jp/images/append.gif\" border=\"0\" alt=\"はてなブックマークに追加\" title=\"はてなブックマークに追加\" /></a></p></blockquote>";
        String ex1 = "http://cdn-ak.b.st-hatena.com/entryimage/283496970-1459165601.jpg";

        assertEquals(ex1, getImageUrl(str1));

        String str2 = "<blockquote cite=\"http://www.infoq.com/jp/news/2016/03/go-patterns\" title=\"Goのプログラミングパターン\"><cite><img src=\"http://cdn-ak.favicon.st-hatena.com/?url=http%3A%2F%2Fwww.infoq.com%2F\" alt=\"\" /> <a href=\"http://www.infoq.com/jp/news/2016/03/go-patterns\">Goのプログラミングパターン</a></cite><p><a href=\"http://www.infoq.com/jp/news/2016/03/go-patterns\"><img src=\"http://cdn-ak.b.st-hatena.com/entryimage/283475380-1459149488.jpg\" alt=\"Goのプログラミングパターン\" title=\"Goのプログラミングパターン\" class=\"entry-image\" /></a></p><p>QCon London 2016において、Peter Bourgon氏は「 Successful Go Program Design, 6 Years On 」というプレゼンを行い、Goでプログラミングするときに使うべきパターンと避けるべきパターンについて説明した。 GOPATH: 環境変数PATHに GOPATH/bin を加え、関係バイナリを簡単にアクセスできるようにする。Bourgon氏は一...</p><p><a href=\"http://b.hatena.ne.jp/entry/http://www.infoq.com/jp/news/2016/03/go-patterns\"><img src=\"http://b.hatena.ne.jp/entry/image/http://www.infoq.com/jp/news/2016/03/go-patterns\" alt=\"はてなブックマーク - Goのプログラミングパターン\" title=\"はてなブックマーク - Goのプログラミングパターン\" border=\"0\" style=\"border: none\" /></a> <a href=\"http://b.hatena.ne.jp/append?http://www.infoq.com/jp/news/2016/03/go-patterns\"><img src=\"http://b.hatena.ne.jp/images/append.gif\" border=\"0\" alt=\"はてなブックマークに追加\" title=\"はてなブックマークに追加\" /></a></p></blockquote>";
        String ex2 = "http://cdn-ak.b.st-hatena.com/entryimage/283475380-1459149488.jpg";

        assertEquals(ex2, getImageUrl(str2));


        String str3 = "<blockquote cite=\"http://qiita.com/syossan27/items/da6430133b0a817985b4\" title=\"クズが教える新人プログラマ処世術 - Qiita\"><cite><img src=\"http://cdn-ak.favicon.st-hatena.com/?url=http%3A%2F%2Fqiita.com%2F\" alt=\"\" /> <a href=\"http://qiita.com/syossan27/items/da6430133b0a817985b4\">クズが教える新人プログラマ処世術 - Qiita</a></cite><p><a href=\"http://qiita.com/syossan27/items/da6430133b0a817985b4\"><img src=\"http://cdn-ak.b.st-hatena.com/entryimage/283471564-1459147365.jpg\" alt=\"クズが教える新人プログラマ処世術 - Qiita\" title=\"クズが教える新人プログラマ処世術 - Qiita\" class=\"entry-image\" /></a></p><p>Welcome to Underground という訳で今年から新人プログラマとして働く方、おめでとうございます。 この先、皆さんには 糞みたいな上司と争ったり、糞みたいな炎上プロジェクトに突っ込まれたり、糞みたいなクライアントの対応したり 素敵なイベントが沢山あることでしょう！ そんな皆さんにクズと自認している私が僭越ながら処世術をお教え致します。 学生時代からOSSに貢献してたり、スタートアッ...</p><p><a href=\"http://b.hatena.ne.jp/entry/http://qiita.com/syossan27/items/da6430133b0a817985b4\"><img src=\"http://b.hatena.ne.jp/entry/image/http://qiita.com/syossan27/items/da6430133b0a817985b4\" alt=\"はてなブックマーク - クズが教える新人プログラマ処世術 - Qiita\" title=\"はてなブックマーク - クズが教える新人プログラマ処世術 - Qiita\" border=\"0\" style=\"border: none\" /></a> <a href=\"http://b.hatena.ne.jp/append?http://qiita.com/syossan27/items/da6430133b0a817985b4\"><img src=\"http://b.hatena.ne.jp/images/append.gif\" border=\"0\" alt=\"はてなブックマークに追加\" title=\"はてなブックマークに追加\" /></a></p></blockquote>";
        String ex3 = "http://cdn-ak.b.st-hatena.com/entryimage/283471564-1459147365.jpg";

        assertEquals(ex3, getImageUrl(str3));

    }


    private String getImageUrl(String str) {

        String url = str.substring(str.indexOf("http://cdn-ak.b.st-hatena.com/entryimage/"), str.length() - 1);
        return url.substring(0, url.indexOf("jpg") == 0? url.indexOf("jpeg") + 4 : url.indexOf("jpg") + 3);
    }

}