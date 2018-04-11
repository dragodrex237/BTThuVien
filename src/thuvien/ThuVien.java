/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.*;
import javafx.scene.transform.Transform;
import javafx.stage.DirectoryChooser;
import javax.imageio.ImageIO;


/**
 *
 * @author ASUS
 */
public class ThuVien extends Application{

    /**
     * @param args the command line arguments
     */
    
    //Stage(Cửa sổ) của chương trình
    Stage stMain = new Stage();
    Stage stSach = new Stage();
    Stage stNXB = new Stage();
    Stage stTG = new Stage();
    Stage stMT = new Stage();
    Stage stHV = new Stage();
    Stage stBC = new Stage();
    Stage stMTQ = new Stage();
    Stage stSQ = new Stage();
    Stage stTK = new Stage();
    Stage stLS = new Stage();
    Stage stDN = new Stage();
    
    //DataList để đổ vào TableView
    ObservableList<Sach> dataSach = FXCollections.observableArrayList();
    ObservableList<NXB> dataNXB = FXCollections.observableArrayList();
    ObservableList<TacGia> dataTG = FXCollections.observableArrayList();
    ObservableList<MTSach> dataMT = FXCollections.observableArrayList();
    ObservableList<HoiVien> dataHV = FXCollections.observableArrayList();
    ObservableList<String> dataNam = FXCollections.observableArrayList();
    ObservableList<String> dataNam2 = FXCollections.observableArrayList();
    ObservableList<TaiKhoan> dataTK = FXCollections.observableArrayList();
    ObservableList<LoaiSach> dataLS = FXCollections.observableArrayList();
    
    //DataList của các Quý trong năm
    ObservableList<MTSach> dataMTQ1 = FXCollections.observableArrayList();
    ObservableList<MTSach> dataMTQ2 = FXCollections.observableArrayList();
    ObservableList<MTSach> dataMTQ3 = FXCollections.observableArrayList();
    ObservableList<MTSach> dataMTQ4 = FXCollections.observableArrayList();
    
    //DataList của các Quý trong năm
    ObservableList<Sach> dataSQ1 = FXCollections.observableArrayList();
    ObservableList<Sach> dataSQ2 = FXCollections.observableArrayList();
    ObservableList<Sach> dataSQ3 = FXCollections.observableArrayList();
    ObservableList<Sach> dataSQ4 = FXCollections.observableArrayList();
    
    //TableView của chương trình
    TableView<Sach> tableSach = new TableView<>();
    TableView<NXB> tableNXB = new TableView<>();
    TableView<TacGia> tableTG = new TableView<>();
    TableView<LoaiSach> tableLS = new TableView<>();
    TableView<MTSach> tableMT = new TableView<>();
    TableView<HoiVien> tableHV = new TableView<>();
    TableView<TaiKhoan> tableTK = new TableView<>();
    
    //Table cho 4 quý trong báo cáo
    TableView<MTSach> tableMTQ1 = new TableView<>();
    TableView<MTSach> tableMTQ2 = new TableView<>();
    TableView<MTSach> tableMTQ3 = new TableView<>();
    TableView<MTSach> tableMTQ4 = new TableView<>();
    
    //Table cho 4 quý trong báo cáo
    TableView<Sach> tableSQ1 = new TableView<>();
    TableView<Sach> tableSQ2 = new TableView<>();
    TableView<Sach> tableSQ3 = new TableView<>();
    TableView<Sach> tableSQ4 = new TableView<>();
    
    //Kết nối và statement
    Connection connection;
    Statement statement;
    
    //Table Column của Table Sách
    TableColumn colMaSach;
    TableColumn colTenSach;
    TableColumn colMaNXB;
    TableColumn colMaLS;
    TableColumn colMoTa;
    TableColumn colMaTG;
    TableColumn colNN;
    
    //Table Column của Table Nhà Xuất Bản
    TableColumn colMaNXB2;
    TableColumn colTenNXB;
    
    //Table Column của Table Tác Giả
    TableColumn colMaTG2;
    TableColumn colTenTG;
    
    //Table Column của Table Loại Sách
    TableColumn colMaLS2;
    TableColumn colTenLS;
    
    //Table Column của Table Mượn Trả Sách
    TableColumn colMaMuon;
    TableColumn colMaHV;
    TableColumn colMaSC;
    TableColumn colDateM;
    TableColumn colDateTDK;
    TableColumn colDateTTT;
    TableColumn colTT;
    TableColumn colSNT;
    TableColumn colTP;
    
    //Table Column của Table Hội Viên
    TableColumn colMaHV2;
    TableColumn colTenHV;
    
    //Table Column của Table Tài Khoản
    TableColumn colUN;
    TableColumn colPW;
    
    //Column Quý 1
    TableColumn colMaMuonQ1;
    TableColumn colMaHVQ1;
    TableColumn colMaSCQ1;
    TableColumn colDateMQ1;
    TableColumn colDateTDKQ1;
    TableColumn colDateTTTQ1;
    TableColumn colTTQ1;
    TableColumn colSNTQ1;
    TableColumn colTPQ1;
    
    //Column Quý 2
    TableColumn colMaMuonQ2;
    TableColumn colMaHVQ2;
    TableColumn colMaSCQ2;
    TableColumn colDateMQ2;
    TableColumn colDateTDKQ2;
    TableColumn colDateTTTQ2;
    TableColumn colTTQ2;
    TableColumn colSNTQ2;
    TableColumn colTPQ2;
    
    //Column Quý 3
    TableColumn colMaMuonQ3;
    TableColumn colMaHVQ3;
    TableColumn colMaSCQ3;
    TableColumn colDateMQ3;
    TableColumn colDateTDKQ3;
    TableColumn colDateTTTQ3;
    TableColumn colTTQ3;
    TableColumn colSNTQ3;
    TableColumn colTPQ3;
    
    //Column Quý 4
    TableColumn colMaMuonQ4;
    TableColumn colMaHVQ4;
    TableColumn colMaSCQ4;
    TableColumn colDateMQ4;
    TableColumn colDateTDKQ4;
    TableColumn colDateTTTQ4;
    TableColumn colTTQ4;
    TableColumn colSNTQ4;
    TableColumn colTPQ4;
    
    //Col Sách Quý 1
    TableColumn colMaSachQ1;
    TableColumn colTenSachQ1;
    TableColumn colMaNXBQ1;
    TableColumn colMaTGQ1;
    TableColumn colMaLSQ1;
    TableColumn colMoTaQ1;
    TableColumn colNNQ1;
    
    //Col Sách Quý 2
    TableColumn colMaSachQ2;
    TableColumn colTenSachQ2;
    TableColumn colMaNXBQ2;
    TableColumn colMaTGQ2;
    TableColumn colMaLSQ2;
    TableColumn colMoTaQ2;
    TableColumn colNNQ2;
    
    //Col Sách Quý 3
    TableColumn colMaSachQ3;
    TableColumn colTenSachQ3;
    TableColumn colMaNXBQ3;
    TableColumn colMaTGQ3;
    TableColumn colMaLSQ3;
    TableColumn colMoTaQ3;
    TableColumn colNNQ3;
    
    //Col Sách Quý 4
    TableColumn colMaSachQ4;
    TableColumn colTenSachQ4;
    TableColumn colMaNXBQ4;
    TableColumn colMaTGQ4;
    TableColumn colMaLSQ4;
    TableColumn colMoTaQ4;
    TableColumn colNNQ4;
    
    //Các Box chứa giao diện của các Stage
    VBox boxThuVien;
    VBox boxNXB;
    VBox boxTG;
    VBox boxLS;
    VBox boxMT;
    VBox boxHV;
    VBox boxTK;
    HBox boxMTQ;
    VBox BoxTable;
    HBox boxSQ;
    VBox BoxTable2;
    
    //Các ComboBox của chương trình
    final ComboBox<Sach> comboBox = new ComboBox(dataSach);
    final ComboBox<NXB> comboBoxNXB = new ComboBox(dataNXB);
    final ComboBox<TacGia> comboBoxTG = new ComboBox(dataTG);
    final ComboBox<LoaiSach> comboBoxLS = new ComboBox(dataLS);
    
    final ComboBox<String> comboBoxNam = new ComboBox(dataNam);
    final ComboBox<String> comboBoxNam2 = new ComboBox(dataNam2);
    
    
    //Các Label của chương trình
    Label lbMaSach;
    Label lbTenSach;
    Label lbMaNXB;
    Label lbMaTG;
    Label lbMaLS;
    Label lbMoTa;
    Label lbMaNXB2;
    Label lbTenNXB;
    Label lbMaTG2;
    Label lbTenTG;
    Label lbMaHV2;
    Label lbTenHV;
    Label lbUN;
    Label lbPW;
    
    Label lbMaLS2;
    Label lbTenLS;
    
    Label lbHVName;
    Label lbSachName;
    
    //Các TextField của chương trình
    TextField txtMaSach;
    TextField txtTenSach;
    TextField txtMaNXB;
    TextField txtMaTG;
    TextField txtMoTa;
    TextField txtMaNXB2;
    TextField txtTenNXB;
    TextField txtMaTG2;
    TextField txtTenTG;
    TextField txtMaHV2;
    TextField txtTenHV;
    TextField txtUN;
    TextField txtPW;
    
    TextField txtMaLS2;
    TextField txtTenLS;
    
    String searchSach = "";
    String searchNXB = "";
    TextField textSach;
    
    int MaMuonSave = -1;
    String SYears ="2018";
    String SYears2 ="2018";
    PieChart chartMTSach;
    PieChart chartSach;
    
    boolean ktDN = false;
    String testUsername = "dragodrex";
    String testPassword = "123";
    
    String DBLink = "jdbc:mysql://localhost/thuvien";
    String DBName = "root";
    String DBPass ="8365003";
    
