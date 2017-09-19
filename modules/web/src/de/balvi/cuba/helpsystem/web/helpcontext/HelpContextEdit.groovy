package de.balvi.cuba.helpsystem.web.helpcontext

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.PersistenceHelper
import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.FieldGroup
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.components.actions.CreateAction
import com.haulmont.cuba.gui.components.actions.EditAction
import com.haulmont.cuba.gui.config.WindowConfig
import com.haulmont.cuba.gui.config.WindowInfo
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext

import javax.inject.Inject

class HelpContextEdit extends AbstractEditor<HelpContext> {


    @Inject
    protected FieldGroup fieldGroup

    @Inject
    protected ComponentsFactory componentsFactory

    @Inject
    Table<Helptext> helptextsTable

    @Inject
    Datasource<HelpContext> helpContextDs


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
        FieldGroup.FieldConfig screenIdFieldConfig = fieldGroup.getField('screenId')
        LookupField lookupField = createScreenLookupField(screenOptions)
        screenIdFieldConfig.component = lookupField
    }

    protected LookupField createScreenLookupField(LinkedHashMap screenOptions) {
        LookupField lookupField = componentsFactory.createComponent(LookupField.NAME) as LookupField
        lookupField.enabled = PersistenceHelper.isNew(item) && item.screenId == null
        lookupField.optionsMap = screenOptions
        lookupField.setDatasource(helpContextDs, 'screenId')
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