package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryGameRepository extends InMemoryRepository<Game, GameId>  implements GameRepository {
}
