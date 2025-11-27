    package com.MarcOnAMission.finance_tracker.Controllers;

    import com.MarcOnAMission.finance_tracker.DataTransferObjects.UserDataTransferObject;
    import com.MarcOnAMission.finance_tracker.Services.UserService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @Tag(name = "User Controller",description = "CRUD Operations on Application User Entities")
    @RestController
    @RequestMapping("api/users")
    public class UserController {

        @Autowired
        UserService service;

        @Operation(summary = "Registers user",description = "Takes in input data and creates a user entity.")
        @PostMapping("/create")
        public ResponseEntity<UserDataTransferObject> registerUser(@RequestBody UserDataTransferObject inputBodyDataFromRequest){
            return ResponseEntity.ofNullable(service.validateCredentialInputAndCreateApplicationUser(inputBodyDataFromRequest));
        }

        @Operation(summary = "Update user",description = "Takes in input data and an id and updates a user entity if found.")
        @PatchMapping("/{id}")
        public ResponseEntity<UserDataTransferObject> updateUserData(@PathVariable long id,@RequestBody UserDataTransferObject inputBodyOfDataToUpdate){
            return ResponseEntity.ofNullable(service.updateApplicationUserInformation(id,inputBodyOfDataToUpdate));
        }

        @Operation(summary = "Find user by id",description = "Takes in id and retrieves data for user if found.")
        @GetMapping("/{id}")
        public ResponseEntity<UserDataTransferObject> getUserById(@PathVariable long idToRetrieve){
            return ResponseEntity.ofNullable(service.retrieveApplicationUserFromDatabaseById(idToRetrieve));
        }

        @Operation(summary = "Deletes user by id",description = "Takes in id and deletes user if found.")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUserById(@PathVariable long id){
            service.deleteApplicationUserFromDatabaseById(id);
            return ResponseEntity.noContent().build();
        }
    }
