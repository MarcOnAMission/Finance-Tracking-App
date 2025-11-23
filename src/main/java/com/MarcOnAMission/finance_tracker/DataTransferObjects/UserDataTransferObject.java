package com.MarcOnAMission.finance_tracker.DataTransferObjects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Application user data transfer object used to create a new user.")
public class UserDataTransferObject {
        @Schema(description = "User's password the person intends to use",example = "007badboy")
        private String password;
        @Schema(description = "User's username the person intends to use",example = "ILoveCoding")
        private String username;

}
