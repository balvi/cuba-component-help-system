package de.balvi.cuba.helpsystem.web

import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.Security
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import com.haulmont.cuba.security.entity.EntityOp
import de.balvi.cuba.declarativecontrollers.web.helper.ButtonsPanelHelper
import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext
import de.balvi.cuba.helpsystem.service.HelpContextService
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component
class HelptextComponentHelper {

    @Inject
    ComponentsFactory componentsFactory

    void createTabForHelptext(Accordion helpAcc, Helptext helptext) {
        ScrollBoxLayout scrollBox = createHelpAccordionScrollBoxLayout()

        BoxLayout textHbox = createHelptextLayoutComponent(helptext)
        scrollBox.add(textHbox)

        createTabComponentForCategory(helpAcc, helptext.category.name, scrollBox)
    }

    Accordion.Tab createTabComponentForCategory(Accordion helpAcc, String categoryName, ScrollBoxLayout scrollBox) {
        helpAcc.addTab(categoryName, scrollBox)
        Accordion.Tab newTab = helpAcc.getTab(categoryName)
        newTab.caption = categoryName
        newTab
    }

    BoxLayout createHelptextLayoutComponent(Helptext helptext) {
        def label = componentsFactory.createComponent(Label)

        label.value = helptext.text
        label.setWidthFull()
        label.htmlEnabled = true

        def textHbox = componentsFactory.createComponent(HBoxLayout)
        textHbox.setSpacing(true)
        textHbox.setMargin(true)
        textHbox.setHeightFull()
        textHbox.setWidthFull()
        textHbox.add(label)
        textHbox
    }

    protected ScrollBoxLayout createHelpAccordionScrollBoxLayout() {
        ScrollBoxLayout scrollBox = componentsFactory.createComponent(ScrollBoxLayout)
        scrollBox.setMargin(true)
        scrollBox.setSpacing(true)
        scrollBox.setHeightFull()
        scrollBox.setWidthFull()
        scrollBox
    }

}
