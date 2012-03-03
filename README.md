# README


## これは何?

[Persona Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Persona+Plugin) の 2.0から、
ペルソナの表示位置を選択できる機能が追加になりました。また、拡張ポイントを使用することで、Persona Pluginを修正することなく追加することができます。
このPersona Selectorはサンプルです。例として画面右上に表示するようになっていますが、簡単な変更で表示位置をカスタマイズする
ことができます。

## 拡張するには

### LocationSelectorの拡張

Jobの設定画面の"Location"に選択肢を追加するには、拡張ポイント"LocationSelector"を拡張する必要があります。
src/main/java/org/jenkinsci/plugins/persona/selector/TopRightSelector.java を参考にしてください。

    public class TopRightSelector extends LocationSelector {
    
        @DataBoundConstructor
            public TopRightSelector() {
        }

        @Extension
        public static class DescriptorImpl extends LocationSelectorDescriptor {

            /**
             * 選択肢として表示される文字列を返します。
             */
            @Override
            public String getDisplayName() {
                // ★ 修正してください。
                return "Top-Right";
            }
        }
    }

ここでは、2箇所修正する必要があります。

* クラス名 TopRightSelector を適切な名称に変更します。あわせて、ファイル名も変更します。
* getDisplayName()の戻り値 "Top-Right"を適切な名称に変更します。この値が、選択肢として表示されます。

### Jellyファイルの修正 

次に、画面表示部分の修正を行います。
Jellyファイルは、src/main/resourcres/org/jenkinsci/plugins/persona/selector/TopRightSelector に、

* config.jelly
* floatingBox.jelly
* summary.jelly

があります。

#### パスの変更

TopRightSelectorの名称を変更したら、Jellyファイルのパスを変更する必要があります。
src/main/resourcres/org/jenkinsci/plugins/persona/selector/TopRightSelector のTopRightSelectorを変更した名称に変更します。

#### config.jelly

ジョブの設定画面で、当該TopRightSelectorを選択した場合に表示する入力項目の設定ですが。通常変更する必要はありません。

#### floatingBox.jelly

Job画面への表示を行います。デフォルトでは、画像を画面右上に背景画像として表示し、セリフを表示します。
画面の右側（推移グラフが表示される位置)に表示されるので、位置を変えたい場合は、JavaScript等で工夫する必要があります。

    <j:jelly xmlns:j="jelly:core" xmlns:st="jelly:stapler" xmlns:d="jelly:define" xmlns:l="/lib/layout" 
        xmlns:t="/lib/hudson" xmlns:f="/lib/form" xmlns:i="jelly:fmt" xmlns:local="local">
    
        <j:set var="img" value="${action.image}"/>
        
        <div style="max-width:400px;">
            <table border="0"> 
                <tr>
                    <td><img src="${rootURL}/${img.smallIconUrl}" width="48" height="48" /></td>
                    <td style="vertical-align: middle;background-color: white;">${action.quote}</td>
                </tr>
            </table>
        </div>  
        <script>
            Element.setStyle($('main-table'), {
                'background-image': 'none'
            });
            Element.setStyle($('main-panel'), {
                'background-image': 'url(${rootURL}/${img.backgroundImageUrl})',
                'background-repeat': 'no-repeat',
                'background-position': 'top right',
                'padding-top': '20px'
            });
        </script>
    </j:jelly>


ここで以下の変数を使用することができます。

* *img.smallIconUrl*  アイコンのURL
* *img.backgroundImageUrl*  画像のURL
* *action.quote*  セリフ
* *action.persona.displayName*  ペルソナの表示名称
* *selector*  セレクター


#### summary.jelly

ビルド画面への表示を行います。基本的に画面の中央に表示され、tableタグの中に追加されますので、アイコンやセリフは下記のとおり、
&lt;tr>&lt;td>を使用すると、他の画面項目と同じ位置に表示されます。

使用できる変数は、config.jellyと同様です。

    <j:jelly xmlns:j="jelly:core" xmlns:st="jelly:stapler" xmlns:d="jelly:define" xmlns:l="/lib/layout" 
        xmlns:t="/lib/hudson" xmlns:f="/lib/form" xmlns:i="jelly:fmt" xmlns:local="local">

        <j:set var="img" value="${action.image}"/>

        <tr>
            <td><img src="${rootURL}/${img.smallIconUrl}" width="48" height="48" /></td>
            <td style="vertical-align: middle;"><div style="max-width: 400px;">${action.quote}</div></td>
        </tr>

        <script>
        Element.setStyle($('main-table'), {
              'background-image': 'none'
        });
        Element.setStyle($('main-panel'), {
              'background-image': 'url(${rootURL}/${img.backgroundImageUrl})',
              'background-repeat': 'no-repeat',
              'background-position': 'top right',
            'padding-top': '20px'
        });
        </script>

    </j:jelly>

### ビルド

以上で、修正は完了したのでビルドしてインストールします。

    \mvn clean package

上記を実行すると、target/persona-selector.hpiができますので、

Jenkinsの管理 -> プラグインの管理 -> 高度な設定 -> プラグインのアップロード 

を使用してインストールして完了です。
