package com.leezer.service.sample.profile.dao;


import com.leezer.service.sample.profile.model.Profile;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface ProfileRepository extends CrudRepository<Profile,Long> {
}
