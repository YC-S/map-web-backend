package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Profile;

public interface ProfileService {
	
	public void addOrUpdateProfile(Profile profile);
	
	public void deleteProfileById(int profileId);
	
	public Profile findProfileById(int profileId);

}
