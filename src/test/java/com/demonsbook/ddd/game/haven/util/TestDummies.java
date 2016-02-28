package com.demonsbook.ddd.game.haven.util;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import com.google.common.collect.ImmutableSet;

public class TestDummies {

	public static final UserId DUMMY_USER_ID = new UserId();
	public static final GameId DUMMY_GAME_ID = new GameId();
	public static final Product DUMMY_PRODUCT = new Product(DUMMY_USER_ID, DUMMY_GAME_ID);
	public static final Offer DUMMY_OFFER = new Offer(DUMMY_USER_ID, ImmutableSet.of(DUMMY_PRODUCT));
}
