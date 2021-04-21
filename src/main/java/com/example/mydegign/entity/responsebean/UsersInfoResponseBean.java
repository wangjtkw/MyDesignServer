package com.example.mydegign.entity.responsebean;

import com.example.mydegign.entity.EducationExperiences;
import com.example.mydegign.entity.Users;

public class UsersInfoResponseBean {
    private Users users;
    private EducationExperiences educationExperiences;

    public UsersInfoResponseBean(Users users, EducationExperiences educationExperiences) {
        this.users = users;
        this.educationExperiences = educationExperiences;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public EducationExperiences getEducationExperiences() {
        return educationExperiences;
    }

    public void setEducationExperiences(EducationExperiences educationExperiences) {
        this.educationExperiences = educationExperiences;
    }
}
