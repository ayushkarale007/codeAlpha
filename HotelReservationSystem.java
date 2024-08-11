import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Booking {
    private int bookingId;
    private Room room;
    private String guestName;
    private double payment;

    public Booking(int bookingId, Room room, String guestName, double payment) {
        this.bookingId = bookingId;
        this.room = room;
        this.guestName = guestName;
        this.payment = payment;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public double getPayment() {
        return payment;
    }
}

public class HotelReservationSystem {
    private List<Room> rooms;
    private List<Booking> bookings;
    private int bookingIdCounter;

    public HotelReservationSystem() {
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.bookingIdCounter = 1;

        // Initialize rooms
        rooms.add(new Room(1, "Single"));
        rooms.add(new Room(2, "Double"));
        rooms.add(new Room(3, "Suite"));
        rooms.add(new Room(4, "Single"));
        rooms.add(new Room(5, "Double"));
        rooms.add(new Room(6, "Suite"));
    }

    public void searchRooms(String category) {
        System.out.println("Available rooms in " + category + " category:");
        for (Room room : rooms) {
            if (room.getCategory().equals(category) && room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber());
            }
        }
    }

    public void makeReservation(String category, String guestName, double payment) {
        Room room = null;
        for (Room r : rooms) {
            if (r.getCategory().equals(category) && r.isAvailable()) {
                room = r;
                break;
            }
        }
        if (room != null) {
            room.setAvailable(false);
            Booking booking = new Booking(bookingIdCounter++, room, guestName, payment);
            bookings.add(booking);
            System.out.println("Reservation successful. Booking ID: " + booking.getBookingId());
        } else {
            System.out.println("No rooms available in " + category + " category.");
        }
    }

    public void viewBookingDetails(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Room Number: " + booking.getRoom().getRoomNumber());
                System.out.println("Guest Name: " + booking.getGuestName());
                System.out.println("Payment: " + booking.getPayment());
                return;
            }
        }
        System.out.println("No booking found with ID " + bookingId);
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search rooms");
            System.out.println("2. Make reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter room category: ");
                    String category = scanner.next();
                    system.searchRooms(category);
                    break;
                case 2:
                    System.out.print("Enter room category: ");
                    category = scanner.next();
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.next();
                    System.out.print("Enter payment: ");
                    double payment = scanner.nextDouble();
                    system.makeReservation(category, guestName, payment);
                    break;
                case 3:
                    System.out.print("Enter booking ID: ");
                    int bookingId = scanner.nextInt();
                    system.viewBookingDetails(bookingId);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}