package de.balvi.cuba.helpsystem.web

import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext
import de.balvi.cuba.helpsystem.service.HelpContextService
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component
class HelptextComponentHelper {

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    HelpContextService helpContextService

    void initHelptextAccordion(HelpContext helpContext, Accordion helptextAccordion) {

        contextIndependentHelptexts.each { Helptext helptext ->
            createTabForHelptext(helptextAccordion, helptext)
        }

        if (helpContext && helpContext.helptexts) {
            helpContext.helptexts.sort { it.category.code }.each { Helptext helptext ->
                createTabForHelptext(helptextAccordion, helptext)
            }
        }
    }


    protected Collection<Helptext> getContextIndependentHelptexts() {
        helpContextService.contextIndependentHelptexts
    }


    @SuppressWarnings('BuilderMethodWithSideEffects')
    void createTabForHelptext(Accordion helptextAccordion, Helptext helptext) {
        ScrollBoxLayout scrollBox = createHelpAccordionScrollBoxLayout()

        BoxLayout textHbox = createHelptextLayoutComponent(helptext)
        scrollBox.add(textHbox)

        createTabComponentForCategory(helptextAccordion, helptext.category.name, scrollBox)
    }

    Accordion.Tab createTabComponentForCategory(Accordion helptextAccordion, String categoryName, ScrollBoxLayout scrollBox) {
        helptextAccordion.addTab(categoryName, scrollBox)
        Accordion.Tab newTab = helptextAccordion.getTab(categoryName)
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
