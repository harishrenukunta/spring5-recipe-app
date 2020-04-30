package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UomServiceImpl implements UomService {
    private final UnitOfMeasureRepository uomRepo;
    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCmd;

    @Override
    public Set<UnitOfMeasureCommand> findAllUomCommands() {
        final Set<UnitOfMeasureCommand> allUomsCmd = new HashSet<>();
        uomRepo.findAll().iterator().forEachRemaining(uom -> allUomsCmd.add(uomToUomCmd.convert(uom)));
        return allUomsCmd;
    }
}
