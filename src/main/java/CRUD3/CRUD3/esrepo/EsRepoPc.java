package CRUD3.CRUD3.esrepo;


import CRUD3.CRUD3.model.tovarmodel.PC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//public interface EsRepoPc extends ElasticsearchRepository<PcEs, Long> {
//
//     Page<PcEs> findByName(String name, Pageable pageable);
//
//     List<PcEs> findByName(String name);
//}