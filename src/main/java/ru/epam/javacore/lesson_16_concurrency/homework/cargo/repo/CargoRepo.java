package ru.epam.javacore.lesson_16_concurrency.homework.cargo.repo;

import ru.epam.javacore.lesson_16_concurrency.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_16_concurrency.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_16_concurrency.homework.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Cargo getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
