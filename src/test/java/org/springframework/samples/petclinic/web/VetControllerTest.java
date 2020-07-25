package org.springframework.samples.petclinic.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    public static final String VETS_VET_LIST = "vets/vetList";
    List<Vet> vetList;

    @Mock
    Map<String,Object> model;

    @Mock
    ClinicService service;

    @InjectMocks
    VetController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        List<Vet> vetList = new ArrayList<>();
        vetList.add(new Vet());
        given(service.findVets()).willReturn(vetList);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));
    }

    @Test
    void showVetList() {
        //when
        String view = controller.showVetList(model);
        //then
        //Se verifica que se este realizando la interaccion con el Mock
        then(service).should().findVets();
        then(model).should().put(anyString(), any());
        assertThat(view).isEqualTo(VETS_VET_LIST);
    }

    @Test
    void showResourcesVetList() {
        //when
        Vets vetsReturned = controller.showResourcesVetList();
        //then
        then(service).should().findVets();
        assertThat(vetsReturned.getVetList()).hasSize(1);
    }
}