import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Book {
    String title;
    String author;
    boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}

class User {
    String name;
    ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }
}

class Library {
    ArrayList<Book> books;
    ArrayList<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
        JOptionPane.showMessageDialog(null, "Book added successfully.");
    }

    public void addUser(String name) {
        User user = new User(name);
        users.add(user);
        JOptionPane.showMessageDialog(null, "User added successfully.");
    }

    public void borrowBook(String userName, String bookTitle) {
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user != null && book != null) {
            if (book.isAvailable) {
                book.isAvailable = false;
                user.borrowedBooks.add(book);
                JOptionPane.showMessageDialog(null, userName + " has successfully borrowed " + bookTitle + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, " + bookTitle + " is not available for borrowing.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "User or book not found.");
        }
    }

    public void returnBook(String userName, String bookTitle) {
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user != null && book != null) {
            if (user.borrowedBooks.contains(book)) {
                book.isAvailable = true;
                user.borrowedBooks.remove(book);
                JOptionPane.showMessageDialog(null, userName + " has successfully returned " + bookTitle + ".");
            } else {
                JOptionPane.showMessageDialog(null, "This book was not borrowed by " + userName + ".");
            }
        } else {
            JOptionPane.showMessageDialog(null, "User or book not found.");
        }
    }

    private User findUser(String userName) {
        for (User user : users) {
            if (user.name.equals(userName)) {
                return user;
            }
        }
        return null;
    }

    private Book findBook(String bookTitle) {
        for (Book book : books) {
            if (book.title.equals(bookTitle)) {
                return book;
            }
        }
        return null;
    }
}

public class p1{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Library library = new Library();
            createAndShowGUI(library);
        });
    }

    private static void createAndShowGUI(Library library) {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JButton addBookButton = new JButton("Add Book");
        JButton addUserButton = new JButton("Add User");
        JButton borrowBookButton = new JButton("Borrow Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton exitButton = new JButton("Exit");

        addBookButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter book title:");
            String author = JOptionPane.showInputDialog("Enter book author:");
            library.addBook(title, author);
        });

        addUserButton.addActionListener(e -> {
            String userName = JOptionPane.showInputDialog("Enter user name:");
            library.addUser(userName);
        });

        borrowBookButton.addActionListener(e -> {
            String borrowerName = JOptionPane.showInputDialog("Enter user name:");
            String bookToBorrow = JOptionPane.showInputDialog("Enter book title to borrow:");
            library.borrowBook(borrowerName, bookToBorrow);
        });

        returnBookButton.addActionListener(e -> {
            String returnerName = JOptionPane.showInputDialog("Enter user name:");
            String bookToReturn = JOptionPane.showInputDialog("Enter book title to return:");
            library.returnBook(returnerName, bookToReturn);
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Exiting Library Management System. Goodbye!");
            System.exit(0);
        });

        frame.add(addBookButton);
        frame.add(addUserButton);
        frame.add(borrowBookButton);
        frame.add(returnBookButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }
}