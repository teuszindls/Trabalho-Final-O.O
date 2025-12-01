package Servicos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SistemaUsuarios {


    private List<Passageiro> listaPassageiros = new ArrayList<>();
    

    private List<Motorista> listaMotoristas = new ArrayList<>();
    

    private Random random = new Random();

    public SistemaUsuarios() {
  
        listaPassageiros.add(new Passageiro("Ana Silva", "111.111.111-11", "ana@email.com", 150));
        listaPassageiros.add(new Passageiro("Bruno Costa", "222.222.222-22", "bruno@email.com", 45));
        listaPassageiros.add(new Passageiro("Carla Mendes", "333.333.333-33", "carla@email.com", 200));
        listaPassageiros.add(new Passageiro("Diego Pereira", "444.444.444-44", "diego@email.com", 80));
        listaPassageiros.add(new Passageiro("Elisa Farias", "555.555.555-55", "elisa@email.com", 120));
        listaPassageiros.add(new Passageiro("Gabriel Rocha", "666.666.666-66", "gabriel@email.com", 10)); 

   
        listaMotoristas.add(new Motorista("Henrique Santos", "777.777.777-77", "henrique@email.com", "ABC-1234", 4.8));
        listaMotoristas.add(new Motorista("Ingrid Oliveira", "888.888.888-88", "ingrid@email.com", "DEF-5678", 4.5));
        listaMotoristas.add(new Motorista("Julio Vaz", "999.999.999-99", "julio@email.com", "GHI-9012", 4.9));
        listaMotoristas.add(new Motorista("Leticia Motta", "000.000.000-00", "leticia@email.com", "JKL-3456", 4.7));
        listaMotoristas.add(new Motorista("Marcelo Neves", "123.456.789-01", "marcelo@email.com", "MNO-7890", 4.6));
        listaMotoristas.add(new Motorista("Nathalia Cruz", "987.654.321-09", "nathalia@email.com", "PQR-1357", 5.0)); 
        
        listaMotoristas.add(new Motorista("Mateus Fernandes", "562.879.556-00", "mateus@email.com", "ABB1254", 4.0));
        listaMotoristas.add(new Motorista("Ingrid Costa", "889.321.845-89", "ingridcosta@email.com", "DDF-5901", 2.0));
        listaMotoristas.add(new Motorista("Matheus Vinicius", "612.234.912-90", "matheusvinicius@email.com", "GHI-9012", 4.9));
        listaMotoristas.add(new Motorista("Leticia Cabello", "957.171.555-68", "leticiaCa@email.com", "LMD-8456", 4.0));
        listaMotoristas.add(new Motorista("Pablo Vitta", "246.915.891-77", "pablovitta@email.com", "MQP-8952", 3.5));
        listaMotoristas.add(new Motorista("Charles Bronx", "573.171.157-66", "charlexxx@email.com", "QFG-8634", 3.0)); 

         listaPassageiros.add(new Passageiro("Hybson Murilo", "852.486.851-69", "y@email.com", 200));
        listaPassageiros.add(new Passageiro("Alessandro Anjos", "845.575.964-75", "alessandro@email.com", 500));
        listaPassageiros.add(new Passageiro("Carlos Pereira", "546.789.123-45", "carlos@email.com", 50));
        listaPassageiros.add(new Passageiro("Denis Silva", "854.123.456-78", "denis@email.com", 80));
        listaPassageiros.add(new Passageiro("Elis Regina", "945.485.785-47", "elis@email.com", 125));
        listaPassageiros.add(new Passageiro("Betriz Motta", "675.475.746-68", "biatt@email.com", 40)); 
    }
     public void chamarUsuário() {
        
        int indicePassageiro = random.nextInt(listaPassageiros.size());
        Passageiro passageiroSorteado = listaPassageiros.get(indicePassageiro);

        int indiceMotorista = random.nextInt(listaMotoristas.size());
        Motorista motoristaSorteado = listaMotoristas.get(indiceMotorista);
}
}
