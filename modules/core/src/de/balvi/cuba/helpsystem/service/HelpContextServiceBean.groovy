package de.balvi.cuba.helpsystem.service

import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext;
import org.springframework.stereotype.Service;

@Service(HelpContextService.NAME)
class HelpContextServiceBean implements HelpContextService {

    @Override
    HelpContext getHelpContext(String screenId, String componentId) {
        return null
    }

    @Override
    Collection<Helptext> getContextIndependentHelptexts() {
        return null
    }
}