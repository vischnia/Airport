import java.util.ArrayList;
import java.util.List;

public class PainelAeroporto {
    private List<Voo> listaVoos;
    
    public PainelAeroporto() {
        this.listaVoos = new ArrayList<>();
    }
    
    //sequência obrigatória dos status
    public String getProximoStatus(String statusAtual) {
        if (statusAtual.equalsIgnoreCase("PREVISTO")) {
            return "CONFIRMADO";
        } else if (statusAtual.equalsIgnoreCase("CONFIRMADO")) {
            return "EMBARQUE EM CURSO";
        } else if (statusAtual.equalsIgnoreCase("EMBARQUE EM CURSO")) {
            return "EMBARQUE ENCERRADO";
        } else if (statusAtual.equalsIgnoreCase("EMBARQUE ENCERRADO")) {
            return "DECOLOU";
        }
        return null; // DECOLOU não tem próximo status
    }
    
    // Busca um voo pelo número
    public Voo buscarVoo(String numero) {
        for (Voo v : listaVoos) {
            if (v.getNumero().equalsIgnoreCase(numero)) {
                return v;
            }
        }
        return null;
    }

    // 5.1 Cadastrar novo voo
    public boolean cadastrarVoo(String numero, String companhia, String horarioEmbarque, String horarioSaida, String destino) {
        if (numero.isEmpty() || companhia.isEmpty() || horarioEmbarque.isEmpty() || horarioSaida.isEmpty() || destino.isEmpty()) {
            System.out.println("Erro: Todos os campos obrigatórios devem ser preenchidos.");
            return false;
        }

        if (buscarVoo(numero) != null) {
            System.out.println("Erro: Já existe um voo cadastrado com o número " + numero.toUpperCase() + ".");
            return false;
        }

        // Validação de horários
        if (horarioEmbarque.compareTo(horarioSaida) >= 0) {
            System.out.println("Erro: O horário de embarque deve ser anterior ao horário de saída.");
            return false;
        }

        Voo novoVoo = new Voo(numero, companhia, horarioEmbarque, horarioSaida, destino);
        listaVoos.add(novoVoo);
        System.out.println("Sucesso: Voo " + novoVoo.getNumero() + " cadastrado com status PREVISTO.");
        return true;
    }

    // 5.2 Apresentar o painel de voos
    public void exibirPainel() {
        if (listaVoos.isEmpty()) {
            System.out.println("\n--- Painel de Voos Vazio ---");
            return;
        }

        System.out.println("\n==================================================================================================");
        System.out.println("                                     PAINEL DE VOOS DE SAÍDA                                      ");
        System.out.println("==================================================================================================");
        System.out.printf("%-10s | %-20s | %-18s | %-16s | %-20s | %-20s\n", 
                "NÚMERO", "COMPANHIA", "H. EMBARQUE", "H. SAÍDA", "DESTINO", "STATUS");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Voo v : listaVoos) {
            System.out.printf("%-10s | %-20s | %-18s | %-16s | %-20s | %-20s\n",
                    v.getNumero(),
                    v.getCompanhia(),
                    v.getHorarioEmbarque(),
                    v.getHorarioSaida(),
                    v.getDestino(),
                    v.getStatus());
        }
        System.out.println("==================================================================================================");
    }

    // 5.3 Alterar informações de um voo
    public boolean alterarVoo(String numero, String novaCompanhia, String novoHorarioEmbarque, String novoHorarioSaida, String novoDestino) {
        Voo v = buscarVoo(numero);
        if (v == null) {
            System.out.println("Erro: Voo não encontrado.");
            return false;
        }

        if (v.getStatus().equalsIgnoreCase("DECOLOU")) {
            System.out.println("Erro: Não é permitido alterar dados cadastrais de um voo que já decolou.");
            return false;
        }

        if (novaCompanhia.isEmpty() || novoHorarioEmbarque.isEmpty() || novoHorarioSaida.isEmpty() || novoDestino.isEmpty()) {
            System.out.println("Erro: Todos os campos devem ser preenchidos.");
            return false;
        }

        if (novoHorarioEmbarque.compareTo(novoHorarioSaida) >= 0) {
            System.out.println("Erro: O horário de embarque deve ser anterior ao horário de saída.");
            return false;
        }

        v.setCompanhia(novaCompanhia);
        v.setHorarioEmbarque(novoHorarioEmbarque);
        v.setHorarioSaida(novoHorarioSaida);
        v.setDestino(novoDestino);
        System.out.println("Sucesso: Dados do voo " + v.getNumero() + " atualizados com sucesso.");
        return true;
    }

    // 5.4 Alterar o status de um voo
    public boolean avancarStatusVoo(String numero) {
        Voo v = buscarVoo(numero);
        if (v == null) {
            System.out.println("Erro: Voo não encontrado.");
            return false;
        }

        String proximo = getProximoStatus(v.getStatus());
        if (proximo == null) {
            System.out.println("Erro: O voo já está no status máximo (DECOLOU) e não pode avançar.");
            return false;
        }

        v.setStatus(proximo);
        System.out.println("Sucesso: Status do voo " + v.getNumero() + " atualizado para: " + proximo);
        return true;
    }
}