package tripplanner.tripplanner.dao;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tripplanner.tripplanner.model.Profile;


@Repository
public class ProfileDaoImpl implements ProfileDao {
	
	private EntityManager entityManager;
	
	@Autowired
	public ProfileDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	

	@Override
	public void addOrUpdateProfile(Profile profile) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(profile);
	}

	@Override
	public void deleteProfileById(int profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Profile profile = findProfileById(profileId);
		
		currentSession.delete(profile);
	}

	@Override
	public Profile findProfileById(int profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Profile profile = currentSession.get(Profile.class, profileId);
		
	    return profile;
	}
}
