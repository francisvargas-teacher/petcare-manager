package br.com.petcare;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Atendimento;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.ServicoClinica;
import br.com.petcare.model.Tutor;
import br.com.petcare.repository.BancoMemoria;
import br.com.petcare.service.ClinicaService;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        BancoMemoria banco = new BancoMemoria();
        ClinicaService clinicaService = new ClinicaService(banco);

        // ===== Cadastro de tutores =====
        Tutor tutor1 = clinicaService.cadastrarTutor("Ana Souza", "5199999-9999", "ana@email.com");
        Tutor tutor2 = clinicaService.cadastrarTutor("Carlos Lima", "5188888-8888", "carlos@email.com");

        // ===== Cadastro de animais =====
        Animal cachorro = clinicaService.cadastrarCachorro("Thor", 3, tutor1, "Médio");
        Animal gato = clinicaService.cadastrarGato("Mingau", 2, tutor2, true);

        // ===== Agendamento de consultas =====
        clinicaService.agendarConsulta(cachorro, LocalDateTime.now().plusDays(1), "Vacinação anual");
        clinicaService.agendarConsulta(gato, LocalDateTime.now().plusDays(2), "Consulta de rotina");

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

        // ===== Feature 07: Cadastro de serviços =====
        System.out.println("\n=== SERVIÇOS CADASTRADOS ===");
        ServicoClinica consulta    = clinicaService.cadastrarServico("Consulta",  "Consulta clínica geral",        120.00, 30);
        ServicoClinica banho       = clinicaService.cadastrarServico("Banho",     "Banho completo",                  80.00, 60);
        ServicoClinica tosa        = clinicaService.cadastrarServico("Tosa",      "Tosa higiênica ou completa",      70.00, 45);
        ServicoClinica vacinacao   = clinicaService.cadastrarServico("Vacinação", "Aplicação de vacina",             60.00, 15);
        ServicoClinica cirurgia    = clinicaService.cadastrarServico("Cirurgia",  "Procedimento cirúrgico geral",  1500.00, 120);

        for (ServicoClinica s : clinicaService.listarServicos()) {
            System.out.println(s);
        }

        // ===== Feature 07: Abertura de atendimentos =====
        System.out.println("\n=== ATENDIMENTOS ===");

        // Atendimento 1 — Thor: banho + tosa + vacinação
        Atendimento atendimento1 = clinicaService.abrirAtendimento(cachorro, LocalDateTime.now());
        clinicaService.adicionarServicoAoAtendimento(atendimento1, banho);
        clinicaService.adicionarServicoAoAtendimento(atendimento1, tosa);
        clinicaService.adicionarServicoAoAtendimento(atendimento1, vacinacao);
        System.out.println(atendimento1);
        System.out.printf("Valor total do atendimento 1: R$%.2f%n",
                clinicaService.calcularValorTotalAtendimento(atendimento1));

        // Atendimento 2 — Mingau: consulta + vacinação
        Atendimento atendimento2 = clinicaService.abrirAtendimento(gato, LocalDateTime.now());
        clinicaService.adicionarServicoAoAtendimento(atendimento2, consulta);
        clinicaService.adicionarServicoAoAtendimento(atendimento2, vacinacao);
        System.out.println(atendimento2);
        System.out.printf("Valor total do atendimento 2: R$%.2f%n",
                clinicaService.calcularValorTotalAtendimento(atendimento2));

        // ===== Listagem geral de atendimentos =====
        System.out.println("\n=== LISTA DE TODOS OS ATENDIMENTOS ===");
        for (Atendimento a : clinicaService.listarAtendimentos()) {
            System.out.println(a);
        }
    }
}
