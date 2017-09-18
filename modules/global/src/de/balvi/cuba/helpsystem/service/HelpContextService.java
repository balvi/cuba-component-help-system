package de.balvi.cuba.helpsystem.service;


import de.balvi.cuba.helpsystem.entity.HelpContext;
import de.balvi.cuba.helpsystem.entity.Helptext;

import java.util.Collection;

public interface HelpContextService {
    String NAME = "dbchs_HelpContextService";

    HelpContext getHelpContext(String screenId, String componentId);

    Collection<Helptext> getContextIndependentHelptexts();
}