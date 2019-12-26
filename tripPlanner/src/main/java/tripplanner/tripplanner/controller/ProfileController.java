package tripplanner.tripplanner.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tripplanner.tripplanner.dao.UserDao;
import tripplanner.tripplanner.model.Profile;
import tripplanner.tripplanner.model.User;
import tripplanner.tripplanner.service.ProfileService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private UserDao userDao;
	
//	String folder = "/Users/dengyang/eclipse-workspace/map-web-backend/tripPlanner/src/main/resources/";
	String folder = "./src/main/resources/";
	
	@GetMapping("/profile/{profileId}")
	public Profile getProfileById(@PathVariable String profileId) {
		Profile myProfile = profileService.findProfileById(profileId);
		if(myProfile == null) {
			throw new RuntimeException("Profile id not found - " + profileId);
		}
		return myProfile;
	}
	
	// Hit endpoint: localhost:8080/api/profileImage/402881ae6f201328016f201e234d0003
	// 402881ae6f201328016f201e234d0003 is profileId
	@GetMapping(value="/profileImage/{profileId}", produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getProfileImage(@PathVariable String profileId) throws IOException {
		InputStream in = ProfileController.class.getClassLoader().getResourceAsStream(profileId + ".png");
		if(in == null) {
			throw new RuntimeException("Profile image cannot be found - " + profileId);
		} 
		return IOUtils.toByteArray(in);
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
		try {
			// save profile into DB first
			String profileId = profileService.addOrUpdateProfile(profile);
			// save profile image to disk
			saveProfileImage(file, profileId);
		} catch (Exception e) {
			System.out.println("addProfile failure!");
			e.printStackTrace();
		}
	}

	@PutMapping("/updateProfile")
	public void updateProfile(@RequestPart("json") Profile profile, @RequestPart("file") MultipartFile file) {
		try {
			// save profile into DB first
			String profileId = profileService.addOrUpdateProfile(profile);
			// save profile image to disk
			saveProfileImage(file, profileId);
		} catch (Exception e) {
			System.out.println("updateProfile failure!");
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
