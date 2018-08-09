package master.project.master_application.social_providers;

import master.project.master_application.model.UserData;
import master.project.master_application.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoogleProvider extends AbstractPopulator {

	private static final String REDIRECT_CONNECT_GOOGLE = "redirect:/login";
	private static final String GOOGLE = "google";

   	@Autowired
    BaseProvider baseProvider ;

	public String getGoogleUserData(Model model, UserEntity userBean) {

		ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Google.class) == null) {
			return REDIRECT_CONNECT_GOOGLE;
		}

		UserData userData = populateUserDetailsFromGoogle(new UserData());
		if(!baseProvider.isAllInformationAvailable(userBean)) {
			model.addAttribute("userBean", userData);
			return "incompleteInfo";
		}
		populateUserEntityObject(userData, userBean);
		//Save the details in DB
		baseProvider.saveUserDetails(userBean);
		//Login the User
		baseProvider.autoLoginUser(userBean);
		model.addAttribute("loggedInUser",userData);
		return "secure/user";
	}

	protected UserData populateUserDetailsFromGoogle(UserData userData) {
		Google google = baseProvider.getGoogle();
		Person googleUser = google.plusOperations().getGoogleProfile();
		userData.setEmail(googleUser.getAccountEmail());
		userData.setFirstName(googleUser.getGivenName());
		userData.setLastName(googleUser.getFamilyName());
		userData.setImage(googleUser.getImageUrl());
		userData.setProvider(GOOGLE);
		return userData;
	}

}
