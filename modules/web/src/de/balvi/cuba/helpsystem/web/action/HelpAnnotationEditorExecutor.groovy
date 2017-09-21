package de.balvi.cuba.helpsystem.web.action

import com.haulmont.cuba.gui.components.Window
import de.balvi.cuba.declarativecontrollers.web.annotationexecutor.editor.EditorAnnotationExecutor
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.inject.Inject
import java.lang.annotation.Annotation

@Component
@Scope("prototype")
class HelpAnnotationEditorExecutor implements EditorAnnotationExecutor<HasHelp> {

    @Inject
    HelpSidePanelRenderer helpSidePanelRenderer

    @Override
    void init(HasHelp annotation, Window.Editor editor, Map<String, Object> params) {

    }

    @Override
    void postInit(HasHelp annotation, Window.Editor editor) {
        helpSidePanelRenderer.initComponent(editor.frame)
    }

    @Override
    boolean supports(Annotation annotation) {
        annotation instanceof HasHelp
    }
}
