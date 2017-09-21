package de.balvi.cuba.helpsystem.web.action

import com.haulmont.cuba.gui.components.Window
import de.balvi.cuba.declarativecontrollers.web.annotationexecutor.browse.BrowseAnnotationExecutor
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.inject.Inject
import java.lang.annotation.Annotation

@Component
@Scope("prototype")
class HelpAnnotationBrowseExecutor implements BrowseAnnotationExecutor<HasHelp> {

    @Inject
    HelpSidePanelRenderer helpSidePanelRenderer

    @Override
    void init(HasHelp annotation, Window.Lookup browse, Map<String, Object> params) {}

    @Override
    void ready(HasHelp annotation, Window.Lookup browse, Map<String, Object> params) {
        helpSidePanelRenderer.initComponent(browse.frame)
    }

    @Override
    boolean supports(Annotation annotation) {
        annotation instanceof HasHelp
    }
}
