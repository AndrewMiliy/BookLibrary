package repositories;

import models.UserModel;

import java.util.List;
import java.util.function.Predicate;

public class UserRepository {
    ElasticArray <UserModel> users = new ElasticArray <UserModel>();


    public void add(UserModel user) {
        if (Validate.validateEmail(user.getEmail())
                && Validate.isPasswordValid(user.getPassword())
                && Validate.validateName(user.getFirstName())
                && Validate.validateName(user.getLastName())) {
            users.add(user);
        }
    }

    public void remove(UserModel user) {
        users.remove(users.findIndexOf(x -> x.equals(user)));
    }

    public UserModel getUser(Predicate<UserModel> predicate){
        return users.find(predicate);
    }
    public List<UserModel> getUsers(Predicate<UserModel> predicate){
        return users.findAll(predicate);
    }

    public void editUser (UserModel user) {

        UserModel targetUser = users.find(x -> x.equals(user.getId()));

        targetUser.setEmail(targetUser.getEmail().equals(user.getEmail()) ? targetUser.getEmail() : user.getEmail());
        targetUser.setFirstName(targetUser.getFirstName().equals(user.getFirstName()) ? targetUser.getFirstName() : user.getFirstName());
        targetUser.setLastName(targetUser.getLastName().equals(user.getLastName()) ? targetUser.getLastName() : user.getLastName());
        targetUser.setPassword(targetUser.getPassword().equals(user.getPassword()) ? targetUser.getPassword() : user.getPassword());
    }



}
