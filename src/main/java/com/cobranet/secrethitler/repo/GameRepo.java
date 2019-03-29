package com.cobranet.secrethitler.repo;
import com.cobranet.secrethitler.model.Game;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepo extends PagingAndSortingRepository<Game,Long> {

}
