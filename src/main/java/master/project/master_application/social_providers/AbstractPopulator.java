/*********************************************************************
 * The Initial Developer of the content of this file is NETCONOMY.
 * All portions of the code written by NETCONOMY are property of
 * NETCONOMY. All Rights Reserved.
 *
 * NETCONOMY Software & Consulting GmbH
 * Hilmgasse 4, A-8010 Graz (Austria)
 * FN 204360 f, Landesgericht fuer ZRS Graz
 * Tel: +43 (316) 815 544
 * Fax: +43 (316) 815544-99
 * www.netconomy.net
 *
 * (c) 2018 by NETCONOMY Software & Consulting GmbH
 *********************************************************************/

package master.project.master_application.social_providers;

import master.project.master_application.model.UserData;
import master.project.master_application.model.UserEntity;

public abstract class AbstractPopulator {

    protected void populateUserEntityObject(UserData userData, UserEntity userBean) {
        userBean.setCountry(userData.getCountry());
        userBean.setName(userData.getFirstName());
        userBean.setSurname(userData.getLastName());
        userBean.setEmail(userData.getEmail());
        userBean.setEmail(userData.getEmail());
    }

}
