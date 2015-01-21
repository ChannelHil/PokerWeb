package repository;

import com.google.inject.Inject;
import models.GameHistory;
import models.Hand;
import models.Result;
import models.User;
import ninja.jpa.UnitOfWork;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Channel on 2015-01-21.
 */
public class GameRepository extends BaseRepository<GameHistory>{

    @Inject
    GameHistory gameHistory;


}
