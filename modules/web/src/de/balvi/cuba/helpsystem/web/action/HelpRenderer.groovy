package de.balvi.cuba.helpsystem.web.action

import com.haulmont.cuba.core.global.PersistenceHelper
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.balvi.cuba.declarativecontrollers.web.helper.ButtonsPanelHelper
import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext
import de.balvi.cuba.helpsystem.service.HelpContextService
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component
@Scope("prototype")
class HelpRenderer {

    @Inject
    ButtonsPanelHelper buttonsPanelHelper

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    HelpContextService helpContextService

    Accordion helpAcc
    private SplitPanel splitPanel
    private Button helpBtn

    boolean istHilfeAktiviert = false

    void initComponent(Frame frame) {
        Frame wrappedFrame = frame

        Collection<com.haulmont.cuba.gui.components.Component> components = new ArrayList<>(wrappedFrame.getOwnComponents())

        wrappedFrame.removeAll()

        BoxLayout leftBox = componentsFactory.createComponent(VBoxLayout.class)
        leftBox.spacing = true
        leftBox.setMargin(false, true, false, false)
        leftBox.setHeightFull()
        components.each {
            leftBox.add(it)
        }

        BoxLayout rightBox = componentsFactory.createComponent(VBoxLayout.class)
        rightBox.setHeightFull()
        rightBox.margin = true



        BoxLayout buttonBox = componentsFactory.createComponent(HBoxLayout.class)
        helpBtn = componentsFactory.createComponent(Button.class)
        helpBtn.icon = 'font-icon:QUESTION_CIRCLE'
        helpAcc = componentsFactory.createComponent(Accordion.class)
        helpAcc.setHeightFull()
        buttonBox.alignment = com.haulmont.cuba.gui.components.Component.Alignment.TOP_RIGHT
        buttonBox.add(helpBtn)
        rightBox.add(buttonBox)
        rightBox.add(helpAcc)
        rightBox.spacing = true
        rightBox.expand(helpAcc)
        helpAcc.visible = false

        helpBtn.caption = ''
        helpBtn.action = new BaseAction('helpBtnAction') {
            @Override
            void actionPerform(com.haulmont.cuba.gui.components.Component component) {
                handleHilfeOeffnenAction(wrappedFrame)
            }
        }

        splitPanel = componentsFactory.createComponent(SplitPanel.class)
        splitPanel.orientation = SplitPanel.ORIENTATION_HORIZONTAL

        splitPanel.setHeightFull()

//        splitPanel.locked = true
        splitPanel.add(leftBox)
        splitPanel.add(rightBox)
        closeHelpPanel()

        wrappedFrame.add(splitPanel)
    }


    void handleHilfeOeffnenAction(Frame frame) {

        if (!istHilfeAktiviert) {
            openHelpPanel()
            helpAcc.visible = true

            helpBtn.styleName = 'friendly'
            istHilfeAktiviert = true
        } else {
            closeHelpPanel()
            helpAcc.visible = false
            helpBtn.styleName = ''

            istHilfeAktiviert = false
        }

        helpAcc.removeAllTabs()
        HelpContext helpContext = getCurrentHelpContext(frame)

        contextIndependentHelptexts.each { Helptext helptext ->
            createTabForHelptext(helptext)
        }

        if (helpContext && helpContext.helptexts) {
            helpContext.helptexts.sort { it.category.code }.each { Helptext helptext ->
                createTabForHelptext(helptext)
            }
        } else {
//            hilfeBearbeitenBtn.setVisible(false)
//            hilfeAnlegenBtn.setVisible(true)
        }
    }

    protected openHelpPanel() {
        splitPanel.setSplitPosition(30, com.haulmont.cuba.gui.components.Component.UNITS_PERCENTAGE, true)
    }

    protected closeHelpPanel() {
        splitPanel.setSplitPosition(65, com.haulmont.cuba.gui.components.Component.UNITS_PIXELS, true)
    }

    protected void createTabForHelptext(Helptext hilfetext) {
        ScrollBoxLayout scrollBox = componentsFactory.createComponent(ScrollBoxLayout)
        scrollBox.setMargin(true)
        scrollBox.setSpacing(true)
        scrollBox.setHeightFull()
        scrollBox.setWidthFull()

        def label = componentsFactory.createComponent(Label)

        label.value = hilfetext.text
        label.width = "100%"
        label.htmlEnabled = true

        def textHbox = componentsFactory.createComponent(HBoxLayout)
        textHbox.setSpacing(true)
        textHbox.setMargin(true)
        textHbox.setHeightFull()
        textHbox.setWidthFull()
        textHbox.add(label)
        scrollBox.add(textHbox)
        helpAcc.addTab('' + hilfetext.category.name, scrollBox)
        Accordion.Tab tab = helpAcc.getTab('' + hilfetext.category.name)

        if (PersistenceHelper.isLoaded(hilfetext, "helpContext") && hilfetext.helpContext) {
            tab.caption = '' + hilfetext.category.name
        } else {
            tab.caption = '' + hilfetext.category.name + ' (allgemein)'
        }
    }

    protected HelpContext getCurrentHelpContext(Frame frame) {
        helpContextService.getHelpContext(frame.id, null)
    }

    protected Collection<Helptext> getContextIndependentHelptexts() {
        helpContextService.contextIndependentHelptexts
    }


//    public void hilfeBearbeiten() {
//        openEditor(getCurrentHelpContext(), WindowManager.OpenType.NEW_TAB)
//    }
//
//    public void hilfeAnlegen() {
//        Hilfeseite hilfeseite = metadata.create(Hilfeseite)
//        hilfeseite.screenId = ermittleAktuelleScreenId()
//        openEditor(hilfeseite, WindowManager.OpenType.NEW_TAB)
//    }

//    public void hilfeOeffnen() {
//        def hilfeseite = getCurrentHelpContext()
//        if (hilfeseite) {
//            openEditor('pl$Hilfeseite.show', hilfeseite, WindowManager.OpenType.DIALOG)
//        } else {
//            showNotification("Keine Hilfetexte für diese Seite vorhanden", Frame.NotificationType.TRAY)
//        }
//    }

}
