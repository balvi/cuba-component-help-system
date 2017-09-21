package de.balvi.cuba.helpsystem.web.helptextcategory

import de.balvi.cuba.declarativecontrollers.web.browse.AnnotatableAbstractLookup
import de.balvi.cuba.helpsystem.web.action.HasHelp

@HasHelp
class HelptextCategoryBrowse extends AnnotatableAbstractLookup {
//
//
//    @Override
//    void ready() {
//        super.ready()
//        initComponent(frame)
//    }
//
//    @Inject
//    ButtonsPanelHelper buttonsPanelHelper
//
//    @Inject
//    ComponentsFactory componentsFactory
//
//    @Inject
//    HelpContextService helpContextService
//
//    @Inject
//    Metadata metadata
//
//    @Inject
//    Security security
//
//    Accordion helpAcc
//
//    SplitPanel splitPanel
//
//    boolean isHelpActivated = false
//    private Frame wrappedFrame
//
//
//    void initComponent(Frame frame) {
//        wrappedFrame = frame
//
//        initLeftRightSplitPanel()
//        closeHelpPanel()
//
//    }
//
//    protected void initLeftRightSplitPanel() {
//        BoxLayout leftBox = createLeftBoxFromExistingContent()
//        BoxLayout rightBox = createRightBox()
//
//        splitPanel = createSplitPanel(leftBox, rightBox)
//        wrappedFrame.add(splitPanel)
//    }
//
//    protected BoxLayout createLeftBoxFromExistingContent() {
//        Collection<com.haulmont.cuba.gui.components.Component> components = new ArrayList<>(wrappedFrame.ownComponents)
//
//        def expandComponent = wrappedFrame.ownComponents.find { wrappedFrame.isExpanded(it) }
//
//        wrappedFrame.removeAll()
//
//        BoxLayout leftBox = componentsFactory.createComponent(VBoxLayout.class)
//        leftBox.spacing = true
//        leftBox.setMargin(false, true, false, false)
//        leftBox.setHeightFull()
//        components.each {
//            leftBox.add(it)
//        }
//
//        if (expandComponent) {
//            leftBox.expand(expandComponent)
//        }
//        leftBox
//    }
//
//    protected BoxLayout createRightBox() {
//        BoxLayout rightBox = createRightBoxLayout()
//        BoxLayout buttonBox = createHelpButtonBox()
//        BoxLayout buttonEnabledBox = createHelpButtonEnabledBox()
//
//        helpAcc = componentsFactory.createComponent(Accordion.class)
//        helpAcc.setHeightFull()
//        rightBox.add(buttonBox)
//        rightBox.add(buttonEnabledBox)
//        rightBox.add(helpAcc)
//        rightBox.spacing = true
//        rightBox.expand(helpAcc)
//        helpAcc.visible = false
//
//
//        rightBox
//    }
//
//    protected BoxLayout createRightBoxLayout() {
//        BoxLayout rightBox = componentsFactory.createComponent(VBoxLayout.class)
//        rightBox.setHeightFull()
//        rightBox.margin = true
//        rightBox
//    }
//
//    protected BoxLayout createHelpButtonBox() {
//        BoxLayout buttonBox = componentsFactory.createComponent(HBoxLayout.class)
//        buttonBox.id = 'disabledButtonBox'
//        Button disabledHelpBtn = createBtn('font-icon:QUESTION_CIRCLE')
//        disabledHelpBtn.action = new BaseAction('helpBtnAction') {
//            @Override
//            void actionPerform(com.haulmont.cuba.gui.components.Component component) {
//                handleOpenHelpAction()
//            }
//        }
//
//        buttonBox.spacing = true
//        buttonBox.alignment = com.haulmont.cuba.gui.components.Component.Alignment.TOP_RIGHT
//
//        buttonBox.add(disabledHelpBtn)
//        buttonBox
//    }
//
//    protected BoxLayout createHelpButtonEnabledBox() {
//        BoxLayout buttonBox = componentsFactory.createComponent(HBoxLayout.class)
//        buttonBox.id = 'enabledButtonBox'
//        def enabledHelpBtn = createBtn('font-icon:QUESTION_CIRCLE')
//        enabledHelpBtn.styleName = 'friendly'
//        enabledHelpBtn.action = new BaseAction('helpBtnAction') {
//            @Override
//            void actionPerform(com.haulmont.cuba.gui.components.Component component) {
//                handleCloseHelpAction()
//            }
//        }
//        createHelpEditBtnIfAllowed(buttonBox)
//
//        def openHelpNewWindowBtn = createBtn('font-icon:EXTERNAL_LINK')
//        openHelpNewWindowBtn.action = new BaseAction('helpBtnAction') {
//            @Override
//            void actionPerform(com.haulmont.cuba.gui.components.Component component) {
//                openHelpInNewTab()
//            }
//        }
//        buttonBox.add(openHelpNewWindowBtn)
//
//
//        buttonBox.spacing = true
//        buttonBox.alignment = com.haulmont.cuba.gui.components.Component.Alignment.TOP_RIGHT
//        buttonBox.add(enabledHelpBtn)
//        buttonBox.visible = false
//        buttonBox
//    }
//
//    protected void createHelpEditBtnIfAllowed(HBoxLayout buttonBox) {
//        if (security.isEntityOpPermitted(HelpContext, EntityOp.UPDATE) && security.isEntityOpPermitted(Helptext, EntityOp.UPDATE)) {
//            def editHelpBtn = createBtn('icons/edit.png')
//
//            editHelpBtn.action = new BaseAction('editHelpBtnAction') {
//                @Override
//                void actionPerform(Component component) {
//                    editHelpContext()
//                }
//            }
//            buttonBox.add(editHelpBtn)
//        }
//    }
//
//    protected Button createBtn(String icon, String caption = '') {
//        Button btn = componentsFactory.createComponent(Button.class)
//        btn.icon = icon
//        btn.caption = caption
//
//        btn
//    }
//
//    protected SplitPanel createSplitPanel(BoxLayout leftBox, BoxLayout rightBox) {
//        splitPanel = componentsFactory.createComponent(SplitPanel.class)
//        splitPanel.orientation = SplitPanel.ORIENTATION_HORIZONTAL
//
//        splitPanel.setHeightFull()
//
//        splitPanel.add(leftBox)
//        splitPanel.add(rightBox)
//        splitPanel
//    }
//
//
//    void handleOpenHelpAction() {
//
//        if (!isHelpActivated) {
//            openHelpPanel()
//            helpAcc.visible = true
//            frame.getComponent('disabledButtonBox').visible = false
//            frame.getComponent('enabledButtonBox').visible = true
//
//            isHelpActivated = true
//
//
//            helpAcc.removeAllTabs()
//            HelpContext helpContext = currentHelpContext
//
//            contextIndependentHelptexts.each { Helptext helptext ->
//                createTabForHelptext(helptext)
//            }
//
//            if (helpContext && helpContext.helptexts) {
//                helpContext.helptexts.sort { it.category.code }.each { Helptext helptext ->
//                    createTabForHelptext(helptext)
//                }
//            }
//        }
//    }
//
//
//    void handleCloseHelpAction() {
//
//        if (isHelpActivated) {
//            closeHelpPanel()
//            frame.getComponent('disabledButtonBox').visible = true
//            frame.getComponent('enabledButtonBox').visible = false
//            helpAcc.visible = false
//
//            isHelpActivated = false
//        }
//
//    }
//
//    protected openHelpPanel() {
//        splitPanel.setSplitPosition(30, com.haulmont.cuba.gui.components.Component.UNITS_PERCENTAGE, true)
//    }
//
//    protected closeHelpPanel() {
//        splitPanel.setSplitPosition(65, com.haulmont.cuba.gui.components.Component.UNITS_PIXELS, true)
//    }
//
//    protected void createTabForHelptext(Helptext helptext) {
//        ScrollBoxLayout scrollBox = createHelpAccordionScrollBoxLayout()
//
//        BoxLayout textHbox = createHelptextLayoutComponent(helptext)
//        scrollBox.add(textHbox)
//
//        createTabComponentForCategory(helptext.category.name, scrollBox)
//    }
//
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
//
//    protected HelpContext getCurrentHelpContext() {
//        helpContextService.getHelpContext(wrappedFrame.id, null)
//    }
//
//    protected Collection<Helptext> getContextIndependentHelptexts() {
//        helpContextService.contextIndependentHelptexts
//    }
//
//
//    void editHelpContext() {
//        def helpContext = currentHelpContext
//
//        if (!helpContext) {
//            helpContext = metadata.create(HelpContext)
//            helpContext.screenId = wrappedFrame.id
//        }
//        wrappedFrame.openEditor(helpContext, WindowManager.OpenType.DIALOG)
//    }
//
//    void openHelpInNewTab() {
//        openWindow('dbchs$Helptext.show', WindowManager.OpenType.NEW_TAB, [
//                helpContext: currentHelpContext,
//                helpContextCaption: caption
//        ])
//    }
}