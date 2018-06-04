package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.components.Component
import de.balvi.cuba.helpsystem.entity.Helptext
import de.balvi.cuba.helpsystem.entity.HelptextCategory
import spock.lang.Specification

class AddHelptextLinkHandlerSpec extends Specification {
    AddHelptextLinkHandler sut

    Component.HasValue target = Mock(Component.HasValue)

    def setup() {
        sut = new AddHelptextLinkHandler(target: target)
    }

    def "handleLookup adds a link to the target value"() {

        given:
        def helptextId = UUID.randomUUID()
        def helptextCategoryName = "my-category"
        def helptext = new Helptext(id: helptextId, category: new HelptextCategory(name: helptextCategoryName))

        when:
        sut.handleLookup([helptext])
        then:
        1 * target.setValue({ it.contains(helptextId.toString()) && it.contains(helptextCategoryName)})
    }
}
