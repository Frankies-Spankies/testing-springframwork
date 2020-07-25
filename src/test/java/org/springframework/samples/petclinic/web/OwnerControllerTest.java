package org.springframework.samples.petclinic.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Permite las anotaciones de mockito; en este caso la de @Captor
@ExtendWith(MockitoExtension.class)
//Estas clases permiten traer un conteaxto de spring,
//inicializar los mocks e injectarlos
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:/spring/mvc-core-config.xml"})
//Limpia el contexto despues de cada metodo
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OwnerControllerTest {

    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService service;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
        given(service.findOwnerByLastName(argumentCaptor.capture())).willAnswer(invocationArguments -> {
            String lastName = invocationArguments.getArgument(0);
            List<Owner> regreso = new ArrayList<>();
            if (lastName.equals("one")) {
                Owner owner = new Owner();
                owner.setId(1);
                regreso.add(owner);
                return regreso;
            }
            if (lastName.equals("many")) {
                return IntStream.rangeClosed(1,5).mapToObj(i -> {
                    Owner owner = new Owner();
                    owner.setId(i);
                    return owner;
                }).collect(Collectors.toList());
            }
            return regreso;
        });
    }

//    @DirtiesContext
    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

//    @DirtiesContext
    @Test
    void processFindFormOne() throws Exception {
        mockMvc.perform(get("/owners")
                .param("lastName", "one"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

//    @DirtiesContext
    @Test
    void processFindFormMany() throws Exception {
        mockMvc.perform(get("/owners")
                .param("lastName", "many"))
                .andExpect(model().attributeExists("selections"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"));
    }

//    @DirtiesContext
    @Test
    void processFindFormEmpty() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
        //Verifica que se le pase el argument capture
        then(service).should().findOwnerByLastName(argumentCaptor.capture());
        //Verifica que el parametro que se le pasa no sea nulo como se le pasa al controller
        assertThat(argumentCaptor.getValue()).isEqualTo("");
    }

//    @DirtiesContext
    @Test
    void processCreationForm() throws Exception {
        mockMvc.perform(post("/owners/new")
                            .param("firstName","Franky")
                            .param("lastName","Rivers")
                            .param("address","Avenida Siempre Viva")
                            .param("city","Springfield")
                            .param("telephone","321789560"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void processCreationFormWrong() throws Exception {
        mockMvc.perform(post("/owners/new")
                            .param("address", "Avenida Siempre Viva")
                            .param("city", "Springfield")
                            .param("telephone", "321789560"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner","firstName","lastName"));
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit",1)
                            .param("firstName","Franky")
                            .param("lastName","Rivers")
                            .param("address","Avenida Siempre Viva")
                            .param("city","Springfield")
                            .param("telephone","321789560"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));//Aqui no hay que cambiar nada
    }

    @Test
    void processUpdateOwnerFormWrong() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit",1)
                .param("address", "Avenida Siempre Viva")
                .param("city", "Springfield")
                .param("telephone", "321789560"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner","firstName","lastName"));
    }
}