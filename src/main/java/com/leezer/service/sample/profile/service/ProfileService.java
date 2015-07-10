package com.leezer.service.sample.profile.service;

import com.leezer.service.sample.profile.dao.ProfileDao;
import com.leezer.service.sample.profile.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileService {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileService(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public long createProfile(Profile profile){
        return profileDao.createProfile(profile);

    }

    public Profile getProfile(long id) {
        return profileDao.getProfileById(id);
    }

    public void deleteProfile(long id){
        profileDao.deleteProfileById(id);
    }

    public void updateProfile(long id, Profile profile){
        profileDao.updateProfile(id, profile);
    }

    public List<Profile> getAllProfiles() {
        return profileDao.getAllProfiles();
    }

    public boolean doesProfileExist(long id) {
        return getProfile(id) != null;
    }
}
