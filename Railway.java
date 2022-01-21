package com.sakthipraba;
import java.util.*;

public class OnlineRailwaySystem {
    static Scanner sc = new Scanner(System.in);
    static String Admin_Id = "Admin";
    static String Admin_Password = "1234";
    static int Admin_Attempt = 3;

    static int User_Id = 1, Current_User = -1;

    static ArrayList<Booking_S> Booking = new ArrayList<>();
    static ArrayList<Waiting_S> Waiting = new ArrayList<>();

    static int station[][] = new int[5][10];

    static String place[] = { "Palce - 1", "Place - 2", "Place - 3", "Place - 4", "Place - 5", "Place - 6", "Place - 7" };

    static int seat[][] = new int[10][7];

    static int waitseat[][] = new int[3][7];
    static int seatid = 1;
    static int bookingid = 1001;

    // ArrayList of object
    // For User
    static ArrayList<Creat_User_Obj> User_List = new ArrayList<>();

    // Clear Screen
    static void Clr_scr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void Admin_Login() {
        while (true) {
            if (Admin_Attempt >= 0) {
                System.out.print("Welcome to Admin Login\n\nEnter the Admin Id : ");
                String id = sc.next();
                sc.nextLine();
                System.out.print("Enter the Admin Password : ");
                String pass = sc.next();
                sc.nextLine();
                if (id.equals(Admin_Id) && pass.equals(Admin_Password)) {
                    Admin_Dashboard();
                } else {
                    Clr_scr();
                    Admin_Attempt -= 1;
                    System.out.println("Id and Password missmatch Please try again ! You have only " + Admin_Attempt
                            + " Attempt's");
                }
            } else {
                Clr_scr();
                System.out.println("Your Account Has Been Locked :-)");
                main(null);
            }
        }
    }

    static void Admin_Dashboard() {
        while (true) {
            System.out.println("Welcome to the Admin Dashboard ! ");
            System.out.println("1 => Add Number of Station & Seat\n2 => View Seat Availability\n3 => Back");
            int ch = sc.nextInt();
            if (ch == 1) {
            } else if (ch == 2) {
                View_Availability();
            } else if (ch == 3) {
                main(null);
            } else {
                System.out.println("Enter the valid Chooise !");
            }
        }
    }

    static void View_Availability() {
    }

    static void User_Panel() {
        while (true) {
            System.out.println("Welcome to User Panel : \n1 => Login \n2 => Register \n3 => Back");
            int ch = sc.nextInt();
            if (ch == 1) {
                User_Login();
            } else if (ch == 2) {
                User_Register();
            } else if (ch == 3) {
                main(null);
            } else {
                System.out.println("Enter the valid Number !");
            }
        }
    }

    static void User_Login() {
        while (true) {
            int k = 1;
            System.out.print("Welcome to User Login \n\nEnter your User Id : ");
            String id = sc.next();
            sc.nextLine();
            System.out.print("Enter Your Password : ");
            String password = sc.next();
            sc.nextLine();
            for (int i = 0; i < User_List.size(); i++) {
                if (Integer.toString(User_List.get(i).U_Id).equals(id)
                        && User_List.get(i).U_Password.equals(password)) {
                    k = 0;
                    Current_User = i;
                    User_Dashboard();
                }
            }
            if (k == 1) {
                System.out.println("Inavlid email or password !");
                User_Panel();
            }
        }
    }

    static void User_Register() {
        while (true) {
            System.out.print("Welcome to user Regestration \nEnter your name : ");
            String name = sc.next();
            sc.nextLine();
            System.out.print("Enter your Password : ");
            String pass = sc.next();
            sc.nextLine();
            User_List.add(new Creat_User_Obj(name, pass, User_Id));
            System.out.println("Your User Id is ==> " + User_Id);
            User_Id += 1;
            User_Login();
        }
    }

    static void User_Dashboard() {
        while (true) {
            System.out.println("Welcome To user Dashboard !");
            System.out.println("1 => Book Ticket\n2 => Delete Ticket\n3 => History\n4 => Back");
            int ch = sc.nextInt();
            if (ch == 1) {
                Ticket_Booking();
            } else if (ch == 2) {
                Ticket_Canceling();
            } else if (ch == 3) {
                History();
            } else if (ch == 4) {
                main(null);
            } else {
                System.out.println("Enter the Valid Number !");
            }
        }
    }

