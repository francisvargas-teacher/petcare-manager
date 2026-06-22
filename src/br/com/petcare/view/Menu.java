package br.com.petcare.view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Tutor;
import br.com.petcare.service.ClinicaService;

//Feature 14: Estoque de medicmentos

import br.com.petcare.model.Medicamento;
import java.time.LocalDate;

public class Menu {
    private Scanner scanner;
    private ClinicaService clinicaService;

    public Menu(ClinicaService clinicaService) {
        this.scanner = new Scanner(System.in);
        this.clinicaService = clinicaService;
    }

    public void iniciar() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n==================================");
            System.out.println("  🐾 Bem-vindo ao PetCare Manager 🐾");
            System.out.println("==================================");
            System.out.println("1. Cadastrar tutor");
            System.out.println("2. Cadastrar cachorro");
            System.out.println("3. Cadastrar gato");
            System.out.println("4. Listar animais");
            System.out.println("5. Listar consultas");
            System.out.println("6. Agendar consulta");
            System.out.println("7. Cadastrar medicamento");
            System.out.println("8. Listar medicamento");
            System.out.println("9. Entrada de estoque");
            System.out.println("10. Saída de estoque");
            System.out.println("11. Medicamentos com estoque baixo");
            System.out.println("12. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado!

            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO TUTOR ---");
                    System.out.print("Nome: ");
                    String nomeTutor = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine();
                    
                    clinicaService.cadastrarTutor(nomeTutor, telefone, email);
                    System.out.println("✅ Tutor cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- NOVO CACHORRO ---");
                    System.out.print("Nome do cachorro: ");
                    String nomeCachorro = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeCachorro = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    System.out.print("Porte (Pequeno/Médio/Grande): ");
                    String porte = scanner.nextLine();
                    
                    Tutor tutorEscolhidoC = selecionarTutor();
                    if (tutorEscolhidoC != null) {
                        clinicaService.cadastrarCachorro(nomeCachorro, idadeCachorro, tutorEscolhidoC, porte);
                        System.out.println("✅ Cachorro cadastrado com sucesso!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- NOVO GATO ---");
                    System.out.print("Nome do gato: ");
                    String nomeGato = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeGato = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    System.out.print("É castrado? (S/N): ");
                    boolean castrado = scanner.nextLine().equalsIgnoreCase("S");
                    
                    Tutor tutorEscolhidoG = selecionarTutor();
                    if (tutorEscolhidoG != null) {
                        clinicaService.cadastrarGato(nomeGato, idadeGato, tutorEscolhidoG, castrado);
                        System.out.println("✅ Gato cadastrado com sucesso!");
                    }
                    break;

                case 4:
                    System.out.println("\n=== LISTA DE ANIMAIS ===");
                    for (Animal animal : clinicaService.listarAnimais()) {
                        System.out.println(animal);
                        System.out.println("Som: " + animal.emitirSom());
                    }
                    break;

                case 5:
                    System.out.println("\n=== LISTA DE CONSULTAS ===");
                    for (Consulta consulta : clinicaService.listarConsultas()) {
                        System.out.println(consulta);
                    }
                    break;

                case 6:
                    System.out.println("\n--- AGENDAR CONSULTA ---");
                    Animal animalEscolhido = selecionarAnimal();
                    if (animalEscolhido != null) {
                        System.out.print("Motivo da consulta: ");
                        String motivo = scanner.nextLine();
                        // Simplificação: Agendando automaticamente para o dia seguinte para evitar erros de formatação de data no Scanner
                        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);
                        
                        clinicaService.agendarConsulta(animalEscolhido, dataHora, motivo);
                        System.out.println("✅ Consulta agendada com sucesso para amanhã!");
                    }
                    break;

                case 7:
                    cadastrarMedicamento();
                    break;

                case 8:
                    listarMedicamentos();
                    break;

                case 9:
                    entradaEstoqueMedicamento();
                    break;

                case 10:
                    saidaEstoqueBaixo();
                    break;

                case 11:
                    listarEstoqueBaixo();
                    break;

                case 12:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    executando = false;
                    break;

                default:
                    System.out.println("\n❌ Opção inválida! Digite um número de 1 a 12.");
            }
        }
        scanner.close();
    }

    // --- MÉTODOS AUXILIARES PARA FACILITAR O MENU ---

    private Tutor selecionarTutor() {
        List<Tutor> tutores = clinicaService.listarTutores();
        if (tutores.isEmpty()) {
            System.out.println("❌ Nenhum tutor cadastrado! Cadastre um tutor primeiro.");
            return null;
        }

        System.out.println("\nSelecione o Tutor pelo número:");
        for (int i = 0; i < tutores.size(); i++) {
            System.out.println((i + 1) + ". " + tutores.get(i).getNome());
        }
        System.out.print("Opção: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpa o buffer

        if (index >= 0 && index < tutores.size()) {
            return tutores.get(index);
        } else {
            System.out.println("❌ Opção inválida.");
            return null;
        }
    }

    private Animal selecionarAnimal() {
        List<Animal> animais = clinicaService.listarAnimais();
        if (animais.isEmpty()) {
            System.out.println("❌ Nenhum animal cadastrado! Cadastre um animal primeiro.");
            return null;
        }

        System.out.println("\nSelecione o Animal pelo número:");
        for (int i = 0; i < animais.size(); i++) {
            System.out.println((i + 1) + ". " + animais.get(i).getNome());
        }
        System.out.print("Opção: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpa o buffer

        if (index >= 0 && index < animais.size()) {
            return animais.get(index);
        } else {
            System.out.println("❌ Opção inválida.");
            return null;
        }
    }

    private void cadastrarMedicamento(){
        System.out.println("\n--- NOVO MEDICAMENTO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();

        System.out.println("Concentração: ");
        String concetracao = scanner.nextLine();

        System.out.print("Quantidade inicial: ");
        int quantidadeInicial = scanner.nextInt();

        System.out.print("Estoque mínimo: ");
        int estoqueMinimo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data de validade (AAAA-MM-DD): ");
        String dataTexto = scanner.nextLine();

        LocalDate dataValidade = LocalDate.parse(dataTexto);

        clinicaService.cadastrarMedicamento(nome, descricao, fabricante, concetracao, quantidadeInicial, estoqueMinimo, dataValidade);

        System.out.println("Medicamento cadastrado com sucesso!");
    }

    private void listarMedicamentos(){
        System.out.println("\n=== ESTOQUE DE MEDICAMENTOS ===");

        for(Medicamento medicamento : clinicaService.listarMedicamentos()){
            System.out.println(medicamento);
        }
    }

    private void entradaEstoqueMedicamento(){
        System.out.println("\n=== ENTRADA DE ESTOQUE ===");

        System.out.print("ID do medicamento: ");
        int id = scanner.nextInt();

        System.out.print("Quantidade para adicionar: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        clinicaService.adicionarEstoqueMedicamento(id, quantidade);

        System.out.println("Estoque atualizado com sucesso!");
    }

    private void saidaEstoqueBaixo(){
        System.out.println("\n === SAÍDA DE ESTOQUE ===");

        System.out.print("ID do medicamento: ");
        int id = scanner.nextInt();

        System.out.print("Quantidade para retirar: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        clinicaService.retirarEstoqueMedicamento(id, quantidade);

        System.out.println("Saida registrada com sucesso!");
    }

    private void listarEstoqueBaixo(){
        System.out.println("\n ===MEDICAMENTOS COM ESTOQUE BAIXO===");

        for (Medicamento medicamento : clinicaService.listarMedicamentosComEstoqueBaixo()){
            System.out.println(medicamento);
        }
    }
}