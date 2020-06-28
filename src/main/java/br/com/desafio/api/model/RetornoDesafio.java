/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.api.model;

import lombok.Data;

/**
 * @author lucas
 * @since 27/06/202
 */
@Data
public class RetornoDesafio {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public RetornoDesafio(String producer, Integer interval, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

}
