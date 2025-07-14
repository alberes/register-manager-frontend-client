package io.github.alberes.register.manager.frontend.client.controllers;

import io.github.alberes.register.manager.frontend.client.constants.Constants;
import io.github.alberes.register.manager.frontend.client.controllers.exceptions.dto.FieldErroDto;
import io.github.alberes.register.manager.frontend.client.controllers.exceptions.dto.StandardErrorDto;
import org.springframework.ui.Model;

import java.net.URI;

public abstract class GenericController {

    public void createMessages(Model model, StandardErrorDto standardErrorDto){
        model.addAttribute(Constants.ERROR, standardErrorDto);
        if (!standardErrorDto.getFields().isEmpty()) {
            for (FieldErroDto f : standardErrorDto.getFields()) {
                model.addAttribute(f.field(), f.message());
            }
        }
    }

    public String extractId(URI uri){
        String[] path = uri.getPath().split(Constants.SLASH);
        String id = path[path.length - 1];
        return id;
    }

}
