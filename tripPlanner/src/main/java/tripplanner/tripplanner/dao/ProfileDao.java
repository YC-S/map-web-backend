package tripplanner.tripplanner.dao;

import tripplanner.tripplanner.model.Profile;

public interface ProfileDao {
	
	public void addOrUpdateProfile(Profile profile);
	
	public void deleteProfileById(String profileId);
	
	public Profile findProfileById(String profileId);
	
}
