package de.balvi.cuba.helpsystem.web.action

import com.haulmont.cuba.core.global.Security
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.Frame
import com.haulmont.cuba.gui.components.SplitPanel
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory

class HelpSidePanelRendererSpec extends SpecificationWithApplicationContext {

    HelpSidePanelRenderer sut

    Frame frame = Mock(Frame)
    Collection<Component> components = []
    ComponentsFactory factory = Mock(ComponentsFactory)
    SplitPanel splitPanel = Mock(SplitPanel)

    def setup() {
        frame.ownComponents >> components
        frame.removeAll() >> { components.clear() }
        frame.registerComponent(_) >> { Component component -> components >> component }

        factory.createComponent(_ as Class) >> { Class type -> Mock(type) }

        sut = new HelpSidePanelRenderer()
        sut.splitPanel = splitPanel
        sut.componentsFactory = factory
        sut.security = Mock(Security)
    }

    def 'initComponent registriert alle Komponenten neu, nachdem diese entfernt wurden'() {

        setup:
        Component c1 = Mock(Component)
        c1.getId() >> 'c1'
        Component c2 = Mock(Component)
        c2.getId() >> 'c2'
        components << c1
        components << c2

        when:
        sut.initComponent(frame)

        then:
        1 * frame.removeAll()

        and:
        1 * frame.registerComponent(c1)
        1 * frame.registerComponent(c2)
    }
}
