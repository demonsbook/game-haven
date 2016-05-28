package com.demonsbook.ddd.game.haven.infrastructure.services;

import com.demonsbook.ddd.game.haven.domain.services.PricingService;
import com.demonsbook.ddd.game.haven.domain.value.object.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;
import java.util.Random;

/**
 * Just a simple pricing service. This can be a separate bounded context or have some complicated logic, but I do not want to get into details here
 * as it's simply out of the scope.
 */
@Service
public class SimplePricingService implements PricingService {
	@Override
	public Money calculatePriceFor(Collection<Product> products) {
		return new Money(BigDecimal.valueOf(new Random().nextInt() % 200), Currency.getInstance("PLN"));
	}
}
