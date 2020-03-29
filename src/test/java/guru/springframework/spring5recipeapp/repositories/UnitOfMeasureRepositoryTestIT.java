package guru.springframework.spring5recipeapp.repositories;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryTestIT {

    @Autowired
    private UnitOfMeasureRepository uomRepository;

    @Test
    @DirtiesContext
    void findByUom() {
        Optional<UnitOfMeasure> tsp = uomRepository.findByUom("Teaspoon");
        assertThat(tsp.get().getUom()).as("Check uom by description...").isEqualTo("Teaspoon");
    }

    @Test
    public void findByUomCups(){
        assertThat(this.uomRepository.findByUom("Cup").get().getUom())
                .as("Check uom by description...").isEqualTo("Cup");
    }
}