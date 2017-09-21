package de.balvi.cuba.helpsystem.service

import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import de.balvi.cuba.helpsystem.entity.HelpContext
import de.balvi.cuba.helpsystem.entity.Helptext
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(HelpContextService.NAME)
class HelpContextServiceBean implements HelpContextService {

    @Inject
    DataManager dataManager
    private final String SCREEN_ID_FIELD_NAME = 'screenId'

    @Override
    HelpContext getHelpContext(String screenId, String componentId) {

        LoadContext.Query query = createQueryForHelpContext(screenId, componentId)
        LoadContext loadContext =
                LoadContext.create(HelpContext)
                        .setQuery(query)
                        .setView('helpContext-with-texts-view')
        dataManager.load(loadContext)
    }

    protected LoadContext.Query createQueryForHelpContext(String screenId, String componentId) {
        def sqlString = 'select e from dbchs$HelpContext e'

        if (screenId) {
            sqlString += ' where e.screenId = :screenId'

            if (componentId) {
                sqlString += ' and e.componentId = :componentId'
                LoadContext.createQuery(sqlString)
                        .setParameter(SCREEN_ID_FIELD_NAME, screenId)
                        .setParameter('componentId', componentId)
            }
            else {
                LoadContext.createQuery(sqlString)
                        .setParameter(SCREEN_ID_FIELD_NAME, screenId)
            }
        }
        else {
            LoadContext.createQuery(sqlString)
        }
    }

    @Override
    Collection<Helptext> getContextIndependentHelptexts() {
        LoadContext loadContext = LoadContext.create(Helptext)
                .setQuery(LoadContext.createQuery('select e from dbchs$Helptext e where e.helpContext is null')
        ).setView('helptext-with-category-view')

        dataManager.loadList(loadContext)
    }
}