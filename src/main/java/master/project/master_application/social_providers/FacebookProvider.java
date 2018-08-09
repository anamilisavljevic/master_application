package master.project.master_application.social_providers;

import master.project.master_application.model.UserData;
import master.project.master_application.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PageOperations;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class FacebookProvider extends AbstractPopulator{

    private static final String FACEBOOK = "facebook";
    private static final String REDIRECT_LOGIN = "redirect:/";

    @Autowired
    BaseProvider baseProvider;

    public String getFacebookUserData(Model model, UserEntity userBean) {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return REDIRECT_LOGIN;
        }
        Connection<Facebook> facebookConnec = connectionRepository.getPrimaryConnection(Facebook.class);
        Facebook facebook = facebookConnec.getApi();

        UserData userData = populateUserDetailsFromFacebook(new UserData());
        if(!baseProvider.isAllInformationAvailable(userBean)) {
            model.addAttribute("userBean", userBean);
            return "incompleteInfo";
        }

        populateUserEntityObject(userData, userBean);

        //Save the details in DB
        baseProvider.saveUserDetails(userBean);
        //Login the User
        baseProvider.autoLoginUser(userBean);
        model.addAttribute("loggedInUser",userBean);
        return "secure/user";
    }

    public void postStatusOn(String userId, String message) {
        ConnectionRepository connectionRepository = baseProvider.getUsersConnectionRepository().createConnectionRepository(userId);
        Connection<Facebook> facebookConnec = connectionRepository.getPrimaryConnection(Facebook.class);
        Facebook facebook = facebookConnec.getApi();
        PageOperations pageOps = facebook.pageOperations();
        for(Account account : pageOps.getAccounts()) {
        }
        facebook.pageOperations().post("1", message);
    }

    protected UserData populateUserDetailsFromFacebook(UserData userData) {
        Facebook facebook = baseProvider.getFacebook();
        String [] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift", "website", "work"};
        User user = facebook.fetchObject("me", User.class, fields);
        userData.setEmail(user.getEmail());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setImage(user.getCover().getSource());
        userData.setProvider(FACEBOOK);
        return userData;
    }


}
