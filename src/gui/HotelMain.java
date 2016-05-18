package gui;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import connection.DbConf;
import controllers.RoomsJpaController;
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entities.Discounts;
import entities.ProductsAndServices;
import entities.TransactionPayments;
import entities.TransactionProducts;
import entities.TransactionRooms;
import entities.Transactions;
import entities.Reservation;
import entities.Room;
import entities.RoomStatus;
import entities.Rooms;
import entities.TransactionDiscounts;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import org.jdesktop.observablecollections.ObservableCollections;
import utils.CalendarUtil;
import utils.Hotel;
import utils.Initializer;
import utils.Messages;
import utils.PrintUtil;
import utils.ProductEnum;
import utils.SearchTable;
import utils.Status;

public class HotelMain extends javax.swing.JFrame {

    private static HotelMain instance;
    private final List<ProductsAndServices> productsAndServices;
    private ProductsAndServices currentPaS;
    private final List<Transactions> transactions;
    private final DecimalFormat df = new DecimalFormat("#,##0.00");
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final List<TransactionProducts> transactionProducts;
    private final List<TransactionPayments> transactionPayments;
    private final List<TransactionRooms> transactionRooms;
    private final List<TransactionPayments> transactionPaymentsreports;
    private List<TransactionDiscounts> transactionDiscounts;
    private Discounts currentDiscount;
    private List<Discounts> discounts;

    private List<TransactionProducts> transactionProductsTemp;
    private List<TransactionPayments> transactionPaymentsTemp;
    private List<TransactionRooms> transactionRoomsTemp;

    private final List<Reservation> reservations;
    private final List<Room> rooms;
    private Transactions transaction;
    private static final String CONFIRMED = "CONFIRMED";
    private static final String CHECKED_IN = "CHECKED IN";
    private static final String SAVE_ONLY = "SAVE";
    private static final String CHECKEDOUT = "CHECKED OUT";

    private final SettingsPanel settingsPanel;
    private final CalendarPanel calendarPanel;
    private final AllInOnePanel allInOnePanel;
    private List<Rooms> roomsSelection;

    {
        transactions = ObservableCollections.observableList(new ArrayList<Transactions>());
        productsAndServices = ObservableCollections.observableList(new ArrayList<ProductsAndServices>());
        transactionProducts = ObservableCollections.observableList(new ArrayList<TransactionProducts>());
        reservations = ObservableCollections.observableList(new ArrayList<Reservation>());
        rooms = ObservableCollections.observableList(new ArrayList<Room>());
        transactionPayments = ObservableCollections.observableList(new ArrayList<TransactionPayments>());
        transactionRooms = ObservableCollections.observableList(new ArrayList<TransactionRooms>());
        transactionPaymentsreports = ObservableCollections.observableList(new ArrayList<TransactionPayments>());
        roomsSelection = ObservableCollections.observableList(new ArrayList<Rooms>());
        transactionDiscounts = ObservableCollections.observableList(new ArrayList<TransactionDiscounts>());
        discounts = ObservableCollections.observableList(new ArrayList<Discounts>());
    }

    public HotelMain() {
        setLookAndFeel();
        initComponents();
        settingsPanel = new SettingsPanel();
        settingsPanel.getPanel5().add(productsAndServicePanel, "productCard");

        calendarPanel = new CalendarPanel();
        allInOnePanel = new AllInOnePanel();
        jPanel1.add(settingsPanel, "settingsCard");
        jPanel1.add(calendarPanel, "calendarCard");
        mainHomePanel.add(allInOnePanel, "allInOneCard");
        new SearchTable().activate(jTable3, jTextField5, 0, 1, 2, 3, 4);
        new SearchTable().activate(jTable6, jTextField13, 0, 1, 2, 3, 4, 5, 6, 7);
        new SearchTable().activate(jTable9, jTextField6, 0, 1, 2, 3, 4, 5, 6, 7);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.setSelected(jRadioButton3.getModel(), true);

        roomIdLabel.setVisible(false);
        jButton23.setVisible(false);
    }

    public void showMainHomePanel() {
        showCard("mainCard", mainHomePanel);
    }

    private void showCard(String str, JPanel panel) {
        CardLayout cl = (CardLayout) panel.getLayout();
        cl.show(panel, str);
    }

    public static HotelMain getInstance() {
        if (instance == null) {
            instance = new HotelMain();
        }
        return instance;
    }

    public List<TransactionDiscounts> getTransactionDiscounts() {
        return transactionDiscounts;
    }

    public void setTransactionDiscounts(List<TransactionDiscounts> transactionDiscounts) {
        this.transactionDiscounts = transactionDiscounts;
    }

