package models;

public class BookModel {
    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isAuthorized() {
        return userRole == UserRole.ListBook || userRole == UserRole.UserRole;
    }

    public void performActionBasedOnUserRole() {
        switch (userRole) {
            case ListBook:
                System.out.println("Perform action for ListBook role.");
                break;
            case UserRole:
                System.out.println("Perform action for UserRole role.");
                break;
            case ID:
                System.out.println("Perform action for ID role.");
                break;
            case FirstName:
                System.out.println("Perform action for FirstName role.");
                break;
            case LastName:
                System.out.println("Perform action for LastName role.");
                break;
            case Email:
                System.out.println("Perform action for Email role.");
                break;
            case HashPassword:
                System.out.println("Perform action for HashPassword role.");
                break;
            default:
                System.out.println("Unauthorized role. Cannot perform the action.");
                break;
        }
    }
    public static void main(String[] args) {
        BookModel bookModel = new BookModel();
        bookModel.setUserRole(UserRole.ListBook);

        System.out.println("User's role: " + bookModel.getUserRole());

        if (bookModel.isAuthorized()) {
            System.out.println("User is authorized to perform this action.");
        } else {
            System.out.println("User is not authorized to perform this action.");
        }

        bookModel.performActionBasedOnUserRole();
    }
}
