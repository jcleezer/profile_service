package com.leezer.service.sample.profile.dao;

import com.leezer.service.sample.profile.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfileDao {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileDao(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Long createProfile(Profile profile){
        return profileRepository.save(profile).getId();

    }

    public Profile getProfileById(long id) {
        return profileRepository.findOne(id);
    }

    public void deleteProfileById(long id){
        profileRepository.delete(id);
    }

    public void updateProfile(long id, Profile profile){
        profile.setId(id);
        profileRepository.save(profile); //TODO be sure to test this
    }

    public List<Profile> getAllProfiles() {
        final List<Profile> profiles  = new ArrayList<Profile>();
        final Iterable<Profile> profileIterable = profileRepository.findAll();
        for (Profile profile: profileIterable){
            profiles.add(profile);
        }
        return profiles;
    }
}
