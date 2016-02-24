package com.demonsbook.ddd.game.haven.application.services;


import com.demonsbook.ddd.game.haven.domain.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.GameId;
import com.demonsbook.ddd.game.haven.domain.UserId;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

	@Mock ProductFactory productFactory;
	@Mock UserRepository userRepository;
	@Mock GameRepository gameRepository;
	@InjectMocks PurchaseService purchaseService;

	@Test
	public void shouldObtainProductDetailsForGivenGameAndUserCombination() {
		assertThat(purchaseService.getProduct(new GameId(), new UserId())).isNotNull();
	}

}