package master.project.master_application.social_providers;

import master.project.master_application.model.UserData;
import master.project.master_application.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LinkedInProvider extends AbstractPopulator {

	private static final String LINKED_IN = "linkedIn";
	
	private static final String REDIRECT_LOGIN = "redirect:/login";

  	@Autowired
	BaseProvider baseProvider ;

	public String getLinkedInUserData(Model model, UserEntity userBean) {

		ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
			return REDIRECT_LOGIN;
		}
		UserData userData = populateUserDetailsFromLinkedIn(new UserData());
		//Check if all Info has been collected
		if(!baseProvider.isAllInformationAvailable(userBean)) {
			model.addAttribute("userBean", userBean);
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

	private UserData populateUserDetailsFromLinkedIn(UserData userData) {
		LinkedIn linkedIn = baseProvider.getLinkedIn();
		LinkedInProfileFull linkedInUser = linkedIn.profileOperations().getUserProfileFull();
		userData.setEmail(linkedInUser.getEmailAddress());
		userData.setFirstName(linkedInUser.getFirstName());
		userData.setLastName(linkedInUser.getLastName());
		userData.setTitle(linkedInUser.getPositions().get(0).getTitle());
		userData.setImage(linkedInUser.getProfilePictureUrl());
		userData.setProvider(LINKED_IN);
		return userData;
	}

}
