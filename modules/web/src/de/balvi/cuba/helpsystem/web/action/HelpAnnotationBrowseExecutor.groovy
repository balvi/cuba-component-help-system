package de.balvi.cuba.helpsystem.web.action

import com.haulmont.cuba.gui.components.Window
import de.balvi.cuba.declarativecontrollers.web.browse.BrowseAnnotationExecutor
import org.springframework.stereotype.Component

import javax.inject.Inject
import java.lang.annotation.Annotation

@Component
class HelpAnnotationBrowseExecutor implements BrowseAnnotationExecutor<HasHelp> {

    @Inject
    HelpRenderer helpRenderer

    @Override
    void init(HasHelp annotation, Window.Lookup browse, Map<String, Object> params) {}

    @Override
    void ready(HasHelp annotation, Window.Lookup browse, Map<String, Object> params) {
        helpRenderer.initComponent(browse.frame)
    }

    @Override
    boolean supports(Annotation annotation) {
        annotation instanceof HasHelp
    }
}
