package br.com.desafio.api;

import br.com.desafio.api.model.Filme;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ConfigWebSpringBoot extends SpringBootServletInitializer {

    public static List<Filme> bancoDeDadosFilmes = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(ConfigWebSpringBoot.class, args);

        /**
         * Carregar arquivo csv em um Hasp
         *
         */
        String arquivoCSV = "C:/tmp/movielist.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {

            br = new BufferedReader(new FileReader(arquivoCSV));
            int id = 0;
            while ((linha = br.readLine()) != null) {
                if (id == 0) {
                    // feito pra ignorar a primeira linha pois vem os titulos das colunas no arquivo CSV
                    id++;
                } else {
                    String[] str = linha.split(csvDivisor);

                    Integer year = Integer.parseInt(str[0]);
                    String title = str[1];
                    String studios = str[2];
                    String producers = str[3];
                    String winner = (str.length == 5 ? str[4] : "no");

                    if (producers.contains(",") && producers.contains("and")) {
                        System.out.println("insere varios");
                        String[] todos = producers.split(",");
                        for (String todo : todos) {

                            if (todo.contains(" and ")) {
                                System.out.println("t: " + todo.substring(0, todo.indexOf(" and ")).trim());
                                System.out.println("t: " + todo.substring(todo.indexOf(" and ") + 4).trim());
                                
                                Filme filme1 = new Filme(year, title, studios, producers.substring(0, producers.indexOf(" and ")).trim(), winner);
                                bancoDeDadosFilmes.add(filme1);

                                Filme filme2 = new Filme(year, title, studios, producers.substring(producers.indexOf(" and ") + 4).trim(), winner);
                                bancoDeDadosFilmes.add(filme2);
                            } else {
                                System.out.println("t: " + todo.trim());
                                Filme filme = new Filme(year, title, studios, todo.trim(), winner);
                                bancoDeDadosFilmes.add(filme);
                            }

                        }
                    } else if (producers.contains(" and ") && !producers.contains(",")) {
                        System.out.println("insere 2");

                        Filme filme1 = new Filme(year, title, studios, producers.substring(0, producers.indexOf(" and ")).trim(), winner);
                        bancoDeDadosFilmes.add(filme1);

                        Filme filme2 = new Filme(year, title, studios, producers.substring(producers.indexOf(" and ") + 4).trim(), winner);
                        bancoDeDadosFilmes.add(filme2);
                        
                    } else {
                        Filme filme = new Filme(year, title, studios, producers, winner);
                        bancoDeDadosFilmes.add(filme);
                    }

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
