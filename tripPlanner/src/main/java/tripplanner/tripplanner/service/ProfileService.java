package tripplanner.tripplanner.service;

import tripplanner.tripplanner.model.Profile;

public interface ProfileService {
	
	public String addOrUpdateProfile(Profile profile);
	
	public void deleteProfileById(String profileId);
	
	public Profile findProfileById(String profileId);

}
