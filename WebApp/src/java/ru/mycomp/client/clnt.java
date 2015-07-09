/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mycomp.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Сережа
 */
public class clnt {

    static int[] A, B, C;
    static int rCount = 0, wCount = 0, q = 0, w = 0;

    public static void main(String[] args) throws SQLException, FileNotFoundException, InterruptedException {
        AccountServiceImpl_Service accService = new AccountServiceImpl_Service();
        AccountServiceImpl service = accService.getAccountServiceImplPort();

        Scanner scanner = new Scanner(new File("conf.txt"));  //0-get(rCount-число клиентов)A[rCount]-id(ключи) ;  1-set(wCount-число клиентов) {B[wCount]-id ,C[wCount]-value}(ключи)
        while (scanner.hasNextInt()) {
            int k = scanner.nextInt();
            if (k == 0) {
                rCount = scanner.nextInt();
                A = new int[rCount];
                for (int i = 0; i < rCount; i++) {
                    A[i] = scanner.nextInt();
                }
            } else if (k == 1) {
                wCount = scanner.nextInt();
                B = new int[wCount];
                C = new int[wCount];
                for (int i = 0; i < wCount; i++) {
                    B[i] = scanner.nextInt();
                    C[i] = scanner.nextInt();
                }
            }
        }

        for (int i = 0; i < rCount; i++) {
            NewThread getT = new NewThread(service, A[i]);
            getT.run();
        }
        for (int i = 0; i < wCount; i++) {
            NewThread2 addT = new NewThread2(service, B[i], C[i]);
            addT.run();
        }
    }
}

class NewThread implements Runnable {

    Thread t;
    String name;
    AccountServiceImpl srvs;
    int ID;

    NewThread(AccountServiceImpl service, int id) {
        // создаем новый поток
        srvs = service;
        ID = id;
    }

    // точка входа во второй поток
    public void run() {
        try {
            srvs.getAmount(ID);
            Thread.sleep(100);

        } catch (InterruptedException e) {
            System.out.println("abort subthread");
        }
        System.out.println("end subthread");
    }
}

class NewThread2 implements Runnable {

    Thread t;
    String name;
    AccountServiceImpl srvs;
    int ID, val;

    NewThread2(AccountServiceImpl service, int id, int bal) {
        // создаем новый поток
        srvs = service;
        ID = id;
        val = bal;
    }

    // точка входа во второй поток
    public void run() {
        try {
            srvs.getAmount(ID);
            srvs.addAmount(ID, Long.parseLong(String.valueOf(val)));
            Thread.sleep(110);

        } catch (InterruptedException e) {
            System.out.println("abort subthread");
        }
        System.out.println("end subthread");
    }
}
