package com.demonsbook.ddd.game.haven.application.services;


import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserDetails;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static com.demonsbook.ddd.game.haven.domain.repository.PurchaseSearchCriteria.aPurchaseSearchCriteria;
import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PURCHASE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

	private static final Product.Version ANY_VERSION = DIGITAL;

	private User user = new User();
	private Game game = new Game();
	private Product product = new Product(user.id(), game.id(), ANY_VERSION);
	private Offer offer = new Offer(user.id(), ImmutableSet.of(product), DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);
	private Purchase purchase = new Purchase(offer);

	@Mock private UserRepository userRepository;
	@Mock private PurchaseRepository purchaseRepository;
	@Mock private DomainEventPublisher domainEventPublisher;
	@InjectMocks private PurchaseService purchaseService;

	@Test
	public void shouldAddProductsToBasket() {
		given(userRepository.getForId(user.id())).willReturn(user);

		purchaseService.addProductToUsersBasket(user.id(), product);

		assertThat(user.getBasketDetails().getProducts()).contains(product);
	}

	@Test
	public void shouldConfirmAPurchase() {
		given(purchaseRepository.getForId(purchase.id())).willReturn(purchase);

		purchaseService.confirmPurchase(purchase.id());

		assertThat(purchase.status()).isSameAs(Purchase.Status.CONFIRMED);
	}

	@Test
	public void shouldReturnUserDetails() {
		given(userRepository.getForId(user.id())).willReturn(user);

		UserDetails userDetails = purchaseService.getUserDetails(user.id());

		assertThat(userDetails).isNotNull();
	}

	@Test
	public void shouldGiveDetailsOfAllPurchasesOfAGivenUser() {
		given(purchaseRepository.getAllMatching(aPurchaseSearchCriteria().forUser(DUMMY_USER_ID).build())).willReturn(ImmutableList.of(DUMMY_PURCHASE));

		Collection<PurchaseDetails> purchaseDetails = purchaseService.getPurchasesOfUser(DUMMY_USER_ID);

		assertThat(purchaseDetails).containsOnly(DUMMY_PURCHASE.getDetails());
	}

}