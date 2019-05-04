/**
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ksch;

import lombok.NoArgsConstructor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@NoArgsConstructor
public abstract class ApplicationFrame extends WebPage {

    public static final String MAIN_CONTENT_ID = "content";

    public ApplicationFrame(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    public void onInitialize() {
        super.onInitialize();

        add(new ExternalLink("goToHomePage", getHomePageUrl()));
        Panel content = getContent();
        content.setMarkupId(MAIN_CONTENT_ID);
        add(content);
    }

    protected abstract Panel getContent();

    protected abstract String getHomePageUrl();
}
