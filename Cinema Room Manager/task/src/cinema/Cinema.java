package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        scanner.close();
        System.out.println();

        new CinemaHall(rows, seatsPerRow);

        while (CinemaHall.open) {
            CinemaHall.mainMenu();
        }
    }
}

class CinemaHall {

    public static boolean open = false;
    public static int rows = 0;
    public static int seatsPerRow = 0;
    public static int seats = 0;
    public static int occupiedSeats = 0;
    public static double occupiedPercentage = 0;
    public static int currentIncome = 0;
    public static int totalIncome = 0;
    public static char[][] hall;

    public CinemaHall(int rows, int seatsPerRow) {
        open = true;
        CinemaHall.rows = rows;
        CinemaHall.seatsPerRow = seatsPerRow;
        seats = rows * seatsPerRow;
        occupiedSeats = 0;
        occupiedPercentage = 0;
        currentIncome = 0;
        totalIncome = calculateTotalIncome();
        hall = createHall(rows, seatsPerRow);
    }

    public static char[][] createHall(int rows, int seatsPerRow) {
        char[][] hall = new char[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                hall[i][j] = 'S';
            }
        }
        return hall;
    }

    public static void mainMenu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch(choice) {
            case 0:
                closeTheCinema();
                break;
            case 1:
                printHall();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                showStatistics();
                break;
            default:
                break;
        }
    }

    public static void printHall() {
        System.out.print("Cinema:\n  ");
        for (int i = 1; i <= hall[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < hall.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < hall[i].length; j++) {
                System.out.print(hall[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket() {
        int rowNumber;
        int seatNumber;
        boolean availableSeat = false;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            scanner.close();

            if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seatsPerRow) {
                System.out.println("Wrong input!\n");
            } else if (hall[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!\n");
            } else {
                hall[rowNumber - 1][seatNumber - 1] = 'B';
                occupiedSeats++;
                occupiedPercentage = (double) occupiedSeats / (double) seats;
                int ticketPrice = calculateTicketPrice(rowNumber);
                currentIncome += ticketPrice;
                System.out.println("Ticket price: $" + ticketPrice);
                System.out.println();
                availableSeat = true;
            }
        } while (!availableSeat);
    }

    public static int calculateTicketPrice(int rowNumber) {
        if (seats <= 60) {
            return 10;
        }
        return rowNumber <= rows / 2 ? 10 : 8;
    }

    public static void showStatistics() {
        System.out.printf("Number of purchased tickets: %d\n" +
                        "Percentage: %.2f%%\n" +
                        "Current income: $%d\n" +
                        "Total income: $%d\n",
                occupiedSeats,
                occupiedPercentage * 100,
                currentIncome,
                totalIncome);
    }

    public static int calculateTotalIncome() {
        if(seats <= 60) {
            return seats * 10;
        } else {
            return rows / 2 * seatsPerRow *10 + (rows - rows / 2) * seatsPerRow * 8;
        }
    }

    public static void closeTheCinema() {
        open = false;
    }
}