package com.MarcOnAMission.finance_tracker.ServiceImplementations;

import com.MarcOnAMission.finance_tracker.CustomExceptions.UserNotFoundException;
import com.MarcOnAMission.finance_tracker.CustomExceptions.UsernameAlreadyExistsException;
import com.MarcOnAMission.finance_tracker.CustomExceptions.WeakPasswordException;
import com.MarcOnAMission.finance_tracker.DataTransferObjects.UserDataTransferObject;
import com.MarcOnAMission.finance_tracker.Mappers.ApplicationUserDTOMapper;
import com.MarcOnAMission.finance_tracker.Model.ApplicationUser;
import com.MarcOnAMission.finance_tracker.Repositories.UserRepository;
import com.MarcOnAMission.finance_tracker.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository users;

    @Override
    public UserDataTransferObject retrieveApplicationUserFromDatabaseById(long appUserIdToRetrieve) {
        return ApplicationUserDTOMapper.createUserDataTransferObject(users.findById(appUserIdToRetrieve).orElseThrow(()->new UserNotFoundException("User not found at this id"+appUserIdToRetrieve)));
    }

    @Override
    public UserDataTransferObject updateApplicationUserInformation(long id,UserDataTransferObject passedUserDataTransferObject) {
        validateCredentials(passedUserDataTransferObject);
        userOfIdIsPresent(id);
        Optional<ApplicationUser> foundUser = users.findById(id);
        ApplicationUser updateUser = foundUser.get();
        updateUser.setPassword(passedUserDataTransferObject.getPassword());
        updateUser.setUsername(passedUserDataTransferObject.getUsername());
        return ApplicationUserDTOMapper.createUserDataTransferObject(users.save(updateUser));
    }

    @Override
    public UserDataTransferObject validateCredentialInputAndCreateApplicationUser(UserDataTransferObject passedUserDataTransferObject) {
        validateCredentials(passedUserDataTransferObject);
        ApplicationUser user = users.save(ApplicationUserDTOMapper.createAppUserEntity(passedUserDataTransferObject));
        return ApplicationUserDTOMapper.createUserDataTransferObject(user);
    }

    private void userOfIdIsPresent(long id){
        if(!users.existsById(id)){
            throw new UserNotFoundException("User of this id not found"+id);
        }
    }

    private void validateCredentials(UserDataTransferObject passedDataTransferObjectToValidate){
        Optional<ApplicationUser> checkIfUserAlreadyExists = users.findByUsername(passedDataTransferObjectToValidate.getUsername());
        if(checkIfUserAlreadyExists.isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists"+passedDataTransferObjectToValidate.getUsername());
        }
        if(passedDataTransferObjectToValidate.getPassword().length()<4){
            throw new WeakPasswordException("Password cannot be shorter than 4 characters");
        }
    }

    @Override
    public void deleteApplicationUserFromDatabaseById(long appUserIdToDelete) {
        if(users.existsById(appUserIdToDelete)){
            users.deleteById(appUserIdToDelete);
        }
        else {
            throw new UserNotFoundException("User not found at this id"+appUserIdToDelete);
        }
    }
}