    //Khởi tạo kết nối Database
    public void initDatabase() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(DBLink, DBName, DBPass);
        statement = connection.createStatement();
    }
    public boolean KTDatabase() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(DBLink, DBName, DBPass);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    public void getDatabase() throws FileNotFoundException, IOException{
            URL url = getClass().getResource("database.txt");
            File f = new File(url.getPath());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            DBLink = br.readLine();
            DBName = br.readLine();
            DBPass = br.readLine();
    }
    public void KetNoiCSDL() throws IOException{
            Stage st = new Stage();
            VBox boxDB = new VBox();
            boxDB.setSpacing(10);
            boxDB.setPadding(new Insets(10, 10, 10, 10));
            boxDB.setAlignment(Pos.CENTER);
            
            Label lb = new Label("Nhập vào thông tin kết nối Cơ Sở Dữ Liệu MySQL");
            TextField txtDBLink = new TextField();
            TextField txtDBName = new TextField();
            PasswordField txtDBPass = new PasswordField();
            
            final URL url = this.getClass().getResource("database.txt");
            final File f = new File(url.getPath());
            FileReader fr;
            try {
                fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
            
                txtDBLink.setText(br.readLine());
                txtDBName.setText(br.readLine());
                txtDBPass.setText(br.readLine());
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Button bt = new Button("Tiếp Tục");
            bt.setOnAction(e -> {
                try {
                    //System.out.println(url.getPath());
                    
                    FileWriter fw = new FileWriter(f);
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    bw.write(txtDBLink.getText());
                    bw.newLine();
                    bw.write(txtDBName.getText());
                    bw.newLine();
                    bw.write(txtDBPass.getText());
                    bw.close();
                    
                    try {
                        getDatabase();
                        if(KTDatabase()){
                            initDatabase();
                            MainLoad();
                            st.close();
                        }
                        else{
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Báo Lỗi");
                            alert.setHeaderText("Lỗi kết nối cơ sở dữ liệu");
                            alert.setContentText("Thông tin kết nối CỞ SỞ DỮ LIỆU MySQL sai. Vui lòng nhập lại");
                            alert.showAndWait();
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            
            boxDB.getChildren().addAll(lb, txtDBLink, txtDBName, txtDBPass, bt);
            
            Scene sc = new Scene(boxDB, 500, 200);
            st.setTitle("Quản Lý Thư Viện");
            st.setResizable(false);
            st.setScene(sc);
            st.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Khởi tạo kết nối database
        try{
            getDatabase();
            initDatabase();
            MainLoad();
        }
        catch(SQLException ex){
            System.out.println("Không thể kết nối");
            KetNoiCSDL();
        }
    }
    
    public void MainLoad() throws SQLException{
        //Load các dataList vào table
        tableSach.setItems(dataSach);
        tableNXB.setItems(dataNXB);
        tableTG.setItems(dataTG);
        tableLS.setItems(dataLS);
        tableMT.setItems(dataMT);
        tableHV.setItems(dataHV);
        tableTK.setItems(dataTK);
        
        //Tạo Cột
        
        
        //Load datalist cho 4 table quý trong năm
        tableMTQ1.setItems(dataMTQ1);
        tableMTQ2.setItems(dataMTQ2);
        tableMTQ3.setItems(dataMTQ3);
        tableMTQ4.setItems(dataMTQ4);
        
        //Load datalist cho 4 table quý trong năm
        tableSQ1.setItems(dataSQ1);
        tableSQ2.setItems(dataSQ2);
        tableSQ3.setItems(dataSQ3);
        tableSQ4.setItems(dataSQ4);
        
        //Load Data từ CSDL vào dataList
        LoadDataSach("select * from Sach");
        LoadDataNXB("select * from nhaxuatban");
        LoadDataTG("select * from tacgia");
        LoadDataLS("select * from loaisach");
        LoadDataMT("select * from muon_tra_sach");
        LoadDataHV("select * from hoivien");
        LoadDataTK("select * from taikhoan");
        
        LoadDataNam();
        LoadDataNam2();
        
        //Load ComboBox
        KhoiTaoComboBoxNam();
        KhoiTaoComboBoxNam2();
        
        VBox MainBox = new VBox();
        HBox Row1 = new HBox();
        HBox Row2 = new HBox();
        HBox Row3 = new HBox();
        
        //Khởi tạo các button điều hướng
        Button btSach = new Button("Quản Lý Sách");
        Button btNXB = new Button("Quản Lý Nhà Xuất Bản");
        Button btTG = new Button("Quản Lý Tác Giả");
        Button btLS = new Button("Quản Lý Loại Sách");
        Button btMT = new Button("Quản Lý Mượn Trả Sách");
        Button btHV = new Button("Quản Lý Hội Viên");
        Button btTK = new Button("Quản Lý Tài Khoản");
        Button btBC = new Button("IN BÁO CÁO");
        Button btBCQ = new Button("BÁO CÁO MƯỢN TRẢ SÁCH THEO QUÝ");
        Button btBCQ2 = new Button("BÁO CÁO NHẬP SÁCH THEO QUÝ");
        
        
        javafx.scene.image.Image img = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        ImageView imgSach = new ImageView(img);
        ImageView imgTG = new ImageView(img);
        ImageView imgLS = new ImageView(img);
        ImageView imgNXB = new ImageView(img);
        ImageView imgMT = new ImageView(img);
        ImageView imgHV = new ImageView(img);
        ImageView imgTK = new ImageView(img);
        ImageView imgBCQ1 = new ImageView(img);
        ImageView imgBCQ2 = new ImageView(img);
        
        btSach.setGraphic(imgSach);
        btNXB.setGraphic(imgNXB);
        btTG.setGraphic(imgTG);
        btLS.setGraphic(imgLS);
        btMT.setGraphic(imgMT);
        btHV.setGraphic(imgHV);
        btTK.setGraphic(imgTK);
        btBCQ.setGraphic(imgBCQ1);
        btBCQ2.setGraphic(imgBCQ2);
        
        //Button mở Stage Sách
        btSach.setOnAction(e -> {
            try {
                if(ktDN){
                    LoadSach();
                    ShowSach();
                }
                else{
                    LoadDN("sach");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        btSach.setPadding(new Insets(10, 10, 10, 10));
        
        //Button mở Stage Nhà Xuất Bản
        btNXB.setOnAction(e -> {
            if(ktDN){
                LoadNXB();
                ShowNXB();
            }
            else{
                LoadDN("NXB");
            }
        });
        btNXB.setPadding(new Insets(10, 10, 10, 10));
        
        //Button mở Stage Tác Giả
        btTG.setOnAction(e -> {
            if(ktDN){
                LoadTG();
                ShowTG();
            }
            else{
                LoadDN("TG");
            }
        });
        btTG.setPadding(new Insets(10, 10, 10, 10));
        
        //Button mở Stage Tác Giả
        btLS.setOnAction(e -> {
            if(ktDN){
                LoadLS();
                ShowLS();
            }
            else{
                LoadDN("LS");
            }
        });
        btLS.setPadding(new Insets(10, 10, 10, 10));
        
        //Button mở Stage Mượn Trả Sách
        btMT.setOnAction(e -> {
            //TinhTienPhat();
            if(ktDN){
                LoadMT();
                ShowMT();
            }
            else{
                LoadDN("MT");
            }
        });
        btMT.setPadding(new Insets(10, 10, 10, 10));
        
        //Button mở Stage Mượn Hội Viên
        btHV.setPadding(new Insets(10, 10, 10, 10));
        
        btHV.setOnAction(e -> {
            //TinhTienPhat();
            if(ktDN){
                LoadHV();
                ShowHV();
            }
            else{
                LoadDN("HV");
            } 
        });
        btTK.setPadding(new Insets(10, 10, 10, 10));
        
        btTK.setOnAction(e -> {
            if(ktDN){
                LoadTK();
                ShowTK();
            }
            else{
                LoadDN("TK");
            } 
        });
        
        //Button mở Stage In Báo Cáo
        btBC.setPadding(new Insets(10, 10, 10, 10));
        
        btBC.setOnAction(e -> {
                //PrintReport();
                LoadBC();
        });
        
        //Button mở Stage Báo Cáo Theo Quý
        btBCQ.setPadding(new Insets(10, 10, 10, 10));
        
        btBCQ.setOnAction(e -> {
            try {
                if(ktDN){
                    LoadMTQ();
                    ShowMTQ();
                }
                else{
                    LoadDN("MTQ");
                } 
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        //Button mở Stage Báo Cáo Theo Quý
        btBCQ2.setPadding(new Insets(10, 10, 10, 10));
        
        btBCQ2.setOnAction(e -> {
            try {
                if(ktDN){
                    LoadSQ();
                    ShowSQ();
                }
                else{
                    LoadDN("SQ");
                } 
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        Text txtMain = new Text("QUẢN LÝ THƯ VIỆN");
        txtMain.setStyle("-fx-font: bold 70 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        txtMain.setStroke(javafx.scene.paint.Color.WHITE);
        txtMain.setStrokeWidth(3);
        
        
        Row1.getChildren().addAll(btSach, btNXB, btTG);
        Row2.getChildren().addAll(btLS, btMT, btHV, btTK);
        Row3.getChildren().addAll(btBCQ, btBCQ2);
        MainBox.getChildren().addAll(txtMain, Row1, Row2 , Row3);
        MainBox.setPadding(new Insets(10, 10, 10, 10));
        Row1.setPadding(new Insets(30, 0, 0, 0));
        
        Row1.setSpacing(10);
        Row2.setSpacing(10);
        Row3.setSpacing(10);
        MainBox.setSpacing(10);
        
        Row1.setAlignment(Pos.CENTER);
        Row2.setAlignment(Pos.CENTER);
        Row3.setAlignment(Pos.CENTER);
        MainBox.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(MainBox, 1000, 550);
        String image = getClass().getResource("background.jpg").toString();
        MainBox.setStyle("-fx-background-image: url('" + image + "'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");
        
        stMain.setTitle("Quản Lý Thư Viện");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stMain.getIcons().add(icon);
        
        stMain.setResizable(false);
        stMain.setScene(scene);
        stMain.show();
        
        //Tắt tất cả các Stage khi tắt MainStage
        stMain.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        stSach.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stNXB.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stTG.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stLS.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stMT.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stHV.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stTK.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stBC.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stMTQ.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
        stSQ.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stMain.show();
            }
        });
    }
    
    //Load Giao diện Stage Sách
    public void LoadSach() throws SQLException, ClassNotFoundException{
        boxThuVien = new VBox();
        
        //Khởi tạo cột cho table
        initColSach();
        
        boxThuVien.setAlignment(Pos.CENTER);
        
        Text txtMain = new Text("QUẢN LÝ SÁCH");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        GridPane Gcontrol = new GridPane();
        Gcontrol.setVgap(10);
        Gcontrol.setHgap(10);
        Gcontrol.setPadding(new Insets(10, 10, 10, 10));
        
        comboBox.setPrefWidth(200);
        
        //Các label và textfield thêm xóa sửa, tìm kiếm
        lbMaSach = new Label("Mã Sách");
        lbMaSach.setAlignment(Pos.BASELINE_RIGHT);
        lbMaSach.setPrefWidth(200);
        
        txtMaSach = new TextField();
        txtMaSach.setPromptText("Nhập vào Mã Sách");
        txtMaSach.setPrefWidth(200);
        
        lbTenSach = new Label("Tên Sách");
        lbTenSach.setAlignment(Pos.BASELINE_RIGHT);
        lbTenSach.setPrefWidth(200);
        
        txtTenSach = new TextField();
        txtTenSach.setPromptText("Nhập vào Tên Sách");
        txtTenSach.setPrefWidth(200);
        
        lbMaNXB = new Label("Mã Nhà Xuất Bản");
        lbMaNXB.setAlignment(Pos.BASELINE_RIGHT);
        lbMaNXB.setPrefWidth(200);
        
        txtMaNXB = new TextField();
        txtMaNXB.setPromptText("Mã Nhà Xuất Bản");
        txtMaNXB.setPrefWidth(100);
        
        lbMaTG = new Label("Mã Tác Giả");
        lbMaTG.setAlignment(Pos.BASELINE_RIGHT);
        lbMaTG.setPrefWidth(200);
        
        txtMaTG = new TextField();
        txtMaTG.setPromptText("Mã Nhà Tác Giả");
        txtMaTG.setPrefWidth(100);
        
        lbMaLS = new Label("Mã Loại Sách");
        lbMaLS.setAlignment(Pos.BASELINE_RIGHT);
        lbMaLS.setPrefWidth(200);
        
        lbMoTa = new Label("Mô Tả");
        lbMoTa.setAlignment(Pos.BASELINE_RIGHT);
        lbMoTa.setPrefWidth(200);
        
        txtMoTa = new TextField();
        txtMoTa.setPromptText("Nhập Mô Tả cho Sách");
        txtMoTa.setPrefWidth(200);
        
        comboBoxNXB.setPrefWidth(200);
        comboBoxTG.setPrefWidth(200);
        comboBoxLS.setPrefWidth(200);
        
        //Tìm kiếm khi nhập từ khóa vào textSach
        textSach = new TextField();
        textSach.setPromptText("Tìm kiếm sách");
        textSach.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                TimKiemSach(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Button Thêm Sửa Dữ Liệu
        Button btTS = new Button();
        btTS.setPadding(new Insets(10, 10, 10, 10));
        btTS.setPrefWidth(200);
        btTS.setText("THÊM | SỬA");
        btTS.setOnAction(e -> {
            try {
                //Kiểm tra thông tin có rỗng hay không
                if(txtMaSach.getText().isEmpty() || txtTenSach.getText().isEmpty() || 
                        txtMoTa.getText().isEmpty() ||
                        comboBoxLS.getSelectionModel().isEmpty() ||
                        comboBoxNXB.getSelectionModel().isEmpty() ||
                        comboBoxTG.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống thông tin khi thêm/sửa Sách");
                    alert.showAndWait();
                    return;
                }
                //Kiểm tra mã sách đã tồn tại hay chưa
                String query = "SELECT * FROM sach WHERE ma_sach = '" + txtMaSach.getText() + "'";
                ResultSet resultTest = statement.executeQuery(query);
                String insertquery;
                
                //Nếu tồn tại rồi thì sửa còn không thì thêm mới
                if(!resultTest.next()){
                    insertquery = "INSERT INTO sach (ma_sach, tensach, ma_nxb, ma_tg, ma_ls, mota, ngay_nhap) VALUES ('" 
                            + txtMaSach.getText() + "', '" 
                            + txtTenSach.getText() + "', '"
                            + comboBoxNXB.getValue().getMaNXB() + "', '"
                            + comboBoxTG.getValue().getMaTG() + "', '"
                            + comboBoxLS.getValue().getMaLS() + "', '"
                            + txtMoTa.getText()
                            + "', NOW())";
                }
                else{
                    insertquery = "UPDATE sach SET tensach = '" + txtTenSach.getText()
                            + "', ma_nxb = '" + comboBoxNXB.getValue().getMaNXB()
                            + "', ma_tg = '" + comboBoxTG.getValue().getMaTG()
                            + "', ma_ls = '" + comboBoxLS.getValue().getMaLS()
                            + "', mota = '" + txtMoTa.getText()
                            + "' WHERE ma_sach = '" + txtMaSach.getText() + "'";
                }
                int result = statement.executeUpdate(insertquery);
                
                //Load lại dataList khi thay đổi thành công
                LoadDataSach("select * from Sach");
                
                //Xóa các textfield và comboBox
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtMaNXB.setText("");
                txtMaTG.setText("");
                txtMoTa.setText("");
                comboBoxNXB.setValue(null);
                comboBoxTG.setValue(null);
                comboBoxLS.setValue(null);
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể cập nhật được do một trong những nguyên nhân sau: "
                        + "\n- Trùng Mã Sách "
                        + "\n- Mã Nhà Xuất Bản không tồn tại "
                        + "\n- Mã Tác Giả khồng tồn tại");
                alert.showAndWait();
            }
        });
        
        //Button Xóa Dữ Liệu
        Button btDEL = new Button();
        btDEL.setPadding(new Insets(10, 10, 10, 10));
        btDEL.setPrefWidth(200);
        btDEL.setText("XÓA");
        btDEL.setOnAction(e -> {
            if(txtMaSach.getText().isEmpty()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không được bỏ trống Mã Sách");
                alert.showAndWait();
                return;
            }
            String deletequery = "DELETE FROM sach WHERE ma_sach = '" + txtMaSach.getText() + "'";
            try {
                int result = statement.executeUpdate(deletequery);
                
                LoadDataSach("select * from Sach");
                
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtMaNXB.setText("");
                txtMaTG.setText("");
                txtMoTa.setText("");
                comboBoxNXB.setValue(null);
                comboBoxTG.setValue(null);
                comboBoxLS.setValue(null);
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể xóa được");
                alert.showAndWait();
            }
        });
        
        //Khởi tạo hiển thị cho các comboBox
        KhoiTaoComboBoxSach();
        KhoiTaoComboBoxNXB();
        KhoiTaoComboBoxTG();
        KhoiTaoComboBoxLoaiSach();
        
        Gcontrol.add(lbMaSach, 0, 0);
        Gcontrol.add(txtMaSach, 1, 0);
        Gcontrol.add(lbTenSach, 0, 1);
        Gcontrol.add(txtTenSach, 1, 1);
        
        Gcontrol.add(lbMaNXB, 2, 0);
        Gcontrol.add(txtMaNXB, 3, 0);
        Gcontrol.add(lbMaTG, 2, 1);
        Gcontrol.add(txtMaTG, 3, 1);
        
        Gcontrol.add(btTS, 0, 2);
        Gcontrol.add(btDEL, 1, 2);
        
        
        //Gcontrol.add(comboBox, 2, 2);
        Gcontrol.add(comboBoxNXB, 4, 0);
        Gcontrol.add(comboBoxTG, 4, 1);
        
        Gcontrol.add(lbMoTa, 5, 0);
        Gcontrol.add(lbMaLS, 5, 1);
        Gcontrol.add(txtMoTa, 6, 0);
        Gcontrol.add(comboBoxLS, 6, 1);
        
        Label lbTK = new Label("Tìm Kiếm:");
        lbTK.setAlignment(Pos.BASELINE_RIGHT);
        lbTK.setPrefWidth(200);
        
        Gcontrol.add(lbTK, 2, 2);
        Gcontrol.add(textSach, 3, 2);
        
        //cập nhật dữ liệu xuống textfield và comboBox khi nhấn vào các dòng trên TableView
        tableSach.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                Sach sach = tableSach.getSelectionModel().getSelectedItem();
                
                txtMaSach.setText(sach.getMaSach());
                txtTenSach.setText(sach.getTenSach());
                txtMaNXB.setText(sach.getMaNXB());
                txtMaTG.setText(sach.getMaTG());
                txtMoTa.setText(sach.getMota());
                
                //comboBox.setValue(FilterSach(sach.getMaSach()));
                comboBoxNXB.setValue(FilterNXB(sach.getMaNXB()));
                comboBoxTG.setValue(FilterTG(sach.getMaTG()));
                comboBoxLS.setValue(FilterLS(sach.getMaLS()));
            }
        });
        boxThuVien.getChildren().addAll(txtMain, tableSach, Gcontrol);
        
    }
    //Load Giao Diện của Stage NXB
    public void LoadNXB(){
        boxNXB = new VBox();
        
        initColNXB();
        GridPane GridCtr = new GridPane();
        
        boxNXB.setAlignment(Pos.CENTER);
        
        Text txtMain = new Text("QUẢN LÝ NHÀ XUẤT BẢN");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        lbMaNXB2 = new Label("Mã NXB");
        lbTenNXB = new Label("Tên NXB");
        Label lbSearchNXB = new Label("Tìm Kiếm: ");
        
        txtMaNXB2 = new TextField();
        txtMaNXB2.setPromptText("Nhập vào Mã Nhà Xuât Bản");
        txtMaNXB2.setPrefWidth(200);
        
        txtTenNXB = new TextField();
        txtTenNXB.setPromptText("Nhập vào Tên Nhà Xuât Bản");
        txtTenNXB.setPrefWidth(200);
        
        TextField txtSearchNXB = new TextField();
        txtSearchNXB.setPromptText("Tìm kiếm Nhà Xuất Bản");
        
        txtSearchNXB.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                TimKiemNXB(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button btIU = new Button("THÊM | SỬA");
        Button btD = new Button("XÓA");
        
        //Khi click vào các row trong tableNXB
        tableNXB.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                NXB nxb = tableNXB.getSelectionModel().getSelectedItem();
                
                txtMaNXB2.setText(nxb.getMaNXB());
                txtTenNXB.setText(nxb.getTenNXB());
            }
        });
        btIU.setOnAction(e ->{
            try {
                if(txtMaNXB2.getText().isEmpty() || txtTenNXB.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống thông tin Nhà Xuất Bản");
                    alert.showAndWait();
                    return;
                }
                String query = "SELECT * FROM nhaxuatban WHERE ma_nxb = '" + txtMaNXB2.getText() + "'";
                ResultSet resultTest = statement.executeQuery(query);
                String insertquery;
                if(!resultTest.next()){
                    insertquery = "INSERT INTO nhaxuatban (ma_nxb, ten_nxb) VALUES ('" 
                            + txtMaNXB2.getText() + "', '" 
                            + txtTenNXB.getText() + "')";
                }
                else{
                    insertquery = "UPDATE nhaxuatban SET ten_nxb = '" + txtTenNXB.getText()
                            + "' WHERE ma_nxb = '" + txtMaNXB2.getText() + "'";
                }
                int result = statement.executeUpdate(insertquery);
                
                LoadDataNXB("select * from nhaxuatban");
                
                txtMaNXB2.setText("");
                txtTenNXB.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể cập nhật dữ liệu được vui lòng thử lại");
                alert.showAndWait();
            }
        });
         btD.setOnAction(e -> {
            if(txtMaNXB2.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống Mã Nhà Xuất Bản");
                    alert.showAndWait();
                    return;
                }
            String deletequery = "DELETE FROM nhaxuatban WHERE ma_nxb = '" + txtMaNXB2.getText() + "'";
            try {
                int result = statement.executeUpdate(deletequery);
                
                LoadDataNXB("select * from nhaxuatban");
                
                txtMaNXB2.setText("");
                txtTenNXB.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể xóa được");
                alert.showAndWait();
            }
        });
        GridCtr.setVgap(10);
        GridCtr.setHgap(10);
        
        GridCtr.add(lbMaNXB2, 0, 0);
        GridCtr.add(txtMaNXB2, 1, 0);
        GridCtr.add(lbTenNXB, 2, 0);
        GridCtr.add(txtTenNXB, 3, 0);
        GridCtr.add(btIU, 4, 0);
        GridCtr.add(btD, 5, 0);
        GridCtr.add(lbSearchNXB, 0, 1);
        GridCtr.add(txtSearchNXB, 1, 1);
        
        GridCtr.setPadding(new Insets(10, 10, 10, 10));
        
        boxNXB.setSpacing(10);
        
        boxNXB.getChildren().addAll(txtMain, tableNXB, GridCtr);
    }
    public void LoadTG(){
        boxTG = new VBox();
        
        initColTG();
        GridPane GridCtr = new GridPane();
        
        boxTG.setAlignment(Pos.CENTER);
        
        Text txtMain = new Text("QUẢN LÝ TÁC GIẢ");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        lbMaTG2 = new Label("Mã Tác Giả");
        lbTenTG = new Label("Tên Tác Giả");
        
        txtMaTG2 = new TextField();
        txtMaTG2.setPromptText("Nhập Mã Tác Giả");
        txtMaTG2.setPrefWidth(200);
        
        txtTenTG = new TextField();
        txtTenTG.setPromptText("Nhập Tên Tác Giả");
        txtTenTG.setPrefWidth(200);
        
        Label lbSearchTG = new Label("Tìm Kiếm: ");
        TextField txtSearchTG = new TextField();
        txtSearchTG.setPromptText("Tìm kiếm tác giả");
        txtSearchTG.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                TimKiemTG(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button btIU = new Button("THÊM | SỬA");
        Button btD = new Button("XÓA");
        
        tableTG.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                TacGia tg = tableTG.getSelectionModel().getSelectedItem();
                
                txtMaTG2.setText(tg.getMaTG());
                txtTenTG.setText(tg.getTenTG());
            }
        });
        btIU.setOnAction(e ->{
            try {
                if(txtMaTG2.getText().isEmpty() || txtTenTG.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống thông tin Tác Giả");
                    alert.showAndWait();
                    return;
                }
                String query = "SELECT * FROM tacgia WHERE ma_tg = '" + txtMaTG2.getText() + "'";
                ResultSet resultTest = statement.executeQuery(query);
                String insertquery;
                if(!resultTest.next()){
                    insertquery = "INSERT INTO tacgia (ma_tg, ten_tg) VALUES ('" 
                            + txtMaTG2.getText() + "', '" 
                            + txtTenTG.getText() + "')";
                }
                else{
                    insertquery = "UPDATE tacgia SET ten_tg = '" + txtTenTG.getText()
                            + "' WHERE ma_tg = '" + txtMaTG2.getText() + "'";
                }
                int result = statement.executeUpdate(insertquery);
                
                LoadDataTG("select * from tacgia");
                
                txtMaTG2.setText("");
                txtTenTG.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể cập nhật dữ liệu được vui lòng thử lại");
                alert.showAndWait();
            }
        });
        
        btD.setOnAction(e -> {
             if(txtMaTG2.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống Mã Tác Giả");
                    alert.showAndWait();
                    return;
             }
            String deletequery = "DELETE FROM tacgia WHERE ma_tg = '" + txtMaTG2.getText() + "'";
            try {
                int result = statement.executeUpdate(deletequery);
                
                LoadDataTG("select * from tacgia");
                
                txtMaTG2.setText("");
                txtTenTG.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể xóa được");
                alert.showAndWait();
            }
        });
        GridCtr.setVgap(10);
        GridCtr.setHgap(10);
        
        GridCtr.add(lbMaTG2, 0, 0);
        GridCtr.add(txtMaTG2, 1, 0);
        GridCtr.add(lbTenTG, 2, 0);
        GridCtr.add(txtTenTG, 3, 0);
        GridCtr.add(btIU, 4, 0);
        GridCtr.add(btD, 5, 0);
        GridCtr.add(lbSearchTG, 0, 1);
        GridCtr.add(txtSearchTG, 1, 1);
        
        GridCtr.setPadding(new Insets(10, 10, 10, 10));
        
        boxTG.setSpacing(10);
        
        boxTG.getChildren().addAll(txtMain, tableTG, GridCtr);
    }
    public void LoadLS(){
        boxLS = new VBox();
        
        initColLS();
        GridPane GridCtr = new GridPane();
        
        boxLS.setAlignment(Pos.CENTER);
        
        Text txtMain = new Text("QUẢN LÝ LOẠI SÁCH");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        lbMaLS2 = new Label("Mã Tác Giả");
        lbTenLS = new Label("Tên Tác Giả");
        
        
        txtMaLS2 = new TextField();
        txtMaLS2.setPromptText("Nhập Mã Loại Sách");
        txtMaLS2.setPrefWidth(200);
        
        txtTenLS = new TextField();
        txtTenLS.setPromptText("Nhập Tên Loại Sách");
        txtTenLS.setPrefWidth(200);
        
        Label lbSearchLS = new Label("Tìm Kiếm: ");
        TextField txtSearchLS = new TextField();
        txtSearchLS.setPromptText("Tìm kiếm Loại Sách");
        txtSearchLS.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                TimKiemLS(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button btIU = new Button("THÊM | SỬA");
        Button btD = new Button("XÓA");
        
        tableLS.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                LoaiSach ls = tableLS.getSelectionModel().getSelectedItem();
                
                txtMaLS2.setText(ls.getMaLS());
                txtTenLS.setText(ls.getTenLS());
            }
        });
        btIU.setOnAction(e ->{
            try {
                if(txtMaLS2.getText().isEmpty() || txtTenLS.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống thông tin Loại Sách");
                    alert.showAndWait();
                    return;
                }
                String query = "SELECT * FROM loaisach WHERE ma_ls = '" + txtMaLS2.getText() + "'";
                ResultSet resultTest = statement.executeQuery(query);
                String insertquery;
                if(!resultTest.next()){
                    insertquery = "INSERT INTO loaisach (ma_ls, ten_ls) VALUES ('" 
                            + txtMaLS2.getText() + "', '" 
                            + txtTenLS.getText() + "')";
                }
                else{
                    insertquery = "UPDATE loaisach SET ten_ls = '" + txtTenLS.getText()
                            + "' WHERE ma_ls = '" + txtMaLS2.getText() + "'";
                }
                int result = statement.executeUpdate(insertquery);
                
                LoadDataLS("select * from loaisach");
                
                txtMaLS2.setText("");
                txtTenLS.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể cập nhật dữ liệu được vui lòng thử lại");
                alert.showAndWait();
            }
        });
         btD.setOnAction(e -> {
             if(txtMaLS2.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống Mã Loại Sách");
                    alert.showAndWait();
                    return;
                }
            String deletequery = "DELETE FROM loaisach WHERE ma_ls = '" + txtMaLS2.getText() + "'";
            try {
                int result = statement.executeUpdate(deletequery);
                
                LoadDataLS("select * from loaisach");
                
                txtMaLS2.setText("");
                txtTenLS.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể xóa được");
                alert.showAndWait();
            }
        });
        GridCtr.setVgap(10);
        GridCtr.setHgap(10);
        
        GridCtr.add(lbMaLS2, 0, 0);
        GridCtr.add(txtMaLS2, 1, 0);
        GridCtr.add(lbTenLS, 2, 0);
        GridCtr.add(txtTenLS, 3, 0);
        GridCtr.add(btIU, 4, 0);
        GridCtr.add(btD, 5, 0);
        GridCtr.add(lbSearchLS, 0, 1);
        GridCtr.add(txtSearchLS, 1, 1);
        
        GridCtr.setPadding(new Insets(10, 10, 10, 10));
        
        boxLS.setSpacing(10);
        
        boxLS.getChildren().addAll(txtMain, tableLS, GridCtr);
    }
    
    String ngayMuon1 = "";
    String ngayMuon2 = "";
    public void LoadMT(){
        //TinhTienPhat();
        boxMT = new VBox();
        
        initColMT();
        GridPane GridCtr = new GridPane();
        
        boxMT.setAlignment(Pos.CENTER);
        
        Text txtMain = new Text("QUẢN LÝ LOẠI SÁCH");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);

        TextField txtMaM = new TextField();
        TextField txtMaHV = new TextField();
        TextField txtMaSC = new TextField();
        
        lbHVName = new Label("...");
        lbSachName = new Label("...");
        
        Button btMuon = new Button("MƯỢN SÁCH");
        Button btTra = new Button("TRẢ SÁCH");
        
        Label lbSearch = new Label("Tìm Kiếm: ");
        TextField txtSearch = new TextField();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                TimKiemMTText(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        DatePicker dateMuon1 = new DatePicker();
        DatePicker dateMuon2 = new DatePicker();
        
        Button btTKMuon = new Button("TÌM KIẾM THEO NGÀY MƯỢN");
        Button btRF = new Button("LÀM MỚI");
        
        btTKMuon.setPrefWidth(250);
        btRF.setPrefWidth(250);
        
        dateMuon1.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("d-M-yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
        
        dateMuon1.setOnAction(e -> {
                DateTimeFormatter format =DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate LDate = dateMuon1.getValue();
                ngayMuon1 = format.format(LDate);
        });
        dateMuon2.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("d-M-yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
        
        dateMuon2.setOnAction(e -> {
                DateTimeFormatter format =DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate LDate = dateMuon2.getValue();
                ngayMuon2 = format.format(LDate);
        });
        btTKMuon.setOnAction(e -> {
            if(ngayMuon1.isEmpty() || ngayMuon2.isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống ngày mượn sách");
                    alert.showAndWait();
                    return;
            }
            String sql = "select * from muon_tra_sach where ngay_muon >= STR_TO_DATE('"+ngayMuon1+"', '%d-%m-%Y') and ngay_muon <= STR_TO_DATE('"+ngayMuon2+"', '%d-%m-%Y')";
            try {
                LoadDataMT(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btRF.setOnAction(e -> {
            String sql = "select * from muon_tra_sach";
            try {
                LoadDataMT(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        GridCtr.add(new Label("Tên Hội Viên: "), 0, 0);
        GridCtr.add(txtMaHV, 1, 0);
        GridCtr.add(new Label("Tên Sách: "), 2, 0);
        GridCtr.add(txtMaSC, 3, 0);
        
        HBox hbox1 = new HBox();
        hbox1.setSpacing(10);
        //hbox1.setPadding(new Insets(0, 0, 10, 10));
        hbox1.getChildren().addAll(btMuon, btTra);

        GridCtr.add(hbox1, 4, 0);
        //GridCtr.add(btTra, 5, 0);
        GridCtr.add(lbHVName, 1, 1);
        GridCtr.add(lbSachName, 3, 1);
        GridCtr.add(lbSearch, 0, 2);
        GridCtr.add(txtSearch, 1, 2);
        GridCtr.add(new Label("Từ ngày:"), 2, 2);
        GridCtr.add(new Label("Đến ngày:"), 2, 3);
        GridCtr.add(dateMuon1, 3, 2);
        GridCtr.add(dateMuon2, 3, 3);
        GridCtr.add(btTKMuon, 4, 2, 2, 1);
        GridCtr.add(btRF, 4, 3);
        
        GridCtr.setVgap(10);
        GridCtr.setHgap(10);
        GridCtr.setPadding(new Insets(10, 10, 10, 10));
        
        boxMT.setSpacing(10);
        
        tableMT.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends MTSach> obs, MTSach oldSelection, MTSach newSelection) -> {
            if (newSelection != null) {
                MTSach MTsach = tableMT.getSelectionModel().getSelectedItem();
                MaMuonSave = MTsach.getMa_muon();
                txtMaHV.setText(MTsach.getMa_hoivien());
                txtMaSC.setText(MTsach.getMa_sach());
                
                
                try {
                    String queryTenSach = "SELECT tensach FROM sach WHERE ma_sach = '" + txtMaSC.getText() + "'";
                    Statement stSach = connection.createStatement();
                    stSach.executeQuery(queryTenSach);
                    ResultSet resultTestMaSach = stSach.getResultSet();
                    while(resultTestMaSach.next()){
                        lbSachName.setText(resultTestMaSach.getString("tensach"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                try {
                    String queryMaHV = "SELECT ten_hoivien FROM hoivien WHERE ma_hoivien = '"+txtMaHV.getText()+"'";
                    Statement st2 = connection.createStatement();
                    st2.executeQuery(queryMaHV);
                    ResultSet resultTestMaHV = st2.executeQuery(queryMaHV);
                    while(resultTestMaHV.next()){
                        lbHVName.setText(resultTestMaHV.getString("ten_hoivien"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        });
        btMuon.setOnAction(e -> {
            try {
                if(txtMaSC.getText().isEmpty() || txtMaHV.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống Thông Tin Hội Viên hoặc Thông Tin Sách");
                    alert.showAndWait();
                    return;
                }
                String queryMaSach = "SELECT * FROM sach WHERE ma_sach = '" + txtMaSC.getText() + "'";
                Statement st1 = connection.createStatement();
                st1.executeQuery(queryMaSach);
                ResultSet resultTestMaSach = st1.getResultSet();
                
                String queryMaHV = "SELECT * FROM hoivien WHERE ma_hoivien = '"+txtMaHV.getText()+"'";
                Statement st2 = connection.createStatement();
                st2.executeQuery(queryMaHV);
                ResultSet resultTestMaHV = st2.executeQuery(queryMaHV);
                
                String queryTenSach = "SELECT ma_sach FROM thuvien.sach WHERE tensach like '" + txtMaSC.getText() + "'";
                Statement st3 = connection.createStatement();
                st3.executeQuery(queryTenSach);
                ResultSet resultTestTenSach = st3.getResultSet();;
                
                String queryTenHV = "SELECT ma_hoivien FROM thuvien.hoivien WHERE ten_hoivien like '"+txtMaHV.getText()+"'";
                Statement st4 = connection.createStatement();
                st4.executeQuery(queryTenHV);
                ResultSet resultTestTenHV = st4.getResultSet();

                //Lấy ngày hôm nay vào calendar
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd-M-yyy");

                //set ngày mượn vào string
                String dateM = format.format(calendar.getTime());
                // cộng thêm 7 ngày để ra ngày trả sách
                calendar.add(Calendar.DATE, 7);
                String dateTDK = format.format(calendar.getTime());
                
                Boolean b1 = resultTestMaHV.next();
                Boolean b2 = resultTestMaSach.next();
                
                Boolean b3 = resultTestTenHV.next();
                Boolean b4 = resultTestTenSach.next();
                
                if(b1 && b2){
                    String insertquery = "INSERT INTO muon_tra_sach (ma_hoivien, ma_sach, ngay_muon, ngay_tra_dk, ngay_tra_tt, tinh_trang, so_ngay_tre, tien_phat) VALUES ('" 
                                + txtMaHV.getText() + "', '" 
                                + txtMaSC.getText() + "', "
                                + "STR_TO_DATE('"+dateM+"', '%d-%m-%Y'), "
                                + "STR_TO_DATE('"+dateTDK+"', '%d-%m-%Y'), "
                                + "NULL, NULL, NULL, NULL)";
                    int result = statement.executeUpdate(insertquery);
                }
                else if(b3 && b4){
                    String insertquery = "INSERT INTO muon_tra_sach (ma_hoivien, ma_sach, ngay_muon, ngay_tra_dk, ngay_tra_tt, tinh_trang, so_ngay_tre, tien_phat) VALUES ('" 
                                + resultTestTenHV.getString("ma_hoivien") + "', '" 
                                + resultTestTenSach.getString("ma_sach") + "', "
                                + "STR_TO_DATE('"+dateM+"', '%d-%m-%Y'), "
                                + "STR_TO_DATE('"+dateTDK+"', '%d-%m-%Y'), "
                                + "NULL, NULL, NULL, NULL)";
                    int result = statement.executeUpdate(insertquery);
                }
                else if (b1 && b4){
                    String insertquery = "INSERT INTO muon_tra_sach (ma_hoivien, ma_sach, ngay_muon, ngay_tra_dk, ngay_tra_tt, tinh_trang, so_ngay_tre, tien_phat) VALUES ('" 
                                + txtMaHV.getText() + "', '" 
                                + resultTestTenSach.getString("ma_sach") + "', "
                                + "STR_TO_DATE('"+dateM+"', '%d-%m-%Y'), "
                                + "STR_TO_DATE('"+dateTDK+"', '%d-%m-%Y'), "
                                + "NULL, NULL, NULL, NULL)";
                    int result = statement.executeUpdate(insertquery);
                }
                else if (b3 && b2){
                    String insertquery = "INSERT INTO muon_tra_sach (ma_hoivien, ma_sach, ngay_muon, ngay_tra_dk, ngay_tra_tt, tinh_trang, so_ngay_tre, tien_phat) VALUES ('" 
                                + resultTestTenHV.getString("ma_hoivien") + "', '" 
                                + txtMaSC.getText() + "', "
                                + "STR_TO_DATE('"+dateM+"', '%d-%m-%Y'), "
                                + "STR_TO_DATE('"+dateTDK+"', '%d-%m-%Y'), "
                                + "NULL, NULL, NULL, NULL)";
                    int result = statement.executeUpdate(insertquery);
                }
                else if(!b1 && !b3){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Hội Viên Không Tồn Tại");
                    alert.showAndWait();
                }
                else if(!b2 && !b4){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Sách Không Tồn Tại");
                    alert.showAndWait();
                }
                MaMuonSave = -1;
                txtMaHV.setText("");
                txtMaSC.setText("");
                LoadDataMT("select * from muon_tra_sach");
                
                st1.close();
                st2.close();
                st3.close();
                st4.close();
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể mượn sách được");
                alert.showAndWait();
            }
        });
        btTra.setOnAction(e -> {
            try {
                if(MaMuonSave!=-1 && !txtMaHV.getText().isEmpty() && !txtMaSC.getText().isEmpty()){
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd-M-yyy");

                    String dateTTT = format.format(calendar.getTime());
                    String insertquery = insertquery = "UPDATE muon_tra_sach "
                            + "SET ngay_tra_tt = STR_TO_DATE('"+dateTTT+"', '%d-%m-%Y')"
                            + "WHERE ma_muon = " + MaMuonSave + " and ngay_tra_tt is NULL";
                    int result = statement.executeUpdate(insertquery);
                    MaMuonSave = -1;
                    txtMaHV.setText("");
                    txtMaSC.setText("");
                    LoadDataMT("select * from muon_tra_sach");
                    TinhTienPhat();
                }
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể trả sách được");
                alert.showAndWait();
            }
        });
        if(boxMT.getChildren().size() == 0){
            boxMT.getChildren().addAll(txtMain, tableMT, GridCtr);
        }
        
    }
    public void LoadHV(){
        boxHV = new VBox();
        
        initColHV();
        GridPane GridCtr = new GridPane();
        
        boxHV.setAlignment(Pos.CENTER);
        
        Text txtMain = new Text("QUẢN LÝ HỘI VIÊN");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        lbMaHV2 = new Label("Mã Tác Giả");
        lbTenHV = new Label("Tên Tác Giả");
        
        txtMaHV2 = new TextField();
        txtMaHV2.setPromptText("Nhập vào Mã Hội Viên");
        txtMaHV2.setPrefWidth(200);
        
        txtTenHV = new TextField();
        txtTenHV.setPromptText("Nhập vào Tên Hội Viên");
        txtTenHV.setPrefWidth(200);
        
        Button btIU = new Button("THÊM | SỬA");
        Button btD = new Button("XÓA");
        
        Label lbSearch = new Label("Tìm Kiếm: ");
        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Tìm kiếm Hội Viên");
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                TimKiemHV(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        tableHV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                HoiVien tg = tableHV.getSelectionModel().getSelectedItem();
                
                txtMaHV2.setText(tg.getMaHV());
                txtTenHV.setText(tg.getTenHV());
            }
        });
        btIU.setOnAction(e ->{
            try {
                if(txtMaHV2.getText().isEmpty() || txtTenHV.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống thông tin Hội Viên");
                    alert.showAndWait();
                    return;
                }
                String query = "SELECT * FROM hoivien WHERE ma_hoivien = '" + txtMaHV2.getText() + "'";
                ResultSet resultTest = statement.executeQuery(query);
                String insertquery;
                if(!resultTest.next()){
                    insertquery = "INSERT INTO hoivien (ma_hoivien, ten_hoivien) VALUES ('" 
                            + txtMaHV2.getText() + "', '" 
                            + txtTenHV.getText() + "')";
                }
                else{
                    insertquery = "UPDATE hoivien SET ten_hoivien = '" + txtTenHV.getText()
                            + "' WHERE ma_hoivien = '" + txtMaHV2.getText() + "'";
                }
                int result = statement.executeUpdate(insertquery);
                
                LoadDataHV("select * from hoivien");
                
                txtMaHV2.setText("");
                txtTenHV.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể cập nhật dữ liệu được vui lòng thử lại");
                alert.showAndWait();
            }
        });
         btD.setOnAction(e -> {
            if(txtMaHV2.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống Mã Hội Viên");
                    alert.showAndWait();
                    return;
                }
            String deletequery = "DELETE FROM hoivien WHERE ma_hoivien = '" + txtMaHV2.getText() + "'";
            try {
                int result = statement.executeUpdate(deletequery);
                
                LoadDataHV("select * from hoivien");
                
                txtMaHV2.setText("");
                txtTenHV.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể xóa được");
                alert.showAndWait();
            }
        });
        GridCtr.setVgap(10);
        GridCtr.setHgap(10);
        
        GridCtr.add(lbMaHV2, 0, 0);
        GridCtr.add(txtMaHV2, 1, 0);
        GridCtr.add(lbTenHV, 2, 0);
        GridCtr.add(txtTenHV, 3, 0);
        GridCtr.add(btIU, 4, 0);
        GridCtr.add(btD, 5, 0);
        GridCtr.add(lbSearch, 0, 1);
        GridCtr.add(txtSearch, 1, 1);
        GridCtr.setPadding(new Insets(10, 10, 10, 10));
        
        boxHV.setSpacing(10);
        boxHV.getChildren().addAll(txtMain, tableHV, GridCtr);
    }
    public void LoadTK(){
        boxTK = new VBox();
        
        initColTK();
        GridPane GridCtr = new GridPane();
        
        boxTK.setAlignment(Pos.CENTER);
        
        Text txtMain = new Text("QUẢN LÝ TÀI KHOẢN");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        lbUN = new Label("Username");
        lbPW = new Label("Password");
        
        txtUN = new TextField();
        txtUN.setPrefWidth(200);
        txtUN.setPromptText("Nhập vào Username");
        
        txtPW = new TextField();
        txtPW.setPrefWidth(200);
        txtPW.setPromptText("Nhập vào Password");
        
        Button btIU = new Button("THÊM | SỬA");
        Button btD = new Button("XÓA");
        
        Label lbSearch = new Label("Tìm Kiếm: ");
        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Tìm kiếm Tài Khoản");
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                TimKiemTK(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        tableTK.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                TaiKhoan tk = tableTK.getSelectionModel().getSelectedItem();
                
                txtUN.setText(tk.getUsername());
                txtPW.setText(tk.getPassword());
            }
        });
        btIU.setOnAction(e ->{
            try {
                if(txtUN.getText().isEmpty() || txtPW.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống thông tin Tài Khoản");
                    alert.showAndWait();
                    return;
                }
                String query = "SELECT * FROM taikhoan WHERE username = '" + txtUN.getText() + "'";
                ResultSet resultTest = statement.executeQuery(query);
                String insertquery;
                if(!resultTest.next()){
                    insertquery = "INSERT INTO taikhoan (username, password) VALUES ('" 
                            + txtUN.getText() + "', '" 
                            + txtPW.getText() + "')";
                }
                else{
                    insertquery = "UPDATE taikhoan SET password = '" + txtPW.getText()
                            + "' WHERE username = '" + txtUN.getText() + "'";
                }
                int result = statement.executeUpdate(insertquery);
                
                LoadDataTK("select * from taikhoan");
                
                txtUN.setText("");
                txtPW.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể cập nhật dữ liệu được vui lòng thử lại");
                alert.showAndWait();
            }
        });
         btD.setOnAction(e -> {
             if(txtUN.getText().isEmpty()){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Báo Lỗi");
                    alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                    alert.setContentText("Không được bỏ trống Username");
                    alert.showAndWait();
                    return;
                }
            String deletequery = "DELETE FROM taikhoan WHERE username = '" + txtUN.getText() + "'";
            try {
                int result = statement.executeUpdate(deletequery);
                
                LoadDataTK("select * from taikhoan");
                
                txtUN.setText("");
                txtPW.setText("");
                
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Báo Lỗi");
                alert.setHeaderText("Lỗi Cập Nhật Dữ Liệu");
                alert.setContentText("Không thể xóa được");
                alert.showAndWait();
            }
        });
        GridCtr.setVgap(10);
        GridCtr.setHgap(10);
        
        GridCtr.add(lbUN, 0, 0);
        GridCtr.add(txtUN, 1, 0);
        GridCtr.add(lbPW, 2, 0);
        GridCtr.add(txtPW, 3, 0);
        GridCtr.add(btIU, 4, 0);
        GridCtr.add(btD, 5, 0);
        GridCtr.add(lbSearch, 0, 1);
        GridCtr.add(txtSearch, 1, 1);
        
        GridCtr.setPadding(new Insets(10, 10, 10, 10));
        
        boxTK.setSpacing(10);
        boxTK.getChildren().addAll(txtMain, tableTK, GridCtr);
    }
    public void LoadMTQ() throws SQLException{
        boxMTQ = new HBox();
        BoxTable = new VBox();
        VBox boxChart = new VBox();
        chartMTSach = new PieChart();
        
        TextField txtTenBC = new TextField();
        txtTenBC.setPrefWidth(200);
        txtTenBC.setPromptText("Nhập tên báo cáo");
        Pane pane = new Pane();
        pane.getChildren().add(txtTenBC);

        Text txtMain = new Text("BÁO CÁO MƯỢN TRẢ SÁCH");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        BoxTable.setSpacing(10);
        BoxTable.setPadding(new Insets(10, 10, 10, 10));
        
        
        Button btIBC = new Button("IN BÁO CÁO");
        btIBC.setPrefWidth(200);
        btIBC.setOnAction(e -> {
            try {
                PrintReportTheoQuy(txtTenBC.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BoxTable.setPrefWidth(500);
        tableMTQ1.setPrefHeight(200);
        tableMTQ2.setPrefHeight(200);
        tableMTQ3.setPrefHeight(200);
        tableMTQ4.setPrefHeight(200);
        
        Text lb1 = new Text("Quý 1");
        Text lb2 = new Text("Quý 2");
        Text lb3 = new Text("Quý 3");
        Text lb4 = new Text("Quý 4");
        
        lb1.setStyle("-fx-font: bold 20 arial;");
        lb1.setFill(javafx.scene.paint.Color.BLACK);
        lb2.setStyle("-fx-font: bold 20 arial;");
        lb2.setFill(javafx.scene.paint.Color.BLACK);
        lb3.setStyle("-fx-font: bold 20 arial;");
        lb3.setFill(javafx.scene.paint.Color.BLACK);
        lb4.setStyle("-fx-font: bold 20 arial;");
        lb4.setFill(javafx.scene.paint.Color.BLACK);
        
        initColMTQ1();
        LoadDataMTQ1();
        
        initColMTQ2();
        LoadDataMTQ2();
        
        initColMTQ3();
        LoadDataMTQ3();
        
        initColMTQ4();
        LoadDataMTQ4();
        
        //KhoiTaoComboBoxNam();
        
        comboBoxNam.getSelectionModel().selectFirst();
        comboBoxNam.setPrefWidth(200);
        comboBoxNam.setPrefHeight(50);
        //comboBoxNam.setStyle("-fx-font-size:20");
        boxChart.setSpacing(10);
        boxChart.setPadding(new Insets(10, 10, 10, 10));
        
        LoadChartMTSach();
        
        BoxTable.getChildren().addAll(lb1, tableMTQ1, lb2, tableMTQ2, lb3, tableMTQ3, lb4, tableMTQ4);
        boxChart.getChildren().addAll(txtMain, comboBoxNam, pane, btIBC, chartMTSach);
        boxMTQ.getChildren().addAll(BoxTable, boxChart);
    }
    
    public void LoadSQ() throws SQLException{
        boxSQ = new HBox();
        BoxTable2 = new VBox();
        VBox boxChart = new VBox();
        chartSach = new PieChart();
        
        TextField txtTenBC = new TextField();
        txtTenBC.setPrefWidth(200);
        txtTenBC.setPromptText("Nhập tên báo cáo");
        Pane pane = new Pane();
        pane.getChildren().add(txtTenBC);
        
        Text txtMain = new Text("BÁO CÁO NHẬP SÁCH");
        txtMain.setStyle("-fx-font: bold 50 arial;");
        txtMain.setFill(javafx.scene.paint.Color.BLACK);
        
        BoxTable2.setSpacing(10);
        BoxTable2.setPadding(new Insets(10, 10, 10, 10));
        
        Button btIBC = new Button("IN BÁO CÁO");
        btIBC.setPrefWidth(200);
        btIBC.setOnAction(e -> {
            try {
                PrintReportTheoQuy2(txtTenBC.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BoxTable2.setPrefWidth(500);
        tableSQ1.setPrefHeight(200);
        tableSQ2.setPrefHeight(200);
        tableSQ3.setPrefHeight(200);
        tableSQ4.setPrefHeight(200);
        
        Text lb1 = new Text("Quý 1");
        Text lb2 = new Text("Quý 2");
        Text lb3 = new Text("Quý 3");
        Text lb4 = new Text("Quý 4");
        
        lb1.setStyle("-fx-font: bold 20 arial;");
        lb1.setFill(javafx.scene.paint.Color.BLACK);
        lb2.setStyle("-fx-font: bold 20 arial;");
        lb2.setFill(javafx.scene.paint.Color.BLACK);
        lb3.setStyle("-fx-font: bold 20 arial;");
        lb3.setFill(javafx.scene.paint.Color.BLACK);
        lb4.setStyle("-fx-font: bold 20 arial;");
        lb4.setFill(javafx.scene.paint.Color.BLACK);
        
        initColSachBC();
        initColSachBC2();
        initColSachBC3();
        initColSachBC4();
        LoadDataSachQ1();
        LoadDataSachQ2();
        LoadDataSachQ3();
        LoadDataSachQ4();
        
        LoadChartMTSach2();
        
        //KhoiTaoComboBoxNam2();
        
        comboBoxNam2.getSelectionModel().selectFirst();
        comboBoxNam2.setPrefWidth(200);

        boxChart.setSpacing(10);
        boxChart.setPadding(new Insets(10, 10, 10, 10));
        
        BoxTable2.getChildren().addAll(lb1, tableSQ1, lb2, tableSQ2, lb3, tableSQ3, lb4, tableSQ4);
        boxChart.getChildren().addAll(txtMain, comboBoxNam2, pane, btIBC, chartSach);
        boxSQ.getChildren().addAll(BoxTable2, boxChart);
    }
    public void LoadDN(String tt){
        GridPane boxDN = new GridPane();

        TextField txtUsername = new TextField();
        txtUsername.setText(testUsername);
        PasswordField txtPassword = new PasswordField();
        txtPassword.setText(testPassword);
        
        boxDN.setAlignment(Pos.CENTER);
        
        txtUsername.setOnAction(e -> {
            txtPassword.requestFocus();
        });
        txtPassword.setOnAction(e -> {
            try {
                DangNhapAction(txtUsername, txtPassword, tt);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button bt = new Button("Đăng Nhập");
        bt.setOnAction(e -> {
            try {
                DangNhapAction(txtUsername, txtPassword, tt);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        boxDN.add(new Label("   Username: "), 0, 0);
        boxDN.add(txtUsername, 1, 0);
        boxDN.add(new Label("   Password: "), 0, 1);
        boxDN.add(txtPassword, 1, 1);
        
        boxDN.add(bt, 0, 2, 3, 1);
        boxDN.setHalignment(bt, HPos.CENTER);
        boxDN.setValignment(bt, VPos.CENTER);
        
        boxDN.setHgap(10);
        boxDN.setVgap(10);
        boxDN.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(boxDN, 350, 150);
        stDN.setTitle("Đăng Nhập");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stDN.getIcons().add(icon);
        
        stDN.setScene(scene);
        stDN.show();
    }
    public void DangNhapAction(TextField txtUsername, TextField txtPassword, String tt) throws SQLException{
        
        if(DangNhap(txtUsername.getText(), txtPassword.getText())){
                    ktDN = true;
                    stDN.hide();
                    if(tt == "sach"){
                        try {
                            LoadSach();
                            ShowSach();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(tt == "NXB"){
                        LoadNXB();
                        ShowNXB();
                    }
                    else if(tt == "TG"){
                        LoadTG();
                        ShowTG();
                    }
                    else if(tt == "LS"){
                        LoadLS();
                        ShowLS();
                    }
                    else if(tt == "MT"){
                        LoadMT();
                        ShowMT();
                    }
                    else if(tt == "HV"){
                        LoadHV();
                        ShowHV();
                    }
                    else if(tt == "MTQ"){
                        LoadMTQ();
                        ShowMTQ();
                    }
                    else if(tt == "SQ"){
                        LoadSQ();
                        ShowSQ();
                    }
                    else if(tt == "TK"){
                        LoadTK();
                        ShowTK();
                    }
                }
        else{
            
        }
    }
    public void ShowSach(){
        Scene scene = new Scene(boxThuVien, 1500, 600);
        stSach.setTitle("Quản Lý Sách");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stSach.getIcons().add(icon);
        
        stSach.setResizable(false);
        stSach.setScene(scene);
        stSach.show();
        stMain.hide();
    }
    public void ShowNXB(){
        Scene scene = new Scene(boxNXB, 1000, 600);
        stNXB.setTitle("Quản Lý NXB");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stNXB.getIcons().add(icon);
        
        stNXB.setResizable(false);
        stNXB.setScene(scene);
        stNXB.show();
        stMain.hide();
    }
    public void ShowTG(){
        Scene scene = new Scene(boxTG, 1000, 600);
        stTG.setTitle("Quản Lý NXB");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stTG.getIcons().add(icon);
        
        stTG.setResizable(false);
        stTG.setScene(scene);
        stTG.show();
        stMain.hide();
    }
    public void ShowLS(){
        Scene scene = new Scene(boxLS, 1000, 600);
        stLS.setTitle("Quản Lý Loại Sách");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stLS.getIcons().add(icon);
        
        stLS.setResizable(false);
        stLS.setScene(scene);
        stLS.show();
        stMain.hide();
    }
    public void ShowMT(){
        Scene scene = new Scene(boxMT, 1200, 650);
        stMT.setTitle("Quản Lý Mượn Trả Sách");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stMT.getIcons().add(icon);
        
        stMT.setResizable(false);
        stMT.setScene(scene);
        stMT.show();
        stMain.hide();
    }
    public void ShowHV(){
        Scene scene = new Scene(boxHV, 1200, 600);
        stHV.setTitle("Quản Lý Hội Viên");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stHV.getIcons().add(icon);
        
        stHV.setResizable(false);
        stHV.setScene(scene);
        stHV.show();
        stMain.hide();
    }
    public void ShowTK(){
        Scene scene = new Scene(boxTK, 1200, 600);
        stTK.setTitle("Quản Lý Tài Khoản");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stTK.getIcons().add(icon);
        
        stTK.setResizable(false);
        stTK.setScene(scene);
        stTK.show();
        stMain.hide();
    }
    
    public void ShowMTQ(){
        Scene scene = new Scene(boxMTQ, 1200, 1000);
        stMTQ.setTitle("Báo Cáo Mượn Trả Sách Theo Quý");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stMTQ.getIcons().add(icon);
        
        stMTQ.setResizable(false);
        stMTQ.setScene(scene);
        stMTQ.show();
        stMain.hide();
    }
    
    public void ShowSQ(){
        Scene scene = new Scene(boxSQ, 1200, 1000);
        stSQ.setTitle("Báo Cáo Nhập Sách Theo Quý");
        
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResource("Books-icon.png").toString());
        stSQ.getIcons().add(icon);
        
        stSQ.setResizable(false);
        stSQ.setScene(scene);
        stSQ.show();
        stMain.hide();
    }
    
    
    
    public void LoadDataSach(String query) throws SQLException{
        
        //String query = "SELECT * FROM sach";
        /*if(searchSach != ""){
            query += " WHERE ma_sach like '%" + searchSach + "%' "
                    + "or ten_sach like '%" +searchSach + "%'";
        }*/
        ResultSet result = statement.executeQuery(query);
        dataSach.clear();
        while(result.next()){
            Sach sach = new Sach(result.getString("ma_sach"), 
                    result.getString("tensach"), 
                    result.getString("ma_nxb"), 
                    result.getString("ma_tg"),
                    result.getString("ma_ls"),
                    result.getString("mota"),
                    result.getDate("ngay_nhap")
            );
            dataSach.add(sach);
        }
        
    }
    
    public void LoadDataNXB(String query) throws SQLException{
        //String query = "SELECT * FROM nhaxuatban";
        ResultSet result = statement.executeQuery(query);
        dataNXB.clear();
        while(result.next()){
            NXB nxb = new NXB(result.getString("ma_nxb"), 
                    result.getString("ten_nxb"));
            dataNXB.add(nxb);
        }
    }
    
    public void LoadDataTG(String query) throws SQLException{
        //String query = "SELECT * FROM tacgia";
        ResultSet result = statement.executeQuery(query);
        dataTG.clear();
        while(result.next()){
            TacGia tg = new TacGia(result.getString("ma_tg"), 
                    result.getString("ten_tg"));
            dataTG.add(tg);
        }
    }
    public void LoadDataLS(String query) throws SQLException{
        //String query = "SELECT * FROM tacgia";
        ResultSet result = statement.executeQuery(query);
        dataLS.clear();
        while(result.next()){
            LoaiSach ls = new LoaiSach(result.getString("ma_ls"), 
                    result.getString("ten_ls"));
            dataLS.add(ls);
        }
    }
    public void LoadDataMT(String query) throws SQLException{
        //TinhTienPhat();
        //String query = "SELECT * FROM muon_tra_sach";
        ResultSet result = statement.executeQuery(query);
        dataMT.clear();
        while(result.next()){
            MTSach mt = new MTSach(result.getInt("ma_muon"), 
                    result.getString("ma_hoivien"),
                    result.getString("ma_sach"),
                    result.getDate("ngay_muon"),
                    result.getDate("ngay_tra_dk"),
                    result.getDate("ngay_tra_tt"),
                    result.getString("tinh_trang"),
                    result.getInt("so_ngay_tre"),
                    result.getString("tien_phat")
            );
            dataMT.add(mt);
        }
    }
    public void LoadDataHV(String query) throws SQLException{
        //TinhTienPhat();
        //String query = "SELECT * FROM hoivien";
        ResultSet result = statement.executeQuery(query);
        dataHV.clear();
        while(result.next()){
            HoiVien hv = new HoiVien(result.getString("ma_hoivien"), 
                    result.getString("ten_hoivien")
            );
            dataHV.add(hv);
        }
    }
    public void LoadDataTK(String query) throws SQLException{
        //TinhTienPhat();
        //String query = "SELECT * FROM taikhoan";
        ResultSet result = statement.executeQuery(query);
        dataTK.clear();
        while(result.next()){
            TaiKhoan tk = new TaiKhoan(result.getString("username"), 
                    result.getString("password")
            );
            dataTK.add(tk);
        }
    }
    public void LoadDataNam() throws SQLException{
        //TinhTienPhat();
        String query = "select YEAR(ngay_muon) from muon_tra_sach group by YEAR(ngay_muon) order by YEAR(ngay_muon) DESC";
        ResultSet result = statement.executeQuery(query);
        dataNam.clear();
        while(result.next()){
            String nam = new String(result.getString(1));
            dataNam.add(nam);
        }
    }
    public void LoadDataNam2() throws SQLException{
        //TinhTienPhat();
        String query = "select YEAR(ngay_nhap) from sach group by YEAR(ngay_nhap) order by YEAR(ngay_nhap) DESC";
        ResultSet result = statement.executeQuery(query);
        dataNam2.clear();
        while(result.next()){
            String nam = new String(result.getString(1));
            dataNam2.add(nam);
        }
    }
    public void LoadDataMTQ1() throws SQLException{
        //TinhTienPhat();
        String query = "select * from muon_tra_sach where (month(ngay_muon) >= 1 and month(ngay_muon) <= 3) and year(ngay_muon) = " + SYears;
        ResultSet result = statement.executeQuery(query);
        dataMTQ1.clear();
        while(result.next()){
            MTSach mt = new MTSach(result.getInt("ma_muon"), 
                    result.getString("ma_hoivien"),
                    result.getString("ma_sach"),
                    result.getDate("ngay_muon"),
                    result.getDate("ngay_tra_dk"),
                    result.getDate("ngay_tra_tt"),
                    result.getString("tinh_trang"),
                    result.getInt("so_ngay_tre"),
                    result.getString("tien_phat")
            );
            dataMTQ1.add(mt);
        }
    }
    public void LoadDataMTQ2() throws SQLException{
        //TinhTienPhat();
        String query = "select * from muon_tra_sach where (month(ngay_muon) >= 4 and month(ngay_muon) <= 6) and year(ngay_muon) = " + SYears;
        ResultSet result = statement.executeQuery(query);
        dataMTQ2.clear();
        while(result.next()){
            MTSach mt = new MTSach(result.getInt("ma_muon"), 
                    result.getString("ma_hoivien"),
                    result.getString("ma_sach"),
                    result.getDate("ngay_muon"),
                    result.getDate("ngay_tra_dk"),
                    result.getDate("ngay_tra_tt"),
                    result.getString("tinh_trang"),
                    result.getInt("so_ngay_tre"),
                    result.getString("tien_phat")
            );
            dataMTQ2.add(mt);
        }
    }
    public void LoadDataMTQ3() throws SQLException{
        //TinhTienPhat();
        String query = "select * from muon_tra_sach where (month(ngay_muon) >= 7 and month(ngay_muon) <= 9) and year(ngay_muon) = " + SYears;
        ResultSet result = statement.executeQuery(query);
        dataMTQ3.clear();
        while(result.next()){
            MTSach mt = new MTSach(result.getInt("ma_muon"), 
                    result.getString("ma_hoivien"),
                    result.getString("ma_sach"),
                    result.getDate("ngay_muon"),
                    result.getDate("ngay_tra_dk"),
                    result.getDate("ngay_tra_tt"),
                    result.getString("tinh_trang"),
                    result.getInt("so_ngay_tre"),
                    result.getString("tien_phat")
            );
            dataMTQ3.add(mt);
        }
    }
    public void LoadDataMTQ4() throws SQLException{
        //TinhTienPhat();
        String query = "select * from muon_tra_sach where (month(ngay_muon) >= 10 and month(ngay_muon) <= 12) and year(ngay_muon) = " + SYears;
        ResultSet result = statement.executeQuery(query);
        dataMTQ4.clear();
        while(result.next()){
            MTSach mt = new MTSach(result.getInt("ma_muon"), 
                    result.getString("ma_hoivien"),
                    result.getString("ma_sach"),
                    result.getDate("ngay_muon"),
                    result.getDate("ngay_tra_dk"),
                    result.getDate("ngay_tra_tt"),
                    result.getString("tinh_trang"),
                    result.getInt("so_ngay_tre"),
                    result.getString("tien_phat")
            );
            dataMTQ4.add(mt);
        }
    }
    public void LoadDataSachQ1() throws SQLException{
        
        String query = "select * from sach where (month(ngay_nhap) >= 1 and month(ngay_nhap) <= 3) and year(ngay_nhap) = " + SYears2;

        ResultSet result = statement.executeQuery(query);
        dataSQ1.clear();
        while(result.next()){
            Sach sach = new Sach(result.getString("ma_sach"), 
                    result.getString("tensach"), 
                    result.getString("ma_nxb"), 
                    result.getString("ma_tg"),
                    result.getString("ma_ls"), 
                    result.getString("mota"),
                    result.getDate("ngay_nhap")
            );
            dataSQ1.add(sach);
        }
    }
    public void LoadDataSachQ2() throws SQLException{
        
        String query = "select * from sach where (month(ngay_nhap) >= 4 and month(ngay_nhap) <= 6) and year(ngay_nhap) = " + SYears2;

        ResultSet result = statement.executeQuery(query);
        dataSQ2.clear();
        while(result.next()){
            Sach sach = new Sach(result.getString("ma_sach"), 
                    result.getString("tensach"), 
                    result.getString("ma_nxb"), 
                    result.getString("ma_tg"),
                    result.getString("ma_ls"), 
                    result.getString("mota"),
                    result.getDate("ngay_nhap")
            );
            dataSQ2.add(sach);
        }
    }
    public void LoadDataSachQ3() throws SQLException{
        
        String query = "select * from sach where (month(ngay_nhap) >= 7 and month(ngay_nhap) <= 9) and year(ngay_nhap) = " + SYears2;

        ResultSet result = statement.executeQuery(query);
        dataSQ3.clear();
        while(result.next()){
            Sach sach = new Sach(result.getString("ma_sach"), 
                    result.getString("tensach"), 
                    result.getString("ma_nxb"), 
                    result.getString("ma_tg"),
                    result.getString("ma_ls"), 
                    result.getString("mota"),
                    result.getDate("ngay_nhap")
            );
            dataSQ3.add(sach);
        }
    }
    public void LoadDataSachQ4() throws SQLException{
        
        String query = "select * from sach where (month(ngay_nhap) >= 10 and month(ngay_nhap) <= 12) and year(ngay_nhap) = " + SYears2;

        ResultSet result = statement.executeQuery(query);
        dataSQ4.clear();
        while(result.next()){
            Sach sach = new Sach(result.getString("ma_sach"), 
                    result.getString("tensach"), 
                    result.getString("ma_nxb"), 
                    result.getString("ma_tg"),
                    result.getString("ma_ls"), 
                    result.getString("mota"),
                    result.getDate("ngay_nhap")
            );
            dataSQ4.add(sach);
        }
    }
    public void initColSach(){
        
        colMaSach = new TableColumn("Mã Sách");
        colMaSach.prefWidthProperty().bind(boxThuVien.widthProperty().divide(7));
        colMaSach.setCellValueFactory(new PropertyValueFactory("maSach"));
        
        colTenSach = new TableColumn("Tên Sách");
        colTenSach.prefWidthProperty().bind(boxThuVien.widthProperty().divide(7));
        colTenSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        
        colMaNXB = new TableColumn("Mã Nhà Xuất Bản");
        colMaNXB.prefWidthProperty().bind(boxThuVien.widthProperty().divide(7));
        colMaNXB.setCellValueFactory(new PropertyValueFactory("maNXB"));
        
        colMaTG = new TableColumn("Mã Tác Giả");
        colMaTG.prefWidthProperty().bind(boxThuVien.widthProperty().divide(7));
        colMaTG.setCellValueFactory(new PropertyValueFactory("maTG"));
        
        colMaLS = new TableColumn("Mã Loại Sách");
        colMaLS.prefWidthProperty().bind(boxThuVien.widthProperty().divide(7));
        colMaLS.setCellValueFactory(new PropertyValueFactory("maLS"));
        
        colMoTa = new TableColumn("Mô Tả");
        colMoTa.prefWidthProperty().bind(boxThuVien.widthProperty().divide(7));
        colMoTa.setCellValueFactory(new PropertyValueFactory("mota"));
        
        colNN = new TableColumn("Ngày Nhập");
        colNN.prefWidthProperty().bind(boxThuVien.widthProperty().divide(7));
        colNN.setCellValueFactory(new PropertyValueFactory("ngaynhap"));
        
        tableSach.getColumns().clear();
        tableSach.getColumns().addAll(colMaSach, colTenSach, colMaNXB, colMaTG, colMaLS, colMoTa, colNN);
    }
    
    public void initColNXB(){
        
        colMaNXB2 = new TableColumn("Mã Nhà Xuất Bản");
        colMaNXB2.prefWidthProperty().bind(boxNXB.widthProperty().divide(2));
        colMaNXB2.setCellValueFactory(new PropertyValueFactory("maNXB"));
        
        colTenNXB = new TableColumn("Tên Nhà Xuất Bản");
        colTenNXB.prefWidthProperty().bind(boxNXB.widthProperty().divide(2));
        colTenNXB.setCellValueFactory(new PropertyValueFactory("tenNXB"));
        
        tableNXB.getColumns().clear();
        tableNXB.getColumns().addAll(colMaNXB2, colTenNXB);
    }
    
    public void initColTG(){
        
        colMaTG2 = new TableColumn("Mã Tác Giả");
        colMaTG2.prefWidthProperty().bind(boxTG.widthProperty().divide(2));
        colMaTG2.setCellValueFactory(new PropertyValueFactory("maTG"));
        
        colTenTG = new TableColumn("Tên Tác Giả");
        colTenTG.prefWidthProperty().bind(boxTG.widthProperty().divide(2));
        colTenTG.setCellValueFactory(new PropertyValueFactory("tenTG"));
        
        tableTG.getColumns().clear();
        tableTG.getColumns().addAll(colMaTG2, colTenTG);
    }
    public void initColLS(){
        
        colMaLS2 = new TableColumn("Mã Loại Sách");
        colMaLS2.prefWidthProperty().bind(boxLS.widthProperty().divide(2));
        colMaLS2.setCellValueFactory(new PropertyValueFactory("maLS"));
        
        colTenLS = new TableColumn("Tên Loại Sách");
        colTenLS.prefWidthProperty().bind(boxLS.widthProperty().divide(2));
        colTenLS.setCellValueFactory(new PropertyValueFactory("tenLS"));
        
        tableLS.getColumns().clear();
        tableLS.getColumns().addAll(colMaLS2, colTenLS);
    }
    public void initColMT(){
        colMaMuon = new TableColumn("Mã Mượn");
        colMaMuon.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colMaMuon.setCellValueFactory(new PropertyValueFactory("ma_muon"));
        
        colMaHV = new TableColumn("Mã Hội Viên");
        colMaHV.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colMaHV.setCellValueFactory(new PropertyValueFactory("ma_hoivien"));

        colMaSC = new TableColumn("Mã Sách");
        colMaSC.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colMaSC.setCellValueFactory(new PropertyValueFactory("ma_sach"));
        
        colDateM = new TableColumn("Ngày Mượn");
        colDateM.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colDateM.setCellValueFactory(new PropertyValueFactory("ngay_muon"));
        
        colDateTDK = new TableColumn("Ngày Trả Dự Kiến");
        colDateTDK.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colDateTDK.setCellValueFactory(new PropertyValueFactory("ngay_tra_dk"));
        
        colDateTTT = new TableColumn("Ngày Trả Thực Tế");
        colDateTTT.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colDateTTT.setCellValueFactory(new PropertyValueFactory("ngay_tra_tt"));
        
        colTT = new TableColumn("Tình Trạng");
        colTT.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colTT.setCellValueFactory(new PropertyValueFactory("tinh_trang"));
        
        colSNT = new TableColumn("Số Ngày Trễ");
        colSNT.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colSNT.setCellValueFactory(new PropertyValueFactory("so_ngay_tre"));
        
        colTP = new TableColumn("Tiền Phạt");
        colTP.prefWidthProperty().bind(boxMT.widthProperty().divide(7));
        colTP.setCellValueFactory(new PropertyValueFactory("tien_phat"));
        
        tableMT.getColumns().clear();
        tableMT.getColumns().addAll(colMaMuon, colMaHV, colMaSC, colDateM, colDateTDK, colDateTTT, colTT, colSNT, colTP);
    }
    public void initColHV(){
        
        colMaHV2 = new TableColumn("Mã Hội Viên");
        colMaHV2.prefWidthProperty().bind(boxHV.widthProperty().divide(2));
        colMaHV2.setCellValueFactory(new PropertyValueFactory("MaHV"));
        
        colTenHV = new TableColumn("Tên Hội Viên");
        colTenHV.prefWidthProperty().bind(boxHV.widthProperty().divide(2));
        colTenHV.setCellValueFactory(new PropertyValueFactory("TenHV"));
        
        tableHV.getColumns().clear();
        tableHV.getColumns().addAll(colMaHV2, colTenHV);
    }
    public void initColTK(){
        
        colUN = new TableColumn("Tên Tài Khoản");
        colUN.prefWidthProperty().bind(boxTK.widthProperty().divide(2));
        colUN.setCellValueFactory(new PropertyValueFactory("username"));
        
        colPW = new TableColumn("Mật Khẩu");
        colPW.prefWidthProperty().bind(boxTK.widthProperty().divide(2));
        colPW.setCellValueFactory(new PropertyValueFactory("password"));
        
        tableTK.getColumns().clear();
        tableTK.getColumns().addAll(colUN, colPW);
    }
    public void initColMTQ1(){
        
        colMaMuonQ1 = new TableColumn("Mã Mượn");
        colMaMuonQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaMuonQ1.setCellValueFactory(new PropertyValueFactory("ma_muon"));
        
        colMaHVQ1 = new TableColumn("Mã Hội Viên");
        colMaHVQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaHVQ1.setCellValueFactory(new PropertyValueFactory("ma_hoivien"));

        colMaSCQ1 = new TableColumn("Mã Sách");
        colMaSCQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaSCQ1.setCellValueFactory(new PropertyValueFactory("ma_sach"));
        
        colDateMQ1 = new TableColumn("Ngày Mượn");
        colDateMQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateMQ1.setCellValueFactory(new PropertyValueFactory("ngay_muon"));
        
        colDateTDKQ1 = new TableColumn("Ngày Trả Dự Kiến");
        colDateTDKQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTDKQ1.setCellValueFactory(new PropertyValueFactory("ngay_tra_dk"));
        
        colDateTTTQ1 = new TableColumn("Ngày Trả Thực Tế");
        colDateTTTQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTTTQ1.setCellValueFactory(new PropertyValueFactory("ngay_tra_tt"));
        
        colTTQ1 = new TableColumn("Tình Trạng");
        colTTQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTTQ1.setCellValueFactory(new PropertyValueFactory("tinh_trang"));
        
        colSNTQ1 = new TableColumn("Số Ngày Trễ");
        colSNTQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colSNTQ1.setCellValueFactory(new PropertyValueFactory("so_ngay_tre"));
        
        colTPQ1 = new TableColumn("Tiền Phạt");
        colTPQ1.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTPQ1.setCellValueFactory(new PropertyValueFactory("tien_phat"));
        
        tableMTQ1.getColumns().clear();
        tableMTQ1.getColumns().addAll(colMaMuonQ1, colMaHVQ1, colMaSCQ1, colDateMQ1, colDateTDKQ1, colDateTTTQ1, colTTQ1, colSNTQ1, colTPQ1);
    }
    public void initColMTQ2(){
        
        colMaMuonQ2 = new TableColumn("Mã Mượn");
        colMaMuonQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaMuonQ2.setCellValueFactory(new PropertyValueFactory("ma_muon"));
        
        colMaHVQ2 = new TableColumn("Mã Hội Viên");
        colMaHVQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaHVQ2.setCellValueFactory(new PropertyValueFactory("ma_hoivien"));

        colMaSCQ2 = new TableColumn("Mã Sách");
        colMaSCQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaSCQ2.setCellValueFactory(new PropertyValueFactory("ma_sach"));
        
        colDateMQ2 = new TableColumn("Ngày Mượn");
        colDateMQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateMQ2.setCellValueFactory(new PropertyValueFactory("ngay_muon"));
        
        colDateTDKQ2 = new TableColumn("Ngày Trả Dự Kiến");
        colDateTDKQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTDKQ2.setCellValueFactory(new PropertyValueFactory("ngay_tra_dk"));
        
        colDateTTTQ2 = new TableColumn("Ngày Trả Thực Tế");
        colDateTTTQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTTTQ2.setCellValueFactory(new PropertyValueFactory("ngay_tra_tt"));
        
        colTTQ2 = new TableColumn("Tình Trạng");
        colTTQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTTQ2.setCellValueFactory(new PropertyValueFactory("tinh_trang"));
        
        colSNTQ2 = new TableColumn("Số Ngày Trễ");
        colSNTQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colSNTQ2.setCellValueFactory(new PropertyValueFactory("so_ngay_tre"));
        
        colTPQ2 = new TableColumn("Tiền Phạt");
        colTPQ2.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTPQ2.setCellValueFactory(new PropertyValueFactory("tien_phat"));
        
        tableMTQ2.getColumns().clear();
        tableMTQ2.getColumns().addAll(colMaMuonQ2, colMaHVQ2, colMaSCQ2, colDateMQ2, colDateTDKQ2, colDateTTTQ2, colTTQ2, colSNTQ2, colTPQ2);
    }
    public void initColMTQ3(){
        
        colMaMuonQ3 = new TableColumn("Mã Mượn");
        colMaMuonQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaMuonQ3.setCellValueFactory(new PropertyValueFactory("ma_muon"));
        
        colMaHVQ3 = new TableColumn("Mã Hội Viên");
        colMaHVQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaHVQ3.setCellValueFactory(new PropertyValueFactory("ma_hoivien"));

        colMaSCQ3 = new TableColumn("Mã Sách");
        colMaSCQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaSCQ3.setCellValueFactory(new PropertyValueFactory("ma_sach"));
        
        colDateMQ3 = new TableColumn("Ngày Mượn");
        colDateMQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateMQ3.setCellValueFactory(new PropertyValueFactory("ngay_muon"));
        
        colDateTDKQ3 = new TableColumn("Ngày Trả Dự Kiến");
        colDateTDKQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTDKQ3.setCellValueFactory(new PropertyValueFactory("ngay_tra_dk"));
        
        colDateTTTQ3 = new TableColumn("Ngày Trả Thực Tế");
        colDateTTTQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTTTQ3.setCellValueFactory(new PropertyValueFactory("ngay_tra_tt"));
        
        colTTQ3 = new TableColumn("Tình Trạng");
        colTTQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTTQ3.setCellValueFactory(new PropertyValueFactory("tinh_trang"));
        
        colSNTQ3 = new TableColumn("Số Ngày Trễ");
        colSNTQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colSNTQ3.setCellValueFactory(new PropertyValueFactory("so_ngay_tre"));
        
        colTPQ3 = new TableColumn("Tiền Phạt");
        colTPQ3.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTPQ3.setCellValueFactory(new PropertyValueFactory("tien_phat"));
        
        tableMTQ3.getColumns().clear();
        tableMTQ3.getColumns().addAll(colMaMuonQ3, colMaHVQ3, colMaSCQ3, colDateMQ3, colDateTDKQ3, colDateTTTQ3, colTTQ3, colSNTQ3, colTPQ3);
    }
    public void initColMTQ4(){
        
        colMaMuonQ4 = new TableColumn("Mã Mượn");
        colMaMuonQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaMuonQ4.setCellValueFactory(new PropertyValueFactory("ma_muon"));
        
        colMaHVQ4 = new TableColumn("Mã Hội Viên");
        colMaHVQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaHVQ4.setCellValueFactory(new PropertyValueFactory("ma_hoivien"));

        colMaSCQ4 = new TableColumn("Mã Sách");
        colMaSCQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colMaSCQ4.setCellValueFactory(new PropertyValueFactory("ma_sach"));
        
        colDateMQ4 = new TableColumn("Ngày Mượn");
        colDateMQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateMQ4.setCellValueFactory(new PropertyValueFactory("ngay_muon"));
        
        colDateTDKQ4 = new TableColumn("Ngày Trả Dự Kiến");
        colDateTDKQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTDKQ4.setCellValueFactory(new PropertyValueFactory("ngay_tra_dk"));
        
        colDateTTTQ4 = new TableColumn("Ngày Trả Thực Tế");
        colDateTTTQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colDateTTTQ4.setCellValueFactory(new PropertyValueFactory("ngay_tra_tt"));
        
        colTTQ4 = new TableColumn("Tình Trạng");
        colTTQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTTQ4.setCellValueFactory(new PropertyValueFactory("tinh_trang"));
        
        colSNTQ4 = new TableColumn("Số Ngày Trễ");
        colSNTQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colSNTQ4.setCellValueFactory(new PropertyValueFactory("so_ngay_tre"));
        
        colTPQ4 = new TableColumn("Tiền Phạt");
        colTPQ4.prefWidthProperty().bind(BoxTable.widthProperty().divide(2));
        colTPQ4.setCellValueFactory(new PropertyValueFactory("tien_phat"));
        
        tableMTQ4.getColumns().clear();
        tableMTQ4.getColumns().addAll(colMaMuonQ4, colMaHVQ4, colMaSCQ4, colDateMQ4, colDateTDKQ4, colDateTTTQ4, colTTQ4, colSNTQ4, colTPQ4);
    }
    public void initColSachBC(){
        
        colMaSachQ1 = new TableColumn("Mã Sách");
        colMaSachQ1.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaSachQ1.setCellValueFactory(new PropertyValueFactory("maSach"));
        
        colTenSachQ1 = new TableColumn("Tên Sách");
        colTenSachQ1.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colTenSachQ1.setCellValueFactory(new PropertyValueFactory("tenSach"));
        
        colMaNXBQ1 = new TableColumn("Mã Nhà Xuất Bản");
        colMaNXBQ1.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaNXBQ1.setCellValueFactory(new PropertyValueFactory("maNXB"));
        
        colMaTGQ1 = new TableColumn("Mã Tác Giả");
        colMaTGQ1.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaTGQ1.setCellValueFactory(new PropertyValueFactory("maTG"));
        
        colMaLSQ1 = new TableColumn("Mã Loại Sách");
        colMaLSQ1.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaLSQ1.setCellValueFactory(new PropertyValueFactory("maLS"));
        
        colMoTaQ1 = new TableColumn("Mô Tả");
        colMoTaQ1.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMoTaQ1.setCellValueFactory(new PropertyValueFactory("mota"));
        
        colNNQ1 = new TableColumn("Ngày Nhập");
        colNNQ1.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colNNQ1.setCellValueFactory(new PropertyValueFactory("ngaynhap"));
        
        tableSQ1.getColumns().clear();
        tableSQ1.getColumns().addAll(colMaSachQ1, colTenSachQ1, colMaNXBQ1, colMaTGQ1, colMaLSQ1, colMoTaQ1, colNNQ1);
        
    }
    public void initColSachBC2(){
        
        colMaSachQ2 = new TableColumn("Mã Sách");
        colMaSachQ2.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaSachQ2.setCellValueFactory(new PropertyValueFactory("maSach"));
        
        colTenSachQ2 = new TableColumn("Tên Sách");
        colTenSachQ2.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colTenSachQ2.setCellValueFactory(new PropertyValueFactory("tenSach"));
        
        colMaNXBQ2 = new TableColumn("Mã Nhà Xuất Bản");
        colMaNXBQ2.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaNXBQ2.setCellValueFactory(new PropertyValueFactory("maNXB"));
        
        colMaTGQ2 = new TableColumn("Mã Tác Giả");
        colMaTGQ2.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaTGQ2.setCellValueFactory(new PropertyValueFactory("maTG"));
        
        colMaLSQ2 = new TableColumn("Mã Loại Sách");
        colMaLSQ2.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaLSQ2.setCellValueFactory(new PropertyValueFactory("maLS"));
        
        colMoTaQ2 = new TableColumn("Mô Tả");
        colMoTaQ2.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMoTaQ2.setCellValueFactory(new PropertyValueFactory("mota"));
        
        colNNQ2 = new TableColumn("Ngày Nhập");
        colNNQ2.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colNNQ2.setCellValueFactory(new PropertyValueFactory("ngaynhap"));
        
        tableSQ2.getColumns().clear();
        tableSQ2.getColumns().addAll(colMaSachQ2, colTenSachQ2, colMaNXBQ2, colMaTGQ2, colMaLSQ2, colMoTaQ2, colNNQ2);
        
    }
    public void initColSachBC3(){
        
        colMaSachQ3 = new TableColumn("Mã Sách");
        colMaSachQ3.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaSachQ3.setCellValueFactory(new PropertyValueFactory("maSach"));
        
        colTenSachQ3 = new TableColumn("Tên Sách");
        colTenSachQ3.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colTenSachQ3.setCellValueFactory(new PropertyValueFactory("tenSach"));
        
        colMaNXBQ3 = new TableColumn("Mã Nhà Xuất Bản");
        colMaNXBQ3.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaNXBQ3.setCellValueFactory(new PropertyValueFactory("maNXB"));
        
        colMaTGQ3 = new TableColumn("Mã Tác Giả");
        colMaTGQ3.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaTGQ3.setCellValueFactory(new PropertyValueFactory("maTG"));
        
        colMaLSQ3 = new TableColumn("Mã Loại Sách");
        colMaLSQ3.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaLSQ3.setCellValueFactory(new PropertyValueFactory("maLS"));
        
        colMoTaQ3 = new TableColumn("Mô Tả");
        colMoTaQ3.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMoTaQ3.setCellValueFactory(new PropertyValueFactory("mota"));
        
        colNNQ3 = new TableColumn("Ngày Nhập");
        colNNQ3.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colNNQ3.setCellValueFactory(new PropertyValueFactory("ngaynhap"));
        
        tableSQ3.getColumns().clear();
        tableSQ3.getColumns().addAll(colMaSachQ3, colTenSachQ3, colMaNXBQ3, colMaTGQ3, colMaLSQ3, colMoTaQ3, colNNQ3);
    }
    public void initColSachBC4(){
        
        colMaSachQ4 = new TableColumn("Mã Sách");
        colMaSachQ4.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaSachQ4.setCellValueFactory(new PropertyValueFactory("maSach"));
        
        colTenSachQ4 = new TableColumn("Tên Sách");
        colTenSachQ4.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colTenSachQ4.setCellValueFactory(new PropertyValueFactory("tenSach"));
        
        colMaNXBQ4 = new TableColumn("Mã Nhà Xuất Bản");
        colMaNXBQ4.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaNXBQ4.setCellValueFactory(new PropertyValueFactory("maNXB"));
        
        colMaTGQ4 = new TableColumn("Mã Tác Giả");
        colMaTGQ4.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaTGQ4.setCellValueFactory(new PropertyValueFactory("maTG"));
        
        colMaLSQ4 = new TableColumn("Mã Loại Sách");
        colMaLSQ4.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMaLSQ4.setCellValueFactory(new PropertyValueFactory("maLS"));
        
        colMoTaQ4 = new TableColumn("Mô Tả");
        colMoTaQ4.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colMoTaQ4.setCellValueFactory(new PropertyValueFactory("mota"));
        
        colNNQ4 = new TableColumn("Ngày Nhập");
        colNNQ4.prefWidthProperty().bind(BoxTable2.widthProperty().divide(2));
        colNNQ4.setCellValueFactory(new PropertyValueFactory("ngaynhap"));
        
        tableSQ4.getColumns().clear();
        tableSQ4.getColumns().addAll(colMaSachQ4, colTenSachQ4, colMaNXBQ4, colMaTGQ4, colMaLSQ4, colMoTaQ4, colNNQ4);
    }
    public void KhoiTaoComboBoxSach(){
        
        //Set Show Selected Item
        comboBox.setCellFactory(new Callback<ListView<Sach>, ListCell<Sach>>() {
            @Override
            public ListCell<Sach> call(ListView<Sach> p) {
                final ListCell<Sach> cell = new ListCell<Sach>() {
                    @Override
                    public void updateItem(Sach t, boolean bln) {
                            super.updateItem(t, bln);
                            if (t != null) {
                                setText(t.getMaSach() + ": " + t.getTenSach());
                            }
                            else {
                                setText(null);
                            }
                        }
                    };
                    return cell;
            }
        });
        
        
        
        
        //set Get Items
        comboBox.setConverter(new StringConverter<Sach>() {
              @Override
              public String toString(Sach sach) {
                  return sach.getTenSach();
              }

            @Override
            public Sach fromString(String id) {
                Sach TSach = null;
                for(Sach temp:comboBox.getItems()){
                    if(temp.getMaSach().equals(id)){
                        TSach = temp;
                    }
                }
                return TSach;
            }
        });
        
        //Value change event
        comboBox.valueProperty().addListener(new ChangeListener<Sach>() {
            @Override
            public void changed(ObservableValue<? extends Sach> ov, Sach t, Sach t1) {
                //Sach sachCB = (Sach) comboBox.getSelectionModel().getSelectedItem();
                
                /*Sach sachCB = comboBox.getValue();
                if(sachCB != null){
                    System.out.println(sachCB.getMaSach());
                }*/
                
                if(t1 != null){
                    txtMaSach.setText(t1.getMaSach());
                }
                else{
                    txtMaSach.setText("");
                }
                
                
            }
        });
    }
    public void KhoiTaoComboBoxNXB(){
        
        //Set Show Selected Item
        comboBoxNXB.setCellFactory(new Callback<ListView<NXB>, ListCell<NXB>>(){
            @Override
            public ListCell<NXB> call(ListView<NXB> p) {
                 
                final ListCell<NXB> cell = new ListCell<NXB>(){
 
                    @Override
                    protected void updateItem(NXB t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getMaNXB() + ": " + t.getTenNXB());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        
        //set Get Items
        comboBoxNXB.setConverter(new StringConverter<NXB>() {
              @Override
              public String toString(NXB nxb) {
                  return nxb.getTenNXB();
              }

            @Override
            public NXB fromString(String id) {
                return null;
            }
        });
        
        //Value change event
        comboBoxNXB.valueProperty().addListener(new ChangeListener<NXB>() {
            @Override
            public void changed(ObservableValue<? extends NXB> ov, NXB t, NXB t1) {
                //Sach sachCB = (Sach) comboBox.getSelectionModel().getSelectedItem();
                
                /*NXB NXBt = comboBoxNXB.getValue();
                if(NXBt != null){
                    System.out.println(NXBt.getMaNXB());
                }*/
                
                if(t1 != null){
                    txtMaNXB.setText(t1.getMaNXB());
                }
                else{
                    txtMaNXB.setText("");
                }
            }
        });
    }
    
    public void KhoiTaoComboBoxTG(){
        
        //Set Show Selected Item
        comboBoxTG.setCellFactory(new Callback<ListView<TacGia>, ListCell<TacGia>>(){
            @Override
            public ListCell<TacGia> call(ListView<TacGia> p) {
                 
                final ListCell<TacGia> cell = new ListCell<TacGia>(){
 
                    @Override
                    public void updateItem(TacGia t, boolean bln) {
                        super.updateItem(t, bln);
                        if(t != null){
                            setText(t.getMaTG() + ": " + t.getTenTG());
                        }
                        else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        
        //set Get Items
        comboBoxTG.setConverter(new StringConverter<TacGia>() {
              @Override
              public String toString(TacGia tg) {
                  return tg.getTenTG();
              }

            @Override
            public TacGia fromString(String id) {
                return null;
            }
        });
        
        //Value change event
        comboBoxTG.valueProperty().addListener(new ChangeListener<TacGia>() {
            @Override
            public void changed(ObservableValue<? extends TacGia> ov, TacGia t, TacGia t1) {
                //Sach sachCB = (Sach) comboBox.getSelectionModel().getSelectedItem();
                
                /*TacGia TGt = comboBoxTG.getValue();
                if(TGt != null){
                    System.out.println(TGt.getMaTG());
                }*/
                if(t1 != null){
                    txtMaTG.setText(t1.getMaTG());
                }
                else{
                    txtMaTG.setText("");
                }
            }
        });
    }
    
    public void KhoiTaoComboBoxLoaiSach(){
        
        //Set Show Selected Item
        comboBoxLS.setCellFactory(new Callback<ListView<LoaiSach>, ListCell<LoaiSach>>() {
            @Override
            public ListCell<LoaiSach> call(ListView<LoaiSach> p) {
                final ListCell<LoaiSach> cell = new ListCell<LoaiSach>() {
                    @Override
                    public void updateItem(LoaiSach t, boolean bln) {
                            super.updateItem(t, bln);
                            if (t != null) {
                                setText(t.getMaLS() + ": " + t.getTenLS());
                            }
                            else {
                                setText(null);
                            }
                        }
                    };
                    return cell;
            }
        });
        
        
        
        
        //set Get Items
        comboBoxLS.setConverter(new StringConverter<LoaiSach>() {
              @Override
              public String toString(LoaiSach ls) {
                  return ls.getTenLS();
              }

            @Override
            public LoaiSach fromString(String id) {
                LoaiSach LSach = null;
                for(LoaiSach temp:comboBoxLS.getItems()){
                    if(temp.getMaLS().equals(id)){
                        LSach = temp;
                    }
                }
                return LSach;
            }
        });
        
        //Value change event
        comboBox.valueProperty().addListener(new ChangeListener<Sach>() {
            @Override
            public void changed(ObservableValue<? extends Sach> ov, Sach t, Sach t1) {
                //Sach sachCB = (Sach) comboBox.getSelectionModel().getSelectedItem();
                
                /*Sach sachCB = comboBox.getValue();
                if(sachCB != null){
                    System.out.println(sachCB.getMaSach());
                }*/
                
                /*if(t1 != null){
                    txtMaSach.setText(t1.getMaSach());
                }
                else{
                    txtMaSach.setText("");
                }*/
            }
        });
    }
    
    public void KhoiTaoComboBoxNam(){
        
        //Set Show Selected Item

        comboBoxNam.setButtonCell(new ListCell<String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    setStyle("-fx-font-size:25");
                    //setAlignment(Pos.CENTER);
                }
            }
        });
        comboBoxNam.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                final ListCell<String> cell = new ListCell<String>() {
                    @Override
                    public void updateItem(String t, boolean bln) {
                            super.updateItem(t, bln);
                            if (t != null) {
                                setText(t);
                                setStyle("-fx-font-size:25");
                            }
                            else {
                                setStyle("-fx-font-size:25");
                                setText(null);
                            }
                        }
                    };
                    return cell;
            }
        });
        
        //set Get Items
        comboBoxNam.setConverter(new StringConverter<String>() {
              @Override
              public String toString(String nam) {
                  return nam;
              }

            @Override
            public String fromString(String id) {
                String Nam = null;
                for(String temp:comboBoxNam.getItems()){
                    if(temp.equals(id)){
                        Nam = temp;
                    }
                }
                return Nam;
            }
        });
        
        //Value change event
        comboBoxNam.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {             
                if(t1 != null){
                    SYears = t1;
                }
                else{
                    SYears = "2018";
                }
                try {
                    LoadDataMTQ1();
                    LoadDataMTQ2();
                    LoadDataMTQ3();
                    LoadDataMTQ4();
                    LoadChartMTSach();
                } catch (SQLException ex) {
                    Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void KhoiTaoComboBoxNam2(){
        
        //Set Show Selected Item
        /*comboBoxNam2.setCellFactory(new Callback<ListView<String>, ListCell<String>>(){
            @Override
            public ListCell<String> call(ListView<String> p) {
                 
                final ListCell<String> cell = new ListCell<String>(){
 
                    @Override
                    protected void updateItem(String t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.toString());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });*/
        
        comboBoxNam2.setButtonCell(new ListCell<String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    setStyle("-fx-font-size:25");
                    //setAlignment(Pos.CENTER);
                }
            }
        });
        comboBoxNam2.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item);
                                //setAlignment(Pos.CENTER);
                                setStyle("-fx-font-size:25");
                            }
                            else {
                                setStyle("-fx-font-size:25");
                                setText(null);
                            }
                        }
                    };
                    return cell;
            }
        });
        
        //set Get Items
        comboBoxNam2.setConverter(new StringConverter<String>() {
              @Override
              public String toString(String nam) {
                  return nam;
              }

            @Override
            public String fromString(String id) {
                String Nam = null;
                for(String temp:comboBoxNam2.getItems()){
                    if(temp.equals(id)){
                        Nam = temp;
                    }
                }
                return Nam;
            }
        });
        
        //Value change event
        comboBoxNam2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {             
                if(t1 != null){
                    SYears2 = t1;
                }
                else{
                    SYears2 = "2018";
                }
                try {
                    LoadDataSachQ1();
                    LoadDataSachQ2();
                    LoadDataSachQ3();
                    LoadDataSachQ4();
                    LoadChartMTSach2();
                } catch (SQLException ex) {
                    Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public Sach FilterSach(String id){
        for(Sach sach:comboBox.getItems()){
            if(sach.getMaSach().equals(id)){
                return sach;
            }
        }
        return null;
    }
    public NXB FilterNXB(String id){
        for(NXB nxb:comboBoxNXB.getItems()){
            if(nxb.getMaNXB().equals(id)){
                return nxb;
            }
        }
        return null;
    }
    public TacGia FilterTG(String id){
        for(TacGia tg:comboBoxTG.getItems()){
            if(tg.getMaTG().equals(id)){
                return tg;
            }
        }
        return null;
    }
    public LoaiSach FilterLS(String id){
        for(LoaiSach ls:comboBoxLS.getItems()){
            if(ls.getMaLS().equals(id)){
                return ls;
            }
        }
        return null;
    }
    public void TimKiemSach(String search) throws SQLException{
        String query = "SELECT * FROM sach";
        if(search != ""){
            query += " WHERE ma_sach like '%" + search + "%' "
                    + "or tensach like '%" + search + "%' "
                    + "or ma_nxb like '%" + search + "%' "
                    + "or ma_tg like '%" + search + "%' "
                    + "or ma_ls like '%" + search + "%' "
                    + "or mota like '%" + search + "%' "
                    + "or ma_nxb in (select ma_nxb from nhaxuatban where ten_nxb like '%"+search+"%') "
                    + "or ma_tg in (select ma_tg from tacgia where ten_tg like '%"+search+"%')"
                    + "or ma_ls in (select ma_ls from loaisach where ten_ls like '%"+search+"%')";
        }
        /*ResultSet result = statement.executeQuery(query);
        dataSach.clear();
        while(result.next()){
            Sach sach = new Sach(result.getString("ma_sach"), 
                    result.getString("tensach"), 
                    result.getString("ma_nxb"), 
                    result.getString("ma_tg"),
                    result.getDate("ngay_nhap")
            );
            dataSach.add(sach);
        }*/
        LoadDataSach(query);
    }
    public void TimKiemNXB(String search) throws SQLException{
        String query = "SELECT * FROM nhaxuatban";
        if(search != ""){
            query += " WHERE ma_nxb like '%" + search + "%' "
                    + "or ten_nxb like '%" + search + "%' ";
        }
        LoadDataNXB(query);
    }
    public void TimKiemTG(String search) throws SQLException{
        String query = "SELECT * FROM tacgia";
        if(search != ""){
            query += " WHERE ma_tg like '%" + search + "%' "
                    + "or ten_tg like '%" + search + "%' ";
        }
        LoadDataTG(query);
    }
    public void TimKiemLS(String search) throws SQLException{
        String query = "SELECT * FROM loaisach";
        if(search != ""){
            query += " WHERE ma_ls like '%" + search + "%' "
                    + "or ten_ls like '%" + search + "%' ";
        }
        LoadDataLS(query);
    }
    public void TimKiemHV(String search) throws SQLException{
        String query = "SELECT * FROM hoivien";
        if(search != ""){
            query += " WHERE ma_hoivien like '%" + search + "%' "
                    + "or ten_hoivien like '%" + search + "%' ";
        }
        LoadDataHV(query);
    }
    public void TimKiemTK(String search) throws SQLException{
        String query = "SELECT * FROM taikhoan";
        if(search != ""){
            query += " WHERE username like '%" + search + "%' "
                    + "or password like '%" + search + "%' ";
        }
        LoadDataTK(query);
    }
    public void TimKiemMTText(String search) throws SQLException{
        String query = "SELECT * FROM muon_tra_sach";
        if(search != ""){
            query += " WHERE ma_sach like '%" + search + "%' "
                    + "or ma_hoivien like '%" + search + "%' "
                    + "or ma_sach in (select ma_sach from sach where tensach like '%"+search+"%') "
                    + "or ma_hoivien in (select ma_hoivien from hoivien where ten_hoivien like '%"+search+"%')";
        }
        LoadDataMT(query);
    }
    public void TinhTienPhat(){
        try {
            for(MTSach mt:dataMT){
                if(mt.getSo_ngay_tre() > 0){
                    int so_ngay = mt.getSo_ngay_tre();
                    int id = mt.getMa_muon();
                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                    String tien = format.format(so_ngay*10000);
                    String queryTien = "UPDATE muon_tra_sach SET tien_phat = '"+tien+"' WHERE ma_muon = "+id;
                    int result = statement.executeUpdate(queryTien);
                }
            }
            LoadDataMT("select * from muon_tra_sach");
        } catch (SQLException ex) {
            Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String dateA;
    String dateB;
    PieChart chart;
    public void LoadBC(){
        GridPane boxBC = new GridPane();
        final DatePicker date = new DatePicker();
        DatePicker date2 = new DatePicker();
        
        Button bt = new Button("IN BÁO CÁO");
        
        date.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("d-M-yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
        
        date2.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("d-M-yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
        
        date.setOnAction(e -> {
                DateTimeFormatter format =DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate LDate = date.getValue();
                dateA = format.format(LDate);
                System.out.println(dateA.toString());
        });
        date2.setOnAction(e -> {
                DateTimeFormatter format =DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate LDate = date2.getValue();
                dateB = format.format(LDate);
                System.out.println(dateB.toString());
        });
        bt.setOnAction(e -> {
            
            
            System.out.println(dateA.toString());
            System.out.println(dateB.toString());
            String sql = "select * from muon_tra_sach where ngay_muon >= STR_TO_DATE('"+dateA+"', '%d-%m-%Y') and ngay_muon <= STR_TO_DATE('"+dateB+"', '%d-%m-%Y')";
            try {
                PrintReport(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Label count = new Label();
        ArrayList<String> stringCount;
        chart = new PieChart();
        try {
            stringCount = getCount();
            for(int i = 0; i <stringCount.size(); i++){
                String tam = count.getText() + "\n" + stringCount.get(i).toString();
                count.setText(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        boxBC.add(date, 0, 0);
        boxBC.add(date2, 1, 0);
        boxBC.add(bt, 2, 0);
        boxBC.add(count, 0, 2);
        boxBC.add(chart, 5, 2);
        
        
        Scene scene = new Scene(boxBC, 600, 600);
        stBC.setScene(scene);
        stBC.show();
    }
    
    public void PrintReport(String sql) throws SQLException, FileNotFoundException, DocumentException{
        Statement st1 = connection.createStatement();
        //ResultSet rs = st1.executeQuery("select * from muon_tra_sach");
        ResultSet rs = st1.executeQuery(sql);
        ResultSetMetaData meta = rs.getMetaData();
        int col = meta.getColumnCount();
        
        Document doc = new Document();
        DirectoryChooser chooser = new DirectoryChooser();
        //File file = new File("D://project/HinhTron.png");
        File file = chooser.showDialog(null);
        String path = "d:/project/report.pdf";
        
        if(file != null){
            path = file.getPath()+"/report.pdf";
        }
        
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        PdfPTable pt = new PdfPTable(col);
        pt.setWidthPercentage(100);
        doc.open();

        try {
            
        
            BaseFont bf = BaseFont.createFont("c:\\Windows\\Fonts\\ARIALUNI.TTF", BaseFont.IDENTITY_H, true);
            
            Font titleFontPara = new Font(bf, 36, Font.BOLD, BaseColor.BLACK);
            doc.add(new Paragraph("Báo Cáo Mượn Trả Sách", titleFontPara));
            doc.add(Chunk.NEWLINE);
            
            Font titleFont = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
            
            Font titleFont2 = new Font(bf, 12, Font.BOLD, BaseColor.BLACK);
            pt.addCell(new PdfPCell(new Phrase("Mã Mượn",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Mã Hội Viên",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Ngày Mượn",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Ngày Trả Dự Kiến",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Ngày Trả Thực Tế",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Tình Trạng",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Số Ngày Trễ",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Tiền Phạt",titleFont2)));
            
            while(rs.next()){
            
            
                pt.addCell(new PdfPCell(new Phrase(""+rs.getInt("ma_muon"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_hoivien"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_sach"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getDate("ngay_muon"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getDate("ngay_tra_dk"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getDate("ngay_tra_tt"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("tinh_trang"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getInt("so_ngay_tre"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("tien_phat"),titleFont)));

                /*pt.addCell(""+rs.getInt("ma_muon"));
                pt.addCell(""+rs.getString("ma_hoivien"));
                pt.addCell(""+rs.getString("ma_sach"));
                pt.addCell(""+rs.getDate("ngay_muon"));
                pt.addCell(""+rs.getDate("ngay_tra_dk"));
                pt.addCell(""+rs.getDate("ngay_tra_tt"));
                pt.addCell(""+rs.getString("tinh_trang"));
                pt.addCell(""+rs.getInt("so_ngay_tre"));
                pt.addCell(""+rs.getString("tien_phat"));*/
                }
            } catch (IOException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        doc.add(pt);
        doc.close();
        
        File myFile = new File(path);
        try {
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void PrintReportTheoQuy(String name) throws SQLException, FileNotFoundException, DocumentException{
        Document doc = new Document();
        DirectoryChooser chooser = new DirectoryChooser();
        //File file = new File("D://project/HinhTron.png");
        File file = chooser.showDialog(null);
        String filename = "reportMuonTraSach";
        if(!name.isEmpty())
            filename = name;
        String path = "d:/project/"+filename+".pdf";
        String pathIMG = "d:/project/DoThi.png";
        if(file != null){
            path = file.getPath()+"/"+filename+".pdf";
            pathIMG = file.getPath()+"/DoThi.png";
        }
        File fileA = new File(pathIMG);
        
        Statement st1 = connection.createStatement();
        Statement st2 = connection.createStatement();
        Statement st3 = connection.createStatement();
        Statement st4 = connection.createStatement();
        
        //ResultSet rs = st1.executeQuery("select * from muon_tra_sach");
        ResultSet rs1 = st1.executeQuery("select * from muon_tra_sach where (month(ngay_muon) >= 1 and month(ngay_muon) <= 3) and year(ngay_muon) = " + SYears);
        ResultSetMetaData meta1 = rs1.getMetaData();
        int col1 = meta1.getColumnCount();
        
        ResultSet rs2 = st2.executeQuery("select * from muon_tra_sach where (month(ngay_muon) >= 4 and month(ngay_muon) <= 6) and year(ngay_muon) = " + SYears);
        ResultSetMetaData meta2 = rs2.getMetaData();
        int col2 = meta2.getColumnCount();
        
        ResultSet rs3 = st3.executeQuery("select * from muon_tra_sach where (month(ngay_muon) >= 7 and month(ngay_muon) <= 9) and year(ngay_muon) = " + SYears);
        ResultSetMetaData meta3 = rs3.getMetaData();
        int col3 = meta3.getColumnCount();
        
        ResultSet rs4 = st4.executeQuery("select * from muon_tra_sach where (month(ngay_muon) >= 10 and month(ngay_muon) <= 12) and year(ngay_muon) = " + SYears);
        ResultSetMetaData meta4 = rs4.getMetaData();
        int col4 = meta4.getColumnCount();
        
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        
        PdfPTable pt1 = new PdfPTable(col1);
        pt1.setWidthPercentage(100);
        
        PdfPTable pt2 = new PdfPTable(col2);
        pt2.setWidthPercentage(100);
        
        PdfPTable pt3 = new PdfPTable(col3);
        pt3.setWidthPercentage(100);
        
        PdfPTable pt4 = new PdfPTable(col4);
        pt4.setWidthPercentage(100);
        
        doc.open();

        try {
            BaseFont bf = BaseFont.createFont("c:\\Windows\\Fonts\\ARIALUNI.TTF", BaseFont.IDENTITY_H, true);
            
            Font titleFontPara = new Font(bf, 30, Font.BOLD, BaseColor.BLACK);
            Font titleFontQuy = new Font(bf, 20, Font.BOLD, BaseColor.BLACK);
            doc.add(new Paragraph("Báo Cáo Mượn Trả Sách Năm " + SYears, titleFontPara));
            Paragraph para1 =  new Paragraph("Quý 1", titleFontQuy);
            Paragraph para2 =  new Paragraph("Quý 2", titleFontQuy);
            Paragraph para3 =  new Paragraph("Quý 3", titleFontQuy);
            Paragraph para4 =  new Paragraph("Quý 4", titleFontQuy);
            Paragraph paraDT =  new Paragraph("Đồ Thị", titleFontQuy);
            
            doc.add(Chunk.NEWLINE);
            
            Font titleFont = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
            
            Font titleFont2 = new Font(bf, 12, Font.BOLD, BaseColor.BLACK);
            
            ThemDuLieuTable(pt1, rs1, titleFont,titleFont2);
            ThemDuLieuTable(pt2, rs2, titleFont,titleFont2);
            ThemDuLieuTable(pt3, rs3, titleFont,titleFont2);
            ThemDuLieuTable(pt4, rs4, titleFont,titleFont2);
            
            /*//Thêm cột tiêu đề cho Quý 1
            pt1.addCell(new PdfPCell(new Phrase("Mã Mượn",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Mã Hội Viên",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Ngày Mượn",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Ngày Trả Dự Kiến",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Ngày Trả Thực Tế",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Tình Trạng",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Số Ngày Trễ",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Tiền Phạt",titleFont2)));
            
            //Thêm cột tiêu đề cho Quý 2
            pt2.addCell(new PdfPCell(new Phrase("Mã Mượn",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Mã Hội Viên",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Ngày Mượn",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Ngày Trả Dự Kiến",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Ngày Trả Thực Tế",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Tình Trạng",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Số Ngày Trễ",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Tiền Phạt",titleFont2)));
            
            //Thêm cột tiêu đề cho Quý 3
            pt3.addCell(new PdfPCell(new Phrase("Mã Mượn",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Mã Hội Viên",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Ngày Mượn",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Ngày Trả Dự Kiến",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Ngày Trả Thực Tế",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Tình Trạng",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Số Ngày Trễ",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Tiền Phạt",titleFont2)));
            
            pt4.addCell(new PdfPCell(new Phrase("Mã Mượn",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Mã Hội Viên",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Ngày Mượn",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Ngày Trả Dự Kiến",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Ngày Trả Thực Tế",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Tình Trạng",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Số Ngày Trễ",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Tiền Phạt",titleFont2)));*/
            
            /*while(rs1.next()){
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getInt("ma_muon"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("ma_hoivien"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("ma_sach"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getDate("ngay_muon"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getDate("ngay_tra_dk"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getDate("ngay_tra_tt"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("tinh_trang"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getInt("so_ngay_tre"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("tien_phat"),titleFont)));
                }
            while(rs2.next()){
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getInt("ma_muon"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("ma_hoivien"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("ma_sach"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getDate("ngay_muon"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getDate("ngay_tra_dk"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getDate("ngay_tra_tt"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("tinh_trang"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getInt("so_ngay_tre"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("tien_phat"),titleFont)));
                }
            while(rs3.next()){
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getInt("ma_muon"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("ma_hoivien"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("ma_sach"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getDate("ngay_muon"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getDate("ngay_tra_dk"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getDate("ngay_tra_tt"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("tinh_trang"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getInt("so_ngay_tre"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("tien_phat"),titleFont)));
                }
            while(rs4.next()){
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getInt("ma_muon"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("ma_hoivien"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("ma_sach"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getDate("ngay_muon"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getDate("ngay_tra_dk"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getDate("ngay_tra_tt"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("tinh_trang"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getInt("so_ngay_tre"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("tien_phat"),titleFont)));
                }*/
            
            doc.add(para1);
            doc.add(Chunk.NEWLINE);
            doc.add(pt1);
            doc.add(Chunk.NEWLINE);
            
            doc.add(para2);
            doc.add(Chunk.NEWLINE);
            doc.add(pt2);
            doc.add(Chunk.NEWLINE);
            
            doc.add(para3);
            doc.add(Chunk.NEWLINE);
            doc.add(pt3);
            doc.add(Chunk.NEWLINE);
            
            doc.add(para4);
            doc.add(Chunk.NEWLINE);
            doc.add(pt4);
            doc.add(Chunk.NEWLINE);
            
            WritableImage writableImage = new WritableImage((int)chartMTSach.getWidth(), 
                    (int)chartMTSach.getHeight());
            SnapshotParameters sp =  new SnapshotParameters();
            sp.setTransform(Transform.scale(0.8, 0.8));
            chartMTSach.snapshot(sp, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", fileA);
            Image image1 = Image.getInstance(pathIMG);
            doc.newPage();
            doc.add(paraDT);
            doc.add(image1);
            
            doc.close();
            fileA.delete();

            } catch (IOException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }

        File myFile = new File(path);
        try {
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void ThemDuLieuTable(PdfPTable pt, ResultSet rs, Font titleFont, Font titleFont2) throws SQLException{
            pt.addCell(new PdfPCell(new Phrase("Mã Mượn",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Mã Hội Viên",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Ngày Mượn",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Ngày Trả Dự Kiến",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Ngày Trả Thực Tế",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Tình Trạng",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Số Ngày Trễ",titleFont2)));
            pt.addCell(new PdfPCell(new Phrase("Tiền Phạt",titleFont2)));
        while(rs.next()){
                pt.addCell(new PdfPCell(new Phrase(""+rs.getInt("ma_muon"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_hoivien"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_sach"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getDate("ngay_muon"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getDate("ngay_tra_dk"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getDate("ngay_tra_tt"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("tinh_trang"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getInt("so_ngay_tre"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("tien_phat"),titleFont)));
        }
            
    }
    public void PrintReportTheoQuy2(String name) throws SQLException, FileNotFoundException, DocumentException{
        Document doc = new Document();
        DirectoryChooser chooser = new DirectoryChooser();
        //File file = new File("D://project/HinhTron.png");
        File file = chooser.showDialog(null);
        String filename = "reportNhapSach";
        if(!name.isEmpty())
            filename = name;
        String path = "d:/project/"+filename+".pdf";
        String pathIMG = "d:/project/DoThi.png";
        if(file != null){
            path = file.getPath()+"/"+filename+".pdf";
            pathIMG = file.getPath()+"/DoThi.png";
        }
        File fileA = new File(pathIMG);
        
        Statement st1 = connection.createStatement();
        Statement st2 = connection.createStatement();
        Statement st3 = connection.createStatement();
        Statement st4 = connection.createStatement();
        
        //ResultSet rs = st1.executeQuery("select * from muon_tra_sach");
        ResultSet rs1 = st1.executeQuery("select * from sach where (month(ngay_nhap) >= 1 and month(ngay_nhap) <= 3) and year(ngay_nhap) = " + SYears2);
        ResultSetMetaData meta1 = rs1.getMetaData();
        int col1 = meta1.getColumnCount();
        
        ResultSet rs2 = st2.executeQuery("select * from sach where (month(ngay_nhap) >= 4 and month(ngay_nhap) <= 6) and year(ngay_nhap) = " + SYears2);
        ResultSetMetaData meta2 = rs2.getMetaData();
        int col2 = meta2.getColumnCount();
        
        ResultSet rs3 = st3.executeQuery("select * from sach where (month(ngay_nhap) >= 7 and month(ngay_nhap) <= 9) and year(ngay_nhap) = " + SYears2);
        ResultSetMetaData meta3 = rs3.getMetaData();
        int col3 = meta3.getColumnCount();
        
        ResultSet rs4 = st4.executeQuery("select * from sach where (month(ngay_nhap) >= 10 and month(ngay_nhap) <= 12) and year(ngay_nhap) = " + SYears2);
        ResultSetMetaData meta4 = rs4.getMetaData();
        int col4 = meta4.getColumnCount();
        
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        
        PdfPTable pt1 = new PdfPTable(col1);
        pt1.setWidthPercentage(100);
        
        PdfPTable pt2 = new PdfPTable(col2);
        pt2.setWidthPercentage(100);
        
        PdfPTable pt3 = new PdfPTable(col3);
        pt3.setWidthPercentage(100);
        
        PdfPTable pt4 = new PdfPTable(col4);
        pt4.setWidthPercentage(100);
        
        doc.open();

        try {
            BaseFont bf = BaseFont.createFont("c:\\Windows\\Fonts\\ARIALUNI.TTF", BaseFont.IDENTITY_H, true);
            
            Font titleFontPara = new Font(bf, 30, Font.BOLD, BaseColor.BLACK);
            Font titleFontQuy = new Font(bf, 20, Font.BOLD, BaseColor.BLACK);
            doc.add(new Paragraph("Báo Cáo Nhập Sách Năm " + SYears2, titleFontPara));
            Paragraph para1 =  new Paragraph("Quý 1", titleFontQuy);
            Paragraph para2 =  new Paragraph("Quý 2", titleFontQuy);
            Paragraph para3 =  new Paragraph("Quý 3", titleFontQuy);
            Paragraph para4 =  new Paragraph("Quý 4", titleFontQuy);
            Paragraph paraDT =  new Paragraph("Đồ Thị", titleFontQuy);
            
            doc.add(Chunk.NEWLINE);
            
            Font titleFont = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
            
            Font titleFont2 = new Font(bf, 12, Font.BOLD, BaseColor.BLACK);
            
            ThemDuLieuTable2(pt1, rs1, titleFont, titleFont2);
            ThemDuLieuTable2(pt2, rs2, titleFont, titleFont2);
            ThemDuLieuTable2(pt3, rs3, titleFont, titleFont2);
            ThemDuLieuTable2(pt4, rs4, titleFont, titleFont2);
            
            /*pt1.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Tên Sách",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Mã Nhà Xuất Bản",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Mã Tác Giả",titleFont2)));
            pt1.addCell(new PdfPCell(new Phrase("Ngày Nhập",titleFont2)));

            pt2.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Tên Sách",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Mã Nhà Xuất Bản",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Mã Tác Giả",titleFont2)));
            pt2.addCell(new PdfPCell(new Phrase("Ngày Nhập",titleFont2)));
            
            pt3.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Tên Sách",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Mã Nhà Xuất Bản",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Mã Tác Giả",titleFont2)));
            pt3.addCell(new PdfPCell(new Phrase("Ngày Nhập",titleFont2)));
            
            pt4.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Tên Sách",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Mã Nhà Xuất Bản",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Mã Tác Giả",titleFont2)));
            pt4.addCell(new PdfPCell(new Phrase("Ngày Nhập",titleFont2)));
            
            while(rs1.next()){
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("ma_sach"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("tensach"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("ma_nxb"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getString("ma_tg"),titleFont)));
                pt1.addCell(new PdfPCell(new Phrase(""+rs1.getDate("ngay_nhap"),titleFont)));
                }
            while(rs2.next()){
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("ma_sach"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("tensach"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("ma_nxb"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getString("ma_tg"),titleFont)));
                pt2.addCell(new PdfPCell(new Phrase(""+rs2.getDate("ngay_nhap"),titleFont)));
                }
            while(rs3.next()){
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("ma_sach"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("tensach"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("ma_nxb"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getString("ma_tg"),titleFont)));
                pt3.addCell(new PdfPCell(new Phrase(""+rs3.getDate("ngay_nhap"),titleFont)));
                }
            while(rs4.next()){
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("ma_sach"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("tensach"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("ma_nxb"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getString("ma_tg"),titleFont)));
                pt4.addCell(new PdfPCell(new Phrase(""+rs4.getDate("ngay_nhap"),titleFont)));
                }*/
            
            doc.add(para1);
            doc.add(Chunk.NEWLINE);
            doc.add(pt1);
            doc.add(Chunk.NEWLINE);
            
            doc.add(para2);
            doc.add(Chunk.NEWLINE);
            doc.add(pt2);
            doc.add(Chunk.NEWLINE);
            
            doc.add(para3);
            doc.add(Chunk.NEWLINE);
            doc.add(pt3);
            doc.add(Chunk.NEWLINE);
            
            doc.add(para4);
            doc.add(Chunk.NEWLINE);
            doc.add(pt4);
            doc.add(Chunk.NEWLINE);
            
            WritableImage writableImage = new WritableImage((int)chartSach.getWidth(),
                    (int)chartSach.getHeight());
            SnapshotParameters sp =  new SnapshotParameters();
            sp.setTransform(Transform.scale(0.8, 0.8));
            chartSach.snapshot(sp, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", fileA);
            Image image1 = Image.getInstance(pathIMG);
            
            doc.newPage();
            doc.add(paraDT);
            doc.add(image1);
            
            doc.close();
            fileA.delete();

            } catch (IOException ex) {
                Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        File myFile = new File(path);
        try {
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            Logger.getLogger(ThuVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void ThemDuLieuTable2(PdfPTable pt, ResultSet rs, Font titleFont, Font titleFont2) throws SQLException{
        pt.addCell(new PdfPCell(new Phrase("Mã Sách",titleFont2)));
        pt.addCell(new PdfPCell(new Phrase("Tên Sách",titleFont2)));
        pt.addCell(new PdfPCell(new Phrase("Mã Nhà Xuất Bản",titleFont2)));
        pt.addCell(new PdfPCell(new Phrase("Mã Tác Giả",titleFont2)));
        pt.addCell(new PdfPCell(new Phrase("Mã Loại Sách",titleFont2)));
        pt.addCell(new PdfPCell(new Phrase("Mô Tả",titleFont2)));
        pt.addCell(new PdfPCell(new Phrase("Ngày Nhập",titleFont2)));
        
        while(rs.next()){
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_sach"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("tensach"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_nxb"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_tg"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("ma_ls"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getString("mota"),titleFont)));
                pt.addCell(new PdfPCell(new Phrase(""+rs.getDate("ngay_nhap"),titleFont)));
        }
    }
    public ArrayList<String> getCount() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select YEAR(ngay_muon), count(ma_muon) from muon_tra_sach group by YEAR(ngay_muon)"); 
        ArrayList<String> thongtin = new ArrayList<>();
        while(resultSet.next()){
            String nam = "";
            nam += "" + resultSet.getString(1) + ": " + resultSet.getInt(2);
            chart.getData().add(new PieChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            thongtin.add(nam);
        }
        return thongtin;
    }
    public void LoadChartMTSach() throws SQLException{
        chartMTSach.getData().clear();
        chartMTSach.getData().add(new PieChart.Data("Quý 1", getCountQ1()));
        chartMTSach.getData().add(new PieChart.Data("Quý 2", getCountQ2()));
        chartMTSach.getData().add(new PieChart.Data("Quý 3", getCountQ3()));
        chartMTSach.getData().add(new PieChart.Data("Quý 4", getCountQ4()));
    }
    public void LoadChartMTSach2() throws SQLException{
        String s1 = "select count(ma_sach) from sach where (month(ngay_nhap) >= 1 and month(ngay_nhap) <= 3) and year(ngay_nhap) = " + SYears2;
        String s2 = "select count(ma_sach) from sach where (month(ngay_nhap) >= 4 and month(ngay_nhap) <= 6) and year(ngay_nhap) = " + SYears2;
        String s3 = "select count(ma_sach) from sach where (month(ngay_nhap) >= 7 and month(ngay_nhap) <= 9) and year(ngay_nhap) = " + SYears2;
        String s4 = "select count(ma_sach) from sach where (month(ngay_nhap) >= 10 and month(ngay_nhap) <= 12) and year(ngay_nhap) = " + SYears2;
        
        chartSach.getData().clear();
        chartSach.getData().add(new PieChart.Data("Quý 1", getSQ1(s1)));
        chartSach.getData().add(new PieChart.Data("Quý 2", getSQ1(s2)));
        chartSach.getData().add(new PieChart.Data("Quý 3", getSQ1(s3)));
        chartSach.getData().add(new PieChart.Data("Quý 4", getSQ1(s4)));
    }
    public int getCountQ1() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(ma_muon) from muon_tra_sach where (month(ngay_muon) >= 1 and month(ngay_muon) <= 3) and year(ngay_muon) = " + SYears); 
        int thongtin = 0;
        while(resultSet.next()){
            thongtin = resultSet.getInt(1);
        }
        return thongtin;
    }
    public int getCountQ2() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(ma_muon) from muon_tra_sach where (month(ngay_muon) >= 4 and month(ngay_muon) <= 6) and year(ngay_muon) = " + SYears); 
        int thongtin = 0;
        while(resultSet.next()){
            thongtin = resultSet.getInt(1);
        }
        return thongtin;
    }
    public int getCountQ3() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(ma_muon) from muon_tra_sach where (month(ngay_muon) >= 7 and month(ngay_muon) <= 9) and year(ngay_muon) = " + SYears); 
        int thongtin = 0;
        while(resultSet.next()){
            thongtin = resultSet.getInt(1);
        }
        return thongtin;
    }
    public int getCountQ4() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(ma_muon) from muon_tra_sach where (month(ngay_muon) >= 10 and month(ngay_muon) <= 12) and year(ngay_muon) = " + SYears); 
        int thongtin = 0;
        while(resultSet.next()){
            thongtin = resultSet.getInt(1);
        }
        return thongtin;
    }
    public int getSQ1(String str) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(str); 
        int thongtin = 0;
        while(resultSet.next()){
            thongtin = resultSet.getInt(1);
        }
        return thongtin;
    }
    public boolean DangNhap(String username, String password) throws SQLException{
        boolean kt= false;
        
        PreparedStatement stTK = (PreparedStatement) connection.prepareStatement("select * from taikhoan where username = ?");
        stTK.setString(1, username);
        ResultSet rsTK = stTK.executeQuery();
        if(!rsTK.next()){
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Đăng Nhập Thất Bại");
                alert.setHeaderText("Tài Khoản Không Chính Xác");
                alert.setContentText("Vui lòng nhập lại tài khoản chính xác");
                alert.showAndWait();
                return false;
        }
        else{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from taikhoan where username = ? and password = ?");
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if(!rs.next()){
                Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Đăng Nhập Thất Bại");
                    alert.setHeaderText("Mật Khẩu Không Chính Xác");
                    alert.setContentText("Vui lòng nhập lại mật khẩu chính xác");
                    alert.showAndWait();
                    return false;
            }
            else{
                kt = true;
                return kt;
            }
        }
    }
}
