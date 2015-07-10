package com.leezer.service.sample.profile.endpoint;

import com.leezer.service.sample.profile.model.Profile;
import com.leezer.service.sample.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URISyntaxException;
import java.util.List;

@Component
@Path("/profiles")
public class ProfileEndpoint {

    private final ProfileService profileService;

    @Autowired
    public ProfileEndpoint(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@PathParam("id") long id){
        final Profile profile = profileService.getProfile(id);
        if (profile != null){
            return Response.ok(profile).build();
        } else {
            return Response.status(404).build();
        }

    }

    @POST
    public Response createProfile(@Context UriInfo uriInfo, Profile profile) throws URISyntaxException {
        final long id = profileService.createProfile(profile);
        return Response.created(UriBuilder.fromUri(uriInfo.getAbsolutePath()).path(Long.toString(id)).build()).build();

    }

    @DELETE
    @Path("{id}")
    public Response deleteProfile(@PathParam("id") long id){
        profileService.deleteProfile(id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(@PathParam("id") long id, Profile profile){
        if(profileService.doesProfileExist(id)) {
            profileService.updateProfile(id, profile);
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }




    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProfiles() {
        List<Profile> profiles =  profileService.getAllProfiles();
        return Response.ok(profiles).build();
    }




}
