import java.util.Scanner;

public class Principal {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        PainelAeroporto painel = new PainelAeroporto();
        int opcao = 0; // Adiciona voos

        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE PAINEL DE VOOS - AEROPORTO");
            System.out.println("========================================");
            System.out.println("1. Cadastrar novo voo");
            System.out.println("2. Consultar painel de voos");
            System.out.println("3. Alterar informações de um voo");
            System.out.println("4. Alterar o status de um voo");
            System.out.println("5. Excluir um voo");
            System.out.println("6. Consultar um voo específico");
            System.out.println("7. Encerrar o sistema");
            System.out.print("Escolha uma opção: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine(); // Limpa o buffer do scanner
            } else {
                System.out.println("Opção inválida. Por favor, digite um número de 1 a 7.");
                sc.nextLine(); // Limpa o buffer do scanner
                continue; // Volta ao início do loop
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- 1. CADASTRAR NOVO VOO ---");
                    System.out.print("Número do voo (ex: LA3456): ");
                    String num = sc.nextLine();
                    System.out.print("Companhia aérea: ");
                    String comp = sc.nextLine();
                    System.out.print("Horário de embarque (ex: 14:30): ");
                    String hEmb = sc.nextLine();
                    System.out.print("Horário de saída (ex: 15:10): ");
                    String hSai = sc.nextLine();
                    System.out.print("Destino: ");
                    String dest = sc.nextLine();

                    painel.cadastrarVoo(num, comp, hEmb, hSai, dest);
                    break;
                case 2:
                    painel.exibirPainel();
                    break;
                case 3:
                    System.out.println("\n--- 3. ALTERAR INFORMAÇÕES DE UM VOO ---");
                    System.out.print("Informe o número do voo que deseja alterar: ");
                    String numAlt = sc.nextLine();
                    
                    Voo vBusca = painel.buscarVoo(numAlt);
                    if (vBusca == null) {
                        System.out.println("Erro: Voo não encontrado.");
                        break;
                    }
                    if (vBusca.getStatus().equalsIgnoreCase("DECOLOU")) {
                        System.out.println("Erro: Não é permitido alterar dados de um voo que já decolou.");
                        break;
                    }

                    System.out.print("Nova companhia aérea: ");
                    String novaComp = sc.nextLine();
                    System.out.print("Novo horário de embarque (ex: 14:45): ");
                    String novoHEmb = sc.nextLine();
                    System.out.print("Novo horário de saída (ex: 15:25): ");
                    String novoHSai = sc.nextLine();
                    System.out.print("Novo destino: ");
                    String novoDest = sc.nextLine();

                    painel.alterarVoo(numAlt, novaComp, novoHEmb, novoHSai, novoDest);
                    break;

                case 4:
                    System.out.println("\n--- 4. ALTERAR STATUS DE UM VOO ---");
                    System.out.print("Informe o número do voo: ");
                    String numStat = sc.nextLine();
                    
                    Voo vStat = painel.buscarVoo(numStat);
                    if (vStat == null) {
                        System.out.println("Erro: Voo não encontrado.");
                        break;
                    }
                    if (vStat.getStatus().equalsIgnoreCase("DECOLOU")) {
                        System.out.println("Erro: O voo já está no status DECOLOU. Não há próximo status.");
                        break;
                    }

                    String proximo = painel.getProximoStatus(vStat.getStatus());
                    System.out.println("Status atual: " + vStat.getStatus());
                    System.out.println("Próximo status permitido: " + proximo);
                    System.out.print("Deseja confirmar a alteração para '" + proximo + "'? (S/N): ");
                    String confirmaStatus = sc.nextLine();

                    if (confirmaStatus.equalsIgnoreCase("S")) {
                        painel.avancarStatusVoo(numStat);
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                    break;
                case 5:
                    System.out.println("\n--- 5. EXCLUIR VOO ---");
                    System.out.print("Informe o número do voo que deseja excluir: ");
                    String numExc = sc.nextLine();
                    
                    Voo vExc = painel.buscarVoo(numExc);
                    if (vExc == null) {
                        System.out.println("Erro: Voo não encontrado.");
                        break;
                    }
                    if (!vExc.getStatus().equalsIgnoreCase("DECOLOU")) {
                        System.out.println("Erro: Exclusão negada. O voo ainda não decolou (Status atual: " + vExc.getStatus() + ").");
                        break;
                    }

                    System.out.print("Tem certeza que deseja excluir o voo " + vExc.getNumero() + "? (S/N): ");
                    String confirmaExc = sc.nextLine();

                    if (confirmaExc.equalsIgnoreCase("S")) {
                        painel.excluirVoo(numExc);
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                    break;
                case 6:
                    System.out.println("\n--- 6. CONSULTAR VOO ESPECÍFICO ---");
                    System.out.print("Informe o número do voo: ");
                    String numCons = sc.nextLine();
                    painel.consultarEspecifico(numCons);
                    break;

                case 7:
                    System.out.println("\nEncerrando o sistema!");
                    break;
            }
        } while (opcao != 7);
        
        sc.close();
        
    }
}
