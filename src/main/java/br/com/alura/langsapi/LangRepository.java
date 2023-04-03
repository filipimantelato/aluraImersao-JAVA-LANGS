package br.com.alura.langsapi;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LangRepository extends MongoRepository<langs, String> {

    List<langs> findByOrderByRanking();
}
