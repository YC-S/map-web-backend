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
	public String addOrUpdateProfile(Profile profile) {
		profileDao.addOrUpdateProfile(profile);
		return profile.getId();
	}

	@Override
	@Transactional
	public void deleteProfileById(String profileId) {
		profileDao.deleteProfileById(profileId);
	}

	@Override
	@Transactional
	public Profile findProfileById(String profileId) {
		return profileDao.findProfileById(profileId);
	}

}
