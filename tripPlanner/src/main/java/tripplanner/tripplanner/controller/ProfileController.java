package tripplanner.tripplanner.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tripplanner.tripplanner.model.Profile;
import tripplanner.tripplanner.service.ProfileService;

@RestController
@RequestMapping("/api")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	ServletContext context;
	
	// Need to change this folder path to your own local path
	String folder = "/Users/shiyuanchen/Desktop/profile_images/";
	
	@GetMapping("/profile/{profileId}")
	public Profile getProfileById(@PathVariable int profileId) {
		Profile myProfile = profileService.findProfileById(profileId);
		if(myProfile == null) {
			throw new RuntimeException("Profile id not found - " + profileId);
		}
		return myProfile;
	}
	
	@DeleteMapping("/profile/{profileId}")
	public String deleteProfile(@PathVariable int profileId) {
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
	
	@PostMapping("/addprofile")
	public void addProfile(@RequestPart("json") Profile profile, @RequestPart("file") MultipartFile file) {
		// save profile into DB first
		profileService.addOrUpdateProfile(profile);
		// save profile image to disk
		try {
			saveProfileImage(file, profile.getId());
		} catch (Exception e) {
			System.out.println("addPrfile failure!");
			e.printStackTrace();
		}
	}
	
	
	/** This method is used to save profile image to server disk
	 */
	public void saveProfileImage(MultipartFile imageFile, int profileId) throws Exception {
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(folder + profileId + ".png");
		Files.write(path, bytes);
	}

}
