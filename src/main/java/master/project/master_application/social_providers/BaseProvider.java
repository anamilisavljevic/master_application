package master.project.master_application.social_providers;

import master.project.master_application.dao.UserDao;
import master.project.master_application.model.UserEntity;
import master.project.master_application.session_config.Autologin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.springframework.social.linkedin.api.LinkedIn;

import javax.annotation.Resource;

@Configuration
@Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

	private Facebook facebook;
	private Google google;
	private LinkedIn linkedIn;
	private ConnectionRepository connectionRepository;
	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Resource
	private UserDao userDao;

	@Resource
	protected Autologin autologin;

	public BaseProvider(Facebook facebook, Google google, LinkedIn linkedIn, ConnectionRepository connectionRepository,
		UsersConnectionRepository usersConnectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
		this.google = google;
		this.linkedIn = linkedIn;
	}

	public void autoLoginUser(UserEntity userEntity) {
		autologin.setSecurityontext(userEntity);
	}

	protected void saveUserDetails(UserEntity userBean) {
		if (StringUtils.isNotEmpty(userBean.getPassword())) {
			userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
		}
		userDao.saveWithoutPassword(userBean.getName(), userBean.getSurname(), userBean.getCountry(), userBean.getImage(), userBean.getEmail());
	}

	protected boolean isAllInformationAvailable(UserEntity userBean) {
		return StringUtils.isNotEmpty(userBean.getEmail()) &&
			StringUtils.isNotEmpty(userBean.getName()) &&
			StringUtils.isNotEmpty(userBean.getSurname()) &&
			StringUtils.isNotEmpty(userBean.getCountry());
	}

	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	public ConnectionRepository getConnectionRepository() {
		return connectionRepository;
	}

	public void setConnectionRepository(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	public Google getGoogle() {
		return google;
	}

	public void setGoogle(Google google) {
		this.google = google;
	}

	public LinkedIn getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(LinkedIn linkedIn) {
		this.linkedIn = linkedIn;
	}

	public UsersConnectionRepository getUsersConnectionRepository() {
		return usersConnectionRepository;
	}

	public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
		this.usersConnectionRepository = usersConnectionRepository;
	}
}