    public List<Discounts> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discounts> discounts) {
        this.discounts = discounts;
    }

    private void setLookAndFeel() {
        try {
            Properties props = new Properties();
            props.put("logoString", "");
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Black", "INSERT YOUR LICENSE KEY HERE", "");
            AcrylLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } //</editor-fold>
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        }
    }

    public List<ProductsAndServices> getProductsAndServices() {
        return productsAndServices;
    }

    public List<TransactionProducts> getTransactionProducts() {
        return transactionProducts;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<TransactionPayments> getTransactionPaymentsreports() {
        return transactionPaymentsreports;
    }

    public List<TransactionPayments> getTransactionPayments() {
        return transactionPayments;
    }

    public ProductsAndServices getCurrentPaS() {
        return currentPaS;
    }

    public void setCurrentPaS(ProductsAndServices currentPaS) {
        this.currentPaS = currentPaS;
    }

    public List<TransactionRooms> getTransactionRooms() {
        return transactionRooms;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public List<Rooms> getRoomsSelection() {
        return roomsSelection;
    }

    private void refreshDiscounts() {
//        List<Discounts> dList = Initializer.getDiscountsJpaController().findAllActiveDiscounts();
//        jComboBox8.removeAllItems();
//        jComboBox8.addItem("Select...");
//        for (Discounts discounts : dList) {
//            jComboBox8.addItem(discounts);
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        checkInDialog = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jTextField23 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        productsTransactionDialog = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jSpinField1 = new com.toedter.components.JSpinField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        productEditorDialog = new javax.swing.JDialog();
        jPanel21 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        paymentsDialog = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        reservationsSelectionDialog = new javax.swing.JDialog();
        jPanel23 = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        guestInfoDialog = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        roomDialog = new javax.swing.JDialog();
        jPanel31 = new javax.swing.JPanel();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        roomIdLabel = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel32 = new javax.swing.JPanel();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        productsAndServicePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        discountsDialog = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jPanel46 = new javax.swing.JPanel();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        discountCommentDialog = new javax.swing.JDialog();
        jPanel47 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        transactionDiscountDialog = new javax.swing.JDialog();
        jPanel49 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jPanel50 = new javax.swing.JPanel();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        paymentDialog = new javax.swing.JDialog();
        jPanel51 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jPanel52 = new javax.swing.JPanel();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        transactionServiceDialog = new javax.swing.JDialog();
        jPanel53 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jPanel54 = new javax.swing.JPanel();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        mainHomePanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton25 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnCalendar = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        posPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        salesTransactionPanel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel42 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        grandTotalTextField = new javax.swing.JTextField();
        discountTextField = new javax.swing.JTextField();
        paymentsTextField = new javax.swing.JTextField();
        balanceTextField = new javax.swing.JTextField();
        hotelBill = new javax.swing.JTextField();
        restaurantBill = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jPanel40 = new javax.swing.JPanel();
        jButton50 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton40 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        incompleteTransactionPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        reservationPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        reportsPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel35 = new javax.swing.JPanel();
        jButton44 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jPanel55 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jPanel56 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jPanel57 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel58 = new javax.swing.JPanel();
        otherPanel = new javax.swing.JPanel();

        checkInDialog.setBounds(new java.awt.Rectangle(0, 0, 400, 450));

        jPanel22.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton17.setText("CHECK IN");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton20.setText("CANCEL");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("RESERVE");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton49.setText("SAVE");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(jButton49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton49))
                .addContainerGap())
        );

        checkInDialog.getContentPane().add(jPanel22, java.awt.BorderLayout.SOUTH);

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Check In Information"));

        jDateChooser3.setDateFormatString("MM/dd/yyyy");
        jDateChooser3.setMinSelectableDate(new Date());
        jDateChooser3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser3PropertyChange(evt);
            }
        });

        jLabel10.setText("Departure Date:");

        jTextField10.setEditable(false);
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jDateChooser4.setDateFormatString("MM/dd/yyyy");
        jDateChooser4.setMinSelectableDate(new Date());
        jDateChooser4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser4PropertyChange(evt);
            }
        });

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${rooms}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Room Type:");

        jLabel9.setText("Arrival Date:");

        jLabel11.setText("Price:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${roomsSelection}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox3);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel46.setText("Room:");

        jLabel35.setText("Down Payment:");

        jLabel49.setText("Total:");

        jTextField31.setEditable(false);

        jLabel53.setText("/day");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, 176, Short.MAX_VALUE)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel49))
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel53)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        checkInDialog.getContentPane().add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Guest Info"));
        jPanel27.setPreferredSize(new java.awt.Dimension(293, 180));

        jLabel31.setText("Address:");

        jLabel34.setText("Contact No.:");

        jLabel32.setText("ZIP:");

        jLabel30.setText("Last Name:");

        jLabel33.setText("Email:");

        jLabel29.setText("First Name:");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                    .addComponent(jTextField21)
                    .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addComponent(jTextField23)
                    .addComponent(jTextField22))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        checkInDialog.getContentPane().add(jPanel27, java.awt.BorderLayout.NORTH);

        productsTransactionDialog.setBounds(new java.awt.Rectangle(0, 0, 405, 300));

        jLabel18.setText("Products/Service Name:");

        jLabel19.setText("Details:");

        jLabel20.setText("Category:");

        jLabel21.setText("Price:");

        jTextField14.setEditable(false);

        jTextField15.setEditable(false);

        jTextField16.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField16CaretUpdate(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextArea2.setRows(2);
        jTextArea2.setOpaque(false);
        jScrollPane9.setViewportView(jTextArea2);

        jSpinField1.setMinimum(0);
        jSpinField1.setValue(1);
        jSpinField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSpinField1FocusLost(evt);
            }
        });
        jSpinField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinField1PropertyChange(evt);
            }
        });

        jLabel22.setText("Qty:");

        jLabel23.setText("Total Price:");

        jTextField17.setEditable(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinField1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        productsTransactionDialog.getContentPane().add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel33.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton27.setText("Cancel");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton26.setText("Done");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(263, Short.MAX_VALUE)
                .addComponent(jButton26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton27)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26)
                    .addComponent(jButton27))
                .addContainerGap())
        );

        productsTransactionDialog.getContentPane().add(jPanel33, java.awt.BorderLayout.SOUTH);

        productEditorDialog.setBounds(new java.awt.Rectangle(0, 0, 405, 385));

        jLabel24.setText("Products/Service Name:");

        jLabel25.setText("Details:");

        jLabel26.setText("Category:");

        jLabel27.setText("Price:");

        jButton30.setText("Save");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setText("Cancel");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(2);
        jTextArea3.setWrapStyleWord(true);
        jScrollPane10.setViewportView(jTextArea3);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Restaurant Menu", "Hotel Service" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jLabel47.setText("Type:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Lunch/Dinner", "Dessert", "Beverage", "Drinks", "Snacks" }));

        jLabel48.setText("Food Category:");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Hot", "Cold", "Beefs", "Porks", "Chickens", "Sea Foods", "Cakes", "Shakes", "Rice" }));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jTextField18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addComponent(jButton30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31)
                .addGap(10, 10, 10))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton30)
                    .addComponent(jButton31))
                .addContainerGap())
        );

        productEditorDialog.getContentPane().add(jPanel21, java.awt.BorderLayout.CENTER);

        paymentsDialog.setBounds(new java.awt.Rectangle(0, 0, 215, 185));

        jButton33.setText("Cancel");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jLabel3.setText("Date:");

        jButton32.setText("Add");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jLabel4.setText("Amount:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jTextField3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton33)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton32)
                    .addComponent(jButton33))
                .addContainerGap())
        );

        paymentsDialog.getContentPane().add(jPanel13, java.awt.BorderLayout.CENTER);

        reservationsSelectionDialog.setBounds(new java.awt.Rectangle(0, 0, 800, 600));

        jPanel23.setPreferredSize(new java.awt.Dimension(360, 50));

        jButton34.setText("Cancel");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setText("Use");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(jButton35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton34)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton34)
                    .addComponent(jButton35))
                .addContainerGap())
        );

        reservationsSelectionDialog.getContentPane().add(jPanel23, java.awt.BorderLayout.SOUTH);

        jPanel24.setLayout(new java.awt.BorderLayout());
        reservationsSelectionDialog.getContentPane().add(jPanel24, java.awt.BorderLayout.CENTER);

        guestInfoDialog.setBounds(new java.awt.Rectangle(0, 0, 435, 265));

        jPanel28.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton38.setText("DONE");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setText("CANCEL");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(jButton38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton39)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton38)
                    .addComponent(jButton39))
                .addContainerGap())
        );

        guestInfoDialog.getContentPane().add(jPanel28, java.awt.BorderLayout.SOUTH);

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Guest Info"));
        jPanel30.setPreferredSize(new java.awt.Dimension(293, 180));

        jLabel38.setText("Address:");

        jLabel39.setText("Contact No.:");

        jLabel40.setText("ZIP:");

        jLabel41.setText("Last Name:");

        jLabel42.setText("Email:");

        jLabel43.setText("First Name:");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12))
                    .addComponent(jTextField28)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField29)
                            .addComponent(jTextField25)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        guestInfoDialog.getContentPane().add(jPanel30, java.awt.BorderLayout.CENTER);

        roomDialog.setBounds(new java.awt.Rectangle(0, 0, 330, 300));

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Check In Information"));

        jDateChooser6.setMinSelectableDate(new Date());
        jDateChooser6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser6PropertyChange(evt);
            }
        });

        jLabel15.setText("Departure Date:");

        jDateChooser7.setMinSelectableDate(new Date());
        jDateChooser7.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser7PropertyChange(evt);
            }
        });

        jLabel16.setText("Room Type:");

        jLabel36.setText("Arrival Date:");

        jLabel37.setText("Price:");

        jTextField4.setEditable(false);

        jLabel45.setText("Room:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${roomsSelection}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roomIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roomIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)))
        );

        roomDialog.getContentPane().add(jPanel31, java.awt.BorderLayout.CENTER);

        jPanel32.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton41.setText("Done");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton42.setText("Cancel");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(jButton41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton42)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton41)
                    .addComponent(jButton42))
                .addContainerGap())
        );

        roomDialog.getContentPane().add(jPanel32, java.awt.BorderLayout.SOUTH);

        productsAndServicePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Products and Services"));
        productsAndServicePanel.setLayout(new java.awt.BorderLayout());

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${productsAndServices}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Product/Service Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${category}"));
        columnBinding.setColumnName("Category");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${details}"));
        columnBinding.setColumnName("Details");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${price}"));
        columnBinding.setColumnName("Price");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);

        productsAndServicePanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(203, 50));

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Edit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        productsAndServicePanel.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jPanel17.setPreferredSize(new java.awt.Dimension(277, 50));

        jButton13.setText("Refresh");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel1.setText("Search:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 623, Short.MAX_VALUE)
                .addComponent(jButton13)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addContainerGap())
        );

        productsAndServicePanel.add(jPanel17, java.awt.BorderLayout.NORTH);

        discountsDialog.setBounds(new java.awt.Rectangle(0, 0, 405, 300));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${discounts}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable11);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${amount}"));
        columnBinding.setColumnName("Amount(%)");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane13.setViewportView(jTable11);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        discountsDialog.getContentPane().add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel46.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton52.setText("Cancel");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton53.setText("Add Selected");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(jButton53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton52)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton53)
                    .addComponent(jButton52))
                .addContainerGap())
        );

        discountsDialog.getContentPane().add(jPanel46, java.awt.BorderLayout.SOUTH);

        discountCommentDialog.setBounds(new java.awt.Rectangle(0, 0, 405, 300));

        jLabel14.setText("Discount Detail(e.g. Card No., Comments and etc):");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 140, Short.MAX_VALUE))
                    .addComponent(jTextField33))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        discountCommentDialog.getContentPane().add(jPanel47, java.awt.BorderLayout.CENTER);

        jPanel48.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton54.setText("Cancel");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jButton55.setText("Done");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addComponent(jButton55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton54)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton55)
                    .addComponent(jButton54))
                .addContainerGap())
        );

        discountCommentDialog.getContentPane().add(jPanel48, java.awt.BorderLayout.SOUTH);

        transactionDiscountDialog.setBounds(new java.awt.Rectangle(0, 0, 405, 300));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${transactionDiscounts}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable12);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type}"));
        columnBinding.setColumnName("Type");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${amount}"));
        columnBinding.setColumnName("Amount");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${comments}"));
        columnBinding.setColumnName("Comments");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane14.setViewportView(jTable12);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        transactionDiscountDialog.getContentPane().add(jPanel49, java.awt.BorderLayout.CENTER);

        jPanel50.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton56.setText("Cancel");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jButton57.setText("Remove Selected");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addContainerGap(209, Short.MAX_VALUE)
                .addComponent(jButton57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton56)
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton57)
                    .addComponent(jButton56))
                .addContainerGap())
        );

        transactionDiscountDialog.getContentPane().add(jPanel50, java.awt.BorderLayout.SOUTH);

        paymentDialog.setBounds(new java.awt.Rectangle(0, 0, 405, 300));

        jLabel50.setText("Amount Tendered:");

        jTextField34.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField34.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField34CaretUpdate(evt);
            }
        });

        jLabel51.setText("Amount Due:");

        jTextField35.setEditable(false);
        jTextField35.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel52.setText("Change:");

        jTextField36.setEditable(false);
        jTextField36.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField35)
                    .addComponent(jTextField34)
                    .addComponent(jTextField36)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel50)
                            .addComponent(jLabel52))
                        .addGap(0, 295, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        paymentDialog.getContentPane().add(jPanel51, java.awt.BorderLayout.CENTER);

        jPanel52.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton58.setText("Cancel");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jButton59.setText("Done");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addComponent(jButton59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton58)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton59)
                    .addComponent(jButton58))
                .addContainerGap())
        );

        paymentDialog.getContentPane().add(jPanel52, java.awt.BorderLayout.SOUTH);

        transactionServiceDialog.setBounds(new java.awt.Rectangle(0, 0, 405, 300));

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Services", "Qty."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTable13);
        if (jTable13.getColumnModel().getColumnCount() > 0) {
            jTable13.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        transactionServiceDialog.getContentPane().add(jPanel53, java.awt.BorderLayout.CENTER);

        jPanel54.setPreferredSize(new java.awt.Dimension(152, 50));

        jButton60.setText("Cancel");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        jButton61.setText("Print");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(jButton61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton60)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton61)
                    .addComponent(jButton60))
                .addContainerGap())
        );

        transactionServiceDialog.getContentPane().add(jPanel54, java.awt.BorderLayout.SOUTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainHomePanel.setBackground(new java.awt.Color(153, 153, 255));
        mainHomePanel.setLayout(new java.awt.CardLayout());

        mainPanel.setBackground(new java.awt.Color(153, 153, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(80, 150));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Untitled-2.png"))); // NOI18N
        jButton25.setToolTipText("Home");
        jButton25.setFocusable(false);
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton25);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reservebutton.png"))); // NOI18N
        jButton1.setToolTipText("Reservations");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/posbutton.png"))); // NOI18N
        jButton3.setToolTipText("Point of Sale");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reportbutton.png"))); // NOI18N
        jButton2.setToolTipText("Report");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        btnCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendarbutton.png"))); // NOI18N
        btnCalendar.setToolTipText("Calendar");
        btnCalendar.setFocusable(false);
        btnCalendar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalendar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCalendar);

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settingsbutton.png"))); // NOI18N
        btnSettings.setToolTipText("Settings");
        btnSettings.setFocusable(false);
        btnSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSettings);

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exitbutton.png"))); // NOI18N
        btnLogout.setToolTipText("Logout");
        btnLogout.setFocusable(false);
        btnLogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLogout);

        jPanel2.add(jToolBar1, java.awt.BorderLayout.CENTER);

        jPanel20.setPreferredSize(new java.awt.Dimension(10, 859));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel20, java.awt.BorderLayout.EAST);

        mainPanel.add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(153, 153, 255));
        homePanel.setLayout(null);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MarzonLogo.png"))); // NOI18N
        homePanel.add(jLabel28);
        jLabel28.setBounds(160, 230, 820, 280);

        jPanel1.add(homePanel, "homeCard");

        posPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("POS"));
        posPanel.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.CardLayout());

        salesTransactionPanel.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(550, 719));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel44.setPreferredSize(new java.awt.Dimension(520, 150));
        jPanel44.setLayout(new java.awt.BorderLayout());

        jButton29.setText("Remove Payment");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton28.setText("Add Payment");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton43.setText("Remove Rooms");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton36.setText("Remove Products/Services");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton36, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton43)
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton28)
                    .addComponent(jButton29)
                    .addComponent(jButton36)
                    .addComponent(jButton43))
                .addContainerGap())
        );

        jPanel44.add(jPanel43, java.awt.BorderLayout.SOUTH);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Payments"));

        jTable7.getTableHeader().setReorderingAllowed(false);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${transactionPayments}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable7);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dateOfPaymentStr}"));
        columnBinding.setColumnName("Date of Payment");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${amountStr}"));
        columnBinding.setColumnName("Amount");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane8.setViewportView(jTable7);

        jPanel44.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel44, java.awt.BorderLayout.SOUTH);

        jPanel45.setLayout(new java.awt.BorderLayout());

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder("Rooms"));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(462, 100));

        jTable8.getTableHeader().setReorderingAllowed(false);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${transactionRooms}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable8);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${room.roomTypeName}"));
        columnBinding.setColumnName("Room Type");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${room.name}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dateArrivalStr}"));
        columnBinding.setColumnName("Arrival Date");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dateDepartureStr}"));
        columnBinding.setColumnName("Departure Date");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numberOfDates}"));
        columnBinding.setColumnName("Days");
        columnBinding.setColumnClass(Long.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${qty}"));
        columnBinding.setColumnName("Qty.");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${price}"));
        columnBinding.setColumnName("Price");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalPrice}"));
        columnBinding.setColumnName("Total Price");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane7.setViewportView(jTable8);

        jPanel45.add(jScrollPane7, java.awt.BorderLayout.NORTH);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Products and Services"));

        jTable2.getTableHeader().setReorderingAllowed(false);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${transactionProducts}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable2);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${qty}"));
        columnBinding.setColumnName("QTY");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${productAndServicesId.name}"));
        columnBinding.setColumnName("Product/Sevice Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${priceStr}"));
        columnBinding.setColumnName("PRICE");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalPriceStr}"));
        columnBinding.setColumnName("TOTAL PRICE");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jPanel45.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel45, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Info"));
        jPanel10.setPreferredSize(new java.awt.Dimension(550, 50));

        jLabel2.setText("Name:");

        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel42.setLayout(new java.awt.BorderLayout());

        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        grandTotalTextField.setEditable(false);
        grandTotalTextField.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        grandTotalTextField.setForeground(new java.awt.Color(0, 153, 0));
        grandTotalTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        grandTotalTextField.setText("0");
        grandTotalTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GRAND TOTAL", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP));
        grandTotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grandTotalTextFieldActionPerformed(evt);
            }
        });

        discountTextField.setEditable(false);
        discountTextField.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        discountTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        discountTextField.setText("0");
        discountTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DISCOUNT", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP));
        discountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountTextFieldActionPerformed(evt);
            }
        });

        paymentsTextField.setEditable(false);
        paymentsTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        paymentsTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        paymentsTextField.setText("0");
        paymentsTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL DOWNPAYMENTS", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP));
        paymentsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentsTextFieldActionPerformed(evt);
            }
        });

        balanceTextField.setEditable(false);
        balanceTextField.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        balanceTextField.setForeground(new java.awt.Color(255, 0, 0));
        balanceTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        balanceTextField.setText("0");
        balanceTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BALANCE", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP));
        balanceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceTextFieldActionPerformed(evt);
            }
        });

        hotelBill.setEditable(false);
        hotelBill.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hotelBill.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        hotelBill.setText("0");
        hotelBill.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL HOTEL BILL", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP));
        hotelBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotelBillActionPerformed(evt);
            }
        });

        restaurantBill.setEditable(false);
        restaurantBill.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        restaurantBill.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        restaurantBill.setText("0");
        restaurantBill.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL RESTAURANT BILL", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP));
        restaurantBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restaurantBillActionPerformed(evt);
            }
        });

        jTextField19.setEditable(false);
        jTextField19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField19.setText("0");
        jTextField19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "12% VAT", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.ABOVE_TOP));
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restaurantBill, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hotelBill, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grandTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentsTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(balanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(discountTextField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(restaurantBill, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hotelBill, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grandTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(balanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel42.add(jPanel41, java.awt.BorderLayout.CENTER);

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton50.setText("View Discounts");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton10.setText("Save");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton45.setText("Check-out");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton37.setText("Reset");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton51.setText("Add Discount");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton50)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel42.add(jPanel40, java.awt.BorderLayout.SOUTH);

        jPanel9.add(jPanel42, java.awt.BorderLayout.PAGE_END);

        salesTransactionPanel.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Products and Services"));

        jTable3.getTableHeader().setReorderingAllowed(false);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${productsAndServices}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable3);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${category}"));
        columnBinding.setColumnName("Category");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Products/Service Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${details}"));
        columnBinding.setColumnName("Details");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${price}"));
        columnBinding.setColumnName("Price");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jLabel5.setText("Search:");

        jButton14.setText("Refresh");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Products and Services", jPanel12);

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Rooms"));

        jTable9.getTableHeader().setReorderingAllowed(false);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${rooms}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable9);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type}"));
        columnBinding.setColumnName("Type");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${rate}"));
        columnBinding.setColumnName("Room Price");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jLabel6.setText("Search:");

        jButton40.setText("Refresh");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jButton40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Rooms", jPanel29);

        jPanel14.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setPreferredSize(new java.awt.Dimension(0, 50));

        jButton5.setText("New Transaction");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton11.setText("Open Transaction");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton23.setText("Browse From Reservations");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton11)
                    .addComponent(jButton23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel6, java.awt.BorderLayout.NORTH);

        salesTransactionPanel.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel7.add(salesTransactionPanel, "transactionCard");

        incompleteTransactionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Incomplete Transactions"));
        incompleteTransactionPanel.setLayout(new java.awt.BorderLayout());

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${transactions}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable4);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Account");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Customer Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${customerTelNo}"));
        columnBinding.setColumnName("Contact No.");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${customerEmail}"));
        columnBinding.setColumnName("Email");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${address}"));
        columnBinding.setColumnName("Address");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalPriceStr}"));
        columnBinding.setColumnName("Total Price");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane4.setViewportView(jTable4);

        incompleteTransactionPanel.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel18.setPreferredSize(new java.awt.Dimension(277, 50));

        jButton24.setText("Refresh");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel7.setText("Search:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 687, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24))
                .addContainerGap())
        );

        incompleteTransactionPanel.add(jPanel18, java.awt.BorderLayout.NORTH);

        jPanel19.setPreferredSize(new java.awt.Dimension(81, 50));

        jButton12.setText("Open");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton12)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton12)
                .addContainerGap())
        );

        incompleteTransactionPanel.add(jPanel19, java.awt.BorderLayout.SOUTH);

        jPanel7.add(incompleteTransactionPanel, "accountTransactionCard");

        posPanel.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel1.add(posPanel, "mainPosCard");

        reservationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservations"));
        reservationPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(331, 50));

        jButton16.setText("Check In");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton19.setText("Send Email");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton18.setText("Confirm Reservation");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton47.setText("Delete");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setText("Edit");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton48)
                .addContainerGap(529, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16)
                    .addComponent(jButton18)
                    .addComponent(jButton19)
                    .addComponent(jButton47)
                    .addComponent(jButton48))
                .addContainerGap())
        );

        reservationPanel.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel25.setLayout(new java.awt.BorderLayout());

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${reservations}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable6);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fullName}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${email}"));
        columnBinding.setColumnName("Email");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${contact}"));
        columnBinding.setColumnName("Tel. No.");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${province}"));
        columnBinding.setColumnName("Province");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${zip}"));
        columnBinding.setColumnName("Zip");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${roomName}"));
        columnBinding.setColumnName("Room");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${arrival}"));
        columnBinding.setColumnName("Date of Arrival");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${departure}"));
        columnBinding.setColumnName("Date of Departure");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${roomNameNumber}"));
        columnBinding.setColumnName("Room Name/Number");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${confirmation}"));
        columnBinding.setColumnName("Confirmation Code");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${status}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane6.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        jPanel25.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(333, 70));

        jLabel17.setText("Search:");

        jButton15.setText("Refresh");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("CHECKED IN");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("CONFIRMED");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("PENDING");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 636, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25.add(jPanel4, java.awt.BorderLayout.NORTH);

        reservationPanel.add(jPanel25, java.awt.BorderLayout.CENTER);

        jPanel1.add(reservationPanel, "reservationCard");

        reportsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Reports"));
        reportsPanel.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));
        jPanel8.setPreferredSize(new java.awt.Dimension(461, 50));

        jDateChooser2.setDateFormatString("MM/dd/yyyy");

        jDateChooser1.setDateFormatString("MM/dd/yyyy");

        jLabel12.setText("Start Date:");

        jLabel13.setText("End Date:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(600, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDateChooser1, jDateChooser2});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportsPanel.add(jPanel8, java.awt.BorderLayout.NORTH);

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jPanel34.setLayout(new java.awt.BorderLayout());

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room No.", "Customer Name", "No. of Days Stayed", "Restaurant Bill", "Hotel Bill", "Total Bill"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTable5);

        jPanel34.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel35.setPreferredSize(new java.awt.Dimension(461, 50));

        jButton44.setText("Generate");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton44)
                .addContainerGap(878, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton44)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel34.add(jPanel35, java.awt.BorderLayout.NORTH);

        jPanel36.setPreferredSize(new java.awt.Dimension(461, 50));

        jLabel44.setText("TOTAL:");

        jTextField30.setEditable(false);
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel56.setText("TOTAL RESTAURANT BILL:");

        jTextField37.setEditable(false);
        jTextField37.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel57.setText("TOTAL HOTEL BILL:");

        jTextField40.setEditable(false);
        jTextField40.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel57)
                        .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel34.add(jPanel36, java.awt.BorderLayout.SOUTH);

        jTabbedPane2.addTab("Summary of Rooms Occupied", jPanel34);

        jPanel55.setLayout(new java.awt.BorderLayout());

        jScrollPane16.setBorder(javax.swing.BorderFactory.createTitledBorder("Available Services"));

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service Name", "Description", "Price", "No. of Used Amenities"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable14.getTableHeader().setReorderingAllowed(false);
        jScrollPane16.setViewportView(jTable14);

        jPanel55.add(jScrollPane16, java.awt.BorderLayout.CENTER);

        jPanel56.setPreferredSize(new java.awt.Dimension(461, 100));

        jLabel54.setText("No. of Rooms Occupied:");

        jTextField38.setEditable(false);

        jLabel55.setText("No. of Available Rooms:");

        jTextField39.setEditable(false);

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField39, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jTextField38))
                .addContainerGap(717, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel55.add(jPanel56, java.awt.BorderLayout.NORTH);

        jPanel57.setPreferredSize(new java.awt.Dimension(461, 50));

        jButton9.setText("Print");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addContainerGap(863, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel55.add(jPanel57, java.awt.BorderLayout.SOUTH);

        jPanel58.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );

        jPanel55.add(jPanel58, java.awt.BorderLayout.EAST);

        jTabbedPane2.addTab("Summary of General Services", jPanel55);

        reportsPanel.add(jTabbedPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(reportsPanel, "accountsCard");

        mainPanel.add(jPanel1, java.awt.BorderLayout.CENTER);

        mainHomePanel.add(mainPanel, "mainCard");

        otherPanel.setLayout(new java.awt.BorderLayout());
        mainHomePanel.add(otherPanel, "otherCard");

        getContentPane().add(mainHomePanel, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (UserUtil.isRestaurantCashier()) {
            posPanel.removeAll();
            RestaurantPos pos = new RestaurantPos();
            posPanel.add(pos, java.awt.BorderLayout.CENTER);
            showCard("mainPosCard", jPanel1);
        } else {
            posPanel.removeAll();
//            posPanel.add(jPanel6, java.awt.BorderLayout.NORTH);
            posPanel.add(jPanel7, java.awt.BorderLayout.CENTER);
            clearFields();
            showCard("mainPosCard", jPanel1);
            refreshDiscounts();
            reset();
            showCard("transactionCard", jPanel7);
            refreshDiscounts();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reservationPanel.add(jPanel25, BorderLayout.CENTER);
        reservationsSelectionDialog.setLocationRelativeTo(this);
        showCard("reservationCard", jPanel1);
    }

    private void refreshReservations() {
        List<Reservation> res = null;
        if (jRadioButton1.isSelected()) {
            res = Initializer.getReservationJpaController().findAllOrderById(CHECKED_IN);
            jButton16.setVisible(false);
            jButton18.setVisible(false);
        }
        if (jRadioButton2.isSelected()) {
            res = Initializer.getReservationJpaController().findAllOrderById(CONFIRMED);
            jButton16.setVisible(true);
            jButton18.setVisible(false);
        }
        if (jRadioButton3.isSelected()) {
            res = Initializer.getReservationJpaController().findAllOrderById("");
            jButton16.setVisible(true);
            jButton18.setVisible(true);
        }
        getReservations().clear();
        getReservations().addAll(res);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void showProductsAndServicesPanel() {
        refreshProductsAndServices();
        showCard("productCard", settingsPanel.getPanel5());
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reset();
        showCard("transactionCard", jPanel7);
        refreshDiscounts();
        guestInfoDialog.setLocationRelativeTo(this);
        guestInfoDialog.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        showCard("accountTransactionCard", jPanel7);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        showCard("accountsCard", jPanel1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        setCurrentPaS(new ProductsAndServices());
        clearProductEditor();
        setCategoryDisplay();
        productEditorDialog.setLocationRelativeTo(this);
        productEditorDialog.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void setCategoryDisplay() {
        if (UserUtil.isRestaurantCashier() || UserUtil.isFrontDesk()) {
            if (UserUtil.isRestaurantCashier()) {
                jComboBox4.setSelectedItem("Restaurant Menu");
                showAdditionalInfo(true);
            }
            if (UserUtil.isFrontDesk()) {
                jComboBox4.setSelectedItem("Hotel Service");
                showAdditionalInfo(false);
            }
            jComboBox4.setEnabled(false);
        }
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (jTable1.getSelectedRowCount() == 1) {
            int r = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
            ProductsAndServices pas = getProductsAndServices().get(r);
            setCurrentPaS(pas);
            jTextField18.setText(pas.getName());
            jTextArea3.setText(pas.getDetails());
            jComboBox4.setSelectedItem(pas.getCategory());
            jTextField20.setText(df.format(pas.getPrice()));
            setCategoryDisplay();
            productEditorDialog.setLocationRelativeTo(this);
            productEditorDialog.setVisible(true);
        } else {
            Messages.showErrorMessage(this, "Please select one");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (jTable1.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Continue Delete?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                int r = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
                ProductsAndServices pas = getProductsAndServices().get(r);
                try {
                    Initializer.getProductsAndServicesJpaController().destroy(pas.getId());
                    Messages.showSuccessMessage(this, "Successfully deleted");
                    refreshProductsAndServices();
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
                    Messages.showErrorMessage(this, ex.getMessage());
                }
            }
        } else {
            Messages.showErrorMessage(this, "Please select one");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    public boolean hasEmptyValue(Map<JLabel, String> map) {
        boolean itHas = false;
        for (JLabel str : map.keySet()) {
            if (map.get(str).isEmpty()) {
                str.setForeground(Color.RED);
                itHas = true;
            } else {
                str.setForeground(Color.BLACK);
            }
        }
        return itHas;
    }

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        doCheckIn();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void save() {
        try {
            prepareProductAndServices();
            if (getCurrentPaS().getId() == null) {
                Initializer.getProductsAndServicesJpaController().create(getCurrentPaS());
            } else {
                Initializer.getProductsAndServicesJpaController().edit(getCurrentPaS());
            }
            Messages.showSuccessMessage(this, "Successfully saved!");
            clearProductEditor();
            refreshProductsAndServices();

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        }
    }

    private void prepareProductAndServices() throws NumberFormatException, Exception {
        getCurrentPaS().setFoodCategory("");
        getCurrentPaS().setFoodType("");
        if (jComboBox4.getSelectedItem().equals(ProductEnum.RESTAURANT_MENU.getName())) {
            if (jComboBox5.getSelectedItem().equals("...") && jComboBox6.getSelectedItem().equals("...")) {
                throw new Exception("Please Select Food Category and Type");
            }
            getCurrentPaS().setFoodCategory(jComboBox5.getSelectedItem().toString());
            getCurrentPaS().setFoodType(jComboBox6.getSelectedItem().toString());
        }
        getCurrentPaS().setName(jTextField18.getText());
        getCurrentPaS().setDetails(jTextArea3.getText());
        getCurrentPaS().setCategory(jComboBox4.getSelectedItem().toString());
        getCurrentPaS().setPrice(Double.parseDouble(jTextField20.getText()));
    }

    private void clearProductEditor() {
        jTextField18.setText(null);
        jTextArea3.setText(null);
        showAdditionalInfo(false);
        jComboBox4.setSelectedItem(ProductEnum.HOTEL_SERVICE.getName());
        jTextField20.setText(null);
    }

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        checkInDialog.setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        refreshProductsAndServices();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void refreshProductsAndServices() {
        List<ProductsAndServices> pasList = Initializer.getProductsAndServicesJpaController().findProductsAndServicesEntities();
        getProductsAndServices().clear();
        if (UserUtil.isFrontDesk() || UserUtil.isRestaurantCashier()) {
            for (ProductsAndServices pas : pasList) {
                if (UserUtil.isFrontDesk()) {
                    if (pas.getCategory().equals("Hotel Service")) {
                        getProductsAndServices().add(pas);
                    }
                }
                if (UserUtil.isRestaurantCashier()) {
                    if (pas.getCategory().equals("Restaurant Menu")) {
                        getProductsAndServices().add(pas);
                    }
                }
            }
        } else {
            getProductsAndServices().addAll(pasList);
        }

        new SearchTable().activate(jTable1, jTextField1, 0, 1, 2, 3, 4);
        new SearchTable().activate(jTable3, jTextField5, 0, 1, 2, 3, 4);
    }

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        refreshProductsAndServices();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        refreshReservations();
        refreshRooms();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void grandTotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grandTotalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grandTotalTextFieldActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        showCard("allInOneCard", mainHomePanel);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        if (evt.getClickCount() == 2) {
            int r = jTable3.convertRowIndexToModel(jTable3.getSelectedRow());

            ProductsAndServices pas = getProductsAndServices().get(r);
            jTextField14.setText(pas.getName());
            jTextArea2.setText(pas.getDetails());
            jTextField15.setText(pas.getCategory());
            jSpinField1.setValue(1);
            jTextField16.setText(df.format(pas.getPrice()));
            jTextField16.setText(df.format(pas.getPrice()));

            productsTransactionDialog.setLocationRelativeTo(this);
            productsTransactionDialog.setVisible(true);
            setCurrentPaS(pas);
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        try {
            TransactionProducts tp = new TransactionProducts();
            tp.setTransactionId(getTransaction());
            tp.setProductAndServicesId(getCurrentPaS());
            tp.setQty(jSpinField1.getValue());
            tp.setPrice(df.parse(jTextField16.getText()).doubleValue());
            tp.setTotalPrice(df.parse(jTextField17.getText()).doubleValue());
            getTransactionProducts().add(tp);
            total();
            productsTransactionDialog.setVisible(false);
        } catch (ParseException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void total() {
        double productsTotal = 0;
        double servicesTotal = 0;
        for (TransactionProducts tr : getTransactionProducts()) {
            if (ProductEnum.RESTAURANT_MENU.getName().equals(tr.getProductAndServicesId().getCategory())) {
                productsTotal += tr.getTotalPrice();
            } else {
                servicesTotal += tr.getTotalPrice();
            }
        }

        double hotelTotal = servicesTotal;
        for (TransactionRooms tr : getTransactionRooms()) {
            if (getTransaction().getId() != null) {
                tr.setDepartureDate(new Date());
            }
            hotelTotal += tr.getTotalPrice() * tr.getNumberOfDates();
        }

        double hotelDiscount = 0;
        double restaurantDiscount = 0;
        for (TransactionDiscounts td : getTransactionDiscounts()) {
            if (Hotel.HOTEL_DISCOUNT.equals(td.getType())) {
                hotelDiscount += (td.getAmount() / 100) * hotelTotal;
            }
            if (Hotel.RESTAURANT_DISCOUNT.equals(td.getType())) {
                restaurantDiscount += (td.getAmount() / 100) * productsTotal;
            }
        }

        double grandTotal = (hotelTotal + productsTotal) - (hotelDiscount + restaurantDiscount);

        double payments = 0;
        for (TransactionPayments tp : getTransactionPayments()) {
            payments += tp.getAmount();
        }

        restaurantBill.setText(df.format(productsTotal - restaurantDiscount));
        hotelBill.setText(df.format(hotelTotal));
        final double totalDiscount = hotelDiscount + restaurantDiscount;
        discountTextField.setText(df.format(totalDiscount));
        jTextField19.setText(df.format(hotelDiscount));
        grandTotalTextField.setText(df.format(grandTotal));
        jTextField19.setText(df.format(grandTotal * 0.12));
        paymentsTextField.setText(df.format(payments));
        balanceTextField.setText(df.format(grandTotal - (payments + totalDiscount)));
    }

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        productsTransactionDialog.setVisible(false);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        refreshTransactions();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void refreshTransactions() {
        getTransactions().clear();
        getTransactions().addAll(Initializer.getTransactionsJpaController().findAllIncompleteTransaction());
    }

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (jTable4.getSelectedRowCount() == 1) {
            reset();
            int r = jTable4.convertRowIndexToModel(jTable4.getSelectedRow());
            Transactions t = getTransactions().get(r);
            setTransaction(t);
            setGuestInfo(t);

            getTransactionProducts().clear();
            List<TransactionProducts> tpList = Initializer.getProductsAndServicesJpaController().findTransactionProductsById(t.getId());
            getTransactionProducts().addAll(tpList);

            getTransactionRooms().clear();
            List<TransactionRooms> trList = Initializer.getTransactionRoomsJpaController().findTransactionRoomsById(t.getId());
            getTransactionRooms().addAll(trList);

            getTransactionPayments().clear();
            List<TransactionPayments> tpayList = Initializer.getTransactionPaymentsJpaController().findTransactionPaymentsById(t.getId());
            getTransactionPayments().addAll(tpayList);

            getTransactionDiscounts().clear();
            List<TransactionDiscounts> tdList = Initializer.getTransactionDiscountsJpaController().findTransactionDiscountById(t.getId());
            getTransactionDiscounts().addAll(tdList);

            jTextField2.setText(t.getName());
            jTextField2.setEditable(false);
            jButton23.setVisible(false);

            total();
            showCard("transactionCard", jPanel7);
            jButton45.setVisible(true);
            setPosButtonsEnable(true);
        } else {
            Messages.showErrorMessage(this, "Please select first");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void setGuestInfo(Transactions t) {
        jTextField27.setText(t.getCustomerFirstName());
        jTextField12.setText(t.getCustomerLastName());
        jTextField28.setText(t.getAddress());
        jTextField26.setText(t.getZip());
        jTextField25.setText(t.getCustomerEmail());
        jTextField29.setText(t.getCustomerTelNo());
    }

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        save();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        productEditorDialog.setVisible(false);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (jTable6.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Continue to check-in selected reservation?", "Confirm", JOptionPane.YES_NO_OPTION);
            jButton21.setVisible(false);
            jButton17.setVisible(true);
            jButton49.setVisible(false);
            initSelectionAction(confirm);
        } else {
            Messages.showErrorMessage(this, "Please first");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if (jTable6.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Continue to confirmed selected reservation?", "Confirm", JOptionPane.YES_NO_OPTION);
            jButton21.setVisible(true);
            jButton17.setVisible(false);
            jButton49.setVisible(false);
            initSelectionAction(confirm);
        } else {
            Messages.showErrorMessage(this, "Please first");
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void initSelectionAction(int confirm) {
        if (confirm == 0) {
            int r = jTable6.convertRowIndexToModel(jTable6.getSelectedRow());

            Reservation res = getReservations().get(r);
            try {
                if (sdf.parse(res.getArrival()).after(new Date())) {
                    int confirmAgain = JOptionPane.showConfirmDialog(this, "You arrive earlier than your expected arrival date.  \nDo you intend to check-in now?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirmAgain == JOptionPane.OK_OPTION) {
                        performCheckIn(res);
                    }
                } else {
                    performCheckIn(res);
                }
            } catch (ParseException ex) {
                Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void performCheckIn(Reservation res) {
        jTextField8.setText(res.getFirstname());
        jTextField9.setText(res.getLastname());
        jTextField21.setText(res.getProvince());
        jTextField22.setText(res.getZip() + "");
        jTextField23.setText(res.getEmail());
        jTextField24.setText(res.getContact() + "");
        jTextField32.setText(res.getDownPayment() + "");

        jComboBox1.setSelectedItem(res.getRoomName());
        jTextField10.setText(res.getRoomPrice() + "");

        try {
            if (sdf.parse(res.getArrival()).before(new Date())
                    || sdf.parse(res.getArrival()).equals(CalendarUtil.setTimeToZero(new Date()).getTime())) {
                jDateChooser3.setDate(sdf.parse(res.getArrival()));
                jDateChooser4.setDate(sdf.parse(res.getDeparture()));
            } else {
                jDateChooser3.setDate(new Date());
                jDateChooser4.setDate(null);
            }
        } catch (ParseException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        refreshRooms();
        setSelectedRoomFromReservation(res.getRoomId());
        roomTypeSelectionChanged();
        checkInDialog.setLocationRelativeTo(this);
        checkInDialog.setVisible(true);
    }

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if (jTable6.getSelectedRowCount() == 1) {
            int r = jTable6.convertRowIndexToModel(jTable6.getSelectedRow());
            Reservation res = getReservations().get(r);
            EmailDialog emailDialog = new EmailDialog(this, true, res.getEmail());
            emailDialog.setLocationRelativeTo(this);
            emailDialog.setVisible(true);
        } else {
            Messages.showErrorMessage(this, "Please first");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        refreshReservations();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        refreshReservations();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        refreshReservations();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jTextField16CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField16CaretUpdate
        calculateTotalPrice();
    }//GEN-LAST:event_jTextField16CaretUpdate

    private void jSpinField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSpinField1FocusLost
        calculateTotalPrice();
    }//GEN-LAST:event_jSpinField1FocusLost

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        if (jDateChooser5.getDate() != null) {
            try {
                TransactionPayments tp = new TransactionPayments();
                tp.setTransactionId(getTransaction());
                tp.setAmount(Double.parseDouble(jTextField3.getText()));
                tp.setDateOfPayment(jDateChooser5.getDate());
                getTransactionPayments().add(tp);
                total();
                paymentsDialog.setVisible(false);
            } catch (NumberFormatException ex) {
                Logger.getLogger(HotelMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                Messages.showErrorMessage(this, ex.getMessage());
            }
        } else {
            Messages.showErrorMessage(this, "Invalid Date");
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        paymentsDialog.setVisible(false);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        jDateChooser5.setDate(new Date());
        jTextField3.setText(null);
        paymentsDialog.setLocationRelativeTo(this);
        paymentsDialog.setVisible(true);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        if (jTable7.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove selected payment?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                int r = jTable7.convertRowIndexToModel(jTable7.getSelectedRow());
                TransactionPayments tp = getTransactionPayments().get(r);
                tp.setTransactionId(null);
                if (tp.getId() != null) {
                    transactionPaymentsTemp.add(tp);
                }
                getTransactionPayments().remove(r);
            }
        } else {
            Messages.showErrorMessage(this, "Please select first.");
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        jPanel24.add(jPanel25, BorderLayout.CENTER);
        reservationsSelectionDialog.setLocationRelativeTo(this);
        reservationsSelectionDialog.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        if (jTable6.getSelectedRowCount() == 1) {
            int r = jTable6.convertRowIndexToModel(jTable6.getSelectedRow());
            Reservation res = getReservations().get(r);
            reservationsSelectionDialog.setVisible(false);
            Transactions t = new Transactions();
            t.setCustomerFirstName(res.getFirstname());
            t.setCustomerLastName(res.getLastname());
            t.setCustomerEmail(res.getEmail());
            t.setCustomerTelNo(res.getContact() + "");
            t.setZip(res.getZip() + "");
            t.setCustosmerAddress(res.getProvince());
            setGuestInfo(t);
            guestInfoDialog.setVisible(true);
        } else {
            Messages.showErrorMessage(this, "Please select");
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        reservationsSelectionDialog.setVisible(false);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void paymentsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentsTextFieldActionPerformed

    private void balanceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceTextFieldActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        saveTransaction();
        reset();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void saveTransaction() {
        try {
            Transactions t = prepareTransaction();
            t.setDiscountName("");
            if (getTransaction().getId() == null) {
                Initializer.getTransactionsJpaController().create(t);
                updateRoomStatus(t);
            } else {
                Initializer.getTransactionsJpaController().edit(t);
                updateRoomStatus(t);
            }
            Messages.showSuccessMessage(this, "Successfully save");
        } catch (ParseException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateRoomStatus(Transactions t) {
        if (!t.getTransactionRoomsCollection().isEmpty()) {
            TransactionRooms roms = ((List<TransactionRooms>) t.getTransactionRoomsCollection()).get(0);
            updateRoomStatus(roms.getArrivalDate(), roms.getDepartureDate(), Status.OCCUPIED, roms.getRoom());
        }
    }

    private void updateRoomStatus(Date start, Date end, int status, Rooms room) {
        if (status > -1) {
            Calendar c = Calendar.getInstance();
            c.setTime(start);
            createRoomStatus(room, c, status);
            while (c.getTime().before(end)) {
                c.add(Calendar.DATE, 1);
                createRoomStatus(room, c, status);
            }
        }
    }

    private void createRoomStatus(Rooms room, Calendar c, int status) {
        RoomStatus rs = new RoomStatus();
        rs.setRoomId(room);
        rs.setStartDate(c.getTime());
        rs.setStatus(status);
        Initializer.getRoomStatusJpaController().create(rs);
    }

    private void reset() {
        jButton45.setVisible(false);
        clearFields();
         setPosButtonsEnable(false);
//        guestInfoDialog.setLocationRelativeTo(this);
//        guestInfoDialog.setVisible(true);
    }

    private void clearFields() {
        getTransactionPayments().clear();
        getTransactionProducts().clear();
        getTransactionRooms().clear();
        getTransactionDiscounts().clear();
        setTransaction(new Transactions());
        transactionPaymentsTemp = new ArrayList<>();
        transactionProductsTemp = new ArrayList<>();
        transactionRoomsTemp = new ArrayList<>();
        total();
        jTextField2.setText(null);
        jButton23.setVisible(true);

        jTextField27.setText(null);
        jTextField12.setText(null);
        jTextField28.setText(null);
        jTextField26.setText(null);
        jTextField25.setText(null);
        jTextField29.setText(null);
    }

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        if (jTable2.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove selected product?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                int r = jTable2.convertRowIndexToModel(jTable2.getSelectedRow());
                TransactionProducts tp = getTransactionProducts().get(r);
                tp.setTransactionId(null);
                if (tp.getId() != null) {
                    transactionProductsTemp.add(tp);
                }
                getTransactionProducts().remove(r);
            }
        } else {
            Messages.showErrorMessage(this, "Please select first.");
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        reset();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        jTextField2.setText(jTextField27.getText() + " " + jTextField12.getText());
        if (!jTextField2.getText().isEmpty()) {
            setPosButtonsEnable(true);
        }
        guestInfoDialog.setVisible(false);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        guestInfoDialog.setVisible(false);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        guestInfoDialog.setLocationRelativeTo(this);
        guestInfoDialog.setVisible(true);
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        if (evt.getClickCount() == 2) {
            try {
                int r = jTable9.convertRowIndexToModel(jTable9.getSelectedRow());
                Room room = getRooms().get(r);
                roomPrice = room.getRate();
                jTextField4.setText(room.getType());
                jDateChooser6.setDate(new Date());
                jDateChooser7.setDate(new Date());
                jTextField11.setText(df.format(room.getRate()));
                roomIdLabel.setText(room.getRoomId() + "");

                getRoomsSelection().clear();
                getRoomsSelection().addAll(new RoomsJpaController(DbConf.getInstance()).findByRoomTypeId(room.getRoomId()));
                jComboBox2.setSelectedItem(null);
                roomDialog.setLocationRelativeTo(this);
                roomDialog.setVisible(true);
            } catch (Exception ex) {
                Messages.showErrorMessage(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jTable9MouseClicked

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        refreshRooms();
    }//GEN-LAST:event_jButton40ActionPerformed

    private double roomPrice;

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        Map<JLabel, String> map = new HashMap<>();
        if (!hasEmptyValue(map) && jComboBox2.getSelectedItem() != null) {
            if (jDateChooser6.getDate() != null && jDateChooser7.getDate() != null
                    && (jDateChooser6.getDate().before(jDateChooser7.getDate()) || jDateChooser6.getDate().equals(jDateChooser7.getDate()))) {

                if (roomNotAvailable((Rooms) jComboBox2.getSelectedItem(), jDateChooser6.getDate(), jDateChooser6.getDate())) {
                    Messages.showErrorMessage(this, "Selected room is not available for selected date.");
                } else {
                    TransactionRooms tr = new TransactionRooms();
                    try {
                        final Rooms selectedRoom = (Rooms) jComboBox2.getSelectedItem();
                        tr.setArrivalDate(jDateChooser6.getDate());
                        tr.setDepartureDate(jDateChooser7.getDate());
                        tr.setPrice(roomPrice);
                        tr.setQty(1);
                        tr.setRoomId(Integer.parseInt(roomIdLabel.getText()));

                        tr.setRoom(selectedRoom);
                        tr.setTransactionId(getTransaction());
                        getTransactionRooms().add(tr);
                        total();
                        roomDialog.setVisible(false);

                    } catch (Exception ex) {
                        Logger.getLogger(HotelMain.class
                                .getName()).log(Level.SEVERE, null, ex);
                        Messages.showErrorMessage(this, ex.getMessage());
                    }
                }
            } else {
                Messages.showErrorMessage(this, "invalid Dates");
            }
        } else {
            Messages.showErrorMessage(this, "Fields with red label should not be empty");
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        roomDialog.setVisible(false);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        if (jTable8.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove selected product?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                int r = jTable8.convertRowIndexToModel(jTable8.getSelectedRow());
                TransactionRooms tr = getTransactionRooms().get(r);
                tr.setTransactionId(null);
                if (tr.getId() != null) {
                    transactionRoomsTemp.add(tr);
                }
                getTransactionRooms().remove(r);
            }
        } else {
            Messages.showErrorMessage(this, "Please select first.");
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null
                && (jDateChooser1.getDate().before(jDateChooser2.getDate()) || jDateChooser1.getDate().equals(jDateChooser2.getDate()))) {
            Date start = CalendarUtil.setTimeToZero(jDateChooser1.getDate()).getTime();
            Date end = CalendarUtil.setTimeToZero(jDateChooser2.getDate()).getTime();
            DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
            dtm.setRowCount(0);
            List<Object[]> tList = (List<Object[]>) Initializer.getTransactionsJpaController().findByDates(start, end);
            double total = 0;
            double total_restaurant = 0;
            double total_hotel = 0;
            for (Object[] objects : tList) {
                double total_bill = (double) objects[5] + (double) objects[6];
                total += total_bill;
                total_restaurant += Double.parseDouble(objects[5].toString());
                total_hotel += Double.parseDouble(objects[6].toString());
                Object[] row = {objects[0],
                    objects[1].toString() + objects[2].toString(),
                    getNumberOfDates((Date) objects[3], (Date) objects[4]),
                    Initializer.PESO_DF.format(objects[5]),
                    Initializer.PESO_DF.format(objects[6]),
                    Initializer.PESO_DF.format(total_bill)
                };
                dtm.addRow(row);
            }

            jTextField37.setText(Initializer.PESO_DF.format(total_restaurant));
            jTextField40.setText(Initializer.PESO_DF.format(total_hotel));
            jTextField30.setText(Initializer.PESO_DF.format(total));
        } else {
            Messages.showErrorMessage(this, "Invalid dates");
        }
    }//GEN-LAST:event_jButton44ActionPerformed
    public long getNumberOfDates(Date start, Date end) {
        long total = ((end.getTime() - start.getTime()) / 86400000) + 1;
        return total;
    }

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        showCard("settingsCard", jPanel1);
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarActionPerformed
        calendarPanel.initTableValues();
        showCard("calendarCard", jPanel1);
    }//GEN-LAST:event_btnCalendarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.hasFocus()) {
            roomTypeSelectionChanged();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        if (jComboBox4.hasFocus()) {
            if (jComboBox4.getSelectedItem().equals(ProductEnum.HOTEL_SERVICE.getName())) {
                showAdditionalInfo(false);
            } else {
                showAdditionalInfo(true);
            }
        }
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.hasFocus()) {
            roomTypeSelectionChanged();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        if (roomNotAvailable((Rooms) jComboBox3.getSelectedItem(), jDateChooser3.getDate(), jDateChooser4.getDate())) {
            Messages.showErrorMessage(this, "Selected room is not available for selected date.");
        } else {
            try {
                int resevationId = updateReservation(CONFIRMED);
                final Rooms roomss = (Rooms) jComboBox3.getSelectedItem();
                updateRoomStatus(jDateChooser3.getDate(), jDateChooser4.getDate(), Status.RESRVE, roomss);
                Messages.showSuccessMessage(this, "Room has been reserved successfully");
                checkInDialog.setVisible(false);
                refreshReservations();
            } catch (Exception ex) {
                Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
                Messages.showErrorMessage(checkInDialog, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Continue to logout?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            showCard("homeCard", jPanel1);
            DbConf.resetInstance();
//            HotelMain.resetMain();
            LoginForm.getInstance().setLocationRelativeTo(HotelMain.getInstance());
//            Messages.showSuccessMessage(this, "Successfully logout");
            LoginForm.getInstance().setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jSpinField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinField1PropertyChange
        if (jSpinField1.getValue() > 0) {
            calculateTotalPrice();
        } else {
            jTextField17.setText("0.00");
        }
    }//GEN-LAST:event_jSpinField1PropertyChange

    private void jDateChooser7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser7PropertyChange
        calculatePrice();
    }//GEN-LAST:event_jDateChooser7PropertyChange

    private void jDateChooser6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser6PropertyChange
        calculatePrice();
    }//GEN-LAST:event_jDateChooser6PropertyChange

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Has the room has been checked?", "confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.OK_OPTION) {
            int confirmAgain = JOptionPane.showConfirmDialog(this, "Are you sure you want to continue check out guest?", "confirm", JOptionPane.YES_NO_OPTION);
            if (confirmAgain == JOptionPane.OK_OPTION) {
                total();
                double TOTAL = 0;
                try {
                    TOTAL = df.parse(grandTotalTextField.getText()).doubleValue() - (df.parse(paymentsTextField.getText()).doubleValue() + df.parse(discountTextField.getText()).doubleValue());
                } catch (ParseException ex) {
                    Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
                    Messages.showErrorMessage(this, ex.getMessage());
                }
                jTextField35.setText(df.format(TOTAL));
                paymentDialog.setLocationRelativeTo(this);
                paymentDialog.setVisible(true);
            }
        } else if (confirm == JOptionPane.NO_OPTION) {
            DefaultTableModel dtm = (DefaultTableModel) jTable13.getModel();
            dtm.setRowCount(0);
            for (TransactionProducts tp : getTransactionProducts()) {
                if (tp.getProductAndServicesId().getCategory().equals("Hotel Service")) {
                    Object[] row = {tp.getProductAndServicesId().getName(), tp.getQty()};
                    dtm.addRow(row);
                }
            }
            if (dtm.getRowCount() > 0) {
                transactionServiceDialog.setLocationRelativeTo(this);
                transactionServiceDialog.setVisible(true);
            } else {
                Messages.showInfoMessage(this, "No Services Availed");
            }
        }
    }//GEN-LAST:event_jButton45ActionPerformed

    private void printCheckOutReceipt(String cash, String change) throws JRException {
        Map<String, Object> map = new HashMap<>();
        map.put("TRANSACTION_NUMBER", getTransaction().getId().toString());
        map.put("GUEST_NAME", getTransaction().getName());
        map.put("DISCOUNT", "₱" + discountTextField.getText());
        map.put("GRAND_TOTAL", "₱" + grandTotalTextField.getText());
        map.put("BALANCE", balanceTextField.getText());
        map.put("DOWNPAYMENT", "₱" + paymentsTextField.getText());
        map.put("VAT", "₱" + jTextField19.getText());
        map.put("CHANGE", "₱" + change);
        map.put("CASH", "₱" + cash);

        List<Particulars> list = new ArrayList<>();
        for (TransactionRooms tr : getTransactionRooms()) {
            Particulars p = new Particulars();
            p.setName(tr.getRoom().getName() + " " + tr.getRoom().getRoomTypeName() + " (" + Initializer.sdf.format(tr.getArrivalDate()) + " " + Initializer.sdf.format(tr.getDepartureDate()) + ")");
            p.setAmount(df.format(tr.getPrice() * tr.getNumberOfDates()));
            list.add(p);
        }
        for (TransactionProducts tp : getTransactionProducts()) {
            Particulars p = new Particulars();
            p.setName(tp.getProductAndServicesId().getName());
            p.setAmount(tp.getTotalPriceStr());
            list.add(p);
        }
//        for (TransactionPayments tp : getTransactionPayments()) {
//            Particulars p = new Particulars();
//            p.setName("Payment: " + tp.getDateOfPaymentStr());
//            p.setAmount("(" + tp.getAmountStr() + ")");
//            list.add(p);
//        }
        PrintUtil.print(list, "resources/CheckOutReport.jasper", "Print", map, this);
    }

    private void checkedOut() throws Exception {
        getTransaction().setTotalHotelBill(df.parse(hotelBill.getText()).doubleValue());
        getTransaction().setTotalRestaurantBill(df.parse(restaurantBill.getText()).doubleValue());
        getTransaction().setDate(new Date());
        getTransaction().setCheckedOut(true);
        getTransaction().setDiscountName("");
        Initializer.getTransactionsJpaController().edit(getTransaction());
        Messages.showSuccessMessage(this, "Successfully checked out guest.");
        saveTransaction();
        if (getTransaction().getReservationId() != null) {
            Reservation res = Initializer.getReservationJpaController().findReservation(Integer.parseInt(getTransaction().getReservationId()));
            res.setStatus(CHECKEDOUT);
            Initializer.getReservationJpaController().edit(res);
        }
        refreshTransactions();
    }


    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        if (jTable6.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                int r = jTable6.convertRowIndexToModel(jTable6.getSelectedRow());
                try {
                    Initializer.getReservationJpaController().destroy(getReservations().get(r).getReservationId());
                    Messages.showSuccessMessage(this, "Successfully deleted.");
                    refreshReservations();
                } catch (NonexistentEntityException ex) {
                    Messages.showErrorMessage(this, ex.getMessage());
                    Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Messages.showErrorMessage(this, "Please select first.");
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        if (jTable6.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Continue to edit selected reservation?", "Confirm", JOptionPane.YES_NO_OPTION);
            jButton21.setVisible(false);
            jButton17.setVisible(false);
            jButton49.setVisible(true);
            initSelectionAction(confirm);
        } else {
            Messages.showErrorMessage(this, "Please first");
        }

    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        if (roomNotAvailable((Rooms) jComboBox3.getSelectedItem(), jDateChooser3.getDate(), jDateChooser4.getDate())) {
            Messages.showErrorMessage(this, "Selected room is not available for selected date.");
        } else {
            try {
                updateReservation(SAVE_ONLY);
                final Rooms roomss = (Rooms) jComboBox3.getSelectedItem();
                updateRoomStatus(jDateChooser3.getDate(), jDateChooser4.getDate(), -1, roomss);
                Messages.showSuccessMessage(this, "Room has been reserved successfully");
                checkInDialog.setVisible(false);
                refreshReservations();
            } catch (Exception ex) {
                Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
                Messages.showErrorMessage(checkInDialog, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void discountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountTextFieldActionPerformed

    private void restaurantBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurantBillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_restaurantBillActionPerformed

    private void hotelBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotelBillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hotelBillActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        transactionDiscountDialog.setLocationRelativeTo(this);
        transactionDiscountDialog.setVisible(true);
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        getDiscounts().clear();
        getDiscounts().addAll(Initializer.getDiscountsJpaController().findAllActiveDiscounts());
        discountsDialog.setLocationRelativeTo(this);
        discountsDialog.setVisible(true);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        discountsDialog.setVisible(false);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        if (jTable11.getSelectedRowCount() == 1) {
            int r = jTable11.convertRowIndexToModel(jTable11.getSelectedRow());
            currentDiscount = getDiscounts().get(r);
            if (currentDiscount.getShowOtherInfo()) {
                jTextField1.setText("");
                discountsDialog.setVisible(false);
                discountCommentDialog.setLocationRelativeTo(this);
                discountCommentDialog.setVisible(true);
            } else {
                addToTransactionDiscount();
            }
        } else {
            Messages.showErrorMessage(discountsDialog, "Please select One");
        }
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        discountCommentDialog.setVisible(false);
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        addToTransactionDiscount();
    }//GEN-LAST:event_jButton55ActionPerformed

    private void addToTransactionDiscount() {
        TransactionDiscounts td = new TransactionDiscounts();
        td.setTransactionId(getTransaction());
        td.setAmount(currentDiscount.getAmount());
        td.setName(currentDiscount.getName());
        td.setComments(jTextField33.getText());
        td.setType(Hotel.HOTEL_DISCOUNT);
        getTransactionDiscounts().add(td);
        Messages.showSuccessMessage(discountCommentDialog, currentDiscount.getName() + " successfully added.");
        total();
        discountCommentDialog.setVisible(false);
    }

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        transactionDiscountDialog.setVisible(false);
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        if (jTable12.getSelectedRowCount() == 1) {
            int confirm = JOptionPane.showConfirmDialog(transactionDiscountDialog, "Are you sure you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                int r = jTable12.convertRowIndexToModel(jTable12.getSelectedRow());
                getTransactionDiscounts().remove(r);
                transactionDiscountDialog.setVisible(false);
                total();
            }
        } else {
            Messages.showErrorMessage(transactionDiscountDialog, "Please select one");
        }
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        paymentDialog.setVisible(false);
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        try {
            double payment = df.parse(jTextField34.getText()).doubleValue();
            final double total = df.parse(jTextField35.getText()).doubleValue();
            if (payment >= total) {
                int confirmagain = JOptionPane.showConfirmDialog(this, "Generate Billing Statement?", "confirm", JOptionPane.YES_NO_OPTION);
                if (confirmagain == JOptionPane.CANCEL_OPTION || confirmagain == JOptionPane.CLOSED_OPTION) {
                    Messages.showInfoMessage(paymentDialog, "Action has been cancelled");
                } else if (confirmagain == JOptionPane.NO_OPTION) {
                    TransactionPayments tp = new TransactionPayments();
                    tp.setDateOfPayment(new Date());
                    tp.setAmount(payment);
                    tp.setName("");
                    getTransactionPayments().add(tp);
                    paymentDialog.setVisible(false);
                    checkedOut();
                    reset();
//                    printCheckOutReceipt();
                    if (payment > total) {
                        Messages.showInfoMessage(this, "Change: " + Initializer.df.format(payment - total));
                    }
                } else if (confirmagain == JOptionPane.OK_OPTION) {
                    TransactionPayments tp = new TransactionPayments();
                    tp.setDateOfPayment(new Date());
                    tp.setAmount(payment);
                    tp.setName("");
                    getTransactionPayments().add(tp);
                    paymentDialog.setVisible(false);
                    checkedOut();
                    printCheckOutReceipt(Initializer.PESO_DF.format(payment), jTextField36.getText());
                    reset();
                    if (payment > total) {
                        Messages.showInfoMessage(this, "Change: " + Initializer.df.format(payment - total));
                    }
                }
            } else {
                Messages.showErrorMessage(paymentDialog, "Insufficient Payment Amount");
            }
        } catch (NumberFormatException e) {
            Messages.showErrorMessage(paymentDialog, e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(paymentDialog, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jTextField34CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField34CaretUpdate
        try {
            if (!jTextField34.getText().equals("")) {
                double amountTendered = df.parse(jTextField34.getText()).doubleValue();
                double amountDue = df.parse(jTextField35.getText()).doubleValue();
                double change = 0;
                if (amountTendered > amountDue) {
                    change = amountTendered - amountDue;
                }
                jTextField36.setText(df.format(change));
            }
        } catch (ParseException e) {
            Messages.showErrorMessage(paymentDialog, e.getMessage());
        }
    }//GEN-LAST:event_jTextField34CaretUpdate

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        transactionServiceDialog.setVisible(false);
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        Map<String, Object> map = new HashMap<>();
        List<Particulars> list = new ArrayList<>();
        for (int i = 0; i < jTable13.getRowCount(); i++) {
            Particulars p = new Particulars();
            p.setName(jTable13.getValueAt(i, 0).toString());
            p.setAmount(jTable13.getValueAt(i, 1).toString());
            list.add(p);
        }

        try {
            PrintUtil.print(list, "resources/TransactionServicesCheckList.jasper", "Print", map, this);

        } catch (JRException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        if (jTabbedPane2.getSelectedIndex() == 1) {
            int totalRooms = Initializer.getRoomsJpaController().getRoomsCount();
            int totalOccupiedRooms = Initializer.getRoomStatusJpaController().countNoRoomsByDateAndStatus(CalendarUtil.setTimeToZero(new Date()).getTime(), Status.OCCUPIED);
            int totalNotAvailableRooms = Initializer.getRoomStatusJpaController().countNotAvailableRooms(CalendarUtil.setTimeToZero(new Date()).getTime());

            jTextField38.setText(String.valueOf(totalOccupiedRooms));
            final int totalAvailableRooms = totalRooms - totalNotAvailableRooms;
            jTextField39.setText(String.valueOf((totalAvailableRooms < 0 ? 0 : totalAvailableRooms)));

            DefaultTableModel dtm = (DefaultTableModel) jTable14.getModel();
            dtm.setRowCount(0);
            List<Object[]> services = Initializer.getTransactionProductsJpaController().getServiceReports();
            for (Object[] objects : services) {
                objects[2] = Initializer.PESO_DF.format(objects[2]);
                dtm.addRow(objects);
            }
        }
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("START_DATE", sdf.format(jDateChooser1.getDate()));
            map.put("END_DATE", sdf.format(jDateChooser2.getDate()));
            map.put("TOTAL", jTextField30.getText());
            map.put("RESTAURANT_BILL_TOTAL", jTextField37.getText());
            map.put("HOTEL_BILL_TOTAL", jTextField40.getText());

            DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
            List<Particulars> list = new ArrayList<>();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                Particulars p = new Particulars();
                p.setRoomNo(dtm.getValueAt(i, 0).toString());
                p.setCusName(dtm.getValueAt(i, 1).toString());
                p.setDateStayed(dtm.getValueAt(i, 2).toString());
                p.setResBill(dtm.getValueAt(i, 3).toString());
                p.setHotelBill(dtm.getValueAt(i, 4).toString());
                p.setTotalBill(dtm.getValueAt(i, 5).toString());
                list.add(p);
            }

            PrintUtil.print(list, "resources/TransactionReport.jasper", "Print", map, this);
        } catch (JRException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("START_DATE", sdf.format(jDateChooser1.getDate()));
            map.put("END_DATE", sdf.format(jDateChooser2.getDate()));
            map.put("OCCUPIED_ROOMS", jTextField38.getText());
            map.put("AVAILABLE_ROOMS", jTextField39.getText());

            DefaultTableModel dtm = (DefaultTableModel) jTable14.getModel();
            List<Particulars> list = new ArrayList<>();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                Particulars p = new Particulars();
                p.setRoomNo(dtm.getValueAt(i, 0).toString());
                p.setCusName(dtm.getValueAt(i, 1).toString());
                p.setDateStayed(dtm.getValueAt(i, 2).toString());
                p.setResBill(dtm.getValueAt(i, 3) != null ? dtm.getValueAt(i, 3).toString() : "");
                list.add(p);
            }

            PrintUtil.print(list, "resources/ServiceReport.jasper", "Print", map, this);
        } catch (JRException ex) {
            Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jDateChooser4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser4PropertyChange
        updateTotalprice();
    }//GEN-LAST:event_jDateChooser4PropertyChange

    private void jDateChooser3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser3PropertyChange
        updateTotalprice();
    }//GEN-LAST:event_jDateChooser3PropertyChange

    private void updateTotalprice() {
        if (jDateChooser3.getDate() != null && jDateChooser4.getDate() != null) {
            try {
                Date dateStart = jDateChooser3.getDate();
                Date dateEnd = jDateChooser4.getDate();
                int noOfDays = getNoOfDays(dateStart, dateEnd);
                double total = noOfDays * df.parse(jTextField10.getText()).doubleValue();
                jTextField31.setText(df.format(total));
                jTextField32.setText(df.format(total * 0.5));
            } catch (ParseException e) {
                Logger.getLogger(HotelMain.class.getName()).log(Level.INFO, null, e);
//                Messages.showErrorMessage(checkInDialog, e.getMessage());
            }
        }
    }

    private int getNoOfDays(Date start, Date end) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        int countNoDays = 0;
        while (c.getTime().before(end)) {
            c.add(Calendar.DATE, 1);
            countNoDays++;
        }
        return countNoDays;
    }

    private void calculatePrice() {
        if (jDateChooser6.getDate() != null && jDateChooser7.getDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(jDateChooser6.getDate());
            int countNoDays = 0;
            while (c.getTime().before(jDateChooser7.getDate())) {
                c.add(Calendar.DATE, 1);
                countNoDays++;
            }
            if (countNoDays >= 5) {
                countNoDays--;
            }
            double price = roomPrice * countNoDays;
            jTextField11.setText(df.format(price));
        }
    }

    private int getNoDays() {
        if (jDateChooser6.getDate() != null && jDateChooser7.getDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(jDateChooser6.getDate());
            int countNoDays = 0;
            while (c.getTime().before(jDateChooser7.getDate())) {
                c.add(Calendar.DATE, 1);
                countNoDays++;
            }
            if (countNoDays >= 5) {
                countNoDays--;
            }
            return countNoDays;
        }
        return 0;
    }

    private void doCheckIn() {
        Map<JLabel, String> map = new HashMap<>();
        map.put(jLabel29, jTextField8.getText());
        map.put(jLabel30, jTextField9.getText());
        map.put(jLabel11, jTextField10.getText());
        if (!hasEmptyValue(map) && jComboBox3.getSelectedItem() != null) {
            if (jDateChooser3.getDate() != null && jDateChooser4.getDate() != null
                    && (jDateChooser3.getDate().before(jDateChooser4.getDate()) || jDateChooser3.getDate().equals(jDateChooser4.getDate()))) {
                try {
                    if (roomNotAvailable((Rooms) jComboBox3.getSelectedItem(), jDateChooser3.getDate(), jDateChooser4.getDate())) {
                        Messages.showErrorMessage(this, "Selected room is not available for selected date.");
                    } else {
                        int resevationId = updateReservation(CHECKED_IN);
                        Transactions trans = createTransaction(resevationId);
                        addTransactionPayment(trans);
                        Rooms roomss = createTransactionRooms(trans);
                        updateRoomStatus(jDateChooser3.getDate(), jDateChooser4.getDate(), Status.OCCUPIED, roomss);
                        Messages.showSuccessMessage(this, "Successfully check in");
                        checkInDialog.setVisible(false);
                        refreshReservations();
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
                    Messages.showErrorMessage(this, ex.getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(HotelMain.class.getName()).log(Level.SEVERE, null, ex);
                    Messages.showErrorMessage(checkInDialog, ex.getMessage());
                }
            } else {
                Messages.showErrorMessage(this, "invalid Dates");
            }
        } else {
            Messages.showErrorMessage(this, "Fields with red label should not be empty");
        }
    }

    public void addTransactionPayment(Transactions transaction) throws NumberFormatException, ParseException {
        TransactionPayments tp = new TransactionPayments();
        tp.setDateOfPayment(new Date());
        tp.setTransactionId(transaction);
        tp.setName("Down Payment");
        try {
            tp.setAmount(Initializer.df.parse(jTextField32.getText()).doubleValue());
            Initializer.getTransactionPaymentsJpaController().create(tp);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    private Rooms createTransactionRooms(Transactions trans) throws NumberFormatException {
        TransactionRooms tr = new TransactionRooms();
        tr.setArrivalDate(jDateChooser3.getDate());
        tr.setDepartureDate(jDateChooser4.getDate());
        tr.setPrice(Double.parseDouble(jTextField10.getText()));
        tr.setQty(1);
        tr.setRoomId(((Room) jComboBox1.getSelectedItem()).getRoomId());
        final Rooms roomss = (Rooms) jComboBox3.getSelectedItem();
        tr.setRoom(roomss);
        tr.setTransactionId(trans);
        Initializer.getTransactionRoomsJpaController().create(tr);
        return roomss;
    }

    private Transactions createTransaction(int resevationId) {
        Transactions trans = new Transactions();
        trans.setCustomerFirstName(jTextField8.getText());
        trans.setCustomerLastName(jTextField9.getText());
        trans.setCustosmerAddress(jTextField21.getText());
        trans.setZip(jTextField22.getText());
        trans.setCustomerEmail(jTextField23.getText());
        trans.setCustomerTelNo(jTextField24.getText());
        trans.setDiscountName("...");
        trans.setDiscountAmount(0);
        trans.setReservationId("" + resevationId);
        Initializer.getTransactionsJpaController().create(trans);
        return trans;
    }

    private int updateReservation(String reservationStatus) throws ParseException, Exception {
        int r = jTable6.convertRowIndexToModel(jTable6.getSelectedRow());
        Reservation res = getReservations().get(r);
        if (!reservationStatus.equals(SAVE_ONLY)) {
            res.setStatus(reservationStatus);
        }
        res.setRoomNameNumber(jComboBox3.getSelectedItem().toString());

        res.setDownPayment(Initializer.df.parse(jTextField32.getText()).doubleValue());
        Initializer.getReservationJpaController().edit(res);
        return res.getReservationId();

    }

    private void roomTypeSelectionChanged() {
        getRoomsSelection().clear();
        getRoomsSelection().addAll(new RoomsJpaController(DbConf.getInstance()).findByRoomTypeId(((Room) jComboBox1.getSelectedItem()).getRoomId()));
    }

    private void showAdditionalInfo(boolean visible) {
        jLabel47.setVisible(visible);
        jLabel48.setVisible(visible);
        jComboBox5.setVisible(visible);
        jComboBox6.setVisible(visible);
    }

    private Transactions prepareTransaction() throws ParseException {
        Transactions t = getTransaction();
        t.setCustomerFirstName(jTextField27.getText());
        t.setCustomerLastName(jTextField12.getText());
        t.setCustosmerAddress(jTextField28.getText());
        t.setZip(jTextField26.getText());
        t.setCustomerEmail(jTextField25.getText());
        t.setCustomerTelNo(jTextField29.getText());

        t.setTransactionPaymentsCollection(getTransactionPayments());
        t.getTransactionPaymentsCollection().addAll(transactionPaymentsTemp);

        t.setTransactionProductsCollection(getTransactionProducts());
        t.getTransactionProductsCollection().addAll(transactionProductsTemp);

        t.setTransactionRoomsCollection(getTransactionRooms());
        t.getTransactionRoomsCollection().addAll(transactionRoomsTemp);

        t.setTransactionDiscountsCollection(getTransactionDiscounts());
//        t.setTransactionDiscountsCollection().addAll(transactionRoomsTemp);

        t.setTotalPrice(df.parse(grandTotalTextField.getText()).doubleValue());

        return t;
    }

    private void calculateTotalPrice() {

        try {
            int qty = jSpinField1.getValue();
            double price = Double.parseDouble(jTextField16.getText().isEmpty() ? "0" : jTextField16.getText());

            double totalPrice = (double) qty * price;
            jTextField17.setText(df.format(totalPrice));

        } catch (NumberFormatException ex) {
            java.util.logging.Logger.getLogger(HotelMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Messages.showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balanceTextField;
    private javax.swing.JButton btnCalendar;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSettings;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JDialog checkInDialog;
    private javax.swing.JDialog discountCommentDialog;
    private javax.swing.JTextField discountTextField;
    private javax.swing.JDialog discountsDialog;
    private javax.swing.JTextField grandTotalTextField;
    private javax.swing.JDialog guestInfoDialog;
    private javax.swing.JPanel homePanel;
    private javax.swing.JTextField hotelBill;
    private javax.swing.JPanel incompleteTransactionPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainHomePanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel otherPanel;
    private javax.swing.JDialog paymentDialog;
    private javax.swing.JDialog paymentsDialog;
    private javax.swing.JTextField paymentsTextField;
    private javax.swing.JPanel posPanel;
    private javax.swing.JDialog productEditorDialog;
    private javax.swing.JPanel productsAndServicePanel;
    private javax.swing.JDialog productsTransactionDialog;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JPanel reservationPanel;
    private javax.swing.JDialog reservationsSelectionDialog;
    private javax.swing.JTextField restaurantBill;
    private javax.swing.JDialog roomDialog;
    private javax.swing.JLabel roomIdLabel;
    private javax.swing.JPanel salesTransactionPanel;
    private javax.swing.JDialog transactionDiscountDialog;
    private javax.swing.JDialog transactionServiceDialog;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void refreshRooms() {
        getRooms().clear();
        getRooms().addAll(Initializer.getRoomJpaController().findRoomEntities());
    }

    public void initUser() {
        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        btnCalendar.setVisible(true);
        btnSettings.setVisible(true);
        showAdditionalInfo(true);
        jTabbedPane1.setEnabledAt(1, true);
        jScrollPane7.setVisible(true);
        jButton25.setVisible(true);
        if (UserUtil.isFrontDesk()) {
            showAdditionalInfo(false);
        } else if (UserUtil.isRestaurantCashier()) {
            jTabbedPane1.setEnabledAt(1, false);
            jScrollPane7.setVisible(false);
            jButton1.setVisible(false);
            btnCalendar.setVisible(false);
            showAdditionalInfo(true);
            jButton2.setVisible(false);
            jButton25.setVisible(false);
        } else if (UserUtil.isAllInOne()) {
            jButton1.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            btnCalendar.setVisible(false);
            btnSettings.setVisible(false);
        } else {
            System.out.println("000000");
        }
        settingsPanel.initSettings();
    }

    private boolean roomNotAvailable(Rooms room, Date start, Date end) {
        return Initializer.getRoomStatusJpaController().roomIsNotAvailable(room, CalendarUtil.setTimeToZero(start).getTime(), CalendarUtil.setTimeToEndTime(end).getTime());
    }

    private void setSelectedRoomFromReservation(int roomId) {
        for (Room r : getRooms()) {
            if (r.getRoomId() == roomId) {
                jComboBox1.setSelectedItem(r);
            }
        }
    }
    
    private void setPosButtonsEnable(boolean enable){
        jButton28.setEnabled(enable);
        jButton29.setEnabled(enable);
        jButton36.setEnabled(enable);
        jButton43.setEnabled(enable);
        jButton37.setEnabled(enable);
        jButton10.setEnabled(enable);
        jButton45.setEnabled(enable);
        jButton51.setEnabled(enable);
        jButton50.setEnabled(enable);
        jTable3.setEnabled(enable);
        jTable9.setEnabled(enable);
    }

}
