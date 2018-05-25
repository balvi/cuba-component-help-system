package de.balvi.cuba.helpsystem.web.helptext

import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.Window
import de.balvi.cuba.helpsystem.entity.Helptext

class AddHelptextLinkHandler implements Window.Lookup.Handler {

    Component.HasValue target

    @Override
    void handleLookup(Collection items) {
        target.setValue(getValueForTarget(items))
    }

    String getValueForTarget(Collection items) {
        "${target.getValue()} ${createLinkForHelptext(items[0] as Helptext)}"
    }

    String createLinkForHelptext(Helptext helptext) {
        '<a href="open?screen=dbchs$Helptext.showSingle&amp;item=dbchs$Helptext-' + helptext.id + '">' + helptext.category.name + '</a>'
    }
}