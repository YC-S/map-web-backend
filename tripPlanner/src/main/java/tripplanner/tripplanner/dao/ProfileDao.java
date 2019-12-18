package tripplanner.tripplanner.dao;

import tripplanner.tripplanner.model.Profile;

public interface ProfileDao {
	
	public void addOrUpdateProfile(Profile profile);
	
	public void deleteProfileById(int profileId);
	
	public Profile findProfileById(int profileId);
	
}
