package repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Hand;

import java.util.Optional;

/**
 * Created by Channel on 2015-01-21.
 */
@Singleton
public class HandRepository extends BaseRepository<Hand>{
    @Inject
    Hand hand;

}
