package de.balvi.cuba.helpsystem.web.helpcontext

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.PersistenceHelper
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.actions.CreateAction
import com.haulmont.cuba.gui.components.actions.EditAction
import com.haulmont.cuba.gui.config.WindowConfig
import com.haulmont.cuba.gui.config.WindowInfo
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext

import javax.inject.Inject
import javax.inject.Named

class HelpContextEdit extends AbstractEditor<HelpContext> {


    @Inject
    protected FieldGroup fieldGroup

    @Named('fieldGroup.componentId')
    TextInputField componentIdField

    @Inject
    protected ComponentsFactory componentsFactory

    @Inject
    Table<Helptext> helptextsTable

    @Inject
    Datasource<HelpContext> helpContextDs
    private final String SCREEN_ID_FIELD_NAME = 'screenId'


    @Override
    protected void postInit() {
        initScreenLookupField()
        initHelptextActions()
    }

    protected void initScreenLookupField() {
        def screenOptions = [:]
        WindowConfig windowConfig = AppBeans.get(WindowConfig)
        windowConfig.windows.each { WindowInfo windowInfo ->
            screenOptions."${windowInfo.id}" = windowInfo.id

        }
        FieldGroup.FieldConfig screenIdFieldConfig = fieldGroup.getField(SCREEN_ID_FIELD_NAME)
        LookupField lookupField = createScreenLookupField(screenOptions)
        screenIdFieldConfig.component = lookupField
    }

    protected LookupField createScreenLookupField(Map screenOptions) {
        LookupField lookupField = componentsFactory.createComponent(LookupField.NAME) as LookupField
        lookupField.enabled = PersistenceHelper.isNew(item) && !item.screenId
        componentIdField.enabled = PersistenceHelper.isNew(item) && !item.screenId
        lookupField.optionsMap = screenOptions
        lookupField.setDatasource(helpContextDs, SCREEN_ID_FIELD_NAME)
        lookupField.nullOptionVisible = false
        lookupField
    }

    protected void initHelptextActions() {
        CreateAction createAction = helptextsTable.getAction('create') as CreateAction
        EditAction editAction = helptextsTable.getAction('edit') as EditAction
        def windowParams = [contextIndependent: false]
        createAction.windowParams = windowParams
        editAction.windowParams = windowParams
    }
}