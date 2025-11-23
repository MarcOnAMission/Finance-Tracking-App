    package com.MarcOnAMission.finance_tracker.Controllers;

    import com.MarcOnAMission.finance_tracker.DataTransferObjects.UserDataTransferObject;
    import com.MarcOnAMission.finance_tracker.Services.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("api/users")
    public class UserController {
        @Autowired
        UserService service;

        @PostMapping("/create")
        public ResponseEntity<UserDataTransferObject> registerUser(@RequestBody UserDataTransferObject inputBodyDataFromRequest){
            return ResponseEntity.ofNullable(service.validateCredentialInputAndCreateApplicationUser(inputBodyDataFromRequest));
        }
        @PatchMapping("/update-data")
        public ResponseEntity<UserDataTransferObject> updateUserData(@RequestBody UserDataTransferObject inputBodyOfDataToUpdate){
            return ResponseEntity.ofNullable(service.updateApplicationUserInformation(inputBodyOfDataToUpdate));
        }
        @GetMapping("/retrieve-user-by-id")
        public ResponseEntity<UserDataTransferObject> getUserById(@RequestParam long idToRetrieve){
            return ResponseEntity.ofNullable(service.retrieveApplicationUserFromDatabaseById(idToRetrieve));
        }
        @DeleteMapping("/delete-user-by-id")
        public ResponseEntity<Void> deleteUserById(@RequestParam long id){
            service.deleteApplicationUserFromDatabaseById(id);
            return ResponseEntity.noContent().build();
        }
    }
