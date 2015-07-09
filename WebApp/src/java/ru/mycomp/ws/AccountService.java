/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mycomp.ws;

/**
 *
 * @author Сережа
 */
public interface AccountService {

    /**
     * Retrieves current balance or zero if addAmount() method was not called
     * before for specified id
     *     
* @param id balance identifier
     */
    public Long getAmount(Integer id);

    /**
     * Increases balance or set if addAmount() method was called first time
     *     
* @param id balance identifier
     * @param value positive or negative value, which must be added to current
     * balance
     */
    public void addAmount(Integer id, Long value);
}
