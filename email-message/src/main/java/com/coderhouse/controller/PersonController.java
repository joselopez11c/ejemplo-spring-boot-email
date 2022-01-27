package com.coderhouse.controller;

import com.coderhouse.model.Person;
import com.coderhouse.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coder-house")
public class PersonController {

    private final PersonService service;

    @Operation(summary = "Método para crear una persona",
            description = "Permite crear personas en Coderhouse",
            tags = {"person"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se creó a la persona"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request ", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @PostMapping("/person")
    public Person createPerson(@RequestBody Person user) {
        return service.create(user);
    }

    @GetMapping("/person/all")
    public List<Person> findPersons() {
        return service.findAll();
    }

    @GetMapping("/person/{id}")
    public Person findByName(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/person/{id}")
    public Person updatePersonById(@PathVariable Long id, @RequestBody Person user) {
        return service.updateById(user, id);
    }

}
