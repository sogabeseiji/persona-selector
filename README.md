# README


## これは何?
--------

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

+ クラス名 TopRightSelector を適切な名称に変更します。
+ getDisplayName()の戻り値 "Top-Right"を適切な名称に変更します。この値が、選択肢として表示されます。

 