package de.balvi.cuba.helpsystem.web.action

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
import de.balvi.cuba.helpsystem.web.HelptextComponentHelper
import org.springframework.context.annotation.Scope

import javax.inject.Inject

@SuppressWarnings('ClassSize')
@org.springframework.stereotype.Component
@Scope('prototype')
class HelpSidePanelRenderer {

    @Inject
    ButtonsPanelHelper buttonsPanelHelper

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    HelpContextService helpContextService

    @Inject
    HelptextComponentHelper helptextComponentHelper

    @Inject
    Metadata metadata

    @Inject
    Security security

    Accordion helpAcc

    SplitPanel splitPanel

    boolean isHelpActivated = false
    private Frame wrappedFrame
    private final String DISABLED_BUTTON_BOX_ID = 'disabledButtonBox'
    private final String ENABLED_BUTTON_BOX_ID = 'enabledButtonBox'
    private final String QUESTION_MARK_ICON = 'font-icon:QUESTION_CIRCLE'
    private final String HELP_BUTTON_ACTION_ID = 'helpBtnAction'


    void initComponent(Frame frame) {
        wrappedFrame = frame

        Collection<Component> components = new ArrayList<>(frame.ownComponents)

        initLeftRightSplitPanel()
        closeHelpPanel()

        //  im Anschluss alles wieder restaurieren
        for (component in components) {
            if (component.id != null) {
                frame.registerComponent(component)
            }
        }
    }

    protected void initLeftRightSplitPanel() {
        BoxLayout leftBox = createLeftBoxFromExistingContent()
        BoxLayout rightBox = createRightBox()

        splitPanel = createSplitPanel(leftBox, rightBox)
        wrappedFrame.add(splitPanel)
    }

    protected BoxLayout createLeftBoxFromExistingContent() {
        Collection<Component> components = new ArrayList<>(wrappedFrame.ownComponents)

        def expandComponent = wrappedFrame.ownComponents.find { wrappedFrame.isExpanded(it) }

        wrappedFrame.removeAll()

        BoxLayout leftBox = componentsFactory.createComponent(VBoxLayout)
        leftBox.spacing = true
        leftBox.setMargin(false, true, false, false)
        leftBox.setHeightFull()
        components.each {
            leftBox.add(it)
        }

        if (expandComponent) {
            leftBox.expand(expandComponent)
        }
        leftBox
    }

    protected BoxLayout createRightBox() {
        BoxLayout rightBox = createRightBoxLayout()
        BoxLayout buttonBox = createHelpButtonBox()
        BoxLayout buttonEnabledBox = createHelpButtonEnabledBox()

        helpAcc = componentsFactory.createComponent(Accordion)
        helpAcc.setHeightFull()
        rightBox.add(buttonBox)
        rightBox.add(buttonEnabledBox)
        rightBox.add(helpAcc)
        rightBox.spacing = true
        rightBox.expand(helpAcc)
        helpAcc.visible = false

        rightBox
    }

    protected BoxLayout createRightBoxLayout() {
        BoxLayout rightBox = componentsFactory.createComponent(VBoxLayout)
        rightBox.setHeightFull()
        rightBox.margin = true
        rightBox
    }

    protected BoxLayout createHelpButtonBox() {
        BoxLayout buttonBox = componentsFactory.createComponent(HBoxLayout)
        buttonBox.id = DISABLED_BUTTON_BOX_ID
        Button disabledHelpBtn = createBtn(QUESTION_MARK_ICON)
        disabledHelpBtn.action = new BaseAction(HELP_BUTTON_ACTION_ID) {
            @Override
            void actionPerform(Component component) {
                handleOpenHelpAction()
            }
        }

        buttonBox.spacing = true
        buttonBox.alignment = Component.Alignment.TOP_RIGHT

        buttonBox.add(disabledHelpBtn)
        buttonBox
    }

    protected BoxLayout createHelpButtonEnabledBox() {
        HBoxLayout buttonBox = createHelpButtonEnabledBoxComponent()

        appendEnabledHelpButton(buttonBox)
        appendHelpEditBtnIfAllowed(buttonBox)
        appendOpenHelpInNewWindowButton(buttonBox)

        buttonBox
    }

