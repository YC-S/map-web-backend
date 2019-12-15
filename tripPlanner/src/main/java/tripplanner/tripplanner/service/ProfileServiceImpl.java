package tripplanner.tripplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripplanner.tripplanner.dao.ProfileDao;
import tripplanner.tripplanner.model.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao profileDao;

	@Override
	@Transactional
	public void addOrUpdateProfile(Profile profile) {
		profileDao.addOrUpdateProfile(profile);
	}

	@Override
	@Transactional
	public void deleteProfileById(int profileId) {
		profileDao.deleteProfileById(profileId);
	}

	@Override
	@Transactional
	public Profile findProfileById(int profileId) {
		return profileDao.findProfileById(profileId);
	}

}
