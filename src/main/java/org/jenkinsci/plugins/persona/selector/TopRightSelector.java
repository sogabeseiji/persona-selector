/*
 * The MIT License
 *
 * Copyright (c) 2012, Seiji Sogabe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.persona.selector;

import hudson.Extension;
import hudson.plugins.persona.selector.LocationSelector;
import hudson.plugins.persona.selector.LocationSelectorDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * 右上に表示します。
 * 
 * クラス名は適当に変更してください。
 * クラス名を変更した場合は、src/main/resources/org/jenkinsci/plugins/persona/selector/TopRightSelector
 * も変更してください。
 */
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
