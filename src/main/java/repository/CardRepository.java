package repository;

import com.google.inject.Inject;
import models.Card;

/**
 * Created by Channel on 2015-01-21.
 */
public class CardRepository  extends BaseRepository<Card>{

    @Inject
    Card card;
}
