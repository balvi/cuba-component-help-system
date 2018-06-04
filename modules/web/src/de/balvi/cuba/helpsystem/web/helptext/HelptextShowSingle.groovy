package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.components.AbstractEditor
import de.balvi.cuba.helpsystem.entity.Helptext

class HelptextShowSingle extends AbstractEditor<Helptext> {

    @Override
    protected void postInit() {
        caption = formatMessage('showCaption',item.category.name)
    }
}