    static void Ticket_Booking() {
        ab: while (true) {
            int seat1 = -1;
            System.out.println("\tWellcome " + User_List.get(Current_User).U_Name);
            System.out.println(
                    "1.Palce - 1\n2.Place - 2\n3.Place - 3\n4.Place - 4\n5.Place - 5\n6.Place - 6\n7.Place - 7\n8.Exit");
            int start;
            while (true) {
                System.out.println("Enter Starting Point");
                start = sc.nextInt();
                if (start > 0 && start < 8) {
                    break;
                } else if (start == 8) {
                    break ab;
                } else {
                    System.out.println("Invalid Starting Ponit...");
                }
            }
            int landing;
            while (true) {
                System.out.println("Enter Landing Point");
                landing = sc.nextInt();
                if (landing < 8 && landing > start) {
                    break;
                } else if (landing == 8) {
                    break ab;
                } else {
                    System.out.println("Invalid Landing Ponit...");
                }
            }
            int count = 0;
            int check = 0;
            int one = 0;
            int seat2 = 0;
            a: while (one == 0) {
                one++;
                for (int j = 0; j < 10; j++) {
                    count = 0;
                    for (int t = 0; t < 7; t++) {
                        if (t >= start - 1 && t < landing) {
                            count += seat[j][t];
                            check = t;
                        }
                    }
                    for (int t = start - 1; t < check; t++) {
                        if (count == 0) {
                            seat[j][t] = seatid;
                            seat1 = j;
                            seat2 = 0;
                        }

                    }
                    if (count == 0) {

                        break a;
                    }

                }

                for (int j = 0; j < 3; j++) {
                    count = 0;
                    for (int t = 0; t < 7; t++) {
                        if (t >= start - 1 && t < landing) {
                            count += waitseat[j][t];
                            check = t;
                        }
                    }
                    for (int t = start - 1; t < check; t++) {
                        if (count == 0) {
                            waitseat[j][t] = seatid;
                            seat1 = j;
                            seat2 = 1;
                        }

                    }
                    if (count == 0) {

                        break a;
                    }

                }
            }
            seatid++;
            if (seat1 != -1 && seat2 == 0) {
                Booking.add(new Booking_S(User_List.get(Current_User).U_Id, (seat1 + 1), bookingid, place[start - 1],
                        place[landing - 1], start, landing, "Seat Alloted"));
                System.out.println("Seat Alloted :" + (seat1 + 1));
                System.out.println("Booking id :" + bookingid);
                bookingid++;
            } else if (seat1 != -1 && seat2 == 1) {
                Waiting.add(new Waiting_S(User_List.get(Current_User).U_Id, (seat1 + 1), bookingid, place[start - 1],
                        place[landing - 1], start, landing, "Seat Waitinglist"));
                System.out.println("WaitSeat Alloted :" + (seat1 + 1));
                System.out.println("Booking id :" + bookingid);

                bookingid++;
            } else {
                System.out.println("Seat Unavalable");
            }
        }
    }

    static void Ticket_Canceling() {

        System.out.println("\tWelcome" + User_List.get(Current_User).U_Name+" !");
        System.out.println("Enter Booking ID:");
        int check = sc.nextInt();
        int count = 0;
        int check1 = 0;
        int st = 0;
        int seat1 = -1;
        for (int j = 0; j < Booking.size(); j++) {
            if (check == Booking.get(j).Booking_Id_List) {
                b: for (int y = Booking.size() - 1; y < 10;) {
                    for (int t = 0; t < 7; t++) {
                        if (t >= Booking.get(j).Starting_Id - 1 && t < Booking.get(j).Landing_Id - 1) {
                            seat[y][t] = 0;
                        }
                    }
                    break b;
                }
                Booking.remove(j);
                // allotedseat.remove(j);
                int ws = 0, wl = 0;
                a: for (int g = 0; g < Waiting.size(); g++) {
                    for (int k = 0; k < 10; k++) {
                        count = 0;
                        for (int t = 0; t < 7; t++) {
                            if (t >= Waiting.get(g).Starting_Id - 1 && t <= Waiting.get(g).Landing_Id - 1) {
                                count += seat[k][t];
                                check1 = t;
                            }
                        }
                        for (int t = Waiting.get(g).Starting_Id - 1; t < check1; t++) {
                            if (count == 0) {

                                seat[k][t] = seatid;
                                ws = Waiting.get(g).Starting_Id - 1;
                                wl = Waiting.get(g).Landing_Id - 1;
                                seat1 = j;
                                st = g;
                            }

                        }
                        if (count == 0) {

                            break a;
                        }

                    }
                }
                if (seat1 != -1) {
                    Booking.add(new Booking_S(User_List.get(Current_User).U_Id, (seat1+1), Waiting.get(j).Booking_Id_List, Waiting.get(j).Starting_Point, Waiting.get(j).Landing_Point, Waiting.get(j).Starting_Id, Waiting.get(j).Landing_Id, "Seat Alloted"));
                    System.out.println("Seat Alloted :" + (seat1 + 1));
                    System.out.println("Booking id :" + Waiting.get(j).Booking_Id_List);
                    bb: for (int y = st; y < 3;) {
                        for (int t = 0; t < 7; t++) {
                            if (t >= ws && t < wl) {
                                waitseat[y][t] = 0;

                            }
                        }
                        break bb;
                    }
                    Waiting.remove(st);
                }

            }
        }

    }

