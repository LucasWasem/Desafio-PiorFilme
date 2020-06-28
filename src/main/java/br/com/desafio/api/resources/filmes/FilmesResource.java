package br.com.desafio.api.resources.filmes;

import br.com.desafio.api.model.Filme;
import br.com.desafio.api.model.RetornoDesafio;
import br.com.desafio.api.service.filmes.FilmesService;
import br.com.desafio.api.model.retorno.Retorno;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 * @since 27/06/202
 */
@RestController
@RequestMapping("/api/filmes")
@CrossOrigin
public class FilmesResource {

    @Autowired
    private FilmesService filmeService;
    
    @GetMapping("/filmes")
    public ResponseEntity<Retorno<RetornoDesafio>> getFilmes() {

        Retorno _retorno = new Retorno();

        List<RetornoDesafio> retornoConsulta = filmeService.getProdutorFilme();

        if (retornoConsulta == null) {
            _retorno.setOk(false);
            _retorno.setMessage(filmeService.mensagem);
            return ResponseEntity.status(filmeService.status).body(_retorno);
        }

        
        _retorno.setOk(true);
        _retorno.setObjs(retornoConsulta);

        return ResponseEntity.ok(_retorno);

    }
}
