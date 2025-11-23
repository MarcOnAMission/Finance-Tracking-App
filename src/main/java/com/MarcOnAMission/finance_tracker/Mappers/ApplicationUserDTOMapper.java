package com.MarcOnAMission.finance_tracker.Mappers;

import com.MarcOnAMission.finance_tracker.DataTransferObjects.UserDataTransferObject;
import com.MarcOnAMission.finance_tracker.Model.ApplicationUser;

public class ApplicationUserDTOMapper {
    public static ApplicationUser createAppUserEntity(UserDataTransferObject dataTransferObject){
        return new ApplicationUser(dataTransferObject.getUsername(), dataTransferObject.getPassword());
    }
    public static UserDataTransferObject createUserDataTransferObject(ApplicationUser passedApplicationUser){
        return new UserDataTransferObject(passedApplicationUser.getUsername(), passedApplicationUser.getPassword());
    }
}
