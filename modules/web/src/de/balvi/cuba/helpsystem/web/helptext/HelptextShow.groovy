package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.WindowParam
import com.haulmont.cuba.gui.components.AbstractWindow
import com.haulmont.cuba.gui.components.Accordion
import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext
import de.balvi.cuba.helpsystem.service.HelpContextService
import de.balvi.cuba.helpsystem.web.HelptextComponentHelper

import javax.inject.Inject

class HelptextShow extends AbstractWindow {


    @Inject
    HelpContextService helpContextService

    @WindowParam
    HelpContext helpContext

    @WindowParam
    String helpContextCaption

//    @Inject
//    ComponentsFactory componentsFactory

    @Inject
    HelptextComponentHelper helptextComponentHelper

    @Inject
    Accordion helpAcc

    @Override
    void ready() {
        super.ready()

        if (helpContextCaption) {
            caption = formatMessage('showCaption', helpContextCaption)
        }
        else {
            caption = formatMessage('defaultShowCaption')
        }
        HelpContext helpContext = currentHelpContext

        contextIndependentHelptexts.each { Helptext helptext ->
            helptextComponentHelper.createTabForHelptext(helpAcc, helptext)
        }

        if (helpContext && helpContext.helptexts) {
            helpContext.helptexts.sort { it.category.code }.each { Helptext helptext ->
                helptextComponentHelper.createTabForHelptext(helpAcc, helptext)
            }
        }

    }


    protected HelpContext getCurrentHelpContext() {
        helpContext
    }

    protected Collection<Helptext> getContextIndependentHelptexts() {
        helpContextService.contextIndependentHelptexts
    }

//    protected void createTabForHelptext(Helptext helptext) {
//        ScrollBoxLayout scrollBox = createHelpAccordionScrollBoxLayout()
//
//        BoxLayout textHbox = createHelptextLayoutComponent(helptext)
//        scrollBox.add(textHbox)
//
//        createTabComponentForCategory(helptext.category.name, scrollBox)
//    }

//    protected Accordion.Tab createTabComponentForCategory(String categoryName, ScrollBoxLayout scrollBox) {
//        helpAcc.addTab(categoryName, scrollBox)
//        Accordion.Tab newTab = helpAcc.getTab(categoryName)
//        newTab.caption = categoryName
//        newTab
//    }
//
//    protected BoxLayout createHelptextLayoutComponent(Helptext helptext) {
//        def label = componentsFactory.createComponent(Label)
//
//        label.value = helptext.text
//        label.setWidthFull()
//        label.htmlEnabled = true
//
//        def textHbox = componentsFactory.createComponent(HBoxLayout)
//        textHbox.setSpacing(true)
//        textHbox.setMargin(true)
//        textHbox.setHeightFull()
//        textHbox.setWidthFull()
//        textHbox.add(label)
//        textHbox
//    }
//
//    protected ScrollBoxLayout createHelpAccordionScrollBoxLayout() {
//        ScrollBoxLayout scrollBox = componentsFactory.createComponent(ScrollBoxLayout)
//        scrollBox.setMargin(true)
//        scrollBox.setSpacing(true)
//        scrollBox.setHeightFull()
//        scrollBox.setWidthFull()
//        scrollBox
//    }
}