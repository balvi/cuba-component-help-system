package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.WindowParam
import com.haulmont.cuba.gui.components.AbstractWindow
import com.haulmont.cuba.gui.components.Accordion
import de.balvi.cuba.helpsystem.entity.HelpContext
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
        helptextComponentHelper.initHelptextAccordion(helpContext, helpAcc)
    }

}