/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.api.service.filmes;

import br.com.desafio.api.model.Filme;
import br.com.desafio.api.model.RetornoDesafio;
import br.com.desafio.api.ConfigWebSpringBoot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 * @since 27/06/202
 */
@Service
public class FilmesService {

    public HttpStatus status;
    public String mensagem;

    private Map<String, Integer[]> hashDados = null; // ganhador + anos que ganhou

    /**
     * Obter o produtor com maior intervalo entre dois prêmios, e o que obteve
     * dois prêmios mais rápido;
     */
    public List<RetornoDesafio> getProdutorFilme() {

        hashDados = new HashMap<String, Integer[]>();

        List<Filme> list = ConfigWebSpringBoot.bancoDeDadosFilmes;

        for (Filme filme : list) {

            if (filme.getWinner().equals("yes")) {

                String chave = filme.getProducers();
                Integer[] value = hashDados.get(chave);

                if (value != null) {
                    Integer[] anos = value;
                    Integer[] novoArray = new Integer[anos.length + 1];

                    int i = 0;
                    for (Integer ano : anos) {
                        novoArray[i] = ano;
                        i++;
                    }
                    novoArray[anos.length] = filme.getYear();
                    value = novoArray;
                } else {
                    value = new Integer[]{filme.getYear()};
                }
                hashDados.put(chave, value);
            }
        }

        List<RetornoDesafio> ganhadoresComMaisDeUm = new ArrayList<>();

        for (String chave : hashDados.keySet()) {

            Integer[] anos = hashDados.get(chave);
            if (anos.length > 1) {

                List<Integer> menorIntervalo = maiorIntervalo(anos);
                RetornoDesafio retornoDesafioMenor = new RetornoDesafio(chave, menorIntervalo.get(0), menorIntervalo.get(1), menorIntervalo.get(2));
                ganhadoresComMaisDeUm.add(retornoDesafioMenor);

                List<Integer> maiorIntervalo = maiorIntervalo(anos);
                RetornoDesafio retornoDesafioMaior = new RetornoDesafio(chave, maiorIntervalo.get(0), maiorIntervalo.get(1), maiorIntervalo.get(2));
                ganhadoresComMaisDeUm.add(retornoDesafioMaior);
            }

        }

        Collections.sort(ganhadoresComMaisDeUm, (o1, o2) -> o1.getInterval().compareTo(o2.getInterval()));

        List<RetornoDesafio> retorno = new ArrayList<>();
        retorno.add(ganhadoresComMaisDeUm.get(0));
        retorno.add(ganhadoresComMaisDeUm.get(ganhadoresComMaisDeUm.size() - 1));

        return retorno;

    }

    private List<Integer> maiorIntervalo(Integer[] anos) {

        List<Integer> retorno = new ArrayList<>();
        Integer menorAnoRetorno = 0;
        Integer maiorAnoRetorno = 0;

        Arrays.sort(anos);

        Integer maiorIntervalo = 0;
        Integer aux = 0;
        Integer anoAnterior = anos[aux];
        Integer anoDepois = anos[aux + 1];

        while (aux < anos.length - 1) {

            Integer diferenca = anoDepois - anoAnterior;

            if (diferenca > maiorIntervalo) {
                maiorIntervalo = diferenca;
                menorAnoRetorno = anoAnterior;
                maiorAnoRetorno = anoDepois;
            }

            anoAnterior = anos[aux];
            anoDepois = anos[aux + 1];
            aux++;

        }
        retorno.add(0, maiorIntervalo);
        retorno.add(1, menorAnoRetorno);
        retorno.add(2, maiorAnoRetorno);

        return retorno;
    }

    private List<Integer> menorIntervalo(Integer[] anos) {

        List<Integer> retorno = new ArrayList<>();
        Integer menorAnoRetorno = 0;
        Integer maiorAnoRetorno = 0;

        Arrays.sort(anos);

        Integer menorIntervalo = 99999;
        Integer aux = 0;
        Integer anoCalculo = 0;

        while (aux < anos.length) {

            anoCalculo = anos[aux];

            for (int i = aux + 1; i < anos.length; i++) {
                Integer ano = anos[i];
                Integer diferenca = ano - anoCalculo;
                if (diferenca < menorIntervalo) {
                    menorIntervalo = diferenca;
                    menorAnoRetorno = anoCalculo;
                    maiorAnoRetorno = ano;
                }
            }
            aux++;
        }

        retorno.add(0, menorIntervalo);
        retorno.add(1, menorAnoRetorno);
        retorno.add(2, maiorAnoRetorno);

        return retorno;
    }

}
