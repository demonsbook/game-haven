package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.util.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;

import java.util.Collection;

public interface PricingService {

	Money calculatePriceFor(Collection<Product> products);

}