    static void History() {
        int k = 0;
        for (int i = 0; i <Booking.size(); i++) {
            if (Booking.get(i).User_Id == User_List.get(Current_User).U_Id) {
                System.out.println("Ticket Number : " + (++k));
                System.out.println("Starting Point : " + Booking.get(i).Starting_Point);
                System.out.println("Lnding Point : " + Booking.get(i).Landing_Point);
                System.out.println("Steat Number : " + Booking.get(i).Alloted_Seat);
                System.out.println("Booking Id : " + Booking.get(i).Booking_Id_List);
                System.out.println("Status : " + Booking.get(i).Status);
                System.out.println("--------------------------------------------");
            }
        }
        k = 0;
        for (int i = 0; i <Waiting.size(); i++) {
            if (Waiting.get(i).User_Id == User_List.get(Current_User).U_Id) {
                System.out.println("Ticket Number : " + (++k));
                System.out.println("Starting Point : " + Waiting.get(i).Starting_Point);
                System.out.println("Lnding Point : " + Waiting.get(i).Landing_Point);
                System.out.println("Steat Number : " + Waiting.get(i).Alloted_Seat);
                System.out.println("Waiting Id : " + Waiting.get(i).Booking_Id_List);
                System.out.println("Status : " + Waiting.get(i).Status);
                System.out.println("--------------------------------------------");
            }
        }
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("Welcome To Online Reservation System !");
            System.out.println("1 => Admin\n2 => User\n3 => Exit");
            int Ch = sc.nextInt();
            if (Ch == 1) {
                Admin_Login();
            } else if (Ch == 2) {
                User_Panel();
            } else if (Ch == 3) {
                System.exit(0);
            } else {
                System.out.println("Enter the valid Number !");
            }
        }

    }
}

class Creat_User_Obj {

    String U_Name, U_Password;
    int U_Id;
    List<String> List = new ArrayList<>();

    Creat_User_Obj(String name, String password, int id) {
        this.U_Name = name;
        this.U_Password = password;
        this.U_Id = id;
    }
}

class Booking_S {

    public int User_Id;
    public int Alloted_Seat;
    public int Booking_Id_List;
    public String Starting_Point;
    public String Landing_Point;
    public int Starting_Id;
    public int Landing_Id;
    public String Status;

    public Booking_S(int User_Id, int Alloted_Seat, int Booked_Id, String StartPt, String EndPt, int St_Id, int Ld_Id,
            String Status) {
        this.User_Id = User_Id;
        this.Alloted_Seat = Alloted_Seat;
        this.Booking_Id_List = Booked_Id;
        this.Starting_Point = StartPt;
        this.Landing_Point = EndPt;
        this.Starting_Id = St_Id;
        this.Landing_Id = Ld_Id;
        this.Status = Status;
    }
}

class Waiting_S {
    public int User_Id;
    public int Alloted_Seat;
    public int Booking_Id_List;
    public String Starting_Point;
    public String Landing_Point;
    public int Starting_Id;
    public int Landing_Id;
    public String Status;

    public Waiting_S(int User_Id, int Alloted_Seat, int Booked_Id, String StartPt, String EndPt, int St_Id, int Ld_Id,
            String Status) {
        this.User_Id = User_Id;
        this.Alloted_Seat = Alloted_Seat;
        this.Booking_Id_List = Booked_Id;
        this.Starting_Point = StartPt;
        this.Landing_Point = EndPt;
        this.Starting_Id = St_Id;
        this.Landing_Id = Ld_Id;
        this.Status = Status;
    }
}
