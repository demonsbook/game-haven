package com.demonsbook.ddd.game.haven.domain.services;

import com.demonsbook.ddd.game.haven.domain.value.object.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;

import java.util.Collection;

public interface PricingService {

	Money calculatePriceFor(Collection<Product> products);

}
