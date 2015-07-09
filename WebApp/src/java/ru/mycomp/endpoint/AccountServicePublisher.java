/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mycomp.endpoint;

import java.sql.SQLException;
import javax.xml.ws.Endpoint;
import ru.mycomp.ws.AccountServiceImpl;

/**
 *
 * @author Сережа
 */
public class AccountServicePublisher {

    public static void main(String... args) throws ClassNotFoundException, SQLException {
        Endpoint.publish("http://localhost:1233/wss/accserv", new AccountServiceImpl());
    }

}
