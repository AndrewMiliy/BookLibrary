package repositories;

import models.UserModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserRepository {
    ElasticArray <UserModel> users = new ElasticArray <UserModel>();

    private final String filename = "USERDB.txt";

    public void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  ElasticArray <UserModel> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ElasticArray <models.UserModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ElasticArray <>();
        }
    }

    public boolean add(UserModel user) {
        if (Validate.validateEmail(user.getEmail())
                && Validate.isPasswordValid(user.getPassword())
                && Validate.validateName(user.getFirstName())
                && Validate.validateName(user.getLastName())) {
            users.add(user);
            return true;
        }
        return false;
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

    public int getUserCount() {
        return users.size();
    }

    public boolean editUser (UserModel user) {
        if (!Validate.validateName(user.getFirstName())
                || !Validate.validateName(user.getLastName())
                || !Validate.validateEmail(user.getEmail())
                || !Validate.isPasswordValid(user.getPassword())) {
        return false;
        }
        UserModel targetUser = users.find(x -> x.equals(user.getId()));

        targetUser.setEmail(targetUser.getEmail().equals(user.getEmail()) ? targetUser.getEmail() : user.getEmail());
        targetUser.setFirstName(targetUser.getFirstName().equals(user.getFirstName()) ? targetUser.getFirstName() : user.getFirstName());
        targetUser.setLastName(targetUser.getLastName().equals(user.getLastName()) ? targetUser.getLastName() : user.getLastName());
        targetUser.setPassword(targetUser.getPassword().equals(user.getPassword()) ? targetUser.getPassword() : user.getPassword());
        return true;
    }




}
