/*
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

package ksch.commons;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.MockedUI;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.SpringServlet;
import com.vaadin.flow.spring.SpringVaadinServletService;
import ksch.KschWorkflowsApplication;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;
import java.util.function.Supplier;

/**
 * Base class for unit tests for web pages and page components.
 * <p>
 * A mocked Vaadin UI and a dummy Spring application context will be used.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KschWorkflowsApplication.class)
public class PageComponentTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Rule
    public ResetDataAfterTest resetDataAfterTest;

    @Before
    public final void initializeMockedVaadinEnvironment() {
        MockVaadin.setup(MockedUI::new, new DummySpringServlet(applicationContext));
    }

    @After
    public void tearDown() {
        MockVaadin.tearDown();
    }

    protected void openPage(Class<? extends Component> page) {
        UI.getCurrent().navigate(page);
    }

    private static class DummySpringServlet extends SpringServlet {

        private final ApplicationContext applicationContext;

        public DummySpringServlet(ApplicationContext applicationContext) {
            super(applicationContext, false);

            this.applicationContext = applicationContext;
        }

        @Override
        @SneakyThrows
        protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration) {
            var service = new DummySpringServletService(this, deploymentConfiguration, applicationContext);
            service.init();
            registerRoutes(getServletContext());
            return service;
        }

        private static void registerRoutes(ServletContext servletContext) {
            var routes = new Routes().autoDiscoverViews();
            routes.register(servletContext);
        }
    }

    private static class DummySpringServletService extends SpringVaadinServletService {

        public DummySpringServletService(
                VaadinServlet servlet, DeploymentConfiguration deploymentConfiguration, ApplicationContext context
        ) {
            super(servlet, deploymentConfiguration, context);
        }

        @Override
        protected boolean isAtmosphereAvailable() {
            return false;
        }

        @Override
        public String getMainDivId(VaadinSession session, VaadinRequest request) {
            return "ROOT-1";
        }
    }

    @RequiredArgsConstructor
    private static class SpringBeanWrapper<T> {
        @NonNull
        private final Class<T> beanType;

        @Getter
        private final Object mock;

        Class<T> getBeanType() {
            return beanType;
        }

        Supplier<Object> getSupplier() {
            return () -> mock;
        }

        @SuppressWarnings("rawtypes")
        static <T> SpringBeanWrapper createSpringBean(Class<T> beanType, Object userDefinedObject) {
            return new SpringBeanWrapper<>(beanType, userDefinedObject);
        }
    }
}
