package ui;

// Importações organizadas
import model.*;
import strategy.*;
import factory.*;
import observer.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Classe JanelaPrincipal
 *
 * Responsável por montar a interface gráfica da aplicação com Swing.
 * Permite a interação com os sensores de lixo e com o sistema de coleta,
 * oferecendo ações como aumentar o nível dos sensores, coletar o lixo
 * e restaurar o estado anterior com base no padrão Memento.
 */
public class JanelaPrincipal extends JFrame {
    private SistemaColetaLixo sistema;                  // Instância principal que coordena sensores, caminhões e estratégia
    private List<JProgressBar> barras;                  // Representação gráfica do nível de lixo de cada sensor
    private JComboBox<String> estrategiaCombo;          // Menu para escolher a estratégia de coleta
    private EstrategiaColeta estrategiaSelecionada;     // Estratégia escolhida no momento

    /**
     * Construtor da JanelaPrincipal
     *
     * Inicializa os sensores, caminhões, estratégia padrão e monta a interface gráfica.
     * Registra os sensores no sistema e prepara os componentes de UI.
     */
    public JanelaPrincipal() {
        setTitle("Sistema de Coleta de Lixo Inteligente");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação dos sensores usando a fábrica
        FabricaSensorCapacidade fabricaSensor = new FabricaSensorCapacidade(10);
        SensorLixo s1 = fabricaSensor.criar();
        SensorLixo s2 = fabricaSensor.criar();
        SensorLixo s3 = fabricaSensor.criar();
        List<SensorLixo> sensores = Arrays.asList(s1, s2, s3);

        // Criação dos caminhões usando a fábrica
        FabricaCaminhaoSimples fabricaCaminhao = new FabricaCaminhaoSimples();
        CaminhaoColeta caminhao = fabricaCaminhao.criar();
        List<CaminhaoColeta> caminhoes = Arrays.asList(caminhao);

        // Define a estratégia padrão como "coleta por nível mais alto"
        estrategiaSelecionada = new ColetaPorNivelMaisAlto();

        // Inicializa o sistema com sensores, caminhões e estratégia
        sistema = new SistemaColetaLixo(sensores, caminhoes, estrategiaSelecionada);
        sistema.salvarEstado(); // Salva o estado inicial para permitir restauração futura

        // Painel de sensores: mostra as barras de progresso
        JPanel painelSensores = new JPanel();
        painelSensores.setLayout(new GridLayout(0, 1));
        barras = new ArrayList<>();

        for (SensorLixo sensor : sensores) {
            JProgressBar barra = new JProgressBar(0, 10);
            barra.setValue(sensor.getNivel());
            barra.setStringPainted(true);
            painelSensores.add(barra);
            barras.add(barra);
        }

        add(painelSensores, BorderLayout.CENTER);

        // Painel de botões com funcionalidades principais
        JPanel painelBotoes = new JPanel();

        // Botão para simular o aumento do nível de lixo em cada sensor
        JButton btnAumentar = new JButton("Simular Aumento");
        btnAumentar.addActionListener((ActionEvent e) -> {
            Random rand = new Random();
            for (SensorLixo sensor : sistema.getSensores()) {
                int aumento = rand.nextInt(5) + 1;
                if (sensor instanceof SensorLixoCapacidade) {
                    SensorLixoCapacidade s = (SensorLixoCapacidade) sensor;
                    if (s.getNivel() < 10) {
                        s.simularAumento(aumento); // aumenta o nível
                    }
                }
            }
            atualizarBarras();             // Atualiza visualmente
            sistema.salvarEstado();        // Salva novo estado no memento
        });
        painelBotoes.add(btnAumentar);

        // Botão para simular a coleta de lixo usando a estratégia selecionada
        JButton btnColetar = new JButton("Coletar Lixo");
        btnColetar.addActionListener((ActionEvent e) -> {
            sistema.setEstrategia(estrategiaSelecionada);
            Rota rota = estrategiaSelecionada.gerarRota(sistema.getSensores());

            // Cria uma thread para simular a coleta com atraso entre sensores
            new Thread(() -> {
                for (SensorLixo sensor : rota.getPontos()) {
                    if (sensor instanceof SensorLixoCapacidade) {
                        SensorLixoCapacidade s = (SensorLixoCapacidade) sensor;
                        s.setNivel(0); // Esvazia o sensor
                        atualizarBarras(); // Atualiza visualmente
                        try {
                            Thread.sleep(1000); // Pausa de 1 segundo entre cada coleta
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                sistema.salvarEstado(); // Salva estado após coleta
            }).start();
        });
        painelBotoes.add(btnColetar);

        // Botão para restaurar o estado anterior salvo (Memento)
        JButton btnRestaurar = new JButton("Restaurar Estado");
        btnRestaurar.addActionListener((ActionEvent e) -> {
            sistema.restaurarEstado();
            atualizarBarras();
        });
        painelBotoes.add(btnRestaurar);

        // ComboBox para selecionar a estratégia desejada
        estrategiaCombo = new JComboBox<>(new String[]{"Nível Mais Alto", "Sequencial"});
        estrategiaCombo.addActionListener((ActionEvent e) -> {
            String escolha = (String) estrategiaCombo.getSelectedItem();
            if ("Nível Mais Alto".equals(escolha)) {
                estrategiaSelecionada = new ColetaPorNivelMaisAlto();
            } else {
                estrategiaSelecionada = new ColetaSequencial();
            }
        });
        painelBotoes.add(estrategiaCombo);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Atualiza a interface gráfica das barras de progresso com os níveis atuais dos sensores
     */
    private void atualizarBarras() {
        List<SensorLixo> sensores = sistema.getSensores();
        for (int i = 0; i < sensores.size(); i++) {
            SensorLixo sensor = sensores.get(i);
            if (sensor instanceof SensorLixoCapacidade) {
                SensorLixoCapacidade s = (SensorLixoCapacidade) sensor;
                barras.get(i).setValue(s.getNivel());

                // Se estiver cheio, destaca visualmente em vermelho e mostra alerta
                if (s.getNivel() >= 10) {
                    barras.get(i).setForeground(Color.RED);
                    barras.get(i).setString("Lixeira " + (i + 1) + " cheia!");
                } else {
                    barras.get(i).setForeground(Color.GREEN);
                    barras.get(i).setString(null);
                }
            }
        }
    }

    /**
     * Método principal para iniciar a interface gráfica da aplicação
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal app = new JanelaPrincipal();
            app.setVisible(true);
        });
    }
}
