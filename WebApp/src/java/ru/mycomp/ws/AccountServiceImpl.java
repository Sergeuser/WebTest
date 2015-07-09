package ru.mycomp.ws;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Сережа
 */
@WebService(serviceName = "AccountServiceImpl")
public class AccountServiceImpl implements AccountService, Runnable {

    //  String query="";

    static int getCounter = 0, addCounter = 0, psG = 0, psA = 0;
    ResultSet rs = null;
    Connection conn;
    Statement stmt;
    Map<Integer, Long> hashmap = new HashMap<Integer, Long>();  //кэш
    Map<Integer, Long> oldHashmap = new HashMap<Integer, Long>(); //чтобы не дергать MYSQL при вставке новых элементов

    public AccountServiceImpl() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdclients", "root", "root32167");
            stmt = conn.createStatement();
            Thread thr = new Thread(this, "UpdaterThread");
            initCach();
            thr.start();

            Thread statisticThr = new Thread(new Runnable() { //запросы в секунду
                @Override
                public void run() {
                    while (true) {
                        try {
                            //  if(psG!=0 || psA!=0)
                            System.out.println("G/s=" + psG + " A/s=" + psA);
                            psG = 0;
                            psA = 0;
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            statisticThr.start();

            Thread statisticClear = new Thread(new Runnable() { //раз в час чистим статистику
                @Override
                public void run() {
                    while (true) {
                        try {
                            getCounter = 0;
                            addCounter = 0;
                            psG = 0;
                            psA = 0;
                            sleep(3600000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            statisticClear.start();

        } catch (ClassNotFoundException ex) {
            System.out.println("Не найден драйвер!");
        }
    }

    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "getAmount")
    @Override
    public Long getAmount(Integer id) {
        if (hashmap.containsKey(id)) {
            getCounter++;
            psG++;
            return hashmap.get(id);
        } else {
            hashmap.put(id, Long.parseLong("0"));
            getCounter++;
            psG++;
            return Long.parseLong("0");
        }
    }

    @WebMethod(operationName = "addAmount")
    @Override
    public void addAmount(Integer id, Long value) {
        if (hashmap.containsKey(id)) {
            hashmap.put(id, hashmap.get(id) + value);
        } else {
            hashmap.put(id, value);
        }
        addCounter++;
        psA++;
    }

    @Override
    public void run() {   //раз в минуту обновляем mySQL и выводим количество совершенных к этому времени запросов
        while (true) {
            try {
                sleep(60000);
                updateBD();
            } catch (InterruptedException ex) {
                Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println("SQLWarning:" + ex.getMessage());
            }
        }
    }

    public void updateBD() throws SQLException {
        for (Map.Entry entry : hashmap.entrySet()) {
            // System.out.println("Key: " + entry.getKey() + " Value: "+ entry.getValue());
            if (oldHashmap.containsKey(entry.getKey())) {
                stmt.executeUpdate("UPDATE clients SET balance = " + entry.getValue() + " WHERE id=" + entry.getKey() + ";");
            } else {
                stmt.executeUpdate("INSERT INTO clients VALUES (" + entry.getKey() + "," + entry.getValue() + ");");
            }
            oldHashmap.put((Integer) entry.getKey(), (Long) entry.getValue());
        }
        System.out.println("BD sucsessfuly update");
        System.out.println("getCounter=" + getCounter);
        System.out.println("addCounter=" + addCounter);

    }

    public void initCach() throws SQLException {
        String query = "SELECT * FROM clients;";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            hashmap.put(rs.getInt("id"), rs.getLong("balance"));
            oldHashmap.put(rs.getInt("id"), rs.getLong("balance"));
        }
        System.out.println("Cach done!");
    }
}
