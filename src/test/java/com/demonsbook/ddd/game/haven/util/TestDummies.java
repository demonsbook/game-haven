package com.demonsbook.ddd.game.haven.util;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import com.google.common.collect.ImmutableSet;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL;

public class TestDummies {

	public static final UserId DUMMY_USER_ID = new UserId();
	public static final GameId DUMMY_GAME_ID = new GameId();
	public static final DeliveryMethodId DUMMY_DELIVERY_METHOD_ID = new DeliveryMethodId();
	public static final Product DUMMY_PRODUCT = new Product(DUMMY_USER_ID, DUMMY_GAME_ID, DIGITAL);
	public static final ImmutableSet<Product> DUMMY_PRODUCTS = ImmutableSet.of(DUMMY_PRODUCT);
	public static final BasketDetails DUMMY_BASKET_DETAILS = new BasketDetails(DUMMY_PRODUCTS, DUMMY_USER_ID);
	public static final PaymentMethodId DUMMY_PAYMENT_METHOD_ID = new PaymentMethodId();
	public static final Offer DUMMY_OFFER = new Offer(DUMMY_USER_ID, DUMMY_PRODUCTS, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);
	public static final OfferId DUMMY_OFFER_ID = DUMMY_OFFER.id();
	public static final Purchase DUMMY_PURCHASE = new Purchase(DUMMY_OFFER);
}
