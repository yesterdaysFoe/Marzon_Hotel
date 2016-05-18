package utils;

import connection.DbConf;
import controllers.DiscountsJpaController;
import controllers.ProductsAndServicesJpaController;
import controllers.TransactionPaymentsJpaController;
import controllers.TransactionRoomsJpaController;
import controllers.TransactionsJpaController;
import controllers.ReservationJpaController;
import controllers.RoomJpaController;
import controllers.RoomStatusJpaController;
import controllers.RoomsJpaController;
import controllers.TransactionDiscountsJpaController;
import controllers.TransactionProductsJpaController;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Initializer {

    public static final DecimalFormat df = new DecimalFormat("#,##0.00");
    public static final DecimalFormat PESO_DF = new DecimalFormat("₱ #,##0.00");
    public static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    private static ReservationJpaController reservationJpaController;
    private static RoomJpaController roomJpaController;
    private static RoomsJpaController roomsJpaController;
    private static ProductsAndServicesJpaController productsAndServicesJpaController;
    private static TransactionsJpaController transactionsJpaController;
    private static TransactionRoomsJpaController transactionRoomsJpaController;
    private static TransactionPaymentsJpaController transactionPaymentsJpaController;
    private static RoomStatusJpaController roomStatusJpaController;
    private static DiscountsJpaController discountsJpaController;
    private static TransactionProductsJpaController transactionProductsJpaController;
    private static TransactionDiscountsJpaController transactionDiscountsJpaController;
    
    public static int safeLongToInt(long l) {
    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
        throw new IllegalArgumentException
            (l + " cannot be cast to int without changing its value.");
    }
    return (int) l;
}

    public static ReservationJpaController getReservationJpaController() {
        if (reservationJpaController == null) {
            reservationJpaController = new ReservationJpaController(DbConf.getInstance());
        }
        return reservationJpaController;
    }

    public static RoomJpaController getRoomJpaController() {
        if (roomJpaController == null) {
            roomJpaController = new RoomJpaController(DbConf.getInstance());
        }
        return roomJpaController;
    }
    public static RoomsJpaController getRoomsJpaController() {
        if (roomsJpaController == null) {
            roomsJpaController = new RoomsJpaController(DbConf.getInstance());
        }
        return roomsJpaController;
    }

    public static ProductsAndServicesJpaController getProductsAndServicesJpaController() {
        if (productsAndServicesJpaController == null) {
            productsAndServicesJpaController = new ProductsAndServicesJpaController(DbConf.getInstance());
        }
        return productsAndServicesJpaController;
    }

    public static TransactionsJpaController getTransactionsJpaController() {
        if (transactionsJpaController == null) {
            transactionsJpaController = new TransactionsJpaController(DbConf.getInstance());
        }
        return transactionsJpaController;
    }

    public static TransactionRoomsJpaController getTransactionRoomsJpaController() {
        if (transactionRoomsJpaController == null) {
            transactionRoomsJpaController = new TransactionRoomsJpaController(DbConf.getInstance());
        }
        return transactionRoomsJpaController;
    }

    public static TransactionPaymentsJpaController getTransactionPaymentsJpaController() {
        if (transactionPaymentsJpaController == null) {
            transactionPaymentsJpaController = new TransactionPaymentsJpaController(DbConf.getInstance());
        }
        return transactionPaymentsJpaController;
    }

    public static RoomStatusJpaController getRoomStatusJpaController() {
        if (roomStatusJpaController == null) {
            roomStatusJpaController = new RoomStatusJpaController(DbConf.getInstance());
        }
        return roomStatusJpaController;
    }

    public static DiscountsJpaController getDiscountsJpaController() {
        if (discountsJpaController == null) {
            discountsJpaController = new DiscountsJpaController(DbConf.getInstance());
        }
        return discountsJpaController;
    }

    public static TransactionProductsJpaController getTransactionProductsJpaController() {
        if (transactionProductsJpaController == null) {
            transactionProductsJpaController = new TransactionProductsJpaController(DbConf.getInstance());
        }
        return transactionProductsJpaController;
    }

    public static TransactionDiscountsJpaController getTransactionDiscountsJpaController() {
        if (transactionDiscountsJpaController == null) {
            transactionDiscountsJpaController = new TransactionDiscountsJpaController(DbConf.getInstance());
        }
        return transactionDiscountsJpaController;
    }

}
