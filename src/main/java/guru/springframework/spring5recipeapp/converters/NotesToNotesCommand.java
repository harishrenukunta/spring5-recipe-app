package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;

public class NotesToNotesCommand implements Converter<Notes,NotesCommand> {
    @Override
    public NotesCommand convert(Notes src) {
        if(src == null){
            return null;
        }

        return NotesCommand.builder()
                .id(src.getId())
                .recipeNotes(src.getRecipeNotes())
                .uom(new UnitOfMeasureToUnitOfMeasureCommand().convert(src.getUom()))
                .build();
    }
}
