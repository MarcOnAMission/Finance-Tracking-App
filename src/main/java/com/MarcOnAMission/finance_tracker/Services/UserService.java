package com.MarcOnAMission.finance_tracker.Services;

import com.MarcOnAMission.finance_tracker.DataTransferObjects.UserDataTransferObject;
import com.MarcOnAMission.finance_tracker.Model.ApplicationUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
        UserDataTransferObject validateCredentialInputAndCreateApplicationUser(UserDataTransferObject passedUserDataTransferObject);
        UserDataTransferObject retrieveApplicationUserFromDatabaseById(long id);
        UserDataTransferObject updateApplicationUserInformation(long id,UserDataTransferObject passedUserDataTransferObject);
        void deleteApplicationUserFromDatabaseById(long id);
}
