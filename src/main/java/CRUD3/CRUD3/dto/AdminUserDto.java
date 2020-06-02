package CRUD3.CRUD3.dto;

import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private String id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String roles;

    public MyUser toUser() {
        MyUser user = new MyUser();
        user.setId(id);
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        //тут не записываются роли, ну да и ...
        return user;
    }

    public static AdminUserDto fromUser(MyUser user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUserName(user.getUserName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());

        String userRoles = "";
        for (Role role : user.getRoles()) {
            userRoles += role.getName() + " ";
        }

        adminUserDto.setRoles(userRoles.trim());
        return adminUserDto;
    }

    public static List<AdminUserDto> fromUsers(List<MyUser> users) {
        List<AdminUserDto> dtoUsers = new ArrayList<>();
        for (int i = 0; i < users.size() ; i++) {
            dtoUsers.add(fromUser(users.get(i)));
        }
        return dtoUsers;
    }
}
