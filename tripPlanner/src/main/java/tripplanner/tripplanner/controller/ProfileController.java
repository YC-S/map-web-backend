package tripplanner.tripplanner.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.Item;
import tripplanner.tripplanner.model.Profile;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.ProfileService;

@RestController
@RequestMapping("/api")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private UserDao userDao;
	
	// Need to change this folder path to your own local path

	String folder = "/Users/dengyang/eclipse-workspace/profile_images";
	
	@GetMapping("/profile/{profileId}")
	public Profile getProfileById(@PathVariable String profileId) {
		Profile myProfile = profileService.findProfileById(profileId);
		if(myProfile == null) {
			throw new RuntimeException("Profile id not found - " + profileId);
		}
		return myProfile;
	}
	
	@ResponseBody
	@RequestMapping(value = "/profileImage/{userId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getProfileImage(@PathVariable String userId) throws IOException {
		// find the user by userId
		Optional<User> result = userDao.findById(userId);
		User theUser = null;
		if(result.isPresent()) {
			theUser = result.get();
			Profile theProfile = theUser.getCores_profile();
			String profileId = theProfile.getId();
		    InputStream in = context.getResourceAsStream(folder + profileId + ".jpg");
		    return IOUtils.toByteArray(in);
		} else {
			throw new RuntimeException("Did not find user id - " + userId);
		}
	}
	
	@DeleteMapping("/profile/{profileId}")
	public String deleteProfile(@PathVariable String profileId) {
		Profile myProfile = profileService.findProfileById(profileId);
		if(myProfile == null) {
			throw new RuntimeException("Profile id not found - " + profileId);
		}
		profileService.deleteProfileById(profileId);
		// delete the profile image from server
		Path path = Paths.get(folder + profileId + ".png");
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return "Deleted employee id - " + profileId;
	}
	
	@PostMapping("/saveProfile")
	public void addProfile(@RequestPart("json") Profile profile, @RequestPart("file") MultipartFile file) {
		// save profile into DB first
		profileService.addOrUpdateProfile(profile);
		// save profile image to disk
		try {
			saveProfileImage(file, profile.getId());
		} catch (Exception e) {

			System.out.println("addProfile failure!");
			e.printStackTrace();
		}
	}
	
	
	/** This method is used to save profile image to server disk
	 */
	public void saveProfileImage(MultipartFile imageFile, String profileId) throws Exception {
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(folder + profileId + ".png");
		Files.write(path, bytes);
	}

}
