package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand src) {
        if(src == null){
            return null;
        }

        return Notes.builder()
                .id(src.getId())
                .recipeNotes(src.getRecipeNotes())
                .uom(new UnitOfMeasureCommandToUnitOfMeasure().convert(src.getUom()))
                .build();
    }
}