    protected HBoxLayout createHelpButtonEnabledBoxComponent() {
        BoxLayout buttonBox = componentsFactory.createComponent(HBoxLayout)
        buttonBox.id = ENABLED_BUTTON_BOX_ID
        buttonBox.spacing = true
        buttonBox.alignment = Component.Alignment.TOP_RIGHT
        buttonBox.visible = false
        buttonBox
    }

    protected void appendEnabledHelpButton(HBoxLayout buttonBox) {
        def enabledHelpBtn = createBtn(QUESTION_MARK_ICON)
        enabledHelpBtn.styleName = 'friendly'
        enabledHelpBtn.action = new BaseAction(HELP_BUTTON_ACTION_ID) {
            @Override
            void actionPerform(Component component) {
                handleCloseHelpAction()
            }
        }
        buttonBox.add(enabledHelpBtn)
    }

    protected void appendOpenHelpInNewWindowButton(HBoxLayout buttonBox) {
        def openHelpNewWindowBtn = createBtn('font-icon:EXTERNAL_LINK')
        openHelpNewWindowBtn.action = new BaseAction(HELP_BUTTON_ACTION_ID) {
            @Override
            void actionPerform(Component component) {
                openHelpInNewTab()
            }
        }
        buttonBox.add(openHelpNewWindowBtn)
    }

    protected void appendHelpEditBtnIfAllowed(HBoxLayout buttonBox) {
        if (security.isEntityOpPermitted(HelpContext, EntityOp.UPDATE) && security.isEntityOpPermitted(Helptext, EntityOp.UPDATE)) {
            def editHelpBtn = createBtn('icons/edit.png')

            editHelpBtn.action = new BaseAction('editHelpBtnAction') {
                @Override
                void actionPerform(Component component) {
                    editHelpContext()
                }
            }
            buttonBox.add(editHelpBtn)
        }
    }

    protected Button createBtn(String icon, String caption = '') {
        Button btn = componentsFactory.createComponent(Button)
        btn.icon = icon
        btn.caption = caption

        btn
    }

    protected SplitPanel createSplitPanel(BoxLayout leftBox, BoxLayout rightBox) {
        splitPanel = componentsFactory.createComponent(SplitPanel)
        splitPanel.orientation = SplitPanel.ORIENTATION_HORIZONTAL

        splitPanel.setHeightFull()

        splitPanel.add(leftBox)
        splitPanel.add(rightBox)
        splitPanel
    }


    void handleOpenHelpAction() {

        if (!isHelpActivated) {
            openHelpPanel()
            helpAcc.visible = true
            wrappedFrame.getComponent(DISABLED_BUTTON_BOX_ID).visible = false
            wrappedFrame.getComponent(ENABLED_BUTTON_BOX_ID).visible = true

            isHelpActivated = true


            helpAcc.removeAllTabs()
            HelpContext helpContext = currentHelpContext

            helptextComponentHelper.initHelptextAccordion(helpContext, helpAcc)
        }
    }


    void handleCloseHelpAction() {

        if (isHelpActivated) {
            closeHelpPanel()
            wrappedFrame.getComponent(DISABLED_BUTTON_BOX_ID).visible = true
            wrappedFrame.getComponent(ENABLED_BUTTON_BOX_ID).visible = false
            helpAcc.visible = false

            isHelpActivated = false
        }

    }

    protected openHelpPanel() {
        splitPanel.setSplitPosition(30, Component.UNITS_PERCENTAGE, true)
    }

    protected closeHelpPanel() {
        splitPanel.setSplitPosition(65, Component.UNITS_PIXELS, true)
    }


    protected HelpContext getCurrentHelpContext() {
        helpContextService.getHelpContext(wrappedFrame.id, null)
    }

    void editHelpContext() {
        def helpContext = currentHelpContext

        if (!helpContext) {
            helpContext = metadata.create(HelpContext)
            helpContext.screenId = wrappedFrame.id
        }
        wrappedFrame.openEditor(helpContext, WindowManager.OpenType.DIALOG)
    }

    void openHelpInNewTab() {
        wrappedFrame.openWindow('dbchs$Helptext.show', WindowManager.OpenType.NEW_TAB, [
                helpContext: currentHelpContext,
                helpContextCaption: wrappedFrame.caption
        ])
    }

}
