package br.com.desafio.api.model;

import lombok.Data;

/**
 * @author lucas
 * @since 27/06/202
 */
@Data
public class Filme {

    private Integer year;
    private String title;
    private String studios;
    private String producers;
    private String winner;

    public Filme(Integer year, String title, String studios, String producers, String winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

}
