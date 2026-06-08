package br.com.petcare;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Tutor;
import br.com.petcare.repository.BancoMemoria;
import br.com.petcare.service.ClinicaService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BancoMemoria banco = new BancoMemoria();
        ClinicaService clinicaService = new ClinicaService(banco);

        Tutor tutor1 = clinicaService.cadastrarTutor(
                "Ana Souza",
                "5199999-9999",
                "ana@email.com"
        );

        Tutor tutor2 = clinicaService.cadastrarTutor(
                "Carlos Lima",
                "5188888-8888",
                "carlos@email.com"
        );

        Animal cachorro = clinicaService.cadastrarCachorro(
                "Thor",
                3,
                tutor1,
                "Médio"
        );

        Animal gato = clinicaService.cadastrarGato(
                "Mingau",
                2,
                tutor2,
                true
        );

        clinicaService.agendarConsulta(
                cachorro,
                LocalDateTime.now().plusDays(1),
                "Vacinação anual"
        );

        clinicaService.agendarConsulta(
                gato,
                LocalDateTime.now().plusDays(2),
                "Consulta de rotina"
        );

        System.out.println("=== TUTORES ===");
        for (Tutor tutor : clinicaService.listarTutores()) {
            System.out.println(tutor);
        }

        System.out.println("\n=== ANIMAIS ===");
        for (Animal animal : clinicaService.listarAnimais()) {
            System.out.println(animal);
            System.out.println("Som: " + animal.emitirSom());
        }

        System.out.println("\n=== CONSULTAS ===");
        for (Consulta consulta : clinicaService.listarConsultas()) {
            System.out.println(consulta);
        }
    }
}
