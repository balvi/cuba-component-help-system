package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.RichTextArea
import de.balvi.cuba.helpsystem.entity.Helptext

import javax.inject.Inject

class HelptextEdit extends AbstractEditor<Helptext> {


    @Inject
    RichTextArea textTextArea

    void addHelptextLink() {
        openLookup(Helptext, new AddHelptextLinkHandler(target: textTextArea), WindowManager.OpenType.DIALOG)
    }
}
