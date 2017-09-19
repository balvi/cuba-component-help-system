package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.components.actions.CreateAction
import com.haulmont.cuba.gui.components.actions.EditAction
import de.balvi.cuba.helpsystem.entity.Helptext

import javax.inject.Inject

class HelptextBrowse extends AbstractLookup {

    @Inject
    Table<Helptext> helptextsTable

    @Override
    void init(Map<String, Object> params) {
        super.init(params)
        initHelptextActions()
    }

    protected void initHelptextActions() {
        CreateAction createAction = helptextsTable.getAction('create') as CreateAction
        EditAction editAction = helptextsTable.getAction('edit') as EditAction
        def windowParams = [contextIndependent: true]
        createAction.windowParams = windowParams

        editAction.windowParams = windowParams
    }
}