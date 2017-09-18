package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.components.AbstractEditor
import de.balvi.cuba.helpsystem.entity.Helptext

class HelptextEdit extends AbstractEditor<Helptext> {

    @Override
    protected void postInit() {
        super.postInit()

        if (item.helpContext) {
            showNotification("hello test")
        }
    }
}