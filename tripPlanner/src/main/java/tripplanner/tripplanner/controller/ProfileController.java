package tripplanner.tripplanner.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		// TODO: delete the profile image from server
		
		
		return "Deleted employee id - " + profileId;
	}
	
	@PostMapping("/addprofile")
	public void addProfile(@RequestPart("json") Profile profile, @RequestPart("file") MultipartFile file) {
		// save profile into DB first
		profileService.addOrUpdateProfile(profile);
		// save profile image to disk
		try {
			saveProfileImage(file);
		} catch (Exception e) {
			System.out.println("addPrfile failure!");
			e.printStackTrace();
		}
	}
	
	
	/** This method is used to save profile image to server disk
	 */
	public void saveProfileImage(MultipartFile imageFile) throws Exception {
		String folder = "/Users/dengyang/eclipse-workspace/profile_images/";
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);
	}

}
